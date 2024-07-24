#include<iostream>
#include<cstdlib>
#include<ctime>

class vertex
{
	private:
		int x;
		int y;
	public:
		vertex(){x=0;y=0;}
		void setRandom()
		{
			std::srand(static_cast<unsigned>(std::time(0)));
			x=std::rand()%201-100;
			y=std::rand()%201-100;
		}
		
		int getX(){return x;}
		int getY(){return y;}
		std::string string_rep()
		{
			return " (x,y) : "+std::to_string(x)+" , "+std::to_string(y);
		}
		~vertex(){}
};


int main()
{
	vertex v;
	std::cout<<"vertices before : "<<v.string_rep();
	v.setRandom();
	std::cout<<"\nvertices after  : "<<v.string_rep()<<"\n";
	
	return 0;
}
