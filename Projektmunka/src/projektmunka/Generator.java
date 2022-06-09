////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
/////////////////////PROG_2 PROJEKTMUNKA////////////////////////
/////////////////////Szücs Tamás MI BSC/////////////////////////
////////////////////////2021.11.30./////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////

package projektmunka;
import java.io.*;
import java.util.*;

public class Generator {
    
    
    //////////////////////////////////////////////////////
    ///Fugvenyek és eljárások a generator felepitesehez///
    //////////////////////////////////////////////////////
   
    
       public static boolean PictureType(String FileName) {
        boolean result = false;
        if (FileName.contains(".jpeg"))
            result = true;
        if (FileName.contains(".jpg"))
            result = true;
        if (FileName.contains(".png"))
            result = true;
        if (FileName.contains(".gif"))
            result = true;
        if (FileName.contains(".jfif"))
            result = true;
        return result;
    }
    ///Meghatarozzuk a kép tipusokat amiket a program fel fog ismerni///
    ///////////////////////////////////////////////////////////////////
       
       
    public static String PathDeleteLast(String FilePath) {
        String[] temp = FilePath.split("/");
        temp[temp.length - 1] = "";
        FilePath = "";
        for (int i = 0; i < temp.length - 1; i++) {
            FilePath = FilePath + temp[i] + "/";
        }
        return FilePath;
    }
///kitoroljuk az utolso "/" jelet///
////////////////////////////////////
    
   
     public static String FileNameNoExt(String FileName) {
        int charAtDot = FileName.indexOf(".");
        String FileNameNoExt = FileName.substring(0, charAtDot);
        return FileNameNoExt;
    }
///Meghatarozzuk a file nevet a kiterjesztese nelkul///
//////////////////////////////////////////////////////  
     
     
     
 public static String getLastDir(String FilePath) {
        String[] help = FilePath.split("/");
        return help[help.length - 1];
    }
///Megkapjuk az utolso mappat///
//////////////////////////////// 
   
    
    
    public static String PathDeleteLastChar(String FilePath) {
        StringBuilder sb = new StringBuilder(FilePath);
        FilePath = String.valueOf(sb.deleteCharAt(sb.length() - 1));
        return FilePath;
    }
///Kitoroljuk az utolso karakter az eleresi utbol///
////////////////////////////////////////////////////
    
     
    public static boolean Directory(String FileName) {
        boolean result = false;
        if (!(FileName.contains(".")))
            result = true;
        return result;
    }
    ///Mappa vagy sem meghatarozasa///
    //////////////////////////////////
    
    
    public static int FirstIndexOfPicture(List<String> pictures) {
        int count = 0;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < pictures.size(); i++) {
            if (PictureType(pictures.get(i)) || Directory(pictures.get(i))) {
                list.add(pictures.get(i));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (PictureType(list.get(i)))
                count++;
        }
        return list.size() - count;
    }
    
    
    
    
    ////////////////////////////////////
    ///A Fugevenyek és eljárások vége///
    ////////////////////////////////////
    
    
    
    /////////////////////////////
    ///A HTML-Genrálás Kezdete///
    /////////////////////////////
 
