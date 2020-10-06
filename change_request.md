# Modifica esame

Si aggiunga al linguaggio My.Pallene il tipo array di float. Deve essere possibile dichiarare array in questo modo:

 
```
a :  float[8];  
b :  float[10]; 
```
 

Per ogni variabile di aggiunga alla tabella dei simboli sia l'informazione che è un array, il tipo base che è fisso: "float" e la taglia dell'array.

 

Si costruisca quindi l'operatore di assegnamento di array "=^" usato in questo modo:

 
```
x =^ y;
```

 

dove x e y devono essere ID dichiarati come array di tipo float  e la taglia di y deve essere <= quella di x

 


La traduzione in C deve essere

 
```
for (i=0; i<n;i++){             // dove n è la taglia di y
    x[i] = y[i];
}
```

 

Opzionale: mettere 0 nei rimanenti elementi di b