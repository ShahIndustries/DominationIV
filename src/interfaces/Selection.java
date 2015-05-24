package interfaces;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import listeners.SelectionListener;

public class Selection extends JPanel {

	/**
	 * Create the panel.
	 */
	public Selection() {
		this.setBounds(0, 0, 300, 200);
		setLayout(null);
		
		JButton btnNewButton = new JButton("Move");
		btnNewButton.setBounds(10, 10, 80, 150);
		btnNewButton.addActionListener(new SelectionListener(SelectionListener.MOVE_BUTTON));
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Attack");
		btnNewButton_1.setBounds(105, 10, 80, 150);
		btnNewButton_1.addActionListener(new SelectionListener(SelectionListener.ATTACK_BUTTON));
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.setBounds(200, 10, 80, 150);
		btnNewButton_2.addActionListener(new SelectionListener(SelectionListener.EXIT_BUTTON));
		add(btnNewButton_2);
	}
}
