global
    a:int = 0;
    b:int ;
    c:nil ;
    d:bool = true ;
    e:bool = false ;
    f:bool ;
    g:float = 10.2;
    h:float = 10.2;
    i:string = "TEST";
    j:string;
    k: {string}
end

function plus(var1:int, var2:int): int
    return var1+var2
end

function blank(): string
    return "result"
end

function whileFun(): nil
    local
        localVar: int = 0;
        while localVar <= 10 do
            "\nWhile iter : ", localVar ==>;
            localVar = localVar +1
        end
    end
end

function forFun(): nil
    local
        index: int;
        for index =0, index <= 10 do
            "\nFor iter : ", index ==>
        end;
        for index =0, 10 do
            "\n 2th For iter : ", index ==>
        end
    end
end

function ifThenElse(): nil
    a=0+0+0+0;
    if a > -1 then "\nthen stmt" ==> end;
    if a < 1 then "\nthen stmt2" ==> end;
    if a >= 0 then "\nthen stmt3" ==> end;
    if a <= 10 then "\nthen stmt4" ==> end;
    if a == 0 then "\nthen stmt5" ==> end;
    if a != 10 then "\nthen stmt6" ==> end;
    if a != 0 then "\nthen stmt7" ==> else "\nelse stmt7" ==> end
end

function arrayTest(): nil
    k[0+0]= "Test";
    local
        arr:{int};
        arr[0]=1;
        arr[1]=2;
        arr[2]=3;
        arr[3]=4;
        arr[4]=5;
        "0 index element ", arr[0] ==>;
        for index =0, 4 do
            "\nArray content : arr[",index, "] = ", arr[index]  ==>
        end;
        local
            var1:int = #arr;
            "\n\nSize of array : ", var1 ==>
        end
    end
end

function testExprs(): nil
    "\nMinus : ", -2 ==>;
    "\nBoolean VAL= ", not d, "\n" ==>
end


function main(): nil
    "Insert 2 numbers" ==>;
    a, b <==;
    "Result a + b = ", plus(a,b), "\n" ==>;
    "Test: ", blank() ==>;
    whileFun();
    forFun();
    ifThenElse();
    arrayTest();
    testExprs()
end


