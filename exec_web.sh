#!/bin/bash
echo "Executing script in "$PWD
#Extract environments variables
source $PWD/lib/emsdk/emsdk_env.sh
echo "Compiling " $1.c " through Emscripten..."
#Delete if exists
rm -r $1_web
mkdir $1_web
emcc $1.c -o $1_web/out.html

