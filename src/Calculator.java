import javax.swing.JFrame;

public class Calculator {

	
	
	public static void main(String args[])
	{
		calculatorGui calcGui = new calcGuiMain();
		calcController calc = new calcController(calcGui);
		
		/*
		 calcMachine calc = new calcMachine();
		 
		 calc.init(2);
		 calc.addOperation(6);
		 calc.addOperation(3);
		 System.out.println(calc.getTotal());
		 	calcGuiMain frame = new calcGuiMain();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(true);
			frame.setSize(400, 405);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);	
			numberListener = new calcNumberButtonListener();
			Controller controller = new Controller();
			//calcGuiMain.addNumberButtonListener(numberListener);
		 */
	}
	
	

}

