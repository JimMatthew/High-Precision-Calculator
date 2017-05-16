
public interface calculatorController {

	public enum NumberBase {DECIMAL, HEXIDECIMAL, BINARY}
	
	public void numberPressed(String number);
	
	public void operatorPressed(String operator);
	
	public void immediateOperatorPressed(String operator);
	
	public void equalPressed();
	
	public void clearCalculatorPressed();
	
	public void negateInputPressed();
	
	public void changeNumberBase(NumberBase base);
	
	public void enterExpression(String expression);
	
}
