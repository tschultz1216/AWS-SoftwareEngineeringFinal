/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aws_softwareengineeringfinal;

/**
 *
 * @author Todd
 */
public class DeductionTree {

    private String expression;

    public DeductionTree(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return this.expression;
    }

    public String evaluate() {

        String expression1;
        String expression2;
        String cleanedExpression1;
        String cleanedExpression2;
        String number1;
        String number2;
        String result;
        String newExpression;
        DeductionTree subTree;

        if (this.hasOperator(this.expression)) {
            expression1 = this.getExpression().substring(0, this.getExpression().indexOf(this.getOperator()));
            expression2 = this.getExpression().substring(this.getExpression().indexOf(this.getOperator()) + 1);
            cleanedExpression1 = this.dropLastNumber(expression1);
            cleanedExpression2 = this.dropFirstNumber(expression2);
            number1 = this.getLastNumber(expression1);
            number2 = this.getFirstNumber(expression2);
            result = this.calculate(number1, number2, this.getOperator());
            newExpression = cleanedExpression1 + result + cleanedExpression2;
            subTree = new DeductionTree(newExpression);
            return subTree.evaluate();
        }
        return expression;
    }

    /**
     * calculates a mathematical expression
     *
     * @param arg1 : numeric argument
     * @param arg2 : numeric argument
     * @param operator : operator to determine calculation performed
     * @return - String of calculated value
     */
    public String calculate(String arg1, String arg2, String operator) {
        Integer result = -1;
        Integer intArg1 = Integer.parseInt(arg1);
        Integer intArg2 = Integer.parseInt(arg2);

        if (operator == "+") {
            result = intArg1 + intArg2;
        }

        if (operator == "x") {
            result = intArg1 * intArg2;
        }
        return String.valueOf(result);
    }

    /**
     * Used to parse the last number of the 1st segment of the expression string,
     *
     * @param s - 1st of 2 binary segments created when the expression string is split on the operator
     * @return The return value is combined with the the return value of getFirstNumber for calculation
     */
    public String getLastNumber(String s) {
        if (!(s.contains("x") || s.contains("+"))) {
            return s;
        } else {
            if (s.contains("+")) {
                return s.substring(s.lastIndexOf("+") + 1);
            } else {
                return s.substring(s.lastIndexOf("x") + 1);
            }
        }
    }

    /**
     * Used to parse the first number of the 2nd segment of the expression string,
     *
     * @param s - 2nd of 2 binary segments created when the expression string is split on the operator
     * @return The return value is combined with the the return value of getLastNumber for calculation
     */
    public String getFirstNumber(String s) {
        if (!(s.contains("x") || s.contains("+"))) {
            return s;
        } else {
            if (s.contains("+")) {
                return s.substring(0, s.indexOf("+"));
            } else {
                return s.substring(0, s.indexOf("x"));
            }
        }
    }

    /**
     * Removes the numbers used in calculation from the expression. concat as exp1.drop1st ++ evalulate(num1,num2) ++ exp2.drop2nd
     *
     * @param secondExpressionHalf
     * @return
     */

    public String dropFirstNumber(String secondExpressionHalf) {
        if (!(this.hasOperator(secondExpressionHalf))) {
            return "";
        } else {
            return secondExpressionHalf.substring(this.getIndexOfFirstOperator(secondExpressionHalf), secondExpressionHalf.length());
        }
    }

    /**
     * Removes the numbers used in calculation from the expression. concat as exp1.drop1st ++ evalulate(num1,num2) ++ exp2.drop2nd
     *
     * @param secondExpressionHalf
     * @return
     */
    public String dropLastNumber(String firstExpressionHalf) {
        if (!(this.hasOperator(firstExpressionHalf))) {
            return "";
        } else {
            return firstExpressionHalf.substring(0, this.getLastIndexOfOperator(firstExpressionHalf) + 1);
        }
    }

    public int getIndexOfFirstOperator(String input) {
        if (this.hasOperator(input)) {
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'x' || input.charAt(i) == '+') {
                    return i;
                }
            }
        }
        return -1;
    }

    public int getLastIndexOfOperator(String input) {
        int returnValue = -1;
        if (this.hasOperator(input)) {
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'x' || input.charAt(i) == '+') {
                    returnValue = i;
                }
            }
        }
        return returnValue;
    }

    public String getOperator() {
        if (this.hasOperator(this.expression)) {
            if (this.expression.contains("x")) {
                return "x";
            } else {
                return "+";
            }
        }
        return "No Operator";
    }

    private boolean hasOperator(String s) {
        if (s.contains("+") || s.contains("x")) {
            return true;
        } else {
            return false;
        }
    }
}
