import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private static final int FISH_SPAWN_DELAY = 150; // Number of timer ticks (33 ms each) to reach 5 seconds (5000 ms)
    
    public OrcaPanel(Dimension initialSize) {
        super();
        
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
                orca.detectFish = false; // Reset the detection flag
                fishSpawnCounter = 0; // Reset the counter when the fish is caught
                System.out.println("Fish caught, setting to null");
            }
        } else {
            // Increment the counter if there is no fish
            fishSpawnCounter++;
            // Check if the counter has reached the limit for 5 seconds
            if (fishSpawnCounter >= FISH_SPAWN_DELAY) {
                spawnFish(size);
                fishSpawnCounter = 0; // Reset the counter after spawning the fish
            }
        }
        
        repaint();
    }
    
    private void spawnFish(Dimension initialSize) {
        if (fish == null) {
            fish = new Fish(
                initialSize.width / 3, 
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
