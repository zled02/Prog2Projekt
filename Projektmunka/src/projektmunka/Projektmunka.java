////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
//////////////////////////MAIN_FILE/////////////////////////////
/////////////////////PROG_2 PROJEKTMUNKA////////////////////////
/////////////////////Szücs Tamás MI BSC/////////////////////////
////////////////////////2021.11.30./////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////


package projektmunka;
import java.io.*;

public class Projektmunka {

  public static void main(String[] args) throws IOException {
    try 
        {
            String FilePath = String.valueOf(args[0]);
            String MainPagePath = String.valueOf(args[0]);
            Bekeres(FilePath, MainPagePath);
            Generator.IndexHTML(FilePath, MainPagePath);
        }   catch (java.lang.ArrayIndexOutOfBoundsException e) 
        {
            System.out.println("|---------------------------|");
            System.out.println("|Adjon meg egy eleresi utat|");
            System.out.println("|                          |");
            System.out.println("-----------------------------");
            
    //////////////////////////////////////////////////////////
    //String EleresiUt = "D://Egyetem//prog2projekt//kepek";
    //String MainPagePath = "D://Egyetem//prog2projekt//kepek";
    //////////////////////////////////////////////////////////
    
        }
  }
  //////////////
  ///mainVege///
  //////////////
  
  
  /////////////////////////////
  ///bekeres utvonal alapjan///
  /////////////////////////////
  public static void Bekeres(String Location, String MainPagePath) throws IOException {
    
      if (!(Location.endsWith("/") || Location.endsWith("'\'")))
        {
           Location = Location + "/";
        }
    
    if (!(MainPagePath.endsWith("/") || MainPagePath.endsWith("'\'"))) 
        {
            MainPagePath = MainPagePath + "/";
        }
    
    try {
        
      File path = new File(Location);
      String[] tarolo = path.list();
      
      for (int i = 0; i < tarolo.length; i++)
      {
        File filename = new File(tarolo[i]);
        
        if (Generator.Directory(tarolo[i])) 
         {
            Location = Location + filename + "/";
            Generator.PictureHTML(Location, tarolo[i], MainPagePath);
            System.out.println("//////" + Location);
            Bekeres(Location, MainPagePath);
            Location = Generator.PathDeleteLast(Location);
         }
        
        if (Generator.PictureType(tarolo[i])) 
         {
            Location = Location + filename + "/";
            Generator.PictureHTML(Location, tarolo[i], MainPagePath);
            Location = Generator.PathDeleteLast(Location);
         }
      }
      
    } catch (java.lang.NullPointerException e) {
      System.out.println("|----------------------------|");
      System.out.println("| Nem jó az elérési útvonal! |");
      System.out.println("|                            |");
      System.out.println("------------------------------");
    }
    
  }
  //////////////////
  ///bekeres vege///
  //////////////////
  
}
