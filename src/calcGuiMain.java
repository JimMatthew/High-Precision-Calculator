import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

public class calcGuiMain extends JFrame implements calculatorGui{

	private calcJButton numberButtonArray[] = new calcJButton[10];
	private JTextField currentAnswerDisplay;
	private JTextField runningCalculationDisplay;
	private calcJButton opSubtractionButton;
	private calcJButton opMultiplicationButton;
	private calcJButton opEqualsButton;
	private calcJButton opAdditionButton;
	private calcJButton decimalButton;
	private calcJButton clearDisplayButton;
	private calcJButton opSquaredButton;
	private calcJButton opPowerButton;
	private calcJButton opSquareRootButton;
	private calcJButton opNegateButton;
	private calcJButton opFactorialButton;
	private calcJButton opDivisionButton;
	private calcJButton numberHexA;
	private calcJButton numberHexB;
	private calcJButton numberHexC;
	private calcJButton numberHexD;
	private calcJButton numberHexE;
	private calcJButton numberHexF;
	JRadioButton baseSelectorDecimal;
	JRadioButton baseSelectorHexidecimal;
	JRadioButton baseSelectorBinary;
	JPanel numberButtonPanel;
	private static calcAboutGui aboutGui;
	private static calcOptionsGui optionsGui;
	JMenuBar menuBar;
	JMenu menu, submenu, helpMenu;
	JMenuItem menuItem;
	JMenuItem helpAboutItem;
	calculatorController controller;
	Boolean isHex = false;
	JPanel buttonPanel;
	private JLabel lblNewLabel_1;
	
	public calcGuiMain()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.add(makeDisplayPanel());
		mainPanel.add(makeButtonPanel());
		setForeground(SystemColor.window);
		this.setTitle("Calculator");
		getContentPane().setLayout(new GridLayout(1,1,0,0));
		getContentPane().add(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(524, 494);
		setLocationRelativeTo(null);
		setVisible(true);	         
		menuBar = new JMenuBar();
		menu = new JMenu("Options");
		helpMenu = new JMenu("Help");
		menuBar.add(menu);
		menuBar.add(helpMenu);
		menuItem = new JMenuItem("Calc Options");
		helpAboutItem = new JMenuItem("About");
		aboutGui = new calcAboutGui();
		optionsGui = new calcOptionsGui();
		helpAboutItem.addActionListener(event -> aboutGui.enableDisplay());
		menuItem.addActionListener(event -> optionsGui.enableDisplay());
		menu.add(menuItem);
		helpMenu.add(helpAboutItem);
		this.setJMenuBar(menuBar);
		setupKeyBoardListener();
	}
	
	public void setCalculatorController(calculatorController controller)
	{
		this.controller = controller;
	}
	
