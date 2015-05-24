package interfaces;

import importing.ImportManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.PlanningListener;

public class Planning extends JPanel{
	
	public JButton addMain;
	public JButton addAdvanced;
	public JButton addHowitzer;
	public JButton execute;
	public JLabel money;
	public JLabel player;
	
	public Planning()
	{
		setLayout(null);
		this.setBounds(0, 0, 300, 200);
		
		//current player label
		if(Main.activePlayer == 0)
		{
			player = new JLabel("Player: Black");
		}
		else
		{
			player = new JLabel("Player: White");
		}
		
		player.setBounds(10, 10, 125, 20);
		add(player);
		
		//money label
		money = new JLabel("$ " + Main.players[Main.activePlayer].money);
		money.setBounds(125, 10, 100, 20);
		add(money);
		
		//add main button
		addMain = new JButton(new ImageIcon(ImportManager.mainUnitIcon));
		addMain.setBounds(10, 50, 70, 50);
		addMain.addActionListener(new PlanningListener(PlanningListener.MAIN_BUTTON));
		add(addMain);
		
		//add advanced button
		addAdvanced = new JButton(new ImageIcon(ImportManager.advancedUnitIcon));
		addAdvanced.setBounds(100, 50, 70, 50);
		addAdvanced.addActionListener(new PlanningListener(PlanningListener.ADVANCED_BUTTON));
		add(addAdvanced);
		
		//add howitzer button
		addHowitzer = new JButton(new ImageIcon(ImportManager.howitzerIcon));
		addHowitzer.setBounds(10, 115, 70, 50);
		addHowitzer.addActionListener(new PlanningListener(PlanningListener.HOWITZER_BUTTON));
		add(addHowitzer);
		
		//execute button
		execute = new JButton("Execute");
		execute.setBounds(190, 10, 100, 150);
		execute.addActionListener(new PlanningListener(PlanningListener.EXECUTE_BUTTON));
		add(execute);
		updateValues();
	}
	
	public void updateValues()
	{
		//update labels
		money.setText("$ " + Main.players[Main.activePlayer].money);
		if(Main.activePlayer == 0)
		{
			player.setText("Player: Black");
		}
		else
		{
			player.setText("Player: White");
		}
		//update buttons
		if(Main.players[Main.activePlayer].money < 500)
		{
			addMain.setEnabled(false);
		}
		else
		{
			addMain.setEnabled(true);
		}
		if(Main.players[Main.activePlayer].money < 2500)
		{
			addAdvanced.setEnabled(false);
		}
		else
		{
			addAdvanced.setEnabled(true);
		}
		if(Main.players[Main.activePlayer].money < 4000)
		{
			addHowitzer.setEnabled(false);
		}
		else
		{
			addHowitzer.setEnabled(true);
		}
	}

}
