package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Element;

public abstract class ASTNode implements Element{
    private Location leftPosition;
    private Location rightPosition;

    /**
    * Initialize a new generic AST node.
    *
    * @param leftPosition  the left position
    * @param rightPosition the right position
    */
    public ASTNode(Location leftPosition, Location rightPosition) {
        this.leftPosition = leftPosition;
        this.rightPosition = rightPosition;
    }

    /**
    * @return The left position
    */
    public Location getLeftPosition() {
        return leftPosition;
    }

    /**
    * @return The right position
    */
    public Location getRightPosition() {
        return rightPosition;
    }
}
