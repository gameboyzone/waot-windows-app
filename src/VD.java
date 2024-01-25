import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class VD implements ActionListener,ItemListener
{
JFrame f;
JPanel p,p1,p2,p3,p4,p5,p6;
JButton b1,b2,b3;
JRadioButton r1,r2;
ButtonGroup bg;
JComboBox cb1,cb2;
JTextField t;
JFileChooser fc;
JOptionPane jp;
MyCanvas mc;
Toolkit kit;
Image img1,img2;
InputStreamReader ISR;
BufferedReader BR;
File fl1,fl2[];
Process ps1,ps2,ps3,ps4,ps5;
String ss,sd1,sd2,ld[],command1[],command2[],command3[],command4[];
Character chr;
int a,i,j,k,len;
char ch;

	VD() 
	{
	cb1=new JComboBox();
	cb2=new JComboBox();
	
	// Logic for calculating all existing drives(i.e. cb1) minus A-Z and all virtual drives(i.e. cb2) //

		try
		{
			ld=new String[20];
			fl2=File.listRoots();
			
			for(i=0;i<fl2.length;i++)
			{
			
				if(fl2[i].toString().substring(0,1)==null)
				{
					break;
				}
				else
				{
					//System.out.println(fl2[i].toString().substring(0,2));
					ld[i]=fl2[i].toString().substring(0,1);
					len=i;
				}
				
			}
			int temp;
				for(j=65;j<=100;j++)
				{
				ch=(char)j;
				chr=new Character(ch);
				
				temp=0;
					
					for(i=0;i<=len;i++)
					{
										
						if(chr.toString().equals(ld[i]))
						{
							System.out.println(i+": "+ld[i]);
								
							temp=1;
						}
					}
					if ((temp==0)&&((chr.toString().equals("A"))||(chr.toString().equals("B"))||(chr.toString().equals("C"))||(chr.toString().equals("D"))||(chr.toString().equals("E"))||(chr.toString().equals("F"))||(chr.toString().equals("G"))||(chr.toString().equals("H"))||(chr.toString().equals("I"))||(chr.toString().equals("J"))||(chr.toString().equals("K"))||(chr.toString().equals("L"))||(chr.toString().equals("M"))||(chr.toString().equals("N"))||(chr.toString().equals("O"))||(chr.toString().equals("P"))||(chr.toString().equals("Q"))||(chr.toString().equals("R"))||(chr.toString().equals("S"))||(chr.toString().equals("T"))||(chr.toString().equals("U"))||(chr.toString().equals("V"))||(chr.toString().equals("W"))||(chr.toString().equals("X"))||(chr.toString().equals("Y"))||(chr.toString().equals("Z"))))
					{
						cb1.addItem(ch+":");
					}
				}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//Logic for finding currently existing Virtual Drives
		try
		{
			ps1=Runtime.getRuntime().exec("subst");
			ISR=new InputStreamReader(ps1.getInputStream());
			BR=new BufferedReader(ISR);
			
			while(true)
			{
				ss=BR.readLine();
				
				if(ss==null)
				{
					break;
				}
				
				cb2.addItem(ss.charAt(0)+":");
			}
		}
		catch(Exception e11)
		{
			e11.printStackTrace();
		}
	
	f=new JFrame("Virtual Drive Utility");
	p=new JPanel();
	p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	p4=new JPanel();
	p5=new JPanel();
	p6=new JPanel();
	b1=new JButton("Create");
	b2=new JButton("Remove");
	b3=new JButton("Browse");
	r1=new JRadioButton("Yes, create new Virtual Drive",true);
	r2=new JRadioButton("Yes, delete an existing Virtual Drive");
	bg=new ButtonGroup();
	t=new JTextField(21);
	fc=new JFileChooser();
	jp=new JOptionPane();
	mc=new MyCanvas();
	kit=Toolkit.getDefaultToolkit();
	img1=kit.getImage("images\\topvd.jpg");
	img2=kit.getImage("images\\bottomvd.jpg");

	p.setLayout(null);
	p1.setLayout(null);
	p2.setLayout(new FlowLayout(FlowLayout.LEFT,2,0));
	p3.setLayout(new FlowLayout(FlowLayout.LEFT,2,0));
	p5.setLayout(new FlowLayout(FlowLayout.LEFT));
	
	bg.add(r1);
	bg.add(r2);
	p4.add(cb1);
	p5.add(t);
	p5.add(b3);
	p6.add(cb2);
	p2.add(r1);
	p2.add(p4);
	p2.add(p5);
	p3.add(r2);
	p3.add(p6);
	p1.add(p2);
	p1.add(p3);
	p.add(mc);
	p.add(p1);
	p.add(b1);
	p.add(b2);
	f.setContentPane(p);
	
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
	r1.addItemListener(this);
	r2.addItemListener(this);
	f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e11)
    {
    	System.out.println("WindowAdapter reached");
    	
    	new Main();
    	
    	f.dispose();
    }});
	
	f.setSize(600,500);
	f.setVisible(true);
	f.setLocationRelativeTo(null);
	f.setResizable(false);
	
	mc.setBounds(0,0,600,192);
	mc.setVisible(true);
	
	
	p1.setBounds(5,195,582,220);
	p2.setBounds(20,20,550,100);
	p3.setBounds(20,125,550,80);
	p4.setPreferredSize(new Dimension(90,60));
	p5.setPreferredSize(new Dimension(268,60));
	p6.setPreferredSize(new Dimension(90,53));
	b1.setBounds(200,425,80,30);
	b2.setBounds(300,425,80,30);
	
	t.setEnabled(false);
	b1.setToolTipText("Click to create the Virtual Drive");
	b2.setToolTipText("Click to delete the Virtual Drive");
	b3.setToolTipText("Click to browse to the target location");
	b2.setEnabled(false);
	cb2.setEnabled(false);
	b1.setMnemonic('C');
    b2.setMnemonic('R');
    b3.setMnemonic('B');
    r1.setMnemonic('c');
    r2.setMnemonic('d');
	r1.setNextFocusableComponent(cb1);
	b3.setNextFocusableComponent(r2);
	r2.setNextFocusableComponent(cb2);
	cb2.setNextFocusableComponent(b1);
	b2.setNextFocusableComponent(r1);
	
	p1.setBorder(BorderFactory.createTitledBorder(" Select your Option: "));
    p2.setBorder(BorderFactory.createTitledBorder(" Create new Virtual Drive: "));
    p3.setBorder(BorderFactory.createTitledBorder(" Delete an existing Virtual Drive: "));
    p4.setBorder(BorderFactory.createTitledBorder(" Drive Letter: "));
    p5.setBorder(BorderFactory.createTitledBorder(" Target Path: "));
   	p6.setBorder(BorderFactory.createTitledBorder(" Drive Letter: "));
    
    p1.repaint();
    p1.validate();
    p.repaint();
    p.validate();
    }
    
    class MyCanvas extends Canvas
    {
    	public void paint(Graphics g)
    	{
    		g.drawImage(img1,0,0,this);
    		g.drawLine(0,90,600,90);
    		g.drawImage(img2,0,91,this);
    		g.drawLine(0,191,600,191);
    	}
    }
    
    public void actionPerformed(ActionEvent e3)
    {
    	
    	if(e3.getSource()==b1)
    	{
    		sd1=(String)cb1.getSelectedItem();
    		sd2=(String)cb2.getSelectedItem();
    		
    		//If the TextField is empty
    		
    		if(t.getText().trim().equals(""))
    		{
    			jp.showMessageDialog(f,"Target Path missing !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
    		}
    		
    		else
    		{
    			//Logic of Execution
    			
    			command1=new String[]{"subst",sd1,t.getText().trim()};
    			command3=new String[]{"cmd","/k","echo","subst",sd1,t.getText().trim(),">","\"%userprofile%\\start menu\\programs\\startup\\"+sd1.substring(0,1)+".bat\""};
				
				for(i=0;i<command3.length;i++)
				{
					System.out.println("Command3: "+command3[i]);
				}
				
				try
				{
					ps2=Runtime.getRuntime().exec(command1);
					ps4=Runtime.getRuntime().exec(command3);
						
					jp.showMessageDialog(f,"Virtual Drive "+sd1+" created successfully !!!","Operation successful !!!",JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e2)
				{
					e2.printStackTrace();
				}
    			
    			System.out.println("Checkpoint 1 reached");
    			
    			f.dispose();
    			
    			new VD();
    		}
    	}	
    			
    	if(e3.getSource()==b2)
    	{
    		sd1=(String)cb1.getSelectedItem();
    		sd2=(String)cb2.getSelectedItem();
    		
    		System.out.println(sd2);
    		
    		if(sd2==null)
    		{
    			jp.showMessageDialog(f,"There are no available Virtual Drives which can be deleted !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
    		}
    		
    		else
    		{
    			//Logic of Execution
    			
    			System.out.println(sd2);
    				
    			command2=new String[]{"subst","/D",sd2};
				command4=new String[]{"cmd","/k","del","/q","\"%userprofile%\\start menu\\programs\\startup\\"+sd2.substring(0,1)+".bat\""};
				
				try
				{
					ps3=Runtime.getRuntime().exec(command2);
					ps5=Runtime.getRuntime().exec(command4);
						
					jp.showMessageDialog(f,"Virtual Drive "+sd2+" removed successfully !!!","Operation successful !!!",JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e2)
				{	
					e2.printStackTrace();
				}
    			
    			System.out.println("Checkpoint 2 reached");
    			
    			f.dispose();
    			
    			new VD();
    		}
    	}
    	
    	if(e3.getSource()==b3)
    	{
    		
    		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    		a=fc.showOpenDialog(f);
    		
    		if(a==JFileChooser.APPROVE_OPTION)
    		{
    			fl1=fc.getSelectedFile();
    			t.setText(fl1.getPath());
    		}
    	}
    }
    
    public void itemStateChanged(ItemEvent e14)
    {
    	if(e14.getSource()==r1)
    	{
    		b2.setEnabled(false);
    		b1.setEnabled(true);
    		cb2.setEnabled(false);
    		cb1.setEnabled(true);
    	}
  
    	if(e14.getSource()==r2)
    	{
    		t.setText("");
    		b1.setEnabled(false);
    		b2.setEnabled(true);
    		cb1.setEnabled(false);
    		cb2.setEnabled(true);
    	}
    }
    
    public static void main(String[] args) 
    {
    	try
    	{
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	}	
    	catch(Exception e4)
    	{
    		e4.printStackTrace();
    	}
    	
    	VD vdd=new VD();
    }
}