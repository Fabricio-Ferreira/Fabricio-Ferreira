#include <conio.h>
#include <stdio.h>
#include <locale.h>
#include <iostream>
#include <cstdlib>


main (void)
{
		int x11,x12,y1=0;
		int x21,x22,y2=0;
		int x31,x32,y3=1;
		int x41,x42,y4=1;
		const int v=1;
		int w0=0,w1=0,w2=0;
		int soma1,soma2,soma3,soma4,y;
		const int c=1;
		
		x11=0;
		x12=0;
		x21=0;
		x22=1;
		x31=1;
		x32=0;
		x41=1;
		x42=1;
	
			do{
					
					
					soma1 = (x11*w1)+(x12*w2)+(v*w0);
					if (soma1 <= 0){
						y = 0;
						
					}else{
						if (soma1 > 0){
							y = 1;
						}
					}
					if (y != y1){
						w0= (w0 + (c * (y1 - y)* v));
						w1= (w1 + (c * (y1 - y)* x11));
						w2= (w2 + (c * (y1 - y)* x12));	
					}
					
					soma2 = (x21*w1)+(x22*w2)+(v*w0);
					if (soma2 <= 0){
						y = 0;
					
					}else{
						if (soma2 > 0){
							y = 1;
						}
					}
					if (y != y2){
						w0= (w0 + (c * (y2 - y)* v));
						w1= (w1 + (c * (y2 - y)* x21));
						w2= (w2 + (c * (y2 - y)* x22));	
					}
					
					
					soma3 = (x31*w1)+(x32*w2)+(v*w0);
					if (soma3 > 0){
						y = 1;
						
					}else{
						if (soma3 <= 0){
							y = 0;
						}
					}
					if (y != y3){
						w0= (w0 + (c * (y3 - y)* v));
						w1= (w1 + (c * (y3 - y)* x31));
						w2= (w2 + (c * (y3 - y)* x32));	
					}
						
					
					soma4 = (x41*w1)+(x42*w2)+(v*w0);
					if (soma4 > 0){
						y = 1;
					
					}else{
						if (soma4 <= 0){
							y = 0;
						}
					}
					if (y != y4){
						w0= (w0 + (c * (y4 - y)* v));
						w1= (w1 + (c * (y4 - y)* x41));
						w2= (w2 + (c * (y4 - y)* x42));	
					}
					
				
								
			}while (y1!=0 || y2!=0 || y3 !=1 || y4 !=1);
			
			printf("\n Pesos finais: %i, %i, %i", w0,w1,w2);
			
			
		}


