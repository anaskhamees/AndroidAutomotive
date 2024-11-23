#include <iostream>
#include <vector>

/* Function prints the vector using normal iterator */
void printUsingIterator(std::vector<int>& v)
{
	std::cout << "########################################################\n";
    std::cout << "#         Print Vector using Normal Iterator           #\n";
	std::cout << "########################################################\n";
    int i = 0;
    for (std::vector<int>::iterator it = v.begin(); it != v.end(); ++it)  // Use ++it instead of it++
    {
        /* print the address of the element and the value */
        std::cout << "Address of element " << i << " : " << static_cast<void*>(&(*it))  // Print address
                  << " -- Element " << i << " : " << *it << " #\n";  // Print value
        i++;
    }
}

/* Function prints the vector using reverse begin rbegin()*/
void printUsingReverseIterator(std::vector<int>&v)
{
	int i=0;
	std::cout << "########################################################\n";
    std::cout << "#         Print Vector using Reverse Iterator          #\n";
	std::cout << "########################################################\n";
    for(auto Reverse_it=v.rbegin();Reverse_it !=v.rend();Reverse_it++)
    {
    	std::cout<<"Address of element " << i << " : " <<static_cast<void*>(&(*Reverse_it))<< " -- Element " << i << " : " << *Reverse_it<<" #\n";
    	i++;
    }
}

/* Function prints the vector using constant begin cbegin()*/
void printUsingConstIterator(std::vector<int>&v)
{         
	int i=0;
	std::cout << "########################################################\n";
    std::cout << "#        Print Vector using Constant Iterator          #\n";
	std::cout << "########################################################\n";
    for(auto Reverse_it=v.cbegin();Reverse_it !=v.cend();Reverse_it++)
    {
    	std::cout<<"Address of element " << i << " : " <<static_cast<const int*>(&(*Reverse_it))<< " -- Element " << i << " : " << *Reverse_it<<" #\n";
    	i++;
    }
}

/* Function prints the vector using constant Reverse Iterator*/
void printUsingConstReverseIterator(std::vector<int>&v)
{         
	int i=0;
	std::cout << "########################################################\n";
    std::cout << "#     Print Vector using Constant Reverse Iterator     #\n";
	std::cout << "########################################################\n";
    for(auto ConstReverse_it=v.crbegin();ConstReverse_it !=v.crend();ConstReverse_it++)
    {
    	std::cout<<"Address of element " << i << " : " <<static_cast<const int*>(&(*ConstReverse_it))<< " -- Element " << i << " : " << *ConstReverse_it<<" #\n";
    	i++;
    }
}

int main()
{
    std::vector<int> vec = {1, 2, 3, 4, 5};
   
    printUsingIterator(vec);
    printUsingReverseIterator(vec);
    printUsingConstIterator(vec);
	printUsingConstReverseIterator(vec);
    return 0;
}

