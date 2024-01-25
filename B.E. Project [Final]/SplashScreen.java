import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer.*;
import java.sql.*;

class SplashScreen 
{
JWindow f;
JPanel p;
JProgressBar jpb;
JOptionPane jp;
MyCanvas mc;
Image img1,img2;
Toolkit kit;
Dimension d;
Timer t;
Process ps1,ps2;
InputStreamReader ISR;
BufferedReader BR;
StringBuffer sb;
Connection cc;
Statement ss;
ResultSet rs;
ResultSetMetaData rsm;
int i=0,col,created,a,b;
String s3,os,command1[],command2[],s5,s7;
int w,h;
	
	SplashScreen()
	{
	f=new JWindow();
	p=new JPanel();
	jpb=new JProgressBar(0,100);
	mc=new MyCanvas();
	jp=new JOptionPane();
	kit=Toolkit.getDefaultToolkit();
	img1=kit.getImage("images\\topspls.jpg");
	img2=kit.getImage("images\\bottomspls.jpg");
	sb=new StringBuffer();
	d=kit.getScreenSize();
    w=d.width;
    h=d.height;
	
	p.setLayout(null);
	
	p.add(mc);
	p.add(jpb);
	f.setContentPane(p);
	
	f.setSize(500,460);
	f.setVisible(true);
	f.setLocationRelativeTo(null);
	f.setAlwaysOnTop(true);
	
	mc.setBounds(0,0,500,428);
	mc.setVisible(true);
	jpb.setBounds(50,432,400,20);
	p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
	jpb.setStringPainted(true);
	jpb.setString("Loading...");
	jpb.setValue(0);
    
    t=new Timer(1000,new ActionListener()
    		{
    			public void actionPerformed(ActionEvent e2)
    			{
    				i=i+10;
    				jpb.setValue(i);
    				
    					if(i==100)
    					{
    						t.stop();
    						
    						//Logic for getting the OS name
    						
    						os=System.getProperty("os.name");
    						
    						if(os.indexOf("XP")==-1)
    						{
    							jp.showMessageDialog(f,"<html><body><b>W.A.O.T. application</b> requires you to have <b>Windows XP</b> for it's working.<br><br>Some of the features may <b>malfunction</b> or <b>cease to work</b>.</body></html>","Incompatible OS Warning !!!",JOptionPane.WARNING_MESSAGE);
    						}
    						
    						//Logic for calculating the Screen Resolution
    						
    						if((w!=800)&&(h!=600))
    						{
    							f.dispose();
    							
    							jp.showMessageDialog(f,"<html><body><b>W.A.O.T. application</b> requires you to have a <b>resolution</b> of <b>800*600</b> pixels for optimal display or some utilities might <b>not</b> display properly.</body></html> !!!","Incompatible Resolution Warning !!!",JOptionPane.WARNING_MESSAGE);	
    						}
    						
    						process();
    					}
    			}
    		}
    	);
    	
    t.start();
    }
    
    public class MyCanvas extends Canvas
    {
    	public void paint(Graphics g)
    	{
    		g.drawImage(img1,0,0,this);
    		g.drawLine(0,76,500,76);
    		g.drawImage(img2,0,77,this);
    		g.drawLine(0,427,500,427);
    	}
    }
    
    void process()
    {
    	try
    	{
    		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    		cc=DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+"DB.mdb","waot","gurucool");
    		ss=cc.createStatement();
    		
    		//ss.execute("alter table login ADD COLUMN Created text(1);");
    		
    		//ss.execute("update login set Created=1 where Uname='gg';");
    		
    		//System.out.println("Column updated successfully");
    		
    		rs=ss.executeQuery("select Created from login;");
    		
    		rsm=rs.getMetaData();
    		col=rsm.getColumnCount();
    				
    		while(rs.next())
    		{	
    			for(i=1;i<=col;i++)
    			{
    				s3=rs.getString(i).trim();
    				created=Integer.parseInt(s3);
    				System.out.println("Created: "+created);
    			}
    		}
    		
    		//When the JProgressBar is done, show the FirstUserAuth screen if Created=0 else show the main screen
    		if(created==0)
    		{
    			f.dispose();
    			
    			a=process1();
    			
    			System.out.println("value of a: "+a);
    			
    			if(a==2)
    			{
    				f.dispose();
    				
    				jp.showMessageDialog(f,"<html><body>You should be an <b>Administrator</b> to use this application.<br><b>W.A.O.T. application</b> will now <b>close</b></body></html> !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
   						
   					System.exit(0);
    			}
    			
    			new FirstUserAuth();
    		}
    		else
    		{
    			//Show the UserAuth Screen and dispose the JWindow
    			
    			f.dispose();
    			
    			a=process1();
    			
    			System.out.println("Value of a: "+a);
    			
    			if(a==2)
    			{
    				f.dispose();
    				
    				jp.showMessageDialog(f,"<html><body>You should be an <b>Administrator</b> to use this application.<br><b>W.A.O.T. application</b> will now <b>close</b></body></html> !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
   						
   					System.exit(0);
    			}
    			
    			new UserAuth(3);
    		}
    				
    		ss.close();
    		cc.close();	
    	}	
    	catch(Exception e3)
    	{
    		e3.printStackTrace();
    	}
    }
    
    int process1()
    {
    	
    	try
    	{
    		command1=new String[]{"cmd","/k","echo","%username%"};
    		ps1=Runtime.getRuntime().exec(command1);
    		ISR=new InputStreamReader(ps1.getInputStream());
    		BR=new BufferedReader(ISR);
    		
    		s5=BR.readLine();
    		ps1.destroy();
    	}
    	catch(Exception e4)
    	{
    		e4.printStackTrace();
    	}
    	
    	System.out.println("Windows XP UserName: "+s5);
    	
    	try
    	{
    		command2=new String[]{"net","user",s5};
    		ps2=Runtime.getRuntime().exec(command2);
    		ISR=new InputStreamReader(ps2.getInputStream());
    		BR=new BufferedReader(ISR);
    		
    		do
    		{
    			
    			s7=BR.readLine();
    			sb.append(s7);
    			
    		}while(s7!=null);
    	
    		ps2.destroy();
    	}
    	catch(Exception e4)
    	{
    		e4.printStackTrace();
    	}
    	
    	if(sb.toString().indexOf("Administrators")!=-1)
    	{	
    		return 1;
    	}
    	else
    	{
    		return 2;
    	}
    }
    
    public static void main(String[] args) 
    {
    	try
   		{
   			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
   		}
   		catch(Exception e)
   		{
   			e.printStackTrace();
   		}
    	
    	SplashScreen spls=new SplashScreen();
    }
}