global
    y : int = 0 ;
    operation: int = -1
end

function scegliOp(fake:int): int
    "\n\nBenvenuto nel programma" ==>;
    "\n\nOperazione da svolgere?" ==>;
    "\n1 - Somma " ==>;
    "\n2 - Moltiplicazione " ==>;
    "\n3 - Divisione " ==>;
    "\n4 - Potenza " ==>;
    "\n5 - Fibonacci " ==>;
    "\n0 - Uscita \n\n" ==>;
    operation <==;
    return operation
end

function fib(num:int):int
    if num == 0 or num == 1 then
        return num
    end;
    return fib(num -1) + fib(num -2)
end

function eseguiOp(operation:int): nil
    if operation == 0 then
        nop
    else
        if operation >= 1 and operation <= 5 then
            "Inserisci il primo operando (x) \n" ==>;
             x <== ;
            if operation <5 then
                 "Inserisci il secondo operando (y)\n" ==>;
                 y <==
            end;
            if operation == 1 then
                local
                    result:int = x + y;
                    "\nLa somma è: ", result  ==>
                end
            end;
            if operation == 2 then
                local
                    result:int = 0;
                    i: int;
                    for i = 0, y do
                        result = result+ x
                    end;
                    "\nIl prodotto è: ", result  ==>
                end
            end;
            if operation == 3 then
                local
                    result:float = x/y;
                    "\nIl risultato della divisione è: ", result  ==>
                end
            end;
            if operation == 4 then
                local
                    result:int = 1;
                    i: int;
                    for i = 0, y do
                        result = result * x
                    end;
                    "\nIl risultato è: ", result  ==>
                 end
            end;
            if operation == 5 then
                local
                    res:int = fib(x);
                    "\nFibonacci: ", res  ==>
                end
            end
         else
            "\nScelta non valida" ==>
         end
    end
end

function main(): nil
    while operation != 0 do
         operation= scegliOp(1);
         eseguiOp(operation)
    end
end