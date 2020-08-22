package syntax.expression;

import syntax.ASTNode;
import visitor.Visitor;

public abstract class Expr extends ASTNode {
    /**
     * Initialize a new generic Expr AST node.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     */
    public Expr(int leftPosition, int rightPosition) {
        super(leftPosition, rightPosition);
    }
}
