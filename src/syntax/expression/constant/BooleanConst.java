package syntax.expression.constant;

import syntax.Leaf;
import syntax.expression.Expr;
import visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class BooleanConst extends Expr implements Leaf<Boolean> {
    private boolean value;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param value the value
     */
    public BooleanConst(Location leftPosition, Location rightPosition, boolean value) {
        super(leftPosition, rightPosition);
        this.value = value;
    }

    /**
     * @return the value
     */
    @Override
    public Boolean getValue() {
        return value;
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
