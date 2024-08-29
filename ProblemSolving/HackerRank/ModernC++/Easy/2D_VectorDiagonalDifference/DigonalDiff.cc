int diagonalDifference(vector<vector<int>> arr) {
int leftDiag=0;
int rightDiag=0;
int rows=arr.size();
int cols=arr.at(0).size();
int result =0;
for(int i=0;i<rows;i++)
{
    for(int j=0;j<cols;j++)
    {
        if(i==j)
        {
            leftDiag+=arr[i][j];
        }
        if(i==(rows-j-1))
        {
            rightDiag+=arr[i][j];
        }
    }
    
}

result=leftDiag-rightDiag;
if(result<0)
{
    result*=-1;
}
return result;

}

