package syntax.expression.binary.arithmetic;

import syntax.expression.Expr;
import syntax.expression.binary.BinaryOperation;
import visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class MinusOperation extends BinaryOperation {
    /**
     * Initialize a new generic binary Operation.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param leftOperand   the left operand
     * @param rightOperand  the right operand
     */
    public MinusOperation(Location leftPosition, Location rightPosition, Expr leftOperand, Expr rightOperand) {
        super(leftPosition, rightPosition, leftOperand, rightOperand);
    }

    /**
     * Implement interface method
     * @param visitor the visitor
     * @param arg argoment
     * @param <T> Visitor param T
     * @param <P> Visitor param P
     * @return the visited element
     */
    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P arg) {
        return visitor.visit(this, arg);
    }
}
