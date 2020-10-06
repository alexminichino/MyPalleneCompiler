# Mypallene compiler

## About 

#### Compiler implemented for university project of Compilers exam

The project consists in an real application of principal notions of compilers course at University computer science department of University of Salerno.\
The first part of project is related to lexical and syntax analysis and consists in realizing an Lexical analyzer through jFlex and an parser with CUP and to produce an XML syntax tree of code (writend in Mypallene language) passed in input.\
The second one is related to semantic analysis, where the scope and type checks are made, and code generation, where the target code is produced and compiled in LLVM and C which, finally, is executed!

## Language specifications

#### Lexical specifications

| FUNCTION     | function                    |
|--------------|-----------------------------|
| MAIN         | main                        |
| LPAR         | (                           |
| RPAR         | )                           |
| END          | end                         |
| ID           | Java id format              |
| COLON        | :                           |
| GLOBAL       | {"global"}                  |
| SEMI         | ;                           |
| COMMA        | ,                           |
| NIL          | nil                         |
| INT          | int                         |
| BOOL         | bool                        |
| FLOAT        | float                       |
| STRING       | string                      |
| BLPAR        | {                           |
| BRPAR        | }                           |
| ARROW        | ->                          |
| ASSIGN       | =                           |
| NOP          | nop                         |
| WHILE        | while                       |
| DO           | do                          |
| IF           | if                          |
| THEN         | then                        |
| ELSE         | else                        |
| FOR          | for                         |
| LOCAL        | local                       |
| SLPAR        | [                           |
| SRPAR        | ]                           |
| READ         | <==                         |
| WRITE        | ==>                         |
| RETURN       | return                      |
| TRUE         | true                        |
| FALSE        | false                       |
| INT_CONST    | Java Decimal integer format |
| FLOAT_CONST  | Java float format           |
| STRING_CONST | Java format constant format |
| PLUS         | +                           |
| MINUS        | -                           |
| TIMES        | *                           |
| DIV          | /                           |
| AND          | and                         |
| OR           | or                          |
| GT           | >                           |
| GE           | >=                          |
| LT           | <                           |
| LE           | <=                          |
| EQ           | ==                          |
| NE           | !=                          |
| NOT          | not                         |
| SHARP        | #                           |

#### Grammar 
```
Programma : Global Functions

Global : GLOBAL Var_decls END
       | ε

Functions : Def_fun Functions
          | Def_fun

Def_fun : FUNCTION ID LPAR Par_decls RPAR COLON Type Statements END
        | FUNCTION ID LPAR RPAR COLON Type Statements END

Par_decls : ID COLON Type COMMA Par_decls
          | ID COLON Type

Var_decls : ID COLON Type Var_init_value SEMI Var_decls
          | ID COLON Type Var_init_value

Var_init_value : ASSIGN Expr
               | ε

Type : NIL | INT | BOOL | FLOAT | STRING
     | BLPAR Type BRPAR
     | LPAR Types RPAR ARROW Type
     | LPAR RPAR ARROW Type
   
Types : Type COMMA Types
      | Type
   
Statements : Stat SEMI Statements
           | Stat
   
Stat : NOP
     | WHILE Expr DO Statements END
     | IF Expr THEN Statements ELSE Statements END
     | IF Expr THEN Statements END
     | FOR ID ASSIGN Expr COMMA Expr DO Statements END
     | LOCAL Var_decls SEMI Statements END
     | ID ASSIGN Expr
     | Expr SLPAR Expr SRPAR ASSIGN Expr
     | ID LPAR Exprs RPAR
     | Vars READ
     | Exprs WRITE
     | RETURN Expr
   
Vars : ID COMMA Vars
     | ID
   
Exprs : Expr COMMA Exprs
      | ExprExpr : NIL
      | TRUE
      | FALSE
      | INT_CONST
      | FLOAT_CONST
      | STRING_CONST
      | BLPAR BRPAR COLON Type
      | ID
      | Expr SLPAR Expr SRPAR
      | ID LPAR Exprs RPAR
      | Expr PLUS Expr
      | Expr MINUS Expr
      | Expr TIMES Expr
      | Expr DIV Expr
      | Expr AND Expr
      | Expr OR Expr
      | Expr GT Expr
      | Expr GE Expr
      | Expr LT Expr
      | Expr LE Expr
      | Expr EQ Expr
      | Expr NE Expr
      | MINUS Expr
      | NOT Expr
      | SHARP Expr
```
## Implementation

The compiler class is the `src/stater.MyPallene.java`.
- `src`
  - `stater` package contains the compiler class and the main classes.

  - `generated` package contains the jFlex and CUP generated files

  - `errors` package contains the error handler

  - `lexical` package contains the string table 

  - `nodetype` package contains the type class of a node

  - `semantic` package contains the symbol table classes

  - `syntax` package contains the AST node classes

  - `utils` package contains the utils classes used in translation to XML and C

  - `test` package contains some test classes

  - `visitor` package contains the visitor classes used for scopechecking, typechecking and translation phases

- `srcflexcup` folder contains the jFlex and cup files

- `test_files` folder contains the test files writen in Mypallene

- `templates` folder contains the template c file used in translation

## Usage
#### To compile an Mypallene file execute 
- `MyPallene2C.java` or `MyPallene2C.java <filePath>`.\
If the `<filePath>` is specified the compiler will compile the file located in its, otherwise the compiler will ask you wich file (present in `test_files` folder) do you want compile.\
The ececution will produce:
  - `filename.xml` - contains the AST
  - `filename.ll` - contains the LLVM file
  - `filename.c` - contains the C program file

- `MyPallene2Web.java` or `MyPallene2Web.java <filePath>` wich produces the same files of `MyPallene2C.java` adding one folder:
  - `filename_web` - contains the webassembly and files generated by Emscripten
  
#### To generate new Lexer or Parser execute

- jFlex `{JFLEX_INSTALLATION_PATH}\jflex -d src\generated srcjflexcup\Lexer.flex`
- CUP `java -jar {CUP_INSTALLATION_PATH}\java-cup-11b.jar -locations -dump -progress -destdir src\generated srcjflexcup\Parser.cup  2> parserGeneration.log`

## Thanks to

Prof. Gennaro Costagliola and Dipartimento di Informatica of University Of Salerno.

<img src="logo_dipartimento.png" height="80"> 


