#include<iostream>

int main()
{
	unsigned int InputSeconds=0;
	unsigned int Seconds=0;
	unsigned int Mins=0;
	unsigned int Hrs=0;
	std::cout<<" Enter the Input Seconds: ";
	std::cin>>InputSeconds;
	Hrs=InputSeconds/(3600);
	InputSeconds=InputSeconds%(3600);
	Mins=InputSeconds/60;
	Seconds=InputSeconds%60;
	std::cout<<"#######################################################\n";
	std::cout<<" H:M:S -  "<<Hrs<<" : "<<Mins<<" : "<<Seconds<<"\n";
	std::cout<<"#######################################################\n";
return 0;
}
