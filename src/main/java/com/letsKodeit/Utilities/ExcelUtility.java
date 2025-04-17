package com.letsKodeit.Utilities;

import com.letsKodeit.base.CustomDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



public class ExcelUtility {
    private static XSSFWorkbook ExcelWBook;
    private static XSSFSheet ExcelWSheet;
    private static final Logger log = LogManager.getLogger(ExcelUtility.class.getName());

    public static void setExcelFile(String path, String sheetName) throws IOException {
        //String path = System.getProperty("user.dir")+"//src//test//resources//ExcelTestData.xlsx";
        try {
            FileInputStream file = new FileInputStream(path);
            ExcelWBook = new XSSFWorkbook(file);
            ExcelWSheet = ExcelWBook.getSheet(sheetName);
        } catch (Exception e) {
            log.error("Exception occurred");
            e.printStackTrace();
        }
    }


    public static String[][] getTestData(String tableName) {
        String testData[][] = null;

        try {
            DataFormatter formatter = new DataFormatter();
            XSSFCell[] boundaryCells = findTableNameCells(tableName);
            XSSFCell startCell = boundaryCells[0];
            XSSFCell endCell = boundaryCells[1];

            int startRow = startCell.getRowIndex() + 1;
            int endRow = endCell.getRowIndex() - 1;
            int startCol = startCell.getColumnIndex() + 1;
            int endCol = endCell.getColumnIndex() - 1;

            testData = new String[endRow - startRow + 1][endCol - startCol + 1];

            for (int i = startRow; i < endRow + 1; i++) {
                for (int j = startCol; j < endCol + 1; j++) {
                    Cell cell = ExcelWSheet.getRow(i).getCell(j);
                    testData[i - startRow][j - startCol] = formatter.formatCellValue(cell);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return testData;
    }

    public static XSSFCell[] findTableNameCells(String tableName) {
        DataFormatter formatter = new DataFormatter();
        String pos = "begin";

        XSSFCell[] cells = new XSSFCell[2];

        for (Row row : ExcelWSheet) {
            for (Cell cell : row) {
                if (tableName.equals(formatter.formatCellValue(cell))) {
                    if (pos.equalsIgnoreCase("begin")) {

                        cells[0] = (XSSFCell) cell;
                        pos = "end";
                    } else {
                        cells[1] = (XSSFCell) cell;

                    }

                }
            }

        }
        return cells;
    }
}