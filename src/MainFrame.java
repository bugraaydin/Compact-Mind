import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	static final String title = "Compact Mind";
	
	PuzzlePanel puzzle;
	SeleniumConnection connection;
	public MainFrame() throws Exception{
		init();
		connection = new SeleniumConnection();
		puzzle = new PuzzlePanel();
		this.add(puzzle);
		Dimension size = new Dimension(puzzle.SCREEN_WIDTH,puzzle.SCREEN_HEIGHT);
		setSize(size);
	}
	private void init(){
		setLayout(new GridLayout(1,1,0,0));
		setTitle(title);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}

	
	public static void main(String[] args) throws Exception{
		MainFrame f = new MainFrame();
	}
}