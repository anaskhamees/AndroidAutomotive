#include <iostream>
#include <cstring>
using namespace std;

int main()
{
    char** ptr = NULL;
    int strNum = 0;

    cout << "Enter the Number of Strings: ";
    cin >> strNum;
    cout << endl;

    ptr = new char*[strNum];
    for(int i = 0; i < strNum; i++)
    {
        ptr[i] = new char[15]; /* Allocate memory for each string */
        cout << "Enter String " << i + 1 << ": ";
        cin >> ws; /* To consume any leading whitespace */
        cin.getline(ptr[i], 15); /* Use getline to read strings with spaces */
    }

    cout <<"############################################"<<endl<<"The Dynamic 2D Array" <<      endl<<"############################################"<<endl;
    for(int i = 0; i < strNum; i++)
    {
        cout << ptr[i] << endl;
    }

    /* Free allocated memory */
    for(int i = 0; i < strNum; i++)
    {
        delete[] ptr[i]; /* Deallocate memory for each string (array of char) */
    }
    delete[] ptr; /* Deallocate memory for the array of pointers */

    return 0;
}

