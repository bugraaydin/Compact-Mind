import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LoadingScreen extends JPanel{
	static final int SCREEN_HEIGHT = 900;
	static final int SCREEN_WIDTH = 1200;
	BufferedImage loadingImage;
	Image loadingCircle;
	public LoadingScreen(){
		try {
			loadingImage = ImageIO.read(getClass().getResourceAsStream("/resources/images/loading.png"));
		}	catch(IOException exc) {
				exc.printStackTrace();
		}
		loadingCircle = new ImageIcon(getClass().getResource("/resources/images/loading_circle.gif")).getImage();
		Dimension screen = new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT);
		setVisible(true);
		this.setPreferredSize(screen);
	}
	public void paint(Graphics g){
		g.drawImage(loadingImage, 0, 0, this);
		g.drawImage(loadingCircle, 562, 580, this);
	}

}
