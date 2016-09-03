#include <iostream>
#include <conio.h>
#include <stdio.h>
#include <cstdlib>

int main()
{
	float x1,x2,w1,w2,soma,y;
	float lr,fr,fs;
	int op;
	
	printf("digite um numero: ");
	scanf("%f",&x1);
	printf("digite um numero: ");
	scanf("%f",&x2);
	printf("informe o peso 1: ");
	scanf("%f",&w1);
	printf("informe o peso 2: ");
	scanf("%f",&w2);
	
	
	soma = (x1*w1)+(x2*w2);
	
	printf("\n Informe a função de transparencia?\n 1.LIMITE RAPIDO\t2.FUNCAO RAMPA\t3.FUNCAO SIGMOIDE\n");
	scanf("%i",&op);
	switch (op){
		case 1:
			if(soma <= 0 ){
			
				y = -1;
				printf("A saida do neuronio eh: %.2f",y);
			}
			else{
				y = 1;
				printf("A saida do neuronio eh: %.2f",y);
			}
			break;
		case 2:
			if(soma < 0){
				y = 0;
				printf("A saida do neuronio eh: %.2f",y);
			}
			else {
				if(soma >= 0 && soma <= 1){
					y = soma;
					printf("A saida do neuronio eh: %.2f",y);
				}
				else {
					if (soma > 1){
						y = 1;
						printf("A saida do neuronio eh: %.2f",y);
					}
				}
			}
			break;
		case 3:
			if(soma >= 0){
				y = 1 -1/(1 + soma);
				printf("A saida do neuronio eh: %.2f",y);
			}
			else{
				y = -1 +1/(1 - soma);
				printf("A saida do neuronio eh: %.2f",y);
			}
			break;
	}
	
	printf("\na soma e = %.2f",soma);
	getch();
		
}
