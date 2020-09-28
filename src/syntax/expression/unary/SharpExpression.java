package syntax.expression.unary;

import syntax.expression.Expr;
import visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class SharpExpression extends UnaryOperation{
    /**
     * Initialize a new generic unary Operation.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param expr          the expression
     */
    public SharpExpression(Location leftPosition, Location rightPosition, Expr expr) {
        super(leftPosition, rightPosition, expr);
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
