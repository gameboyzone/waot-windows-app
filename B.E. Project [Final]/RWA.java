import java.io.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import ca.beq.util.win32.registry.*;

class RWA implements ActionListener,ItemListener
{
JFrame f;
JPanel p,p1,p2,p3;
JCheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11,cb12,cb13,cb14,cb15,cb16,cb17,cb18,cb19,cb20,cb21,cb22,cb23;
JButton b1,b2,b3,b4,b5;
JTextField t1,t2,t3;
JFileChooser fc;
JOptionPane jp;
MyCanvas mc;
Toolkit kit;
Image img1,img2;
File fl1,fl2,fl3;
RegistryKey r;
RegistryValue v;
Iterator it;
String s5,s1[]={"","iexplore.exe","firefox.exe","wmplayer.exe","notepad.exe","mspaint.exe","wordpad.exe","msmsgs.exe","YahooMessenger.exe","googletalk.exe","msnmsgr.exe","winword.exe","onenote.exe","powerpnt.exe","infopath.exe","frontpage.exe","groove.exe","msaccess.exe","mspub.exe","excel.exe","ois.exe","slot1","slot2","slot3"};
int a,b,c,i;
        
 	RWA() 
 	{
 	//First create the Disallow Registry Sub-Key below Explorer and Registry Value named DisallowRun in Explorer 	
 		
 		try
 		{
 			r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
			
			if(r.exists()==false)
			{	
				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
				r.createSubkey("DisallowRun");
				System.out.println("Sub-Key Created successfully");
 			}
 		}
 		catch(Exception e2)
 		{
 			e2.printStackTrace();
 		}
 		
 		try
 		{
 			r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
 			v=new RegistryValue("DisallowRun",ValueType.REG_DWORD,new Integer(0x00000001));
			r.setValue(v);
			System.out.println("Registry Value Written");
 		}
 		catch(Exception e3)
 		{
 			e3.printStackTrace();
 		}
 	
 	f=new JFrame("Restricting Windows XP and User Applications");
	p=new JPanel();
	p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	t1=new JTextField(20);
	t2=new JTextField(20);
	t3=new JTextField(20);
	cb1=new JCheckBox("Internet Explorer");
	cb2=new JCheckBox("Mozilla Firefox");
	cb3=new JCheckBox("Windows Media Player");
	cb4=new JCheckBox("Notepad");
	cb5=new JCheckBox("Microsoft Paint");
	cb6=new JCheckBox("Wordpad");
	cb7=new JCheckBox("Windows Messenger");
	cb8=new JCheckBox("Yahoo Messenger");
	cb9=new JCheckBox("GTalk");
	cb10=new JCheckBox("Live Messenger");
	cb11=new JCheckBox("Word");
	cb12=new JCheckBox("OneNote");
	cb13=new JCheckBox("Powerpoint");
	cb14=new JCheckBox("InfoPath");
	cb15=new JCheckBox("FrontPage");
	cb16=new JCheckBox("Groove");
	cb17=new JCheckBox("Access");
	cb18=new JCheckBox("Publisher");
	cb19=new JCheckBox("Excel");
	cb20=new JCheckBox("Picture Manager");
	cb21=new JCheckBox("Slot 1");
	cb22=new JCheckBox("Slot 2");
	cb23=new JCheckBox("Slot 3");
	jp=new JOptionPane();
	b1=new JButton("Apply Changes");
	b2=new JButton("Cancel");
	b3=new JButton(" ... ");
	b4=new JButton(" ... ");
	b5=new JButton(" ... ");
	fc=new JFileChooser();
	mc=new MyCanvas();
	kit=Toolkit.getDefaultToolkit();
	img1=kit.getImage("images\\toprwa.jpg");
	img2=kit.getImage("images\\bottomrwa.jpg");
	
	//Read the keys which are currently existing and set the status of the JCheckBox
 		try
 		{
 			r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
				
				if(r.hasValues())
				{
   					it=r.values();
   					
   					while(it.hasNext()) 
   					{
      					v=(RegistryValue)it.next();
      					
      					if(v.getName().toString().equals("1"))
      					{
      						cb1.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("2"))
      					{
      						cb2.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("3"))
      					{
      						cb3.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("4"))
      					{
      						cb4.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("5"))
      					{
      						cb5.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("6"))
      					{
      						cb6.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("7"))
      					{
      						cb7.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("8"))
      					{
      						cb8.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("9"))
      					{
      						cb9.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("10"))
      					{
      						cb10.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("11"))
      					{
      						cb11.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("12"))
      					{
      						cb12.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("13"))
      					{
      						cb13.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("14"))
      					{
      						cb14.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("15"))
      					{
      						cb15.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("16"))
      					{
      						cb16.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("17"))
      					{
      						cb17.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("18"))
      					{
      						cb18.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("19"))
      					{
      						cb19.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("20"))
      					{
      						cb20.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("21"))
      					{
      						try
      						{
      							r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
			
								if(r.hasValue("21"))
								{
   								v=r.getValue("21");
   								t1.setText(v.getData().toString());
								}
      						}
      						catch(Exception e11)
      						{
      							e11.printStackTrace();
      						}
      								
      						cb21.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("22"))
      					{
      						try
      						{
      							r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
			
								if(r.hasValue("22"))
								{
   								v=r.getValue("22");
   								t2.setText(v.getData().toString());
								}
      						}
      						catch(Exception e11)
      						{
      							e11.printStackTrace();
      						}
      						
      						cb22.setSelected(true);
      					}
      					
      					if(v.getName().toString().equals("23"))
      					{
      						try
      						{
      							r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
			
								if(r.hasValue("23"))
								{
   								v=r.getValue("23");
   								t3.setText(v.getData().toString());
								}
      						}
      						catch(Exception e11)
      						{
      							e11.printStackTrace();
      						}
      						
      						cb23.setSelected(true);
      					}
   					} 
				}
 		}
 		catch(Exception e4)
 		{
 			e4.printStackTrace();
 		}
	
	p1.setLayout(new GridLayout(5,2));
	p2.setLayout(new GridLayout(5,2));
	p3.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
	p.setLayout(null);
	
	p1.add(cb1);
	p1.add(cb2);
	p1.add(cb3);
	p1.add(cb4);
	p1.add(cb5);
	p1.add(cb6);
	p1.add(cb7);
	p1.add(cb8);
	p1.add(cb9);
	p1.add(cb10);
	p2.add(cb11);
	p2.add(cb12);
	p2.add(cb13);
	p2.add(cb14);
	p2.add(cb15);
	p2.add(cb16);
	p2.add(cb17);
	p2.add(cb18);
	p2.add(cb19);
	p2.add(cb20);
	p3.add(cb21);
	p3.add(t1);
	p3.add(b3);
	p3.add(cb22);
	p3.add(t2);
	p3.add(b4);
	p3.add(cb23);
	p3.add(t3);
	p3.add(b5);
	p.add(mc);
	p.add(p1);
	p.add(p2);
	p.add(p3);
	p.add(b1);
	p.add(b2);
	f.setContentPane(p);
	
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
	b4.addActionListener(this);
	b5.addActionListener(this);
	cb21.addItemListener(this);
	cb22.addItemListener(this);
	cb23.addItemListener(this);
	f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e11)
    		{
    			new Main();
			
				f.dispose();
    		}
    	});	
    
    f.setSize(600,500);
	f.setVisible(true);
	f.setLocationRelativeTo(null);
	f.setResizable(false);
	
	mc.setBounds(0,0,600,192);
	mc.setVisible(true);
	p1.setBounds(5,200,320,120);
	p2.setBounds(330,200,260,120);
	p3.setBounds(5,325,320,100);
	b1.setBounds(150,430,110,30);
	b2.setBounds(350,430,70,30);
	
	p1.setBorder(BorderFactory.createTitledBorder(" Windows Applications: "));
    p2.setBorder(BorderFactory.createTitledBorder(" Office Applications: "));
    p3.setBorder(BorderFactory.createTitledBorder(" User Applications: "));
	t1.setEditable(false);
	t2.setEditable(false);
	t3.setEditable(false);
	b1.setMnemonic('A');
	b2.setMnemonic('C');
	
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
    
    public void actionPerformed(ActionEvent e2)
    {
    	if(e2.getSource()==b1)					//Apply Changes Button
    	{
    		if((cb21.isSelected()&&t1.getText().trim().equals(""))||(cb22.isSelected()&&t2.getText().trim().equals(""))||(cb23.isSelected()&&t3.getText().trim().equals("")))
    		{
    			jp.showMessageDialog(f,"Required fields INCOMPLETE !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
    		}
    		else
    		{	
    			if(cb1.isSelected())
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 						v=new RegistryValue("1",ValueType.REG_SZ,"iexplore.exe");
						r.setValue(v);
    				}
    				catch(Exception e8)
    				{
    					e8.printStackTrace();
    				}
    			}
    			else
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
						r.deleteValue("1");
    				}
    				catch(Exception e8)
    				{
    					//e8.printStackTrace();
    				}
    			}
    		
    			if(cb2.isSelected())
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 						v=new RegistryValue("2",ValueType.REG_SZ,"firefox.exe");
						r.setValue(v);
    				}
    				catch(Exception e8)
    				{
    					e8.printStackTrace();
    				}
    			}
    			else
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
						r.deleteValue("2");
    				}
    				catch(Exception e8)
    				{
    					//e8.printStackTrace();
    				}
    			}
    		
    			if(cb3.isSelected())
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 						v=new RegistryValue("3",ValueType.REG_SZ,"wmplayer.exe");
						r.setValue(v);
    				}
    				catch(Exception e8)
    				{
    					e8.printStackTrace();
    				}
    			}
    			else
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
						r.deleteValue("3");
    				}
    				catch(Exception e8)
    				{
    					//e8.printStackTrace();
    				}
    			}
    			
    			if(cb4.isSelected())
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 						v=new RegistryValue("4",ValueType.REG_SZ,"notepad.exe");
						r.setValue(v);
    				}
    				catch(Exception e8)
    				{
    					e8.printStackTrace();
    				}
    			}
    			else
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
						r.deleteValue("4");
    				}
    				catch(Exception e8)
    				{
    					//e8.printStackTrace();
    				}
    			}
    			
    			if(cb5.isSelected())
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 						v=new RegistryValue("5",ValueType.REG_SZ,"mspaint.exe");
						r.setValue(v);
    				}
    				catch(Exception e8)
    				{
    					e8.printStackTrace();
    				}
    			}	
    			else
    			{
    				try
    				{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
						r.deleteValue("5");
    				}
    				catch(Exception e8)
    				{
    					//e8.printStackTrace();
    				}
    			}
    			
    			if(cb6.isSelected())
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 						v=new RegistryValue("6",ValueType.REG_SZ,"wordpad.exe");
						r.setValue(v);
    				}
    				catch(Exception e8)
    				{
    					e8.printStackTrace();
    				}
    			}
    			else
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
						r.deleteValue("6");
    				}
    				catch(Exception e8)
    				{
    					//e8.printStackTrace();
    				}
    			}
    			
    			if(cb7.isSelected())
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 						v=new RegistryValue("7",ValueType.REG_SZ,"msmsgs.exe");
						r.setValue(v);
    				}
    				catch(Exception e8)
    				{
    					e8.printStackTrace();
    				}
    			}
    			else
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
						r.deleteValue("7");
    				}
    				catch(Exception e8)
    				{
    					//e8.printStackTrace();
    				}
    			}
    			
    			if(cb8.isSelected())
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 						v=new RegistryValue("8",ValueType.REG_SZ,"YahooMessenger.exe");
						r.setValue(v);
    				}
    				catch(Exception e8)
    				{
    					e8.printStackTrace();
    				}
    			}
    			else
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
						r.deleteValue("8");
    				}
    				catch(Exception e8)
    				{
    					//e8.printStackTrace();
    				}
    			}
    				
    				if(cb9.isSelected())
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 						v=new RegistryValue("9",ValueType.REG_SZ,"googletalk.exe");
						r.setValue(v);
    				}
    				catch(Exception e8)
    				{
    					e8.printStackTrace();
    				}
    			}
    			else
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
						r.deleteValue("9");
    				}
    				catch(Exception e8)
    				{
    					//e8.printStackTrace();
    				}
    			}
    			
    			if(cb10.isSelected())
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 						v=new RegistryValue("10",ValueType.REG_SZ,"msnmsgr.exe");
						r.setValue(v);
    				}
    				catch(Exception e8)
    				{
    					e8.printStackTrace();
    				}
    			}
    			else
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
						r.deleteValue("10");
    				}
    				catch(Exception e8)
    				{
    					//e8.printStackTrace();
    				}
    			}	
    		
    		if(cb11.isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 					v=new RegistryValue("11",ValueType.REG_SZ,"winword.exe");
					r.setValue(v);
    			}
    			catch(Exception e8)
    			{
    				e8.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
					r.deleteValue("11");
    			}
    			catch(Exception e8)
    			{
    				//e8.printStackTrace();
    			}
    		}
    		
    		if(cb12.isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 					v=new RegistryValue("12",ValueType.REG_SZ,"onenote.exe");
					r.setValue(v);
    			}
    			catch(Exception e8)
    			{
    				e8.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
					r.deleteValue("12");
    			}
    			catch(Exception e8)
    			{
    				//e8.printStackTrace();
    			}
    		}
    		
    		if(cb13.isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 					v=new RegistryValue("13",ValueType.REG_SZ,"powerpnt.exe");
					r.setValue(v);
    			}
    			catch(Exception e8)
    			{
    				e8.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
					r.deleteValue("13");
    			}
    			catch(Exception e8)
    			{
    				//e8.printStackTrace();
    			}
    		}
    		
    		if(cb14.isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 					v=new RegistryValue("14",ValueType.REG_SZ,"infopath.exe");
					r.setValue(v);
    			}
    			catch(Exception e8)
    			{
    				e8.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
					r.deleteValue("14");
    			}
    			catch(Exception e8)
    			{
    				//e8.printStackTrace();
    			}
    		}
    		
    		if(cb15.isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 					v=new RegistryValue("15",ValueType.REG_SZ,"frontpage.exe");
					r.setValue(v);
    			}
    			catch(Exception e8)
    			{
    				e8.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
					r.deleteValue("15");
    			}
    			catch(Exception e8)
    			{
    				//e8.printStackTrace();
    			}
    		}
    		
    		if(cb16.isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 					v=new RegistryValue("16",ValueType.REG_SZ,"groove.exe");
					r.setValue(v);
    			}
    			catch(Exception e8)
    			{
    				e8.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
					r.deleteValue("16");
    			}
    			catch(Exception e8)
    			{
    				//e8.printStackTrace();
    			}
    		}
    		
    		if(cb17.isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 					v=new RegistryValue("17",ValueType.REG_SZ,"msaccess.exe");
					r.setValue(v);
    			}
    			catch(Exception e8)
    			{
    				e8.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
					r.deleteValue("17");
    			}
    			catch(Exception e8)
    			{
    				//e8.printStackTrace();
    			}
    		}
    		
    		if(cb18.isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 					v=new RegistryValue("18",ValueType.REG_SZ,"mspub.exe");
					r.setValue(v);
    			}
    			catch(Exception e8)
    			{
    				e8.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
					r.deleteValue("18");
    			}
    			catch(Exception e8)
    			{
    				//e8.printStackTrace();
    			}
    		}
    		
    		if(cb19.isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 					v=new RegistryValue("19",ValueType.REG_SZ,"excel.exe");
					r.setValue(v);
    			}
    			catch(Exception e8)
    			{
    				e8.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
					r.deleteValue("19");
    			}
    			catch(Exception e8)
    			{
    				//e8.printStackTrace();
    			}
    		}
    		
    		if(cb20.isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 					v=new RegistryValue("20",ValueType.REG_SZ,"ois.exe");
					r.setValue(v);
    			}
    			catch(Exception e8)
    			{
    				e8.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
					r.deleteValue("20");
    			}
    			catch(Exception e8)
    			{
    				//e8.printStackTrace();
    			}
    		}
    		
    		if(cb21.isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 					v=new RegistryValue("21",ValueType.REG_SZ,t1.getText().trim());
					r.setValue(v);
    			}
    			catch(Exception e8)
    			{
    				e8.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
					r.deleteValue("21");
    			}
    			catch(Exception e8)
    			{
    				//e8.printStackTrace();
    			}
    		}
    		
    			if(cb22.isSelected())
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 						v=new RegistryValue("22",ValueType.REG_SZ,t2.getText().trim());
						r.setValue(v);
    				}
    				catch(Exception e8)
    				{
    					e8.printStackTrace();
    				}
    			}
    			else
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
						r.deleteValue("22");
    				}
    				catch(Exception e8)
    				{
    					//e8.printStackTrace();
    				}
    			}
    			
    			if(cb23.isSelected())
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
 						v=new RegistryValue("23",ValueType.REG_SZ,t3.getText().trim());
						r.setValue(v);
    				}
    				catch(Exception e8)
    				{
    					e8.printStackTrace();
    				}
    			}
    			else
    			{
    				try
    				{
    					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");
						r.deleteValue("23");
    				}
    				catch(Exception e8)
    				{
    					//e8.printStackTrace();
    				}
    			}	
    		
    			jp.showMessageDialog(f,"<html><body>The <b>applied changes</b> will take effect after the <b>next system restart</b> !!!</body></html>","Changes applied successfully !!!",JOptionPane.INFORMATION_MESSAGE);
    			
    			new Main();
    			
    			f.dispose();
    		}
    	}
    	
    	if(e2.getSource()==b2)				//Cancel Button
    	{
    		//Dispose the Frame and call the Main()
    		
    		new Main();	
    		
    		f.dispose();
    	}
    	
    	if(e2.getSource()==b3)
    	{
    		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    		a=fc.showOpenDialog(f);
    		
    		if(a==JFileChooser.APPROVE_OPTION)
    		{
    			fl1=fc.getSelectedFile();
    			
    			String sf1=fl1.getName();
    			
    			if((((sf1.indexOf(".exe")!=-1)&&(sf1.indexOf(".lnk")==-1))||(sf1.indexOf(".EXE")!=-1))&&(fl1.exists()))
    			{
    				t1.setText(sf1);
    				cb21.setSelected(true);
    			}
    			else
    			{
    				jp.showMessageDialog(f,"The Target Application should be an executable(i.e. with .exe extension) !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
    				b3.doClick();
    			}
    			
    		}
    		else
    		{	
    			cb21.setSelected(false);
    		}
    		
    	}
    	
    	if(e2.getSource()==b4)
    	{
    		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    		b=fc.showOpenDialog(f);
    		
    		if(b==JFileChooser.APPROVE_OPTION)
    		{
    			fl2=fc.getSelectedFile();
    			
    			String sf2=fl2.getName();
    			
    			if((((sf2.indexOf(".exe")!=-1)&&(sf2.indexOf(".lnk")==-1))||(sf2.indexOf(".EXE")!=-1))&&(fl2.exists()))
    			{
    				t2.setText(sf2);
    				cb22.setSelected(true);
    			}
    			else
    			{
    				jp.showMessageDialog(f,"The Target Application should be an executable(i.e. with .exe extension) !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
    				b4.doClick();
    			}
    			
    		}
    		else
    		{	
    			cb22.setSelected(false);
    		}
    	}
    	
    	if(e2.getSource()==b5)
    	{
    		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    		c=fc.showOpenDialog(f);
    		
    		if(c==JFileChooser.APPROVE_OPTION)
    		{
    			
    			fl3=fc.getSelectedFile();
    			
    			String sf3=fl3.getName();
    			
    			if((((sf3.indexOf(".exe")!=-1)&&(sf3.indexOf(".lnk")==-1))||(sf3.indexOf(".EXE")!=-1))&&(fl3.exists()))
    			{
    				t3.setText(sf3);
    				cb23.setSelected(true);
    			}
    			else
    			{
    				jp.showMessageDialog(f,"The Target Application should be an executable(i.e. with .exe extension) !!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
    				b5.doClick();
    			}
    			
    		}
    		else if(a==JFileChooser.CANCEL_OPTION)
    		{	
    			cb23.setSelected(false);
    		}
    	}
    	
    }
    
    public void itemStateChanged(ItemEvent e10)
    {
    	if(cb21.isSelected()==false)
    	{
    		t1.setText("");
    	}
    	
    	if(cb22.isSelected()==false)
    	{
    		t2.setText("");
    	}
    	
    	if(cb23.isSelected()==false)
    	{
    		t3.setText("");
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
    	
        RWA rwa=new RWA();
    }
}