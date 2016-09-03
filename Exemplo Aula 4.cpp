#include <conio.h>
#include <stdio.h>
#include <locale.h>
#include <iostream>
#include <cstdlib>

int main(){
	
	int x11,x12,x21,x22;
	int y,y1,y2,w1,w2;
	int soma1,soma2,c,i,ar;
	
	x21 = 0;
	x22 = 0;
	w1 = -1;
	w2 = -1;
	c = 1;

	
	do{
		i = 0;
		ar = 0;
		
		printf("\n Digite um valor:");
		scanf("%i",&x11);
		printf("\n Digite um valor:");
		scanf("%i",&x12);
			
		soma1= ((x11*w1) + (x12*w2));
		if(soma1 < 0){
				y = 0;
			}
			else {
				if(soma1 >= 0 && soma1 <= 1){
					y = soma1;
				}
				else {
					if (soma1 > 1){
						y = 1;
					}
				}
			}
		if(y != y1){
			w1= (w1 + (c * (y1 - y)* x11));
			w2= (w2 + (c * (y1 - y)* x12));	
			ar++;		
		}
		
		soma2= ((x21*w1) + (x22*w2));
		if(soma2 < 0){
				y = 0;
			}
			else {
				if(soma2 >= 0 && soma2 <= 1){
					y = soma2;
				}
				else {
					if (soma2 > 1){
						y = 1;
					}
				}
			}	
		if(y != y2){
			w1= (w1 +(c * (y2 - y)* x21));
			w2= (w2 +(c * (y2 - y)* x22));
			ar++;
		}
		
		i++;
		ar= ar;
		
	}while(y1==1 && y2==0);
	
	printf("\n Os pesos finais: %i , %i\n", w1, w2);
	printf("\n Foram ajustes real: %i\n", ar);
	printf("\n Quantidade de loop: %i\n", i);
	printf("\n A saida obtida: %i\n", y);
}
