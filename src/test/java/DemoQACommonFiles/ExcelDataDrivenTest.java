package DemoQACommonFiles;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelDataDrivenTest {


    @Test
    public void excelData() throws IOException {

        String CaseName = "Negative1";

        ArrayList<String> dataFromArray = getDataFromExcel(CaseName);
        //dataFromArray.stream().forEach(S -> System.out.println(S));
        System.out.println("-----------------");
        System.out.println(dataFromArray.get(2));


    }


    public ArrayList<String> getDataFromExcel(String testCaseName) throws IOException {
        ArrayList<String> dataArrey = new ArrayList<>();

        FileInputStream testDataExcel = new FileInputStream("D://tools//testData1.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(testDataExcel);
        int noOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < noOfSheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> initializeRows = sheet.iterator();
                Row firstRow = initializeRows.next();
                Iterator<Cell> initializeCell = firstRow.cellIterator();
                int k = 0;
                int column = 0;
                while (initializeCell.hasNext()) {
                    Cell cellValue = initializeCell.next();
                    if (cellValue.getStringCellValue().equalsIgnoreCase("Test Case")) {
                        column = k;
                    }
                    k++;
                }
                //once column is identified then scan entire first column to identify desired test case.
                while (initializeRows.hasNext()) {
                    Row desiredRow = initializeRows.next();
                    if (desiredRow.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
                        Iterator<Cell> DesiredCell = desiredRow.cellIterator();
                        while (DesiredCell.hasNext()) {
                            Cell NextCellValue = DesiredCell.next();
                            if (NextCellValue.getCellType()== CellType.STRING){
                                System.out.println("inside get cell String type");
                                dataArrey.add(NextCellValue.getStringCellValue());
                            }else {
                                dataArrey.add(String.valueOf(NextCellValue.getNumericCellValue()));
                                //dataArrey.add(String.valueOf(NextCellValue.getNumericCellValue()));
                            }


                        }
                    }
                }
            }
        }
        return dataArrey;

    }


}
