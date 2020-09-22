package syntax;

import syntax.expression.Id;
import syntax.types.Type;
import visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class ParDecl extends ASTNode{
    private Variable variable;
    private Type type;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param variable the variable
     * @param type type
     */
    public ParDecl(Location leftPosition, Location rightPosition, Variable variable, Type type) {
        super(leftPosition, rightPosition);
        this.variable= variable;
        this.type = type;
    }

    /**
     * @return the variable
     */
    public Variable getVariable() {
        return variable;
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
