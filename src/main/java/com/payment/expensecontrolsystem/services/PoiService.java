package com.payment.expensecontrolsystem.services;

import com.payment.expensecontrolsystem.config.PoiSingleton;
import com.payment.expensecontrolsystem.config.SecretsConfig;
import com.payment.expensecontrolsystem.enums.FileExtensions;
import com.payment.expensecontrolsystem.interfaces.IFileManagementService;
import com.payment.expensecontrolsystem.models.Invoices;
import com.payment.expensecontrolsystem.models.Product;
import com.payment.expensecontrolsystem.utils.DateUtils;
import com.payment.expensecontrolsystem.utils.SpreadSheetsStylesPatterns;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class PoiService implements IFileManagementService {  //TODO: implementation with s3Service to upload files to s3 bucket
    private final SecretsConfig secretsConfig;
    private static final Logger logger = LoggerFactory.getLogger(PoiService.class.getName());

    public PoiService(SecretsConfig secretsConfig){
        this.secretsConfig = secretsConfig;
    }

    @Override
    public File createInvoiceFileXlsx(String fileName, Invoices dto) {
        String date = new Date().toString().replaceAll(" ", "_").replaceAll(":", "_");
        File insightsFile = new File(this.secretsConfig.getFullPath() + fileName+"_"+dto.getId()+"_"+date+FileExtensions.XLSX.getExtension());
        logger.info("gerando arquivo {}.xlsx", insightsFile.getName());
        FileOutputStream fileOutputStream = null;
        Workbook workbook = null;

        try {
            workbook = PoiSingleton.getInstance();
            Sheet sheet = workbook.createSheet("invoice");
            fileOutputStream = generateSheet(workbook, sheet, dto, insightsFile);
            workbook.write(fileOutputStream);
        } catch (FileNotFoundException e) {
            logger.error("erro ao buscar arquivo de nome {}, gerando o seguinte erro: {}", insightsFile.getName(), e.getMessage());
        } catch (IOException e) {
            logger.error("erro ao acessar o arquivo {}, gerando o seguinte erro: {}", insightsFile.getName(), e.getMessage());
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (fileOutputStream != null) fileOutputStream.close();
                if (workbook != null) workbook.close();
            } catch (IOException e) {
                logger.error("Erro ao fechar recursos: {}", e.getMessage());
            }
        }
        return insightsFile;
    }


    @Override
    public File CreateManyInvoicesFileXlsx(String fileName, List<Invoices> invoices) {
        String date = new Date().toString().replaceAll(" ","_").replaceAll(":","_");
        File file = new File(this.secretsConfig.getFullPath() + fileName+"_"+"_"+date+FileExtensions.XLSX.getExtension());
        logger.info("gerando arquivo {}.xlsx", file.getName());
        FileOutputStream fileOutputStream = null;
        Workbook workbook = null;

        try {
            workbook = PoiSingleton.getInstance();
            Sheet sheet;

            for(Invoices invoice : invoices){
                sheet = workbook.createSheet("invoice_"+DateUtils.normalizeDate(invoice.getCreatedAt()).replaceAll("/","_")+"_"+invoice.getId());
                fileOutputStream = generateSheet(workbook, sheet, invoice, file);
                workbook.write(fileOutputStream);
            }
        }
        catch(IOException e){
            logger.error("erro ao acessar arquivo: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return file;
    }

    private FileOutputStream generateSheet(Workbook workbook, Sheet sheet, Invoices invoice, File file) throws FileNotFoundException {
        CellStyle titleStyle = SpreadSheetsStylesPatterns.generateTitleStyle(workbook);

        // Título mesclado na primeira linha
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Nota Fiscal: " + invoice.getId() +
                " - forma de pagamento: " + invoice.getPaymentMethod().getName() +
                " - data: " + DateUtils.normalizeDate(invoice.getCreatedAt()));
        titleCell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));

        // Estilo para o cabeçalho da tabela (linha 1)
        CellStyle headerStyle = SpreadSheetsStylesPatterns.generateHeaderStyle(workbook);

        // Estilo para células padrão
        CellStyle cellStyle = SpreadSheetsStylesPatterns.generateDataStyle(workbook);

        String[][] table = generateTableFormatModel(invoice);

        Row headerRow = sheet.createRow(1);
        for (int i = 0; i < table[0].length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(table[0][i]);
            cell.setCellStyle(headerStyle);
        }
        //estilos monetários
        CellStyle moneyStyle = SpreadSheetsStylesPatterns.generateMoneyStyle(workbook);
        // Dados (linhas 2+)
        for (int i = 1; i < table.length; i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < table[i].length; j++) {
                Cell cell = row.createCell(j);
                if(j == 3 || j == 4) {
                    BigDecimal valor = new BigDecimal(table[i][j]);
                    cell.setCellValue(valor.doubleValue());
                    cell.setCellStyle(moneyStyle);
                }
                else{
                    cell.setCellValue(table[i][j]);
                    cell.setCellStyle(cellStyle);
                }
            }
        }
        // linha de valor total (linha 7)
        Row totalValueLabel = sheet.createRow(table.length + 1);
        Cell totalValueLabelCell = totalValueLabel.createCell(0);
        totalValueLabelCell.setCellValue("Valor Total: ");
        totalValueLabelCell.setCellStyle(SpreadSheetsStylesPatterns.generateHeaderStyle(workbook));
        Cell totalValue = totalValueLabel.createCell(1);
        totalValue.setCellValue(invoice.getTotalValue().doubleValue());
        totalValue.setCellStyle(moneyStyle);

        for (int i = 0; i < table[0].length+1; i++) {
            sheet.autoSizeColumn(i);
        }
        return new FileOutputStream(file);
    }

    private String[][] generateTableFormatModel(Invoices dto) {
        List<Product> products = dto.getProducts();
        int colSize = 1 + dto.getProducts().size();
        int rowSize = 7;
        String[][] table = new String[colSize][rowSize];
        table[0][0] = "id";
        table[0][1] = "nome";
        table[0][2] = "codigo";
        table[0][3] = "valor unitário";
        table[0][4] = "valor total";
        table[0][5] = "quantidade";
        table[0][6] = "unidade de medida";
        for(int i = 1; i < colSize; i++) {
            Product product = products.get(i-1);
            table[i][0] = product.getId().toString();
            table[i][1] = product.getName();
            table[i][2] = product.getCode();
            table[i][3] = product.getUnitprice().toString();
            table[i][4] = product.getTotalprice().toString();
            if(product.getMeasure().equalsIgnoreCase("kg")) {
                double quantityValue = product.getQuantity().doubleValue() / 1000.00;
                table[i][5] = Double.toString(quantityValue);
            }
            else{
                table[i][5] = product.getQuantity().toString();
            }
            table[i][6] = product.getMeasure();
        }
        return table;
    }

}
