package pl.mlopatka.notation.operators;

import pl.mlopatka.exception.InvalidCharacterException;

public class SimpleMathOperators implements Operators {

    private static final String INVALID_CHAR = "%c is not an operator";
    private static char ADDITION = '+';
    private static char SUBTRACTION = '-';
    private static char MULTIPLICATION = '*';
    private static char DIVISION = '/';

    @Override
    public boolean isOperator(char operator) {
        return operator == ADDITION || operator == SUBTRACTION || operator == DIVISION || operator == MULTIPLICATION;
    }

    @Override
    public double calculateOperation(char operator, double firstNr, double secondNr) {
        if(operator == ADDITION) {
            return firstNr + secondNr;
        }

        if(operator == SUBTRACTION) {
            return firstNr - secondNr;
        }

        if(operator == MULTIPLICATION) {
            return firstNr * secondNr;
        }

        if(operator == DIVISION) {
            return firstNr / secondNr;
        }

        throw new InvalidCharacterException(String.format(INVALID_CHAR, operator));
    }
}
