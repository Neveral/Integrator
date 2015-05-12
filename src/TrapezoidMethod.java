/**
 * Created by Neveral on 05.05.15.
 */
public class TrapezoidMethod {
    public int getNumberOfPartitions(double a, double b, double maxDerivative, double occuracy) {
        return (int)Math.sqrt((b-a) * (b-a) * (b-a) * maxDerivative / (12 * occuracy));
    }

    public double integrate(double[] dots, double h) {
        double sum = 0;
        for(int i = 0; i < dots.length; ++i) {
            sum += (i==0 || i==dots.length-1 ? 0.5:1)*dots[i];
        }
        return h*sum;
    }
}
