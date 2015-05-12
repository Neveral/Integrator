import java.util.*;

/**
 * Created by Neveral on 23.04.15.
 */
public class Parser {
    private  String inputExpression;
    private List<String> outputExpression = new LinkedList<>();
    private String[] standartOperators = {"(", ")", "+", "-", "*", "/", "^", "s", "c", "l"};
    private List<String> operators = new LinkedList(Arrays.asList(standartOperators));

    public Parser() {
        this.inputExpression = "";
    }

    public Parser(String InputExpression) {
      this.inputExpression = inputExpression;
    }

    public void setInputExpression(String inputExpression) {
        this.inputExpression = inputExpression;
    }

    public List<String> getReversePolishNotation() {
        prepareinputExpression(); // delete spaces and replace ',' to '.'
        Stack<Character> stack = new Stack();
        StringBuilder number = new StringBuilder(); // накапливает число из символов цифр
        boolean mayUnary = true; // может ли текущий оператор быть унарным
        for(int i=0; i< inputExpression.length(); ++i) {
            if(inputExpression.charAt(i) == ')') {
                while(stack.peek() != '(') {
                    outputExpression.add(String.valueOf(stack.pop()));
                }
                stack.pop();
                mayUnary = false;
            }
            else if(inputExpression.charAt(i) == '(') {
                stack.push(inputExpression.charAt(i));
                mayUnary = true;
            }
            else if(operators.contains(String.valueOf(inputExpression.charAt(i)))) {
                /*if(mayUnary)
                    stack.push('0');*/ //унарные доделать!!! Приоритет неправильный
                if(stack.isEmpty()) {
                    stack.push(inputExpression.charAt(i));
                }
                else {
                    if (getPriority(String.valueOf(stack.peek())) < getPriority(String.valueOf(inputExpression.charAt(i))))
                        stack.push(inputExpression.charAt(i));
                    else {
                        while(!stack.isEmpty() && getPriority(String.valueOf(stack.peek())) >= getPriority(String.valueOf(inputExpression.charAt(i)))) {
                            outputExpression.add(String.valueOf(stack.pop()));
                        }
                        stack.push(inputExpression.charAt(i));
                    }
                }
                if(inputExpression.charAt(i) == 's' || inputExpression.charAt(i) == 'c' || inputExpression.charAt(i) == 'l') {
                    i = i + 2;
                }
                mayUnary = true;
            }
            else { // if next input value is digit or variable;
                number.setLength(0);
                while (isDigit(inputExpression.charAt(i))) {
                    number.append(inputExpression.charAt(i));
                    i++;
                    if(i == inputExpression.length())
                        break;
                }
                i--;
                outputExpression.add(number.toString());
                mayUnary = false;
            }


        }
        while (!stack.isEmpty()) {
            outputExpression.add(String.valueOf(stack.pop()));
        }
        return outputExpression;
    }

    public boolean isDigit(char ch) {
        //return (ch >= '0' && ch <='9')?true:false;
        if (!operators.contains(String.valueOf(ch)))
            return true;
            else
            return false;
    }

    public int getPriority(String c) {
        switch(c) {
            case "(":
            case ")":
                return 0;
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
            case "s":
            case "c":
            case "l":
                return 3;
            default: return 4;
        }
    }

    public void prepareinputExpression() {
        inputExpression = inputExpression.replaceAll(" ", "");
        inputExpression = inputExpression.replaceAll(",", ".");
    }
}
