/* File :TScoreFrame.java
* Project: Math Tools in Java 
* Purpose: Create TScoreFrame
* Author : Wachara R.
* First Released: Fri 26 March 2010
* Last Updated :  Fri 26 March 2010
*/
package tscoreApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TScoreFrame extends JFrame {


public TScoreFrame() {
	

	Toolkit  kit = Toolkit.getDefaultToolkit();
/*	Dimension screenSize= kit.getScreenSize();
	int scrHeight = screenSize.height;
	int scrWidth = screenSize.width;
//	System.out.println("width = "+scrWidth+ "  and Height="+ scrHeight);

	setSize(scrWidth*5/10, scrHeight*60/100);
	*/
	setSize(450,700);
	setLocationByPlatform(true);
	 setResizable(false);

	Image picIcon = kit.getImage("./classes/tscoreApp/TScore.png");
	setIconImage(picIcon);
	setTitle("Normalized T-Score Calculation");

	add(new TScorePanel());
}
  	
	
 }
