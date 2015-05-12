/**
 * Created by Neveral on 05.05.15.
 */
public class SimpsonMethod {
    public int getNumberOfPartitions(double a, double b, double maxDerivative, double occuracy) {
        return (int)Math.sqrt((b-a)*(b-a)*(b-a)*(b-a)*(b-a) * maxDerivative / (2880 * occuracy));
    }

    public double integrate(double[] dots, double h) {
        double sum = 0;
        for(int i = 0; i < dots.length; ++i) {
            sum += (i==0 || i==dots.length-1 ? 1: (i+1) % 2 == 0? 4 : 2) * dots[i];
        }
        return h*sum/3;
    }
}
