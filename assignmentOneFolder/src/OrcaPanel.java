import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class OrcaPanel extends JPanel implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    private Orca orca;
    private Fish fish = null;
    private Ocean backgroundObject;
    private Timer t;
    private Dimension size;
    private int fishSpawnCounter = 0;
    private static final int FISH_SPAWN_DELAY = 150;
    private ArrayList<Bubbles> bubbles;
    private int bubblesLength = 3;
    
    public OrcaPanel(Dimension initialSize) {
        super();
        
        bubbles = new ArrayList<Bubbles>();
        
        for(int i = 0; i < bubblesLength; i++) {
        	bubbles.add(new Bubbles(Util.randomInt(30, initialSize.width - 30), initialSize.height, Util.randomInt(2, 6), Util.randomInt(10,50)));
        	System.out.println("new bubble");
        }
        
        size = initialSize;
        backgroundObject = new Ocean(initialSize);
        
        orca = new Orca(
            initialSize.width / 2, 
            initialSize.height / 2,
            Math.min(initialSize.width, initialSize.height) / 6,
            (int) Util.random(-10, 10), 
            (int) Util.random(-10, 10)
        );

        t = new Timer(33, this);
        t.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        size = getSize();
        
        backgroundObject.setSize(size);
        backgroundObject.drawBackground(g);
        
        orca.draw(g);
        
        if(fish != null) {
            fish.draw(g);
        }
        
        for (int i = 0; i < bubblesLength; i++) {
            bubbles.get(i).drawBubble(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        orca.move();
        orca.checkCollision(size);
        
        if (fish != null) {
            fish.move();
            fish.checkCollision(size);
            
            orca.detectFishFunction(fish.pos);
            
            if (orca.detectFish) {
                fish = null;
                orca.detectFish = false; 
                fishSpawnCounter = 0; 
                System.out.println("Fish caught, setting to null");
            }
        } else {
            fishSpawnCounter++;
            
            if (fishSpawnCounter >= FISH_SPAWN_DELAY) {
                spawnFish(size);
                fishSpawnCounter = 0;
            }
        }
        
        for (int i = 0; i < bubbles.size(); i++) {
            Bubbles bubble = bubbles.get(i);
            bubble.moveBubble();
            if (!bubble.isOnScreen(size)) {
                bubbles.remove(i);
                bubbles.add(new Bubbles(Util.randomInt(30, size.width - 30), size.height, Util.randomInt(2, 6), Util.randomInt(10, 30)));
                System.out.println("New bubble");
            }
        }
        
        repaint();
    }
    
    private void spawnFish(Dimension initialSize) {
        if (fish == null) {
            fish = new Fish(
            	(int) Util.random(100, initialSize.width - 100), 
                initialSize.height / 3,
                Math.min(initialSize.width, initialSize.height) / 15,
                (int) Util.random(1, 10), 
                (int) Util.random(0, 0),
                Util.randomColor()
            );
            System.out.println("Spawning fish");
        }
    }
}
