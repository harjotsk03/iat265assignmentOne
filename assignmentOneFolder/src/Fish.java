import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import processing.core.PVector;

public class Fish {

    private int size;
    private PVector speed;
    public PVector pos;
    private Color color;

    public Fish(int x, int y, int size, int speedx, int speedy, Color color) {
        pos = new PVector(x, y);
        this.size = size;
        this.speed = new PVector(speedx, speedy);
        this.color = color;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        AffineTransform old = g2.getTransform();

        g2.translate(pos.x, pos.y);
        g2.rotate(speed.heading());

        g2.setColor(color);
        g2.fillOval(-size / 2, -size / 4, size, size / 2);

        GeneralPath tail = new GeneralPath();
        tail.moveTo(-size / 2, 0);
        tail.lineTo(-size / 2 - size / 4, -size / 4);
        tail.lineTo(-size / 2 - size / 4, size / 4);
        tail.closePath();
        g2.fill(tail);

        // Draw fish eye
        g2.setColor(Color.WHITE);
        g2.fillOval(size / 4, -size / 8, size / 8, size / 8);
        g2.setColor(Color.BLACK);
        g2.fillOval(size / 4 + size / 16, -size / 8 + size / 16, size / 16, size / 16);

        g2.setTransform(old);
    }

    public void move() {
        pos.add(speed);
    }

    public void checkCollision(Dimension panelSize) {
        // Check collision with the left and right walls
        if ((pos.x < (size / 2) + 20) || (pos.x > panelSize.width - (size / 2) - 20)) {
            speed.x *= -1;

            // Adjust position by 20 pixels to avoid the tail swinging out
            if (speed.x > 0) {
                pos.x += 20;
            } else {
                pos.x -= 20;
            }
        }

        // Check collision with the top and bottom walls
        if ((pos.y < (size / 2) + 20) || (pos.y > panelSize.height - (size / 2) - 55)) {
            speed.y *= -1;

            // Adjust position by 20 pixels to avoid the tail swinging out
            if (speed.y > 0) {
                pos.y += 20;
            } else {
                pos.y -= 20;
            }
        }
    }
}