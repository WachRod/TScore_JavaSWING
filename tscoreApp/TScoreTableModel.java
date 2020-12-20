/* File :TScoraeTableModel.java
* Project: Math Tools in Java 
* Purpose: data management for displaying in table
* Author : Wachara R.
* First Released: Sat 17 May 2008
* Last Updated :  Sat 14 June 2008
*/
package tscoreApp;

import tscore.TScore;
import javax.swing.table.*;

class TScoreTableModel extends AbstractTableModel {
String[ ]      columnNames = { "RawScore", "Frequency", "Cum Freq", "Percentile", "T Score","Grade"};
Object[][] cell;
int rowCount;
double mean;  // average value
double sd;		// standard deviation.
double[ ] za; // area under normal curve
double[ ] tscore;  // T Score
double[ ] classifiedData;  // data which being classified
int [ ] freq;  // frequency of data
int [ ] cf; // cumulated frequency
String [ ] gr;  // grade level
int [ ] gc; // grade count
// Model constructor.
public TScoreTableModel () { }
public TScoreTableModel (double[] rawScore, int numData, int gradeOption) {

	  TScore ts = new TScore( rawScore, numData,gradeOption);
		  int numDataAtLast = ts.getnumDataAtLast();
		  rowCount = numDataAtLast;
		  classifiedData = new double[numDataAtLast];	
	   classifiedData = ts.getManagedData();
		freq = new int[numDataAtLast];
		 cf = new int[numDataAtLast];		
		za = new double[numDataAtLast];
		tscore = new double[numDataAtLast];
		gr = new String[numDataAtLast];

		freq= ts.getFrequency();
		cf  = ts.getCumulativeFrequency();
		za= ts.getPercentile();
		tscore = ts.getTScore();
		gr = ts.getGrade();
		mean = ts.getMean();
		sd=ts.getStandardDeviation();
		gc = ts.getGradeCount();
     cell = new Object[numDataAtLast][6];
    for(int i= 0; i < numDataAtLast; i++) {
		cell[i][0] = String.format("%.2f",classifiedData[i]);
		cell[i][1] =String.format("%4d", freq[i]);
		cell[i][2] =  String.format("%4d",cf[i]);
		cell[i][3] =   String.format("%2.2f", za[i]*100);
		cell[i][4] = String.format("%.2f",tscore[i]);
		cell[i][5] = gr[i] ;
	} // for i

}
// Retrieve the number of rows for the table to be prepared.
public int getRowCount() {
return rowCount;
}
//  retrieve the column count.
public int getColumnCount() {
return columnNames.length;
}
//  Retrieve each of the table values at the specified
// row and column.
public Object getValueAt(int row, int column) {
	return cell[row][column];
}

//  Retrieve the column names to be used in the table header.
public String getColumnName(int column) {
	return columnNames[column];
}
public double getMean()	{	return mean; }
public double getSD() { return sd;}
public int [ ] getGradeCount() {return gc;}


}
