/* File :UserGuidePanel.java
* Project: Normalized T Score Calxulation.
* Purpose: Display user's guide in Normalized T score calculation
* Author : Wachara R.
* First Released: Tu 17 June 2008
* Last Updated :  Tu 1 Dec 2009
*/
package tscoreApp;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class UserGuidePanel extends JPanel {
JButton cmdLanguageChange=new JButton("Thai");
JPanel buttonPanel = new JPanel();
JTextArea  textArea = new JTextArea(25,38);  // row , column
JTabbedPane parent;
String str, Tstr;

public UserGuidePanel(JTabbedPane parent, String str1, String str2) {
super();
setLayout(new BorderLayout());
this.parent=parent;
this.str = str1;
this.Tstr = str2;
cmdLanguageChange.setForeground(Color.gray);
textArea.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
textArea.setEditable(false);
textArea.setTabSize(3);
textArea.setLineWrap(true);
if(cmdLanguageChange.getText() == "Thai") textArea.setText(str); else textArea.setText(Tstr);
cmdLanguageChange.addActionListener( new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	if(cmdLanguageChange.getText()=="Thai"){
		cmdLanguageChange.setText("English");
		textArea.setText(Tstr);
	} else {
		cmdLanguageChange.setText("Thai");
		textArea.setText(str);
	}
	}
});
buttonPanel.add(cmdLanguageChange);
this.add(buttonPanel, BorderLayout.NORTH);
this.add(textArea,BorderLayout.CENTER);
}


}