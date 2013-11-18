package UI;

import java.io.IOException;

public class OpenImg {

	public static void main(String[] args) {
		String fpath="W:\\project_eclipse\\MyLang\\src\\img.jpg";
		try {
			Runtime.getRuntime().exec("rundll32 c:\\Windows\\System32\\shimgvw.dll,ImageView_Fullscreen "+fpath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}