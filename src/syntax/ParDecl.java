package syntax;

import syntax.expression.Id;
import syntax.types.Type;
import visitor.Visitor;

public class ParDecl extends ASTNode{
    private Id id;
    private Type type;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param id id
     * @param type type
     */
    public ParDecl(int leftPosition, int rightPosition, Id id, Type type) {
        super(leftPosition, rightPosition);
        this.id = id;
        this.type = type;
    }

    /**
     * @return the id
     */
    public Id getId() {
        return id;
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
