import java.io.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import ca.beq.util.win32.registry.*;

class Optimization implements ActionListener
{
JFrame f;
JPanel p,p1,p2,p3,p4,p5;
JButton b1,b2;
JTabbedPane jt;
JOptionPane jp;
JCheckBox cb[];
MyCanvas mc;
Toolkit kit;
Image img1,img2;
RegistryKey r;
RegistryValue v;
Iterator it;
String st[];
int i;        
        
	Optimization() 
	{
	f=new JFrame("Restricting Windows XP and User Applications");
	p=new JPanel();
	p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	jt=new JTabbedPane(JTabbedPane.TOP,JTabbedPane.WRAP_TAB_LAYOUT);
	jp=new JOptionPane();
	b1=new JButton("Apply Changes");
	b2=new JButton("Cancel");
	mc=new MyCanvas();
	cb=new JCheckBox[31];
	kit=Toolkit.getDefaultToolkit();
	img1=kit.getImage("images\\topoptmn.jpg");
	img2=kit.getImage("images\\bottomoptmn.jpg");
	st=new String[]{"0","Disable Drive Autorun","Disable Task Manager","Disable WIN Keys","Disable USB device detection","No \"Add/Remove Programs\"","Disable Drives in My Computer","7","8","Disable Recycle Bin","Make Admin Account visible on Login Screen","No Desktop","No Desktop/Explorer ContextMenu","Disable Tray ContextMenu","Disabling wrapping icon/shortcut labels","Remove the low disk space tooltip","Disable the Security Center tooltips","No \"Themes\" Tab in Display Properties","No \"Desktop\" Tab in Display Properties","No \"Appearance\" Tab in Display Properties","No \"Settings\" Tab in Display Properties","Remove \"Turn Off Computer\" from Start-Menu","Remove \"Log Off\" from Start-Menu","Disable Search","Remove \"Control Panel\" from explorer/Start-Menu","Remove \"Run\" from Start-Menu","Disable ability to change the Start-Menu","Disable Properties of MyComputer","Suppress Error Messages on OS Boot","Disable Most Frequently Used List in Start-Menu","Disable \"Folder Options\" in explorer/Control Panel"};
	for(i=0;i<=30;i++)
	{
		cb[i]=new JCheckBox(st[i]);
	}
	
	//Read the keys which are currently existing and set the status of the JCheckBox
	try
    {
    	r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
			
		if(r.hasValue("NoDriveTypeAutoRun"))
		{
   			v = r.getValue("NoDriveTypeAutoRun");
   			
   			if(v.getData().toString().indexOf("255")!=-1)
   			{
   				cb[1].setSelected(true);
   			}
		}
		
		if(r.hasValue("DisableWinKeys"))
		{
   			v = r.getValue("DisableWinKeys");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[3].setSelected(true);
   			}
		}
		
		if(r.hasValue("NoDrives"))
		{
   			v = r.getValue("NoDrives");
   			
   			if(v.getData().toString().indexOf("253")!=-1)
   			{
   				cb[6].setSelected(true);
   			}
		}
		
		if(r.hasValue("NoDesktop"))
		{
   			v = r.getValue("NoDesktop");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[11].setSelected(true);
   			}
		}
		
