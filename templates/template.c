/**
* C program compiled by Mypallene compiler
* Developed By Alexander Minichino
*/
#include <stdio.h>
#include <string.h>

#define nop() ;
#define count(x)  (sizeof(x) / sizeof((x)[0]))

typedef int bool;
#define true 1
#define false 0

/*
* Var declarations
*/
{{var_declarations}}



/*
* Function definitions
*/
{{function_definitions}}