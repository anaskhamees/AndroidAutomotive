#include<iostream>

class car
{
	private:
		std::string company;
		std::string model;
		int Year;
	public:
		car(){}
		void setCompany(std::string companyName)
		{
			company=companyName;
		}
		void setModel(std::string modelName)
		{
			model=modelName;
		}
		void setYear(int year)
		{
			Year=year;
		}
		std::string getCompany()
		{
			return company;
		}
		std::string getModel()
		{
			return model;
		}
		int getYear()
		{
			return Year;
		}
		
		~car(){}

};

int main()
{
	car car1;
	car1.setCompany("OPEL");
	car1.setModel("LAND GRAND");
	car1.setYear(2022);
	
	std::cout<<" Car Company: "<<car1.getCompany()<<"\n";
	std::cout<<" Car Model  : "<<car1.getModel()<<"\n";
	std::cout<<" Car Year   : "<<car1.getYear()<<"\n"; 
}
