package nodetype;

public class FunctionNodeType implements NodeType{
    private NodeType paramsType;
    private CompositeNodeType compositeNodeType;


    public FunctionNodeType(NodeType paramsType, CompositeNodeType compositeNodeType) {
        this.paramsType = paramsType;
        this.compositeNodeType = compositeNodeType;
    }

    public NodeType getParamsType() {
        return paramsType;
    }

    public CompositeNodeType getCompositeNodeType() {
        return compositeNodeType;
    }

    @Override
    public PrimitiveNodeType verifyAddition(PrimitiveNodeType type) {
        return null;
    }

    @Override
    public PrimitiveNodeType verifySubtraction(PrimitiveNodeType type) {
        return null;
    }

    @Override
    public PrimitiveNodeType verifyMultiplication(PrimitiveNodeType type) {
        return null;
    }

    @Override
    public PrimitiveNodeType verifyDivision(PrimitiveNodeType type) {
        return null;
    }

    @Override
    public PrimitiveNodeType verifyRelation(PrimitiveNodeType type) {
        return null;
    }
}
