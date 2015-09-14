import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Neveral on 23.04.15.
 */
public class Integrator {

    private RectangleMethod rect = new RectangleMethod();
    private TrapezoidMethod trap = new TrapezoidMethod();
    private SimpsonMethod simps = new SimpsonMethod();
    private Calculator calc = new Calculator();
    private Parser parser = new Parser();

    public Integrator(String integralFunc, double a, double b, double occuracy) {
        String integral = integralFunc;
        parser.setInputExpression(integral);
        List<String> polish = parser.getReversePolishNotation();
        calculateByRectMethod(a, b, occuracy, polish);
        calculateByTrapMethod(a, b, occuracy, polish);
        calculateBySimpMethod(a, b, occuracy, polish);
    }

    public static void main(String[] args) {
        System.out.println("Значение интеграла: ");
        //Пример вычисление интеграла в промежутке от 0 до 1 с точностью 0.0001
        Integrator integrator = new Integrator("log(x)*log(1+x)", 0, 1, 0.00001);

    }

    public void calculateByRectMethod(double a, double b, double occuracy, List<String> polish) {
        int n = 100;
        double h = (b-a)/n;
        ArrayList<Double> derivatives = new ArrayList<>();
        double x;

        for (int i = 1; i<=n; ++i) {
            x = a + i*h;
            derivatives.add(calc.d2f(polish, x, occuracy));
        }

        n = rect.getNumberOfPartitions(a, b, Collections.max(derivatives), occuracy);
        h = (b-a) / n;
        double[] dots = new double[n];

        for(int i = 1; i < n; ++i) {
            dots[i] = a+i*h;
            dots[i] = calc.getAnswer(polish, dots[i]-h/2);
        }
        double answer = rect.integrate(dots, h);
        System.out.println("Метод прямоугольников: " + answer);
    }

    public void calculateByTrapMethod(double a, double b, double occuracy, List<String> polish) {
        int n = 100;
        double h = (b-a)/n;
        ArrayList<Double> derivatives = new ArrayList<>();
        double x;

        for (int i = 1; i<=n; ++i) {
            x = a + i*h;
            derivatives.add(calc.d2f(polish, x, occuracy));
        }

        n = trap.getNumberOfPartitions(a, b, Collections.max(derivatives), occuracy);
        h = (b-a) / n;
        double[] dots = new double[n];

        for(int i = 1; i < n; ++i) {
            dots[i] = a+i*h;
            dots[i] = calc.getAnswer(polish, dots[i]);
        }
        double answer = trap.integrate(dots, h);
        System.out.println("Метод трапеций: " + answer);
    }

    public void calculateBySimpMethod(double a, double b, double occuracy, List<String> polish) {
        int n = 100;
        double h = (b-a)/n;
        ArrayList<Double> derivatives = new ArrayList<>();
        double x;

        for (int i = 1; i<=n; ++i) {
            x = a + i*h;
            derivatives.add(calc.d4f(polish, x, occuracy));
        }

        n = simps.getNumberOfPartitions(a, b, Collections.max(derivatives), occuracy);
        h = (b-a) / n;
        double[] dots = new double[n];

        for(int i = 1; i < n; ++i) {
            dots[i] = a + i*h;
            dots[i] = calc.getAnswer(polish, dots[i]);
        }
        double answer = simps.integrate(dots, h);
        System.out.println("Метод Симпсона: " + answer);
    }
}