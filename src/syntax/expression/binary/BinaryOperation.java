package syntax.expression.binary;

import syntax.expression.Expr;

public abstract class BinaryOperation extends Expr {
    private Expr leftOperand;
    private Expr rightOperand;

    /**
     * Initialize a new generic binary Operation.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param leftOperand the left operand
     * @param rightOperand the right operand
     */
    public BinaryOperation(int leftPosition, int rightPosition, Expr leftOperand, Expr rightOperand) {
        super(leftPosition, rightPosition);
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * @return the left operand
     */
    public Expr getLeftOperand() {
        return leftOperand;
    }

    /**
     * @return  the right operand
     */
    public Expr getRightOperand() {
        return rightOperand;
    }
}
