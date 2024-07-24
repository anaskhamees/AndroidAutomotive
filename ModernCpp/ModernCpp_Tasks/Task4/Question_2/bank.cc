#include <iostream>
#include "bank.hpp"
#include <ctime>
#include <iomanip> // Include this for std::put_time

int Account::m_noOfAccounts = 0;
int Account::m_totalAmount = 0;
int Account::m_totalNbDeposits = 0;
int Account::m_totalNbWithdrawals = 0;

Account::Account(int initial_deposit) : m_amount(initial_deposit), m_noOfDeposits(0), m_noOfWithdrawals(0) {
    m_accountIndex = ++m_noOfAccounts;
    m_totalAmount += initial_deposit;
}

Account::~Account() {
    // Destructor implementation (even if empty)
}

int Account::getNoOfAccounts(void) {
    return m_noOfAccounts;
}

int Account::getTotalAmount(void) {
    return m_totalAmount;
}

int Account::getNbDeposits(void) {
    return m_totalNbDeposits;
}

int Account::getNoOfWithdrawals(void) {
    return m_totalNbWithdrawals; // Fix the syntax error
}

void Account::m_displayTimestamp() {
    std::time_t now = std::time(nullptr);
    std::cout << std::put_time(std::localtime(&now), "[%Y-%m-%d %H:%M:%S] ") << std::endl;
}

void Account::displayAccountsInfos(void) {
    std::cout << " Date and Time : ";
    Account::m_displayTimestamp();
    std::cout << "# Number of Accounts: " << m_noOfAccounts << "\n";
    std::cout << "# Total amount      : " << m_totalAmount << "\n";
    std::cout << "# Total Deposits    : " << m_totalNbDeposits << "\n";
    std::cout << "# Total Withdrawals : " << m_totalNbWithdrawals << "\n";
}

void Account::makeDeposit(int deposit) {
    m_amount += deposit;
    m_totalAmount += deposit;
    m_noOfDeposits++;
    m_totalNbDeposits++;
}

bool Account::makeWithdrawal(int withdrawal) {
    if (withdrawal <= m_amount) {
        m_amount = m_amount - withdrawal;
        m_totalAmount = m_totalAmount - withdrawal;
        m_noOfWithdrawals++;
        m_totalNbWithdrawals++;
        return true;
    } else {
        return false;
    }
}

int Account::checkAmount(void) const {
    return m_amount;
}

void Account::displayStatus(void) const {
    std::cout << " Account Index  : " << m_accountIndex << "\n";
    std::cout << " Account #" << m_accountIndex << "  Balance: " << m_amount << "\n";
    std::cout << " Account #" << m_accountIndex << " Number of deposits :" << m_noOfDeposits << "\n";
    std::cout << " Account #" << m_accountIndex << " Number of withdrawals :" << m_noOfWithdrawals << "\n";
}

int main() {
    Account acc1(100);
    acc1.displayStatus();

    Account acc2(200);
    acc2.displayStatus();

    Account acc3(300);
    acc3.displayStatus();

    Account::displayAccountsInfos();
}

