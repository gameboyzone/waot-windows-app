import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.*;
import javax.imageio.*;
import java.sql.*;
import java.util.Date;

class SC extends Thread implements ActionListener
{
JFrame f;
JPanel p,p2,p3,p4,p5;
MenuItem mi1,mi2,mi3;
PopupMenu pm;
SystemTray st;
TrayIcon ti;
JTextField t3;
JComboBox cb,cb1,cb2,cb3,cb4;
JLabel l1,l2,l3,l4,l5,l6;
JButton b1,b2,b3;
String s1[]={"15","30","60","120","300","600"};
String s2[]={"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
String s3[]={"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
JFileChooser fc;	
Timer timer;
Toolkit kit;
Image img1,img2,img3;
JOptionPane jp;
int x1,y1,x2,y2,cp,cdt,tt1,tt;
int dt1,dt2,a;
int countdowntimer,i=0;
File fl;
String path,dtt;
MyCanvas mc;
Calendar calendar,cal1;
Date dt;
	
	SC() 
	{
	f=new JFrame("Screen Capturing utility");
	mc=new MyCanvas();
	p=new JPanel();	
    p2=new JPanel();
    p3=new JPanel();
    p4=new JPanel();
    p5=new JPanel();
    cb=new JComboBox(s1);
    cb1=new JComboBox(s2);
    cb2=new JComboBox(s3);
    cb3=new JComboBox(s2);
    cb4=new JComboBox(s3);    
    l1=new JLabel("From:");
    l2=new JLabel("To:");
    l3=new JLabel("Seconds");
    l4=new JLabel("System Clock",JLabel.CENTER);
    l5=new JLabel("Output:");
    t3=new JTextField(32);
    b1=new JButton("Schedule");
    b2=new JButton("Cancel");
    b3=new JButton("Browse");
 	fc=new JFileChooser();
 	jp=new JOptionPane();
 	mi1=new MenuItem("Display Main Screen");
	mi2=new MenuItem("Stop Capturing and Exit to System");
	mi3=new MenuItem("About W.A.O.T.");
 	kit=Toolkit.getDefaultToolkit();	
 	img1=kit.getImage("images\\topsc.jpg");
 	img2=kit.getImage("images\\bottomsc.jpg");
 	img3=kit.getImage("images\\sticonsc.jpg");
 	st=SystemTray.getSystemTray();
 	pm=new PopupMenu();
 	ti=new TrayIcon(img3,"Screen Capturing utility",pm);
 	
 	pm.add(mi1);
 	pm.add(mi2);
 	pm.add(mi3);
    p2.add(l4);
    p3.add(l1);
    p3.add(cb1);
    p3.add(cb2);
    p3.add(l2);
    p3.add(cb3);
    p3.add(cb4);
    p4.add(cb);
    p4.add(l3);
    p5.add(l5);
    p5.add(t3);
    p5.add(b3);

	p.add(mc);
    p.add(p2);
    p.add(p3);
    p.add(p4);
    p.add(p5);
    p.add(b1);
    p.add(b2);
    f.setContentPane(p);
    
    f.setLayout(null);
    p.setLayout(null);
    p5.setLayout(new FlowLayout(FlowLayout.LEFT));
    
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
    mi1.addActionListener(this);
    mi2.addActionListener(this);
    mi3.addActionListener(this);
    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e11)
    {
       	new Main();
       	
       	f.dispose();
    }});
    
    f.setSize(600,500);
    f.setVisible(true);
    f.setLocationRelativeTo(null);
    f.setResizable(false);
    mc.setSize(600,192);
    mc.setVisible(true);
    ti.setImageAutoSize(true);
    t3.setEditable(false);
    
    l4.setPreferredSize(new Dimension(105,40));
    l4.setForeground(Color.GRAY);
    l4.setFont(new Font("Comic Sans MS",Font.BOLD,16));
    
    p2.setBorder(BorderFactory.createTitledBorder(" System Clock "));
    p3.setBorder(BorderFactory.createTitledBorder(" Set Time(In 24 Hrs. format) "));
    p4.setBorder(BorderFactory.createTitledBorder(" Select Time Interval "));
    p5.setBorder(BorderFactory.createTitledBorder(" Select Ouput Folder "));
    
