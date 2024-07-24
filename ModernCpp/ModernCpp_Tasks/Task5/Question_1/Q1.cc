#include<iostream>


class Time
{
	private:
		int hours  ;
		int minutes;
		int seconds;
	public:
		
Time():hours(0),minutes(0),seconds(0){}		Time(int hrs,int mins,int secs):hours(hrs),minutes(mins),seconds(secs){}
void displayTime()
{
	std::cout<<" Time : "<<hours<<":"<<minutes<<":"<<seconds<<"\n";
}		

Time addObj(const Time& obj1,const Time& obj2)
{
	Time obj3;
	obj3.hours=obj1.hours+obj2.hours;
	if(obj3.hours>23)
	{
		obj3.hours=0;	
	}
	obj3.minutes=obj1.minutes+obj2.minutes;
	{
		if(obj3.minutes>59)
		{
			obj3.minutes=0;
			obj3.hours++;		
		}
	}
	obj3.seconds=obj1.seconds+obj2.seconds;	
	{
		obj3.seconds=0;
		obj3.seconds++;
	}
	return obj3;
}

~Time(){}
			
};

int main()
{
	Time obj1(11,59,59),obj2(11,59,59),obj3;
	obj1.displayTime();
	obj2.displayTime();
	obj3=obj3.addObj(obj1,obj2);
	obj3.displayTime();
return 0;
}
