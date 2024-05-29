import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import processing.core.PVector;

public class Bubbles {
    
    PVector pos;
    int size, speed;
    boolean onScreen = true;
	private Color bubbleColor = new Color(0,160,250, 50);
	private Color bubbleColorOutline = new Color(0,200,255, 150);
    
    public Bubbles(int x, int y, int speed, int size) {
        this.pos = new PVector(x, y);
        this.speed = speed;
        this.size = size;
    }
    
    public void drawBubble(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        
        AffineTransform originalTransform = g2.getTransform();
        g2.translate(this.pos.x, this.pos.y - (this.size * 2));
        
        g2.setColor(bubbleColorOutline);
        g2.drawOval(0, 0, size, size);
        g2.setColor(bubbleColor);
        g2.fillOval(0, 0, size, size);
        
        g2.setTransform(originalTransform);
    }
    
    public void moveBubble() {
        pos.y -= speed;
    }
    
    public boolean isOnScreen(Dimension screenSize) {
        return this.pos.y + this.size > 0;
    }
}
