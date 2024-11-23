## Bank Account Implementation

#### Public Members

- **Static Functions**:
  - `getNoOfAccounts()`: Returns the total number of accounts created.
  - `getTotalAmount()`: Returns the total amount of money across all accounts.
  - `getNbDeposits()`: Returns the total number of deposits made across all accounts.
  - `getNoOfWithdrawals()`: Returns the total number of withdrawals made across all accounts.
  - `displayAccountsInfos()`: Displays the number of accounts, total amount, total deposits, and total withdrawals.
- **Constructor and Destructor**:
  - `Account(int initial_deposit)`: Initializes a new account with an initial deposit.
  - `~Account()`: Destructor to handle any necessary cleanup.
- **Member Functions**:
  - `makeDeposit(int deposit)`: Adds a deposit to the account.
  - `makeWithdrawal(int withdrawal)`: Attempts to withdraw a specified amount from the account, returns `true` if successful, `false` otherwise.
  - `checkAmount() const`: Returns the current balance of the account.
  - `displayStatus() const`: Displays the current status of the account.

#### Private Members

- **Static Data Members**:
  - `m_noOfAccounts`: Keeps track of the number of accounts.
  - `m_totalAmount`: Tracks the total amount of money across all accounts.
  - `m_totalNbDeposits`: Tracks the total number of deposits across all accounts.
  - `m_totalNbWithdrawals`: Tracks the total number of withdrawals across all accounts.
- **Non-static Data Members**:
  - `m_accountIndex`: Unique identifier for the account.
  - `m_amount`: Current balance of the account.
  - `m_noOfDeposits`: Number of deposits made to this account.
  - `m_noOfWithdrawals`: Number of withdrawals made from this account.
- **Private Member Function**:
  - `m_displayTimestamp()`: Static function to display the current timestamp.
