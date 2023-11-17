# OOAD-UseCase
## Description
A Use Case about a bank was given. <br />
Use case : When a customer attempts to deposit money, the list of all accounts which the customers possess should be listed so that the customer can select to which account he would like to deposit the money.[Hint: Customer has Accounts] . Customer can have the following accounts: <br />
**SavingsMaxAccount**<br />
**CurrentAccount**<br />
**LoanAccount**<br />
* Customer(customerCode,customerName,List<Account>)
* Account(accountNo,accountType,balance,Product).
* Product(productCode,productName,List<Service>)
* SavingsMaxAccount is a Product(minimumBalance of Rs1000 should be maintained in the account)
* CurrentAccount is a Product
* LoanAccount is a Product.(chequeDeposit should be chargeable ie).3%).
* Service(serviceCode,serviceName,rate) <br />
<br />He should be given default services like: <br />
* **SavingsMaxAccount(CashDeposit. ATMWithdrawl,OnlineBanking)**
* **CurrentAccount(CashDeposit,OnlineBanking,ATMWithdrawl,MobileBanking)**
* **LoanAccount(CashDeposit,ChequeDeposit)**
## Features
1. Create Account
2. Display Account
3. Transaction Bills
4. Create Services
5. Create Products
6. Manage Accounts
7. Exit
## How to run as a Java program
1. Run the CustomerUtility.java file as a Java Application.
2. When a window opens, press 4 to create services.
3. After creating services, press 'y' to go back to main menu.
4. Then press 5 to create Products, which are predefined and then select products and assign services to them.
5. After creation of Products, user can create multiple accounts based on the products.
6. After account creation, customer code and name can be entered.
7. Then User can display the accounts, check the transaction bill, or manage available accounts.
## How to clone
Use the following command to clone this repository to your local machine:
```bash
git clone https://github.com/AadarshR05/OOAD-UseCase.git
```
# UML Diagram
![UseCase_ClassDiagram](https://github.com/AadarshR05/OOAD-UseCase/assets/92199351/75223c60-7474-445b-a16b-c952acb16c0b)
