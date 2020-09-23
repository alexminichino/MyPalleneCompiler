global
    x : float = 0.0 ;
    y : float = 0.0 ;
    operation: int = -1;
    result : float = 0.0
end


function scegliOp(fake:int): int
    "\nBenvenuto nel programma" ==>;
    "\nOperazione da svolgere?" ==>;
    "\n1 - Addizione " ==>;
    "\n2 - Sottrazione " ==>;
    "\n3 - Moltiplicazione " ==>;
    "\n4 - Quadrato " ==>;
    "\n0 - Uscita \n" ==>;
    operation <==;
    return operation
end

function eseguiOp(operation:int): nil
    if operation == 0 then
        nop
    else
        if operation >= 1 and operation <= 4 then
            "Inserisci il primo operando (x) \n" ==>;
             x <== ;
            if operation <4 then
                 "Inserisci il secondo operando (y)\n" ==>;
                 y <==
            end;
            if operation == 1 then
                result = x + y;
                "\nLa somma è: ", result  ==>
            end;
            if operation == 2 then
                result = x - y;
                "\nLa differenza è: ", result  ==>
            end;
            if operation == 3 then
                result = x * y;
                "\nIl prodotto è: ", result  ==>
            end;
            if operation == 4 then
                result = x * x ;
                "\nIl risultato del quadrato  è: ", result  ==>;
                "\n\n" ==>
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