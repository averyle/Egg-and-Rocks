import java.awt.EventQueue;

import javax.swing.*;

public class Snake extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Snake() {
        
        initializeUI();
    }
    
    private void initializeUI() {
        
        add(new EggsRocks());
               
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
    	JOptionPane.showMessageDialog(null,"The game is about to start");
    	
        EventQueue.invokeLater(() -> {
            JFrame gameFrame = new Snake();
            gameFrame.setVisible(true);
            // Add a replay button to restart the game
            JButton replay = new JButton("REPLAY");
        	replay.addActionListener(new Replay());
        	gameFrame.add(replay);
        });
    }
}