import javax.swing.JFrame;

public class Calculator {
	
	public static void main(String args[])
	{
		calculatorGui calcGui = new calcGuiMain();
		calcController calc = new calcController(calcGui);
	}
}

