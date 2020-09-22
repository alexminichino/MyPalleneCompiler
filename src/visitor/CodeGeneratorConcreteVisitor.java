package visitor;
import nodetype.NodeType;
import nodetype.PrimitiveNodeType;
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

import java.util.*;
import java.util.function.Consumer;

public class CodeGeneratorConcreteVisitor implements Visitor<String, SymbolTable> {

  private final String root;

  public CodeGeneratorConcreteVisitor(String root) {
    this.root = root;
  }

  private String beautify(List<? extends ASTNode> nodes, StringJoiner joiner, SymbolTable table){
    nodes.forEach(node -> joiner.add(node.accept(this, table)));
    return joiner.toString();
  }

  private Consumer<ParDecl> formatArg(StringJoiner joiner, SymbolTable table){
    return new Consumer<ParDecl>() {
      @Override
      public void accept(ParDecl t) {
        if(t.getType() instanceof ArrayType) {
          joiner.add(String.format("%s[]", t.accept(CodeGeneratorConcreteVisitor.this, table)));
        } else if(t.getType() instanceof FunctionType) {
          joiner.add(String.format(t.accept(CodeGeneratorConcreteVisitor.this, table), "%s"));
        } else {
          joiner.add(String.format("%s", t.accept(CodeGeneratorConcreteVisitor.this, table)));
        }
      }
    };
  }

  /**
   *
   * */
  private String getFormatSpecifier(NodeType type){
    PrimitiveNodeType pType = PrimitiveNodeType.class.cast(type);
    switch(pType){
      case FLOAT:
        return "%f";
      case STRING:
        return "%s";
      default:
        return "%d";
    }
  }

  /**
   * Move main to end of file
   * */
  private ArrayList<Function> reorderFunctions(ArrayList<Function> functionsList){
    ArrayList<Function> ordered = new ArrayList<>();
    Function main = null;
    for (Function f : functionsList){
      if (f.getVariable().getName().equalsIgnoreCase("main"))
        main = f;
      else
        ordered.add(f);
    }
    if(main != null){
      ordered.add(main);
    }
    return  ordered;
  }

  @Override
  public String visit(Program program, SymbolTable arg) {
    arg.enterInScope();
    String global = program.getGlobal().accept(this, arg);
    String functionDefinitions = this.beautify(reorderFunctions(program.getFunctions()), new StringJoiner("\n"), arg);
    arg.exitFromScope();

    return root
        .replace("{{var_declarations}}", global)
        .replace("{{function_definitions}}", functionDefinitions);
  }

  @Override
  public String visit(Global global, SymbolTable arg) {
    String varDeclarations = this.beautify(global.getVarDecls(), new StringJoiner("\n"), arg);
    return varDeclarations;
  }

  @Override
  public String visit(Function function, SymbolTable arg) {
    String functionName = function.getVariable().accept(this, arg);
    StringJoiner arguments = new StringJoiner(", ");
    arg.enterInScope();
    function.getParDecls().forEach(this.formatArg(arguments, arg));
    String returnType = function.getType().accept(this, arg);
    String body = this.beautify(function.getStatements(), new StringJoiner("\n"), arg);
    arg.exitFromScope();
    if(functionName.equalsIgnoreCase("main")) returnType = "int";
    else if(returnType == "" && !functionName.equalsIgnoreCase("main")) { returnType = "void"; }
    return String.format("%s %s(%s){\n%s\n}\n", returnType, functionName, arguments.toString(), body);
  }

  @Override
  public String visit(ParDecl parDecl, SymbolTable arg) {
    String parameterType = parDecl.getType().accept(this, arg);
    String parameterName = parDecl.getVariable().accept(this, arg);
    if(parDecl.getType() instanceof FunctionType) {
      return String.format(parameterType, parameterName);
    } else {
      return String.format("%s %s", parameterType, parameterName);
    }
  }

