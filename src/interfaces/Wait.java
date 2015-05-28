package interfaces;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;

public class Wait extends JPanel {

	/**
	 * Create the panel.
	 */
	public Wait() {
		setLayout(null);
		this.setBounds(0, 0, 300, 200);
		JLabel lblWait = new JLabel(" WAIT");
		lblWait.setFont(new Font("SansSerif", Font.PLAIN, 91));
		lblWait.setBounds(0, 0, 300, 200);
		add(lblWait);

	}
}
