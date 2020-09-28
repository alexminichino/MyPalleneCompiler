package syntax.statements;

import syntax.expression.Expr;
import visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class ArrayAssignStatement extends Statement {
    private Expr arrayExpression;
    private Expr arrayPointExpression;
    private Expr assignExpression;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param arrayExpression
     * @param arrayPointExpression
     * @param assignExpression
     */
    public ArrayAssignStatement(Location leftPosition, Location rightPosition, Expr arrayExpression, Expr arrayPointExpression, Expr assignExpression) {
        super(leftPosition, rightPosition);
        this.arrayExpression = arrayExpression;
        this.arrayPointExpression = arrayPointExpression;
        this.assignExpression = assignExpression;
    }

    /**
     * @return
     */
    public Expr getArrayExpression() {
        return arrayExpression;
    }

    /**
     * @return
     */
    public Expr getArrayPointExpression() {
        return arrayPointExpression;
    }

    /**
     * @return
     */
    public Expr getAssignExpression() {
        return assignExpression;
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
