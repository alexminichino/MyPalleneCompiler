package syntax.expression;

import syntax.Leaf;
import visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class Id extends Expr implements Leaf<String> {
    private String name;


    /**
     * Initialize a new ID node.
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param name
     */
    public Id(Location leftPosition, Location rightPosition, String name) {
        super(leftPosition, rightPosition);
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return name;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P arg) {
        return visitor.visit(this, arg);
    }
}
