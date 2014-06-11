import java.awt.*;
import java.awt.event.*;


public class Calculator extends Frame implements WindowListener
{
		double answer, input, temp = 1;
		TextField Display;
		String prev_op = "=";
		Boolean startNum = true;
		String[] opOrder = {"+","-","*","/","%","^","sqrt","="};
		String[] numOrder = {"1","2","3","4","5","6","7","8","9","C","0","CE"};
		
		Button[] num = new Button[12];
		Button[] op = new Button[8];
		private static final long serialVersionUID = 1L;
		
		public Calculator() throws NumberFormatException
		{
			addWindowListener(this);
			
			Panel panelDisplay = new Panel(new FlowLayout());
		    Display = new TextField("0", 20);
		    panelDisplay.add(Display);
		    
		    ActionListener NumListener = new NumListener();
		    ActionListener OpListener = new OpListener();
		    
		    Panel panelButtons = new Panel(new GridLayout(1,2));
		    
		    Panel panelNum = new Panel(new GridLayout(4,3));
		    for(int i=0; i<12; i++)
		    {
		    	num[i] = new Button(numOrder[i]);
			    panelNum.add(num[i]);
			    num[i].addActionListener(NumListener);
		    }
		    Panel panelOp = new Panel(new GridLayout(4,2));
		    for(int i=0; i<8; i++)
		    {
		    	op[i] = new Button(opOrder[i]);
			    panelOp.add(op[i]);
			    op[i].addActionListener(OpListener);
		    }
		    
		   	Display.setText("");
		    
		    setLayout(new BorderLayout());
		    add(panelDisplay, BorderLayout.NORTH);
		    panelButtons.add(panelNum);
		    panelButtons.add(panelOp);
		    add(panelButtons, BorderLayout.CENTER);
				    
		    setTitle("Calculator");
		    setSize(400,300);
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
		
		
		public void windowClosing(WindowEvent we)
		{
			System.exit(0);
		}
		
		public void windowOpened(WindowEvent we) {}
		
		public void windowClosed(WindowEvent we) {}
		
		public void windowIconified(WindowEvent we) {}
		
		public void windowDeiconified(WindowEvent we) {}
		
		public void windowActivated(WindowEvent we) {}
		
		public void windowDeactivated(WindowEvent we) {}
		
		public static void main(String Args[])
		{
			new Calculator();
		}
	
}
