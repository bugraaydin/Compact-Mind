import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PuzzlePanel extends JPanel{
	static final int SCREEN_HEIGHT = 900;
	static final int SCREEN_WIDTH = 1200;
	static final int puzzleSize = 5;
	static final int blankRectPixel = 2;
	static final int blankRect = -1;
	static final int puzzleRect = 0;
	static final int rectWidth = 100;
	//
	private HTMLProcessor input;
	//
	private Control mouse;
	//
	public BufferedImage revealImage;
	public BufferedImage startImage;
	//
	private boolean isRevealed;
	private String[][] puzzle;
	
	public PuzzlePanel() throws Exception{
		isRevealed = false;
		try {
			revealImage = ImageIO.read(getClass().getResourceAsStream("/resources/images/reveal_image.png"));
		}	catch(IOException exc) {
				exc.printStackTrace();
		}
		try {
			startImage = ImageIO.read(getClass().getResourceAsStream("/resources/images/start_image.png"));
		}	catch(IOException exc) {
				exc.printStackTrace();
		}
		puzzle = new String[5][5];
		for(int i = 0;i<puzzleSize;i++){
			for(int j = 0;j<puzzleSize;j++){
				puzzle[i][j] = "";
			}
		}
		mouse = new Control(); // mouse adapter
		addMouseListener(mouse);
		input = new HTMLProcessor(); //HTML saver
		Dimension screen = new Dimension(SCREEN_HEIGHT,SCREEN_WIDTH);
		setVisible(true);
		this.setPreferredSize(screen);
		//
	}
	
	public void paint(Graphics g){
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, 1200, 900);
		g.setColor(Color.black);
		int startX = 45;
		int startY = 40;
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		for(int i = 0; i < puzzleSize;i++){
			for(int j = 0; j < puzzleSize;j++){
				if(input.puzzle[i][j].currentLetter == "-1")
					g.fillRect(startX, startY, rectWidth, rectWidth); // -1 for blank
				else if(input.puzzle[i][j].currentLetter != ""){
					g.drawRect(startX, startY, rectWidth, rectWidth);
					g.drawString(puzzle[i][j],startX+50,startY+60);
				}
				else{
					g.drawRect(startX, startY, rectWidth, rectWidth);
				} 
				if(input.puzzle[i][j].questionNo != ""){
					g.drawRect(startX, startY, rectWidth, rectWidth);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
					g.drawString(input.puzzle[i][j].questionNo, startX + 10, startY + 20);
				}
				startY = startY + rectWidth + blankRectPixel;
			}
			startY = 40;
			startX = startX + rectWidth + blankRectPixel;
		}
		g.drawLine(600, 0, 600, 600);
		//ACROSS HINTS READ FROM HINTS[0] STRING ARRAY AND GUI COMPONENTS//
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		g.drawString("Across", 620, 50);
		g.drawLine(600, 52, 1200, 52);
		int hintY = 70;
		for(int i = 0; i < input.hints[0].size(); i++){
			g.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 
			g.drawString(input.hints[0].get(i), 630, hintY);
			hintY = hintY + 30;
		}
		////////////
		//DOWN HINTS READ FROM HINTS[1] STRING ARRAY AND GUI COMPONENTS//
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		g.drawLine(600, 300, 1200, 300);
		g.drawString("Down", 620, 320);
		g.drawLine(600, 322, 1200, 322);
		hintY = 340;
		for(int i = 0; i < input.hints[1].size(); i++){
			g.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 
			g.drawString(input.hints[1].get(i), 630, hintY);
			hintY = hintY + 30;
		}
		//GRAY RECTANGLE GUI COMPONENTS
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,570,1200,30); 
		g.fillRect(0, 0, 1200, 30);
		g.fillRect(0, 0, 30, 900);
		g.fillRect(1155, 0, 30, 900);
		g.fillRect(0, 825, 1200, 30);
		/////////////////////////////
		g.drawLine(0, 570, 1200, 570);
		g.drawLine(0, 600, 1200, 600);

		g.setColor(Color.BLUE);
		// REVEAL/START IMAGES
		g.drawImage(startImage, 600, 620, this);
		g.drawImage(revealImage, 680, 620, this);
		//
		g.setColor(Color.WHITE);  //PRORAM LOG
		g.fillRect(50, 620, 520, 180);
	}
	public void reveal(){
		if(isRevealed == false){
			System.out.println("Reveal called");
			for(int i = 0; i < puzzleSize;i++)
				for(int j = 0; j < puzzleSize;j++)
					puzzle[i][j] = input.puzzle[i][j].currentLetter;
			isRevealed = true;
			}
		else{
			System.out.println("Unreveal called");
			for(int i = 0; i < puzzleSize;i++)
				for(int j = 0; j < puzzleSize;j++)
					puzzle[i][j] = "";
			isRevealed = false;
		}
		repaint();
		
	}
	public void start(){
		System.out.println("Start called");
	}
	
	//Mouse adapter
	public class Control extends MouseAdapter{
		
		private int mouseX;
		private int mouseY;
		
		public void mousePressed(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY(); 
			if(mouseX >= 600 && mouseY >= 620 && mouseX <= 675 && mouseY <= 685){
				start();
				mouseX = 0;
				mouseY = 0;
			}
			if(mouseX >= 680 && mouseY >= 620 && mouseX <= 755 && mouseY <= 685){
				reveal();
				mouseX = 0;
				mouseY = 0;
			}
		}
		

	}
}
