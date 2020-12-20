package tscore;

/* File : Simplestat.java
 * Project: Math Tools in Java
 * Purpose: Compute basic statistics
 *			frequency, central tendency, measure of variation.
 *			nomal curve distribution.
 * Author : Wachara R.
 * First Released: Th 3 May 2007
 * Last Updated : Tu 8 July 2008
 *     add constructor SimpleStat ( double, int)
 */

import static java.lang.Math.*;
import static java.lang.Double.*;
//import simpleStat.SimpleStatException;
//import static common.CommonConstants.*;
//import common.Function;
//import integration.*;

/** class for basic statistics algorithm:  frequency, central tendency,
 *	 measure of variation.
 *	some inference statistics*/

public  class SimpleStat {

    /** data for computing the basic statistics */ public double[ ] data;
    /** number of data */  public int  numData;
    /** number of data after finding frequency */public  int numDataAtLast;
/** basic statistic quantities */
    /** mean value */  double mean;
    /** median value */ double median;
    /** range of data */ double range;
    /** standard deviation */ double sd;
//sorted data */ double [ ] sData;
    /** managed data */  double [] mData;
    /** keeping repeated mode data */ double[] modeData;
    /** mode of data */ int mode;
    /** frequency of data set */ public int [] frequency;
    /** accumulated frequency */ public int [] cFrequency;
    /** checking data have been sorted */ protected boolean isSorted = false;
    /** checking data have been classified with frequencies */
    boolean isClassified = false;
    /** checking data have been computed for cumulated frequencies */
    protected boolean havingCumulatedFreq=false;
/** Constructor
 * @param dat data for computing basic statistics
 */
    /** Constructor */
    public SimpleStat( ) {
    }

    public SimpleStat( double [ ] dat ){
        data = dat;
        numData = dat.length;
        findMean();
        findSD();
//	sData = new double[ numData];
//	sData = copyData(data,numData);
        quickSort( data, 0,numData-1,false);
        findMedian();
        findFrequency();
        findCumulativeFrequency();
        findMode();
        findRange();

	/*
	System.out.println("Data frequency...: ");
		for(int i = 0; i < numDataAtLast; i++){
			System.out.println( mData[i] +"   " + frequency[i]);
		}

	*/
    }
    public SimpleStat( double [ ] dat, int numberOfData ){
        data = dat;
        numData = numberOfData;
        findMean();
        findSD();
        quickSort( data, 0,numData-1,false);
        findMedian();
        findFrequency();
        findCumulativeFrequency();
        findMode();
        findRange();

    }
    /** copy data from current array to another array */
    private double[ ] copyData(double[] itemToBeCopied,int nItem) {
        double[ ] dummy  = new double[ nItem];
        for(int i = 0 ; i < nItem; i++)
            dummy[i] = itemToBeCopied[i];
        return dummy;
    }

    private int[ ] copyData(int[] itemToBeCopied,int nItem) {
        int[ ] dummy  = new int[ nItem];
        for(int i = 0 ; i < nItem; i++)
            dummy[i] = itemToBeCopied[i];
        return dummy;
    }


