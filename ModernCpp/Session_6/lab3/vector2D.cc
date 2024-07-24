#include<iostream>
#include<vector>


std::vector<std::vector<int>> transpose2DMatrix(std::vector<std::vector<int>> v,int row,int col)
{
	std::vector<std::vector<int>> vec(col,std::vector<int>(row));

	for(int i=0;i<row;i++){
		
		for(int j=0;j<col;j++)
		{
			vec[j][i]=v[i][j];
		}
	}
	
	return vec;	
}

void print(const std::vector<std::vector<int>>& v,int row,int col)
{
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
	int row=0;int col=0;
	std::cout<<"Enter the Rows Number    : ";
	std::cin>>row;
	std::cout<<"\n";
	std::cout<<"Enter the Colomns Number : ";
	std::cin>>col;
	std::cout<<"\n";
	
	std::vector<std::vector<int>>vec(row,std::vector<int>(col));
	std::cout<<"Enter the 2D Vector Elements \n\n";
	for(int rows=0;rows<row;rows++)
	{
		for(int cols=0;cols<col;cols++)
		{
			std::cout<<"Element ["<<rows<<"]["<<cols<<"] : ";
			std::cin>>vec[rows][cols];
			std::cout<<"\n";
		}
	}
	
std::cout<<"###########################################################\n";	
std::cout<<"The Normal 2D Vector \n";
std::cout<<"###########################################################\n";	
print(vec,row,col);
std::cout<<"###########################################################\n";	
std::cout<<"The Transposed 2D Vector:\n";
std::cout<<"###########################################################\n";	
print(transpose2DMatrix(vec,row,col),col,row);
std::cout<<"###########################################################\n";	
return 0;
}
