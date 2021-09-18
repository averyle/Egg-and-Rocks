/* Avery Le, Bennett Sanford, Eddi Arenas
 * 12/03/2020
 * Final game: Eggs and Rocks
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;


public class EggsRocks extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

    private final int frameWidth = 250;
    private final int frameHeight = 250;
    private final int dotSize = 10;
    private final int maxDots = 625;
    private final int eggPosition = 24;
    private final int snakeSpeed = 130;

    private final int x[] = new int[maxDots];
    private final int y[] = new int[maxDots];

    private int dots;
    private int egg_x;
    private int egg_y;
    private int rock1_x;
    private int rock1_y;
    private int rock2_x;
    private int rock2_y;
    private int rock3_x;
    private int rock3_y;

    private boolean moveLeft = false;
    private boolean moveRight = true;
    private boolean moveUp = false;
    private boolean moveDown = false;
    private boolean activeGame = true;

    private Timer timer;
    private Image body;
    private Image egg;
    private Image rock;
    private Image head;

    public EggsRocks() {
        initializeBoard();
    }
    
    //Method to create a frame with name, size, background color, and starts the graphics
    private void initializeBoard() {
    	
    	Color darkPurple = new Color(39, 13, 48);
        addKeyListener(new TAdapter());
        setBackground(darkPurple);
        setFocusable(true);

        setPreferredSize(new Dimension(frameHeight, frameWidth));
        images();
        initializeGame();
    }
    // Method to import graphics from machine
    private void images() {

        ImageIcon dotImage = new ImageIcon("C:/Users/ngang/OneDrive/Desktop/dot.png");
        body = dotImage.getImage();

        ImageIcon eggImage = new ImageIcon("C:/Users/ngang/OneDrive/Desktop/egg.png");
        egg = eggImage.getImage();

        ImageIcon headImage = new ImageIcon("C:/Users/ngang/OneDrive/Desktop/head.png");
        head = headImage.getImage();
        
        ImageIcon rockImage = new ImageIcon("C:/Users/ngang/OneDrive/Desktop/rock.png");
        rock = rockImage.getImage();
    }

    //Method to randomly generate the location of the snake, rocks, and eggs
    private void initializeGame() {

        dots = 3;

        for (int i = 0; i < dots; i++) {
            x[i] = 50 - i * 10;
            y[i] = 50;
        }
        
        locateEgg();
        locateRock();

        timer = new Timer(snakeSpeed, this);
        timer.start();
    }

    private void move() {

        for (int i = dots; i > 0; i--) {
            x[i] = x[(i - 1)];
            y[i] = y[(i - 1)];
        }

        if (moveLeft) {
            x[0] -= dotSize;
        }

        if (moveRight) {
            x[0] += dotSize;
        }

        if (moveUp) {
            y[0] -= dotSize;
        }

        if (moveDown) {
            y[0] += dotSize;
        }
    }

    //Method to display score
    private void scoreScreen(Graphics g) {
    	
            String msg = "YOUR SCORE:" + (dots/2-1);
            Font small = new Font("Courier", Font.BOLD, 25);
            FontMetrics metr = getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (frameWidth - metr.stringWidth(msg)) / 2, frameHeight / 2);
      }
    

    //Method places the egg in random positions on the frame
    private void locateEgg() {

        int r = (int) (Math.random() * eggPosition);
        egg_x = ((r * dotSize));

        r = (int) (Math.random() * eggPosition);
        egg_y = ((r * dotSize));
    }
    //Method places rocks at random positions on the frame
    private void locateRock() {

        int r1 = (int) (Math.random() * eggPosition);
        rock1_x = ((r1 * dotSize));

        r1 = (int) (Math.random() * eggPosition);
        rock1_y = ((r1 * dotSize));
        
        int r2 = (int) (Math.random() * eggPosition);
        rock2_x = ((r2 * dotSize));

        r2 = (int) (Math.random() * eggPosition);
        rock2_y = ((r2 * dotSize));
        
        int r3 = (int) (Math.random() * eggPosition);
        rock3_x = ((r3 * dotSize));

        r3 = (int) (Math.random() * eggPosition);
        rock3_y = ((r3 * dotSize));
        
    }
    
    // Method to increment body size by 2 dots every time the snake eats an egg
    private void checkEgg() {

        if ((x[0] == egg_x) && (y[0] == egg_y)) {

            dots = dots + 2;
            locateEgg();
            locateRock();
        }
    }


    // Method to check if the snake touches the border of the frame, touches its body, or touches the rocks
    private void checkCollision() {

        for (int i = dots; i > 0; i--) {

            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                activeGame = false;
            }
        }

        if (y[0] >= frameHeight) {
            activeGame = false;
        }

        if (y[0] < 0) {
            activeGame = false;
        }

        if (x[0] >= frameWidth) {
            activeGame = false;
        }

        if (x[0] < 0) {
            activeGame = false;
        }
    	
    	if ((x[0] == rock1_x) && (y[0] == rock1_y)) {
    		activeGame = false;
    	}
    	
    	if ((x[0] == rock2_x) && (y[0] == rock2_y)) {
    		activeGame = false;
    	}
    	
    	if ((x[0] == rock3_x) && (y[0] == rock3_y)) {
    		activeGame = false;
    	}
    	
        if (!activeGame) {
            timer.stop();
        }
    }
    // Method draws the game as it's happening
    private void printGraphic(Graphics gameGraphics) {
        
        if (activeGame) {

            gameGraphics.drawImage(egg, egg_x, egg_y, this);
            gameGraphics.drawImage(rock,rock1_x, rock1_y, this);
            gameGraphics.drawImage(rock,rock2_x, rock2_y, this);
            gameGraphics.drawImage(rock,rock3_x, rock3_y, this);
            for (int i = 0; i < dots; i++) {
                if (i == 0) {
                    gameGraphics.drawImage(head, x[i], y[i], this);
                } else {
                    gameGraphics.drawImage(body, x[i], y[i], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }  
        else {
        	scoreScreen(gameGraphics);
        }
    }

    @Override
    public void actionPerformed(ActionEvent keyEvent) {

        if (activeGame) {

            checkEgg();
            checkCollision();
            move();
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics gameGraphics) {
        super.paintComponent(gameGraphics);

        printGraphic(gameGraphics);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent keyEvent) {

            int key = keyEvent.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!moveRight)) {
                moveLeft = true;
                moveUp = false;
                moveDown = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!moveLeft)) {
                moveRight = true;
                moveUp = false;
                moveDown = false;
            }

            if ((key == KeyEvent.VK_UP) && (!moveDown)) {
                moveUp = true;
                moveRight = false;
                moveLeft = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!moveUp)) {
                moveDown = true;
                moveRight = false;
                moveLeft = false;
            }
        }
    }
}
