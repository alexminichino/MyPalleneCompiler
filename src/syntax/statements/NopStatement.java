package syntax.statements;

import visitor.Visitor;

public class NopStatement extends Statement{

    /**
     * Initialize a new nop node.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     */
    public NopStatement(int leftPosition, int rightPosition) {
        super(leftPosition, rightPosition);
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
