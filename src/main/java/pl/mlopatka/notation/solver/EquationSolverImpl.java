package pl.mlopatka.notation.solver;

import pl.mlopatka.exception.EquationException;
import pl.mlopatka.notation.Notation;

public class EquationSolverImpl implements EquationSolver {

    @Override
    public double solveEquation(Notation notation, String equation) {
        if (equation == null || "".equals(equation)) {
            throw new EquationException("Equation can't be empty");
        }

        return notation.solveEquation(equation);
    }
}
