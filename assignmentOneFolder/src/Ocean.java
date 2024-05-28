import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ocean {
	private Dimension size;
	private Color bgColor = new Color(0,150,187);
    private Color sandColor = new Color(237, 201, 175);
	
	public Ocean ( Dimension size) {
		this.size = size;
	}
	
	public void drawBackground(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);
		g2.drawRect(0, 0, 20, size.height);
		g2.fillRect(0, 0, 20, size.height);
		
		g2.drawRect(0, 0, size.width, 20);
		g2.fillRect(0, 0, size.width, 20);
		
		g2.drawRect(0, size.height - 20, size.width, 20);
		g2.fillRect(0, size.height - 20, size.width, 20);
		
		g2.drawRect(size.width - 20, 0, 20, size.height);
		g2.fillRect(size.width - 20, 0, 20, size.height);
		
		
		g2.setColor(bgColor);
		g2.drawRect(20, 20, size.width-40, size.height-40);
		g2.fillRect(20, 20, size.width-40, size.height-40);
		
		g2.setColor(sandColor);
		g2.drawRect(20, size.height-50, size.width-40, 30);
		g2.fillRect(20, size.height-50, size.width-40, 30);
	}

    public void setSize(Dimension size) {
        this.size = size;
    }
}
