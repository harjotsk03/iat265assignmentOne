import java.awt.Color;
import java.awt.Dimension;
import processing.core.PVector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Orca {
	
	private int size;
	private PVector pos, speed;
	private double scalex = 1;
	private double scaley = 1;
	public boolean detectFish = false;
	
	public Orca(int x, int y, int size, int speedx, int speedy) {
	        pos = new PVector(x, y);
	        this.size = size;
	        this.speed = new PVector(speedx, speedy);
	}

	public void draw(Graphics g) {		
		Graphics2D g2 = (Graphics2D) g;
		
        	AffineTransform old = g2.getTransform();
		
			g2.translate(pos.x,  pos.y);
			g2.rotate(speed.heading());
			g2.setColor(Color.white);
			g2.scale(scalex, scaley);
			g2.drawOval(-size/2, -size/2, size, size);
			g2.fillOval(-size/2, -size/2, size, size);
			
			g2.setColor(Color.black);
			g2.translate(size/2 - size/3, 0);
			g2.drawOval(size/15, size/15, size/12, size/12);
			g2.fillOval(size/15, size/15, size/12, size/12);
			
	        g2.setTransform(old);

	}
	
	public void move() {
		pos.add(speed);
	}
	
	public void checkCollision(Dimension panelSize) {
		if ((pos.x < (size/2) + 20) || (pos.x > panelSize.width - (size/2) - 20)) {
			speed.x *= -1;
		}
		
		if ((pos.y < (size/2) + 20) || (pos.y > panelSize.height - (size/2) - 55)) {
			speed.y *= -1;
		}
	}

	public boolean detectFishFunction(PVector fishPos) {
		PVector direction = PVector.sub(fishPos, pos);
        direction.setMag(10);
        speed = direction.copy();
        
        if (PVector.dist(pos, fishPos) <= size / 2 + 10) {
            detectFish = true;
            speed = new PVector((float)Util.random(-10, 10), (float)Util.random(-10, 10));
        }
		
		return detectFish;
	}
	
}
