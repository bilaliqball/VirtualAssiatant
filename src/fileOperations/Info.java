package fileOperations;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Info {
public String operation="";
public void showMessage(String mes,String tit){JOptionPane.showMessageDialog(null, mes, tit, JOptionPane.PLAIN_MESSAGE);}

public void displayAllCmd() {
ImageIcon icon = new ImageIcon("C:\\Users\\bilal.iqbal\\Pictures\\FILES\\Pictures\\cmd.jpg");
JOptionPane.showMessageDialog(null,icon,"All CMD Descriptions", JOptionPane.PLAIN_MESSAGE);}




public String openInfo(){
String mes="open file against provided name";
return mes;}

public String closeInfo(){
String mes="close file against provided name";
return mes;}

public String renameInfo(){
String mes="rename file/directory against provided name";
return mes;}

public String removeInfo(){
String mes="remove file/directory against provided name";
return mes;}

public String deleteInfo(){
String mes="permanently delete file/directory against provided name";
return mes;}

public String printInfo(){
String mes="print file/directory";
return mes;}

public String copyInfo(){
String mes="copy file/directory to specified provided path";
return mes;}

public String moveInfo(){
String mes="move file/directory to specified provided path";
return mes;}

public String backupInfo(){
String mes="backup file/directory to predefined path";
return mes;}

public String favourtInfo(){
String mes="favourtize file/directory to predefined path";
return mes;}

public String arrangeInfo(){
String mes="arrange audi/video/docu files to specific folders";
return mes;}

public String filterInfo(){
String mes="filter files on basis of name/type";
return mes;}

public String extractInfo(){
String mes="Extarct files on the basis of extentions";
return mes;}

public String searchInfo(){
String mes="search file/directory against provided name";
return mes;}

public String sortInfo(){
String mes="sort files on basis of name/path/size";
return mes;}

public String compressInfo(){
String mes="compress image/video files";
return mes;}

public String resizeInfo(){
String mes="resize image/video";
return mes;}

public String zipInfo(){
String mes="create zip for files/directory";
return mes;}

public String displayInfo(){
String mes="display";
return mes;}





public void checkInfoMessage(String op){this.operation=op;
	 if(operation.contains("arc")) {String res=searchInfo();	showMessage(res,"searchInfo");}
else if(operation.contains("ran")) {String res=arrangeInfo();	showMessage(res,"arrangeInfo");}
else if(operation.contains("pen")) {String res=openInfo();		showMessage(res,"openInfo");}
else if(operation.contains("ose")) {String res=closeInfo();	    showMessage(res,"closeInfo()");}
else if(operation.contains("ena")) {String res=renameInfo();	showMessage(res,"renameInfo");}
else if(operation.contains("emo")) {String res=removeInfo();	showMessage(res,"removeInfo");}
else if(operation.contains("ele")) {String res=deleteInfo();	showMessage(res,"deleteInfo");}
else if(operation.contains("rin")) {String res=printInfo();	    showMessage(res,"printInfo");}
else if(operation.contains("opy")) {String res=copyInfo();		showMessage(res,"copyInfo");}
else if(operation.contains("ove")) {String res=moveInfo();		showMessage(res,"moveInfo");}
else if(operation.contains("ack")) {String res=backupInfo();	showMessage(res,"backupInfo");}
else if(operation.contains("avo")) {String res=favourtInfo();   showMessage(res,"favourtizeInfo");}
else if(operation.contains("ilt")) {String res=filterInfo();	showMessage(res,"filterInfo");}
else if(operation.contains("isp")) {String res=displayInfo();	showMessage(res,"displayInfo");}
else if(operation.contains("ort")) {String res=sortInfo();		showMessage(res,"sortInfo");}
else if(operation.contains("xtr")) {String res=extractInfo();	showMessage(res,"extractInfo");}
else if(operation.contains("mpr")) {String res=compressInfo();	showMessage(res,"compressInfo");}
else if(operation.contains("esi")) {String res=resizeInfo();	showMessage(res,"resizeInfo");}
else if(operation.contains("zip")) {String res=zipInfo();		showMessage(res,"zipInfo");}
else if(operation.contains("all")) {displayAllCmd();}
else {String res="No Info available.";showMessage(res,"Info");}}
	

}
