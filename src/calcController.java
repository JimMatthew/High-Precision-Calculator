import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.function.Consumer;

public class calcController implements calculatorController{

	calcMachine calcMachine;
	private String input = "";
	private String runningCalc="";
	private String holdOp = "";
	private Boolean operatorDisable;
	private Boolean onEqual = false;
	HashMap<String, Consumer<String>> command;
	HashMap<String, Runnable> runCommand;
	Consumer<String> opConsumer;
	NumberBase numberBase;
	calculatorGui calcGui;
	
	public calcController(calculatorGui calcGui)
	{
		this.calcGui = calcGui;
		this.calcMachine = new calcMachine();
		createOperationCommandMap();
		operatorDisable = true;
		numberBase = NumberBase.DECIMAL;
		calcGui.setCalculatorController(this);
	}
	
	//Hash set of all operations and methods they will call
	//Note: will throw ArithmeticException if invalid data is used
	private void createOperationCommandMap()
	{
		command = new HashMap<>();
		runCommand = new HashMap<>();
		command.put("+", calcMachine :: addOperation);
		command.put("-", calcMachine :: subtractOperation);
		command.put("x", calcMachine :: multiplyOperation);
		command.put("*", calcMachine :: multiplyOperation);
		command.put("^", calcMachine :: powerOperation);
		command.put("", (op) -> {if ((op != "")) {calcMachine.addOperation(op);}
			else{calcMachine.addOperation("0"); }	});
		command.put("/",(op) -> {if (!input.equals("0")) {calcMachine.divideOperation(input);}
			else {throw new ArithmeticException("ERROR: Cannot Divide by 0"); }});
		runCommand.put("^2", calcMachine :: squareOperation);
		runCommand.put("\u221A", () -> {if (!(Double.parseDouble(calcMachine.getTotalString()) < 0)) {calcMachine.squareRootOperation();}
		else {throw new ArithmeticException("Error: Cannot Square Root Negative");  }});
		runCommand.put("!", calcMachine :: factorialOperation);
	}
	
	private void doOperation(String operation)
	{
		if (numberBase.equals(NumberBase.HEXIDECIMAL) && !(input.equals("")))
		{
			input = hexToDec(input);
		}
		else if (numberBase.equals(NumberBase.BINARY) && !(input.equals("")))
		{
			input = binaryToDec(input);
		}
		if(command.containsKey(operation))
		{
			command.get(operation).accept(input);
		}
		else if(runCommand.containsKey(operation))
		{
			runCommand.get(operation).run();
		}
	}
	
	@Override
	public void numberPressed(String num)
	{
		if (!(num.equals(".") && input.contains(".")))
		{	
			operatorDisable = false;
			input = input + num;
			runningCalc = runningCalc + num;
			updateTotalDisplayWithInput();
		}		
	}
	
	@Override
	public void operatorPressed(String operation)
	{
		 if(!operatorDisable) 
		 {
			 operatorDisable = true;
			 onEqual = false;
			 runningCalc = runningCalc + operation;
			 try {
				 doOperation(holdOp);
			 } catch(ArithmeticException ex) {
				 errorRecovery("ERROR");
				 return;
			 }
			 updateTotalDisplayWithResult();
			 holdOp = operation;	
			 input = "";
		 }
	}
	
	@Override
	public void equalPressed()
	{
		if (!onEqual)
		{
			 try {
				 doOperation(holdOp);
			 } catch(ArithmeticException ex) {
				 errorRecovery(ex.getMessage());
				 return;
			 }
			holdOp = "";
			input = "";
			onEqual = true;
			updateTotalDisplayWithResult();
		}
		operatorDisable = false;
	}
	
	@Override
	public void negateInputPressed()
	{
		 if (!input.equals("")) //only negate if there is number in input
		 {
			 input = "-"+input;
			 runningCalc = new StringBuilder(runningCalc).insert(runningCalc.length() - input.length()+1, "-").toString();
			 updateTotalDisplayWithInput();
		 }
	}
	
