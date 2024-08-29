/*
 * Complete the 'lonelyinteger' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY a as parameter.
 */

int lonelyinteger(vector<int> a) {
int unique =a[0];
for(int i=1;i<a.size();i++)
{
   unique^=a[i]; 
}
return unique;

}

