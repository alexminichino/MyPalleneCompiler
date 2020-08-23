package visitor;


import errors.ErrorHandler;
import errors.ErrorItem;
import nodetype.*;
import semantic.SymbolTable;
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
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * TypeCheckingVisitor
 */
public class TypeCheckerConcreteVisitor implements Visitor<NodeType, SymbolTable> {

  private ErrorHandler errorHandler;

  private NodeType lastTypeToReturn = null;
  private HashMap<String, ArrayList<NodeType>> returnedValuesPerId = new HashMap<>();
  private String lastFunction = null;

  public TypeCheckerConcreteVisitor(ErrorHandler errorHandler) {
  this.errorHandler = errorHandler;
  }

  private Consumer<? super ASTNode> typeCheck(SymbolTable arg){
    return (ASTNode node) -> node.accept(this, arg);
  }

  @Override
  public NodeType visit(Program program, SymbolTable arg) {
    arg.enterInScope();
    if(program.getGlobal() != null) {
      program.getGlobal().accept(this, arg);
    }
    program.getFunctions().forEach(this.typeCheck(arg));
    arg.exitFromScope();
    return PrimitiveNodeType.NIL;
  }

  @Override
  public NodeType visit(Global global, SymbolTable arg) {
    global.getVarDecls().forEach(this.typeCheck(arg));
    return PrimitiveNodeType.NIL;
  }

  @Override
  public NodeType visit(Function function, SymbolTable arg) {
    this.lastTypeToReturn = function.codomain();
    this.returnedValuesPerId.putIfAbsent(function.getId().getValue(), new ArrayList<>());
    this.lastFunction = function.getId().getValue();

    NodeType functionType = function.getId().accept(this, arg);
    arg.enterInScope();
    function.getParDecls().forEach(this.typeCheck(arg));
    function.getStatements().forEach(this.typeCheck(arg));
    arg.exitFromScope();

    if((function.codomain().toString() != "void" && this.returnedValuesPerId.get(function.getId().getValue()).isEmpty())) {
      errorHandler.adderror(new ErrorItem("Function "+ function.getId().getValue()+ " must have return statement!", function));
    }
    this.lastTypeToReturn = null;

    return functionType;
  }

  @Override
  public NodeType visit(ParDecl parDecl, SymbolTable arg) {
    NodeType parDeclType = parDecl.getType().accept(this, arg);
    parDecl.getId().accept(this, arg);
    return parDeclType;
  }

  @Override
  public NodeType visit(VarDecl varDecl, SymbolTable arg) {
    NodeType varDeclType = varDecl.getType().accept(this, arg);
    varDecl.getId().accept(this, arg);
    if(varDecl.getVarInitValue() != null) {
      varDecl.getVarInitValue().accept(this, arg);
    }
    return varDeclType;
  }

  @Override
  public NodeType visit(VarInitValue varInitValue, SymbolTable arg) {
    NodeType varInitValueType = varInitValue.getExpression().accept(this, arg);
    return varInitValueType;
  }

  @Override
  public NodeType visit(PrimitiveType primitiveType, SymbolTable arg) {
    return primitiveType.typeFactory();
  }

  @Override
  public NodeType visit(ArrayType arrayType, SymbolTable arg) {
    return arrayType.typeFactory();
  }

  @Override
  public NodeType visit(FunctionType functionType, SymbolTable arg) {
    return functionType.typeFactory();
  }

  @Override
  public NodeType visit(IfStatement ifStatement, SymbolTable arg) {
    NodeType condIf = ifStatement.getCondition().accept(this, arg);
    if(!condIf.equals(PrimitiveNodeType.BOOL)) {
      errorHandler.adderror(new ErrorItem("Type error " + PrimitiveNodeType.BOOL.toString()+ " Expected but found "+ condIf, ifStatement ));
    }
    ifStatement.getThenStatement().forEach(this.typeCheck(arg));
    return PrimitiveNodeType.NIL;
  }