    // ========= Frequency ==========
    /** finding frequency of each data */
    private void  findFrequency(	){
        int n_freq = 0;
        int tempfrequency[ ]  = new int[numData];
        double temp [ ]  = new double[numData];
        double dummy[] = new double [numData];
        if (isSorted == false) 			quickSort( data, 0,numData-1,false);
        temp = copyData(data, numData);
        for (int i = 0; i < numData;i++){
            if (temp[i] != MAX_VALUE) {
                tempfrequency[n_freq]=1;
                dummy[n_freq] = temp[i];
                int j = i;
                while ( (j < numData-1) && (temp[j+1] == temp[i])){
                    tempfrequency[n_freq] +=1;
                    temp[j+1] = MAX_VALUE;
                    j = j+1;
                }

                n_freq +=1;
            }

        }
        mData= new double[n_freq];
        mData = copyData ( dummy, n_freq);
        frequency = new int[n_freq];
        frequency = copyData(tempfrequency, n_freq);
        isClassified = true;
        numDataAtLast = n_freq;
    }
    /** finding cumulative frequency */
    protected void findCumulativeFrequency(){
        // if data are not sorted, sort them decendingly.
        if (isSorted == false) 			quickSort( data, 0,numData-1,true);
        cFrequency = new int[numDataAtLast];
        cFrequency[ numDataAtLast-1] =frequency[numDataAtLast-1];
        for(int i = numDataAtLast -2; i >=0; i--){
            cFrequency[i] = frequency[i] + cFrequency[i+1];
        }
        havingCumulatedFreq=true;
    }
    /** quick sort: sort  all data in ascending or decending
     * param item  data to be sorted
     * param left  the first item of data
     * param right the last item of data
     * param ascending  if true , data will be sort in ascending othrewise decending
     */
    protected void quickSort(double [ ]  item, int left, int right, boolean ascending) {
        int i,j;
        double comparand, temp;
        i = left;
        j = right;
        comparand = item[(left+right)/2];
        do { if (ascending) {
            while( item[i] < comparand && i < right) i++;
            while (comparand < item[j] && j > left) j--;
        }else {
            while( item[i] > comparand && i < right) i++;
            while (comparand > item[j] && j > left) j--;
        }
            if ( i <= j) {
                temp = item[i];
                item[i] = item[j];
                item[j] = temp;
                i++;
                j--;
            }
        }while ( i <= j );
        if (left < j) quickSort(item, left, j, ascending);
        if(i < right ) quickSort(item, i,right, ascending);
        isSorted = true;
    }
    // ============ Central tendecy =============
    /** find mean or average of data */
    private void findMean( ) {
        double sumData=0;
        for(int i = 0 ; i < numData; i++ )
            sumData +=data[i];

        mean = sumData/(double)numData;
    }
    /** find median  of data */
    private void findMedian( ) {
        if (isSorted == false) 			quickSort(data, 0,numData-1,true);
        if (numData%2 == 0)
            // number of data is even, find the average
            median = (data[numData/2] + data[(numData/2)-1])/2.0 ;
        else
            median = data[numData/2];
    }

    /** find mode  of data */
    private void findMode( ) {
        int multiMode =1;

        if (isClassified == false) 			findFrequency( );
        if(numData == numDataAtLast)  {
            modeData = new double[1];
            modeData[0] =0;
            mode = 0;
            return;
        }
// walk through classified data looking for double mode or multimode
        for (int i = 0; i < numDataAtLast; i++) {
            if(frequency[i] >= mode) {
                if (frequency[i] == mode)
                    multiMode  +=1;
                else {
                    mode = frequency[i];
                    multiMode = 1;
                }
            }
        }
        if (multiMode ==1 ) {
            modeData = new double[1];
        } else {
            modeData = new double[multiMode];
        }
        int j =0;
        for (int i = 0 ; i < numDataAtLast ; i++){
            if (frequency[i] == mode) {
                modeData[j] = mData[i];
                j = j+1;
            }
        }
    }


    // ===========Measure of variation =====
    /** find range of data */
    private void findRange(){
        if (isClassified == false) 			findFrequency( );
        range = data[0] - data[data.length-1];
    }

    /** find standard deviation of data */
    private void findSD() {
        double sumDifference=0;
        for(int i = 0; i < numData; i++){
            sumDifference += (data[i] - mean)*(data[i]-mean);
        }
        sd = sqrt(sumDifference/(double)(numData-1));
    }


// ========== Some parameter inference statistics ==========

    /** Compute the area between 2 statistic z value
     * @param lower_z lower limit of area
     * @param upper_z upper limit of area
     * @ return area under normal curve between 2 value of z
     */

    public double findAreaUnderNormalCurve(double lower_z, double upper_z) {

        Function normalFunction = new Function() {
            public double Of(double x) {
                return (1/sqrt(2.0*PI)*exp(-(x*x)/2.0));}
        };
        Simpson1_3Integration si = new Simpson1_3Integration (normalFunction,lower_z, upper_z,1000);
        return (si.getIntegrationResult());
    }

    /** finding statistic Z of any data x
     * @param x   any data x
     * @return z value statistic Z
     */
    public double findZ(double x){
        return ( x - mean)/sd;
    }

