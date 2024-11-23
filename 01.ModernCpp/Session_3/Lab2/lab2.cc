#include <iostream>

namespace DynamicAlloc{

void create2DArr()
{
int row=0;
int col=0;
 std::cout<<"Enter the Row size: ";
 std::cin>>row;
 
 std::cout<<std::endl<<"Enter the col size: ";
 std::cin>>col;
 std::cout<<std::endl;

int** ptr=new int*[row];

for(int i=0;i<row;i++)
{
	ptr[i]=new int[col];
}

for(int i=0;i<row;i++)
{
	std::cout<<"Enter The Row "<<i<<" :"<<std::endl;
	for(int j=0;j<col;j++)
	{
		std::cin>>ptr[i][j];
			std::cout<<std::endl;
	}
	std::cout<<std::endl;
}


for(int i=0;i<row;i++)
{
	std::cout<<"The Row "<<i<<" :"<<std::endl;
	for(int j=0;j<col;j++)
	{
		std::cout<<ptr[i][j];
	}
	std::cout<<std::endl;
}
for(int i = 0; i < row; i++) {
        delete[] ptr[i];
    }
    delete[] ptr;
}
}

int main()
{
	DynamicAlloc::create2DArr();
	return 0;
}
