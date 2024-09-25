//Libraries in java are essentially packages that contain classes or interfaces
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

//class name that subclass from JFrame and implementing actions for the mouse from ActionListener
public class JCalc extends JFrame implements ActionListener

{

	// data fields
	JPanel top,bottom;
	JScrollPane middle;
	JTextArea ta;
	JButton btn1,btn2;
	JButton[] btn;
	Container c;
	String op="";
	String a="",b="";
	DecimalFormat df;

	// constructor with string as an argument
	JCalc(String title){

	// pass title name to JFrame
	super(title);

	// display two decimal numbers by using DecimalFormat
	df=new DecimalFormat("0.00");

	// initialize Container
	c=getContentPane();

	// set as null
	c.setLayout(null);

	// initialize JTextArea
	ta=new JTextArea();

	// initialize JPanel
	top=new JPanel();

	// initialize JScrollPane
	middle=new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	// initialize JPanel
	bottom=new JPanel();

	// initialize JButton and set the title
	btn1=new JButton("Clear Last");

	// initialize JButton and set the title
	btn2=new JButton("Clear All");

	// set the position for JPanel top
	top.setBounds(0,0,380,50);

	// set the position for JScrollPane middle
	middle.setBounds(0,50,380,250);

	// set the position for JPanel bottom
	bottom.setBounds(0,300,380,300);

	// set the Layout for the JPanel top to be GridLayout
	top.setLayout(new GridLayout(1,2));

	// set the Layout for JPanel bottom to be GridLayout
	bottom.setLayout(new GridLayout(4,4));

	// add to Container
	c.add(top);
	c.add(middle);
	c.add(bottom);

	// add buttons to JPanel top
	top.add(btn1);
	top.add(btn2);

	// generate 16 array buttons
	btn=new JButton[16];

	// initialize JButtons and set their titles
	btn[0]=new JButton("7");
	btn[1]=new JButton("8");
	btn[2]=new JButton("9");
	btn[3]=new JButton("+");
	btn[4]=new JButton("4");
	btn[5]=new JButton("5");
	btn[6]=new JButton("6");
	btn[7]=new JButton("-");
	btn[8]=new JButton("1");
	btn[9]=new JButton("2");
	btn[10]=new JButton("3");
	btn[11]=new JButton("*");
	btn[12]=new JButton("0");
	btn[13]=new JButton(".");
	btn[14]=new JButton("=");
	btn[15]=new JButton("/");

	// add their mouse actions by using for loop
	for(int i=0;i<16;i++)

	{

	btn[i].addActionListener(this);
	bottom.add(btn[i]);

	}

	// add mouse actions  for btn1 and btn2
	btn1.addActionListener(this);
	btn2.addActionListener(this);

	// display the frame and set its size
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	setSize(400,640);

	}

	// action for mouse events
	public void actionPerformed(ActionEvent ae)

	{

	// get the action event
	String s=ae.getActionCommand();

	// check if the button title is Clear Last
	if(s.equals("Clear Last"))

	{

	// check if the button title is =

	if(!op.equals("="))

	{

	// check if JTextArea is empty
	if(ta.getText().endsWith("\n"))

	{
		
	// display the result in JTextArea
	ta.setText(ta.getText().substring(0,ta.getText().length()-2));

	// assign = to op
	op="";

	}

	else

	{
	// display the result in JTextArea
	ta.setText(ta.getText().substring(0,ta.getText().length()-1));

	//checks op string variable if it empty or contains character =
	if(op.equals("")||op.equals("="))

	// get substring from string variable a from 0 to its length
	a=a.substring(0,a.length()-1);

	else
		
	//get substring from string variable b from 0 to its length
	b=b.substring(0,b.length()-1);

	}
	
	}

	}

	// check if the button title is Clear All
	else if(s.equals("Clear All"))

	{
	// clear textarea
	ta.setText("");

	// assign = to op
	op="";

	}
	// check if the button title is +,-,* or /
	else if(s.equals("+") || s.equals("-")||s.equals("*")||s.equals("/"))

	{

	// checks textarea and get a substring from it to check
	if(ta.getText().substring(ta.getText().length()-2,ta.getText().length()-1).equals("+") ||ta.getText().substring(ta.getText().length()-2,ta.getText().length()-1).equals("-")||ta.getText().substring(ta.getText().length()-2,ta.getText().length()-1).equals("*")||ta.getText().substring(ta.getText().length()-2,ta.getText().length()-1).equals("/"))

	{
		
	// display the result of condition
	ta.setText(ta.getText()+"Operator \'"+s+"\' has been ignored\n");

	}

	else

	{

	// check string op
	if(op.equals("") ||op.equals("="))

	{
	
	// display the result of condition
	ta.setText(ta.getText()+s+"\n");

	// assign = to op
	op=s;

	}

	else

	{
		
	// set the text in ta as the input and symbol =
	ta.setText(ta.getText()+"=");

	// call the method calc()
	calc();

	// set the text in textarea ta
	ta.setText(ta.getText()+s+"\n");

	// assign = to op
	op=s;

	}

	}

	}

	// check variable s if it contains character =
	else if(s.equals("="))

	{


	// set the text in textarea ta
	ta.setText(ta.getText()+s);

	// call the method calc()
	calc();

	// assign = to op
	op="=";

	}

	// check variable s  if it contains .
	else if(s.equals("."))

	{

	// check variable op if it conatins= or empty
	if(op.equals("") || op.equals("="))

	{
	// check if a contains . or not
	if(a.indexOf(".")==-1)

	{

	// concatenate a and s
	a=a+s;

	// set the text in ta
	ta.setText(ta.getText()+s);

	}

	}

	else

	{
	// check if b contains . or not
	if(b.indexOf(".")==-1)

	{
		
	// concatenate b and s
	b=b+s;

	// set the text in ta
	ta.setText(ta.getText()+s);

	}

	}

	}

	else

	{
	
	// check variable op if it conatins = or empty
	if(op.equals("="))

	{

	// assign value for a
	a="";

	// set the text in ta
	ta.setText(ta.getText()+"\n");

	}

	// set the text in ta
	ta.setText(ta.getText()+s);


	// check variable op if it conatins = or empty
	if(op.equals("")||op.equals("="))

	{

	// concatenate a and s
	a=a+s;

	}

	else

	{
	// concatenate a and s
	b=b+s;

	}

	}

	}
	// method calc that calculates the input's value
	void calc()