  @Override
  public NodeType visit(IfElseStatement ifElseStatement, SymbolTable arg) {
    NodeType condIf = ifElseStatement.getCondition().accept(this, arg);
    if(!condIf.equals(PrimitiveNodeType.BOOL)) {
      errorHandler.adderror(new ErrorItem("Type error " + PrimitiveNodeType.BOOL.toString()+ " Expected but found "+ condIf, ifElseStatement ));
    }
    ifElseStatement.getThenStatement().forEach(this.typeCheck(arg));
    ifElseStatement.getElseStatement().forEach(this.typeCheck(arg));
    return PrimitiveNodeType.NIL;
  }

  @Override
  public NodeType visit(ForStatement forStatement, SymbolTable arg) {
    arg.enterInScope();
    NodeType exprType = forStatement.getInitialConditionExpression().accept(this, arg);
    NodeType postCondType = forStatement.getLoopConditionExpression().accept(this, arg);
    if(!exprType.equals(PrimitiveNodeType.INT) && !postCondType.equals(PrimitiveNodeType.INT)) {
      errorHandler.adderror(new ErrorItem("Type error " + PrimitiveNodeType.INT.toString()+ " Expected but found "+ exprType, forStatement ));
      errorHandler.adderror(new ErrorItem("Type error " + PrimitiveNodeType.INT.toString()+ " Expected but found "+ postCondType, forStatement ));
    }
    forStatement.getStatements().forEach(this.typeCheck(arg));
    arg.exitFromScope();
    return PrimitiveNodeType.NIL;
  }

  @Override
  public NodeType visit(AssignStatement assignStatement, SymbolTable arg) {
    NodeType left = assignStatement.getId().accept(this, arg);
    NodeType right = assignStatement.getExpression().accept(this, arg);
    if(!left.equals(right))
      errorHandler.adderror(new ErrorItem("Type error " + left+ " Expected but found "+ right, assignStatement ));
    return right;
  }

  @Override
  public NodeType visit(CallFunctionStatement callFunctionStatement, SymbolTable arg) {
    CompositeNodeType input = new CompositeNodeType(new ArrayList<>());
    callFunctionStatement.getExpressionsParams().forEach(e -> input.addNodeType(e.accept(this, arg)));
    FunctionNodeType functionCallType = (FunctionNodeType) callFunctionStatement.getId().accept(this, arg);
    if (!functionCallType.getParamsType().equals(input)) {
      errorHandler.adderror(new ErrorItem("Type error " + functionCallType+ " Expected but found "+ input, callFunctionStatement ));
    }
    return functionCallType.getNodeType();
  }

  @Override
  public NodeType visit(ReadStatement readStatement, SymbolTable arg) {
    readStatement.getVariables().forEach(this.typeCheck(arg));
    return PrimitiveNodeType.NIL;
  }

  @Override
  public NodeType visit(WriteStatement writeStatement, SymbolTable arg) {
    writeStatement.getExpressions().forEach(this.typeCheck(arg));
    return PrimitiveNodeType.NIL;
  }

  @Override
  public NodeType visit(ReturnStatement returnStatement, SymbolTable arg) {
    NodeType returnType = returnStatement.getReturnExpression().accept(this, arg);

    this.returnedValuesPerId.get(this.lastFunction).add(returnType);
    if(!returnType.equals(this.lastTypeToReturn)) {
      errorHandler.adderror(new ErrorItem("Return Type error " +  this.lastTypeToReturn+ " Expected but found "+ returnType, returnStatement ));
    }

    return PrimitiveNodeType.NIL;
  }

  @Override
  public NodeType visit(LocalStatement localStatement, SymbolTable arg) {
    arg.enterInScope();
    localStatement.getVarDecls().forEach(this.typeCheck(arg));
    localStatement.getStatements().forEach(this.typeCheck(arg));
    arg.exitFromScope();
    return PrimitiveNodeType.NIL;
  }

