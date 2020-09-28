package syntax.types;

import nodetype.NodeType;
import nodetype.PrimitiveNodeType;
import visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class PrimitiveType extends Type{
    private String nameType;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param nameType the name of type
     */
    public PrimitiveType(Location leftPosition, Location rightPosition, String nameType) {
        super(leftPosition, rightPosition);
        this.nameType = nameType;
    }


    /**
     * @return the name of type eg String, int, boolean,...
     */
    public String getNameType() {
        return nameType;
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
    public PrimitiveNodeType typeFactory() {
        switch(nameType){
            case "INT":
                return PrimitiveNodeType.INT;
            case "FLOAT":
                return PrimitiveNodeType.FLOAT;
            case "STRING":
                return PrimitiveNodeType.STRING;
            case "BOOL":
                return PrimitiveNodeType.BOOL;
            default:
                return PrimitiveNodeType.NIL;
        }
    }

    @Override
    public String getCType() {
        switch(this.nameType){
            case "INT":
                return "int";
            case "FLOAT":
                return "float";
            case "STRING":
                return "char *";
            case "BOOL":
                return "bool";
            default:
                return "undefined";
        }
    }
}
