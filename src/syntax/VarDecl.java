package syntax;

import syntax.expression.Id;
import syntax.types.Type;
import visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class VarDecl extends ASTNode{
    private Variable variable;
    private Type type;
    private VarInitValue varInitValue;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param variable the variable
     * @param type type
     * @param varInitValue initial value
     */
    public VarDecl(Location leftPosition, Location rightPosition, Variable variable, Type type, VarInitValue varInitValue) {
        super(leftPosition, rightPosition);
        this.variable = variable;
        this.type = type;
        this.varInitValue = varInitValue;
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
     * @return the initial value
     */
    public VarInitValue getVarInitValue() {
        return varInitValue;
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
