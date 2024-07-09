#include <iostream>
#include <typeinfo>

enum type
{
    INT,
    Double,
    Char
};

class VPointerArray
{
    int size;
    void** arr;
public:
    VPointerArray(int x)
    {
        size = x;
        arr = new void*[size];
    }

    ~VPointerArray()
    {
        delete[] arr;
    }

    void setVal(void* data, int type, int index)
    {
        switch (type)
        {
            case INT:
                arr[index] = static_cast<int*>(data);
                break;
            case Double:
                arr[index] = static_cast<double*>(data);
                break;
            case Char:
                arr[index] = static_cast<char*>(data);
                break;
        }
    }

    void* getVal(int index)
    {
        return arr[index];
    }

    int getArrSize()
    {
        return size;
    }

    const char* getDataType(int index)
    {
        return typeid(*arr[index]).name();
    }

    void print()
    {
        for (int i = 0; i < size; i++)
        {
            switch (getDataType(i)[0]) {
                case 'i':
                    std::cout << *static_cast<int*>(arr[i]) << std::endl;
                    break;
                case 'd':
                    std::cout << *static_cast<double*>(arr[i]) << std::endl;
                    break;
                case 'c':
                    std::cout << *static_cast<char*>(arr[i]) << std::endl;
                    break;
                default:
                    std::cout << "Unknown type" << std::endl;
                    break;
            }
        }
    }
};

int main()
{
    VPointerArray vp(3);
    int x1 = 1;
    double x2 = 2.5;
    char x3 = 'a';

    vp.setVal(&x1, INT, 0);
    vp.setVal(&x2, Double, 1);
    vp.setVal(&x3, Char, 2);
    vp.print();

    return 0;
}

