import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class About
{
JDialog jd;
JPanel p;
JTextArea ta;
MyCanvas1 mc1;
MyCanvas2 mc2;
MyCanvas3 mc3;
Toolkit kit;
Image img1,img2,img3;
String s1,s2,s3,s4;
int i;
       
	About() 
	{
	jd=new JDialog();
	p=new JPanel();
	ta=new JTextArea(10,30);
	mc1=new MyCanvas1();
	mc2=new MyCanvas2();
	mc3=new MyCanvas3();
	kit=Toolkit.getDefaultToolkit();
	img1=kit.getImage("images\\topabt.jpg");
	img2=kit.getImage("images\\bottomabt.jpg");
	img3=kit.getImage("images\\bottomabtlogo.jpg");
	
	p.setLayout(null);
	
	p.add(mc1);
	p.add(mc2);
	p.add(ta);
	p.add(mc3);
	jd.setContentPane(p);
	
	jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	
	jd.setTitle("About W.A.O.T. Team");
	jd.setSize(500,528);
	jd.setVisible(true);
	jd.setLocationRelativeTo(null);
	jd.setResizable(false);
	jd.setAlwaysOnTop(true);
	
	mc1.setBounds(0,0,500,77);
	mc1.setVisible(true);
	mc2.setBounds(0,78,302,292);
	mc2.setVisible(true);
	ta.setLineWrap(true);
	ta.setWrapStyleWord(true);
	ta.setBounds(303,78,197,292);
	ta.setText("Team Members:\n--> Ankith Shetty\n    (Trigger)\n--> Alok Sarang\n    (LoKi)\n--> Hardik Shah\n    (<~~Guru~~>)\n\nIn-house Co-ordinator:\nMr. Pramod Shanbhag\n(H.O.D. - I.T. Dept.)\n\nOut-house Co-ordinator:\nMr. Mehul Parikh\n(Chief Architect - ZBS)");
	ta.setEditable(false);
	ta.setFocusable(false);
	mc3.setBounds(0,370,500,128);
	mc3.setVisible(true);
	
	jd.repaint();
	jd.validate();
	p.repaint();
	p.validate();
    }
    
    public class MyCanvas1 extends Canvas
    {
    	public void paint(Graphics g)
    	{
    		g.drawImage(img1,0,0,this);
    		g.drawLine(0,76,500,76);
    	}
    }
    
    public class MyCanvas2 extends Canvas
    {
    	public void paint(Graphics g)
    	{
    		g.drawImage(img2,0,0,this);
    		g.drawLine(301,0,301,323);
    	}
    }
   
   public class MyCanvas3 extends Canvas
   {
    	public void paint(Graphics g)
    	{
    		g.drawImage(img3,0,0,this);
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
    	
    	About abt=new About();
   }
}