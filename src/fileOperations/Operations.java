package fileOperations;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;



public class Operations {

private static XSSFWorkbook workbook;



    

public static void WriteExcel() {
String FILE_NAME = "C:\\Users\\bilal.iqbal\\Pictures\\FILES\\sample\\abc1.xlsx";
XSSFWorkbook workbook = new XSSFWorkbook();
XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
Object[][] datatypes = {
{"Datatype", "Type", "Size"},
{"int", "Primitive", 2},
{"float", "Primitive", 4},
{"double", "Primitive", 8},
{"char", "Primitive", 1},
{"String", "Non-Primitive", "No fixed size"}};

int rowNum = 0;
System.out.println("Creating excel");
for (Object[] datatype : datatypes) {
Row row = sheet.createRow(rowNum++);
int colNum = 0;
for (Object field : datatype) {
Cell cell = row.createCell(colNum++);
     if (field instanceof String) {cell.setCellValue((String) field);} 
else if (field instanceof Integer){cell.setCellValue((Integer) field);}}}
try {
FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
workbook.write(outputStream);
workbook.close();} 
catch (FileNotFoundException e) {e.printStackTrace();} 
catch (IOException e) {e.printStackTrace();}
System.out.println("Done");}

    
    
   

@SuppressWarnings("rawtypes")
public static void ReadExcel() {
String FILE_NAME ="C:\\Users\\bilal.iqbal\\Pictures\\FILES\\sample\\abc1.xlsx";
try {
FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
workbook = new XSSFWorkbook(excelFile);
XSSFSheet datatypeSheet =workbook.getSheetAt(0);
Iterator iterator = (Iterator) datatypeSheet.iterator();
while (iterator.hasNext()==true) {
Row currentRow = (Row) iterator.next();
Iterator<Cell> cellIterator = currentRow.iterator();
while (cellIterator.hasNext()) {
Cell currentCell = cellIterator.next();
     if (currentCell.getCellTypeEnum() == CellType.STRING) {System.out.print(currentCell.getStringCellValue() + "--");} 
else if (currentCell.getCellTypeEnum() == CellType.NUMERIC){System.out.print(currentCell.getNumericCellValue() + "--");}}
System.out.println();}} 
catch (FileNotFoundException e) {e.printStackTrace();} 
catch (IOException e) {e.printStackTrace();}
}
    

public static void AddNew() {
String excelFilePath = "C:\\Users\\bilal.iqbal\\Pictures\\FILES\\sample\\abc1.xlsx";
try {
FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
XSSFWorkbook workbook = new XSSFWorkbook(excelFilePath);
XSSFSheet sheet =workbook.getSheetAt(0);
Object[][] bookData = {
{"The Passionate Programmer", "Chad Fowler", 16},
{"Software Craftmanship", "Pete McBreen", 26},
{"The Art of Agile Development", "James Shore", 32},
{"Continuous Delivery", "Jez Humble", 41},};

int rowCount = sheet.getLastRowNum();
for (Object[] aBook : bookData) {
Row row = sheet.createRow(++rowCount);
int columnCount = 0;
Cell cell = row.createCell(columnCount);
cell.setCellValue(rowCount);
for (Object field : aBook) {
cell = row.createCell(++columnCount);
     if (field instanceof String) {cell.setCellValue((String) field);} 
else if (field instanceof Integer) {cell.setCellValue((Integer) field);}}}
inputStream.close();
FileOutputStream outputStream = new FileOutputStream("JavaBooks.xls");
workbook.write(outputStream);
workbook.close();
outputStream.close();} 
catch (IOException | EncryptedDocumentException ex) { ex.printStackTrace();}}


@SuppressWarnings("resource")
public static void OverWrite() throws Exception{
String file="C:\\Users\\bilal.iqbal\\Pictures\\FILES\\sample\\abc1.xlsx";
FileInputStream fsIP= new FileInputStream(file); 
XSSFWorkbook wb = new XSSFWorkbook(fsIP); 
XSSFSheet worksheet = wb.getSheetAt(0); 
Cell cell = null; 
cell = worksheet.getRow(2).getCell(2);
cell.setCellValue("OverRide Last Name");
fsIP.close();
FileOutputStream output_file =new FileOutputStream(new File(file));
wb.write(output_file);
output_file.close(); }
    



}