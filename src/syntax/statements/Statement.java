package syntax.statements;

import syntax.ASTNode;

public abstract class Statement  extends ASTNode {
    /**
     * Initialize a new generic AST node.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     */
    public Statement(int leftPosition, int rightPosition) {
        super(leftPosition, rightPosition);
    }
}
