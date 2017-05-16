import java.math.*;
import java.text.DecimalFormat;

public class calcMachine {
	
	BigDecimal value;
	BigDecimal hold;
	BigDecimal test;
	BigDecimal test2;
	
	calcMachine()
	{
		value = new BigDecimal("0");
	}

	calcMachine(String num)
	{
		value = new BigDecimal(num);
	}

	public void init(String num)
	{
		value = new BigDecimal(num);
	}
	
	public void addOperation(String num)
	{
		hold = new BigDecimal(num);
		value = value.add(hold);
	}
	
	public void subtractOperation(String num)
	{
		hold = new BigDecimal(num);
		value = value.subtract(hold);
	}
	
	public void multiplyOperation(String num)
	{
		hold = new BigDecimal(num);
		value = value.multiply(hold);
	}
	
	public void squareOperation()
	{
		value = value.multiply(value);
	}
	
	public void divideOperation(String num)
	{
		hold = new BigDecimal(num);
		value = value.divide(hold,18, RoundingMode.HALF_UP);
	}
	
	public void powerOperation(String num)
	{
		int pow = Integer.parseInt(num);
		value = value.pow(pow);
	}
	
	public void squareRootOperation()
	{
		hold = value;
		value = sqrt(hold, 10);
	}
	public String getTotalString()
	{
		return value.toString();
	}
	
	public String getTotalStringFormatted()
	{
		DecimalFormat f = new DecimalFormat("###,###.#################");
		String string = f.format(value);
		
		System.out.println(String.format("%X", value.toBigInteger()));
		return string;
	}
	
	public void clearOperation()
	{
		value = new BigDecimal("0");
	}
	
	public void factorialOperation()
	{
		hold = value;
		value = fac(value, value);	        
	}

	 public BigDecimal fac(BigDecimal n, BigDecimal acc)
	 {
		      if (n.equals(BigDecimal.ONE))
		      {
		            return acc;
		      }
		        BigDecimal lessOne = n.subtract(BigDecimal.ONE);
		        return fac(lessOne, acc.multiply(lessOne));
	}
	
	public static BigDecimal sqrt(BigDecimal in, int scale){
	    BigDecimal sqrt = new BigDecimal(1);
	    sqrt.setScale(scale + 3, RoundingMode.FLOOR);
	    BigDecimal store = new BigDecimal(in.toString());
	    boolean first = true;
	    do{
	        if (!first){
	            store = new BigDecimal(sqrt.toString());
	        }
	        else first = false;
	        store.setScale(scale + 3, RoundingMode.FLOOR);
	        sqrt = in.divide(store, scale + 3, RoundingMode.FLOOR).add(store).divide(
	                BigDecimal.valueOf(2), scale + 3, RoundingMode.FLOOR);
	    }while (!store.equals(sqrt));
	    return sqrt.setScale(scale, RoundingMode.FLOOR);
	}
}
