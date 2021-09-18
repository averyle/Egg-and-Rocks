import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Replay implements ActionListener {

	//Add action to perform when "REPLAY" button is clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		EventQueue.invokeLater(() -> {
            JFrame gameFrame = new Snake();
            gameFrame.setVisible(true);
            JButton replay = new JButton("REPLAY");
        	replay.addActionListener(new Replay());
        	gameFrame.add(replay);
        });
	}

}
