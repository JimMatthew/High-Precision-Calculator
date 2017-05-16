import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class calcAboutGui extends JFrame
{
	private JTextField txtJamesLindstrom;
	private JTextField txtCalculationMachine;
	private JTextField txtVer;
	private JTextField txtInfinitePrecisionJava;

	public calcAboutGui()
	{
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 250, 203);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtJamesLindstrom = new JTextField();
		txtJamesLindstrom.setBorder(null);
		txtJamesLindstrom.setEditable(false);
		txtJamesLindstrom.setBounds(10, 172, 135, 20);
		txtJamesLindstrom.setText("2017 James Lindstrom");
		panel.add(txtJamesLindstrom);
		txtJamesLindstrom.setColumns(10);
		
		txtCalculationMachine = new JTextField();
		txtCalculationMachine.setBorder(null);
		txtCalculationMachine.setEditable(false);
		txtCalculationMachine.setFont(new Font("Nirmala UI", Font.BOLD, 24));
		txtCalculationMachine.setText("Calculation Machine");
		txtCalculationMachine.setBounds(10, 11, 279, 32);
		panel.add(txtCalculationMachine);
		txtCalculationMachine.setColumns(10);
		
		txtVer = new JTextField();
		txtVer.setEditable(false);
		txtVer.setBorder(null);
		txtVer.setText("version 0.10");
		txtVer.setBounds(165, 172, 86, 20);
		panel.add(txtVer);
		txtVer.setColumns(10);
		
		txtInfinitePrecisionJava = new JTextField();
		txtInfinitePrecisionJava.setBorder(null);
		txtInfinitePrecisionJava.setEditable(false);
		txtInfinitePrecisionJava.setBackground(SystemColor.control);
		txtInfinitePrecisionJava.setText("Infinite precision Java based calculator");
		txtInfinitePrecisionJava.setBounds(35, 54, 230, 20);
		panel.add(txtInfinitePrecisionJava);
		txtInfinitePrecisionJava.setColumns(10);
		
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
		setSize(350, 250);
		setLocationRelativeTo(null);
		setVisible(true);	
		build();
	}
}
