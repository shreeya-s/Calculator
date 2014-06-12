import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class CalculatorSwing extends JFrame
{
	
	double answer, input, temp = 1;
	
	JTextField Display = new JTextField("0");
	
	JButton[] Num = new JButton[12];
	JButton[] Op = new JButton[8];
	
	Boolean startNum = true;
	String prev_op = "=";
	String[] num = {"1","2","3","4","5","6","7","8","9","C","0","CE"};
	String[] op = {"+","-","*","/","%","^","sqrt","="};
	
	private static final long serialVersionUID = 1L;
 	
	public CalculatorSwing() throws NumberFormatException
	{
		Container pane = getContentPane();
		
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		Display.setFont(font1);
		Display.setBackground(Color.GRAY);
		Display.setForeground(Color.WHITE);
		Display.setPreferredSize(new Dimension(300,50));
		
		JPanel panelDisplay = new JPanel();
		panelDisplay.setLayout(new FlowLayout());
		panelDisplay.add(Display);
		
		ActionListener NumListener = new NumListener();
		ActionListener OpListener = new OpListener();
		
		JPanel panelNum = new JPanel();
		panelNum.setLayout(new GridLayout(3,4));
		for(int i=0; i<12; i++)
		{
			Num[i] = new JButton(num[i]);
			panelNum.add(Num[i]);
			Num[i].addActionListener(NumListener);
		}
		
		JPanel panelOp = new JPanel();
		panelOp.setLayout(new GridLayout(2,4));
		for(int i=0; i<8; i++)
		{
			Op[i] = new JButton(op[i]);
			panelOp.add(Op[i]);
			Op[i].addActionListener(OpListener);
		}
		JPanel panelEmpty = new JPanel();
		JPanel panelMisc = new JPanel();
	   	Display.setText("");
	   	
		pane.setLayout(new BorderLayout());
		pane.add(panelDisplay, BorderLayout.NORTH);
		
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new GridLayout(1,2));
		panelButton.add(panelNum);
		
		panelMisc.setLayout(new GridLayout(2,1));
		panelMisc.add(panelOp);
		panelMisc.add(panelEmpty);
		
		panelButton.add(panelMisc);
		
		pane.add(panelButton, BorderLayout.CENTER);
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,300);
		setTitle("Calculator");
		setVisible(true);
	}
	
	class NumListener implements ActionListener
	{
		public void actionPerformed(ActionEvent n)
		{
			if(n.getSource()==num[9])
			{
				String temp = Display.getText();
				Display.setText(temp.substring(0,temp.length()-1));
			}
			else if(n.getSource()==num[11])
			{
				startNum = true;
				prev_op = "=";
				Display.setText("");
				answer = 0.0;
				input = 0.0;
				temp = 1;
			}
			else
			{   String digit = n.getActionCommand();
				 if (startNum) 
				 {
					 Display.setText(digit);
		             startNum = false;
		         } 
				 else 
				 {  
					 Display.setText(Display.getText()+digit);
		         }
			}
		}
	}
	
	class OpListener implements ActionListener
	{
		public void actionPerformed(ActionEvent o)
			{
				input = Double.parseDouble(Display.getText());

				if(startNum)
				{
					startNum = true;
					prev_op = "=";
					Display.setText("");
					answer = 0.0;
					input = 0.0;
					temp = 1;
				}
				else
				{	
					startNum = true;

					if(o.getSource()== op[0])
					{
						input = Double.parseDouble(Display.getText());
						answer += input;
						Display.setText(answer + "");
						prev_op = "+";
					}
					else if(o.getSource()== op[1])
					{
						input = Double.parseDouble(Display.getText());
						answer -= input;
						Display.setText(answer + "");
						prev_op = "-";
					}
					else if(o.getSource()== op[2])
					{
						input = Double.parseDouble(Display.getText());
						if(answer == 0)
						{
							temp *= input;
							answer = temp;
						}
						else
							answer *= input;
						Display.setText(answer + "");
						prev_op = "*";
					}
					else if(o.getSource()== op[3])
					{
						input = Double.parseDouble(Display.getText());
						temp = input/temp;
						answer = temp;
						Display.setText(answer + "");
						prev_op = "/";
					}
					else if(o.getSource()== op[4])
					{
						input = Double.parseDouble(Display.getText()); 
						answer = input;
						Display.setText(input + "");
						prev_op = "%";
					}
					else if(o.getSource()== op[5])
					{
						input = Double.parseDouble(Display.getText());
						answer = input;
						answer = Math.pow(answer, input);
						Display.setText(answer + "");
						prev_op = "^";
					}
					else if(o.getSource()== op[6])
					{
						input = Double.parseDouble(Display.getText());
						answer = Math.sqrt(input);
						Display.setText(answer + "");
						prev_op = "sqrt";
					}
					else if(o.getSource()== op[7])
					{
						switch(prev_op)
						{
						case "+":	input = Double.parseDouble(Display.getText());
									answer += input;
									Display.setText(answer + "");
									break;
						case "-":	input = Double.parseDouble(Display.getText());
									answer -= input;
									Display.setText(answer + "");
									break;
						case "*":	input = Double.parseDouble(Display.getText());
									answer *= input;
									Display.setText(answer + "");
									break;
						case "/":	input = Double.parseDouble(Display.getText());
									temp = temp/input;
									answer = temp;
									Display.setText(answer + "");
									break;
						case "%":	input = Double.parseDouble(Display.getText());
									answer %= input;
									Display.setText(answer + "");
									break;
						case "^":	input = Double.parseDouble(Display.getText());
									answer = Math.pow(answer, input);
									Display.setText(answer + "");
									break;
						case "sqrt":	input = Double.parseDouble(Display.getText());
										answer = Math.sqrt(input);
										Display.setText(answer + "");
										break;
						default: 	Display.setText(answer + "");
									break;
						}
						prev_op = "=";
					}
				}
		}
	}
	
	public static void main(String Args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException 
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new CalculatorSwing();
	}
}
