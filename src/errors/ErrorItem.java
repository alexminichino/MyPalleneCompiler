package errors;

import syntax.ASTNode;

public class ErrorItem {
    private String message;
    private ASTNode errorNode;

    public ErrorItem(String message, ASTNode errorNode) {
        this.message = message;
        this.errorNode = errorNode;
    }

    public String getMessage() {
        return message;
    }

    public ASTNode getErrorNode() {
        return errorNode;
    }
}
