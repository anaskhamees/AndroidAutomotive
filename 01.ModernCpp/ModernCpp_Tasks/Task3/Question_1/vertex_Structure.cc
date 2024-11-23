#include<iostream>
#include<cstdlib>
#include<ctime>
struct vertex
{
	int x;
	int y;
};

int main()
{
	/* Seed the random number with the current time */
	std::srand(static_cast<unsigned>(std::time(0)));
	vertex vertices[5];
	/* Generate 5 random vertices */
	for(int i=0;i<5;i++)
	{
		vertices[i].x=std::rand()%201-100;
		vertices[i].y=std::rand()%201-100;
	}
	
 std::cout << "Random vertices (x, y):" << std::endl;
    for (int i = 0; i < 5; ++i) {
        std::cout << "(" << vertices[i].x << ", " << vertices[i].y << ")" << std::endl;
    }
	return 0;
}

