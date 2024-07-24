#include <iostream>
#include <string>

// Structures as previously defined
typedef struct {
    std::string firstName;
    std::string secondName;
    std::string lastName;
} Names;

typedef struct {
    unsigned int year;
    unsigned char month;
    unsigned char day;
} DateOfBirth;

typedef struct {
    std::string street;
    std::string city;
    std::string country;
} Address;

typedef struct {
    std::string phoneNum;
    std::string email;
} Contacts;

typedef struct {
    double basic;
    double additional;
    double reduction;
    double tax;
} Salary;

// Structure for employee data
typedef struct {
    Names name;
    DateOfBirth dob;
    Address addr;
    Contacts contact;
    Salary sal;
} Employee;

int main() {
    // Initialize an employee
    Employee emp = {
        {"Anas", "Kh", "hussien"},
        {1990, 5, 21},
        {"123 Main St", "Alex", "Egypt"},
        {"01500000", "anas@example.com"},
        {50000.00, 10000.00, 5000.00, 0.20}
    };

    // Print employee data
    std::cout << "Employee Details:\n";
    std::cout << "Name: " << emp.name.firstName << " " << emp.name.secondName << " " << emp.name.lastName << "\n";
    std::cout << "Date of Birth: " << (int)emp.dob.day << "-" << (int)emp.dob.month << "-" << emp.dob.year << "\n";
    std::cout << "Address: " << emp.addr.street << ", " << emp.addr.city << ", " << emp.addr.country << "\n";
    std::cout << "Contact: Phone: " << emp.contact.phoneNum << ", Email: " << emp.contact.email << "\n";
    std::cout << "Salary: Basic: $" << emp.sal.basic << ", Additional: $" << emp.sal.additional 
              << ", Reduction: $" << emp.sal.reduction << ", Tax rate: " << emp.sal.tax * 100 << "%\n";

    // Calculate net salary
    double grossSalary = emp.sal.basic + emp.sal.additional;
    double totalDeductions = emp.sal.reduction + (grossSalary * emp.sal.tax);
    double netSalary = grossSalary - totalDeductions;

    std::cout << "Net Salary: $" << netSalary << "\n";

    return 0;
}

