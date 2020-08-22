package syntax.expression.unary;

import syntax.expression.Expr;

public abstract class UnaryOperation extends Expr {
    private Expr expr;
    /**
     * Initialize a new generic unary Operation.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param expr the expression
     */
    public UnaryOperation(int leftPosition, int rightPosition, Expr expr) {
        super(leftPosition, rightPosition);
        this.expr = expr;
    }

    /**
     * @return the expression
     */
    public Expr getExpr() {
        return expr;
    }
}