  @Override
  public NodeType visit(NilConst nilConst, SymbolTable arg) {
    nilConst.setType(PrimitiveNodeType.NIL);
    return nilConst.getNodeType();
  }

  @Override
  public NodeType visit(BooleanConst booleanConst, SymbolTable arg) {
    booleanConst.setType(PrimitiveNodeType.BOOL);
    return booleanConst.getNodeType();
  }

  @Override
  public NodeType visit(IntegerConst integerConst, SymbolTable arg) {
    integerConst.setType(PrimitiveNodeType.INT);
    return integerConst.getNodeType();
  }

  @Override
  public NodeType visit(FloatConst floatConst, SymbolTable arg) {
    floatConst.setType(PrimitiveNodeType.FLOAT);
    return floatConst.getNodeType();
  }

  @Override
  public NodeType visit(StringConst stringConst, SymbolTable arg) {
    stringConst.setType(PrimitiveNodeType.STRING);
    return stringConst.getNodeType();
  }

  @Override
  public NodeType visit(ArrayAssignStatement arrayAssignStatement, SymbolTable arg) {
    NodeType indexType = arrayAssignStatement.getArrayPointExpression().accept(this, arg);
    if(!indexType.equals(PrimitiveNodeType.INT)) {
      errorHandler.adderror(new ErrorItem("Type error " + PrimitiveNodeType.INT+ " Expected but found "+ indexType, arrayAssignStatement ));
    }

    ArrayNodeType arrayNodeType = (ArrayNodeType) arrayAssignStatement.getArrayExpression().accept(this, arg);
    if(!arrayNodeType.equals(arrayAssignStatement.getArrayExpression().getNodeType())) {
      errorHandler.adderror(new ErrorItem("Type error " + arrayNodeType+ " Expected but found "+ arrayAssignStatement.getArrayExpression().getNodeType(), arrayAssignStatement ));
    }

    NodeType assigneeType = arrayAssignStatement.getAssignExpression().accept(this, arg);
    if(!assigneeType.equals(arrayNodeType.getTypeElement())) {
      errorHandler.adderror(new ErrorItem("Type error " + arrayNodeType.getTypeElement()+ " Expected but found "+ assigneeType, arrayAssignStatement ));
    }
    return PrimitiveNodeType.NIL;
  }

  @Override
  public NodeType visit(ArrayReadElement arrayReadElement, SymbolTable arg) {
    NodeType indexType = arrayReadElement.getIndexExpression().accept(this, arg);
    if(!indexType.equals(PrimitiveNodeType.INT)) {
      errorHandler.adderror(new ErrorItem("Type error " +PrimitiveNodeType.INT+ " Expected but found "+ indexType, arrayReadElement ));
    }
    ArrayNodeType arrayNodeType = (ArrayNodeType) arrayReadElement.getArrayAssignExpr().accept(this, arg);
    return arrayNodeType.getTypeElement();
  }

  @Override
  public NodeType visit(FunctionCall functionCall, SymbolTable arg) {
    CompositeNodeType input = new CompositeNodeType(new ArrayList<>());
    functionCall.getExprs().forEach(e -> input.addNodeType(e.accept(this, arg)));
    FunctionNodeType functionCallType = (FunctionNodeType) functionCall.getId().accept(this, arg);
    if (!functionCallType.getParamsType().equals(input)) {
      errorHandler.adderror(new ErrorItem("Type error " +functionCallType+ " Expected but found "+ input, functionCall ));
    }
    functionCall.setType(functionCallType);
    return functionCallType.getNodeType();
  }

  @Override
  public NodeType visit(MinusOperation minusOp, SymbolTable arg) {
    NodeType lType = minusOp.getLeftOperand().accept(this, arg);
    NodeType rType = minusOp.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NIL;
    result = lType.verifySubtraction((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NIL))
      errorHandler.adderror(new ErrorItem("Type error " +lType+ " Expected but found "+ result, minusOp ));
    minusOp.setType(result);
    return result;
  }

