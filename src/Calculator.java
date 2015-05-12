import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Neveral on 23.04.15.
 * Получает на вход выражение (List<String>) в обратной польской нотации и производит вычисление
 */
public class Calculator {
    private String[] standartOperators = {"+", "-", "*", "/", "^", "s", "c", "l"};
    private LinkedList<String> operators = new LinkedList(Arrays.asList(standartOperators));

    public double getAnswer(List<String> inputExpression) {
        Stack<String> stack = new Stack();
        for(int i = 0; i < inputExpression.size(); ++i) {
            if(operators.contains(inputExpression.get(i))) {
                double secondArg;
                double firstArg;
                double result;
                switch (inputExpression.get(i)) {
                    case "+":
                        secondArg = Double.parseDouble(stack.pop());
                        firstArg = Double.parseDouble(stack.pop());
                        result =  firstArg + secondArg;
                        stack.push(String.valueOf(result));
                        break;
                    case "-":
                        secondArg = Double.parseDouble(stack.pop());
                        firstArg = Double.parseDouble(stack.pop());
                        result =  firstArg - secondArg;
                        stack.push(String.valueOf(result));
                        break;
                    case "*":
                        secondArg = Double.parseDouble(stack.pop());
                        firstArg = Double.parseDouble(stack.pop());
                        result =  firstArg * secondArg;
                        stack.push(String.valueOf(result));
                        break;
                    case "/":
                        secondArg = Double.parseDouble(stack.pop());
                        firstArg = Double.parseDouble(stack.pop());
                        result =  firstArg / secondArg;
                        stack.push(String.valueOf(result));
                        break;
                    case "^":
                        secondArg = Double.parseDouble(stack.pop());
                        firstArg = Double.parseDouble(stack.pop());
                        result =  Math.pow(firstArg, secondArg);
                        stack.push(String.valueOf(result));
                        break;
                    case "s":
                        secondArg = Double.parseDouble(stack.pop());
                        result =  Math.sin(secondArg);
                        stack.push(String.valueOf(result));
                        break;
                    case "c":
                        secondArg = Double.parseDouble(stack.pop());
                        result =  Math.cos(secondArg);
                        stack.push(String.valueOf(result));
                        break;
                    case "l":
                        secondArg = Double.parseDouble(stack.pop());
                        result =  Math.log(secondArg);
                        stack.push(String.valueOf(result));
                        break;
                }
            }
            else {
                stack.push(inputExpression.get(i));
            }
        }
        return Double.parseDouble(stack.peek());
    }

    public double getAnswer(List<String> inputExpression, double argument) {
        Stack<String> stack = new Stack();
        for(int i = 0; i < inputExpression.size(); ++i) {
            if(operators.contains(inputExpression.get(i))) {
                double secondArg;
                double firstArg;
                double result;
                switch (inputExpression.get(i)) {
                    case "+":
                        secondArg = Double.parseDouble(stack.pop());
                        firstArg = Double.parseDouble(stack.pop());
                        result =  firstArg + secondArg;
                        stack.push(String.valueOf(result));
                        break;
                    case "-":
                        secondArg = Double.parseDouble(stack.pop());
                        firstArg = Double.parseDouble(stack.pop());
                        result =  firstArg - secondArg;
                        stack.push(String.valueOf(result));
                        break;
                    case "*":
                        secondArg = Double.parseDouble(stack.pop());
                        firstArg = Double.parseDouble(stack.pop());
                        result =  firstArg * secondArg;
                        stack.push(String.valueOf(result));
                        break;
                    case "/":
                        secondArg = Double.parseDouble(stack.pop());
                        firstArg = Double.parseDouble(stack.pop());
                        result =  firstArg / secondArg;
                        stack.push(String.valueOf(result));
                        break;
                    case "^":
                        secondArg = Double.parseDouble(stack.pop());
                        firstArg = Double.parseDouble(stack.pop());
                        result =  Math.pow(firstArg, secondArg);
                        stack.push(String.valueOf(result));
                        break;
                    case "s":
                        secondArg = Double.parseDouble(stack.pop());
                        result =  Math.sin(secondArg);
                        stack.push(String.valueOf(result));
                        break;
                    case "c":
                        secondArg = Double.parseDouble(stack.pop());
                        result =  Math.cos(secondArg);
                        stack.push(String.valueOf(result));
                        break;
                    case "l":
                        secondArg = Double.parseDouble(stack.pop());
                        result =  Math.log(secondArg);
                        stack.push(String.valueOf(result));
                        break;
                }
            }
            else {
                if(inputExpression.get(i).equals("x")|| inputExpression.get(i).equals("X"))
                    stack.push(String.valueOf(argument));
                else
                    stack.push(inputExpression.get(i));
            }
        }
        return Double.parseDouble(stack.peek());
    }

    public double df (List<String> inputFunc, double argument, double accuracy) {
        return (getAnswer(inputFunc, argument-accuracy)-getAnswer(inputFunc, argument+accuracy))/(2*accuracy);
    }
    public double d2f (List<String> inputFunc, double argument, double accuracy) {
        return (df(inputFunc, argument - accuracy, accuracy)-df(inputFunc, argument + accuracy, accuracy))/(2*accuracy);
    }
    public double d3f (List<String> inputFunc, double argument, double accuracy) {
        return (d2f(inputFunc, argument-accuracy, accuracy)-d2f(inputFunc, argument+accuracy, accuracy))/(2*accuracy);
    }
    public double d4f (List<String> inputFunc, double argument, double accuracy) {
        return (d3f(inputFunc, argument-accuracy, accuracy)-d3f(inputFunc, argument+accuracy, accuracy))/(2*accuracy);
    }
}
