package syntax.types;

import nodetype.ArrayNodeType;
import nodetype.NodeType;
import nodetype.PrimitiveNodeType;
import visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class ArrayType extends Type{

    private Type type;

    /**
     * @param leftPosition
     * @param rightPosition
     * @param type
     */
    public ArrayType(Location leftPosition, Location rightPosition, Type type) {
        super(leftPosition, rightPosition);
        this.type = type;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    public NodeType getElementsType() {
        return this.type.typeFactory();
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
    public String getCType() {
        switch((PrimitiveNodeType) this.getElementsType()){
            case STRING:
                return "char *";
            case FLOAT:
                return "float";
            case BOOL:
                return "bool";
            case INT:
                return "int";
            default:
                return "";
        }
    }

    @Override
    public NodeType typeFactory() {
        return new ArrayNodeType((PrimitiveNodeType) this.type.typeFactory());
    }
}
