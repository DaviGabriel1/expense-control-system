package com.payment.expensecontrolsystem.config;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiSingleton {

      private static Workbook workbook = null;

     private PoiSingleton(Workbook workbookInstance) {
         workbook = workbookInstance;
     }

     public static Workbook getInstance() {
         if(workbook == null){
             return new XSSFWorkbook();
         }
         return workbook;
     }
}