  @Override
  public String visit(VarDecl varDecl, SymbolTable arg) {
    String type = varDecl.getType().accept(this, arg);
    String varName = varDecl.getVariable().accept(this, arg);
    if(varDecl.getType() instanceof ArrayType) varName = varName + "[60]";
    if(varDecl.getVarInitValue() != null) {
      String varInitValue = varDecl.getVarInitValue().accept(this, arg);
      return String.format("%s %s = %s;", type, varName, varInitValue);
    } else {
      return String.format("%s %s;", type, varName);
    }
  }

  @Override
  public String visit(VarInitValue varInitValue, SymbolTable arg) {
    String initValue = varInitValue.getExpression().accept(this, arg);
    return initValue;
  }

  @Override
  public String visit(PrimitiveType primitiveType, SymbolTable arg) {
    return primitiveType.typeFactory().getCType();
  }

  @Override
  public String visit(ArrayType arrayType, SymbolTable arg) {
    return arrayType.getCType();
  }

  @Override
  public String visit(FunctionType functionType, SymbolTable arg) {
    return functionType.getCType();
  }

  @Override
  public String visit(IfStatement ifStatement, SymbolTable arg) {
    String condition = ifStatement.getCondition().accept(this, arg);
    String then = this.beautify(ifStatement.getThenStatement(), new StringJoiner("\n"), arg);
    return String.format("if(%s){\n%s\n}", condition, then);
  }

  @Override
  public String visit(IfElseStatement ifElseStatement, SymbolTable arg) {
    String condition = ifElseStatement.getCondition().accept(this, arg);
    String then = this.beautify(ifElseStatement.getThenStatement(), new StringJoiner("\n"), arg);
    String thenElse = this.beautify(ifElseStatement.getElseStatement(), new StringJoiner("\n"), arg);
    return String.format("if(%s){\n%s\n} else {\n%s\n}", condition, then, thenElse);
  }

  @Override
  public String visit(ForStatement forStatement, SymbolTable arg) {
    arg.enterInScope();
    String variable = forStatement.getVariable().accept(this, arg);
    String initExpr = forStatement.getInitialConditionExpression().accept(this, arg);
    String postCond = forStatement.getLoopConditionExpression().accept(this, arg);
    String statements = this.beautify(forStatement.getStatements(), new StringJoiner("\n"), arg);
    arg.exitFromScope();
    return String.format("{\nint %s;\nfor(%s = %s; %s < %s; %s++){\n%s\n}\n}", variable, variable, initExpr, variable, postCond, variable, statements);
  }

  @Override
  public String visit(AssignStatement assignStatement, SymbolTable arg) {
    String left = assignStatement.getId().accept(this, arg);
    String right = assignStatement.getExpression().accept(this, arg);
    return String.format("%s = %s;", left, right);
  }

  @Override
  public String visit(CallFunctionStatement callFunctionStatement, SymbolTable arg) {
    StringJoiner funJoiner = new StringJoiner(", ");
    callFunctionStatement.getExpressionsParams().forEach(i -> funJoiner.add(i.accept(this, arg)));
    String fName = callFunctionStatement.getId().accept(this, arg);
    return String.format("%s(%s);", fName, funJoiner.toString());
  }

  @Override
  public String visit(ReadStatement readStatement, SymbolTable arg) {
    StringJoiner scanfs = new StringJoiner("\n");
    readStatement.getVariables().forEach(var -> {
      String type = this.getFormatSpecifier(arg.getTableEntryIfExists(var.getValue()).get().getNodeType());
      String varName = (type == "%s" ? "&" + var.getName() : var.getName());
      scanfs.add(String.format("scanf(\"%s\", &%s);", type, varName));
    });
    return scanfs.toString();
  }

