package syntax.statements;

import syntax.expression.Expr;
import visitor.Visitor;

public class ReturnStatement extends Statement {
    private Expr returnExpression;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param returnExpression
     */
    public ReturnStatement(int leftPosition, int rightPosition, Expr returnExpression) {
        super(leftPosition, rightPosition);
        this.returnExpression = returnExpression;
    }

    /**
     * @return the return expression
     */
    public Expr getReturnExpression() {
        return returnExpression;
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
