#include<iostream>
#include <string> 
#include<string_view>
std::string sourceString="";
std::string replacedStr="";
std::string replacedBy="";

void strFun(std::string& src_string,std::string_view replaced_string,std::string_view replacedby_string)
{
	int pos=src_string.find(replaced_string);
	src_string.replace(pos,replaced_string.size(),replacedby_string);

}
int main()
{

std::cout<<"Enter the source string: ";
getline(std::cin,sourceString);

std::cout<<"Enter the string to be replaced : ";
getline(std::cin,replacedStr);

std::cout<<"Enter the string replaced by : ";
getline(std::cin,replacedBy);

strFun(sourceString,replacedStr,replacedBy);
std::cout<<std::endl<<sourceString<<std::endl;


return 0;
}
