package syntax.types;

import nodetype.CompositeNodeType;
import nodetype.FunctionNodeType;
import nodetype.NodeType;
import visitor.Visitor;

import java.util.ArrayList;

public class FunctionType extends Type{
    private ArrayList<Type> paramTypes;
    private Type returnType;

    /**
     * Function with params
     * @param leftPosition
     * @param rightPosition
     * @param paramTypes
     * @param returnType
     */
    public FunctionType(int leftPosition, int rightPosition, ArrayList<Type> paramTypes, Type returnType) {
        super(leftPosition, rightPosition);
        this.paramTypes = paramTypes;
        this.returnType = returnType;
    }

    /**
     * Function without params
     * @param leftPosition
     * @param rightPosition
     * @param returnType
     */
    public FunctionType(int leftPosition, int rightPosition, Type returnType) {
        super(leftPosition, rightPosition);
        this.paramTypes = paramTypes;
        this.returnType = returnType;
    }

    /**
     * @return the params types
     */
    public ArrayList<Type> getParamTypes() {
        return paramTypes;
    }

    /**
     * @return the return type
     */
    public Type getReturnType() {
        return returnType;
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
        return new FunctionNodeType(codomain(), domain());
    }

    public CompositeNodeType domain() {
        CompositeNodeType compositeNodeType = new CompositeNodeType(new ArrayList<>());
        this.getParamTypes().forEach(typeDenoter -> compositeNodeType.addNodeType(typeDenoter.typeFactory()));
        return compositeNodeType;
    }

    public NodeType codomain() {
        return this.getReturnType().typeFactory();
    }
}
