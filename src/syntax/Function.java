package syntax;

import nodetype.CompositeNodeType;
import nodetype.NodeType;
import syntax.expression.Id;
import syntax.statements.Statement;
import syntax.types.Type;
import visitor.Visitor;

import java.util.ArrayList;

public class Function extends ASTNode {
    private Type type;
    private Id id;
    private ArrayList<Statement> statements;
    private ArrayList<ParDecl> parDecls;

    /**
     * Initialize a new Function with params.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param id the id
     * @param type the type
     * @param parDecls the pardecl
     * @param statements list of statement
     */
    public Function(int leftPosition, int rightPosition, Id id, ArrayList<ParDecl> parDecls, Type type, ArrayList<Statement> statements) {
        super(leftPosition, rightPosition);
        this.type = type;
        this.id = id;
        this.statements = statements;
        this.parDecls = parDecls;
    }

    /**
     * Initialize a new Function without params.
     *
     * @param leftPosition  the left position
     * @param rightPosition the right position
     * @param id the id
     * @param type the type
     * @param statements list of statement
     */
    public Function(int leftPosition, int rightPosition, Id id, Type type, ArrayList<Statement> statements) {
        super(leftPosition, rightPosition);
        this.type = type;
        this.id = id;
        this.statements = statements;
        this.parDecls = new ArrayList<ParDecl>();
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @return the id
     */
    public Id getId() {
        return id;
    }

    /**
     * @return the statement list
     */
    public ArrayList<Statement> getStatements() {
        return statements;
    }

    /**
     * @return the parDecl
     */
    public ArrayList<ParDecl> getParDecls() {
        return parDecls;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P arg) {
        return visitor.visit(this, arg);
    }

    public CompositeNodeType domain() {
        CompositeNodeType compositeNodeType = new CompositeNodeType(new ArrayList<>());
        this.getParDecls().forEach(parDecl -> {
            compositeNodeType.addNodeType(parDecl.getType().typeFactory());
        });
        return compositeNodeType;
    }

    public NodeType codomain() {
        return this.getType().typeFactory();
    }

}
