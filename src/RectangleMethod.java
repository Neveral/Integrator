/**
 * Created by Neveral on 25.04.15.
 */
public class RectangleMethod {

    public int getNumberOfPartitions(double a, double b, double maxDerivative, double occuracy) {
        return (int)Math.sqrt((b-a)*(b-a)*(b-a) * maxDerivative / (24 * occuracy));
    }

    public double integrate(double[] dots, double h) {
        double sum = 0;
        for(int i = 0; i < dots.length; ++i) {
            sum += dots[i];
        }
        return h*sum;
    }

}
