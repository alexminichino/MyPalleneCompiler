package nodetype;

import java.util.Objects;

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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        else if (obj == null) {
            return false;
        }
        else if (getClass() != obj.getClass()) {
            return false;
        }
        final FunctionNodeType other = (FunctionNodeType) obj;
        return Objects.equals(this.nodeType, other.nodeType) && Objects.equals(this.paramsType, other.paramsType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(" + paramsType.toString() + ")");
        sb.append(" -> ");
        sb.append(nodeType.toString());
        return sb.toString();
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
