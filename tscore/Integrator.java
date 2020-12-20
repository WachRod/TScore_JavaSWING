package tscore;
/* File : Integrator.java
 * Project: Math Tools in Java
 * Purpose: Abstract class for Integration
 * Author : Wachara R.
 * First Released: Wed 07 Mar 2007
 * Last Updated : Wed 07 Mar 2007
 */

//import common.Function;

/** Abstract class for integration */

public abstract class Integrator {
    /** Function to integrate */				Function function;
    /** the number of equal-width intervals */  int intervals;
    /** the lower limit of integration */			 double lower;
    /** the upper limit of integration */			 double upper;
    /** Integration results */					 double IntegrationResult;

    Integrator() { }

    Integrator( Function f, double a, double b, int n){
        this.function = f;
        this.lower = a;
        this.upper = b;
        this.intervals = n;
    }
    /** getting the Integration result */
    public double getIntegrationResult(){ return IntegrationResult;}

    /** Compute the area at nth   region */
    abstract double integrate()throws IntegrationException;
    abstract double findAreaUnderCurve(double x_left, double h);
}


