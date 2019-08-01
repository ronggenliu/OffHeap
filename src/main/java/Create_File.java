import java.io.*;

class Create_File 
{ 
    public static void main(String[] args) throws IOException  
    { 
        //attach keyboard to DataInputStream 
        DataInputStream dis=new DataInputStream(System.in); 
  
        // attach file to FileOutputStream 
        FileOutputStream fout=new FileOutputStream("file.txt"); 
  
        //attach FileOutputStream to BufferedOutputStream 
        BufferedOutputStream bout=new BufferedOutputStream(fout,1024); 
        System.out.println("Enter text (@ at the end):"); 
        char ch;

        File f = new File("file.txt");

        System.out.println("file size:" + f.length() + " bytes.");
        //read characters from dis into ch. Then write them into bout. 
        //repeat this as long as the read character is not @ 
        while((ch=(char)dis.read())!='@') 
        { 
            bout.write(ch); 
        } 
        //close the file 
        bout.close(); 
    } 
}