#include<iostream>

class complex
{
    private:
        int real;
        int img;
    public:
        complex()
        {
            real = 0;
            img = 0;
        }
        complex(int num)
        {
            real = img = num;
        }
        complex(int r, int i)
        {
            real = r;
            img = i;
        }
        
        void setReal(int real)
        {
            this->real = real;
        }
        void setImg(int img)
        {
            this->img = img;
        }
        void setComplex(int real, int img)
        {
            this->real = real;
            this->img = img;
        }
        
        int getReal() const
        {
            return real;
        } 
        int getImg() const
        {
            return img;
        }
        
        void printComplex()
        {
            std::cout << "Complex number: " << real << " + " << img << "i" << std::endl;
        }
};

int main()
{
    complex c1(3, 4);
    c1.printComplex();

    complex c2(5);
    c2.printComplex();

    complex c3;
    c3.setComplex(7, 8);
    c3.printComplex();

    return 0;
}

