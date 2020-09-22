package syntax.statements;

import syntax.ASTNode;
import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class Statement  extends ASTNode {
    /**
     * Initialize a new generic AST node.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     */
    public Statement(Location leftPosition, Location rightPosition) {
        super(leftPosition, rightPosition);
    }
}
