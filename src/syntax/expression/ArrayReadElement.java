package syntax.expression;

import visitor.Visitor;

public class ArrayReadElement extends Expr{
    private Expr arrayAssignExpr;
    private Expr indexExpression;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param arrayAssignExpr expression of assignment
     * @param indexExpression
     */
    public ArrayReadElement(int leftPosition, int rightPosition, Expr arrayAssignExpr, Expr indexExpression) {
        super(leftPosition, rightPosition);
        this.arrayAssignExpr = arrayAssignExpr;
        this.indexExpression = indexExpression;
    }

    /**
     * @return the array assign
     */
    public Expr getArrayAssignExpr() {
        return arrayAssignExpr;
    }

    /**
     * @return the index exprression
     */
    public Expr getIndexExpression() {
        return indexExpression;
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
