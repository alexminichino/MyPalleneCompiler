package syntax.expression;

import nodetype.NodeType;
import syntax.ASTNode;
import visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class Expr extends ASTNode {
    NodeType type;
    /**
     * Initialize a new generic Expr AST node.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     */
    public Expr(Location leftPosition, Location rightPosition) {
        super(leftPosition, rightPosition);
    }

    public NodeType getNodeType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }
}
