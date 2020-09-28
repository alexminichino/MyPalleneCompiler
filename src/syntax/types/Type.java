package syntax.types;

import nodetype.NodeType;
import syntax.ASTNode;
import visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class Type extends ASTNode {
    /**
     * Initialize a new generic Type node.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     */
    public Type(Location leftPosition, Location rightPosition) {
        super(leftPosition, rightPosition);
    }

    public abstract String getCType();

    public abstract NodeType typeFactory();
}
