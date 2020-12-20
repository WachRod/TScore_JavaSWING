/* File : ScoreTextArea.java
* Project: T Score Calculation
* Purpose: Raw score input in  JTextArea  and then  
*			converse them to number.
* Author : Wachara R.
* First Released: Sun 9 March 2008
* Last Updated : Tue 7 November 2009
*/
package tscore;

import javax.swing.*;

import java.awt.*;
import static tscore.TScoreConstants.*;

public class  ScoreTextArea extends JTextArea
{
Font textFont = new Font("Dialog", Font.PLAIN, 12);
int NumberOfScore;
private double [ ] Score  = new double[MAXIMUM_NUMBER_OF_DATA+1];

public ScoreTextArea ()
	{	
			setFont(textFont);
			setBackground(Color.white);
			setSelectionColor(Color.pink);
			setLineWrap(false); // Wrap the line at the container end.
	}
public int readScore( )
	{
	String  score = super.getText();
    char[] ch = score.toCharArray(); 
	String temp="";
	boolean IN_COMMENT=false;
	boolean  IN_DIGIT = false;
	int point = 0;
	int line = 0;
	int n = 0;

		for(int i =0; i < ch.length ; i++)
	{
    switch (ch[i])
		{
		case '*' :
			IN_COMMENT = true;
			break;
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
		case '.':
			
			if (!IN_COMMENT)
		{
			IN_DIGIT =true;
			if (ch[i] == '.')  point +=1;
		temp = temp + ch[i];
			if (point > 1)   {
				displayError ("Some score not (a) numbers at line"+(line+1), "Error");
			return -1;
			}
		}
		break;
		
		case ' ':
		case '\n':
			if (ch[i] == '\n') 
		{
			line = line +1;
			IN_COMMENT = false;
		}
		if (!(IN_COMMENT ) && IN_DIGIT)
		{
			point = 0;
		//	if (temp != "")
		if  (temp != ""  &&  temp != " ") {
				Score[n] = Double.parseDouble(temp);
				temp = "";
				n=n+1;
				if ( n > (MAXIMUM_NUMBER_OF_DATA)) {
					displayError ("Sorry! Your data exceed  "+(MAXIMUM_NUMBER_OF_DATA)+"\nPlease truncate your data","Error" );
				return -1;
				} // if (n >=.....

			}
			IN_DIGIT = false;
		}
		break;
		default :
			if (!IN_COMMENT)
			{
				displayError("Error at line"+(line+1), "Error");
				return -1;
			}
			break;
		} //switch
		
      if ( i == (ch.length-1) && ch[i] != '\n'  && ch[i] != ' ') {   // does'nt work  --> && ch[i] !=null
		  Score[n] = Double.parseDouble(temp);
				n=n+1;
				if ( n > (MAXIMUM_NUMBER_OF_DATA)) {
						displayError ("Sorry! Your data exceed  "+(MAXIMUM_NUMBER_OF_DATA)+"\nPlease truncate your data","Error" );
				return -1;
				} // if (n >=.....
	  }

			}// for loop
			return n;
	}
	public double [ ]  getScore()
	{
		return Score;
	}

	  void displayError( String msgErr, String msgTitle) {
		  JOptionPane.showMessageDialog(null, msgErr, msgTitle,JOptionPane.ERROR_MESSAGE);
    }
}