  @Override
  public String visit(WriteStatement writeStatements, SymbolTable arg) {
    StringJoiner printfs = new StringJoiner("\n");
    writeStatements.getExpressions().forEach(expr -> {
      String type = this.getFormatSpecifier(expr.getNodeType());
      String toPrint = expr.accept(this, arg);
      printfs.add(String.format("printf(\"%s\", %s);", type, toPrint));
    });
    return printfs.toString();
  }

  @Override
  public String visit(ReturnStatement returnStatement, SymbolTable arg) {
    String toReturn = returnStatement.getReturnExpression().accept(this, arg);
    return String.format("return %s;", toReturn);
  }

  @Override
  public String visit(LocalStatement localStatement, SymbolTable arg) {
    arg.enterInScope();
    String varDecls = this.beautify(localStatement.getVarDecls(), new StringJoiner("\n"), arg);
    String statements = this.beautify(localStatement.getStatements(), new StringJoiner("\n"), arg);
    arg.exitFromScope();
    return String.format("{\n%s\n%s\n}", varDecls, statements);
  }

  @Override
  public String visit(NilConst nilConst, SymbolTable arg) {
    return "NULL";
  }

  @Override
  public String visit(BooleanConst booleanConst, SymbolTable arg) {
    return Boolean.toString(booleanConst.getValue());
  }

  @Override
  public String visit(IntegerConst integerConst, SymbolTable arg) {
    return Integer.toString(integerConst.getValue());
  }

  @Override
  public String visit(FloatConst floatConst, SymbolTable arg) {
    return Float.toString(floatConst.getValue());
  }

  @Override
  public String visit(StringConst stringConst, SymbolTable arg) {
    return "\"" + stringConst.getValue() + "\"";
  }

  @Override
  public String visit(ArrayAssignStatement arrayAssignStatement, SymbolTable arg) {
    String array = arrayAssignStatement.getArrayExpression().accept(this, arg);
    String index = arrayAssignStatement.getArrayPointExpression().accept(this, arg);
    String assignee = arrayAssignStatement.getAssignExpression().accept(this, arg);
    return String.format("%s[%s] = %s;", array, index, assignee);
  }

  @Override
  public String visit(ArrayReadElement arrayReadElement, SymbolTable arg) {
    String array = arrayReadElement.getArrayAssignExpr().accept(this, arg);
    String index = arrayReadElement.getIndexExpression().accept(this, arg);
    return String.format("%s[%s]", array, index, index);
  }

  @Override
  public String visit(FunctionCall functionCall, SymbolTable arg) {
    StringJoiner funJoiner = new StringJoiner(", ");
    functionCall.getExprs().forEach(i -> funJoiner.add(i.accept(this, arg)));
    String fName = functionCall.getId().accept(this, arg);
    return String.format("%s(%s)", fName, funJoiner.toString());
  }

  @Override
  public String visit(MinusOperation minusOperation, SymbolTable arg) {
    String left = minusOperation.getLeftOperand().accept(this, arg);
    String right = minusOperation.getRightOperand().accept(this, arg);
    return String.format("%s - %s", left, right);
  }

  @Override
  public String visit(PlusOperation plusOperation, SymbolTable arg) {
    String left = plusOperation.getLeftOperand().accept(this, arg);
    String right = plusOperation.getRightOperand().accept(this, arg);
    return String.format("%s + %s", left, right);
  }

  @Override
  public String visit(TimesOperation timesOperation, SymbolTable arg) {
    String left = timesOperation.getLeftOperand().accept(this, arg);
    String right = timesOperation.getRightOperand().accept(this, arg);
    return String.format("%s * %s", left, right);
  }

  @Override
  public String visit(DivOperation divOperation, SymbolTable arg) {
    String left = divOperation.getLeftOperand().accept(this, arg);
    String right = divOperation.getRightOperand().accept(this, arg);
    return String.format("%s / %s", left, right);
  }

  @Override
  public String visit(AndRelOperation andRelOperation, SymbolTable arg) {
    String left = andRelOperation.getLeftOperand().accept(this, arg);
    String right = andRelOperation.getRightOperand().accept(this, arg);
    return String.format("%s && %s", left, right);
  }

