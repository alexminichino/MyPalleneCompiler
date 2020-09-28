package syntax.types;

import nodetype.CompositeNodeType;
import nodetype.FunctionNodeType;
import nodetype.NodeType;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.StringJoiner;
import java_cup.runtime.ComplexSymbolFactory.Location;

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
    public FunctionType(Location leftPosition, Location rightPosition, ArrayList<Type> paramTypes, Type returnType) {
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
    public FunctionType(Location leftPosition, Location rightPosition, Type returnType) {
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

    @Override
    public String getCType() {
        StringJoiner joiner = new StringJoiner(", ");
        this.getParamTypes().stream().map((Type t) -> {
            if(t instanceof ArrayType) {
                return t.getCType() + "[]";
            } else {
                return t.getCType();
            }
        }).forEach(joiner::add);
        return String.format("%s (*%s) (%s)", this.getReturnType().typeFactory(), "%s", joiner.toString());
    }
}