    ///1: A képek HTML-jeinek legenerálása///
    public static void PictureHTML(String FilePath, String FileName, String MainPage)
            throws IOException { 
         /*
          * A PictureHTML eldönti, hogy egy adott file az egy libary vagy csak egy kép,
          * és az alapján legenerálja a html File-ját
         */
        
        if (!(FilePath.endsWith("/") || FilePath.endsWith("'\'"))) 
        {
            FilePath = FilePath + "/";
        }

        if (!(MainPage.endsWith("/") || MainPage.endsWith("'\'"))) 
        {
            MainPage = MainPage + "/";
        }

        File fullfile = new File((PathDeleteLast(FilePath)));
        String[] fulllist = fullfile.list();
        List<String> list = new ArrayList<>();
        List<String> picture = new ArrayList<>();
        
        for (int i = 0; i < fulllist.length; i++) 
        {
            if (PictureType(fulllist[i]) || Directory(fulllist[i])) 
            {
                list.add(fulllist[i]);
            }
        }

        if (PictureType(FileName)) {

            for (int i = 0; i < list.size(); i++) 
            {
                if (PictureType(list.get(i)))
                    picture.add(list.get(i));
            }
            
            int index = picture.indexOf(FileName);
            File file = new File(String.valueOf(PathDeleteLast(FilePath)) + (FileNameNoExt(FileName) + ".html"));
            BufferedWriter filewritter = new BufferedWriter(new FileWriter(file));

            filewritter.write("<html><body><h1><a href=" + (MainPage) + getLastDir(MainPage) + ".html" + ">Start Page</a></h1>");
            if (PathDeleteLast(FilePath).equals(MainPage)) 
            {
                filewritter.write("<h2><a href=" + (MainPage) + getLastDir(MainPage) + ".html" + ">^^^^  " + "</a></h2>");
            } 
            else
                filewritter.write("<h2><a href=" + PathDeleteLastChar(PathDeleteLast(FilePath)) + ".html" + ">^^^^  "
                        + "</a></h2>");
            
            if (index == 0) 
            {
                filewritter.write("<<a href=" + PathDeleteLast(FilePath) + FileNameNoExt(picture.get(picture.indexOf(FileName)))
                        + ".html" + "><<</a><h1 style=" + "display:inline;" + "> " + picture.get(index) + "</h1><a href="
                        + PathDeleteLast(FilePath) + FileNameNoExt(picture.get(picture.indexOf(FileName) + 1)) + ".html"
                        + ">>></a>>");
                filewritter.write("<h1>" + "</h1>");
                filewritter.write("<h1>" + "</h1>");
                filewritter.write("<a href=" + PathDeleteLast(FilePath) + FileNameNoExt(picture.get(picture.indexOf(FileName) + 1))
                        + ".html" + "><img src=" + PathDeleteLast(FilePath) + FileName + " alt="
                        + FileNameNoExt(FileName) + "width='200' height='240'></a>");
            }

            if (index > 0 && index < picture.size() - 1) 
            {
                filewritter.write("<<a href=" + PathDeleteLast(FilePath) + FileNameNoExt(picture.get((picture.indexOf(FileName)) - 1))
                        + ".html" + "><<</a><h1 style=" + "display:inline;" + "> " + picture.get(picture.indexOf(FileName))
                        + "</h1><a href=" + PathDeleteLast(FilePath)
                        + FileNameNoExt(picture.get(picture.indexOf(FileName) + 1)) + ".html" + ">>></a>>");
                filewritter.write("<h1>" + "</h1>");
                filewritter.write("<h1>" + "</h1>");
                filewritter.write("<a href=" + PathDeleteLast(FilePath) + FileNameNoExt(picture.get(picture.indexOf(FileName) + 1))
                        + ".html" + "><img src=" + PathDeleteLast(FilePath) + FileName + " alt="
                        + FileNameNoExt(FileName) + "width='200' height='240'></a>");

            }

            if (index == picture.size() - 1) 
            {
                filewritter.write("<<a href=" + PathDeleteLast(FilePath) + FileNameNoExt(picture.get(picture.indexOf(FileName) - 1))
                        + ".html" + "><<</a><h1 style=" + "display:inline;" + "> " + picture.get(index) + "</h1><a href="
                        + PathDeleteLast(FilePath) + FileNameNoExt(picture.get(picture.indexOf(FileName))) + ".html"
                        + ">>></a>>");
                filewritter.write("<h1>" + "</h1>");
                filewritter.write("<h1>" + "</h1>");
                filewritter.write("<a href=" + PathDeleteLast(FilePath) + FileNameNoExt(picture.get(picture.indexOf(FileName)))
                        + ".html" + "><img src=" + PathDeleteLast(FilePath) + FileName + " alt="
                        + FileNameNoExt(FileName) + "width='200' height='240'></a>");
            }

            filewritter.write("<h1>" + "</h1>");
            filewritter.write("</body></html>");
            filewritter.close();
        }

        if (Directory(FileName)) 
        {
            File fileDir = new File((PathDeleteLast(FilePath) + FileName));
            String[] dirList = fileDir.list();
            File file = new File((PathDeleteLast(FilePath)) + FileName + ".html");
            BufferedWriter filewritter = new BufferedWriter(new FileWriter(file));
            
            filewritter.write("<!DOCTYPE html><html lang=" + "hu" + " dir=" + "ltr" + "><head><meta charset=" + "utf-8"
                    + "><title>" + String.valueOf(FileName) + "</title></head><body>");
            filewritter.write("<html><body><h1><a href=" + (MainPage) + getLastDir(MainPage) + ".html" + ">Start Page</a></h1>");
            
            if (PathDeleteLast(FilePath).equals(MainPage)) 
            {
                filewritter.write("<h2><a href=" + (MainPage) + getLastDir(MainPage) + ".html" + ">^^^^  " + "</a></h2>");
            } 
            else
                filewritter.write("<h2><a href=" + PathDeleteLastChar(PathDeleteLast(FilePath)) + ".html" + ">^^^^  "
                        + "</a></h2>");
            filewritter.write("<hr><h1>Directories:</h1>");

            for (int i = 0; i < dirList.length; i++) 
            {
                if (Directory(dirList[i])) 
                {
                    filewritter.write("<li><a href=" + FilePath + (dirList[i]) + ".html" + "> " + (dirList[i]) + "</a></li>");
                }
            }
            
            filewritter.write("<hr><h1>Pictures:</h1>");
            
            for (int i = 0; i < dirList.length; i++) 
            {
                if (PictureType(dirList[i])) 
                {
                    int charAtDot = dirList[i].indexOf(".");
                    String FileNameNoExt = dirList[i].substring(0, charAtDot);
                    filewritter.write("<li><a href=" + FilePath + (FileNameNoExt + ".html") + "> " + (dirList[i]) + "</a></li>");
                }
            }
            
            filewritter.write("</body></html>");
            filewritter.close();
        }

    }

    
    
    
    ///Itt keszul el az index HTML file///
    //////////////////////////////////////
    public static void IndexHTML(String FilePath, String MainPage) throws IOException {
        if (!(FilePath.endsWith("/") || FilePath.endsWith("'\'"))) 
        {
            FilePath = FilePath + "/";
        }
        
        if (!(MainPage.endsWith("/") || MainPage.endsWith("'\'"))) 
        {
            MainPage = MainPage + "/";
        }
        
        File fileDir = new File(MainPage);
        String[] dirlist = fileDir.list();
        File file = new File((MainPage) + getLastDir(MainPage) + ".html");
        BufferedWriter filewritter = new BufferedWriter(new FileWriter(file));
        
        filewritter.write("<!DOCTYPE html><html lang=" + "hu" + " dir=" + "ltr" + "><head><meta charset=" + "utf-8" + "><title>"
                + "MainPage" + "</title></head><body>");
        filewritter.write("<h1><a href=" + (MainPage) + getLastDir(MainPage) + ".html" + ">Start Page   " + "</a></h1>");
        filewritter.write("<h2><a href=" + (MainPage) + getLastDir(MainPage) + ".html" + ">^^^^</a></h2>");
        filewritter.write("<hr>");
        filewritter.write("<hr><h1>Directories:</h1>");

        for (int i = 0; i < dirlist.length; i++) {
            if (Directory(dirlist[i])) {
                filewritter.write("<li><a href=" + FilePath + (dirlist[i]) + ".html" + "> " + (dirlist[i]) + "</a></li>");
            }
        }
        filewritter.write("<hr><h1>Pictures:</h1>");
        for (int i = 0; i < dirlist.length; i++) {
            if (PictureType(dirlist[i])) {
                int charAtDot = dirlist[i].indexOf(".");
                String FileNameNoExt = dirlist[i].substring(0, charAtDot);
                filewritter.write("<li><a href=" + FilePath + (FileNameNoExt + ".html") + "> " + (dirlist[i]) + "</a></li>");
            }
        }
        filewritter.write("</body></html>");
        filewritter.close();
    }
}