    p2.setBounds(410,210,180,80);
    p3.setBounds(10,210,390,80);
    p4.setBounds(10,300,160,80);
    p5.setBounds(180,300,400,80);
    b1.setBounds(190,400,80,35);
    b2.setBounds(310,400,80,35);
    
    l1.setDisplayedMnemonic('F');
    l1.setLabelFor(cb1);
    l2.setDisplayedMnemonic('T');
    l2.setLabelFor(cb3);
    l3.setDisplayedMnemonic('S');
	l3.setLabelFor(cb);
	b1.setMnemonic('S');
	b1.setToolTipText("Click to commit the above mentioned details");
    b2.setMnemonic('C');
    b2.setToolTipText("Click to go back to the main screen");
    b3.setMnemonic('B');
    b3.setToolTipText("Click to set the Output Folder for the captured images");
    
    f.repaint();
    f.validate();
    p2.repaint();
    p2.validate();

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
    			dt=new Date();
    				
    			dtt=dt.getHours()+" : "+dt.getMinutes()+" : "+dt.getSeconds();
				
				l4.setText(dtt.trim());
				
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
    	
    	if(e.getSource()==b1)
    	{
     	
     		//If textfield is empty
     	
     		if(t3.getText().trim().equals(""))
     		{
     			System.out.println("TextField Empty");
     			jp.showMessageDialog(f,"Output Folder location EMPTY !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
     		}
     		  
     		//If all right then show status and send to the SystemTray
     		
     		else
     		{
     		String sc1=(String)cb1.getSelectedItem(); 
     		String sc2=(String)cb2.getSelectedItem();
     		String sc3=(String)cb3.getSelectedItem(); 
     		String sc4=(String)cb4.getSelectedItem();
     		String ct=(String)cb.getSelectedItem();
     		      
     	   	  //Start reading values of Combo boxes ad SC algorithm 
     			
     		 x1=Integer.parseInt(sc1);
			 y1=Integer.parseInt(sc2);
			 x2=Integer.parseInt(sc3);
			 y2=Integer.parseInt(sc4);
		 	 cp=Integer.parseInt(ct);
		  	
		  	System.out.println("Initial time Hrs:"+x1+"Mins:"+y1);
		  	System.out.println("Ending time Hrs:"+x2+"Mins:"+y2);
		  	
		  	dt1=0;
		  	dt2=0;		  	
      	  
      	 	if((x2==x1)&&(y2>=y1))
			{
    			System.out.println("Delay in Hours:"+(dt1=0));
    			System.out.println("Delay in minutes :"+(dt2=(y2-y1)));
			}
			
			else if((x2==x1)&&(y2<y1))
			{
	   			System.out.println("Delay in Hours:"+(dt1=23));
    			System.out.println("Delay in minutes :"+(dt2=(60-(y1-y2))));
			}
			
			else if((x2>x1)&&(y2>=y1))
			{
    			System.out.println("Delay in Hours:"+(dt1=(x2-x1)));
    			System.out.println("Delay in minutes :"+(dt2=(y2-y1)));	
			}
			
			else if((x2>x1)&&(y2<y1))
			{
				System.out.println("Delay in Hours:"+(dt1=((x2-x1)-1)));
    			System.out.println("Delay in minutes :"+(dt2=(60-(y1-y2))));
			}
			
			else if((x2<x1)&&(y2>=y1))
			{
				System.out.println("Delay in Hours:"+(dt1=(24-(x1-x2))));
    			System.out.println("Delay in minutes :"+(dt2=(y2-y1)));
			}
			
			else if((x2<x1)&&(y2<y1))
			{
				System.out.println("Delay in Hours:"+(dt1=(24-(x1-x2)-1)));
    			System.out.println("Delay in minutes :"+(dt2=(60-(y1-y2))));
			}	
      	
      	  int tt1;
      	  int tt;
      	  
       	  System.out.println("Total delay in minutes: "+ (tt=((dt1*60)+dt2))); 
       	  System.out.println("Interval for Screenshots: "+cp);
       	  
       	  System.out.println("Delay in seconds :"+(tt1=(tt)*60));
       	  System.out.println("Countdown timer :"+(cdt=(tt1/cp)));
       	  
       	  
       	  calendar = Calendar.getInstance();       	  
       	  int tempdt=calendar.get(Calendar.DAY_OF_MONTH);
       	  
       	  if(x1<calendar.get(Calendar.HOUR_OF_DAY))
       	  {
       	  	calendar.set(Calendar.DAY_OF_MONTH,tempdt+1);
       	  }
       	  
       	  else if(x1==calendar.get(Calendar.HOUR_OF_DAY))
       	  {
       	  	if(y1<=calendar.get(Calendar.MINUTE))
       	  	{
       	  		calendar.set(Calendar.DAY_OF_MONTH,tempdt+1);
       	  	}
       	  }
       	  
       	  calendar.set(Calendar.HOUR_OF_DAY,x1);
		  calendar.set(Calendar.MINUTE,y1);
		  calendar.set(Calendar.SECOND,0);
		  Date time = calendar.getTime();	  		
			
		  System.out.println("Time :" +time);
	      timer = new Timer();
		  timer.schedule(new CaptureTest(),time,cp*1000);
       	  
       	  // Display JOption pane showing all committed details
     	  jp.showMessageDialog(f,"<html><body><ul><li> Request for Screen Capturing acknowledged from <b>"+sc1+":"+sc2+"</b> to <b>"+sc3+":"+sc4+"</b> with an interval of <b>"+ct+"</b> seconds.<br><li> The utility will now be <b>minimized</b> to the <b>System Tray</b>.<br><li> To <b>stop</b> the capturing, terminate the process from the <b>System Tray</b></body></html>.","Note !!!",JOptionPane.INFORMATION_MESSAGE);
     		
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
     	   
      if(e.getSource()==b2)
      {
      	//Show the main screen
      	new Main();
      	
      	//Dispose the SC JFrame
      	f.dispose();
      }
      
      
     if(e.getSource()==b3)
     {
     		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
     	
     		a=fc.showSaveDialog(f);	 
		
			fl=fc.getSelectedFile();
			
			if(a==JFileChooser.APPROVE_OPTION)
			{
				if(fl.isDirectory())
				{
					path=fl.getPath();
					
    				t3.setText(path);
				}
			}
     }
     
     if(e.getSource()==mi1)
     {
     	//Display User Auth and then Main Screen
      	
      	new UserAuth(1);
     }      
     	
     if(e.getSource()==mi2)
     {
     	//Display the UserAuth window and then Exit to System
     	
     	new UserAuth(2);
     }  
     	
     if(e.getSource()==mi3)
     {
     	//Display the About Screen
     	
      	new About();
     }  		
     	
     }
      
	class CaptureTest extends TimerTask 
	{
		
	  public void run()
	  {	
	  Toolkit kit;
	  BufferedImage img;
	  Rectangle rt; 
	  Dimension d;
	  File file;

			kit=Toolkit.getDefaultToolkit();
			d=kit.getScreenSize();
			rt=new Rectangle(kit.getScreenSize());
	
			//int countdowntimer=cdt;
			 
			 if(cdt>=0)
			 {
			 	System.out.println("Countdowntimer: "+ cdt);		
				
				try
				{
						
						Robot r=new Robot();
						img=r.createScreenCapture(rt);
			
						//Save the screenshot as a jpg
						System.out.println("Output Folder Path in Inner Class: "+path);
						
						cal1=Calendar.getInstance();
						int thrs=cal1.get(Calendar.HOUR_OF_DAY);
						int tmin=cal1.get(Calendar.MINUTE);
						int tsec=cal1.get(Calendar.SECOND);
						//file = new File(path+"/screen"+i+".jpg");
						//file = new File(path+"/"+thrs+tmin+"hrs and "+tsec+"secs.jpg");
						file = new File(path+"/"+thrs+"-"+tmin+"-"+tsec+".jpg");
						ImageIO.write(img,"jpg",file);
						cdt--;
					
				}
		
				catch(Exception e)
				{
					e.printStackTrace();
				}
	
			 }
			 
		 	else
			{
			 	System.out.println("Time's UP!!");
			 	timer.cancel();
			 	
			 	// At this time the job is completed and SystemTray will persist.		 	
			}
	  }	 	
    
	}
		
    public static void main(String[] args) 
    {
    	try 
    	{
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e8)
        {
        	e8.printStackTrace();
        }
	
        SC scg=new SC();
    }
}