#include <iostream>
#include <unordered_map>
#include <string>

using namespace std;

int main() {
    unsigned queriesNum = 0;
    unordered_map<string, int> studentMarks;
    cin >> queriesNum;

    while (queriesNum--) {
        int queryType;
        cin >> queryType;

        string name;
        switch (queryType) {
            case 1: {
                int marks;
                cin >> name >> marks;
                studentMarks[name] += marks;  
            } break;

            case 2: {
                cin >> name;
                studentMarks.erase(name);  
            } break;

            case 3: {
                cin >> name;
                cout << studentMarks[name] << endl;  
            } break;
        }
    }

    return 0;
}

