import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	static final String title = "Compact Mind";
	
	PuzzlePanel puzzle;
	SeleniumConnection connection;
	public MainFrame() throws Exception{
		LoadingScreen loading = new LoadingScreen();
		this.add(loading);
		Dimension size = new Dimension(1200,900);
		setSize(size);
		init();
		connection = new SeleniumConnection();
		puzzle = new PuzzlePanel();
		loading.setVisible(false);
		this.remove(loading);
		this.add(puzzle);
		pack();
		repaint();
	}
	private void init(){
		setLayout(new GridLayout(1,1,0,0));
		setTitle(title);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}

	
	public static void main(String[] args) throws Exception{
		MainFrame f = new MainFrame();
	}
}