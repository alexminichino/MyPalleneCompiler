package syntax.expression.constant;

import syntax.Leaf;
import syntax.expression.Expr;
import visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class StringConst extends Expr implements Leaf<String> {
    private String value;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param value the value
     */
    public StringConst(Location leftPosition, Location rightPosition, String value) {
        super(leftPosition, rightPosition);
        this.value = value;
    }


    /**
     * @return the value
     */
    @Override
    public String getValue() {
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
