	/* File :HelpPanel.java
* Project: Math Tools in Java 
* Purpose: Show how to use Normalized T score calculation
* Author : Wachara R.
* First Released: Fri 23 May 2008
* Last Updated :  Sun 25 Oct 2009
*/
package tscoreApp;
import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {
	JTabbedPane userGuidePane = new JTabbedPane(JTabbedPane.BOTTOM);
	UserGuidePanel  tab1;
	UserGuidePanel  tab2;
	UserGuidePanel  tab3;

	String str1 = 
		"\n There are 2 ways to input your data: \n\n   1. Typing  your scores in text  area \n\t"+
		"- Both Integer or real numbers are OK.\n\t" +
		"- Separate each score with space.\n\t- Don't use Comma or any alphabets.\n\t"+
		"- You can type scores in a line as long as  you want\n\t"+
		"   or  press <enter> for a new line. \n\t- A star ( * ) at the beginning of the line means\n\t "+
		"  this line is ignored (It's a comment). \n\t"+
		"- You can type scores in any editor (eg. NotePad)\n\t" +
		"   then copy(Ctr+c) and paste (Ctr+ v) into this text area.\n\n" +
		"   2. Reading data from textfile ( suitable for  hundreds of data)\n\t"+
		"- Create your data with any editor (NotePad, Editplus etc.)\n\t"+
		"  following the rule as mention in 1.\n\t"+
		"- Save your data as textfile. \n\t"+
		"- Click 'Read file' button for opening your textfile\n\t"+
		"  your data will be read into text area.\n\n"+
		"WARNING:\n"+
		"\t**The maximum number of data is  5000.** ";
	String str2 = 
		"\n\t- When data appear in text area, clicking on 'Calculate' button\n"+
		"  will launch the program crunching the numbers. Sorted scores, frequency\n"+
		"  T score and grade  being displayed in the table in no time\n\n"+
		"\t- Selecting data in table (by draging mouse in table area), \n"+
		"  You can copy (Ctr+c) and paste (Ctr+v) them into word  processing \n"+
		"   or spread sheet application.\n\n"+
		"\t- To change the range of grade assesment, selecting any\n "+
		"specific range as shown in combo box . The new grades  will  be \n" + 
		"calculated   automatically.";
	String str3 = 
		"\n\n\t- Just clicking on 'Print result' button, the output result will be\n"+
		"sent to the printer\n\n"+
		" WARNING ..\n\n"+
		"\tThe paper size setting is limited to A4 ( 11\" x 8.5\" ) \n";

	String Tstr1 = "\n ผู้ใช้สามารถป้อนข้อมูลได้ 2 วิธี: \n\n   1. พิมพ์คะแนนลงไปในช่อง text area \n\t"+
		"- คะแนนจะเป็นจำนวนเต็มหรือจำนวนที่มีทศนิยมก็ได้.\n\t" +
		"- แยกคะแนนแต่ละคะแนนด้วย 'ช่องว่าง'.\n\t- ห้ามใช้เครื่องหมายจุลภาค หรือ ตัวอักขระใด ๆ.\n\t"+
		"- บรรทัดหนึ่ง ๆ จะพิมพ์คะแนนกี่ค่าก็ได้ตามแต่จะต้องการ \n\t"+
		"   ต้องการขึ้นบรรทัดใหม่ให้กดปุ่ม 'enter'. \n\t- ต้องการเขียน comment ใหใส่ * ที่หน้าบรรทัดนั้น\n\t "+
		"  บรรทัดนั้นจะไม่ถูกนำไปประมวลผล. \n\t"+
		"- เราอาจใช้โปรแกรม editor เช่น NotePad พิมพ์คะแนนเตรียมไว้ก่อน\n\t" +
		"   จากนั้น copy(Ctr+c) แล้ว paste (Ctr+ v) มาใส่ใน text area.\n\n" +
		"   2. อ่านค่าคะแนนจาก text file (เหมาะสำหรับกรณีที่มีข้อมูลเป็นจำนวนมาก)\n\t"+
		"- พิมพ์คะแนนลงในโปรแกรม editor ใด ๆ  (NotePad, Editplus ฯลฯ)\n\t"+
		"  กฎเกณฑ์การพิม์คะแนนเหมือนกับที่กล่าวไว้ในข้อ 1.\n\t"+
		"- บันทึกข้อมูล เก็บไว้เป็น text file. \n\t"+
		"- คลิกปุ่ม 'Read file' เพื่ออ่านคะแนนจากแฟ้มข้อมูลนั้น\n\t"+
		"  คะแนนที่ถูกเก็บไว้ในแฟ้มข้อมูลจะถูกนำมาแสดงใน  text area.\n\n"+
		"คำเตือน:\n"+
		"\t**จำนวนข้อมูลหรือคะแนนต้องไม่เกิน  5000 ค่า** ";

	String Tstr2 = "\n\t- เมื่อข้อมูลถูกแสดงใน text area , ให้คลิกที่ปุ่ม'Calculate'\n"+
		"  โปรแกรมจะทำการประมวลผล  โดยจะเรียงลำดับข้อมูล หาความถี่\n"+
		"  คำนวณ T Score และกำหนดระดับคะแนน แสดงให้เห็นในตารางชั่วอึดใจ\n\n"+
		"\t- สามารถเลือกข้อมูลในตาราง (โดยลากเมาส์ระบายในบริเวณตาราง), \n"+
		"  จากนั้น copy (Ctr+c) และ paste (Ctr+v) ข้อมูลไปใช้ในโปรแกรม word processing \n"+
		"   หรือโปรแกรมตารางคำนวณ.\n\n"+
		"\t- ต้องการเปลี่ยนช่วงของระดับคะแนน, ให้คลิกเลือกช่วงระดับคะแนน\n "+
		"ที่ต้องการที่ 'กล่องตัวเลือกเกรด' ด้านล่างของ text area โปรแกรมจะตัดเกรด \n " + "ให้ใหม่อีกครั้งโดยอัตโนมัติ ";
	
	String Tstr3 = "\n\n\t- ต้องการพิมพ์ผลลัพธ์ให้คลิกปุ่ม 'Print result' ข้อมูลในตาราง\n"+
		"จะถูกส่งไปพิมพ์ที่เครื่องพิมพ์\n\n"+
		" คำเตือน ..\n\n"+
		"\t**ได้กำหนดกระดาษเป็นค่ามาตรฐานไว้ที่ขนาด A4 ( 11\" x 8.5\" )** \n";
	
	public HelpPanel()
	{
	 tab1=new UserGuidePanel(userGuidePane,str1, Tstr1);
	tab2=new UserGuidePanel(userGuidePane,str2,Tstr2);
	tab3=new UserGuidePanel(userGuidePane,str3,Tstr3);
	setBorder(BorderFactory.createRaisedBevelBorder());
	userGuidePane.setForeground(Color.black);
	userGuidePane.addTab("<html><font color=#FF33CC>Step1 </font>Input Data.</html> ", tab1);
	userGuidePane.addTab("<html><font color=#FF33CC>Step2 </font> Calculating</html> ", tab2);
	userGuidePane.addTab("<html><font color=#FF33CC>Step3 </font>How to print?</html> ", tab3);
	this.add(userGuidePane);
	
	}
	
}
