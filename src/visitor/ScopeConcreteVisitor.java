package visitor;

import errors.ErrorHandler;
import errors.ErrorItem;
import nodetype.NodeTipology;
import nodetype.PrimitiveNodeType;
import semantic.SymbolTable;
import semantic.SymbolTableEntry;
import syntax.*;
import syntax.expression.ArrayReadElement;
import syntax.expression.FunctionCall;
import syntax.expression.Id;
import syntax.expression.binary.arithmetic.DivOperation;
import syntax.expression.binary.arithmetic.MinusOperation;
import syntax.expression.binary.arithmetic.PlusOperation;
import syntax.expression.binary.arithmetic.TimesOperation;
import syntax.expression.binary.relation.*;
import syntax.expression.constant.*;
import syntax.expression.unary.NotExpression;
import syntax.expression.unary.SharpExpression;
import syntax.expression.unary.UminusExpression;
import syntax.statements.*;
import syntax.types.ArrayType;
import syntax.types.FunctionType;
import syntax.types.PrimitiveType;

import java.util.ArrayList;

public class ScopeConcreteVisitor implements Visitor<Boolean, SymbolTable> {
    private ErrorHandler errorHandler;

    public ScopeConcreteVisitor(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    private boolean acceptList(ArrayList<? extends ASTNode> nodes, SymbolTable table){
        if(nodes.isEmpty()){
            return true;
        }
        for (ASTNode node : nodes){
            if(!node.accept(this,table)){
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean visit(Function function, SymbolTable arg) {
        arg.enterInScope();
        boolean isOk = acceptList(function.getParDecls(), arg);
        isOk = isOk & acceptList(function.getStatements(), arg);
        isOk = isOk & function.getType().accept(this, arg);
        if (! isOk){
            errorHandler.adderror(new ErrorItem("Function declaration error", function));
        }
        arg.exitFromScope();
        return isOk;
    }

    @Override
    public Boolean visit(Id id, SymbolTable arg) {
        boolean ret = arg.getTableEntryIfExists(id.getValue()).isPresent();
        return ret;
    }

    @Override
    public Boolean visit(DivOperation divOperation, SymbolTable arg) {
        boolean isOk = divOperation.getLeftOperand().accept(this, arg);
        isOk &= divOperation.getRightOperand().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Division operation error", divOperation ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(MinusOperation minusOperation, SymbolTable arg) {
        boolean isOk = minusOperation.getLeftOperand().accept(this, arg);
        isOk &= minusOperation.getRightOperand().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Subtraction operation error", minusOperation ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(PlusOperation plusOperation, SymbolTable arg) {
        boolean isOk = plusOperation.getLeftOperand().accept(this, arg);
        isOk &= plusOperation.getRightOperand().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Plus operation error", plusOperation ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(TimesOperation timesOperation, SymbolTable arg) {
        boolean isOk = timesOperation.getLeftOperand().accept(this, arg);
        isOk &= timesOperation.getRightOperand().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Mul operation error", timesOperation ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(AndRelOperation andRelOperation, SymbolTable arg) {
        boolean isOk = andRelOperation.getLeftOperand().accept(this, arg);
        isOk &= andRelOperation.getRightOperand().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("AND operation error", andRelOperation ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(EqualsRelOperation equalsRelOperation, SymbolTable arg) {
        boolean isOk = equalsRelOperation.getLeftOperand().accept(this, arg);
        isOk &= equalsRelOperation.getRightOperand().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Equals operation error", equalsRelOperation ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(GreatThanEqualsRelOperation greatThanEqualsRelOperation, SymbolTable arg) {
        boolean isOk = greatThanEqualsRelOperation.getLeftOperand().accept(this, arg);
        isOk &= greatThanEqualsRelOperation.getRightOperand().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Great Than Equals operation error", greatThanEqualsRelOperation ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(GreatThanRelOperation greatThanRelOperation, SymbolTable arg) {
        boolean isOk = greatThanRelOperation.getLeftOperand().accept(this, arg);
        isOk &= greatThanRelOperation.getRightOperand().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Great Than operation error", greatThanRelOperation ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(LessThanEqualsRelOperation lessThanEqualsRelOperation, SymbolTable arg) {
        boolean isOk = lessThanEqualsRelOperation.getLeftOperand().accept(this, arg);
        isOk &= lessThanEqualsRelOperation.getRightOperand().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Less Than Equals operation error", lessThanEqualsRelOperation ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(LessThanRelOperation lessThanRelOperation, SymbolTable arg) {
        boolean isOk = lessThanRelOperation.getLeftOperand().accept(this, arg);
        isOk &= lessThanRelOperation.getRightOperand().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Less Than operation error", lessThanRelOperation ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(NotEqualsRelOperation notEqualsRelOperation, SymbolTable arg) {
        boolean isOk = notEqualsRelOperation.getLeftOperand().accept(this, arg);
        isOk &= notEqualsRelOperation.getRightOperand().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Not equals operation error", notEqualsRelOperation ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(OrRelOperation orRelOperation, SymbolTable arg) {
        boolean isOk = orRelOperation.getLeftOperand().accept(this, arg);
        isOk &= orRelOperation.getRightOperand().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("OR operation error", orRelOperation ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(ArrayConst arrayConst, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(BooleanConst booleanConst, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(FloatConst floatConst, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(IntegerConst integerConst, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(NilConst nilConst, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(StringConst stringConst, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(ArrayReadElement arrayReadElement, SymbolTable arg) {
        boolean isOk = arrayReadElement.getArrayAssignExpr().accept(this, arg);
        isOk &= arrayReadElement.getIndexExpression().accept(this, arg);
        if(! isOk){
            errorHandler.adderror(new ErrorItem("Array reading error", arrayReadElement));
        }
        return isOk;
    }

    @Override
    public Boolean visit(FunctionCall functionCall, SymbolTable arg) {
        Id id = functionCall.getId();
        if (id.getName().equalsIgnoreCase("main")){
            errorHandler.adderror(new ErrorItem("Invalid function name", functionCall ));
        }
        boolean isOk = id.accept(this, arg);
        if(! isOk){
            errorHandler.adderror(new ErrorItem("Unknown error", functionCall));
        }
        else{
            isOk = acceptList(functionCall.getExprs(), arg);
            if(!isOk){
                errorHandler.adderror(new ErrorItem("Function call error", functionCall));
            }
        }
        return isOk;
    }

    @Override
    public Boolean visit(ArrayAssignStatement arrayAssignStatement, SymbolTable arg) {
        boolean isOk = arrayAssignStatement.getArrayExpression().accept(this, arg);
        isOk &= arrayAssignStatement.getArrayPointExpression().accept(this, arg);
        isOk &= arrayAssignStatement.getAssignExpression().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Array element assign error", arrayAssignStatement));
        }
        return isOk;
    }

    @Override
    public Boolean visit(AssignStatement assignStatement, SymbolTable arg) {
        boolean isOk = assignStatement.getId().accept(this, arg);
        isOk &= assignStatement.getExpression().accept(this, arg);
        if( ! isOk){
            errorHandler.adderror(new ErrorItem("Assign  statement error", assignStatement));
        }
        return isOk;
    }

    @Override
    public Boolean visit(CallFunctionStatement callFunctionStatement, SymbolTable arg) {
        Id id = callFunctionStatement.getId();
        if (id.getName().equalsIgnoreCase("main")){
            errorHandler.adderror(new ErrorItem("Invalid function name", callFunctionStatement ));
        }
        boolean isOk = id.accept(this, arg);
        if(! isOk){
            errorHandler.adderror(new ErrorItem("Unknown error", callFunctionStatement));
        }
        else{
            isOk = acceptList(callFunctionStatement.getExpressionsParams(), arg);
            if(!isOk){
                errorHandler.adderror(new ErrorItem("Function call error", callFunctionStatement));
            }
        }
        return isOk;
    }

    @Override
    public Boolean visit(ForStatement forStatement, SymbolTable arg) {
        arg.enterInScope();
        Variable variable = forStatement.getVariable();
        boolean isOk = variable.accept(this, arg);
        if (isOk){
            arg.addEntry(variable.getValue(), new SymbolTableEntry(NodeTipology.VAR,  PrimitiveNodeType.INT, variable.getName()));
        }
        isOk &= forStatement.getInitialConditionExpression().accept(this, arg);
        isOk &= forStatement.getLoopConditionExpression().accept(this, arg);
        isOk &= acceptList(forStatement.getStatements(), arg);
        if (! isOk ){
            errorHandler.adderror(new ErrorItem("Statement in for", forStatement));
        }
        arg.exitFromScope();
        return isOk;
    }

    @Override
    public Boolean visit(IfElseStatement ifElseStatement, SymbolTable arg) {
        boolean isOk = acceptList(ifElseStatement.getThenStatement(), arg);
        isOk &= acceptList(ifElseStatement.getElseStatement(), arg);
        isOk &= ifElseStatement.getCondition().accept(this, arg);
        if(!isOk){
            errorHandler.adderror(new ErrorItem("If else error", ifElseStatement));
        }
        return isOk;
    }

    @Override
    public Boolean visit(IfStatement ifStatement, SymbolTable arg) {
        boolean isOk = acceptList(ifStatement.getThenStatement(), arg);
        isOk &= ifStatement.getCondition().accept(this, arg);
        if(!isOk){
            errorHandler.adderror(new ErrorItem("If error", ifStatement));
        }
        return isOk;
    }

    @Override
    public Boolean visit(LocalStatement localStatement, SymbolTable arg) {
        arg.enterInScope();
        boolean isOk = acceptList(localStatement.getVarDecls(), arg);
        isOk &= acceptList(localStatement.getStatements(), arg);
        if(!isOk){
            errorHandler.adderror(new ErrorItem("Local error", localStatement));
        }
        arg.exitFromScope();
        return isOk;
    }

    @Override
    public Boolean visit(NopStatement nopStatement, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(ReadStatement readStatement, SymbolTable arg) {
        boolean isOk= acceptList(readStatement.getVariables(),arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Read error", readStatement));
        }
        return isOk;
    }

    @Override
    public Boolean visit(ReturnStatement returnStatement, SymbolTable arg) {
        boolean isOk= returnStatement.getReturnExpression().accept(this,arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Return error", returnStatement));
        }
        return isOk;
    }

    @Override
    public Boolean visit(WhileStatement whileStatement, SymbolTable arg) {
        boolean isOk = whileStatement.getConditionExpression().accept(this,arg);
        isOk &= acceptList(whileStatement.getStatements(), arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("While error", whileStatement));
        }
        return isOk;
    }

    @Override
    public Boolean visit(WriteStatement writeStatement, SymbolTable arg) {
        boolean isOk= acceptList(writeStatement.getExpressions(),arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Write error", writeStatement));
        }
        return isOk;
    }

    @Override
    public Boolean visit(Global global, SymbolTable arg) {
        boolean isOk= acceptList(global.getVarDecls(),arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Global error", global));
        }
        return isOk;
    }

    @Override
    public Boolean visit(ParDecl parDecl, SymbolTable arg) {
        Variable variable = parDecl.getVariable();
        boolean isOk= variable.accept(this, arg);
        isOk &= parDecl.getType().accept(this, arg);

        if (!isOk){
            errorHandler.adderror(new ErrorItem("Parameter Declaration error", parDecl));
        }
        else{
            arg.addEntry(variable.getValue(), new SymbolTableEntry(NodeTipology.VAR, parDecl.getType().typeFactory(), variable.getName() ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(Program program, SymbolTable arg) {
        arg.enterInScope();
        boolean isOk = (program.getGlobal()!=null)? program.getGlobal().accept(this,arg): true;
        isOk &= acceptList(program.getFunctions(), arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Program error", program));
        }
        arg.exitFromScope();
        return isOk;
    }

    @Override
    public Boolean visit(VarDecl varDecl, SymbolTable arg) {
        boolean isOk = varDecl.getVariable().accept(this,arg);
        isOk &= varDecl.getType().accept(this,arg);
        isOk &=  (varDecl.getVarInitValue() != null) ? varDecl.getVarInitValue().accept(this, arg) : true;
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Variable Declaration error", varDecl));
        }
        else {
            arg.addEntry(varDecl.getVariable().getValue(), new SymbolTableEntry(NodeTipology.VAR, varDecl.getType().typeFactory(), varDecl.getVariable().getName()));
        }
        return isOk;
    }

    @Override
    public Boolean visit(VarInitValue varInitValue, SymbolTable arg) {
        boolean isOk = varInitValue.getExpression().accept(this,arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Variable Initial error", varInitValue));
        }
        return isOk;
    }

    @Override
    public Boolean visit(ArrayType arrayType, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(FunctionType functionType, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(PrimitiveType primitiveType, SymbolTable arg) {
        return true;
    }

    @Override
    public Boolean visit(NotExpression notExpression, SymbolTable arg) {
        boolean isOk = notExpression.getExpr().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("NOT operation error", notExpression ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(SharpExpression sharpExpression, SymbolTable arg) {
        boolean isOk = sharpExpression.getExpr().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("SHARP error", sharpExpression ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(UminusExpression uminusExpression, SymbolTable arg) {
        boolean isOk = uminusExpression.getExpr().accept(this, arg);
        if (!isOk){
            errorHandler.adderror(new ErrorItem("Uminus error", uminusExpression ));
        }
        return isOk;
    }

    @Override
    public Boolean visit(Variable variable, SymbolTable arg) {
        return !arg.containsLexeme(variable.getValue() );
    }
}
