import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class UserAuth implements ActionListener,KeyListener
{
JDialog jd;
JPanel p;
JLabel l1,l2;
JButton b1,b2;
JTextField t1;
JPasswordField t2;
JOptionPane jp;
Connection cc;
Statement ss;
ResultSet rs;
ResultSetMetaData rsm;
String s1,s2,s3,options[]={"Yes","No"};
int i,c,col,userbit;

//If usb passed=1 then show Main Screen, if usb passed=2 then EXIT to SYSTEM ,is usb=3 then execute the same as usb=1 and set the close properties for the JDialog
     
	UserAuth(int usb)
	{
	userbit=usb;	
		
	jd=new JDialog();
	p=new JPanel();
	l1=new JLabel("Username: ");
	l2=new JLabel("Password: ");
	t1=new JTextField(10);
	t2=new JPasswordField(10);
	b1=new JButton("OK");
	b2=new JButton("Clear All");
	jp=new JOptionPane();
		
	jd.setLayout(null);
	p.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
	
	p.add(l1);
	p.add(t1);
	p.add(l2);
	p.add(t2);
	p.add(b1);
	p.add(b2);
	jd.add(p);
	
	b1.addActionListener(this);
	b2.addActionListener(this);
	t1.addKeyListener(this);
	t2.addKeyListener(this);
	if(usb==3)
	{
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
	}
	else
	{
		jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	jd.setTitle("User Authentication");
	jd.setSize(250,200);
	jd.setVisible(true);
	jd.setLocationRelativeTo(null);
	jd.setResizable(false);
	jd.setAlwaysOnTop(true);
	
	p.setBounds(35,30,180,100);
	p.setBorder(BorderFactory.createRaisedBevelBorder());
	
	b1.setMnemonic('O');
	b2.setMnemonic('C');
	l1.setDisplayedMnemonic('U');
	l2.setDisplayedMnemonic('P');
	l1.setLabelFor(t1);
	l2.setLabelFor(t2);
	jd.requestFocus();
	jd.requestFocusInWindow();
	t1.requestFocusInWindow();
	
	jd.repaint();
	jd.validate();
	p.repaint();
	p.validate();
    }
    
    public void actionPerformed(ActionEvent e2)
    {
    	if(e2.getSource()==b1)
    	{
    	s1=t1.getText().trim();
    	s2=t2.getText().trim();
    		
    		if(s1.trim().equals("")||s2.trim().equals(""))
    		{
    			jp.showMessageDialog(jd,"Username or Password cannot be empty !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
    		}
    		
    		else
    		{
    		
    			try
    			{
    				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    				cc=DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+"DB.mdb","waot","gurucool");
    				ss=cc.createStatement();
    				
    				//ss.execute("create table login(Uname text(20),Pword text(20))");
    				
    				rs=ss.executeQuery("select Pword from login where Uname='"+s1+"';");
    				
    				rsm=rs.getMetaData();
    				col=rsm.getColumnCount();
    				
    				while(rs.next())
    				{	
    					for(i=1;i<=col;i++)
    					{
    							s3=rs.getString(i).trim();
    							//System.out.println("Password:"+s3);
    					}
    				}
    				
    				ss.close();
    				cc.close();	
    			}
    			
    			catch(Exception e3)
    			{
    				e3.printStackTrace();
    			}
    			
    			if(s2.equals(s3))
    			{
    				
    				if((userbit==1)||(userbit==3))
    				{
    					//Is userbit=1 dispose the JDialog and goto Main Screen
    					
    					jp.showMessageDialog(jd,"<html><body><b>User authenticated successfully</b>.<br>W.A.O.T. application will now <b>show</b> the <b>Main Screen</b>.</body></html>","Authentication successful !!!",JOptionPane.INFORMATION_MESSAGE);
    					
    					System.out.println("Display Main Screen");
    					
    					new Main();
    					
    					jd.dispose();
    				}
    				else
    				{
    					//Is userbit=2, Exit to System
    					
    					jp.showMessageDialog(jd,"<html><body><b>User authenticated successfully</b>.Any <b>running utilites</b> of W.A.O.T. will also be <b>terminated</b>.<br><br>W.A.O.T. application will now <b>QUIT</b> and <b>EXIT TO SYSTEM</b>.</body></html>","Authentication successful !!!",JOptionPane.INFORMATION_MESSAGE);
    					
    					jd.dispose();
    					
    					System.out.println("Exit to System");
    					
    					System.exit(0);
    				}
    				
    			}
    			
    			else
    			{
    				jp.showMessageDialog(jd,"Invalid Username or Password.","User authentication failed !!!",JOptionPane.WARNING_MESSAGE);
    			}
    		}
    	}
    	
    	if(e2.getSource()==b2)
    	{
    		t1.setText("");
    		t2.setText("");
    	}
    }
    
    public void keyPressed(KeyEvent e4)
    {
    	if(e4.getKeyCode()==e4.VK_ENTER)
    	{
    		b1.doClick();
    	}
    }
    
    public void keyReleased(KeyEvent e4)
    {
    }
    
    public void keyTyped(KeyEvent e4)
    {
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
    	
    	UserAuth jdt=new UserAuth(2);
   }
}