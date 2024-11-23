#include<iostream>


int main()
{
	int Num=0;
	int result=0;
	do
	{
		std::cout<<" Please Enter the Number: ";
		std::cin>>Num;
		result+=Num;
	}while(Num!=0);
	
	std::cout<<" The result: "<<result<<"\n"; 
	return 0;
}



