package DemoQACommonFiles;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.IOException;

public class excelToDataObjectUtility {

    public Object[][] excelData() throws IOException {


        String excelFilePath = "D://tools//testData1.xlsx";
        XSSFSheet fileSheet = callingExcelFile(excelFilePath);
        int noOfRow = rowCountInSheet(fileSheet);
        System.out.println(noOfRow);
        int noOfColumn =columnCountInSheet(fileSheet,noOfRow);
        System.out.println(noOfColumn);
        Object[][] getData = new Object[noOfRow-1][noOfColumn-1];

        for(int i=0;i<noOfRow-1;i++){
            XSSFRow row = fileSheet.getRow(i+1);
            for (int j=0; j<noOfColumn-1; j++){
                XSSFCell cell = row.getCell(j+1);
                DataFormatter format =new DataFormatter();
                //System.out.println(format.formatCellValue(cell));
                getData[i][j]= format.formatCellValue(cell);
                //System.out.println(getData[i][j]);
            }
        }
        return getData;
    }

    public XSSFSheet callingExcelFile(String excelPath) throws IOException, IOException {
        FileInputStream testDataFile = new FileInputStream(excelPath);
        XSSFWorkbook excelWorkBook = new XSSFWorkbook(testDataFile);
        XSSFSheet sheetFromWorkBook= excelWorkBook.getSheetAt(0);
        return sheetFromWorkBook;
    }
    public int rowCountInSheet(XSSFSheet sheet){
        //int rowCount =sheet.getPhysicalNumberOfRows();
        //System.out.println(rowCount);
        int rowCount=5;
        return rowCount;
    }
    public int columnCountInSheet(XSSFSheet sheet,int rowCount){
//        XSSFRow rowDetails= sheet.getRow(0);
//        int colCount=rowDetails.getLastCellNum();
//        System.out.println(colCount);
        int colCount = 3;
        return colCount;
    }


}
