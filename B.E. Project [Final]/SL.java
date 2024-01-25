import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.Date;

class SL extends Thread implements ActionListener,FocusListener,KeyListener
{
JFrame f;
Toolkit kit;
Image img1;
Dimension d;
Robot r;
JTextField t1;
JPasswordField t2;
JButton bt1,bt2;
JPanel p,p1,p2,p3;
JLabel l1,l2,l3,l4,l5,l6;
JOptionPane jp;
Connection cc;
Statement ss;
ResultSet rs;
ResultSetMetaData rsm;
MyCanvas1 mc1;
Process ps;
String s1,s2,s3,dtt1;
int w,h,i,col;
boolean done=true;
Date dt1;

	SL() 
	{
		try
		{
			String command1[]={"reg","add","HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System","/v","DisableTaskMgr","/t","REG_DWORD","/d","1","/f"};
			ps=Runtime.getRuntime().exec(command1);
		}
		catch(Exception e8)
		{
			e8.printStackTrace();
		}
		
	f=new JFrame("SL Test");
	p=new JPanel();
	p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	t1=new JTextField(10);
	t2=new JPasswordField(10);
	l1=new JLabel("Username: ");
	l2=new JLabel("Password: ");
	l3=new JLabel("--> Mouse events and Task Manager have been disabled.");
	l4=new JLabel("--> Use the “TAB” button to navigate across fields.");
	l5=new JLabel("");
	bt1=new JButton("OK");
	bt2=new JButton("Clear All");
	mc1=new MyCanvas1();
	kit=Toolkit.getDefaultToolkit();
	img1=kit.getImage("images\\topsl.JPG");
	d=kit.getScreenSize();
	w=d.width;
	h=d.height;
	
	f.setLayout(null);
	p.setLayout(null);
	p2.setLayout(new FlowLayout(FlowLayout.LEFT));
	p1.setLayout(new FlowLayout(FlowLayout.CENTER,10,15));
	
	p1.add(l1);
	p1.add(t1);
	p1.add(l2);
	p1.add(t2);
	p1.add(bt1);
	p1.add(bt2);
	p2.add(l3);
	p2.add(l4);
	p3.add(l5);
	p.add(mc1);
	p.add(p1);
	p.add(p2);
	p.add(p3);
	f.setContentPane(p);
	
	f.addFocusListener(this);
	f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	f.addWindowListener(new WindowAdapter(){public void windowIconified(WindowEvent e7){f.setState(JFrame.NORMAL);}});
	bt1.addActionListener(this);
	bt2.addActionListener(this);
	t1.addKeyListener(this);
	t2.addKeyListener(this);
	
	f.setUndecorated(true);	
	f.setSize(w,h);
	f.setVisible(true);
	f.setResizable(false);
	f.setLocation(0,0);
	f.setAlwaysOnTop(true);
	f.setMinimumSize(new Dimension(w,h));
	t1.setNextFocusableComponent(t2);
	l1.setDisplayedMnemonic('U');
	l1.setLabelFor(t1);
	bt1.setMnemonic('O');
	l2.setDisplayedMnemonic('P');
	l2.setLabelFor(t2);
	bt2.setMnemonic('C');
	l5.setFont(new Font("Comic Sans MS",Font.BOLD,14));
	
	mc1.setBounds(0,0,800,132);
	mc1.setVisible(true);
	p1.setBounds(290,180,200,140);
	p1.setBorder(BorderFactory.createTitledBorder(" User Authentication: "));
	p2.setBounds(240,450,295,80);
	p2.setBorder(BorderFactory.createTitledBorder(" Note: "));
	p3.setBounds(240,360,295,60);
	p3.setBorder(BorderFactory.createTitledBorder("Screen Locked at:"));
	
	p.repaint();
	p.validate();
	
	try
    {
    	dt1=new Date();
    	dtt1=dt1.getHours()+" : "+dt1.getMinutes()+" : "+dt1.getSeconds();
		l5.setText(dtt1.trim());
	}
    		
    catch(Exception e9)
    {
    	e9.printStackTrace();
    }
	
		try
		{
			r=new Robot();
		}
		
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		start();
    }								//Constructor closed
    
    class MyCanvas1 extends Canvas
    {
    	public void paint(Graphics g)
    	{
    		g.drawImage(img1,0,0,this);
    		g.drawLine(0,131,800,131);
    	}
    }
    
    public void run()
    {
    	try
    	{
    		while(done==true)
    		{		
    			r.mouseMove(790,300);
    			r.mousePress(InputEvent.BUTTON1_MASK);
    			r.mouseMove(790,300);
    			r.mouseRelease(InputEvent.BUTTON1_MASK);
    			r.mouseMove(790,300);
    		}
    	
    	}
    	catch(Exception e2)
    	{
    		e2.printStackTrace();
    	}
    }
    
    public void focusLost(FocusEvent e1)
    {
    	if(e1.getSource()==f)
    	{
    		f.requestFocus();
    		f.toFront();
    		f.setSize(w,h);
    		f.setAlwaysOnTop(true);
    		t1.requestFocus();
    		t1.requestFocusInWindow();
    		System.out.println("JFrame Focus Lost");
    	}
    }
    
    public void focusGained(FocusEvent e1)
    {
    	if(e1.getSource()==f)	
    	{
    		t1.requestFocus();
    		t1.requestFocusInWindow();
    		System.out.println("JFrame Focus Gained");
    	}
    }
    
    public void actionPerformed(ActionEvent e4)
    {
    	if(e4.getSource()==bt1)
    	{
    		s1=t1.getText().trim();
    		s2=t2.getText().trim();
    		
    		if(s1.trim().equals("")||s2.trim().equals(""))
    		{
    			jp.showMessageDialog(f,"Username or Password cannot be empty !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
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
    				done=false;
    				jp.showMessageDialog(f,"User authenticated successfully. The Screen Locking utility will now close.","Note",JOptionPane.INFORMATION_MESSAGE);
    				
    				try
					{
						String command2[]={"reg","add","HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System","/v","DisableTaskMgr","/t","REG_DWORD","/d","0","/f"};
						ps=Runtime.getRuntime().exec(command2);
					}
					catch(Exception e12)
					{
						e12.printStackTrace();
					}
    				
    				//Dispose the Frame and run the Main Frame
    				
    				new Main();
    				
    				f.dispose();
    				
    			}
    			else
    			{
    				jp.showMessageDialog(f,"Invalid Username or Password.","User authentication failed !!!",JOptionPane.WARNING_MESSAGE);
    			}
    		}
    	}
    	
    	if(e4.getSource()==bt2)
    	{
    		t1.setText("");
    		t2.setText("");
    	}
    }
    
    public void keyPressed(KeyEvent e2)
    {
    	if(e2.getSource()==t1)
    	{	
    		if(e2.getKeyCode()==e2.VK_ENTER)    	
    		{
    			bt1.doClick();
    		}
    	}
    	
    	if(e2.getSource()==t2)
    	{	
    		if(e2.getKeyCode()==e2.VK_ENTER)    	
    		{
    			bt1.doClick();
    		}
    	}
    }
    
    public void keyTyped(KeyEvent e2)
    {	
    }
    
    public void keyReleased(KeyEvent e2)
    {
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
    	
        SL slt=new SL();
	 }
}