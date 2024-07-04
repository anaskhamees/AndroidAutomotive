#include <iostream>
using namespace std;
struct vector
{
	/*Pointer to the dynamically allocated array*/
	int* data;
	/*Number of elements in the array*/
	int size;
	/*Maximum number of elements that can be held in the current allocation*/
	int capacity;
};

void vectorInit(vector& vec,int size,int capacity)
{

vec.size=size;
vec.capacity=capacity;
vec.data=new int[capacity];

if(!vec.data)
{
	cout<<"Memory Allocation Failed"<<endl;
	exit(1);
}

for(int i=0;i<size;i++)
{
	cout<<"Item "<<i<<" : ";	
	cin>>vec.data[i];
	cout<<endl;
}
}

void vectorInsert(vector& vec,int value,int index)
{

if(index<0 ||index>vec.size)
{
	cout<<"The index is out of rang !";
	return;
}
if(vec.size>=vec.capacity)
{
	cout<<"Increasing Capacity form "<<vec.capacity<<" to "<<vec.capacity*2<<endl;
	vec.capacity*=2;
	int* newData=new int[vec.capacity];
	if(!newData)
	{
		cout<<"Memory Location failed !"<<endl;
		return;
	}
	for(int i=0;i<vec.size;i++)
	{
		newData[i]=vec.data[i];
	}
	
	delete[] vec.data;
	vec.data=newData;
}

for(int i=vec.size;i>index;i--)
{
	vec.data[i]=vec.data[i-1];
}

vec.data[index]=value;
vec.size++;
}

void vectorRemove(vector& vec,int index)
{
	if(index<0 ||index>vec.size)
	{
	cout<<"The index is out of rang !"<<endl;
	}
	for(int i=index;i<vec.size-1;i++)
	{
		vec.data[i]=vec.data[i+1];
	}
	vec.size--;

}

void vectorPrint(vector& vec)
{ 
	cout<<"######################################"<<endl;
	cout<<"The vector Elements: ";
	for(int i=0;i<vec.size;i++)
	{
		cout<<vec.data[i]<<" ";
	}
	
	cout<<endl<<"######################################"<<endl;
}
int main()
{

int size =0;
int index=0;
int value=0;
vector vec;
cout<<"Enter the Vector Size: ";
cin>>size;
int capacity=size;
cout<<endl;

vectorInit(vec,size,capacity);	
vectorPrint(vec);

cout<<"Enter the new Value to be inserted: ";
cin>>value;
cout<<endl;
cout<<" Enter the Index : ";
cin>>index;
cout<<endl;

vectorInsert(vec,value,index);
vectorPrint(vec);
cout<<"Enter the Value Index to be deleted : ";
cin>>index;
cout<<endl;

vectorRemove(vec,index);
vectorPrint(vec);
}

