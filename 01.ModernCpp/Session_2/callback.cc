#include <stdio.h>
#include <stdlib.h>

typedef int*(*cbFun)(int** Arr2D,int colSize,int* rowSizes,int(*fun)(int*,int));

int ArrSum(int* arr,int size)
{
	int sum=0;
	for(int i=0;i<size;i++)
	{
		sum+=arr[i];
	}
return sum;
}

int* GetrowSum(int** Arr2D,int colSize,int* rowSizes,int(*rowSum)(int*,int))
{
	int* arrSum=(int*)malloc(sizeof(int)*colSize);
	for(int i=0;i<colSize;i++)
	{
		arrSum[i]=rowSum(Arr2D[i],rowSizes[i]);
	} 

return arrSum;
}


int main()
{
	int arr1[]={1,2,3};
	int arr2[]={5,6,7};
	int arr3[]={8,9,10};
	int* Arr2D[]={arr1,arr2,arr3};
	int rowsizes[]={3,3,3};
	int colsize=3;
	
	cbFun callback=GetrowSum;
	int* rowsum=callback(Arr2D,colsize,rowsizes,ArrSum);

	for(int i=0;i<colsize;i++)
	{
			printf("The Row %i Sum: %i",i,rowsum[i]);
			printf("\n");
	
	}
	
	free(rowsum);

return 0;

}
