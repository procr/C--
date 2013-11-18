package Parser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class MyLang 
{
	public static void main(String args[]) throws ParseException, FileNotFoundException 
	{
		File f = new File("src\\input.txt");  
        InputStream in = new FileInputStream(f);  
	    Eg2 t = new Eg2(in);
	    try {
	        ASTStart n = t.Start();
	        JjtImage jjimg = new JjtImage();
	        jjimg.genJjtImg(n);
	        n.dump("");
	        //System.out.println(t.allTokens);
	        System.out.println("Thank you.");
	      } catch (Exception e) {
	        System.out.println("Oops.");
	        System.out.println(e.getMessage());
	        e.printStackTrace();
	      }
	}
}
