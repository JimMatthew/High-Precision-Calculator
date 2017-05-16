import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class calcOptionsGui extends JFrame {
	private JTextField txtCalculationMachine;

	public calcOptionsGui()
	{
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 311, 249);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtCalculationMachine = new JTextField();
		txtCalculationMachine.setBorder(null);
		txtCalculationMachine.setEditable(false);
		txtCalculationMachine.setFont(new Font("Nirmala UI", Font.BOLD, 16));
		txtCalculationMachine.setText("Options");
		txtCalculationMachine.setBounds(10, 11, 171, 20);
		panel.add(txtCalculationMachine);
		txtCalculationMachine.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(311, 0, 131, 249);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
	}
	
	private void build()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 1, 0, 0));
		getContentPane().add(mainPanel);
	}
	
	public void enableDisplay()
	{    	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setVisible(true);	
	}
}
