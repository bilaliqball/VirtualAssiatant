package fileOperations;
import javax.swing.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;


public class Undo {
public String OP; public void setOP(String op) {this.OP=op;}
public String SR; public void setSR(String sr) {this.SR=sr;}
public String DS; public void setDS(String ds) {this.DS=ds;}
public String UO; public void setUO(String uo) {this.UO=uo;}
public String US; public void setUS(String us) {this.US=us;}
public String UD; public void setUD(String ud) {this.UD=ud;}
public String mes;




public Undo() {}


public String toPath(String s) {
String pa[]=s.split("\\\\");
String absPath="";
int si=pa.length;
int i=0;
for(i=0;i<si;i++) {absPath=absPath+pa[i]+"\\\\";}
return absPath;} 

public void print(String s) {System.out.println(s);}
public int showMessage(String mes){
int n = JOptionPane.showConfirmDialog(null,mes,"Info",JOptionPane.YES_NO_OPTION);
return n;}


public  void renameFile(File source, File dest) throws IOException {
source.renameTo(dest);}

public  void renameDirectory(File source, File dest) throws IOException {
source.renameTo(dest);}

public  void copyFile(File source, File dest) throws IOException {
FileUtils.copyFile(source, dest);}

public  void copyDirectory(File source, File dest) throws IOException {
FileUtils.copyDirectoryToDirectory(source, dest);}

public void moveFile(File source, File dest) throws IOException {
FileUtils.moveFile(source, dest);}

public  void moveDirectory(File source, File dest) throws IOException {
FileUtils.moveDirectoryToDirectory(source, dest, true);}

public  void deleteFile(File source) throws IOException {
source.delete();}

public  void deleteDirectory(File source) throws IOException {
FileUtils.deleteDirectory(source);}


public void undoCreateDirectory(String undo,String source,String dest) throws IOException {
mes="undoCreateDirectory: "+ "\n" +undo+"\n"+source+" ?";
mes="undo Create Directory?";
int n=showMessage(mes);
if(n==0) {
deleteDirectory(new File(dest));}}

public void undoCreateFile(String undo,String source,String dest) throws IOException {
mes="undoCreateFile: "+ "\n" +undo+"\n"+source +" ?";
mes="undo Create File?";
int n=showMessage(mes);
if(n==0) {
deleteFile(new File(dest));}}

public void undoRenameFile(String undo,String source,String dest) throws IOException {
mes="undoRenameFile: "+ "\n" +undo+"\n"+source +"\n"+dest +" ?";
mes="undo Rename File?";
int n=showMessage(mes);
if(n==0) {
renameFile(new File(source), new File(dest));}}



public void undoRenameDir(String undo,String source,String dest) throws IOException {
mes="undoRenameDir: "+ "\n" +undo+"\n"+source +"\n"+dest +" ?";
mes="undo Rename Directory?";
int n=showMessage(mes);
if(n==0) {
renameDirectory(new File(source), new File(dest));}}

public void undoCopyFile(String undo,String source,String dest) throws IOException {
mes="undoCopyFile: "+ "\n" +undo+"\n"+source +" ?";
mes="undo Copy File?";
int n=showMessage(mes);
if(n==0) {
deleteFile(new File(source));}}

public void undoCopyDirectory(String undo,String source,String dest) throws IOException {
mes="undoCopyDirectory :" + "\n" +undo+"\n"+source +" ?";
mes="undo Copy Directory?";
int n=showMessage(mes);
if(n==0) {
deleteDirectory(new File(source));}}

public void undoBackupFile(String undo,String source,String dest) throws IOException {
mes="undoBackupFile: "+ "\n" +undo+"\n"+source +" ?";
mes="undo Backup File?";
int n=showMessage(mes);
if(n==0) {
deleteFile(new File(source));}}

public void undoBackupDirectory(String undo,String source,String dest) throws IOException {
mes="undoBackupDirectory: "+ "\n" +undo+"\n"+source +" ?";
mes="undo Backup Directory?";
int n=showMessage(mes);
if(n==0) {
deleteDirectory(new File(source));}}

public void undoFavourtizeFile(String undo,String source,String dest) throws IOException {
mes="undoFavourtizeFile: "+ "\n" +undo+"\n"+source +"\n"+dest +" ?";
mes="undo Favourtize File?";
int n=showMessage(mes);
if(n==0) {
moveFile(new File(source),new File(dest));}}

public void undoFavourtizeDirectory(String undo,String source,String dest) throws IOException {
mes="undoFavourtizeDirectory: "+ "\n" +undo+"\n"+source +"\n"+dest +" ?";
mes="undo Favourtize Directory?";
int n=showMessage(mes);
if(n==0) {
moveDirectory(new File(source), new File(dest));}}

public void undoMoveFile(String undo,String source,String dest) throws IOException {
mes= "undoMoveFile: "+ "\n" +undo+"\n"+source +"\n"+dest +" ?";
mes= "undo Move File?";
int n=showMessage(mes);
if(n==0) {
moveFile(new File(source), new File(dest));}}

public void undoMoveDirectory(String undo,String source,String dest) throws IOException {
mes="undoMoveDirectory: "+ "\n" +undo+"\n"+source +"\n"+dest +" ?";
mes="undo Move Directory?";
int n=showMessage(mes);
if(n==0) {
moveDirectory(new File(source), new File(dest));}}



public void undoRemoveFile(String undo,String source,String dest) throws IOException {
mes="undoRemoveFile:"+ "\n" +undo+"\n"+source +"\n"+dest +" ?";
mes="undo Remove File?";
int n=showMessage(mes);
if(n==0) {
moveFile(new File(source), new File(dest));}}

public void undoRemoveDirectory(String undo,String source,String dest) throws IOException {
mes="undoRemoveDirectory: "+ "\n" +undo+"\n"+source +"\n"+dest +" ?";
mes="undo Remove Directory?";
int n=showMessage(mes);
if(n==0) {
moveDirectory(new File(source), new File(dest));}}


public void undoCompressAllFiles(String undo,String source,String dest) throws IOException {
mes="undoCompressAllFiles: "+ "\n" +undo+"\n"+source+" ?";
mes="undo Compress All Files?";
int n=showMessage(mes);
if(n==0) {
deleteDirectory(new File(source));}}

public void undoCompressFiles(String undo,String source,String dest) throws IOException {
mes="undoCompressFiles: "+ "\n" +undo+"\n"+source +" ?";
mes="undo Compress Files?";
int n=showMessage(mes);
if(n==0) {
deleteFile(new File(source));}}

public void undoCompressFile(String undo,String source,String dest) throws IOException {
mes="undoCompressFile: "+ "\n" +undo+"\n"+source +" ?";
mes="undoCompressFile?";
int n=showMessage(mes);
if(n==0) {
deleteDirectory(new File(source));}}

public void undoCompressDirectory(String undo,String source,String dest) throws IOException {
mes="undoCompressDirectory: "+ "\n" +undo+"\n"+source +" ?";
mes="undoCompressDirectory?";
int n=showMessage(mes);
if(n==0) {
deleteDirectory(new File(source));	}}


public void undoResizeAllFiles(String undo,String source,String dest) throws IOException {
mes="undoResizeAllFiles: "+ "\n" +undo+"\n"+source +" ?";
mes="undoResizeAllFiles?";
int n=showMessage(mes);
if(n==0) {
deleteDirectory(new File(source));}}

public void undoResizeFiles(String undo,String source,String dest) throws IOException {
mes="undoResizeFiles: "+ "\n" +undo+"\n"+source +" ?";
mes="undoResizeFiles?";
int n=showMessage(mes);
if(n==0) {
deleteDirectory(new File(source));}}

public void undoResizeFile(String undo,String source,String dest) throws IOException {
mes="undoResizeFile: "+ "\n" +undo+"\n"+source +" ?";
mes="undoResizeFile?";
int n=showMessage(mes);
if(n==0) {
deleteFile(new File(source));}}

public void undoResizeDirectory(String undo,String source,String dest) throws IOException {
mes="undoResizeDirectory: "+ "\n" +undo+"\n"+source +" ?";
mes="undoResizeDirectory?";
int n=showMessage(mes);
if(n==0) {
deleteDirectory(new File(source));}}


public void undoZipAllFiles(String undo,String source,String dest) throws IOException {
mes="undoZipAllFiles: "+ "\n" +undo+"\n"+source +" ?";
mes="undoZipAllFiles?";
int n=showMessage(mes);
if(n==0) {
deleteFile(new File(source));}}

public void undoZipFiles(String undo,String source,String dest) throws IOException {
mes="undoZipFiles: "+ "\n" +undo+"\n"+source +" ?";
mes="undoZipFiles?";
int n=showMessage(mes);
if(n==0) {
deleteFile(new File(source));}}

public void undoZipFile(String undo,String source,String dest) throws IOException {
mes="undoZipFile: "+ "\n" +undo+"\n"+source +" ?";
mes="undoZipFile?";
int n=showMessage(mes);
if(n==0) {
deleteFile(new File(source));}}

public void undoZipDirectory(String undo,String source,String dest) throws IOException {
mes="undoZipDirectory: "+ "\n" +undo+"\n"+source +" ?";
mes="undoZipDirectory?";
int n=showMessage(mes);
if(n==0) {
deleteFile(new File(source));	}}


public void undoResizeZipAllFiles(String undo,String source,String dest) throws IOException {
mes="undoResizeZipAllFiles: "+ "\n" +undo+"\n"+source +" ?";
mes="undoResizeZipAllFiles?";
int n=showMessage(mes);
if(n==0) {
deleteFile(new File(source));}}

public void undoCompressZipAllFiles(String undo,String source,String dest) throws IOException {
mes="undoCompressZipAllFiles: "+ "\n" +undo+"\n"+source +" ?";
mes="undoCompressZipAllFiles?";
int n=showMessage(mes);
if(n==0) {
deleteFile(new File(source));}}






public void isUndo(String les) throws IOException, InterruptedException, ParseException {
String command[]=les.split(" ");
int params=command.length;
System.out.println("Last executed command: "+ les +"Number of Params: "+params);
setOP(command[0]);System.out.println("OPeration: " +command[0]);
setSR(command[1]);System.out.println("Source: " +command[1]);
setDS(command[2]);System.out.println("Destination: " +command[2]);
setUO(command[3]);System.out.println("UndoOperation: " +command[3]);
setUS(command[4]);System.out.println("UndoSource: " +command[4]);
setUD(command[5]);System.out.println("undoDestination: " +command[5]);
undoOperation(command[0],command[3],command[4],command[5]);}



public void undoOperation(String operation,String undo,String source,String dest) throws IOException, InterruptedException, ParseException {
     if(operation.contains("reat") && operation.contains("cre")) {undoCreateFile(undo,source,dest);}
else if(operation.contains("reat") && operation.contains("ir")) {undoCreateDirectory(undo,source,dest);}

else if(operation.contains("ip")  && operation.contains("esi")) {undoResizeZipAllFiles(undo,source,dest);}
else if(operation.contains("ip" ) && operation.contains("ompr")){undoCompressZipAllFiles(undo,source,dest);} 
     
else if(operation.contains("esi") && operation.contains("ll")) {undoResizeAllFiles(undo,source,dest);}
else if(operation.contains("esi") && operation.contains("les")){undoResizeFiles(undo,source,dest);}
else if(operation.contains("esi") && operation.contains("ir")) {undoResizeDirectory(undo,source,dest);}
else if(operation.contains("esi") && operation.contains("si")) {undoResizeFile(undo,source,dest);}

else if(operation.contains("mpr") && operation.contains("ll")) {undoCompressAllFiles(undo,source,dest);}
else if(operation.contains("mpr") && operation.contains("il")) {undoCompressFiles(undo,source,dest);}
else if(operation.contains("mpr") && operation.contains("ir")) {undoCompressDirectory(undo,source,dest);}
else if(operation.contains("mpr") && operation.contains("om")) {undoCompressFile(undo,source,dest);}
	 
else if(operation.contains("ip")  && operation.contains("ll")) {undoZipAllFiles(undo,source,dest);} 
else if(operation.contains("ip")  && operation.contains("il")) {undoZipFiles(undo,source,dest);}	 
else if(operation.contains("ip")  && operation.contains("ir")) {undoZipDirectory(undo,source,dest);}
else if(operation.contains("ip")  && operation.contains("ip")) {undoZipFile(undo,source,dest);} 

else if(operation.contains("ack") && operation.contains("ir")) {undoBackupDirectory(undo,source,dest);}
else if(operation.contains("ack") && operation.contains("ac")) {undoBackupFile(undo,source,dest);}	 
else if(operation.contains("avo") && operation.contains("ir")) {undoFavourtizeDirectory(undo,source,dest);}
else if(operation.contains("avo") && operation.contains("av")) {undoFavourtizeFile(undo,source,dest);}
	 
else if(operation.contains("ove") && operation.contains("ir")) {undoMoveDirectory(undo,source,dest);}
else if(operation.contains("ove") && operation.contains("ove")) {undoMoveFile(undo,source,dest);}
else if(operation.contains("opy") && operation.contains("ir")) {undoCopyDirectory(undo,source,dest);}
else if(operation.contains("opy") && operation.contains("op")) {undoCopyFile(undo,source,dest);}

else if(operation.contains("em") && operation.contains("ir")) {undoRemoveDirectory(undo,source,dest);}
else if(operation.contains("em") && operation.contains("em")) {undoRemoveFile(undo,source,dest);}
else if(operation.contains("en") && operation.contains("en")) {undoRenameFile(undo,source,dest);}

else {}}

}
