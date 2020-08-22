package syntax;

import visitor.Element;

public abstract class ASTNode implements Element{
    private int leftPosition;
    private int rightPosition;

    /**
    * Initialize a new generic AST node.
    *
    * @param leftPosition  the left position
    * @param rightPosition the right position
    */
    public ASTNode(int leftPosition, int rightPosition) {
        this.leftPosition = leftPosition;
        this.rightPosition = rightPosition;
    }

    /**
    * @return The left position
    */
    public int getLeftPosition() {
        return leftPosition;
    }

    /**
    * @return The right position
    */
    public int getRightPosition() {
        return rightPosition;
    }
}
