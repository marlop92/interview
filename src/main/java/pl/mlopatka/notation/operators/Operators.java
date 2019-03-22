package pl.mlopatka.notation.operators;

public interface Operators {

    boolean isOperator(char operand);

    double calculateOperation(char operand, double firstNr, double secondNr);
}
