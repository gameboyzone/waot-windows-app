import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.*;
import java.util.Date;

class SH extends Thread implements ItemListener,ActionListener
{
JFrame f;
SystemTray st;
TrayIcon ti;
PopupMenu pm;
MenuItem mi1,mi2,mi3;
JPanel p,p1,p2,p3,p4,p5,p6;
JComboBox cb1,cb2;
JRadioButton r1,r2,r3,r4,r5;
ButtonGroup bg1,bg2;
JTextField t;
JButton bt1,bt2;
JLabel l;
JOptionPane jp;
String s1[]={"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
String s2[]={"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
MyCanvas mc;
Date dt1,dt2;
String dtt;
Toolkit kit;
Timer timer1;
Calendar calendar,calendar1;
Process pcs;
Image img1,img2,img3;
String a,b="-c",v1,v2,msg,status="Shutdown";
int bit=0,b1,b2,onlyonce=0;
Robot r;

    SH()
    {
    f=new JFrame("Time-based Shutdown/Restart/LogOff utility");
    mc=new MyCanvas();
    p=new JPanel();
    p1=new JPanel();
    p2=new JPanel();
    p3=new JPanel();
    p4=new JPanel();
    p5=new JPanel();
    p6=new JPanel();
    t=new JTextField(30);
    cb1=new JComboBox(s1);
    cb2=new JComboBox(s2);
    r1=new JRadioButton("Shutdown",true);
    r2=new JRadioButton("Restart");
    r3=new JRadioButton("Log Off");
    r4=new JRadioButton("With alert message displayed before 30 seconds",true);
    r5=new JRadioButton("Without any alert message");
	bg1=new ButtonGroup(); 
	bg2=new ButtonGroup();  
	bt1=new JButton("Schedule");
	bt2=new JButton("Cancel");
	l=new JLabel();
	jp=new JOptionPane();
	kit=Toolkit.getDefaultToolkit();
	st=SystemTray.getSystemTray();
 	pm=new PopupMenu();
 	mi1=new MenuItem("Display Main Screen");
	mi2=new MenuItem("Stop Shutdown/Restart/LogOff and Exit to System");
	mi3=new MenuItem("About W.A.O.T.");
 	img3=kit.getImage("images\\SHIcon.JPG");
 	ti=new TrayIcon(img3,"Time-based Shutdown/Restart/LogOff utility",pm);	
 	img1=kit.getImage("images\\topsh.JPG");
 	img2=kit.getImage("images\\bottomsh.JPG");	
    
    pm.add(mi1);
 	pm.add(mi2);
 	pm.add(mi3);
    bg1.add(r1);
    bg1.add(r2);
    bg1.add(r3);
    bg2.add(r4);
    bg2.add(r5);
    
    p1.add(r1);
    p1.add(r2);
    p1.add(r3);
    p2.add(cb1);
    p2.add(cb2);
    p3.add(r4);
    p3.add(r5);
    p4.add(t);
    p5.add(bt1);
    p5.add(bt2);
    p6.add(l);
    p.add(mc);
    p.add(p1);
    p.add(p2);
    p.add(p3);
    p.add(p4);
    p.add(p5);
    p.add(p6);
    f.setContentPane(p);
    
    f.setLayout(null);
    p.setLayout(null);
    
    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e11)
    {
       	new Main();
       	
       	f.dispose();
    }});
    
    mi1.addActionListener(this);
    mi2.addActionListener(this);
    mi3.addActionListener(this);
    bt1.addActionListener(this);
    bt2.addActionListener(this);
    r1.addItemListener(this);
    r2.addItemListener(this);
    r3.addItemListener(this);
  	r4.addItemListener(this);
  	r5.addItemListener(this);
    
    f.setSize(600,500);
    f.setVisible(true);
    f.setLocationRelativeTo(null);
    f.setResizable(false);
    
    mc.setSize(600,192);
    mc.setVisible(true);
    l.setForeground(Color.GRAY);
    l.setFont(new Font("Comic Sans MS",Font.BOLD,14));
    ti.setImageAutoSize(true);
    bt1.setMnemonic('S');
    bt2.setMnemonic('C');
    r1.setMnemonic('h');
    r2.setMnemonic('R');
    r3.setMnemonic('L');
    r4.setMnemonic('W');
    r5.setMnemonic('a');
    	
    p1.setBorder(BorderFactory.createTitledBorder(" Choose your Option: "));
    p2.setBorder(BorderFactory.createTitledBorder(" Set Time(In 24 Hrs. format) "));
    p3.setBorder(BorderFactory.createTitledBorder(" Execute the operation: "));
    p4.setBorder(BorderFactory.createTitledBorder(" Enter alert Message: "));
    p6.setBorder(BorderFactory.createTitledBorder(" System Clock: "));
    
    p1.setBounds(10,200,280,60);
    p2.setBounds(298,200,145,60);
    p3.setBounds(40,270,500,60);
    p4.setBounds(80,335,400,60);
    p5.setBounds(80,420,400,60);
    p6.setBounds(450,200,135,60);
    
    f.repaint();
    f.validate();
    p.repaint();
    p.validate();  	
    
    start();	
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
    
