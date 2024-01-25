import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MScan implements ActionListener,ItemListener 
{
JFrame f;
JPanel p,p1,p2,p3,p4;
JCheckBox cb1,cb2,cb3,cb4;
JButton b1,b2,b3;
JTextField t1;
JRadioButton r1;
JComboBox cbx;
ButtonGroup bg;
JOptionPane jp;
JFileChooser fc;
Toolkit kit;
Image img1,img2;       
MyCanvas mc;
Process ps1,ps2,ps3,ps4;
BufferedReader BR;
InputStreamReader ISR;
String command1[],command2[],command3[],command4[],options[]={"Yes","No"},ld[];
String s1,s2,rlocation,tlocation,x="/f",y="/r",z="/x",s5;
File fl1,fl2[];
FileWriter fw;
int i,a,b,c,statusbit,gt=1;
    
   	MScan()
    {
    	//Logic of calculating all local drives
    	try
    	{
    		fl2=File.listRoots();
    		ld=new String[fl2.length];
    		
    		for(i=0;i<fl2.length;i++)
    		{	
    			ld[i]=fl2[i].toString().substring(0,3);
    			System.out.println(ld[i]);				//Comment this later
    		}
    		
    	}
    	catch(Exception e2)
    	{
    		e2.printStackTrace();
    	}
    	
    f=new JFrame("Manual Scandisk Utility");
    p=new JPanel();
    p1=new JPanel();
    p2=new JPanel();
    p3=new JPanel();
    p4=new JPanel();	
    cb1=new JCheckBox("Perform Scandisk",true);
    cb2=new JCheckBox("Fix the Errors",true);
    cb3=new JCheckBox("Recover lost file fragments",true);
    cb4=new JCheckBox("Generate",true);	
    t1=new JTextField(40);
    b1=new JButton("Browse");
    b2=new JButton("Schedule");
    b3=new JButton("Cancel");
    cbx=new JComboBox(ld);
    bg=new ButtonGroup();
    r1=new JRadioButton("Select Drive:",true);
    fc=new JFileChooser();
    jp=new JOptionPane();
    mc=new MyCanvas();
    kit=Toolkit.getDefaultToolkit();
    img1=kit.getImage("images/topms.JPG");
	img2=kit.getImage("images/bottomms.JPG");
      
    p.setLayout(null);
    p1.setLayout(new FlowLayout(FlowLayout.LEFT));
    p2.setLayout(new FlowLayout(FlowLayout.LEADING,15,0));
    
    bg.add(r1);
    p1.add(cb1);
    p1.add(cb2);
    p1.add(cb3);
    p2.add(cb4);
    p2.add(p3);
    p3.add(t1);
    p3.add(b1);
    p4.add(r1);
    p4.add(cbx);
    p.add(mc);
    p.add(p1);
    p.add(p4);
    p.add(p2);
    p.add(b2);
    p.add(b3);
    f.setContentPane(p);
    
    cb1.addItemListener(this);
    cb2.addItemListener(this);
    cb3.addItemListener(this);
    cb4.addItemListener(this);
    r1.addItemListener(this);
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e11)
    {
    	new Main();
    	
    	f.dispose();
    }});
    
    f.setSize(600,500);
    f.setVisible(true);
    f.setResizable(false);
    f.setLocationRelativeTo(null);
    
    mc.setBounds(0,0,600,192);
    mc.setVisible(true);
    cb1.setToolTipText("Perform mandatory Scandisk operation");
    t1.setEnabled(false);
    b1.setMnemonic('B');
    b2.setMnemonic('S');
    b3.setMnemonic('C');
    cb4.setMnemonic('G');
    p3.setPreferredSize(new Dimension(430,70));
    
    p1.setBorder(BorderFactory.createTitledBorder(" Select your Options: "));
    p2.setBorder(BorderFactory.createTitledBorder(" Generate status report: "));
    p3.setBorder(BorderFactory.createTitledBorder(" Status report location: "));
    p4.setBorder(BorderFactory.createTitledBorder(" Target Area: "));
    
    p1.setBounds(5,195,390,70);
    p4.setBounds(400,195,180,70);
    p2.setBounds(5,280,570,110);
    b2.setBounds(170,410,80,30);
    b3.setBounds(350,410,80,30);
    
    p.repaint();
    p.validate();
    f.repaint();
    f.validate();
    }
    
    public class MyCanvas extends Canvas
    {
    	public void paint(Graphics g)
    	{
    		g.drawImage(img1,0,0,this);
    		g.drawLine(0,90,600,90);
    		g.drawImage(img2,0,91,this);
    		g.drawLine(0,191,600,191);
    	}
    }
   
    public void actionPerformed(ActionEvent e2)
    {
   		if(e2.getSource()==b1)							//File Report Location Browse Button
   		{
   			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
   			
   			a=fc.showOpenDialog(f);
   			
   			if(a==JFileChooser.APPROVE_OPTION)
   			{
   				fl1=fc.getSelectedFile();
    			t1.setText(fl1.getPath());
   			}
   		}
   		
   		if(e2.getSource()==b2)							//Schedule Button
   		{	
   			//Target Area validation
   			
   			if(r1.isSelected())
   			{
   				tlocation=cbx.getSelectedItem().toString().trim();
   			}
   			
   			//Generate report validation
   			
   			if(cb4.isSelected())
   			{
   				gt=1;
   				rlocation=t1.getText();
   			}
   			else
   			{
   				gt=0;
   			}
   			
   			// If Status Report Location Field Empty
   			
   			if((cb4.isSelected())&&(t1.getText().trim().equals("")))
   			{
   				jp.showMessageDialog(f,"Status Report Location Empty !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
   			}
   			
   			
   			//If all conditions checked then execute the logic
   			else
   			{
   				// Get the Home drive and check if the selected one is the same then throw a warning regarding the restriction.
   				
   				try
   				{
   					command1 =new String[]{"cmd","/k","echo","%HOMEDRIVE%"};
   					
   					ps1=Runtime.getRuntime().exec(command1);
   				
   					ISR=new InputStreamReader(ps1.getInputStream());
   					
   					BR=new BufferedReader(ISR);
   					
   					s1=BR.readLine();
   					
   					s1=s1+"\\";
   					
   					System.out.println("Home Drive: "+s1);
   				
   					//If the selected area and the primary partition is same.
   					
   					if(s1.equals(tlocation))
   					{
   						statusbit=0;
   						
   						System.out.println("Status Bit: "+statusbit);
   						
   						c=jp.showOptionDialog(f,"<html><ul><li>You've selected the <b>primary partition</b> as the target area.<br><li>Fixing the errors and recovering lost file-fragments is <b>not possible</b> with the Primary Partition.<br><li>The TARGET AREA will only be scanned.<br><li>The utility may become <b>unresponsive</b> for some time during the scan.</ul><br><br>Do you want to continue...?</html>"," Choose your Option: ",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
   						
   						if(c==JOptionPane.OK_OPTION)
   						{
   							new Main();
   							
   							f.dispose();
   							
   							new Output(tlocation,statusbit);
   						}
   					}
   					
   					//If the selected area is not the primary partition
   					
   					else
   					{
   						statusbit=1;
   						
   						System.out.println("Status Bit: "+statusbit);
   						
   						c=jp.showOptionDialog(f,"<html><ul><li>Your selected TARGET AREA is: "+tlocation+"<br><li>All open handles to this drive will be void and the drive will be <b>dismounted</b>.<br><li>Please <b>save</b> any files/programs opened from the indented drive else you might loose some unsaved work.<br><li>The utility may become <b>unresponsive</b> for some time during the scan.<br><li>You can terminate the scan midway by pressing the <b>QUIT</b> button in the following dialog.</ul><br><br>Do you want to continue...?</html>"," Choose your Option: ",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
   						
   						if(c==JOptionPane.OK_OPTION)
   						{
   							new Main();
   							
   							f.dispose();
   							
   							new Output(tlocation,statusbit);
   						}
   					}
   				}
   			
   				catch(Exception e)
   				{
   					e.printStackTrace();
   				}
   				
   			}
   		}
   		
   		if(e2.getSource()==b3)							//Cancel Button
   		{
   			//Dispose the JFrame and show the Main Screen
   			
   			new Main();
   			
   			f.dispose();
   			
   		}
   		
    }
    
    public void itemStateChanged(ItemEvent e4)
    {
    	//Keep cb1 i.e. Perform Scan-disk always checked
    	
    	if(e4.getSource()==cb1)
    	{
    		cb1.setSelected(true);
    	}
    	
    	if(e4.getSource()==cb2)
    	{
    		if(cb2.isSelected()==true)
    		{
    			x="/f";
    		}
    		else
    		{
    			x="";
    		}
    	}
    	
    	if(e4.getSource()==cb3)
    	{
    		if(cb3.isSelected()==true)
    		{
    			y="/r";
    		}
    		else
    		{
    			y="";
    		}
    	}
    	
    	if(e4.getSource()==cb4)
    	{
    		if(cb4.isSelected()==true)
    		{
    			b1.setEnabled(true);
    			gt=1;
    		}
    		else
    		{
    			b1.setEnabled(false);
    			gt=0;
    			t1.setText("");
    		}
    	}
    	
    	if(r1.isSelected())
   		{
   			cbx.setEnabled(true);
   		}
   		
    }

public class Output implements ActionListener
{
JDialog jd;
JPanel pd;
JButton jb1,jb2;
JTextArea ta;
JScrollPane sp;
SystemTray st;
TrayIcon ti;
Image img;
Toolkit kit;
PopupMenu pm;
JProgressBar jpb;
MenuItem mi1,mi2,mi3,mi4;
StringBuffer sb;
Timer t;
int bit=0;

	Output(String tlocation,int statusbit) 
	{
	jd=new JDialog();
	pd=new JPanel();
	jb1=new JButton("Minimize to System Tray");
	jb2=new JButton("Quit");
	ta=new JTextArea(5,20);
	sp=new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	pm=new PopupMenu();
	mi1=new MenuItem("Display Main Screen");
	mi2=new MenuItem("Stop Manual Scandisk Utility and Exit to System");
	mi3=new MenuItem("About W.A.O.T.");
	mi4=new MenuItem("Show the Manual Scandisk Progress Dialog");
	jpb=new JProgressBar(0,100);
	st=SystemTray.getSystemTray();
	kit=Toolkit.getDefaultToolkit();
	img=kit.getImage("images\\stms.jpg");
	ti=new TrayIcon(img,"Manual Scandisk Utility",pm);
	sb=new StringBuffer();
	
	jpb.setStringPainted(true);
	jpb.setString("In Progress...");
	jpb.setIndeterminate(true);
	
	pd.setLayout(null);
	
	pm.add(mi4);
	pm.add(mi1);
 	pm.add(mi2);
 	pm.add(mi3);
 	pd.add(sp);
	pd.add(jpb);
	pd.add(jb1);
	pd.add(jb2);
	jd.setContentPane(pd);
	
	mi1.addActionListener(this);
    mi2.addActionListener(this);
    mi3.addActionListener(this);
    mi4.addActionListener(this);
	jb1.addActionListener(this);
	jb2.addActionListener(this);
	jd.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	
	jd.setSize(450,350);
	jd.setVisible(true);
	jd.setTitle("Scandisk: In Progress....");
	jd.setLocationRelativeTo(null);
	jd.setResizable(false);
	
	ti.setImageAutoSize(true);
	ta.setText("[-------------- Progress Report ---------------]\n");
		
	sp.setBounds(5,5,435,220);
	jpb.setBounds(65,240,300,25);
	jb1.setBounds(5,280,150,30);
	jb2.setBounds(370,280,70,30);
	
	jb1.setMnemonic('M');
	jb2.setMnemonic('Q');
	ta.setEditable(false);
	   
    	//Case 1 when the TARGET AREA is the primary partition
    
    	if(statusbit==0)
    	{
    	System.out.println("Checkpoint statusbit 0 entered");	
    		
    		try
    		{
    		command2=new String[]{"chkdsk",tlocation.substring(0,2)};
    		
    			for(int i=0;i<command2.length;i++)
    			{
    				System.out.println(command2[i]);
    			}
    		
    			ps1=Runtime.getRuntime().exec(command2);
    			ISR=new InputStreamReader(ps1.getInputStream());
    			BR=new BufferedReader(ISR);
    			
    			t=new Timer(6000,new ActionListener()
    			{
    				public void actionPerformed(ActionEvent e2)
    				{	
    					try
    					{
    						s5=BR.readLine();
    						
    							if(s5==null)
    							{
    								t.stop();
    								bit=1;
    								jpb.setIndeterminate(false);
    								jpb.setValue(100);
    								jpb.setString("Scandisk Completed");
    								
    								//If gt=1 then print Report
    								if(gt==1)
    								{
    									try
    									{
    										fw=new FileWriter(rlocation+"\\Report.txt");
											fw.write(sb.toString());
											fw.close();	
    									}
    									catch(Exception e4)
    									{
    										e4.printStackTrace();
    									}
    								}
    								else
    								{
    									//If gt=0 then don't print Report
    								}
    							}
    							else
    							{	
    								sb.append(s5+"\n");
    							
    								ta.append(s5+"\n");
    								
    								//System.out.println(sb);
    							}
    					}
    					catch(Exception e4)
    					{
    						e4.printStackTrace();
    					}
    				}
    			});
    			
    			if(bit==0)
    			{
    				t.start();
    			}
    			
    		}
    		catch(Exception e8)
    		{
    			e8.printStackTrace();
    		}
 
    	}		//Case 1 closed
    	
    	//Usual case 2 when the TARGET AREA in not the primary partition
    	
    	if(statusbit==1)
    	{
    	System.out.println("Checkpoint statusbit 1 entered");
    	
    		try
    		{
    			command2=new String[]{"chkdsk",tlocation.substring(0,2),x,y,z};
    			
    			for(int i=0;i<command2.length;i++)
    			{
    				System.out.println(command2[i]);
    			}
    		
    			ps2=Runtime.getRuntime().exec(command2);
    			ISR=new InputStreamReader(ps2.getInputStream());
    			BR=new BufferedReader(ISR);
    			
    			t=new Timer(6000,new ActionListener()
    			{
    				public void actionPerformed(ActionEvent e2)
    				{	
    					try
    					{
    						s5=BR.readLine();
    						
    							if(s5==null)
    							{
    								t.stop();
    								bit=1;
    								jpb.setIndeterminate(false);
    								jpb.setValue(100);
    								jpb.setString("Scandisk Completed");
    								
    								//If gt=1 then print Report
    								if(gt==1)
    								{
    									try
    									{
    										fw=new FileWriter(rlocation+"\\Report.txt");
											fw.write(sb.toString());
											fw.close();	
    									}
    									catch(Exception e4)
    									{
    										e4.printStackTrace();
    									}
    								}
    								else
    								{
    									//If gt=0 then don't print Report
    								}
    							}
    							
    							else if(s5.equals("Cannot open volume for direct access."))
    							{
    							//Show the error message on the JTextArea if the Target Driveis Floppy Drive,CD-ROM
    							
    							ta.append(s5);
    							ta.append("\n\nThe TARGET DRIVE may either be a Floppy/CD-ROM/Virtual Drive which is not permissible.");
    							
    							t.stop();
    							bit=1;
    							jpb.setIndeterminate(false);
    							jpb.setValue(100);
    							jpb.setString("Scandisk Completed");
    							}
    							
    							else	
    							{
    								sb.append(s5+"\n");
    							
    								ta.append(s5+"\n");
    								
    								//System.out.println(sb);
    							}
    					}
    					catch(Exception e4)
    					{
    						e4.printStackTrace();
    					}
    				}
    			});
    			
    			if(bit==0)
    			{
    				t.start();
    			}
    			
    		}
    		catch(Exception e8)
    		{
    			e8.printStackTrace();
    		}
    	
    	}		//Case 2 closed
    
}								//Constructor closed
    
    public void actionPerformed(ActionEvent e2)
    {
    	if(e2.getSource()==jb1)			//Minimize to System Tray
    	{
    	jd.dispose();
    	
    		try
    		{
    			st.add(ti);
    		}
    		catch(Exception e6)
    		{
    			e6.printStackTrace();
    		}
    	}
    	
    	if(e2.getSource()==jb2)				//QUIT Button
    	{
    		c=jp.showOptionDialog(f,"<html><body>You are about to <b>EXIT</b> a manual Scandisk which is <b>IN PROGRESS</b>.<br><br><b>Are you sure that you want to QUIT ?</b></body></html>"," Are you sure that you want to QUIT ? ",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[0]);
   						
   			if(c==JOptionPane.OK_OPTION)
   			{
   				bit=1;
   				
   				t.stop();
   				
   				jd.dispose();
   				   				
   				if(statusbit==0)
   				{
   					ps1.destroy();
   				}
   				else
   				{
   					ps2.destroy();
   				}
   				
   				//Remove the TrayIcon from the SystemTray if present
   				try
   				{
   					st.remove(ti);
   				}
   				catch(Exception e12)
   				{
   					e12.printStackTrace();
   				}
   				
   				//System.exit(0);		//Causes serious issues since it exits all running utilities
   			}
    	}
    	
    	if(e2.getSource()==mi1)
     	{
     		//Verify UName & Pword
    	  	
      		new UserAuth(1);
     	} 	     
     		
     	if(e2.getSource()==mi2)
     	{	
    	 	//Display the Dialog Box for authentication and then Exit
     		
     		new UserAuth(2);
     	}  	
    	 			
    	if(e2.getSource()==mi3)
    	{		
    	 	//Display the About W.A.O.T. Screen
   		  	
   		   	new About();
    	}
    	
    	if(e2.getSource()==mi4)
    	{
    		jd.setVisible(true);
    	}
    }
}						//Inner class Closed
   
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
    	
        MScan msn=new MScan();
    }
}