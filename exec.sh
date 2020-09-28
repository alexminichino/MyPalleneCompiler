#!/bin/bash
echo "Executing script in "$PWD
echo "Ident c code.."
clang-format -i $1.c
echo "Compiling LLVM..." $1.c
clang $1.c -S -emit-llvm -v -o $1.ll 2>&1 | tee outputClang.log
if [ $2 ==  "-exec" ]
then
  echo "Executing..."
  lli $1.ll
fi