    public void run()
    {
    	while(true)
    	{	
    		try
    		{
    			dt1=new Date();
    				
    			dtt=dt1.getHours()+" : "+dt1.getMinutes()+" : "+dt1.getSeconds();
				
				l.setText(dtt.trim());
				
				sleep(1000);
			}
    		
    		catch(Exception e9)
    		{
    			e9.printStackTrace();
    		}
    	}
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource()==bt1)					//Schedule Button
    	{
    		v1=cb1.getSelectedItem().toString();
    		v2=cb2.getSelectedItem().toString();
			b1=Integer.parseInt(v1);
			b2=Integer.parseInt(v2);
			
			if((t.getText().trim().equals(""))&&(bit==0))
			{
				jp.showMessageDialog(f,"Alert Message missing !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
			}
			
			else
			{	
				if(r1.isSelected())
				{
					a="-s";
					status="Shutdown";
				}
				
				else if(r2.isSelected())
				{
					a="-r";
					status="Restart";
				}
					
				else if(r3.isSelected())
				{
					a="-l";
					status="LogOff";
				}
				
				if(r4.isSelected())
				{
					b="-c";
					msg=t.getText();
				}
				
				else if(r5.isSelected())
				{
					b="";
					msg="";
				}		
				
				calendar=Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY,b1);
				calendar.set(Calendar.MINUTE,b2-1);		//One minute prior
				calendar.set(Calendar.SECOND,30);		//And alert set for 30 secs.
								
				calendar1 = Calendar.getInstance();       	  
       	  		int tempdt=calendar.get(Calendar.DAY_OF_MONTH);
       	  
       	  		if(b1<calendar1.get(Calendar.HOUR_OF_DAY))
       	  		{
       	  			calendar.set(Calendar.DAY_OF_MONTH,tempdt+1);
       	  		}
       	  
       	  		else if(b1==calendar1.get(Calendar.HOUR_OF_DAY))
       	  		{
       	  			if(b2<=calendar1.get(Calendar.MINUTE))
       	  			{
       	  				calendar.set(Calendar.DAY_OF_MONTH,tempdt+1);
       	  			}
       	  		}
       	  		
       	  		dt2=calendar.getTime();		//Here dt2 is the date set
				
				timer1=new Timer();
				timer1.schedule(new RemindTask(),dt2);
				
				if(r3.isSelected())
    				{
    					bit=1;
    				}	
					
				jp.showMessageDialog(f,"<html><body><ul><li>  <b>"+status+"</b> scheduled at <b>"+v1+" : "+v2+"</b><br><li>  The utility will now be <b>minimized</b> to the <b>System Tray</b>.<br><li>  To <b>STOP</b> the capturing, terminate the process from the <b>System Tray</b></body></html>.","Note !!!",JOptionPane.INFORMATION_MESSAGE);
				
				try
     	      	{
     	      		st.add(ti);
     	      	}
     	      	catch(Exception e10)
     	      	{
     	      		e10.printStackTrace();
     	      	}
     	      	
     	      	//Show Main Screen
     	      
     	      	new Main();
     	      	
     	      	f.dispose();
    		}   		
    		
    	}
    	
    	if(e.getSource()==bt2)						//Cancel Button
    	{
    		//Show the main screen
    		new Main();
    		
    		
      		//Dispose the SH JFrame
      		f.dispose();
    	}
    	
    	if(e.getSource()==mi1)
     	{
     		//Display Main Screen
    	  	
      		new UserAuth(1);
     	} 	     
     		
     	if(e.getSource()==mi2)
     	{	
    	 	//Display the Dialog Box for authentication and then Exit
     	
     		new UserAuth(2);
     	}  	
    	 			
    	if(e.getSource()==mi3)
    	{		
    	 	//Display the About W.A.O.T. Screen 
    	 	
   		   	new About();
    	}
     	
    }
    
    public void itemStateChanged(ItemEvent e4)
    {
    	if(e4.getSource()==r3)
    	{
    		if(onlyonce==0)
    		{
    			jp.showMessageDialog(f,"Alert message cannot be given using the Log Off option !!!","Note !!!",JOptionPane.WARNING_MESSAGE);
    		}
    		t.setText("");
    		r5.setSelected(true);
    		t.setEnabled(false);
    		r4.setEnabled(false);
    		onlyonce=1;
    	}
    	
    	else if(e4.getSource()==r1||e4.getSource()==r2)
    	{
    		t.setEnabled(true);
    		r4.setEnabled(true);
    		r4.setSelected(true);
    	}
    
    	if(e4.getSource()==r4)
    	{
    		t.setEnabled(true);
    		bit=0;
    	}
    	
    	if(e4.getSource()==r5)
    	{
    		t.setText("");
    		t.setEnabled(false);
    		bit=1;
    	}
    }
    
    public class RemindTask extends TimerTask
    {
    	public void run()
    	{
    		if(bit==0)
    		{
    			try
    			{
    				System.out.println("Checkpoint 1 reached");		//Comment this later
    				String command[]={"shutdown",a,"-t","30",b,msg};
    				pcs=Runtime.getRuntime().exec(command);
    				
    			}
    			catch(Exception e4)
    			{
    				e4.printStackTrace();
    			}
    				
    		}
    		    		   		
    		else if(bit==1)
    		{
    			try
    			{
    				System.out.println("Checkpoint 2 reached");		//Comment this later
    				
    				r=new Robot();
    				r.delay(28000);
    				
    				String command[]={"shutdown",a,"-t","00"};
    				pcs=Runtime.getRuntime().exec(command);
    			}
    			catch(Exception e5)
    			{
    				e5.printStackTrace();
    			}			
    		
    		}			
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
    	
    	SH shsh=new SH();
    }
}