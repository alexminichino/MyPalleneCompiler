package semantic;

import nodetype.NodeTipology;
import nodetype.NodeType;

public class SymbolTableEntry {
    private NodeTipology nodeTipology;
    private NodeType nodeType;
    private String lexeme;

    public SymbolTableEntry(NodeTipology nodeTipology, NodeType nodeType, String lexeme) {
        this.nodeTipology = nodeTipology;
        this.nodeType = nodeType;
        this.lexeme = lexeme;
    }

    public NodeTipology getNodeTipology() {
        return nodeTipology;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public String getLexeme() {
        return lexeme;
    }

    @Override
    public String toString() {
        return "SymbolTableEntry{" +
                "nodeTipology=" + nodeTipology +
                ", nodeType=" + nodeType +
                ", lexeme='" + lexeme + '\'' +
                '}';
    }
}
