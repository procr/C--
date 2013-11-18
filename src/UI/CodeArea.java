package UI;
import javax.swing.*;


public class CodeArea  extends JTextArea{
	
	private JTextField codeField;
	
	
	public CodeArea() {
		init();
	}
	
	private void init() {
		codeField = new JTextField();
		
		this.setTabSize(4);
		//this.setLineWrap(true);
		//this.setWrapStyleWord(true);
		this.setEditable(true);
		
	}
}