	@Override
	public void clearCalculatorPressed()
	{
		calcMachine.clearOperation();
		holdOp = "";
		runningCalc = "";
		input = "";
		operatorDisable = true;
		onEqual = false;
		updateTotalDisplayWithInput();
		opConsumer = null;
	}
	
	@Override
	public void immediateOperatorPressed(String operation)
	{
		if(!operatorDisable)
		{
			 if (!(input.equals(""))) //If there is an input, calculate before new operation
			 {
				 try {
					 doOperation(holdOp);
				 } catch(ArithmeticException ex) {
					 errorRecovery(ex.getMessage());
					 return;
				 }
			 }
			 try {
				 doOperation(operation);
			 } catch(ArithmeticException ex) {
				 errorRecovery(ex.getMessage());
				 return;
			 }
			 holdOp = "";
			 input = "";
			 runningCalc = runningCalc + operation;
			 updateTotalDisplayWithResult();
		}
	}
	
	@Override
	public void changeNumberBase(NumberBase base) 
	{
		NumberBase oldBase = numberBase;
		numberBase = base;
		if (!(input.equals(""))) 
		{
			if (oldBase.equals(NumberBase.HEXIDECIMAL))
			{
				input = hexToDec(input);
			}
			else if (oldBase.equals(NumberBase.BINARY))
			{
				input = binaryToDec(input);
			}
			if (base.equals(NumberBase.HEXIDECIMAL))
			{
				input = String.format("%X", new BigInteger(input));
			}
			else if (base.equals(NumberBase.DECIMAL))
			{
				//drink a beer
			}
			else if (base.equals(NumberBase.BINARY))
			{
				input = new BigInteger(input).toString(2);
			}
			updateTotalDisplayWithInput();
		}
		else
		{
			updateTotalDisplayWithResult();
		}
	}

	@Override
	public void enterExpression(String expression)
	{
		
	}
	
	private void updateTotalDisplayWithInput()
	{
		calcGui.setRunningCalcDisplay(runningCalc);
		if (numberBase.equals(NumberBase.DECIMAL))
		{
			if (!input.equals(""))
			{
				calcGui.setCurrentTotalDisplay(new DecimalFormat("#,###.##").format(Double.parseDouble(input)));
			}
			else
				calcGui.setCurrentTotalDisplay(input);
		}
		else if (numberBase.equals(NumberBase.HEXIDECIMAL))
		{
			calcGui.setCurrentTotalDisplay(formatHexString(input));
		}
		else if (numberBase.equals(NumberBase.BINARY))
		{
			calcGui.setCurrentTotalDisplay(formatHexString(input));
		}
	}
	
	private void updateTotalDisplayWithResult()
	{
		if (numberBase.equals(NumberBase.DECIMAL))
		{
			setTotalDisplay(calcMachine.getTotalStringFormatted());
		}
		else if (numberBase.equals(NumberBase.HEXIDECIMAL))
		{
			calcGui.setCurrentTotalDisplay(formatHexString(decToHex(calcMachine.getTotalString())));
		}
		else if (numberBase.equals(NumberBase.BINARY))
		{
			calcGui.setCurrentTotalDisplay(formatHexString(decToBinary(calcMachine.getTotalString())));
		}
		calcGui.setRunningCalcDisplay(runningCalc);
	}
	
	private String hexToDec(String hex)
	{
		return new BigInteger(hex, 16).toString();
	}
	
	private String decToHex(String dec)
	{
		return String.format("%X",new BigDecimal(dec).toBigInteger());
	}
	
	private String decToBinary(String dec)
	{
		return new BigDecimal(dec).toBigInteger().toString(2);
	}
	
	private String binaryToDec(String bin)
	{
		return new BigInteger(bin, 2).toString();
	}
	
	private String formatHexString(String hexString)
	{
		StringBuilder string = new StringBuilder(hexString);
		int idx = string.length() - 4;
		while (idx > 0)
		{
		    string.insert(idx, " ");
		    idx = idx - 4;
		}
		return string.toString();
	}
	
	private void setTotalDisplay(String value)
	{
		calcGui.setCurrentTotalDisplay(value);
	}
	
	private void errorRecovery(String error)
	{
		clearCalculatorPressed();
		setTotalDisplay(error);
	}
}