    /** finding statistic Z value at known area under normal curve
     * @param area  the area under normal curve should be between 0 to 1.
     * @return z value at known area
     */
    public double findZAtKnownArea (double area) throws SimpleStatException {
        boolean isAreaGreaterThanHalf = false;
        double lowerLimit=0;
        double newArea=0;
        double deltaX;
        double z=0 , sumArea=0;
        double dA;
        if ( area < 0 || area >1 ) throw new SimpleStatException(SimpleStatException.BAD_AREA);
        if (area > 0.5){
            newArea = area - 0.5;
            isAreaGreaterThanHalf = true;
        } else {
            newArea = 0.5 - area;
        }

        if (newArea >= 0.01993880583837) {
            lowerLimit = 0.05;
            sumArea = 0.01993880583837;
        }
        if (newArea >= 0.09870632568292) {
            lowerLimit = 0.25;
            sumArea = 0.09870632568292;
        }
        if (newArea >= 0.19146246127401) {
            lowerLimit = 0.5;
            sumArea = 0.19146246127401;
        }
        if (newArea >= 0.34134474606854 ) {
            lowerLimit = 1.0;
            sumArea = 0.34134474606854;
        }
        if (newArea >= 0.43319279873114) {
            lowerLimit = 1.5;
            sumArea = 0.43319279873114;
        }
        if (newArea >= 0.47724986805182) {
            lowerLimit = 2;
            sumArea = 0.47724986805182;
        }

        if (newArea >= 0.48609655248650) {
            lowerLimit = 2.2;
            sumArea = 0.48609655248650;
        }
        if (newArea >= 0.48927588997832) {
            lowerLimit = 2.3;
            sumArea = 0.48927588997832;
        }
        if (newArea >= 0.49180246407540) {
            lowerLimit = 2.4;
            sumArea = 0.49180246407540;
        }
        if (newArea >= 0.49379033467422) {

            lowerLimit = 2.5;
            sumArea = 0.49379033467422;
        }
        if (newArea >=0.49653302619696) {
            lowerLimit = 2.7;
            sumArea = 0.49653302619696;
        }
        if (newArea >=0.49744486966957) {
            lowerLimit = 2.8;
            sumArea =0.49744486966957 ;
        }
        if (newArea >=0.49813418669962) {
            lowerLimit = 2.9;
            sumArea =0.49813418669962 ;
        }
        if (newArea >= 0.49865010196837) {
            lowerLimit = 3;
            sumArea = 0.49865010196837;
        }
        if (newArea >=0.49903239678678 ) {
            lowerLimit = 3.1;
            sumArea = 0.49903239678678;
        }
        if (newArea >= 0.49931286206208) {
            lowerLimit = 3.2;
            sumArea = 0.49931286206208;
        }
        if (newArea >= 0.49951657585762) {
            lowerLimit = 3.3;
            sumArea = 0.49951657585762;
        }
        z = lowerLimit;
        deltaX = 0.00001;
        while( newArea - sumArea > CommonConstants.DEFAULT_TOLERANCE) {
            dA = (1/sqrt(2*PI))*0.5*deltaX*(exp(-0.5*z*z) + exp(-0.5*(z+deltaX)*(z+deltaX)));
            sumArea += dA;
            z += deltaX;
        }
        //	System.out.println(" z in method = " + z);
        //			System.out.println(" Sumarea = " + sumArea);

        if (isAreaGreaterThanHalf)  return z+deltaX;
        else return -(z+deltaX);
    }

    // ========= Getter ==============
    public double getMean(){ return mean;}
    public double getMedian() { return median;}
    public double getStandardDeviation( ){ return sd;}
    public double getRange() { return range;}
    public int getFrequencyOfMode() { return mode;}
    public double[] getModeData(){ return modeData;}
    public double[] getSortedData( ) { return data;}
    public double[]getManagedData() { return mData;}
    public int[ ] getFrequency() { return frequency;}
    public int[] getCumulativeFrequency() { return cFrequency;};
    public int getnumDataAtLast() { return numDataAtLast;};

}


