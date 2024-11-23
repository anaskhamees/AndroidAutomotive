#ifndef _ACCOUNT_H_
#define _ACCOUNT_H_

/* ************************************************************************** */
/*
----- Account Class -----
*/
/* ************************************************************************** */

class Account
{
public:
    using acc = Account;

    // Static member functions
    static int getNoOfAccounts(void);          // Returns the number of accounts
    static int getTotalAmount(void);           // Returns the total amount of all accounts
    static int getNbDeposits(void);            // Returns the number of deposits
    static int getNoOfWithdrawals(void);       // Returns the number of withdrawals
    static void displayAccountsInfos(void);    // Displays account statistics

    // Constructor and destructor
    Account(int initial_deposit);
    ~Account(void);

    // Member functions
    void makeDeposit(int deposit);
    bool makeWithdrawal(int withdrawal);
    int checkAmount(void) const;
    void displayStatus(void) const;

private:
    // Static member variables
    static int m_noOfAccounts;
    static int m_totalAmount;
    static int m_totalNbDeposits;
    static int m_totalNbWithdrawals;

    // Static member function
    static void m_displayTimestamp(void); // Displays the current timestamp

    // Member variables
    int m_accountIndex;
    int m_amount;
    int m_noOfDeposits;
    int m_noOfWithdrawals;

    // Private constructor
    Account(void);
};


#endif /* _ACCOUNT_H_ */

