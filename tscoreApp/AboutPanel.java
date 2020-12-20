	/* File :AboutPanel.java
* Project: Math Tools in Java 
* Purpose: Display History and staff
* Author : Wachara R.
* First Released: Tu 3 June 2008
* Last Updated :  Sat 5 Dec 2009  The day of Father's day
* Last Updated :  Sun 14 Jan 2018  

*/
package tscoreApp;
import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {
	JPanel authorPanel = new JPanel();
	JPanel hPanel = new JPanel(new BorderLayout());

//	JLabel jLabel0 = new  JLabel( " ");
	JLabel jLabel1 = new JLabel(" Version 1.2  Special", JLabel.CENTER);
	JLabel jLabel2 = new JLabel("  ");
	JLabel jLabel3 = new JLabel("      Presented  by :");
	JLabel jLabel4 = new JLabel("วัชระ รอดสัมฤทธิ์ Wachara Rodsumrid   (wachr0@gmail.com)");

	JLabel jLabel5 = new JLabel("จันทนี อุทธิสินธุ์ Janthanee Authisin (janthaneeau@hotmail.com)");
	JLabel jLabel6 = new JLabel("ชนกนันท์ บางเลี้ยง Chanoknun Bangleing (bchanoknan@hotmail.com)");
	JLabel jLabel7 = new JLabel(" ");
	JLabel jLabel8 = new JLabel ( "               ----------------------------------------------------- ");
	String str1 = "\n- June 18, 2008: First released.";
	String str2 = "\n\n- Oct 2, 2009: Increasing number of Data  from 2000 to 4000";
	String str3 ="\n\n- Nov 30, 2009: Adding grade summary and improving grade option";
	String str4 = "\n\n- Sep 19, 2010: Increasing the limit of Data  to  5000";
	String str5 = "\n\n- Oct 22, 2014:  Code tightened   and preparing to mobile devices";
	String str6 = "\n\n -Jan 14, 2018  Special version for Ajarn Prasong at St Theresa  International College.";

	JLabel jHistory = new JLabel("History...");
	JTextArea  txtHistory  = new  JTextArea(str1, 3, 45); //  string to be displayed, row , column
	JScrollPane  scrollpane = new JScrollPane( txtHistory);

	
	public AboutPanel()
	{
	setBorder(BorderFactory.createEmptyBorder(20,20,10,20)); // top, left , bottom, right
	//setLayout( new GridLayout(6,0));
	authorPanel.setLayout(new GridLayout(9,0));

	jLabel1.setForeground(Color.blue);
	jLabel1.setFont(new Font("Dialog", Font.BOLD, 14));
//	jLabel3.setForeground(Color.gray);
	jLabel3.setFont(new Font("Dialog", Font.BOLD, 12));
//	jLabel4.setForeground(Color.gray);
	jLabel4.setFont(new Font("Tahoma", Font.PLAIN, 12));
//	jLabel5.setForeground(Color.gray);
	jLabel5.setFont(new Font("Tahoma", Font.PLAIN, 12));
//	jLabel6.setForeground(Color.gray);
	jLabel6.setFont(new Font("Tahoma", Font.PLAIN, 12));
//	jLabel7.setForeground(Color.gray);
	jLabel7.setFont(new Font("Tahoma", Font.BOLD, 12));
//	authorPanel.add(jLabel0);
	authorPanel.add(jLabel1); authorPanel.add(jLabel2);
	authorPanel.add(jLabel3); authorPanel.add(jLabel4);
	authorPanel.add(jLabel5); authorPanel.add(jLabel6);
	authorPanel.add(jLabel7); authorPanel.add(jLabel8); 

	jHistory.setFont(new Font("Dialog", Font.BOLD,12));

	txtHistory.setEditable(false);
	txtHistory.setFont(new Font("Dialog", Font.PLAIN, 10));
	txtHistory.setOpaque(false);
	txtHistory.setForeground(Color.black);
	txtHistory.setLineWrap(true); // Wrap the line at the container end.
	txtHistory.append(str2);
	txtHistory.append(str3);
	txtHistory.append(str4);
	txtHistory.append(str5);
	txtHistory.append(str6);
	hPanel.add(jHistory,BorderLayout.NORTH);
	hPanel.add(txtHistory, BorderLayout.CENTER);

	add(authorPanel);
	add(hPanel);
	}
  
	
}
