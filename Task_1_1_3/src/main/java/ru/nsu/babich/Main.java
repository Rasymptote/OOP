package ru.nsu.babich;

import ru.nsu.babich.expression.Add;
import ru.nsu.babich.expression.Expression;
import ru.nsu.babich.expression.Mul;
import ru.nsu.babich.expression.Number;
import ru.nsu.babich.expression.Variable;

public class Main {

    /**
     * Program entry point.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        e.print();
        Expression de = e.derivative("x");
        de.print();
        int result = e.eval("x = 10; y = 13");
        System.out.println(result);
    }
}
