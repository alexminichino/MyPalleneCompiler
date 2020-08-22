package syntax.types;

import nodetype.ArrayNodeType;
import nodetype.NodeType;
import nodetype.PrimitiveNodeType;
import visitor.Visitor;

public class ArrayType extends Type{

    private Type type;

    /**
     * @param leftPosition
     * @param rightPosition
     * @param type
     */
    public ArrayType(int leftPosition, int rightPosition, Type type) {
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

    @Override
    public NodeType typeFactory() {
        return new ArrayNodeType((PrimitiveNodeType) this.type.typeFactory());
    }
}
