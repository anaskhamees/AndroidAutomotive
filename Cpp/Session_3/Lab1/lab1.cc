#include<iostream>
#include<cmath>
#include <cctype>
#include <cstring> // For strlen() function



namespace MathFunctions{
void print(){
int number=0;
std::cout<<"Enter the number to get square root: ";
std::cin>>number;
std::cout<<" The square root: "<<sqrt(number)<<std::endl;
}
}

namespace StringFunctions{

void print()
{
char arrString[20];
char ConvertedStr[20]={0};
std::cout<<"Enter the string to be converted into Lower/Upper: ";
std::cin>>arrString;
for(int i=0;i<strlen(arrString);i++)
{
	if(isupper(arrString[i]))
	{
		ConvertedStr[i]=tolower(arrString[i]);
	}
	else
	{
		ConvertedStr[i]=toupper(arrString[i]);
	}
} 
std::cout<<"The Converted String is : ";
for(int i=0;i<strlen(ConvertedStr);i++)
{
	std::cout<<ConvertedStr[i];
}
std::cout<<std::endl;
}

}

namespace ArrayFunctions{

void print()
{
	int size=0;
	std::cout<<"Enter the Array size: ";
	std::cin>>size;
	int arr[size];
	std::cout<<std::endl<<"Enter the Array Elements: ";
	for(int i=0;i<size;i++)
	{
		std::cin>>arr[i];
	}
	
	std::cout<<"The reversed Array is: "<<std::endl;
	for(int i=size-1;i>=0;i--)
	{
		std::cout<<"Index "<<i<<" : "<<arr[i]<<std::endl;
	}
	std::cout<<std::endl;
}

}

int main()
{

MathFunctions::print();
std::cout<<"#########################################################"<<std::endl;

StringFunctions::print();
std::cout<<"#########################################################"<<std::endl;

ArrayFunctions::print();
std::cout<<"#########################################################"<<std::endl;

return 0;
}
