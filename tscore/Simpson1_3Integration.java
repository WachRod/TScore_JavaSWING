package tscore;
/* File :Simpson1_3Integration.java
 * Project: Math Tools in Java
 * Author : Wachara R.
 * First Released: Thu 8 Mar 2007.
 * Last Updated : Thu 8 Mar 2007.
 */

//import common.Function;

/** Integration of given function from lower limit
 to upper limit using the Simpson 1/3 method.
 */
public class Simpson1_3Integration extends Integrator{

    /** Constructor.
     * @param function the function to integration.
     * @param lower the lower limit of integration.
     * @param upper the upper limit of integration.
     * @param intervals the number of equal-width intervals
     */
    public Simpson1_3Integration (Function function, double lower, double upper, int intervals){
        super(function, lower, upper,intervals);
        try {
            IntegrationResult = integrate();
        } catch (IntegrationException msg) {
            System.out.println( "Error : "+ msg);
        }
    }
    double integrate() throws IntegrationException {
        if(intervals <=0) throw new IntegrationException(IntegrationException.INVALID_INTERVALS);
        double h = (upper - lower)/(2*intervals); // half of interval width
        double area = 0;
        for(int i = 0; i < intervals; i++){
            double x1 = lower + 2*i*h;
            area += findAreaUnderCurve(x1,h);
        }
        return area;
    }
    /** Evaluate the area under parabolic curve
     * @param x1 the left bound of the region
     * @param h the interval width
     */
    double findAreaUnderCurve(double x1, double h){
        // area of the parabolic region = h/3(f(-h) +4f(0) +f(h))

        double x2 = x1 + h; // middle point
        double x3 = x2 + h; // rightmost point
        double y1 = function.Of(x1);
        double y2 = function.Of(x2);
        double y3 = function.Of(x3);

        return (h*(y1 + 4*y2 +y3)/3);  // area under parabolic curve element
    }
}

