package fileOperations;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.PdfImageObject;


public class Compression {
Conversion con;
Process process;
String compressor;
public Compression() {

con=new Conversion();
process=null;
//compressor="compress.exe";

String username = System.getProperty("user.name");
compressor= "C:/Users/"+username+"/compress.exe";
}


//public void CompressFile(String file, String factor) throws Exception {
//File fi=new File(file);
//String fn=FilenameUtils.removeExtension(fi.getName());
//String fe=FilenameUtils.getExtension(fi.getName());
//String doc="docx";
//String xls="xlsx";
//String ppt="pptx";
//String pdf="pdf";
//String img="jpg png";
//String vid="wmv mp4 flv mov";
//String path="D:\\VirtualAssistant\\Compressed\\";
//File src,dest;
//     if(doc.contains(fe.toLowerCase())){src=new File(file);dest=new File(path+"Documents\\"+fn+fe+"compressed.pdf");compressWord(src, dest);}
//else if(xls.contains(fe.toLowerCase())){src=new File(file);dest=new File(path+"Documents\\"+fn+fe+"compressed.pdf");compressExcel(src, dest);}
//else if(ppt.contains(fe.toLowerCase())){src=new File(file);dest=new File(path+"Documents\\"+fn+fe+"compressed.pdf");compressSlides(src, dest);}
//else if(pdf.contains(fe.toLowerCase())){src=new File(file);dest=new File(path+"Documents\\"+fn+fe+"compressed.pdf");compressPdf(src, dest);}
//else if(img.contains(fe.toLowerCase())){src=new File(file);dest=new File(path+"Pictures\\"+fn+fe+"compressed."+fe);compressImage(src, dest);}
//else if(vid.contains(fe.toLowerCase())){src=new File(file);dest=new File(path+"Video\\"+fn+fe+"compressed."+fe);compressVideo(src, dest);}}



public void CompressFile(String file, String factor) throws Exception {
File fi=new File(file);
String fn=FilenameUtils.removeExtension(fi.getName());
String fe=FilenameUtils.getExtension(fi.getName());
File src,dest;
if(fe.toLowerCase().contains("docx")){
src=new File(file);
dest=new File(src.getParentFile()+"\\"+fn+fe+"compressed.pdf");
compressWord(src, dest);}
else if(fe.toLowerCase().contains("xlsx")){
src=new File(file);
dest=new File(src.getParentFile()+"\\"+fn+fe+"compressed.pdf");
compressExcel(src, dest);}

else if(fe.toLowerCase().contains("pptx")){
src=new File(file);
dest=new File(src.getParentFile()+"\\"+fn+fe+"compressed.pdf");
compressSlides(src, dest);}

else if(fe.toLowerCase().contains("pdf")){
src=new File(file);
dest=new File(src.getParentFile()+"\\"+fn+fe+"compressed.pdf");
compressPdf(src, dest);}

else if(fe.toLowerCase().contains("jpg") || fe.toLowerCase().contains("png")){
src=new File(file);
dest=new File(src.getParentFile()+"\\"+fn+fe+"compressed."+fe);
compressImage(src, dest);}


else if(fe.toLowerCase().contains("wmv") || fe.toLowerCase().contains("mp4") ||fe.toLowerCase().contains("flv")){
src=new File(file);
dest=new File(src.getParentFile()+"\\"+fn+fe+"compressed.mp4");
compressVideo(src, dest);}}


public void compressWord(File src,File dest) throws IOException, DocumentException {
String ext=FilenameUtils.getExtension(src.getName());
String fn=FilenameUtils.removeExtension(src.getName());
File convertto=new File(src.getParent()+"\\"+fn+ext+".pdf");
con.docxToPdf(src, convertto);
compress(convertto,dest);
convertto.delete();}

public void compressExcel(File src,File dest) throws IOException, DocumentException {
String ext=FilenameUtils.getExtension(src.getName());
String fn=FilenameUtils.removeExtension(src.getName());
File convertto=new File(src.getParent()+"\\"+fn+ext+".pdf");
con.xlsxToPdf(src, convertto);
compress(convertto,dest);
convertto.delete();}

public void compressSlides(File src,File dest) throws IOException, DocumentException, InvalidFormatException {
String ext=FilenameUtils.getExtension(src.getName());
String fn=FilenameUtils.removeExtension(src.getName());
File convertto=new File(src.getParent()+"\\"+fn+ext+".pdf");
con.pptxToPdf(src, convertto);
compress(convertto,dest);
convertto.delete();}


public void compressPdf(File src,File dest) throws IOException, DocumentException, InvalidFormatException {
compress(src,dest);}

public void compress(File src, File dest) throws IOException, DocumentException {
float FACTOR = 0.5f;
PdfName key = new PdfName("ITXT_SpecialId");
PdfName value = new PdfName("123456789");
PdfReader reader = new PdfReader(src.toString());
int n = reader.getXrefSize();
PdfObject object;
PRStream stream;
for (int i = 0; i < n; i++) {
object = reader.getPdfObject(i);
if (object == null || !object.isStream())
continue;
stream = (PRStream)object;
PdfObject pdfsubtype = stream.get(PdfName.SUBTYPE);
System.out.println(stream.type());
if (pdfsubtype != null && pdfsubtype.toString().equals(PdfName.IMAGE.toString())) {
PdfImageObject image = new PdfImageObject(stream);
BufferedImage bi = image.getBufferedImage();
if (bi == null) continue;
int width = (int)(bi.getWidth() * FACTOR);
int height = (int)(bi.getHeight() * FACTOR);
BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
AffineTransform at = AffineTransform.getScaleInstance(FACTOR, FACTOR);
Graphics2D g = img.createGraphics();
g.drawRenderedImage(bi, at);
ByteArrayOutputStream imgBytes = new ByteArrayOutputStream();
ImageIO.write(img, "JPG", imgBytes);
stream.clear();
stream.setData(imgBytes.toByteArray(), false, PRStream.BEST_COMPRESSION);
stream.put(PdfName.TYPE, PdfName.XOBJECT);
stream.put(PdfName.SUBTYPE, PdfName.IMAGE);
stream.put(key, value);
stream.put(PdfName.FILTER, PdfName.DCTDECODE);
stream.put(PdfName.WIDTH, new PdfNumber(width));
stream.put(PdfName.HEIGHT, new PdfNumber(height));
stream.put(PdfName.BITSPERCOMPONENT, new PdfNumber(8));
stream.put(PdfName.COLORSPACE, PdfName.DEVICERGB);}}
PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
stamper.close();
reader.close();}


@SuppressWarnings("unused")
public void audioConversion(File src, File dest) {
try {
Process process = new ProcessBuilder(
compressor,"-i",
src.toString(), "-y", 
dest.toString()).start();
}catch (IOException e) { e.printStackTrace();}}

@SuppressWarnings("unused")
public void videoConversion(File source, File dest) {
Process process=null;
try {
process = new ProcessBuilder(
compressor,"-i",
source.toString(), "-y", 
dest.toString()).start();
}catch (IOException e) { e.printStackTrace();}}

public void resizeVideo(File source, File dest) {
try {
process = new ProcessBuilder(
compressor,"-i",
source.toString() , "-s","720x500",//"\"-c:a\"","copy", 
dest.toString()).start();
}catch (IOException e) { e.printStackTrace();}}


public void compressVideo(File source, File dest) {
try {
process = new ProcessBuilder(
compressor,"-i",
source.toString() , "-s","640x360",
dest.toString()).start();
}catch (IOException e) { e.printStackTrace();}}

//public void compressVideo(File source, File dest) {
//try {
//process= new ProcessBuilder(
//compressor,"-i",
//source.toString(), "-vf","scale=\"720:-1\"",
//dest.toString()).start();
////ffmpeg.waitFor();
////if (0 != ffmpeg.exitValue()) {System.out.println("ffmpeg failed! Exit value: {}"+ ffmpeg.exitValue());}} 
////catch (IOException e) { e.printStackTrace();throw new RuntimeException("Unable to run ffmpeg", e);} 
////catch (InterruptedException e) {e.printStackTrace();}
//}catch (IOException e) { e.printStackTrace();}}



public void resizeImage(File file, File dest) throws IOException {
File output =dest;
BufferedImage originalImage = ImageIO.read(file);
int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
BufferedImage resizeImageJpg = resizeImage(originalImage, type);
ImageIO.write(resizeImageJpg, "jpg",output);} 
private static BufferedImage resizeImage(BufferedImage originalImage, int type){
int Width=originalImage.getWidth();
int Height=originalImage.getHeight();
double Slope=(double)Height/Width;
int width=0;int height=0;
if(Width>1280) {width=1280;}else {width=Width;}
height=(int) (Slope*width);
//width=(int)(Width*0.5);height=(int)(Height*0.5);
BufferedImage resizedImage = new BufferedImage(width, height, type);
Graphics2D g = resizedImage.createGraphics();
g.drawImage(originalImage, 0, 0, width, height, null);
g.dispose();return resizedImage;}
		
		
public  void compressImage(File file, File dest) throws IOException {
File input =file;//String name=file.getName();
File compressedImageFile = dest;
BufferedImage image = ImageIO.read(input);
OutputStream os =new FileOutputStream(compressedImageFile);
Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
ImageWriter writer = (ImageWriter) writers.next();
ImageOutputStream ios = ImageIO.createImageOutputStream(os);
writer.setOutput(ios);
float CR=0.5f;long size=file.length();
CR=(float)102400L/size;
if(CR>1.0) {CR=1.0f;}else{CR=0.5f;}
ImageWriteParam param = writer.getDefaultWriteParam();
param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
param.setCompressionQuality(CR);
writer.write(null, new IIOImage(image, null, null), param);
os.close();
ios.close();
writer.dispose();}

}
