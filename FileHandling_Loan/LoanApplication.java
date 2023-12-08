package FileHandling_Loan;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class LoanApplication
{
public File file;
public String fileName;

public void createFile(String fileName) throws IOException
{
this.fileName = fileName;
file = new File("C:/Users/anany/OneDrive/Documents/COLLEGE/SECOND YEAR/JAVA Programming/JAVA_Project/Loan_Applications/" + fileName);

if(file.createNewFile())
System.out.println("Loan File created succesfully for this account.");
}

void getFileInfo()
{
if(file.exists())
{
System.out.println("file name: "+file.getName());
System.out.println("file size: "+file.length());
System.out.println("file writable: "+file.canWrite());
System.out.println("file readable: "+file.canRead());
System.out.println("file Path: "+file.getAbsolutePath());
}
}

public void writeFile(String details) throws IOException
{
if(file.exists())
{
FileWriter fw = new FileWriter(file);
fw.write(details);
fw.close();
}
}

public void readFile() throws FileNotFoundException, IOException 
{
if (file.exists()) 
{
try (FileReader fr = new FileReader(file)) 
{
int data;
while ((data = fr.read()) != -1) 
{
System.out.print((char) data);
}
}
}
}
}