package pl.mlopatka.notation;

import pl.mlopatka.exception.InvalidCharacterException;
import pl.mlopatka.exception.EquationException;
import pl.mlopatka.notation.operators.Operators;

import java.util.EmptyStackException;
import java.util.Stack;

public class ReversePolishNotation implements Notation {

    private static final String INVALID_CHARACTER = "%c can't be a part of equation";
    public static final String INVALID_EQUATION = "Number of operands and operators is invalid";
    private Operators operators;

    public ReversePolishNotation(Operators operators) {
        this.operators = operators;
    }

    @Override
    public double solveEquation(String equation) {
        Stack<String> operandStack = new Stack<>();
        for (char letter : equation.toCharArray()) {
            if (isOperand(letter)) {
                operandStack.push(String.valueOf(letter));
                continue;
            }

            if (operators.isOperator(letter)) {
                double result = calculateOperation(operandStack, letter);
                operandStack.push(String.valueOf(result));
                continue;
            }

            throw new InvalidCharacterException(String.format(INVALID_CHARACTER, letter));
        }

        return getResult(operandStack);
    }

    private double getResult(Stack<String> operandStack) {
        double result = Double.parseDouble(operandStack.pop());

        if(!operandStack.empty()){
            throw new EquationException(INVALID_EQUATION);
        }
        return result;
    }

    private double calculateOperation(Stack<String> operandStack, char operator) {
        try{
            double secondOperand = Double.parseDouble(operandStack.pop());
            double firstOperand= Double.parseDouble(operandStack.pop());

            return operators.calculateOperation(operator, firstOperand, secondOperand);
        } catch (EmptyStackException emptyStackException) {
            throw new EquationException(INVALID_EQUATION);
        }
    }

    private boolean isOperand(char letter) {
        return letter >= '0' && letter <= '9';
    }

}
