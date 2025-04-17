package com.letsKodeit.overview;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReadExample {

    public static void main(String arg[]) throws IOException {
        XSSFWorkbook excelWorkBook;
        XSSFSheet excelSheet;
        XSSFCell excelCell;
        String path;

        path = System.getProperty("user.dir")+"//src//test//resources//ExampleData.xlsx";
        String sheetName = "Scenario1";

        try {
            FileInputStream file = new FileInputStream(path);
            excelWorkBook = new XSSFWorkbook(file);
            excelSheet = excelWorkBook.getSheet(sheetName);
            excelCell = excelSheet.getRow(0).getCell(1);

            String cellData = excelCell.getStringCellValue();

            System.out.println("Cell value is --->" + cellData);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
}
