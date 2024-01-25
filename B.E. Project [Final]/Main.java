import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Main implements ActionListener
{
JFrame f;
JPanel p,p1,p2,p3;
JButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
JTabbedPane jt;
JTextArea ta1,ta2;
JOptionPane jp;
JScrollPane sp1,sp2;
JDialog jd;
ImageIcon ic1,ic2,ic3,ic4,ic5,ic6,ic7,ic8,ic9,ic10;
Process ps;
InputStreamReader ISR;
BufferedReader BR;
MyCanvas mc;
Toolkit kit;
Image img1,img2;
String options[]={"Yes","No"},command[],s5;
int c;

	Main() 
	{
	f=new JFrame("Main Screen");
	jd=new JDialog();
    p=new JPanel();
    p1=new JPanel();
    p2=new JPanel();
    p3=new JPanel();
    jt=new JTabbedPane(JTabbedPane.TOP,JTabbedPane.WRAP_TAB_LAYOUT);
    jp=new JOptionPane();
    mc=new MyCanvas();
    ta1=new JTextArea(5,50);
    ta2=new JTextArea(5,50);
    sp1=new JScrollPane(ta1,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	sp2=new JScrollPane(ta2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	kit=Toolkit.getDefaultToolkit();
	img1=kit.getImage("images\\topmn.jpg");
	img2=kit.getImage("images\\bottomsh.jpg");
    ic1=new ImageIcon("Images\\1.GIF");
    ic2=new ImageIcon("Images\\2.GIF");
    ic3=new ImageIcon("Images\\3.GIF");
    ic4=new ImageIcon("Images\\SC.GIF");
    ic5=new ImageIcon("Images\\SL.GIF");
    ic6=new ImageIcon("Images\\SH.GIF");
    ic7=new ImageIcon("Images\\OPT.GIF");
    ic8=new ImageIcon("Images\\MScan.GIF");
    ic9=new ImageIcon("Images\\RWA.GIF");
    ic10=new ImageIcon("Images\\VD.GIF");
    b1=new JButton("Screen Capturing",ic4);
    b2=new JButton("Screen Locking",ic5);
    b3=new JButton("Time-based Shutdown",ic6);
    b4=new JButton("Optimization",ic7);
    b5=new JButton("Manual Scandisk",ic8);
    b6=new JButton("Restricting Applications",ic9);
    b7=new JButton("Virtual Drive",ic10);
    b8=new JButton("System Info.");
    b9=new JButton("About");
    
    p.setLayout(null);
    p1.setLayout(null);
    p2.setLayout(null);
    p3.setLayout(null);
    jd.setLayout(null);
    
    jd.add(sp2);
    p1.add(b1);
    p1.add(b2);
    p1.add(b3);
    p2.add(b4);
    p3.add(b5);
    p3.add(b6);
    p3.add(b7);
    p.add(mc);
    p.add(jt);
    p.add(sp1);
    p.add(b8);
    p.add(b9);
    f.setContentPane(p);
    
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
    b4.addActionListener(this);
    b5.addActionListener(this);
    b6.addActionListener(this);
    b7.addActionListener(this);
    b1.addMouseListener(new MouseAdapter(){public void mouseEntered(MouseEvent e){ta1.setText("Using the Time-based Screen Capturing utility:\n\n\t> You can take time based Screenshots of the entire screen.\n\t> Monitor the current activity of the user.\n\t> Get high quality snapshots in the size of the screen resolution.");}public void mouseExited(MouseEvent e){ta1.setText("");}});
    b2.addMouseListener(new MouseAdapter(){public void mouseEntered(MouseEvent e){ta1.setText("Use the Screen Locking Utility:\n\n\t> To completely lock the screen from any user intervention.\n\t> Prevent any tampering of running applications from other users.\n\t> The utility can be exited only on successful authentication.");}public void mouseExited(MouseEvent e){ta1.setText("");}});
    b3.addMouseListener(new MouseAdapter(){public void mouseEntered(MouseEvent e){ta1.setText("Using the Time-based Shutdown/Restart/LogOff utility:\n\n\t> You can schedule Shutdown/Restart/LogOff operation at a specified time.\n\t> Notify the user with an alert 30 seconds prior to the operation.\n\t> Keep the scheduled operation anonymous.");}public void mouseExited(MouseEvent e){ta1.setText("");}});
    b4.addMouseListener(new MouseAdapter(){public void mouseEntered(MouseEvent e){ta1.setText("Use the “Optimization” Utility:\n\n\t> To make wide-spread changes in explorer, desktop, start-menu etc.\n\t> The features in general are not available directly to the users in XP.\n\t> The changes made are reflected on the next system restart.");}public void mouseExited(MouseEvent e){ta1.setText("");}});
    b5.addMouseListener(new MouseAdapter(){public void mouseEntered(MouseEvent e){ta1.setText("Using the Manual Scandisk Utility:\n\t> You can manually schedule scandisk of LOGICAL drives at your own\n\t   discretion.\n\t> Fix the errors detected and recover lost file-fragments.\n\t> The drive under scan will be dismounted till that time.");}public void mouseExited(MouseEvent e){ta1.setText("");}});
    b6.addMouseListener(new MouseAdapter(){public void mouseEntered(MouseEvent e){ta1.setText("Use the “Restricting Windows XP and User Applications” Utility:\n\t> To block the Windows Applications pre-bundled with it.\n\t> You can also block Office applications and other miscellaneous applications\n\t   which are used in general.\n\t> User specific applications can also be blocked.");}public void mouseExited(MouseEvent e){ta1.setText("");}});
    b7.addMouseListener(new MouseAdapter(){public void mouseEntered(MouseEvent e){ta1.setText("Using the Virtual Drive Utility:\n\t> You can create a virtual drive pointing to a location on disk.\n\t> Provide a drive letter at your own discretion.\n\t> Provide an isolated space for a child/programmer/developer/home-user.\n\t> Changes made to the virtual drive are reflected in the target folder.");}public void mouseExited(MouseEvent e){ta1.setText("");}});    
    b8.addActionListener(this);
    b9.addActionListener(this);
    
    jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    f.addWindowListener(new WindowAdapter()
    	{
    		public void windowClosing(WindowEvent e2)
    		{
    			c=jp.showOptionDialog(f,"<html><body>Closing this window will <b>exit</b> the Main Screen.<br>However, any running utilities won't be terminated.<br><br>Do you want to continue...?</body></html>"," Are you sure...? ",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[0]);
   						
   				if(c==JOptionPane.OK_OPTION)
   				{
   					f.dispose();
   				}
    		}
    	});
    
    f.setSize(600,500);
    f.setVisible(true);
    f.setLocationRelativeTo(null);
    f.setResizable(false);
    jd.setSize(500,500);
    jd.setTitle(" System Information ");
    jd.setLocationRelativeTo(null);
    jd.setResizable(false);
    
    mc.setBounds(0,0,600,92);
	mc.setVisible(true);
    sp1.setBounds(20,350,560,100);
    sp2.setBounds(5,10,485,455);
    b8.setBounds(400,110,100,30);
    b9.setBounds(510,110,70,30);
    jt.setBounds(20,150,560,200);
    jt.addTab("Administration",ic1,p1,"Click to view tools under Administration");
    jt.addTab("Optimization",ic2,p2,"Click to view tools under Optimization");
    jt.addTab("Tuning",ic3,p3,"Click to view tools under Tuning");
    b1.setBounds(50,25,145,100);
    b2.setBounds(205,25,145,100);
    b3.setBounds(360,25,145,100);
    b4.setBounds(205,25,145,100);
    b5.setBounds(50,25,145,100);
    b6.setBounds(205,25,145,100);
    b7.setBounds(360,25,145,100);
    b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    b3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	b1.setToolTipText("Click to enter the \"Time-based Screen Capturing\" Utility");
	b2.setToolTipText("Click to enter the \"Screen Locking\" Utility");
	b3.setToolTipText("Click to enter \"Time-based Shutdown/Restart/LogOff\" Utility");
	b4.setToolTipText("Click to enter \"Optimization\" Utility");
	b5.setToolTipText("Click to enter \"Manual Scandisk\" Utility");
	b6.setToolTipText("Click to enter \"Restricting Windows and User Applications\" Utility");
	b7.setToolTipText("Click to enter \"Virtual Drive\" Utility");
	b8.setToolTipText("Click to view your \"System Information\"");
	b9.setToolTipText("Click to view info. about \"W.A.O.T. Team\"");
	b1.setMnemonic('C');
	b2.setMnemonic('L');
	b3.setMnemonic('S');
	b4.setMnemonic('O');
	b5.setMnemonic('M');
	b6.setMnemonic('R');
	b7.setMnemonic('V');
	b8.setMnemonic('S');
	b9.setMnemonic('A');
		
    ta1.setLineWrap(true);
    ta1.setWrapStyleWord(true);
    ta1.setTabSize(4);
    ta1.setForeground(Color.GRAY);
    ta1.setFont(new Font("Times New Roman",Font.BOLD,14)); 
    ta1.setEditable(false);
    ta2.setText("[---------------  System Information  ------------------]");
    ta2.setEditable(false);
    
    b1.setHorizontalTextPosition(JButton.CENTER);
    b1.setVerticalTextPosition(JButton.BOTTOM);
    b2.setHorizontalTextPosition(JButton.CENTER);
    b2.setVerticalTextPosition(JButton.BOTTOM);
    b3.setHorizontalTextPosition(JButton.CENTER);
    b3.setVerticalTextPosition(JButton.BOTTOM);
    b4.setHorizontalTextPosition(JButton.CENTER);
    b4.setVerticalTextPosition(JButton.BOTTOM);
    b5.setHorizontalTextPosition(JButton.CENTER);
    b5.setVerticalTextPosition(JButton.BOTTOM);
    b6.setHorizontalTextPosition(JButton.CENTER);
    b6.setVerticalTextPosition(JButton.BOTTOM);
    b7.setHorizontalTextPosition(JButton.CENTER);
    b7.setVerticalTextPosition(JButton.BOTTOM);
    
    p.repaint();
    p.validate();
    }
    
    class MyCanvas extends Canvas
    {
    	public void paint(Graphics g)
    	{
    		g.drawImage(img1,0,0,this);
    		g.drawLine(0,90,600,90);
    	}
    }
    
    public void actionPerformed(ActionEvent e2)
    {
    	if(e2.getSource()==b1)
    	{
    		new SC();
    		
    		f.dispose();
    	}
    	
    	if(e2.getSource()==b2)
    	{
    		new SL();
    		
    		f.dispose();
    	}
    	
    	if(e2.getSource()==b3)
    	{
    		new SH();
    		
    		f.dispose();
    	}
    	
    	if(e2.getSource()==b4)
    	{
    		new Optimization();
    		
    		f.dispose();
    	}
    	if(e2.getSource()==b5)
    	{
    		new MScan();
    		
    		f.dispose();
    	}
    	
    	if(e2.getSource()==b6)
    	{
    		new RWA();
    		
    		f.dispose();
    	}
    	
    	if(e2.getSource()==b7)
    	{
    		new VD();
    		
    		f.dispose();
    	}
    	
    	if(e2.getSource()==b8)
    	{
    		try
    		{
    			command=new String[]{"systeminfo"};
    			ps=Runtime.getRuntime().exec(command);
    			ISR=new InputStreamReader(ps.getInputStream());
    			BR=new BufferedReader(ISR);
    			
    			while(true)
    			{
    			s5=BR.readLine();
    				
    				if(s5==null)
    				{
    					ps.destroy();
    					break;
    				}
    			ta2.append("\n"+s5);
    			}
    		}
    		catch(Exception e10)
    		{
    			e10.printStackTrace();
    		}
    		
    		jd.setVisible(true);
    	}
    	if(e2.getSource()==b9)
    	{
    		new About();
    	}
    }
    
   	public static void main(String[] args) 
   	{
    	try 
    	{
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    	
    	Main mn=new Main();
    }
}