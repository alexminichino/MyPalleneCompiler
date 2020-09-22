package nodetype;

import java.util.List;
import java.util.Objects;

public class CompositeNodeType  implements  NodeType{
    private final List<NodeType> types;

    public CompositeNodeType(List<NodeType> types) {
        this.types = types;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }
        CompositeNodeType other = (CompositeNodeType) obj;
        return Objects.equals(this.types, other.types);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.types.forEach(t -> sb.append(t.toString()));
        return sb.toString();
    }


    @Override
    public PrimitiveNodeType verifyAddition(PrimitiveNodeType type) {
        return PrimitiveNodeType.NIL;
    }

    @Override
    public PrimitiveNodeType verifySubtraction(PrimitiveNodeType type) {
        return PrimitiveNodeType.NIL;
    }

    @Override
    public PrimitiveNodeType verifyMultiplication(PrimitiveNodeType type) {
        return PrimitiveNodeType.NIL;
    }

    @Override
    public PrimitiveNodeType verifyDivision(PrimitiveNodeType type) {
        return PrimitiveNodeType.NIL;
    }

    @Override
    public PrimitiveNodeType verifyRelation(PrimitiveNodeType type) {
        return PrimitiveNodeType.NIL;
    }

    public void addNodeType(NodeType type) {
        types.add(type);
    }
}
