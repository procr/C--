package UI;
import java.awt.Color;
import java.io.FileOutputStream;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import Parser.ASTStart;
import Parser.Node;

public class SemanticTree extends JPanel{
	private JTree semTree;
	
	//try 
	/*DefaultMutableTreeNode root = new DefaultMutableTreeNode("China");
	DefaultMutableTreeNode zj = new DefaultMutableTreeNode("Zhejiang");
	DefaultMutableTreeNode zs = new DefaultMutableTreeNode("Zhoushan");
	DefaultMutableTreeNode sh = new DefaultMutableTreeNode("Shanghai");
	DefaultMutableTreeNode mh = new DefaultMutableTreeNode("Minhang");*/
	
	public SemanticTree() {
		//init();
		this.setBackground(new Color(255, 255, 255));
	}
	
	public void init() {
		/*zj.add(zs);
		sh.add(mh);
		root.add(zj);
		root.add(sh);
		semTree = new JTree(root);
		semTree.putClientProperty("JTree.lineStyle", "Angled");
		semTree.setShowsRootHandles(true);
		semTree.setRootVisible(true);*/
		
		this.add(semTree);
	}
	
	private void addNodeToTree(Node node, DefaultMutableTreeNode father)
	{
		String nodeName = node.toString();
		int numChildren = node.jjtGetNumChildren();
		for (int i = 0; i < numChildren; i++)
		{
			Node childNode = node.jjtGetChild(i);
			DefaultMutableTreeNode c = new DefaultMutableTreeNode(childNode.toString());
			father.add(c);
			addNodeToTree(childNode, c);
		}
	}
	
	
	public void generateTree(ASTStart root) {
		DefaultMutableTreeNode r = new DefaultMutableTreeNode(root.toString());
		addNodeToTree(root, r);
		semTree = new JTree(r);
		semTree.putClientProperty("JTree.lineStyle", "Angled");
		semTree.setShowsRootHandles(true);
		semTree.setRootVisible(true);
		this.add(semTree);
	}
}
