#include <iostream>

class stack
{
    private:
        int size;
        int* stk;
        int TOS;
    public:
        stack()
        {
            size = 10;
            stk = new int[size];
            TOS = -1;
        }
        stack(int size)
        {
            this->size = size;
            stk = new int[size];
            TOS = -1;
        }
        int pop()
        {
            if (TOS >= 0)
            {
                return stk[TOS--];
            }
            else
            {
                std::cout << "Stack is Empty\n";
                return -1; // Sentinel value for empty stack
            }
        } 
        void push(int item)
        {
            if (TOS < size - 1)
            {
                stk[++TOS] = item;
            }
            else
            {
                std::cout << "Stack is Full\n";
            }
        }
        
        ~stack()
        {
            delete[] stk;
            stk = nullptr;
        }
};

int main()
{
    stack s1; // Default size stack
    stack s2(5); // Custom size stack

    // Pushing elements to stack s1
    for (int i = 1; i <= 12; ++i) {
        std::cout << "Pushing " << i << " to s1.\n";
        s1.push(i);
    }

    // Popping elements from stack s1
    for (int i = 0; i < 5; ++i) {
        int value = s1.pop();
        if (value != -1) {
            std::cout << "Popped " << value << " from s1.\n";
        }
    }

    // Pushing elements to stack s2
    for (int i = 1; i <= 7; ++i) {
        std::cout << "Pushing " << i << " to s2.\n";
        s2.push(i);
    }

    // Popping elements from stack s2
    for (int i = 0; i < 6; ++i) {
        int value = s2.pop();
        if (value != -1) {
            std::cout << "Popped " << value << " from s2.\n";
        }
    }

    return 0;
}