	private JPanel makeButtonPanel()
	{
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 219, 518, 252);
		buttonPanel.setBackground(SystemColor.controlHighlight);
		buttonPanel.setLayout(null);
		buttonPanel.add(makeNumberButtonPanel());
		buttonPanel.add(makeBasicOperatorPanel());
		return buttonPanel;
	}
	
	private void setupKeyBoardListener()
	{
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
		  .addKeyEventDispatcher(new KeyEventDispatcher() {
		      @Override
		      public boolean dispatchKeyEvent(KeyEvent e) {
		        if (KeyEvent.KEY_PRESSED == e.getID())
		        {
		        	if (isNumeric( String.valueOf(e.getKeyChar())))
			   		 {
			   			 controller.numberPressed(String.valueOf(e.getKeyChar()));	
			   		 }
			   		else if( String.valueOf(e.getKeyChar()).equals("+") || String.valueOf(e.getKeyChar()).equals("-") 
			   				|| String.valueOf(e.getKeyChar()).equals("*") ||String.valueOf(e.getKeyChar()).equals("/"))
			   		{
			   		    controller.operatorPressed(String.valueOf(e.getKeyChar()));
			   		}
			   		else if( String.valueOf(e.getKeyChar()).equals("="))
			   		{
			   			controller.equalPressed();
			   		}    
		        }
		        return false;
		      }
		});
	}
	private JPanel makeNumberButtonPanel()
	{
		numberButtonPanel = new JPanel();
		numberButtonPanel.setBackground(SystemColor.controlHighlight);
		numberButtonPanel.setBorder(null);
		numberButtonPanel.setBounds(10, 23, 295, 175);
		numberButtonPanel.setLayout(new GridLayout(4, 3, 0, 0));
		numberHexA = new calcJButton("A");
		numberHexB = new calcJButton("B");
		numberHexC = new calcJButton("C");
		numberHexD = new calcJButton("D");
		numberHexE = new calcJButton("E");
		numberHexF = new calcJButton("F");
		decimalButton = new calcJButton(".");
		decimalButton.setBorder(null);
		clearDisplayButton = new calcJButton("clear");
		clearDisplayButton.setBorder(null);
		for (int i=0 ;i<10 ;i++)
		{
			numberButtonArray[i] = new calcJButton(Integer.toString(i));
		}
		for (int i=3; i>0; i--)
		{
			for(int k=2; k>=0; k--)
			{
				numberButtonPanel.add(numberButtonArray[i*3-k]);
			}
		}
		numberButtonPanel.add(numberButtonArray[0]);
		numberButtonPanel.add(decimalButton);
		numberButtonPanel.add(clearDisplayButton);
		for (int i=0;i<10;i++)
		{
			numberButtonArray[i].addActionListener( event -> controller.numberPressed(event.getActionCommand()));
		}
		numberHexA.addActionListener( event -> controller.numberPressed(event.getActionCommand()));
		numberHexB.addActionListener( event -> controller.numberPressed(event.getActionCommand()));
		numberHexC.addActionListener( event -> controller.numberPressed(event.getActionCommand()));
		numberHexD.addActionListener( event -> controller.numberPressed(event.getActionCommand()));
		numberHexE.addActionListener( event -> controller.numberPressed(event.getActionCommand()));
		numberHexF.addActionListener( event -> controller.numberPressed(event.getActionCommand()));
		decimalButton.addActionListener(event -> controller.numberPressed(event.getActionCommand()));
		clearDisplayButton.addActionListener(event -> {controller.clearCalculatorPressed();});
		return numberButtonPanel;
	}
	
	private JPanel makeBasicOperatorPanel()
	{
		opSquaredButton = new calcJButton("^2");
		opAdditionButton = new calcJButton("+");
		opSubtractionButton = new calcJButton("-");
		opMultiplicationButton = new calcJButton("x");
		opDivisionButton = new calcJButton("/");
		opEqualsButton = new calcJButton("=");
		opPowerButton = new calcJButton("^");
		opSquareRootButton = new calcJButton("\u221A");
		opNegateButton = new calcJButton("neg");
		opFactorialButton = new calcJButton("!");
		
		JPanel operatorPanel = new JPanel();
		operatorPanel.setLayout(new GridLayout(5, 2, 0, 0));
		operatorPanel.setBorder(null);
		operatorPanel.setBounds(315, 23, 182, 175);
		operatorPanel.add(opSquaredButton);
		operatorPanel.add(opPowerButton);
		operatorPanel.add(opSquareRootButton);
		operatorPanel.add(opAdditionButton);
		operatorPanel.add(opSubtractionButton);
		operatorPanel.add(opMultiplicationButton);
		operatorPanel.add(opDivisionButton);
		operatorPanel.add(opNegateButton);
		operatorPanel.add(opEqualsButton);
		operatorPanel.add(opFactorialButton);
		
		opAdditionButton.addActionListener(event -> controller.operatorPressed(event.getActionCommand()));
		opSubtractionButton.addActionListener(event -> controller.operatorPressed(event.getActionCommand()));
		opMultiplicationButton.addActionListener(event -> controller.operatorPressed(event.getActionCommand()));
		opDivisionButton.addActionListener(event -> controller.operatorPressed(event.getActionCommand()));
		opPowerButton.addActionListener(event -> controller.operatorPressed(event.getActionCommand()));
		opSquaredButton.addActionListener(event -> controller.immediateOperatorPressed(event.getActionCommand()));
		opFactorialButton.addActionListener(event -> controller.immediateOperatorPressed(event.getActionCommand()));
		opSquareRootButton.addActionListener(event -> controller.immediateOperatorPressed(event.getActionCommand()));
		opNegateButton.addActionListener(event -> controller.negateInputPressed());
		opEqualsButton.addActionListener(event -> controller.equalPressed());
		this.getRootPane().setDefaultButton(opEqualsButton);
		return operatorPanel;
	}
	
	private JPanel makeDisplayPanel()
	{
		JPanel displayPanel = new JPanel();
		displayPanel.setBackground(UIManager.getColor("MenuBar.background"));
		displayPanel.setBorder(BorderFactory.createEmptyBorder(20,10,10,20));
		displayPanel.setBounds(0, 0, 508, 219);
		displayPanel.setLayout(null);
		
		currentAnswerDisplay = new JTextField("");
		currentAnswerDisplay.setBorder(null);
		currentAnswerDisplay.setBounds(10, 175, 487, 33);
		currentAnswerDisplay.setBackground(UIManager.getColor("MenuBar.background"));
		currentAnswerDisplay.setEditable(false);
		currentAnswerDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		currentAnswerDisplay.setFont(new Font("SansSerif", Font.BOLD, 16));
		displayPanel.add(currentAnswerDisplay);
		
		runningCalculationDisplay = new JTextField("");
		runningCalculationDisplay.setBorder(null);
		runningCalculationDisplay.setBounds(10, 116, 487, 33);
		runningCalculationDisplay.setEditable(false);
		runningCalculationDisplay.setBackground(UIManager.getColor("Menu.background"));
		runningCalculationDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		runningCalculationDisplay.setFont(new Font("SansSerif", Font.BOLD, 16));
		displayPanel.add(runningCalculationDisplay);
		
		JLabel lblCalculation = new JLabel("calculation:");
		lblCalculation.setFont(new Font("Verdana", Font.BOLD, 12));
		lblCalculation.setBounds(422, 102, 76, 14);
		displayPanel.add(lblCalculation);

		JLabel answerLabel = new JLabel("Answer:");
		answerLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		answerLabel.setBounds(442, 160, 55, 14);
		displayPanel.add(answerLabel);
		
		baseSelectorDecimal = new JRadioButton("Decimal");
		baseSelectorDecimal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		baseSelectorDecimal.setBounds(10, 46, 109, 23);
		baseSelectorDecimal.setSelected(true);
		displayPanel.add(baseSelectorDecimal);
		
		baseSelectorHexidecimal = new JRadioButton("Hexidecimal");
		baseSelectorHexidecimal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		baseSelectorHexidecimal.setBounds(10, 66, 109, 23);
		displayPanel.add(baseSelectorHexidecimal);
		
		baseSelectorBinary = new JRadioButton("Binary");
		baseSelectorBinary.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		baseSelectorBinary.setBounds(10, 86, 109, 23);
		displayPanel.add(baseSelectorBinary);
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(baseSelectorDecimal);
		radioButtonGroup.add(baseSelectorHexidecimal);
		radioButtonGroup.add(baseSelectorBinary);
		
		JLabel titleLabel = new JLabel("Calculator");
		titleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
		titleLabel.setBounds(10, 11, 175, 28);
		displayPanel.add(titleLabel);
		
		baseSelectorDecimal.addActionListener(event -> {changeBaseDisplay(calculatorController.NumberBase.DECIMAL);});
		baseSelectorBinary.addActionListener(event -> {changeBaseDisplay(calculatorController.NumberBase.BINARY);});
		baseSelectorHexidecimal.addActionListener(event -> {changeBaseDisplay(calculatorController.NumberBase.HEXIDECIMAL);});
		return displayPanel;
	}
	
	private void changeBaseDisplay(calculatorController.NumberBase base)
	{
		Boolean numbersEnabled = true;
		Boolean decButtonEnabled = true;
		numberButtonPanel.setLayout(new GridLayout(4, 3, 0, 0));
		if (base.equals(calculatorController.NumberBase.BINARY))
		{
			numbersEnabled = false;
			decButtonEnabled = false;
		}
		numberButtonPanel.removeAll();	
		
		if (base.equals(calculatorController.NumberBase.HEXIDECIMAL))
		{
			numberButtonPanel.setLayout(new GridLayout(6, 3, 0, 0));
			decButtonEnabled = false;
			numberButtonPanel.add(numberHexA);
			numberButtonPanel.add(numberHexB);
			numberButtonPanel.add(numberHexC);
			numberButtonPanel.add(numberHexD);
			numberButtonPanel.add(numberHexE);
			numberButtonPanel.add(numberHexF);
		}
		for (int i=3; i>0; i--)
		{
			for(int k=2; k>=0; k--)
			{
				numberButtonPanel.add(numberButtonArray[i*3-k]).setEnabled(numbersEnabled);
			}
		}
		if (base.equals(calculatorController.NumberBase.BINARY))
		{
			numberButtonArray[0].setEnabled(true);
			numberButtonArray[1].setEnabled(true);
		}
		numberButtonPanel.add(numberButtonArray[0]);
		numberButtonPanel.add(decimalButton).setEnabled(decButtonEnabled);
		numberButtonPanel.add(clearDisplayButton);
		controller.changeNumberBase(base);
		numberButtonPanel.revalidate();
		numberButtonPanel.repaint();
	}
	
	private static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}  
	
	@Override
	public void setRunningCalcDisplay(String string)
	{
		runningCalculationDisplay.setText(string);
	}
	
	 @Override
	public void setCurrentTotalDisplay(String string)
	{
		currentAnswerDisplay.setText(string);
	}	
}
