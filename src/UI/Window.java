package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.*;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.filechooser.FileNameExtensionFilter;

import Parser.ASTStart;
import Parser.Eg2;
import Parser.JjtImage;
import Parser.ParseException;

public class Window extends JFrame{
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private CodeArea codeArea;
	private File file;
	private final JTabbedPane tabPane = new JTabbedPane();
	//private Eg2 parser;
	
	class OpenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("My Language", "txt"));
			int retVal = fc.showOpenDialog(getContentPane());
			if (retVal == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				if (file.getName().endsWith(".txt")) {
					try {
						InputStream in = new FileInputStream(file);
						codeArea.read(new InputStreamReader(in), null);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//codeArea.setText(file.toString());
				}
				else {
					System.out.print("Open: Can not this file\t" + file.getAbsolutePath());
				}
			}
		}
		
	}
	
	class SaveListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if ((file == null) || (!file.exists())) {
				SaveAsListener saveAs = new SaveAsListener();
				saveAs.actionPerformed(e);
			}
			else {
				String content = codeArea.getText();
				try {
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(content);
					bw.close();
					System.out.print("Svae: " + file.getAbsolutePath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
		
	}
	
	class SaveAsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("My Language", "txt"));
			int retVal = fc.showSaveDialog(getContentPane());
			if (retVal == JFileChooser.APPROVE_OPTION) {
				try {
					File newFile = new File(fc.getSelectedFile() + ".txt");
					if (!newFile.exists())
						newFile.createNewFile();
					FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(codeArea.getText());
					bw.close();
					System.out.print("Svae As: " + newFile.getAbsolutePath());
				} catch(IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	
	/*class CompileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}*/
	
	class LexAnalyze implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub	
			try {
				CodeArea lexArea = new CodeArea();
				InputStream in = new ByteArrayInputStream(codeArea.getText().getBytes());
				Eg2 parser = new Eg2(in);
				ASTStart n = parser.Start();
				lexArea.setText(parser.allTokens);
				lexArea.setEditable(false);
				JScrollPane codeScroll = new JScrollPane(lexArea);
				codeScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				codeScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				tabPane.add(codeScroll);
				int i = tabPane.getTabCount();
		        ButtonTabComponent btc = new ButtonTabComponent(tabPane);
		        btc.setLabel("Lexical Analyze");
		        tabPane.setTabComponentAt(i - 1, btc);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	        
		}
		
	}
	
	class SemAnalyze implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				InputStream in = new ByteArrayInputStream(codeArea.getText().getBytes());
				Eg2 parser = new Eg2(in);
				ASTStart n = parser.Start();
				SemanticTree semArea = new SemanticTree();
				semArea.generateTree(n);
				JjtImage jjimg = new JjtImage();
		        jjimg.genJjtImg(n);
		        jjimg.openImg();
		        n.dump("");
		        
		        
				JScrollPane scrollPane = new JScrollPane(semArea);
				scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				tabPane.add(scrollPane);
				int i = tabPane.getTabCount();
		        ButtonTabComponent btc = new ButtonTabComponent(tabPane);
		        btc.setLabel("Semantic Analyze");
		        tabPane.setTabComponentAt(i - 1, btc);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
	}
	
	public Window(){
		init();
	}
	
	private void init(){
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);
		menuBar.setAlignmentY(menuBar.TOP_ALIGNMENT);
		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(new OpenListener());
		menu.add(menuItem);
		menuItem = new JMenuItem("Save");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem.addActionListener(new SaveListener());
		menu.add(menuItem);
		menuItem = new JMenuItem("Save As");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		menuItem.addActionListener(new SaveAsListener());
		menu.add(menuItem);
		/*menuItem = new JMenuItem("Compile");
		menuItem.addActionListener(new CompileListener());
		menu.add(menuItem);*/
		menu = new JMenu("Option");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);
		menuItem = new JMenuItem("Lexical Analyze");
		menuItem.addActionListener(new LexAnalyze());
		menu.add(menuItem);
		menuItem = new JMenuItem("Semantic Analyze");
		menuItem.addActionListener(new SemAnalyze());
		menu.add(menuItem);
		
		//init codeArea tab
		codeArea = new CodeArea();
		JScrollPane codeScroll = new JScrollPane(codeArea);
		codeScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		codeScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        tabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabPane.addTab("Source Code", codeScroll);
        
		
		
		
		setTitle("My Language");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menuBar);
		add(tabPane);
		//getContentPane().add(codeScrollV);
		//getContentPane().add(codeScrollH);
		//JLabel label = new JLabel("Source Code");
		//frame.add(label);
		setSize(800,600);
	}
}
