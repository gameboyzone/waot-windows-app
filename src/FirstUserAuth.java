import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class FirstUserAuth implements ActionListener,KeyListener
{
JDialog jd;
JPanel p,p1;
JLabel l1,l2,l3;
JButton b1,b2;
JTextField t1;
JPasswordField t2,t3;
JOptionPane jp;
MyCanvas mc;
Toolkit kit;
Image img1,img2;
Connection cc;
Statement ss;
String s1,s2,s3,s4,options[]={"Yes","No"};
int i,c;
       
	FirstUserAuth() 
	{
	jd=new JDialog();
	p=new JPanel();
	p1=new JPanel();
	l1=new JLabel("Username: ");
	l2=new JLabel("Password: ");
	l3=new JLabel("Re-type Password: ");
	t1=new JTextField(10);
	t2=new JPasswordField(10);
	t3=new JPasswordField(10);
	b1=new JButton("OK");
	b2=new JButton("Clear All");
	jp=new JOptionPane();
	mc=new MyCanvas();
	kit=Toolkit.getDefaultToolkit();
	img1=kit.getImage("images\\topfua.jpg");
	img2=kit.getImage("images\\bottomfua.jpg");
	
	p.setLayout(null);
	p1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
	
	p.add(mc);
	p1.add(l1);
	p1.add(t1);
	p1.add(l2);
	p1.add(t2);
	p1.add(l3);
	p1.add(t3);
	p1.add(b1);
	p1.add(b2);
	p.add(p1);
	jd.setContentPane(p);
	
	t1.addKeyListener(this);
	t2.addKeyListener(this);
	t3.addKeyListener(this);
	b1.addActionListener(this);
	b2.addActionListener(this);
	jd.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	jd.addWindowListener(new WindowAdapter()
    	{
    		public void windowClosing(WindowEvent e2)
    		{
    			c=jp.showOptionDialog(jd,"Closing this window will exit the application. Do you want to continue...?"," Are you sure...? ",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[0]);
   						
   				if(c==JOptionPane.OK_OPTION)
   				{
   					System.exit(0);
   				}
    		}
    	});
	
	jd.setTitle("Admin Account Creation");
	jd.setSize(500,400);
	jd.setVisible(true);
	jd.setLocationRelativeTo(null);
	jd.setResizable(false);
	jd.setAlwaysOnTop(true);
	mc.setBounds(0,0,500,152);
	mc.setVisible(true);
	
	p1.setBounds(130,180,220,150);
	p1.setBorder(BorderFactory.createRaisedBevelBorder());
	
	b1.setMnemonic('O');
	b2.setMnemonic('C');
	l1.setDisplayedMnemonic('U');
	l1.setLabelFor(t1);
	l2.setDisplayedMnemonic('P');
	l2.setLabelFor(t2);
	l3.setDisplayedMnemonic('R');
	l3.setLabelFor(t3);
	
	jd.requestFocus();
	jd.requestFocusInWindow();
	t1.requestFocusInWindow();
	
	jd.repaint();
	jd.validate();
	p.repaint();
	p.validate();
    }
    
    public class MyCanvas extends Canvas
    {
    	public void paint(Graphics g)
    	{
    		g.drawImage(img1,0,0,this);
    		g.drawLine(0,76,500,76);
    		g.drawImage(img2,0,77,this);
    		g.drawLine(0,151,500,151);
    	}
    }
    
   public void actionPerformed(ActionEvent e2)
    {
    	if(e2.getSource()==b1)
    	{
    	s1=t1.getText().trim();
    	s2=t2.getText().trim();
    	s3=t3.getText().trim();
    		
    		if(s1.trim().equals("")||s2.trim().equals("")||s3.trim().equals(""))
    		{
    			jp.showMessageDialog(jd,"Username or Password cannot be empty !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
    		}
    		
    		else if(s2.equals(s3)==false)
    		{
    			jp.showMessageDialog(jd,"Both the passwords do not match !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
    		}
    		
    		else
    		{
    		
    			try
    			{
    				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    				cc=DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+"DB.mdb","waot","gurucool");
    				ss=cc.createStatement();
    				
    				//ss.execute("create table login(Created number,Uname text(20),Pword text(20))");
    				
    				ss.execute("update login set Uname='"+s1+"',Pword='"+s2+"',Created='1' where Created='0';");
    				
    				System.out.println("Uname and Pword updated successfully");
    				
    				ss.close();
    				cc.close();	
    			}
    			
    			catch(Exception e3)
    			{
    				e3.printStackTrace();
    			}
    			   			
    			jp.showMessageDialog(jd,"Admin account created successfully.","Operation successful !!!",JOptionPane.INFORMATION_MESSAGE);
    			
    			//Dispose the Frame and run the Main Frame
    			
    			new Main();
    			
    			jd.dispose();
    		}
    	}
    	
    	if(e2.getSource()==b2)
    	{
    		t1.setText("");
    		t2.setText("");
    		t3.setText("");
    	}
    }
    
    public void keyTyped(KeyEvent e4)
    {
    }
    
    public void keyReleased(KeyEvent e4)
    {
    }
    
    public void keyPressed(KeyEvent e4)
    {
    	if(e4.getKeyCode()==e4.VK_ENTER)
    	{
    		b1.doClick();
    	}
    }
    
   public static void main(String args[]) 
   {
   		try
   		{
   			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
   		}
   		catch(Exception e)
   		{
   			e.printStackTrace();
   		}
    	
    	FirstUserAuth fua=new FirstUserAuth();
   }
}