  @Override
  public NodeType visit(PlusOperation plusOp, SymbolTable arg) {
    NodeType lType = plusOp.getLeftOperand().accept(this, arg);
    NodeType rType = plusOp.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NIL;
    result = lType.verifyAddition((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NIL))
      errorHandler.adderror(new ErrorItem("Type error " +lType+ " Expected but found "+ result, plusOp ));
    plusOp.setType(result);
    return result;
  }

  @Override
  public NodeType visit(TimesOperation timesOp, SymbolTable arg) {
    NodeType lType = timesOp.getLeftOperand().accept(this, arg);
    NodeType rType = timesOp.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NIL;
    result = lType.verifyMultiplication((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NIL))
      errorHandler.adderror(new ErrorItem("Type error " +lType+ " Expected but found "+ result, timesOp ));
    timesOp.setType(result);
    return result;
  }

  @Override
  public NodeType visit(DivOperation divOp, SymbolTable arg) {
    NodeType lType = divOp.getLeftOperand().accept(this, arg);
    NodeType rType = divOp.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NIL;
    result = lType.verifyDivision((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NIL))
      errorHandler.adderror(new ErrorItem("Type error " +lType+ " Expected but found "+ result, divOp ));
    divOp.setType(result);
    return result;
  }

  @Override
  public NodeType visit(AndRelOperation andRelOperation, SymbolTable arg) {
    NodeType lType = andRelOperation.getLeftOperand().accept(this, arg);
    NodeType rType = andRelOperation.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NIL;
    result = lType.verifyRelation((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NIL))
      errorHandler.adderror(new ErrorItem("Type error " +lType+ " Expected but found "+ result, andRelOperation ));
    andRelOperation.setType(result);
    return result;
  }

  @Override
  public NodeType visit(GreatThanRelOperation greatThanRelOperation, SymbolTable arg) {
    NodeType lType = greatThanRelOperation.getLeftOperand().accept(this, arg);
    NodeType rType = greatThanRelOperation.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NIL;
    result = lType.verifyRelation((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NIL))
      errorHandler.adderror(new ErrorItem("Type error " +lType+ " Expected but found "+ result, greatThanRelOperation ));
    greatThanRelOperation.setType(result);
    return result;
  }

  @Override
  public NodeType visit(OrRelOperation orRelOperation, SymbolTable arg) {
    NodeType lType = orRelOperation.getLeftOperand().accept(this, arg);
    NodeType rType = orRelOperation.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NIL;
    result = lType.verifyRelation((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NIL))
      errorHandler.adderror(new ErrorItem("Type error " +lType+ " Expected but found "+ result, orRelOperation ));
    orRelOperation.setType(result);
    return result;
  }

  @Override
  public NodeType visit(GreatThanEqualsRelOperation greatThanEqualsRelOperation, SymbolTable arg) {
    NodeType lType = greatThanEqualsRelOperation.getLeftOperand().accept(this, arg);
    NodeType rType = greatThanEqualsRelOperation.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NIL;
    result = lType.verifyRelation((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NIL))
      errorHandler.adderror(new ErrorItem("Type error " +lType+ " Expected but found "+ result, greatThanEqualsRelOperation ));
    greatThanEqualsRelOperation.setType(result);
    return result;
  }

  @Override
  public NodeType visit(LessThanRelOperation lessThanRelOperation, SymbolTable arg) {
    NodeType lType = lessThanRelOperation.getLeftOperand().accept(this, arg);
    NodeType rType = lessThanRelOperation.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NIL;
    result = lType.verifyRelation((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NIL))
      errorHandler.adderror(new ErrorItem("Type error " +lType+ " Expected but found "+ result, lessThanRelOperation ));
    lessThanRelOperation.setType(result);
    return result;
  }

