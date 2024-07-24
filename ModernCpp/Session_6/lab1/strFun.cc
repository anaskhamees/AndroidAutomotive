#include<iostream>

std::string name="The cycle of life is a cycle of cycles.";
std::string cycle="cycle";
std::string circle="circle";
int main()
{
		std::cout<<"Old String: "<<name<<std::endl;
		int pos=name.find(cycle);
		while(pos!=std::string::npos)
		{
			name.replace(pos,cycle.size(),circle);
			pos=name.find(cycle,circle.size()+pos);
		}
		
		std::cout<<"New String: "<<name<<std::endl;
		std::cout<<"#######################################################################\n";
		pos=name.find(circle);
		name.insert(pos-1," great");
		std::cout<<"New New String: "<<name<<std::endl;
		std::cout<<"#######################################################################\n";
		pos=name.find("circle",name.find("circle")+circle.size());
		name.insert(pos-1," Never-ending ");
		std::cout<<"New New New String: "<<name<<std::endl;
	return 0;
}
