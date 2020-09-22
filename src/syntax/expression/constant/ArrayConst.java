package syntax.expression.constant;

import syntax.expression.Expr;
import syntax.types.Type;
import visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class ArrayConst extends Expr {
    private Type type;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param type type
     */
    public ArrayConst(Location leftPosition, Location rightPosition, Type type) {
        super(leftPosition, rightPosition);
        this.type = type;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
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