	{

	// check operator
	if(op.equals("+"))

	{
	
	// check if a contains . and b
	if(a.indexOf(".")>=0 || b.indexOf(".")>=0)

	{
		
	// set the text in ta
	ta.setText(ta.getText()+"\n"+df.format(Float.parseFloat(a)+Float.parseFloat(b)));

	// format the result in two decimal
	a=df.format(Float.parseFloat(a)+Float.parseFloat(b));

	}

	else

	{

	//set the text in ta
	ta.setText(ta.getText()+"\n"+(Integer.parseInt(a)+Integer.parseInt(b)));

	// addtion  operation
	a=(Integer.parseInt(a)+Integer.parseInt(b))+"";

	}

	}
	//check operator

	else if(op.equals("-"))

	{


	// check if a contains . and b
	if(a.indexOf(".")>=0 || b.indexOf(".")>=0)

	{

	// set the text in ta
	ta.setText(ta.getText()+"\n"+df.format(Float.parseFloat(a)-Float.parseFloat(b)));

	// format the result in two decimal
	a=df.format(Float.parseFloat(a)-Float.parseFloat(b));

	}

	else

	{

	// set the text in ta
	ta.setText(ta.getText()+"\n"+(Integer.parseInt(a)-Integer.parseInt(b)));

	// subtract  operation

	a=(Integer.parseInt(a)-Integer.parseInt(b))+"";

	}

	}
	// check operator

	else if(op.equals("*"))

	{

	// check if a contains . and b
	if(a.indexOf(".")>=0 || b.indexOf(".")>=0)

	{

	// set the text in ta
	ta.setText(ta.getText()+"\n"+df.format(Float.parseFloat(a)*Float.parseFloat(b)));

	// format the result in two decimal
	a=df.format(Float.parseFloat(a)*Float.parseFloat(b));

	}

	else

	{

	// set the text in ta
	ta.setText(ta.getText()+"\n"+(Integer.parseInt(a)*Integer.parseInt(b)));

	// multiply operation
	a=(Integer.parseInt(a)*Integer.parseInt(b))+"";

	}

	}
	
	// check operator
	else if(op.equals("/"))

	{

	// check if a contains . and b
	if(a.indexOf(".")>=0 || b.indexOf(".")>=0)

	{

	ta.setText(ta.getText()+"\n"+df.format(Float.parseFloat(a)/Float.parseFloat(b)));
	
	// format the result in two decimal

	// divide operation
	a=df.format(Float.parseFloat(a)/Float.parseFloat(b));

	}

	else

	{

	ta.setText(ta.getText()+"\n"+(Integer.parseInt(a)/Integer.parseInt(b)));

	// divide operation
	a=(Integer.parseInt(a)/Integer.parseInt(b))+"";

	}

	}

	// assign b
	b="";

	}

	// main driver to run the program
	public static void main(String[] args) {

	// creating instance object from JCalc
	new JCalc("");

	}
}
