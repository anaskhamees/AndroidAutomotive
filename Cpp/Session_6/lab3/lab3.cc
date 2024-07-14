#include<iostream>
#include<vector>


std::vector<std::vector<int>> transpose2D(std::vector<std::vector<int>> v,int row,int col)
{
	std::vector<std::vector<int>> vec;
	std::cout<<"The 2D Vector is :\n";

	for(int i=0;i<row;i++){
		
		for(int j=0;j<col;j++)
		{
			vec[i][j]=v[j][i];
		}
		std::cout<<std::endl;
	}
	
	return vec;	
}

void print(const std::vector<std::vector<int>>& v,int row,int col)
{
	std::cout<<"The 2D Vector is :\n";
	for(int i=0;i<row;i++){
		
		for(int j=0;j<col;j++)
		{
			std::cout<<v[i][j]<<" ";
		}
		std::cout<<std::endl;
	}
}
int main()
{
	int row=3;int col=3;
	std::vector<std::vector<int>>vec{{1,2,3},
						   {4,5,6},
						   {7,8,9}
						  };
		
std::cout<<"The Normal 2D Vector \n";
print(vec,row,col);
std::cout<<"The Transposed 2D Vector:\n";
print(transpose2D(vec,row,col),row,col);

return 0;
}