  @Override
  public NodeType visit(LessThanEqualsRelOperation lessThanEqualsRelOperation, SymbolTable arg) {
    NodeType lType = lessThanEqualsRelOperation.getLeftOperand().accept(this, arg);
    NodeType rType = lessThanEqualsRelOperation.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NIL;
    result = lType.verifyRelation((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NIL))
      errorHandler.adderror(new ErrorItem("Type error " +lType+ " Expected but found "+ result, lessThanEqualsRelOperation ));
    lessThanEqualsRelOperation.setType(result);
    return result;
  }

  @Override
  public NodeType visit(EqualsRelOperation equalsRelOperation, SymbolTable arg) {
    NodeType lType = equalsRelOperation.getLeftOperand().accept(this, arg);
    NodeType rType = equalsRelOperation.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NIL;
    result = lType.verifyRelation((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NIL))
      errorHandler.adderror(new ErrorItem("Type error " +lType+ " Expected but found "+ result, equalsRelOperation ));
    equalsRelOperation.setType(result);
    return result;
  }

  @Override
  public NodeType visit(NotEqualsRelOperation notEqualsRelOperation, SymbolTable arg) {
    NodeType lType = notEqualsRelOperation.getLeftOperand().accept(this, arg);
    NodeType rType = notEqualsRelOperation.getRightOperand().accept(this, arg);
    NodeType result = PrimitiveNodeType.NIL;
    result = lType.verifyRelation((PrimitiveNodeType) rType);
    if(result.equals(PrimitiveNodeType.NIL))
      errorHandler.adderror(new ErrorItem("Type error " +lType+ " Expected but found "+ result, notEqualsRelOperation ));
    notEqualsRelOperation.setType(result);
    return result;
  }

  @Override
  public NodeType visit(UminusExpression uminusExpression, SymbolTable arg) {
    NodeType type = uminusExpression.getExpr().accept(this, arg);
    if(!type.equals(PrimitiveNodeType.INT) && !type.equals(PrimitiveNodeType.FLOAT))
      errorHandler.adderror(new ErrorItem("Type error " +PrimitiveNodeType.FLOAT+ " Expected but found "+ type, uminusExpression ));
    return type;
  }

  @Override
  public NodeType visit(NotExpression notExpression, SymbolTable arg) {
    NodeType type = notExpression.getExpr().accept(this, arg);
    if(!type.equals(PrimitiveNodeType.BOOL))
      errorHandler.adderror(new ErrorItem("Type error " +PrimitiveNodeType.BOOL+ " Expected but found "+ type, notExpression ));
    return type;
  }

  @Override
  public NodeType visit(SharpExpression sharpExpression, SymbolTable arg) {
    NodeType type = sharpExpression.getExpr().accept(this, arg);
    if(!type.equals(PrimitiveNodeType.STRING) && !(type instanceof ArrayNodeType)) {
      errorHandler.adderror(new ErrorItem("Type error " +PrimitiveNodeType.STRING+ "OR  array"+ " Expected but found "+ type, sharpExpression ));
    }
    return PrimitiveNodeType.INT;
  }


  @Override
  public NodeType visit(Id id, SymbolTable arg) {
    NodeType iType = arg.getEntry(id.getValue()).get().getNodeType();
    id.setType(iType);
    return iType;
  }

  @Override
  public NodeType visit(ArrayConst arrayConst, SymbolTable arg) {
    arrayConst.setType(arrayConst.getType().typeFactory());
    return arrayConst.getNodeType();
  }

  @Override
  public NodeType visit(NopStatement nopStatement, SymbolTable arg) {
    return PrimitiveNodeType.NIL;
  }

  @Override
  public NodeType visit(WhileStatement whileStatement, SymbolTable arg) {
    NodeType condType = whileStatement.getConditionExpression().accept(this, arg);
    if(!condType.equals(PrimitiveNodeType.BOOL))
      errorHandler.adderror(new ErrorItem("Type error " +PrimitiveNodeType.BOOL+ " Expected but found "+ condType, whileStatement ));
    whileStatement.getStatements().forEach(this.typeCheck(arg));
    return PrimitiveNodeType.NIL;
  }

}