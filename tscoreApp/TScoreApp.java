/* File :TScoreApp.java
* Project: Math Tools in Java 
* Purpose: Show how to use Normalized T score calculation
* Author : Wachara R.
* First Released: Wed 4 June 2008
* Last Updated :  Fri 23 March 2008
*/
package tscoreApp;
import javax.swing.*;
import java.awt.*;

public class TScoreApp  {

  	 public static void main(String args[ ]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			TScoreFrame tsf = new TScoreFrame();
			tsf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			tsf.setVisible(true);
		}
		});
	 } // main
 }