		if(r.hasValue("NoContextMenu"))
		{
   			v = r.getValue("NoContextMenu");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[12].setSelected(true);
   			}
		}
		
		if(r.hasValue("NoTrayContextMenu"))
		{
   			v = r.getValue("NoTrayContextMenu");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[13].setSelected(true);
   			}
		}
		
		if(r.hasValue("NoLowDiskSpaceChecks"))
		{
   			v = r.getValue("NoLowDiskSpaceChecks");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[15].setSelected(true);
   			}
		}
		
		if(r.hasValue("NoThemesTab"))
		{
   			v = r.getValue("NoThemesTab");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[17].setSelected(true);
   			}
		}
		
		if(r.hasValue("NoClose"))
		{
   			v = r.getValue("NoClose");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[21].setSelected(true);
   			}
		}
		
		if(r.hasValue("StartMenuLogoff"))
		{
   			v = r.getValue("StartMenuLogoff");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[22].setSelected(true);
   			}
		}
		
		if(r.hasValue("NoFind"))
		{
   			v = r.getValue("NoFind");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[23].setSelected(true);
   			}
		}
		
		if(r.hasValue("NoControlPanel"))
		{
   			v = r.getValue("NoControlPanel");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[24].setSelected(true);
   			}
		}
		
		if(r.hasValue("NoRun"))
		{
   			v = r.getValue("NoRun");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[25].setSelected(true);
   			}
		}
		
		if(r.hasValue("NoChangeStartMenu"))
		{
   			v = r.getValue("NoChangeStartMenu");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[26].setSelected(true);
   			}
		}
		
		if(r.hasValue("NoPropertiesMyComputer"))
		{
   			v = r.getValue("NoPropertiesMyComputer");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[27].setSelected(true);
   			}
		}
		
		if(r.hasValue("NoStartMenuMFUprogramsList"))
		{
   			v = r.getValue("NoStartMenuMFUprogramsList");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[29].setSelected(true);
   			}
		}
		
		if(r.hasValue("NoFolderOptions"))
		{
   			v = r.getValue("NoFolderOptions");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[30].setSelected(true);
   			}
		}
		
    }
   	catch(Exception e4)
    {
    	//e4.printStackTrace();
    }
    
    try
    {
    	r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SYSTEM\\ControlSet001\\Services\\USBSTOR");
    	
    	if(r.hasValue("Start"))
		{
   			v = r.getValue("Start");
   			
   			if(v.getData().toString().indexOf("4")!=-1)
   			{
   				cb[4].setSelected(true);
   			}
		}
    }
    catch(Exception e4)
    {
    	//e4.printStackTrace();
    }
    
    try
    {
    	r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\BitBucket");
    	
    	if(r.hasValue("NukeOnDelete"))
		{
   			v = r.getValue("NukeOnDelete");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[9].setSelected(true);
   			}
		}
    }
    catch(Exception e4)
    {
    	//e4.printStackTrace();
    }
	
	try
    {
    	r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Winlogon\\SpecialAccounts\\UserList");
    	
    	if(r.hasValue("Administrator"))
		{
   			v = r.getValue("Administrator");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[10].setSelected(true);
   			}
		}
    }
    catch(Exception e4)
    {
    	//e4.printStackTrace();
    }
    
    try
    {
    	r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Control Panel\\Desktop\\WindowMetrics");
    	
    	if(r.hasValue("IconTitleWrap"))
		{
   			v = r.getValue("IconTitleWrap");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[14].setSelected(false);
   			}
   			else
   			{
   				cb[14].setSelected(true);
   			}
		}
    }
    catch(Exception e4)
    {
    	//e4.printStackTrace();
    }
    
    try
    {
    	r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SOFTWARE\\Microsoft\\Security Center");
    	
    	if(r.hasValue("AntiVirusDisableNotify"))
		{
   			v = r.getValue("AntiVirusDisableNotify");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[16].setSelected(true);
   			}
		}
    }
    catch(Exception e4)
    {
    	//e4.printStackTrace();
    }
    
    try
    {
    	r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System");
    	
    	if(r.hasValue("NoDispBackgroundPage"))
		{
   			v = r.getValue("NoDispBackgroundPage");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[18].setSelected(true);
   			}
		}
    }
    catch(Exception e4)
    {
    	//e4.printStackTrace();
    }
    
    try
    {
    	r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System");
    	
    	if(r.hasValue("NoDispAppearancePage"))
		{
   			v = r.getValue("NoDispAppearancePage");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[19].setSelected(true);
   			}
		}
    }
    catch(Exception e4)
    {
    	//e4.printStackTrace();
    }
    
    try
    {
    	r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System");
    	
    	if(r.hasValue("NoDispSettingsPage"))
		{
   			v = r.getValue("NoDispSettingsPage");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[20].setSelected(true);
   			}
		}
    }
    catch(Exception e4)
    {
    	//e4.printStackTrace();
    }
    
    try
    {
    	r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SYSTEM\\CurrentControlSet\\Control\\Windows");
    	
    	if(r.hasValue("NoPopupsOnBoot"))
		{
   			v = r.getValue("NoPopupsOnBoot");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[28].setSelected(true);
   			}
		}
    }
    catch(Exception e4)
    {
    	e4.printStackTrace();
    }
    
    try
    {
    	r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System");
    	
    	if(r.hasValue("DisableTaskMgr"))
		{
   			v = r.getValue("DisableTaskMgr");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[2].setSelected(true);
   			}
  
		}
    }
    catch(Exception e4)
    {
    	e4.printStackTrace();
    }
    
    
    try
    {
    	r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Uninstall");
    	
    	if(r.hasValue("NoAddRemovePrograms"))
		{
   			v = r.getValue("NoAddRemovePrograms");
   			
   			if(v.getData().toString().indexOf("1")!=-1)
   			{
   				cb[5].setSelected(true);
   			}
  
		}
    }
    catch(Exception e4)
    {
    	e4.printStackTrace();
    }
    
	p1.setLayout(new GridLayout(4,2));
	p2.setLayout(new GridLayout(5,2));
	p3.setLayout(new GridLayout(5,2));
	p.setLayout(null);
	
	p.add(mc);
	for(i=1;i<=10;i++)
	{
		if((i==7)||(i==8))
		{
			continue;
		}
		
		p1.add(cb[i]);
	}
	for(i=11;i<=20;i++)
	{
		p2.add(cb[i]);
	}
	for(i=21;i<=30;i++)
	{
		p3.add(cb[i]);
	}
	p.add(jt);
	p.add(b1);
	p.add(b2);
	f.setContentPane(p);
	
	b1.addActionListener(this);
	b2.addActionListener(this);
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
	
	mc.setBounds(0,0,600,192);
	mc.setVisible(true);
	jt.setBounds(10,200,560,200);
	b1.setBounds(150,420,110,30);
	b2.setBounds(350,420,70,30);
    jt.addTab("Explorer",null,p1,"Click to view options under Explorer");
    jt.addTab("Desktop",null,p2,"Click to view options under Desktop");
    jt.addTab("Miscellaneous",null,p3,"Click to view options under Miscellaneous");
	//Show keys of IE and Start-Menu in Miscellaneous
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
    	if(e2.getSource()==b1)				//Apply Changes Button
    	{
    		
    		if(cb[1].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoDriveTypeAutoRun",ValueType.REG_DWORD,new Integer(0x000000ff));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoDriveTypeAutoRun",ValueType.REG_DWORD,new Integer(0x00000091));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[2].isSelected())
    		{
    			try
				{
					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies");
					r.createSubkey("System");
				}
				catch(Exception e20)
				{
					//e20.printStackTrace();
				}
    			
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System");
					v=new RegistryValue("DisableTaskMgr",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
				{
					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies");
					r.createSubkey("System");
				}
				catch(Exception e20)
				{
					//e20.printStackTrace();
				}
    			
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System");
					v=new RegistryValue("DisableTaskMgr",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[3].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("DisableWinKeys",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("DisableWinKeys",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[4].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SYSTEM\\ControlSet001\\Services\\USBSTOR");
					v=new RegistryValue("Start",ValueType.REG_DWORD,new Integer(0x00000004));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SYSTEM\\ControlSet001\\Services\\USBSTOR");
					v=new RegistryValue("Start",ValueType.REG_DWORD,new Integer(0x00000003));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[5].isSelected())
    		{
    			try
				{
					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies");
					r.createSubkey("Uninstall");
				}
				catch(Exception e20)
				{
					//e20.printStackTrace();
				}
    			
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Uninstall");
					v=new RegistryValue("NoAddRemovePrograms",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
				{
					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies");
					r.createSubkey("Uninstall");
				}
				catch(Exception e20)
				{
					//e20.printStackTrace();
				}
    			
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Uninstall");
					v=new RegistryValue("NoAddRemovePrograms",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[6].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoDrives",ValueType.REG_DWORD,new Integer(0x000000fd));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoDrives",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[9].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\BitBucket");
					v=new RegistryValue("NukeOnDelete",ValueType.REG_DWORD,new Integer(0x00000001));		//1 --> Disable
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\BitBucket");
					v=new RegistryValue("NukeOnDelete",ValueType.REG_DWORD,new Integer(0x00000000));		//0 --> Enable
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[10].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Winlogon\\SpecialAccounts\\UserList");
					v=new RegistryValue("Administrator",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Winlogon\\SpecialAccounts\\UserList");
					v=new RegistryValue("Administrator",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[11].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoDesktop",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoDesktop",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[12].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoContextMenu",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoContextMenu",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[13].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoTrayContextMenu",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoTrayContextMenu",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[14].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Control Panel\\Desktop\\WindowMetrics");
					v=new RegistryValue("IconTitleWrap",ValueType.REG_SZ,new Integer(0x00000000));	//0 --> Disable
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Control Panel\\Desktop\\WindowMetrics");
					v=new RegistryValue("IconTitleWrap",ValueType.REG_SZ,new Integer(0x00000001));	//1 --> Enable
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[15].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoLowDiskSpaceChecks",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoLowDiskSpaceChecks",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[16].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SOFTWARE\\Microsoft\\Security Center");
					v=new RegistryValue("AntiVirusDisableNotify",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
					
					r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SOFTWARE\\Microsoft\\Security Center");
					v=new RegistryValue("FirewallDisableNotify",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SOFTWARE\\Microsoft\\Security Center");
					v=new RegistryValue("AntiVirusDisableNotify",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
					
					r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SOFTWARE\\Microsoft\\Security Center");
					v=new RegistryValue("FirewallDisableNotify",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[17].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoThemesTab",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoThemesTab",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[18].isSelected())
    		{
    			try
				{
					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies");
					r.createSubkey("System");
				}
				catch(Exception e20)
				{
					//e20.printStackTrace();
				}
    			
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System");
					v=new RegistryValue("NoDispBackgroundPage",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
				{
					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies");
					r.createSubkey("System");
				}
				catch(Exception e20)
				{
					//e20.printStackTrace();
				}
    			
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System");
					v=new RegistryValue("NoDispBackgroundPage",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[19].isSelected())
    		{
    			try
				{
					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies");
					r.createSubkey("System");
				}
				catch(Exception e20)
				{
					//e20.printStackTrace();
				}
    			
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System");
					v=new RegistryValue("NoDispAppearancePage",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
				{
					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies");
					r.createSubkey("System");
				}
				catch(Exception e20)
				{
					//e20.printStackTrace();
				}
    			
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System");
					v=new RegistryValue("NoDispAppearancePage",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[20].isSelected())
    		{
    			try
				{
					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies");
					r.createSubkey("System");
				}
				catch(Exception e20)
				{
					//e20.printStackTrace();
				}
    			
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System");
					v=new RegistryValue("NoDispSettingsPage",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
				{
					r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies");
					r.createSubkey("System");
				}
				catch(Exception e20)
				{
					//e20.printStackTrace();
				}
    			
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\System");
					v=new RegistryValue("NoDispSettingsPage",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[21].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoClose",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoClose",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[22].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("StartMenuLogoff",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("StartMenuLogoff",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[23].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoFind",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoFind",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[24].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoControlPanel",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoControlPanel",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[25].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoRun",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoRun",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[26].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoChangeStartMenu",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoChangeStartMenu",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[27].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoPropertiesMyComputer",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
					
					r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoPropertiesMyComputer",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoPropertiesMyComputer",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
					
					r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoPropertiesMyComputer",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[28].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SYSTEM\\CurrentControlSet\\Control\\Windows");
					v=new RegistryValue("NoPopupsOnBoot",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,"SYSTEM\\CurrentControlSet\\Control\\Windows");
					v=new RegistryValue("NoPopupsOnBoot",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		if(cb[29].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoStartMenuMFUprogramsList",ValueType.REG_DWORD,new Integer(0x00000001));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoStartMenuMFUprogramsList",ValueType.REG_DWORD,new Integer(0x00000000));
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		System.out.println("30 reached");
    		
    		if(cb[30].isSelected())
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoFolderOptions",ValueType.REG_DWORD,new Integer(0x00000001));		//1 --> Enabled
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		else
    		{
    			try
    			{
    				r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer");
					v=new RegistryValue("NoFolderOptions",ValueType.REG_DWORD,new Integer(0x00000000));		//0 --> Disabled
					r.setValue(v);
    			}
    			catch(Exception e4)
    			{
    				//e4.printStackTrace();
    			}
    		}
    		
    		jp.showMessageDialog(f,"The applied changes will take effect after the next system restart !!!","Changes applied successfully !!!",JOptionPane.INFORMATION_MESSAGE);
    		
    		new Main();
    		
    		f.dispose();
    			
    	}
    	
    	if(e2.getSource()==b2)				//Cancel Button
    	{
    		//Dispose the Frame and call the Main()
    		
    		new Main();
    		
    		f.dispose();
 
    	}
    }
    
    public static void main(String args[]) 
    {
    	try
    	{
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	}	
    	catch(Exception e4)
    	{
    		e4.printStackTrace();
    	}
    	
    	Optimization optmn=new Optimization();  
    }
}