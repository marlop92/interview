package pl.mlopatka.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.mlopatka.exception.EquationException;
import pl.mlopatka.exception.InvalidCharacterException;
import pl.mlopatka.notation.Notation;
import pl.mlopatka.notation.operators.Operators;
import pl.mlopatka.notation.ReversePolishNotation;
import pl.mlopatka.notation.operators.SimpleMathOperators;
import pl.mlopatka.notation.solver.EquationSolver;
import pl.mlopatka.notation.solver.EquationSolverImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EquationSolverTest {

    EquationSolver notationService = new EquationSolverImpl();

    @ParameterizedTest
    @CsvSource({
            "34+, 7",
            "842/+, 10",
            "15+2*, 12",
            "345*-, -17",
            "345*+2*, 46",
            "247*-4+5*, -110"
    })
    public void reversePolishNotionEquationShouldReturnValidOutput(String equation, double expected) {
        //given
        Operators operators = new SimpleMathOperators();
        Notation notation = new ReversePolishNotation(operators);

        //when
        double result = notationService.solveEquation(notation, equation);

        //than
        assertEquals(expected, result);
    }

    @Test
    public void invalidCharacterShouldThrowException() {
        //given
        String equation = "a2*";
        Operators operators = new SimpleMathOperators();
        Notation notation = new ReversePolishNotation(operators);

        //when

        //than
        assertThrows(InvalidCharacterException.class, () -> notationService.solveEquation(notation, equation));
    }


    @ParameterizedTest
    @CsvSource({
            "345+",
            "++"
    })
    public void invalidEquationFormatShouldThrowException(String equation) {
        //given
        Operators operators = new SimpleMathOperators();
        Notation notation = new ReversePolishNotation(operators);

        //when

        //than
        assertThrows(EquationException.class, () -> notationService.solveEquation(notation, equation));
    }

    @Test
    public void nullEquationShouldTrowException() {
        //given
        String equation = null;
        Operators operators = new SimpleMathOperators();
        Notation notation = new ReversePolishNotation(operators);

        //when

        //than
        assertThrows(EquationException.class, () -> notationService.solveEquation(notation, equation));
    }

    @Test
    public void emptyEquationShouldTrowException() {
        //given
        String equation = "";
        Operators operators = new SimpleMathOperators();
        Notation notation = new ReversePolishNotation(operators);

        //when

        //than
        assertThrows(EquationException.class, () -> notationService.solveEquation(notation, equation));
    }
}