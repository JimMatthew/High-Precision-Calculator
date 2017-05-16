import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class calcJButton extends JButton{

	public calcJButton(String buttonLabel)
	{
		super(buttonLabel);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFont(new Font("SansSerif", Font.BOLD, 20));
		setBackground(SystemColor.controlHighlight);
		setBorder(null);
	}
}
