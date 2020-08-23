package nodetype;

public class FunctionNodeType implements NodeType{
    private NodeType nodeType ;
    private CompositeNodeType paramsType ;

    public FunctionNodeType(NodeType nodeType, CompositeNodeType paramsType) {
        this.nodeType = nodeType;
        this.paramsType = paramsType;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public CompositeNodeType getParamsType() {
        return paramsType;
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
