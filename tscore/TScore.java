/* File : TScore.java
* Project: Math Tools in Java 
* Purpose: Compute Normalized Tscore and  
*			and Grade Assesment
* Author : Wachara R.
* First Released: Sun 9 March 2008
* Last Updated : Tue 24 May 2008
*/

package tscore;

import simpleStat.SimpleStat;
import simpleStat.SimpleStatException;


/** class for computing nomalized Tscore and 
*	  appropriated grade  assesment
*	*/

public  class TScore extends SimpleStat {
	/** Percentile */ private double [ ] Percentile;
	/** TScore */      private double [ ] Tscore;
	/** Grade Option */  private int gradeOption=0;
	/** Grade assessment of each score */ private String [ ] grade;
	/** Grade Counting */  private int [ ] gradeCount;;


/** Constructor
* @param dat data for computing basic statistics
*/
/** Constructor */
public TScore( ) {
	super();
}

public TScore( double[ ] dat) {
	super(dat);
	findPercentile();
	computeTScore();
	assessGrade();
}

public TScore( double[ ] dat, int nData, int grOption) {
	super(dat,nData);
	gradeOption = grOption;
	findPercentile();
	computeTScore();
	assessGrade();
	countGrade();
}
/** finding  percentiles*/

void findPercentile(){
	// if data are not sorted, sort them descendingly.
if (isSorted == false) 			quickSort( data, 0,numData-1,true);
 // if data  are not computed cumulated frequency , compute them
if(havingCumulatedFreq=false) findCumulativeFrequency();
	Percentile = new double[numData];
	
	for(int i = numDataAtLast -1; i >=0; i--){
		if ( i == numDataAtLast-1)
			Percentile[i] = 0.5*frequency[i]/numData ;
			else
			Percentile[i] = (cFrequency[i+1] + 0.5* frequency[i] )/numData;
	}
}

void computeTScore() {
	Tscore = new double[numDataAtLast];
	try
	{
		for (int i=0;i < numDataAtLast ;i++ )		{
			Tscore[i]=	findZAtKnownArea (Percentile[i]) ;
			// Change  z value to T score.
			Tscore[i] = Tscore[i]*10.0 +50.0;
		}
	}
	catch (SimpleStatException err)
	{	}

}

void  assessGrade( )  {
double difference, range;

   		 difference = Tscore[0] - Tscore[numDataAtLast -1];
		 if (difference < 0)   difference = -difference;

   grade = new String[numDataAtLast];
    switch ( gradeOption)    {
    case 0 : {
				range = difference/5.0;
				for(int i = 0 ; i < numDataAtLast ; i++) {
					grade[ i ] = "F";
					if (Tscore[i] >= 50.0 -1.5*range)  grade[i] = "D";
					if (Tscore[i] >= 50.0 -1.0*range)  grade[i] = "D+";
					if (Tscore[i] >= 50.0 -0.5*range)  grade[i] = "C";
					if (Tscore[i] >= 50.0)  grade[i] = "C+";
					if (Tscore[i] >= 50.0 +0.5*range)  grade[i] = "B";
					if (Tscore[i] >= 50.0 +1.0*range)  grade[i] = "B+";
					if (Tscore[i] >= 50.0 +1.5*range)  grade[i] = "A";
				}
				break;
				}
	case 1: {
			range = difference/5.0;
		    for(int i = 0 ; i < numDataAtLast ; i++) {
					grade[ i ] = "F";
					if (Tscore[i] >= 50.0 -1.5*range) 				grade[i] = "D";
					if (Tscore[i] >= 50.0 -0.5*range) 				grade[i] = "C";
					if (Tscore[i] >= 50.0 +0.5*range) 			grade[i] = "B";
					if (Tscore[i] >= 50.0 +1.5*range)				grade[i] = "A";
			}
				break;
				}
    case 2: {
			range = difference/4.0;
			for(int i = 0 ; i < numDataAtLast ; i++) {
					grade[ i ] = "D";
							
					if (Tscore[i] >= 50.0 -1.25*range)  grade[i] = "D+";
					if (Tscore[i] >= 50.0 -1.0*range)  grade[i] = "C";
					if (Tscore[i] >= 50.0 -0.5*range)  grade[i] = "C+";
					if (Tscore[i] >= 50.0)  grade[i] = "B";
					if (Tscore[i] >= 50.0 +0.5*range)  grade[i] = "B+";
					if (Tscore[i] >= 50.0 +1.0*range)  grade[i] = "A";
		
				}
				break;
				}
    
case 3: {
		range = difference/4.0;
		for(int i = 0 ; i < numDataAtLast ; i++) {
					grade[ i ] = "D";
					if (Tscore[i] >= 50.0 -1.0*range)  grade[i] = "C";
					if (Tscore[i] >= 50.0)  grade[i] = "B";
					if (Tscore[i] >= 50.0 +1.0*range)  grade[i] = "A";
				}
				break;
				}
case 4: {
		range =difference/3.0;
	for(int i = 0 ; i < numDataAtLast ; i++) {
					grade[ i ] = "C";

				if (Tscore[i] >= 50.0 -0.75*range)  grade[i] = "C+";
					if (Tscore[i] >= 50.0 -0.5*range)  grade[i] = "B";
					if (Tscore[i] >= 50.0)  grade[i] = "B+";
					if (Tscore[i] >= 50.0 +0.5*range)  grade[i] = "A";
				}
				break;
				}
case 5 : {
			range = difference/3.0;
			for(int i = 0 ; i < numDataAtLast ; i++) {
					grade[ i ] = "C";
					if (Tscore[i] >= 50.0 -0.5*range)  grade[i] = "B";
					if (Tscore[i] >= 50.0 +0.5*range)  grade[i] = "A";
				}
				break;
				}
   case 6 : {
			range = difference/2.0;
			for(int i = 0 ; i < numDataAtLast ; i++) {
					grade[ i ] = "B";
					if (Tscore[i] >= 50.0 )  grade[i] = "A";
				}
				break;
				}
	} //  end switch
}

void countGrade() {
	gradeCount  = new int[8];
	for (int i=0; i <numDataAtLast; i++) {
		if(grade[i].equals("A")) gradeCount[0]+= frequency[i];
		if(grade[i].equals("B+")) gradeCount[1]+= frequency[i];
		if(grade[i].equals("B")) gradeCount[2]+= frequency[i];
		if(grade[i].equals("C+")) gradeCount[3]+= frequency[i];
		if(grade[i].equals("C")) gradeCount[4]+= frequency[i];
		if(grade[i].equals("D+")) gradeCount[5]+= frequency[i];
		if(grade[i].equals("D")) gradeCount[6]+= frequency[i];
		if(grade[i].equals("F")) gradeCount[7]+= frequency[i];
	}
}

public double[ ] getPercentile( ) { return Percentile;}
public double[ ] getTScore( ) { return Tscore;}
public String[ ] getGrade() {return grade; }
public int [ ]   getGradeCount() {return gradeCount;}
public int getGradeOption() { return gradeOption;}
public void setGradeOption(int gOption) { 
	gradeOption = gOption;
	if(gradeOption < 0 || gradeOption > 6)
			gradeOption=0;
}

}

