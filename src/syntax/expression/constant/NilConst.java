package syntax.expression.constant;

import syntax.Leaf;
import syntax.expression.Expr;
import visitor.Visitor;

import javax.lang.model.type.NullType;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class NilConst extends Expr implements Leaf<NullType> {



    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     */
    public NilConst(Location leftPosition, Location rightPosition) {
        super(leftPosition, rightPosition);
    }


    /**
     * @return the value
     */
    @Override
    public NullType getValue() {
        return null;
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