  @Override
  public String visit(GreatThanRelOperation greatThanRelOperation, SymbolTable arg) {
    String left = greatThanRelOperation.getLeftOperand().accept(this, arg);
    String right = greatThanRelOperation.getRightOperand().accept(this, arg);
    return String.format("%s > %s", left, right);
  }

  @Override
  public String visit(OrRelOperation orRelOperation, SymbolTable arg) {
    String left = orRelOperation.getLeftOperand().accept(this, arg);
    String right = orRelOperation.getRightOperand().accept(this, arg);
    return String.format("%s || %s", left, right);
  }

  @Override
  public String visit(GreatThanEqualsRelOperation greatThanEqualsRelOperation, SymbolTable arg) {
    String left = greatThanEqualsRelOperation.getLeftOperand().accept(this, arg);
    String right = greatThanEqualsRelOperation.getRightOperand().accept(this, arg);
    return String.format("%s >= %s", left, right);
  }

  @Override
  public String visit(LessThanRelOperation lessThanRelOperation, SymbolTable arg) {
    String left = lessThanRelOperation.getLeftOperand().accept(this, arg);
    String right = lessThanRelOperation.getRightOperand().accept(this, arg);
    return String.format("%s < %s", left, right);
  }

  @Override
  public String visit(LessThanEqualsRelOperation lessThanEqualsRelOperation, SymbolTable arg) {
    String left = lessThanEqualsRelOperation.getLeftOperand().accept(this, arg);
    String right = lessThanEqualsRelOperation.getRightOperand().accept(this, arg);
    return String.format("%s <= %s", left, right);
  }

  @Override
  public String visit(EqualsRelOperation equalsRelOperation, SymbolTable arg) {
    String left = equalsRelOperation.getLeftOperand().accept(this, arg);
    String right = equalsRelOperation.getRightOperand().accept(this, arg);
    return String.format("%s == %s", left, right);
  }

  @Override
  public String visit(NotEqualsRelOperation notEqualsRelOperation, SymbolTable arg) {
    String left = notEqualsRelOperation.getLeftOperand().accept(this, arg);
    String right = notEqualsRelOperation.getRightOperand().accept(this, arg);
    return String.format("%s != %s", left, right);
  }

  @Override
  public String visit(UminusExpression uminusExpression, SymbolTable arg) {
    String left = uminusExpression.getExpr().accept(this, arg);
    return String.format("-%s", left);
  }

  @Override
  public String visit(NotExpression notExpression, SymbolTable arg) {
    String left = notExpression.getExpr().accept(this, arg);
    return String.format("!(%s)", left);
  }

  @Override
  public String visit(SharpExpression sharpExpression, SymbolTable arg) {
    String a = sharpExpression.getExpr().accept(this, arg);
    sharpExpression.setType(PrimitiveNodeType.INT);
    if (sharpExpression.getExpr().getNodeType().toString().equalsIgnoreCase("string")) {
      return String.format("strlen(%s)", a);
    } else {
      return String.format("count(%s)", a);
    }
  }

  @Override
  public String visit(Id id, SymbolTable arg) {
    return id.getValue();
  }

  @Override
  public String visit(ArrayConst arrayConst, SymbolTable arg) {
    return "{ }";
  }

  @Override
  public String visit(NopStatement nopStatement, SymbolTable arg) {
    return "nop();";
  }

  @Override
  public String visit(WhileStatement whileStatement, SymbolTable arg) {
    String expr = whileStatement.getConditionExpression().accept(this, arg);
    String statements = this.beautify(whileStatement.getStatements(), new StringJoiner("\n"), arg);
    return String.format("while(%s){\n%s\n}", expr, statements);
  }

  @Override
  public String visit(Variable variable, SymbolTable arg) {
    return variable.getName();
  }
}
