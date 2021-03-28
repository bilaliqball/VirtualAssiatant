package fileOperations;

import java.awt.Desktop;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.AgeFileFilter;



import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class FileOperation {
public String file="";
public void setFile(String f) {this.file=f;}


public String directory="";
public void setDirectory(String d) {this.directory=d;}
	

public String command="";
public void setComamnd(String c) {this.command=c;}

public String query="";
public void setQuery(String q) {this.query=q;}
	


public File DES;

String backupDir;
String compressDir;
String favouriteDir;
String resizeDir;
String trashDir;
String zipDir;
String defaultDir;

public int found=0; 
public int count=0;
public String res="";
public Undo UN;

public ArrayList<Path> parentDirectoryList;
public ArrayList<Path> DirectoryList;
public ArrayList<Path> FileList;
public ArrayList<File> fileList;
public ArrayList<File> filterfileList;
public ArrayList<File> sortedfileList;
public ArrayList<String> openfileList;;
public ArrayList<file> files;


public ArrayList<String> operations;
public ArrayList<String> commands;
public ArrayList<String> history;



public FileOperation() throws IOException {
parentDirectoryList=new ArrayList<Path>();
DirectoryList=new ArrayList<Path>();
FileList=new ArrayList<Path>() ;
fileList=new ArrayList<File>() ;
filterfileList=new ArrayList<File>() ;
sortedfileList=new ArrayList<File>() ;
files=new ArrayList<file>();

operations=new ArrayList<String>();
commands=new ArrayList<String>();
history=new ArrayList<String>();
openfileList=new ArrayList<String>();

listParentDirectories();
listAllDirectories();
listAllFiles();
listFiles();

backupDir="D:\\VirtualAssistant\\Backup\\";
compressDir="D:\\VirtualAssistant\\Compressed\\";
favouriteDir="D:\\VirtualAssistant\\Favourite\\";
resizeDir="D:\\VirtualAssistant\\Resized\\";
trashDir="D:\\VirtualAssistant\\Trash\\";
zipDir="D:\\VirtualAssistant\\Zipped\\";
defaultDir="D:\\VirtualAssistant\\Default\\";}

public void REFRESH() throws IOException {
parentDirectoryList=new ArrayList<Path>();
DirectoryList=new ArrayList<Path>();
FileList=new ArrayList<Path>() ;
fileList=new ArrayList<File>() ;
filterfileList=new ArrayList<File>() ;
sortedfileList=new ArrayList<File>() ;
files=new ArrayList<file>();
listParentDirectories();
listAllDirectories();
listAllFiles();
listFiles();}

public void refresh() throws IOException {
FileList=new ArrayList<Path>() ;
files=new ArrayList<file>();
listAllFiles();
listFiles();}

public class file{
public String path="";
public String name="";
public String ext="";
public long size=0;
public void setPath(String p) {this.path=p;} public String getPath() {return this.path;}
public void setName(String n) {this.name=n;} public String getName() {return this.name;}
public void setExt(String e) {this.ext=e;} public String getExt() {return this.ext;}
public void setSize(long s) {this.size=s;} public long getSize() {return this.size;}
public file(String p, String n, String e) {this.path=p;this.name=n;this.ext=e;}
public file(String p, String n, String e,long s) {this.path=p;this.name=n;this.ext=e;this.size=s;}
public file() {}

public  Comparator<file> pathComparator = new Comparator<file>() {
public int compare(file f1, file f2) {
String file1 = f1.getPath();
String file2 = f2.getPath();
return file1.compareTo(file2);}};

public  Comparator<file> nameComparator = new Comparator<file>() {
public int compare(file f1, file f2) {
String file1 = f1.getName();
String file2 = f2.getName();
return file1.compareTo(file2);}};

public  Comparator<file> extComparator = new Comparator<file>() {
public int compare(file f1, file f2) {
String file1 = f1.getExt();
String file2 = f2.getExt();
return file1.compareTo(file2);}};

public  Comparator<file> sizeComparator = new Comparator<file>() {
public int compare(file f1, file f2) {
long file1 = f1.getSize();
long file2 = f2.getSize();
if(file1>file2) {return -1;}
if(file2>file1) {return 1;}
else {return 0;}}};
}


//************************************************************************************************************************************************************************
public void listParentDirectories() throws IOException {
File dirC=new File("C:\\"); if(dirC.isDirectory()) {listParentDirectoriesC(dirC.toString());}
//File dirD=new File("D:\\"); if(dirD.isDirectory()) {listParentDirectoriesD(dirD.toString());} 
//File dirE=new File("E:\\"); if(dirE.isDirectory()) {listParentDirectoriesE(dirE.toString());} 
//File dirG=new File("G:\\"); if(dirG.isDirectory()) {listParentDirectoriesE(dirG.toString());} 
}
public void listParentDirectoriesC(String dir) throws IOException {
String username = System.getProperty("user.name");
String dirName = "C:\\Users\\"+username;
Files.list(new File(dirName).toPath()).forEach(path -> {
File p=new File(path.toString());String n=p.getName();
if(n.contains("ictures") || n.contains("ideos") || n.contains("ownloads") || n.contains("usic")) {//  || n.contains("esktop") n.contains("rive") ||
parentDirectoryList.add(path); }});}
public void listParentDirectoriesD(String dir) throws IOException {
String dirName = dir;
Files.list(new File(dirName).toPath()).forEach(path -> {
File p=new File(path.toString());String n=p.getName();
if(n.contains("RECYCLE") || n.contains("System Volume Information") || n.contains("rojects") || n.contains("rivio") || n.contains("oftwares") || n.contains("irtualAssistant")){}
else {parentDirectoryList.add(path); }});}

public void listParentDirectoriesE(String dir) throws IOException {
String dirName = dir;
Files.list(new File(dirName).toPath()).forEach(path -> {
File p=new File(path.toString());String n=p.getName();
if(n.contains("RECYCLE") || n.contains("System Volume Information")){}
else {parentDirectoryList.add(path); }});}


public void listAllDirectories() throws IOException {
for(int i=0;i<parentDirectoryList.size();i++) {
String di=parentDirectoryList.get(i).toString();
try (Stream<Path> paths = Files.walk(Paths.get(di))) {paths.filter(Files::isDirectory).forEach(DirectoryList::add);}}}


public void listAllFiles() throws IOException {
String path="";
for(int i=0;i<parentDirectoryList.size();i++) {
Path pa=parentDirectoryList.get(i);
path=pa.toString();
try (Stream<Path> paths = Files.walk(Paths.get(path))) {
paths.filter(Files::isRegularFile).forEach(FileList::add);}}}


public void listFiles() throws IOException {
final String EXT="txt doc docx xlsx xls ppt pptx pdf jpg png bmp tif gif mp3 mp4 mkv avi webm flv wmv zip";
Path fi;
file f;
String fp;
String fn;
String fe;
String name[];String filename;
for(int i=0;i<FileList.size();i++) {
fi=FileList.get(i);
fp=fi.getParent().toString();filename=fi.getFileName().toString();name=filename.split("\\.");
fn=name[0];
fe=FilenameUtils.getExtension(filename);
if(EXT.contains(fe)) {fileList.add(new File(fi.toString()));
f=new file(fp,fn,fe);files.add(f);}}}


public Boolean searchDirectory(String dir) {
Path path;
String pa="";
String parent,name;
Boolean status=false;
for(int i=0;i<DirectoryList.size();i++) {
path=DirectoryList.get(i);
parent=path.getParent().toString();
name=path.getFileName().toString();
if((dir.equalsIgnoreCase(name))) {
status=true;
pa=parent+"\\"+name;}}
setDirectory(pa);
return status;}

public Boolean SEARCHDirectory(String dir) {
ArrayList<String> directoriesFound=new ArrayList<String>();
Path path;
String pa="";
String parent,name;
Boolean status=false;
for(int i=0;i<DirectoryList.size();i++) {
path=DirectoryList.get(i);
parent=path.getParent().toString();
name=path.getFileName().toString();
if((dir.equalsIgnoreCase(name)) ||(name.contains(dir))) {
status=true;
pa=parent+"\\"+name;
directoriesFound.add(pa);}}
if(directoriesFound.size()==1) {setDirectory(directoriesFound.get(0));}
else {
String message="";
String dirFound="";
for(int i=0;i<directoriesFound.size();i++) {
String ff=directoriesFound.get(i);
String index=Integer.toString(i+1);
dirFound=index+": "+ff+"\n";
message+=dirFound;}
String context="Please choose DIRECTORY no. from following. \n\n"+message;
String fc = JOptionPane.showInputDialog(null, context, "Choose Directory#", JOptionPane.INFORMATION_MESSAGE);
int chooseIndex=Integer.parseInt(fc);
String chooseFile="";
chooseFile=directoriesFound.get(chooseIndex-1);
setDirectory(chooseFile);}
return status;}

public void displayParentDirectories() {
for(int i=0;i<parentDirectoryList.size();i++) {System.out.println(parentDirectoryList.get(i));}	}

public void displayAllDirectories() {
for(int i=0;i<DirectoryList.size();i++) {System.out.println(DirectoryList.get(i));}	}

public void info(String res) {
JTextArea textArea = new JTextArea(res);
JScrollPane scrollPane = new JScrollPane(textArea);  
textArea.setLineWrap(true);  
textArea.setWrapStyleWord(true); 
scrollPane.setPreferredSize( new Dimension(680, 920) );
JOptionPane.showMessageDialog(null, scrollPane, "INFO",JOptionPane.INFORMATION_MESSAGE);}



public void Query(String op, String sr, String de,String uo,String us,String ud) {
String CMD="create rename copy move remove back favourt compress resize zip";
query=op+ " "+sr+" "+de+" "+uo+" "+us+" "+ud+" ";
if(CMD.contains(command.toLowerCase().substring(0,3))) {
operations.add(query);
commands.add(command);}}

public void deQuery() throws IOException, InterruptedException, ParseException {
if(operations.size()==0) {JOptionPane.showMessageDialog(null, "There is no more operation to undo", "INFO",JOptionPane.INFORMATION_MESSAGE);}
else{
String lastExecutedCommand=operations.get(operations.size()-1); 

isUndo(lastExecutedCommand);
operations.remove(operations.size()-1);
commands.remove(commands.size()-1);}}


public void displayLastExecutedCommands() throws IOException, InterruptedException, ParseException {
if(operations.size()==0) {JOptionPane.showMessageDialog(null, "There is no more operation to undo", "INFO",JOptionPane.INFORMATION_MESSAGE);}
else {
String message="";int size=operations.size()-1; 
for(int i=0;i<operations.size();i++) {message+=Integer.toString(i+1)+": ";message+=commands.get(size-i)+"\n";}
String context="Please choose OPERATION to revert \n\n"+message;
String fc = JOptionPane.showInputDialog(null, context, "Choose Operation#", JOptionPane.INFORMATION_MESSAGE);
int chooseIndex=Integer.parseInt(fc);
String commandExecuted=operations.get(operations.size()-chooseIndex); 
System.out.println(commandExecuted);
isUndo(commandExecuted);
operations.remove(operations.size()-chooseIndex);
commands.remove(commands.size()-chooseIndex);}}

public void displayQueries() {
int i;String mes="";
mes+="HISTORY [ALL EXECUTED COMMANDS: ] \n";
mes+="[ ";
for(i=0;i<history.size();i++) {mes+=history.get(i)+ ", ";}mes+="]";

mes+="\n\n\n COMMANDS [UNDOABLE COMMANDS] \n";
mes+="[ ";
for(i=0;i<commands.size();i++) {mes+=commands.get(i)+ ", ";}mes+="]";

info(mes);}

public void displayHistory() {
int i;String mes="";
mes+="HISTORY [ALL EXECUTED COMMANDS: ] \n";
mes+="[ ";
for(i=0;i<history.size();i++) {mes+=history.get(i)+ ", ";}mes+="]";
info(mes);}

public Boolean isUndo(String cmd) throws IOException, InterruptedException, ParseException {
Boolean isundo=false;
UN=new Undo();
UN.isUndo(cmd);
return isundo;} 





public void searchFiles(String byy,String forr) throws ParseException, IOException {
	 if(byy.toLowerCase().contains("name")) {searchByName(forr);}
else if(byy.toLowerCase().contains("type")) {searchByType(forr);}
else if(byy.toLowerCase().contains("ext"))  {searchByExt(forr);}
else if(byy.toLowerCase().contains("date")) {searchByDate(forr);}}

public void searchByName(String name) {
File fi; String fn;res="";
ArrayList<File> filterFiles=new ArrayList<File>();
for(int i=0;i<fileList.size();i++) {
fi=fileList.get(i);fn=fi.getName();String FN[]=fn.split("\\.");
fn=FN[0];
if(fn.toLowerCase().contains(name.toLowerCase())) {
filterFiles.add(fi);}}
for(int j=0;j<filterFiles.size();j++) {
res+=filterFiles.get(j)+"\n";}
info(res);res="";}



public void searchByDate(String day) throws ParseException, IOException {
File fi;long fm;String fn;
long fc; long threshold=0;res="";
BasicFileAttributes attr;
ArrayList<File> filterFiles=new ArrayList<File>();
SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy");
Date date = (Date) sdf.parse(day);
threshold = date.getTime();
for(int i=0;i<fileList.size();i++) {
fi=fileList.get(i);fn=fi.getName();
attr= Files.readAttributes(fi.toPath(),BasicFileAttributes.class);
fm=fi.lastModified();
fc=attr.creationTime().to(TimeUnit.MILLISECONDS);
if((fc>=threshold || fm>=threshold) && fi.isFile() && FilenameUtils.getExtension(fn)!="") {
filterFiles.add(fi);}}
for(int j=0;j<filterFiles.size();j++) {
res+=filterFiles.get(j).getName()+"\n";}
info(res);res="";}
public void searchByType(String ext) {
String EXT="";res="";
String WORD="doc docx";
String PDF="pdf";
String EXCEL="xls xlsx";
String SLIDES="ppt pptx";
String DOCUMENT="doc docx pdf xls xlsx ppt pptx";
String IMAGE="jpg png";
String MUSIC="mp3";
String VEDIO="mp4 mkv avi webm wmv";
     if(ext.toLowerCase().contains("docu" )){EXT=DOCUMENT;}
else if(ext.toLowerCase().contains("image")){EXT=IMAGE;}
else if(ext.toLowerCase().contains("music")){EXT=MUSIC;} 
else if(ext.toLowerCase().contains("video")){EXT=VEDIO;}
else if(ext.contains("ord" )||ext.contains("doc")){EXT=WORD;}
else if(ext.contains("dobe")||ext.contains("pdf")){EXT=PDF;}
else if(ext.contains("xcel")||ext.contains("xls")||ext.contains("xls")){EXT=EXCEL;}
else if(ext.contains("ower")||ext.contains("lides")||ext.contains("ppt")){EXT=SLIDES;}
else if(ext.contains("ict") ||ext.contains("mage")||ext.contains("jpg")||ext.contains("png")){EXT=IMAGE;}
else if(ext.contains("usic")||ext.contains("ong")){EXT=MUSIC;} 
else if(ext.contains("ideo")||ext.contains("movie")){EXT=VEDIO;}
File fi; String fe;
ArrayList<File> filterFiles=new ArrayList<File>();
for(int i=0;i<fileList.size();i++) {
fi=fileList.get(i);
fe=FilenameUtils.getExtension(fi.toString());
if(EXT.contains(fe)&& fe!="") {
filterFiles.add(fi);}}
for(int j=0;j<filterFiles.size();j++) {
res+=filterFiles.get(j).getName()+"\n";}
info(res);res="";}


public void searchByExt(String ext) {
String EXT="";res="";
String WORD="doc docx";
String PDF="pdf";
String EXCEL="xls xlsx";
String SLIDES="ppt pptx";
String IMAGE="jpg png";
String MUSIC="mp3";
String VEDIO="mp4 mkv avi webm flv wmv";
     if(ext.contains("ord" )||ext.contains("doc")){EXT=WORD;}
else if(ext.contains("dobe")||ext.contains("pdf")){EXT=PDF;}
else if(ext.contains("xcel")||ext.contains("xls")||ext.contains("xls")){EXT=EXCEL;}
else if(ext.contains("ower")||ext.contains("lides")||ext.contains("ppt")){EXT=SLIDES;}
else if(ext.contains("ict") ||ext.contains("mage")){EXT=IMAGE;}
else if(ext.contains("usic")||ext.contains("ong")){EXT=MUSIC;} 
else if(ext.contains("ideo")||ext.contains("movie")){EXT=VEDIO;}
else if(ext.contains("df")){EXT="pdf";}
else if(ext.contains("oc")){EXT="doc docx";}
else if(ext.contains("ls")){EXT="xls xlsx";}
else if(ext.contains("pt")){EXT="ppt pptx";}
else if(ext.contains("pg")){EXT="jpg";}
else if(ext.contains("ng")){EXT="png";}
else if(ext.contains("p3")){EXT="mp3";} 
else if(ext.contains("p4")){EXT="mp4";}
else if(ext.contains("vi")){EXT="avi";}
else if(ext.contains("mv")){EXT="wmv";}
else if(ext.contains("lv")){EXT="flv";}
     
file fi; String fp,fn,fe;
ArrayList<File> filterFiles=new ArrayList<File>();
for(int i=0;i<files.size();i++) {
fi=files.get(i);
fp=fi.path;fn=fi.name; fe=fi.ext;
if(EXT.contains(fe)&& fe!="") {
String f=fp+"\\"+fn+"."+fe;
filterFiles.add(new File(f));}}
for(int j=0;j<filterFiles.size();j++) {
	res+=filterFiles.get(j)+"\n";}
info(res);res="";}


public  void sortFiles(String comp) {
    if(comp.contains("ame")||comp.contains("ame")){Collections.sort(files,new file().nameComparator);}
else if(comp.contains("ath")||comp.contains("old")){Collections.sort(files,new file().pathComparator);}
else if(comp.contains("xt")||comp.contains("ype")) {Collections.sort(files,new file().extComparator);}
else if(comp.contains("ize")||comp.contains("eng")){Collections.sort(files,new file().sizeComparator);}

file fi; String fp,fn,fe;String f;res="";
for(int i=0;i<files.size();i++) {
fi=files.get(i);
fp=fi.path;  fn=fi.name; fe=fi.ext;
f=fp+"\\"+fn+"."+fe;
f=fn+"."+fe;
sortedfileList.add(new File(f));}
for(File sf:sortedfileList) {
res+=sf.toString()+"\n";}
res+="Files Found: "+ sortedfileList.size();
info(res);res="";}

//___________________________________________________________________________________________________________________________________________________________________
public void init() throws IOException {
String username = System.getProperty("user.name");
String dir = "C:\\Users\\"+username+"\\compress.exe";
String fil="C:\\Users\\"+username+"\\compression.exe";
if(new File(dir).exists()&& new File(fil).exists()) {System.out.println("exists");}
else {
FileUtils.copyFile(new File("resources/compression.exe"),new File( "C:\\Users\\"+username+"\\compression.exe"));
FileUtils.copyFile(new File("resources/compress.exe"),new File( "C:\\Users\\"+username+"\\compress.exe"));}}

@SuppressWarnings("unused")
public void compression(String file, String factor) {
String username = System.getProperty("user.name");
String compressor= "C:\\Users\\"+username+"\\compression.exe";
String opertaion="compress";
String filename=file;
try {
Process process = new ProcessBuilder(
compressor,
opertaion,
filename,
factor).start();
}catch (IOException ex) { ex.printStackTrace();}}

@SuppressWarnings("unused")
public void resizing(String file, String factor) {
String username = System.getProperty("user.name");
String compressor= "C:\\Users\\"+username+"\\compression.exe";
String opertaion="resize";
String filename=file;
try {
Process process = new ProcessBuilder(
compressor,
opertaion,
filename,
factor).start();
}catch (IOException ex) { ex.printStackTrace();}}


@SuppressWarnings("unused")
public void conversion(String file, String form) { 
String username = System.getProperty("user.name");
String convertor= "C:\\Users\\"+username+"\\compression.exe";
String opertaion="convert";
String filename=file;
String format=form;
try {
Process process = new ProcessBuilder(
convertor,
opertaion,
filename,
format).start();
}catch (IOException ex) { ex.printStackTrace();}}
	
//***********************************************************************************************************************************************************
public void compressFile(File file) throws IOException {
DES=new File(compressDir+file.getName());
compressImage(file,DES);
Query("compressFile",file.toString(),DES.toString(),"deleteFile",DES.toString(),"null");}


public void compressDirectory(File dir) throws IOException {
final String EXT="jpg png";String cd=compressDir+dir.getName()+"\\";
createFolder(cd);
File[] file=dir.listFiles();File fi;
String name; String ext;
System.out.println("Files found "+ file.length);
for(int i=0;i<file.length;i++) {
fi=file[i];
name=fi.getName();ext=FilenameUtils.getExtension(name);
if((EXT.contains(ext) && ext!="")) {
DES=new File(cd+fi.getName());System.out.println(DES);
compressImage(fi,DES);}}
Query("compressDirectory",dir.toString(),DES.getParent(),"deleteDirectory",cd,"null");}

public void compressFiles() throws IOException {
final String EXT="jpg png";String cd=compressDir+"files\\";
createFolder(cd);
File file; String name; String ext;
long size=0;
final long min=10000; final long max=100000;
for(int i=0;i<fileList.size();i++) {
file=fileList.get(i);
name=file.getName();ext=FilenameUtils.getExtension(name);size=file.length();
if((EXT.contains(ext) && ext!="") && (size>max && size >min)) {
DES=new File(cd+file.getName());
compressImage(file,DES);}}
Query("compressFiles","null",DES.getParent(),"deleteDirectory",cd,"null");}

public void compressAllFiles() throws IOException {
final String EXT="jpg png";String cd=compressDir+"files\\";
createFolder(cd);
File file; String name; String ext;
long size=0;
for(int i=0;i<fileList.size() ;i++) {
file=fileList.get(i);
name=file.getName();ext=FilenameUtils.getExtension(name);size=file.length();
if(EXT.contains(ext) && ext!="" && size>=10000) {System.out.println(file);
DES=new File(cd+file.getName());
compressImage(file,DES);}}
Query("compressAllFiles","null",DES.getParent(),"deleteDirectory",cd,"null");}


public void resizeFile(File file) throws IOException {
DES=new File(resizeDir+file.getName());
resizeImage(file,DES);
Query("resizeFile",file.toString(),DES.toString(),"deleteFile",DES.toString(),"null");}


public void resizeDirectory(File dir) throws IOException {
final String EXT="jpg png";String rd=resizeDir+dir.getName()+"\\";
createFolder(rd);
File[] file=dir.listFiles();File fi;
String name; String ext;
long size=0;
for(int i=0;i<file.length;i++) {
fi=file[i];
name=fi.getName();ext=FilenameUtils.getExtension(name);size=fi.length();
if((EXT.contains(ext) && ext!="") && (size>10000L)) {
DES=new File(rd+fi.getName());
resizeImage(fi,DES);}}
Query("resizeDirectory",dir.toString(),DES.getParent(),"deleteDirectory",rd,"null");}


public void resizeFiles() throws IOException {
final String EXT="jpg png";String rd=resizeDir+"files\\";
createFolder(rd);
File file; String name; String ext;
long size=0;final long max=248000;
for(int i=0;i<fileList.size();i++) {
file=fileList.get(i);
name=file.getName();ext=FilenameUtils.getExtension(name);size=file.length();
if((EXT.contains(ext) && ext!="") && (size>max)) {
DES=new File(rd+file.getName());	
resizeImage(file,DES);}}
Query("resizeFiles","null",DES.getParent(),"deleteDirectory",rd,"null");}


public void resizeAllFiles() throws IOException {
final String EXT="jpg png";File file;
String name;String ext;int count=0;String rd=resizeDir+"files\\";
createFolder(rd);
for(int i=0;i<fileList.size();i++) {
file=fileList.get(i);name=file.getName();ext=FilenameUtils.getExtension(name);
if(EXT.contains(ext)&& ext!="") {
DES=new File(rd+file.getName());System.out.println(file);
resizeImage(file,DES);count++;}}
System.out.println("File Resized"+count);
Query("resizeAllFiles","null",DES.getParent(),"deleteDirectory",rd,"null");}




public void zipFile(File f) throws IOException {
String OUTPUT_ZIP_FILE = zipDir+f.getName()+".zip";
byte[] buffer = new byte[1024];  	
try{
FileOutputStream fos = new FileOutputStream(OUTPUT_ZIP_FILE);
ZipOutputStream zos = new ZipOutputStream(fos);
ZipEntry ze= new ZipEntry(file.toString());
zos.putNextEntry(ze);count++;
FileInputStream in = new FileInputStream(file.toString());
int len;
while ((len = in.read(buffer)) > 0) {zos.write(buffer, 0, len);}
in.close();
zos.closeEntry();
zos.close();}
catch(IOException ex){ex.printStackTrace();}
System.out.println("Zip created.");
Query("zipFile",file.toString(),OUTPUT_ZIP_FILE,"deleteFile",OUTPUT_ZIP_FILE,"null");}

public void unZipFile(File f){
String zipFile=f.toString(); 
String outputFolder="D:\\VirtualAssistant\\Zipped\\";
byte[] buffer = new byte[1024];
try{
File folder = new File(outputFolder);
if(!folder.exists()){folder.mkdir();}
ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
ZipEntry ze = zis.getNextEntry();
while(ze!=null){
String fileName = ze.getName();
File newFile = new File(outputFolder + File.separator + fileName);
//System.out.println("file unzip : "+ newFile.getAbsoluteFile());
new File(newFile.getParent()).mkdirs();
FileOutputStream fos = new FileOutputStream(newFile);             
int len;
while ((len = zis.read(buffer)) > 0) {fos.write(buffer, 0, len);}
fos.close();   
ze = zis.getNextEntry();}
zis.closeEntry();
zis.close();
System.out.println("Done");}
catch(IOException ex){ex.printStackTrace(); }}


public void zipDirectory(File dir) throws IOException{
File[] fi=dir.listFiles();String name=dir.getName();
String OUTPUT_ZIP_FILE = zipDir+name+".zip";
byte[] buffer = new byte[1024];  	
try{
FileOutputStream fos = new FileOutputStream(OUTPUT_ZIP_FILE);
ZipOutputStream zos = new ZipOutputStream(fos);
for(File file : fi){
ZipEntry ze= new ZipEntry(file.toString());
zos.putNextEntry(ze);
FileInputStream in = new FileInputStream(file.toString());
int len;
while ((len = in.read(buffer)) > 0) {zos.write(buffer, 0, len);}
in.close();}
zos.closeEntry();
zos.close();
System.out.println("Done");}
catch(IOException ex){ex.printStackTrace();}
Query("zipDirectory",dir.toString(),OUTPUT_ZIP_FILE,"deleteFile",OUTPUT_ZIP_FILE,"null");}

public void zipFiles() throws IOException {
final String EXT="jpg png";File file;String name;String ext;int count=0;
String OUTPUT_ZIP_FILE = zipDir+"ZIPFILES.zip";
byte[] buffer = new byte[1024];  	
try{
FileOutputStream fos = new FileOutputStream(OUTPUT_ZIP_FILE);
ZipOutputStream zos = new ZipOutputStream(fos);
for(int i=0;i<fileList.size();i++) {
file=fileList.get(i);name=file.getName();ext=FilenameUtils.getExtension(name);
if(EXT.contains(ext)&& ext!="") {ZipEntry ze= new ZipEntry(file.toString());
zos.putNextEntry(ze);count++;
FileInputStream in = new FileInputStream(file.toString());
int len;
while ((len = in.read(buffer)) > 0) {zos.write(buffer, 0, len);}
in.close();}}
zos.closeEntry();
zos.close();}
catch(IOException ex){ex.printStackTrace();}
System.out.println("Zip created."+ count);
Query("zipFiles","null",OUTPUT_ZIP_FILE,"deleteFile",OUTPUT_ZIP_FILE,"null");}





public void unZipFiles(){
String zipFile="D:\\VirtualAssistant\\Zipped\\bird.zip"; 
String outputFolder="D:\\VirtualAssistant\\Zipped\\";
byte[] buffer = new byte[1024];

try{
File folder = new File(outputFolder);
if(!folder.exists()){folder.mkdir();}

ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
ZipEntry ze = zis.getNextEntry();
while(ze!=null){
String fileName = ze.getName();
File newFile = new File(outputFolder + File.separator + fileName);
System.out.println("file unzip : "+ newFile.getAbsoluteFile());
new File(newFile.getParent()).mkdirs();
FileOutputStream fos = new FileOutputStream(newFile);             
int len;while ((len = zis.read(buffer)) > 0) {fos.write(buffer, 0, len);}
fos.close();   
ze = zis.getNextEntry();}
zis.closeEntry();
zis.close();
System.out.println("Done");}
catch(IOException ex){ex.printStackTrace(); }}

public void zipAllFiles() throws IOException {
String OUTPUT_ZIP_FILE =zipDir+"ZIPALLFILES.zip";
byte[] buffer = new byte[1024]; File file; long size;	
try{
FileOutputStream fos = new FileOutputStream(OUTPUT_ZIP_FILE);
ZipOutputStream zos = new ZipOutputStream(fos);
for(int i=0;i<fileList.size();i++) {
file=fileList.get(i);size=file.length();
ZipEntry ze= new ZipEntry(file.toString());
zos.putNextEntry(ze);count++;if(size>=4000 && size<=100*1024*1024) {
FileInputStream in = new FileInputStream(file.toString());System.out.println(file);
int len;
while ((len = in.read(buffer)) > 0) {zos.write(buffer, 0, len);}
in.close();}}
zos.closeEntry();
zos.close();}
catch(IOException ex){ex.printStackTrace();}
System.out.println("Zip created."+ count);
Query("zipAllFiles","null",OUTPUT_ZIP_FILE,"deleteFile",OUTPUT_ZIP_FILE,"null");}


public void compressZipAllFiles() throws IOException {
final String EXT="jpg png"; String cd=compressDir+"compresszip\\";
File file;String ext;
createFolder(cd);
for(int i=0;i<fileList.size();i++) {
file=fileList.get(i);
ext=FilenameUtils.getExtension(file.getName());
if(EXT.contains(ext)&& ext!="") {
DES=new File(cd+file.getName());
compressImage(file,DES);;System.out.println(file);}}
File dir=new File(cd);
File[] files=dir.listFiles();
String OUTPUT_ZIP_FILE = zipDir+"compressZipAllFiles.zip";
byte[] buffer = new byte[1024];  	try{
FileOutputStream fos = new FileOutputStream(OUTPUT_ZIP_FILE);
ZipOutputStream zos = new ZipOutputStream(fos);
for(File fi : files){
ZipEntry ze= new ZipEntry(fi.toString());
zos.putNextEntry(ze);
FileInputStream in = new FileInputStream(fi.toString());
int len;
while ((len = in.read(buffer)) > 0) {zos.write(buffer, 0, len);}
in.close();}
zos.closeEntry();
zos.close();
System.out.println("Done");}
catch(IOException ex){ex.printStackTrace();}
FileUtils.deleteDirectory(dir);
Query("compressZipAllFiles","null",dir.toString(),"deleteFile",OUTPUT_ZIP_FILE,"null");}


public void resizeZipAllFiles() throws IOException {
final String EXT="jpg png"; String rd=resizeDir+"resizezip\\";
File file;String ext;
createFolder(rd);
for(int i=0;i<fileList.size();i++) {
file=fileList.get(i);
ext=FilenameUtils.getExtension(file.getName());
if(EXT.contains(ext)&& ext!="") {
DES=new File(rd+file.getName());
resizeImage(file,DES);System.out.println(file);}}
File dir=new File(rd);
File[] files=dir.listFiles();
String OUTPUT_ZIP_FILE = zipDir+"resizeZipAllFiles.zip";
byte[] buffer = new byte[1024];  	try{
FileOutputStream fos = new FileOutputStream(OUTPUT_ZIP_FILE);
ZipOutputStream zos = new ZipOutputStream(fos);
for(File fi : files){
ZipEntry ze= new ZipEntry(fi.toString());
zos.putNextEntry(ze);
FileInputStream in = new FileInputStream(fi.toString());
int len;
while ((len = in.read(buffer)) > 0) {zos.write(buffer, 0, len);}
in.close();}
zos.closeEntry();
zos.close();
System.out.println("Done");}
catch(IOException ex){ex.printStackTrace();}
FileUtils.deleteDirectory(dir);
Query("resizeZipAllFiles","null",dir.toString(),"deleteFile",OUTPUT_ZIP_FILE,"null");}


public void backupFiles() throws IOException {
String OUTPUT_ZIP_FILE = backupDir+"BACKUP.zip";
byte[] buffer = new byte[1024]; File file; long size;	
try{
FileOutputStream fos = new FileOutputStream(OUTPUT_ZIP_FILE);
ZipOutputStream zos = new ZipOutputStream(fos);
for(int i=0;i<fileList.size();i++) {
file=fileList.get(i);size=file.length();
ZipEntry ze= new ZipEntry(file.toString());
zos.putNextEntry(ze);count++;if(size>=4000 && size<=100*1024*1024) {
FileInputStream in = new FileInputStream(file.toString());System.out.println(file);
int len;
while ((len = in.read(buffer)) > 0) {zos.write(buffer, 0, len);}
in.close();}}
zos.closeEntry();
zos.close();}
catch(IOException ex){ex.printStackTrace();}
System.out.println("Zip created."+ count);
Query("backupFiles","null",OUTPUT_ZIP_FILE,"deleteFile",OUTPUT_ZIP_FILE,"null");}


public void backupAllFiles() throws IOException {
String OUTPUT_ZIP_FILE = backupDir+"BACKUPALL.zip";
byte[] buffer = new byte[1024]; File file; 	
try{
FileOutputStream fos = new FileOutputStream(OUTPUT_ZIP_FILE);
ZipOutputStream zos = new ZipOutputStream(fos);
for(int i=0;i<fileList.size();i++) {
file=fileList.get(i);System.out.println(file.getName());
ZipEntry ze= new ZipEntry(file.toString());
zos.putNextEntry(ze);count++;
FileInputStream in = new FileInputStream(file.toString());
int len;
while ((len = in.read(buffer)) > 0) {zos.write(buffer, 0, len);}
in.close();}
zos.closeEntry();
zos.close();}
catch(IOException ex){ex.printStackTrace();}
System.out.println("Zip created."+ count);
Query("backupAllFiles","null",OUTPUT_ZIP_FILE,"deleteFile",OUTPUT_ZIP_FILE,"null");}

public void arrangeAllFiles() throws IOException {
File file;File source = null; File dest = null;
String filename;String ext;long size;
String mydirectory="D:\\VirtualAssistant\\Assistant\\";
final String Documents="doc docx xls xlsx ppt pptx pdf txt";
final String Pictures="jpg png bmp tif gif";
final String Music="mp3";
final String Videos="mp4 mkv avi webm flv wmv";

for(int i=0;i<fileList.size();i++) {
file=fileList.get(i);
source=file;
filename=file.getName();
ext=FilenameUtils.getExtension(filename);
size=file.length();
     if(Documents.contains(ext) && ext!="" && size>1000L) {dest=new File(mydirectory+"Documents\\"+file.getName());FileUtils.copyFile(source, dest);System.out.println(source);}
else if(Pictures.contains(ext)  && ext!="" && size>10000L) {dest=new File(mydirectory+"Pictures\\"+file.getName());FileUtils.copyFile(source, dest);System.out.println(source);}
else if(Music.contains(ext)     && ext!="" && size>100000L) {dest=new File(mydirectory+"Music\\"+file.getName());FileUtils.copyFile(source, dest);System.out.println(source);}
else if(Videos.contains(ext)    && ext!="" && size>1000000L) {dest=new File(mydirectory+"Videos\\"+file.getName());FileUtils.copyFile(source, dest);System.out.println(source);}}
System.out.println("...");}


public  void copyFile(File source, File dest) throws IOException {
FileUtils.copyFile(source, dest);
Query("copyFile",source.toString(),dest.toString(),"deleteFile",dest.toString(),"null");}

public  void copyDirectory(File source, File dest) throws IOException {
FileUtils.copyDirectoryToDirectory(source, dest);
Query("copyDirectory",source.toString(),dest.toString(),"deleteDirectory",dest.toString()+"\\"+source.getName(),"null");}

public  void overrideDirectory(File source, File dest) throws IOException {
FileUtils.copyDirectoryToDirectory(source, dest);FileUtils.deleteDirectory(source);
Query("moveDirectory",source.toString(),dest.toString(),"moveDirectory",dest.toString()+"\\"+source.getName().toString(),source.getParent());}


public void backupFile(File f) throws IOException {
File source=f;String filename;File dest=null;
filename=f.getName();String ext=FilenameUtils.getExtension(filename);
String Documents="doc docx xls xlsx ppt pptx pdf txt";
String Pictures="jpg png bmp tif gif";
String Music="mp3";
String Videos="mp4 mkv avi webm flv wmv";
     if(Documents.contains(ext)) {dest=new File(backupDir+"Documents\\"+f.getName());}
else if(Pictures.contains(ext)) {dest=new File(backupDir+"Pictures\\"+f.getName());}
else if(Music.contains(ext)) {dest=new File(backupDir+"Music\\"+f.getName());}
else if(Videos.contains(ext)) {dest=new File(backupDir+"Videos\\"+f.getName());}
FileUtils.copyFile(source, dest);
Query("backupFile",source.toString(),dest.toString(),"deleteFile",dest.toString(),"null");}


public void backupDirectory(File dir) throws IOException {
File source=dir;
File dest=new File(backupDir);
FileUtils.copyDirectoryToDirectory(source, dest);
Query("backupDirectory",source.toString(),dest.toString(),"deleteDirectory",dest.toString()+"\\"+dir.getName().toString(),"null");}


public  void backupFileAs(File f, String des) throws IOException {
File source=f;String filename;File dest=null;
filename=f.getName();String ext=FilenameUtils.getExtension(filename);
String Documents="doc docx xls xlsx ppt pptx pdf txt";
String Pictures="jpg png bmp tif gif";
String Music="mp3";
String Videos="mp4 mkv avi webm flv wmv";
     if(Documents.contains(ext)) {dest=new File(backupDir+"Documents\\"+des+"."+FilenameUtils.getExtension(f.toString()));}
else if(Pictures.contains(ext)) {dest=new File(backupDir+"Pictures\\"+f.getName());}
else if(Music.contains(ext)) {dest=new File(backupDir+"Music\\"+f.getName());}
else if(Videos.contains(ext)) {dest=new File(backupDir+"Videos\\"+f.getName());}
FileUtils.copyFile(source, dest);
Query("BackupFileAs",source.toString(),dest.toString(),"deleteFile",dest.toString(),"null");}

public  void backupDirectoryAs(File dir, String renameas) throws IOException {
File source=dir;
File dest=new File(backupDir);
FileUtils.copyDirectoryToDirectory(source, dest);REFRESH();
File sourceas=new File(backupDir+source.getName());
File destas=new File(backupDir+renameas);
sourceas.renameTo(destas);
Query("backupDirectoryAs",source.toString(),dest.toString(),"deleteDirectory",destas.toString(),"null");}




public void moveFile(File source, File dest) throws IOException {
FileUtils.moveFile(source, dest);refresh();
Query("moveFile",source.toString(),dest.toString(),"moveFile",dest.toString(),source.toString());}

public  void moveDirectory(File source, File dest) throws IOException {
FileUtils.moveDirectoryToDirectory(source, dest, true);REFRESH();
Query("moveDirectory",source.toString(),dest.toString(),"moveDirectory",dest.toString()+"\\"+source.getName(),source.getParent());}

public void favourtizeFile(File f) throws IOException {
File source=f;String filename;File dest=null;
filename=f.getName();String ext=FilenameUtils.getExtension(filename);
String Documents="doc docx xls xlsx ppt pptx pdf txt";
String Pictures="jpg png bmp tif gif";
String Music="mp3 mp4";
String Videos="mp4 mkv avi webm flv wmv";
     if(Documents.contains(ext)) {dest=new File(favouriteDir+"Documents\\"+f.getName());}
else if(Pictures.contains(ext)) {dest=new File(favouriteDir+"Pictures\\"+f.getName());}
else if(Music.contains(ext)) {dest=new File(favouriteDir+"Music\\"+f.getName());}
else if(Videos.contains(ext)) {dest=new File(favouriteDir+"Videos\\"+f.getName());}
FileUtils.moveFile(source, dest);refresh();
Query("favourtizeFile",source.toString(),dest.toString(),"moveFile",dest.toString(),source.toString());}


public void favourtizeDirectory(File dir) throws IOException {
File source=dir;
File dest=new File(favouriteDir);
FileUtils.moveDirectoryToDirectory(source, dest,true);REFRESH();
Query("favourtizeDirectory",source.toString(),dest.toString(),"moveDirectory",dest.toString()+"\\"+dir.getName().toString(),source.getParent());}


public void favourtizeFileAs(File f, String des) throws IOException {
File source=f;String filename;File dest=null;
filename=f.getName();String ext=FilenameUtils.getExtension(filename);
String Documents="doc docx xls xlsx ppt pptx pdf txt";
String Pictures="jpg png bmp tif gif";
String Music="mp3 mp4";
String Videos="mp4 mkv avi webm flv wmv";
     if(Documents.contains(ext)) {dest=new File(favouriteDir+"Documents\\"+des+"."+FilenameUtils.getExtension(f.toString()));}
else if(Pictures.contains(ext)) {dest=new File(favouriteDir+"Pictures\\"+f.getName());}
else if(Music.contains(ext)) {dest=new File(favouriteDir+"Music\\"+f.getName());}
else if(Videos.contains(ext)) {dest=new File(favouriteDir+"Videos\\"+f.getName());}
FileUtils.moveFile(source, dest);refresh();
Query("favourtizeFileAs",source.toString(),dest.toString(),"moveFile",dest.toString(),source.toString());}

public  void favourtizeDirectoryAs(File dir, String renameas) throws IOException {
File source=dir;
File dest=new File(favouriteDir);
FileUtils.moveDirectoryToDirectory(source, dest,true);

File sourceas=new File(favouriteDir+source.getName());
File destas=new File(favouriteDir+renameas);
sourceas.renameTo(destas);REFRESH();
Query("favourtizeDirectoryAs",source.toString(),dest.toString(),"moveDirectory",destas.toString(),source.toString());}

public  void compressImage(File file, File dest) throws IOException {
File input =file;
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

public void resizeImage(File file, File dest) throws IOException {
File output =dest;
BufferedImage originalImage = ImageIO.read(file);
int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
BufferedImage resizeImageJpg = resizeImage(originalImage, type);
ImageIO.write(resizeImageJpg, "jpg",output);} 
private static BufferedImage resizeImage(BufferedImage originalImage, int type){
int Width=originalImage.getWidth();
int Height=originalImage.getHeight();
//int Diagnal=(int)Math.sqrt((Width*Width)+(Height*Height));
double Slope=(double)Height/Width;
int width=0;int height=0;
if(Width>1280) {width=1280;}else {width=Width;}
height=(int) (Slope*width);
//width=(int)(Width*0.5);height=(int)(Height*0.5);
BufferedImage resizedImage = new BufferedImage(width, height, type);
Graphics2D g = resizedImage.createGraphics();
g.drawImage(originalImage, 0, 0, width, height, null);
g.dispose();return resizedImage;}




//********************************************************************************************************************************************************************************************************************
public Boolean searchFile(String fi) {
file f; String p,n,e,filename;
Boolean status=false;
filename=fi;
if(filename.contains(".")) {
String filenames[]=filename.split("\\.");
String fn=filenames[0];
String fe=filenames[1];
for(int i=0;i<files.size();i++) {
f=files.get(i);
p=f.path;  n=f.name; e=f.ext;

if((fn.equalsIgnoreCase(n)&& fe.equalsIgnoreCase(e))  ) {
status=true;
String pa=p+"\\"+n+"."+e;
setPath(pa);
break;}}}

else {
String fn=fi;
for(int i=0;i<files.size();i++) {
f=files.get(i);
p=f.path;  n=f.name; e=f.ext;
if((fn.equalsIgnoreCase(n))) {
status=true;
String pa=p+"\\"+n+"."+e;
setPath(pa);
break;}}}
return status;}


public Boolean SEARCHFile(String fi) {
file f; String p,n,e,filename;
Boolean status=false;
filename=fi;
ArrayList<String> filesFound=new ArrayList<String>();
if(filename.contains(".")) {
String filenames[]=filename.split("\\.");
String fn=filenames[0];
String fe=filenames[1];
for(int i=0;i<files.size();i++) {
f=files.get(i);
p=f.path;  n=f.name; e=f.ext;

if((fn.equalsIgnoreCase(n)&& fe.equalsIgnoreCase(e))  ) {
status=true;
String pa=p+"\\"+n+"."+e;
filesFound.add(pa);}}}

else {
String fn=fi;
for(int i=0;i<files.size();i++) {
f=files.get(i);
p=f.path;  n=f.name; e=f.ext;
if((fn.equalsIgnoreCase(n)) && n.contains(fn)) {
status=true;
String pa=p+"\\"+n+"."+e;
filesFound.add(pa);}}}

if(filesFound.size()==1) {setPath(filesFound.get(0));}

else {
String message="";
String fileFound="";
for(int i=0;i<filesFound.size();i++) {
String ff=filesFound.get(i);
String index=Integer.toString(i+1);
fileFound=index+": "+ff+"\n";
message+=fileFound;}

String context="Please choose file no. from following. \n\n"+message;
String fc = JOptionPane.showInputDialog(null, context, "Choose File#", JOptionPane.INFORMATION_MESSAGE);
int chooseIndex=Integer.parseInt(fc);
String chooseFile="";
chooseFile=filesFound.get(chooseIndex-1);
setPath(chooseFile);}
return status;}


public Boolean searchFileList(String filename) {
Boolean status=false;
File fi;
String fn; String fe;
if(filename.contains(".")){
for(int i=0;i<fileList.size();i++) {
fi=fileList.get(i);
fn=fi.getName();
fe=FilenameUtils.getExtension(fn);
if(filename.equalsIgnoreCase(fn) && filename.contains(fe)) {setPath(fi.toString());status=true;break;}}}

else{
String [] FN; String nm;
for(int i=0;i<fileList.size();i++) {
fi=fileList.get(i);
fn=fi.getName();FN=fn.split("\\.");nm=FN[0];
if(filename.equalsIgnoreCase(nm) && fn.contains(nm)) {setPath(fi.toString());status=true;break;}}}
return status;}


public void openFile(File f) {
try {
if (Desktop.isDesktopSupported()) {Desktop.getDesktop().open(f);}}
catch (IOException ioe) {ioe.printStackTrace();}}


public Boolean OPENFile(String fi) {
file f; String p,n,e,filename;
Boolean status=false;
filename=fi;
ArrayList<String> filesFound=new ArrayList<String>();
if(filename.contains(".")) {
String filenames[]=filename.split("\\.");
String fn=filenames[0];
String fe=filenames[1];
for(int i=0;i<files.size();i++) {
f=files.get(i);
p=f.path;  n=f.name; e=f.ext;

if((fn.equalsIgnoreCase(n)&& fe.equalsIgnoreCase(e))  ) {
status=true;
String pa=p+"\\"+n+"."+e;
filesFound.add(pa);}}}

else {
String fn=fi;
for(int i=0;i<files.size();i++) {
f=files.get(i);
p=f.path;  n=f.name; e=f.ext;
if((fn.equalsIgnoreCase(n)) && n.contains(fn)) {
status=true;
String pa=p+"\\"+n+"."+e;
filesFound.add(pa);}}}

if(filesFound.size()==1) {
try {
if (Desktop.isDesktopSupported()) {
Desktop.getDesktop().open(new File(filesFound.get(0)));openfileList.add(filesFound.get(0));}}
catch (IOException ioe) {ioe.printStackTrace();}}

else {
String message="";
String fileFound="";
for(int i=0;i<filesFound.size();i++) {
String ff=filesFound.get(i);
String index=Integer.toString(i+1);
fileFound=index+": "+ff+"\n";
message+=fileFound;}

String context="Please choose file no. from following. \n\n"+message;
String fc = JOptionPane.showInputDialog(null, context, "Choose File#", JOptionPane.INFORMATION_MESSAGE);
int chooseIndex=Integer.parseInt(fc);
String chooseFile="";
chooseFile=filesFound.get(chooseIndex-1);
try {
if (Desktop.isDesktopSupported()) {
Desktop.getDesktop().open(new File(chooseFile));openfileList.add(chooseFile);}}
catch (IOException ioe) {ioe.printStackTrace();}}
return status;}

public Process process;

public static void openURL() throws URISyntaxException {
try {
String url="https://accounts.google.com/";
if (Desktop.isDesktopSupported()) {
URI uri=new URI(url);Desktop.getDesktop().browse(uri);}} 
catch (IOException ioe) {ioe.printStackTrace();}}

public  void closeFile(String filename) {
ArrayList<String> opened=new ArrayList<String>();
String fi="";String toClose=""; File file;int i;

for(i=0;i<openfileList.size();i++) {
fi=openfileList.get(i);
if(fi.contains(filename)) {
opened.add(fi);}}

if(opened.size()==1) {toClose=opened.get(0);file=new File(toClose);openfileList.remove(toClose);}
else {
String mes="";
for(i=0;i<opened.size();i++) {mes+=Integer.toString(i+1)+ " "+opened.get(i)+"\n"; }
String context="Please choose file no. from following. \n\n"+mes;;
String fc = JOptionPane.showInputDialog(null, context, "Choose File#", JOptionPane.INFORMATION_MESSAGE);
int chooseIndex=Integer.parseInt(fc);
toClose=opened.get(chooseIndex-1);file=new File(toClose);openfileList.remove(toClose);}

//System.out.println("All files opened: ");
//for(String s:openfileList) {System.out.println(s);}
//
//System.out.println("\n opened files now:");
//for(String d:opened) {System.out.println(d);}

String cmd="";
String ext = FilenameUtils.getExtension(file.toString());

String notepad="taskkill /IM notepad.exe";
String word="taskkill /IM winword.exe";
String excel="taskkill /IM excel.exe";
String powerpoint="taskkill /IM powerpnt.exe";
String pdf="taskkill /im acroRD32.exe";
String jpg="taskkill /im dllhost.exe";
String mp3="taskkill /F /IM wmplayer.exe";
String mp4="taskkill /F /IM vlc.exe";

     if(ext.equals("txt")){cmd=notepad;}
else if(ext.equals("txt")){cmd=notepad;}
else if(ext.equals("docx")){cmd=word;}
else if(ext.equals("pdf")){cmd=pdf;}
else if(ext.equals("xlsx")){cmd=excel;}
else if(ext.equals("pptx")){cmd=powerpoint;}
else if(ext.equals("mp3")){cmd=mp3;} 
else if(ext.equals("3gpp") || ext.equals("wmv")){cmd=mp4;}
else if(ext.equals("jpg") || ext.equals("png")){cmd=jpg;}
try { process = Runtime.getRuntime().exec(cmd);} 
catch (Exception ex) {ex.printStackTrace();}}

public  void closeAll() {
String notepad="taskkill /F /IM notepad.exe";
String word="taskkill /F /IM winword.exe";
String excel="taskkill /F /IM excel.exe";
String slide="taskkill /F /IM powerpnt.exe";
String pdf="taskkill /F /IM acroRD32.exe";
String jpg="taskkill /f /im dllhost.exe";
String mp3="taskkill /F /IM wmplayer.exe";
String mp4="taskkill /F /IM vlc.exe";
String cmd[]= {notepad,word,excel,slide,pdf,jpg,mp3,mp4};
for(int i=0;i<cmd.length;i++) {
try { process = Runtime.getRuntime().exec(cmd[i]);} 
catch (Exception ex) {ex.printStackTrace();}}openfileList.clear();}

public void createFile(String filename) throws IOException {
File dest=null;
String ext=FilenameUtils.getExtension(filename);
String Documents="doc docx xls xlsx ppt pptx pdf txt";
String Pictures="jpg png bmp tif gif";
String Music="mp3 mp4";
String Videos="mp4 mkv avi webm flv wmv";
     if(Documents.contains(ext)){dest=new File(defaultDir+filename);}
else if(Pictures.contains(ext)) {dest=new File(defaultDir+filename);}
else if(Music.contains(ext))    {dest=new File(defaultDir+filename);}
else if(Videos.contains(ext))   {dest=new File(defaultDir+filename);}
	     
try {
if (Desktop.isDesktopSupported()) {
File f=dest;
f.createNewFile();
Desktop.getDesktop().open(f);}} 
catch (IOException ioe) {ioe.printStackTrace();}
refresh();
Query("createFile",filename,dest.toString(),"deleteFile",dest.toString(),"null");}

public void createFolder(String fol)throws IOException {
File fi=new File(fol);
if(fi.exists() && fi.isDirectory()) {}
else{fi.mkdir();}}

public void createDirectory(String fol) throws IOException {
File fi=new File(defaultDir+fol);
if(fi.exists() && fi.isDirectory()) {}
else{fi.mkdir();
Query("createDirectory",fol,fol,"deleteDirectory",fi.toString(),"null");}}

public  void renameFile(File source, File dest) throws IOException {
source.renameTo(dest);
refresh();
Query("renameFile",source.toString(),dest.toString(),"renameFile",dest.toString(),source.toString());}

public  void renameDir(File source, File dest) throws IOException {
source.renameTo(dest);
REFRESH();
Query("renameDirectory",source.toString(),dest.toString(),"renameDirectory",dest.toString(),source.toString());}






public  void renameAsFile(File source, File dest) throws IOException {
source.renameTo(dest);refresh();
Query("renameAsFile",source.toString(),dest.toString(),"renameFile",dest.toString(),source.toString());}



public  void deleteFile(File source) throws IOException {
String message="Are you sure you want to delete "+source.toString()+" ?";
int n = JOptionPane.showConfirmDialog(null,message,"Delete File?",JOptionPane.YES_NO_OPTION);
if (n==0) {source.delete();}refresh();}

public  void deleteDirectory(File source) throws IOException {
String message="Are you sure you want to delete "+source.toString()+" ?";
int n = JOptionPane.showConfirmDialog(null,message,"Delete Directory?",JOptionPane.YES_NO_OPTION);
if (n==0) {FileUtils.deleteDirectory(source);}REFRESH();}



public  void removeFile(File source) throws IOException {
String message="Are you sure you want to delete "+source.toString()+" ?";
int n = JOptionPane.showConfirmDialog(null,message,"Delete File?",JOptionPane.YES_NO_OPTION);
String src,des="";
if (n==0) {
src=source.getName();
des=trashDir+src;
long now=System.currentTimeMillis();
source.setLastModified(now);
FileUtils.moveFile(source, new File(des));}refresh();
Query("removeFile",source.toString(),des.toString(),"moveFile",des.toString(),source.toString());}

public  void removeDirectory(File source) throws IOException {
File dest=new File(trashDir);
String message="Are you sure you want to delete "+source.toString()+" ?";
int n = JOptionPane.showConfirmDialog(null,message,"Delete Directory?",JOptionPane.YES_NO_OPTION);
if (n==0) {FileUtils.moveDirectoryToDirectory(source,dest,true);}REFRESH();
Query("removeDirectory",source.toString(),dest.toString(),"moveDirectory",dest.toString(),source.toString());}


public void updateTrash() throws IOException {
File dir = new File(trashDir);
final long days=24*60*60*1000;
long threshold = System.currentTimeMillis() - (1*days);
FileFilter olderFile=new AgeFileFilter(threshold, true);
File[] files=dir.listFiles(olderFile);
for (File file : files) {
if(file.isFile()) {file.delete();}
else if(file.isDirectory()) {FileUtils.deleteDirectory(file);}}}


public void deleteTrash() throws IOException {
File dir = new File(trashDir);
File[] files=dir.listFiles();
for (File file : files) {
if(file.isFile()) {file.delete();}
else if(file.isDirectory()) {FileUtils.deleteDirectory(file);}}}


public void lastModify(File source) throws IOException {
ArrayList<Path> trashList=new ArrayList<Path>() ;
String path=trashDir;
try (Stream<Path> paths = Files.walk(Paths.get(path))) {
paths.filter(Files::isRegularFile).forEach(trashList::add);}
long last;
long now;;
long diff=0;
String name="";
for (int i=0;i<trashList.size();i++) {
Path p=trashList.get(i);
File f=new File(p.toString());
name=f.getName();
last=f.lastModified();
now=System.currentTimeMillis();
diff=(now-last)/(1000*60*60);
System.out.println(i+1+": "+name+" "+diff);}}

public void printFile(File source) {
try {
if (Desktop.isDesktopSupported()) {
Desktop.getDesktop().print(source);}} 
catch (IOException ioe) {ioe.printStackTrace();}}

public void printDirectory(File dir) {
File f[]=dir.listFiles();
File fi;String EXT="docx doc pdf txt"; String fe="";
for(int i=0;i<f.length;i++) {
fi=f[i];fe=FilenameUtils.getExtension(fi.getName());
if(EXT.contains(fe)) {printFile(fi);}}}

public  void displayAllFiles() {
res="";
Path f;for(int i=0;i<FileList.size();i++) {
f=FileList.get(i);
res+=f.toString()+"\n";}
res+="number of files found: "+ FileList.size()+"\n";
info(res);
res="";
}

public  void displaySystemFiles() {
res="";
File f;for(int i=0;i<fileList.size();i++) {
f=fileList.get(i);
res+=f.toString()+"\n";}
res+="number of files found: "+ fileList.size()+"\n";
info(res);
res="";}

public  void displaySystemFolders() {
res="";
Path d;for(int i=0;i<DirectoryList.size();i++) {
d=DirectoryList.get(i);
res+=d.toString()+"\n";}
res+="number of Directories found: "+ DirectoryList.size()+"\n";
info(res);
res="";}



public  void displayDirectoryFiles(File dir) {
File files[]=dir.listFiles();
res="";
for(File f:files) {
if(f.isFile()==true) {
res+=f.toString()+"\n";}}
info(res);}

public  void displayDirectoryFolders(File dir) {
File files[]=dir.listFiles();
res="";
for(File f:files) {
if(f.isDirectory()==true) {
res+=f.toString()+"\n";}}
info(res);}

public  void displayDirectorySubFiles(File dir) throws IOException {
ArrayList<Path> dirSubFiles=new ArrayList<>();
try (Stream<Path> paths = Files.walk(Paths.get(dir.toString()))) {paths.filter(Files::isRegularFile).forEach(dirSubFiles::add);}
res="";
for(Path f:dirSubFiles) {
res+=f.toString()+"\n";}
info(res);}

public  void displayDirectorySubFolders(File dir) throws IOException {
ArrayList<Path> dirSubFolders=new ArrayList<>();
try (Stream<Path> paths = Files.walk(Paths.get(dir.toString()))) {paths.filter(Files::isDirectory).forEach(dirSubFolders::add);}
res="";
for(Path f:dirSubFolders) {
res+=f.toString()+"\n";}
info(res);}


public void setPath(String s) {
String pa[]=s.split("\\\\");
String absPath="";
int si=pa.length;
int i=0;
for(i=0;i<si-1;i++) {absPath=absPath+pa[i]+"\\\\";}
absPath=absPath+pa[i];
setFile(absPath);}

public String toPath(String s) {
String pa[]=s.split("\\\\");
String absPath="";
int si=pa.length;
int i=0;
for(i=0;i<si-1;i++) {absPath=absPath+pa[i]+"\\\\";}
absPath=absPath+pa[i];
return absPath;}

//*******************************************************************************************************************************************************************************************************
public Process openProcess;
public Process closeProcess;
public void openNotepad() throws IOException, InterruptedException {
try{
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "start notepad.exe";
openProcess= Runtime.getRuntime().exec(cmd);
System.out.println("Notepad Opened");}
catch (Exception ex) {ex.printStackTrace();}}

public void openWord() throws IOException, InterruptedException {
try{
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "start winword.exe";
openProcess= Runtime.getRuntime().exec(cmd);
System.out.println("Microsoft Word Opened");}
catch (Exception ex) {ex.printStackTrace();}}

public void openExcel() throws IOException, InterruptedException {
try{
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "start excel.exe";
openProcess= Runtime.getRuntime().exec(cmd);
System.out.println("Microsoft Excel Opened");}
catch (Exception ex) {ex.printStackTrace();}}

public void openPowerPoint() throws IOException, InterruptedException {
try{
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "start powerpnt.exe";
openProcess= Runtime.getRuntime().exec(cmd);
System.out.println("Microsoft PowerPoint Opened");}
catch (Exception ex) {ex.printStackTrace();}}

public void openAcrobat() throws IOException, InterruptedException {
try{
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "start acroRD32.exe";
openProcess= Runtime.getRuntime().exec(cmd);
System.out.println("Adobe Acrobat Opened");}
catch (Exception ex) {ex.printStackTrace();}}

public void openMediaPlayer() throws IOException, InterruptedException {
try{
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "start wmplayer.exe";
openProcess= Runtime.getRuntime().exec(cmd);
System.out.println("Windows MediaPlayer Opened");}
catch (Exception ex) {ex.printStackTrace();}}

public void openVLC() throws IOException, InterruptedException {
try{
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "start vlc.exe";
openProcess= Runtime.getRuntime().exec(cmd);
System.out.println("VLC MediaPlayer Opened");}
catch (Exception ex) {ex.printStackTrace();}}



public void closeNotepad() throws IOException, InterruptedException {
try{
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "taskkill /F /IM notepad.exe";
closeProcess= Runtime.getRuntime().exec(cmd);
System.out.println("Notepad Closed");}
catch (Exception ex) {ex.printStackTrace();}}

public void closeWord() throws IOException, InterruptedException {
try{
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "taskkill /F /IM winword.exe";
closeProcess= Runtime.getRuntime().exec(cmd);
System.out.println("Microsoft Word Closed");}
catch (Exception ex) {ex.printStackTrace();}}

public void closeExcel() throws IOException, InterruptedException {
try{
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "taskkill /F /IM excel.exe";
closeProcess= Runtime.getRuntime().exec(cmd);
System.out.println("Microsoft Excel Closed");}
catch (Exception ex) {ex.printStackTrace();}}

public void closePowerPoint() throws IOException, InterruptedException {
try{
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "taskkill /F /IM powerpnt.exe";
closeProcess= Runtime.getRuntime().exec(cmd);
System.out.println("Microsoft PowerPoint Closed");}
catch (Exception ex) {ex.printStackTrace();}}

public void closeAcrobat() throws IOException, InterruptedException {
try{
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "taskkill /F /IM acroRD32.exe";
closeProcess= Runtime.getRuntime().exec(cmd);
System.out.println("Adobe Acrobat Closed");}
catch (Exception ex) {ex.printStackTrace();}}

public void closeMediaPlayer() throws IOException, InterruptedException {
try{
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "taskkill /F /IM wmplayer.exe";
closeProcess= Runtime.getRuntime().exec(cmd);
System.out.println("Windows MediaPlayer Closed");}
catch (Exception ex) {ex.printStackTrace();}}

public void closeVLC() throws IOException, InterruptedException {
try{
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "taskkill /F /IM vlc.exe";
closeProcess= Runtime.getRuntime().exec(cmd);
System.out.println("VLC MediaPlayer Closed");}
catch (Exception ex) {ex.printStackTrace();}}

//**********************************************************************************************************************************************************************************************************
public void ping() {
String domainName = "google.com";
String command = "ping -n 3 " + domainName;
StringBuffer output = new StringBuffer();
Process p;
try {
p = Runtime.getRuntime().exec(command);
p.waitFor();
BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
String line = "";		
while ((line = reader.readLine())!= null) {output.append(line + "\n");}} 
catch (Exception e) {e.printStackTrace();}
System.out.println(output.toString());}

public void date() throws IOException {
String[] cmd = new String[3];
cmd[0] = "cmd.exe";
cmd[1] = "/C";
cmd[2] = "date";
StringBuffer output = new StringBuffer();
Process p;
try {
p = Runtime.getRuntime().exec(cmd);
BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
String line = "";			
while ((line = reader.readLine())!= null) {output.append(line + "\n");System.out.println(">"+output.toString());}} 
catch (Exception e) {e.printStackTrace();}
System.out.println(">"+output.toString());}

public void toMilli() throws ParseException {
String day= "23Jul2018";
SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy");
Date date = (Date) sdf.parse(day);
long millis = date.getTime();
System.out.println(millis);
System.out.println(new Date(millis));}

public void toDate() {
DateFormat sdf;
sdf= new SimpleDateFormat("ddMMMyyyy");
long ts=System.currentTimeMillis();
Date d=new Date(ts);
System.out.println(sdf.format(d));}

public static void dateTime(){
DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
LocalDateTime now = LocalDateTime.now();  
System.out.println(dtf.format(now));
System.out.println(now);
System.out.println(java.time.LocalDate.now());
   
Date date = new Date(30*12*30*24*60*60);
System.out.println(sdf.format(date));

Calendar cal = Calendar.getInstance();
System.out.println(sdf.format(cal.getTime()));

LocalDateTime now1 = LocalDateTime.now();
System.out.println(dtf.format(now1));

LocalDate localDate = LocalDate.now();
System.out.println(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate));}


}