package com.payment.expensecontrolsystem.services;

import com.payment.expensecontrolsystem.data.dto.invoices.CreateInvoiceProductsDTO;
import com.payment.expensecontrolsystem.enums.PaymentMethod;
import com.payment.expensecontrolsystem.exceptions.ResourceNotFoundException;
import com.payment.expensecontrolsystem.interfaces.IGeneratorInvoiceService;
import com.payment.expensecontrolsystem.interfaces.IProductService;
import com.payment.expensecontrolsystem.mapper.InvoiceMapper;
import com.payment.expensecontrolsystem.models.Invoices;
import com.payment.expensecontrolsystem.models.Product;
import com.payment.expensecontrolsystem.repositories.InvoicesRepository;
import com.payment.expensecontrolsystem.utils.ParseString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class NfceService implements IGeneratorInvoiceService {
    private final IProductService productService;
    private final InvoicesRepository invoicesRepository;

    public NfceService(IProductService productService, InvoicesRepository invoicesRepository) {
        this.productService = productService;
        this.invoicesRepository = invoicesRepository;
    }
    @Override
    public void generateInvoice(String url) throws Exception {
        Document doc = Jsoup.connect(url).timeout(10000).get();
        Invoices invoice = this.createInvoice(doc);
        Invoices invoiceResult = this.invoicesRepository.save(invoice); //TODO: salvar apenas o invoice com cascade.ALL
        final List<Product> products = this.createProductsList(doc, invoiceResult);
        this.productService.saveAllProducts(products);
    }

    @Override
    public Invoices getInvoiceById(Long id) throws ResourceNotFoundException {
        return this.invoicesRepository.findByIdWithProducts(id);
    }

    private Invoices createInvoice(Document doc){
        BigDecimal valorTotalElement = new BigDecimal(ParseString.parseStringToNumber(doc.selectFirst(".txtMax").text()));
        Integer totalItens = Integer.parseInt(doc.selectFirst(".totalNumb").text());
        PaymentMethod metodoPagamento = parsePaymentMethod(doc.selectFirst(".tx").text());

        return new Invoices(totalItens, metodoPagamento, valorTotalElement);
    }

    private List<Product> createProductsList(Document doc, Invoices invoices) throws Exception {
        List<Product> productsList = new ArrayList<>();
        Element tabelaItens = doc.selectFirst("#tabResult");
        if(tabelaItens == null) {
            throw new Exception("invoice not found");
        }
        Elements productTr = tabelaItens.select("tr");
        if(productTr.isEmpty()) {
            throw new Exception("no items found");
        }

        for(Element item : productTr) {
            String nomeProduto = item.selectFirst(".txtTit").text();
            String codigo = ParseString.parseStringToNumber(item.selectFirst(".RCod").text()).trim();
            Integer quantidade = Integer.parseInt(item.selectFirst(".Rqtd").text().replaceAll("[^0-9]", ""));
            String unidade = item.selectFirst(".RUN").text().split(" ")[1].toLowerCase();
            BigDecimal valorUnitario = new BigDecimal(ParseString.parseStringToNumber(item.selectFirst(".RvlUnit").text()));
            BigDecimal valorTotal = new BigDecimal(ParseString.parseStringToNumber(item.selectFirst(".valor").text()));
            Product product = new Product(nomeProduto, valorTotal, valorUnitario, quantidade, codigo, unidade, invoices);

            boolean flag = false;
            for(Product p : productsList) {
                if(p.getCode().equals(product.getCode())){
                    p.setQuantity(p.getQuantity() + product.getQuantity());
                    p.setTotalprice(p.getTotalprice().add(product.getTotalprice()));
                    flag = true;
                }
            }
            if(!flag) {
                productsList.add(product);
            }
        }
        return productsList;
    }

    private PaymentMethod parsePaymentMethod(String metodoPagamento) {
        if(metodoPagamento.equalsIgnoreCase("Cartão de Débito")){
            return PaymentMethod.DEBIT;
        }
        if(metodoPagamento.equalsIgnoreCase("Cartão de Crédito")){
            return PaymentMethod.CREDIT;
        }
        if(metodoPagamento.equalsIgnoreCase("Dinheiro")){
            return PaymentMethod.CASH;
        }
        if(metodoPagamento.equalsIgnoreCase("Pix")){
            return PaymentMethod.PIX;
        }
        return PaymentMethod.OTHER;
    }

    public void createManualInvoice(CreateInvoiceProductsDTO createInvoiceProductsDTO) {
        Invoices invoice = InvoiceMapper.toEntity(createInvoiceProductsDTO);
        this.invoicesRepository.save(invoice);
    }
}
