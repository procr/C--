package Parser;
import java.awt.Graphics2D; 
import java.awt.image.BufferedImage; 
import java.io.FileOutputStream; 
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec; 
import com.sun.image.codec.jpeg.JPEGImageEncoder; 


public class JjtImage 
{
	private static BufferedImage bufImage;
	private static Graphics2D g;
	private static int space = 160;
	
	public JjtImage()
	{
		bufImage = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_RGB); 
		g = bufImage.createGraphics();
	}
	
	private void addNodeToImg(Node node, int x, int y)
	{
		String nodeName = node.toString();
		g.drawString(nodeName, x - nodeName.length() / 2 * 5, y);
		int numChildren = node.jjtGetNumChildren();
		if (numChildren == 0)
			return;
		g.drawString("|", x, y += 10);
		
		String line = "";
		for (int i = 0; i < numChildren / 2 * 2; i++)
			line += "----------------------------------------";
		g.drawString(line, x - space * (numChildren / 2), y += 10);
		
		int xOffSet = x - space * (numChildren / 2);
		for (int i = 0; i < numChildren; i++)
		{
			if (numChildren > 1)
			{
				g.drawString("|", xOffSet, y + 10);
				addNodeToImg(node.jjtGetChild(i), xOffSet, y + 20);
			}
			else
				addNodeToImg(node.jjtGetChild(i), xOffSet, y + 10);
			if ((numChildren % 2 == 0) && (i + 1 == numChildren / 2))
				xOffSet += 2 * space;
			else
				xOffSet += space;
		}	
	}
	
	public void genJjtImg(ASTStart n)
	{		
		try
		{
			addNodeToImg(n, 1000, 10);			
			FileOutputStream fos = new FileOutputStream("src\\img.jpg"); 
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos); 
			encoder.encode(bufImage);
			fos.close();
		}
		catch(Exception e)
		{
		} 
	}
	
	
	public static void openImg() {
		String fpath="W:\\project_eclipse\\MyLang\\src\\img.jpg";
		try {
			Runtime.getRuntime().exec("rundll32 c:\\Windows\\System32\\shimgvw.dll,ImageView_Fullscreen "+fpath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}