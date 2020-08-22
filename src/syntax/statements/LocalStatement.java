package syntax.statements;

import syntax.VarDecl;
import visitor.Visitor;

import java.util.ArrayList;

public class LocalStatement extends Statement {
    private ArrayList<VarDecl> varDecls;
    private ArrayList<Statement> statements;

    /**
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param varDecls
     * @param statements
     */
    public LocalStatement(int leftPosition, int rightPosition, ArrayList<VarDecl> varDecls, ArrayList<Statement> statements) {
        super(leftPosition, rightPosition);
        this.varDecls = varDecls;
        this.statements = statements;
    }

    /**
     * @return the var decls
     */
    public ArrayList<VarDecl> getVarDecls() {
        return varDecls;
    }

    /**
     * @return the statements list
     */
    public ArrayList<Statement> getStatements() {
        return statements;
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
}
