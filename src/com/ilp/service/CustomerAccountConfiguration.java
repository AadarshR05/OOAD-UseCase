package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Services;

public class CustomerAccountConfiguration {

	public static Customer createCustomer(ArrayList<Product> productList) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		ArrayList<Account> accountList = new ArrayList<Account>();

		char choice;
		do {

			accountList.add(createAccount(productList));

			System.out.println("Do you want to create another account? (y/n)");
			choice = scanner.next().charAt(0);
		} while (choice == 'y');
		System.out.println("Enter Customer Code");
		String customerCode = scanner.next();
		System.out.println("Enter Customer Name");
		String customerName = scanner.next();
		int k = 0;
		int i = 0;
		for (Account account : accountList) {
			System.out.println("A " + account.getProduct().getProductName() + " for " + customerName
					+ " Was created with following services");
			k = 0;
			for (Services service : accountList.get(i).getProduct().getServiceList()) {

				System.out.println(service.getServiceName());
				k++;
			}
			i++;
		}
		Customer customer = new Customer(customerCode, customerName, accountList);

		return customer;
	}

	public static void displayCustomer(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println("*********Customer Acct Details*********************************************");
		System.out.println("Customer ID:            CustomerName:           AccountType:       Balance:");
		System.out.println("***************************************************************************");
		int currentPosition = 0;
		for (Account account : customer.getAccountList()) {
			System.out.printf(customer.getCustomerCode() + "                      " + customer.getCustomerName() + "                  "
					+ customer.getAccountList().get(currentPosition).getProduct().getProductName() + "    "
					+ customer.getAccountList().get(currentPosition).getAccountBalance());
			currentPosition++;
		}

	}

	public static Account createAccount(ArrayList<Product> productList) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Account Number:");
		String accountNumber = scanner.next();
		int i = 1;
		System.out.println("Choose the Product to be added");
		for (Product products : productList) {
			System.out.println(i + ". " + products.getProductName());
			i++;
		}
		int productChoice = scanner.nextInt();
		Product product = productList.get(productChoice - 1);
		double accountBalance = 0;
		SavingsMaxAccount savingsAccount = null;
		if (product instanceof SavingsMaxAccount) {
			System.out.println("Enter Account Opening Balance: (Min Balance should be above 1000)");
			savingsAccount = (SavingsMaxAccount) product;
			accountBalance = scanner.nextDouble();
			while (accountBalance < savingsAccount.getMinBalance()) {
				System.out.println(
						"Entered Balance is less than 1000. Account cannot be created. Please enter balance above 1000");
				accountBalance = scanner.nextDouble();
			}
		} else {
			System.out.println("Enter Account Opening Balance:");
			accountBalance = scanner.nextDouble();
		}
		System.out.println("Account is Active!!!");

		return new Account(accountNumber, accountBalance, product);
	}

	public static void transactCustomer(Customer customer) {
		Scanner scanner = new Scanner(System.in);
		char goBackToTransactMenu;
		do {
			int i = 0;
			for (Account account : customer.getAccountList()) {
				System.out.println((i + 1) + "." + account.getProduct().getProductName());
				i++;
			}
			System.out.println("Enter Your Choice: ");
			int accountChoice = scanner.nextInt();
			i = 0;
			for (Services service : customer.getAccountList().get(accountChoice - 1).getProduct().getServiceList()) {
				System.out.println((i + 1) + "." + service.getServiceName() + "   " + service.getServiceRate());
				i++;
			}
			int serviceChoice = scanner.nextInt();
			System.out.println("Enter Number of Transaction: ");
			double transactionNo = scanner.nextInt();
			double total = transactionNo * customer.getAccountList().get(accountChoice - 1).getProduct()
					.getServiceList().get(serviceChoice - 1).getServiceRate();
			System.out.println("Total Transaction Bill Amount: ₹" + total);
			System.out.println("Do you want to do another transaction? (y/n)");
			goBackToTransactMenu = scanner.next().charAt(0);
		} while (goBackToTransactMenu == 'y');

	}

	public static void manageAccounts(Customer customer) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Customer Code: ");
		String customerCode = scanner.next();
		if (customerCode.compareToIgnoreCase(customer.getCustomerCode()) == 0) {
			char goBack;
			char goBackMain;
			do {
				System.out.println(customer.getCustomerName() + " has the following Accounts");
				int i = 0;
				for (Account account : customer.getAccountList()) {
					System.out.println((i + 1) + "." + account.getProduct().getProductName());
					i++;
				}
				System.out.println("Choose Account to Manage: ");
				int accountChoice = scanner.nextInt();
				if (!(customer.getAccountList().get(accountChoice - 1).getProduct() instanceof LoanAccount)) {
					do {
						i = 1;
						for (Services service : customer.getAccountList().get(accountChoice - 1).getProduct()
								.getServiceList()) {
							System.out.println(i + "." + service.getServiceName());
							i++;
						}
						System.out.println("Enter Your Choice: (Please type in the chosen service)");
						String manageAccountChoice = scanner.next();
						if (manageAccountChoice.compareToIgnoreCase("cashdeposit") == 0
								|| manageAccountChoice.compareToIgnoreCase("chequedeposit") == 0) {
							depositMoney(customer.getAccountList().get(accountChoice - 1));
						} else if (manageAccountChoice.compareToIgnoreCase("atmwithdrawal") == 0) {
							withdrawMoney(customer.getAccountList().get(accountChoice - 1));
						} else if (manageAccountChoice.compareToIgnoreCase("onlinebanking") == 0
								|| manageAccountChoice.compareToIgnoreCase("mobilebanking") == 0) {
							displayCustomer(customer);
						}
						System.out.println("Do you want to go back to function choosing menu?");
						goBack = scanner.next().charAt(0);
					} while (goBack == 'y');
				} else if ((customer.getAccountList().get(accountChoice - 1).getProduct() instanceof LoanAccount)) {

					do {
						i = 1;
						System.out.println("1.Deposit");
						System.out.println("2.Balance");
						System.out.println("Enter Your Choice:(Please type in the chosen service)");
						String manageAccountChoice = scanner.next();
						if (manageAccountChoice.compareToIgnoreCase("deposit") == 0) {
							depositMoney(customer.getAccountList().get(accountChoice - 1));
						} else if (manageAccountChoice.compareToIgnoreCase("balance") == 0) {
							displayCustomer(customer);
						}
						System.out.println("Do you want to go back to function choosing menu?");
						goBack = scanner.next().charAt(0);
					} while (goBack == 'y');
				}

				System.out.println("Do you want to go back to Account selection menu?");
				goBackMain = scanner.next().charAt(0);
			} while (goBackMain == 'y');
		} else {
			System.out.println("Customer ID not available. Create New Account");
		}

	}

	public static void depositMoney(Account account) {
		Scanner scanner = new Scanner(System.in);
		LoanAccount loanAccount = null;
		if (!(account.getProduct() instanceof LoanAccount)) {
			System.out.println("Enter Amount to be deposited: ");
			double depositAmt = scanner.nextDouble();
			account.setAccountBalance(depositAmt + account.getAccountBalance());
		} else {
//			LoanAccount loanAcct = null;
			loanAccount = (LoanAccount) account.getProduct();
			System.out.println("Choose type of deposit: ");
			int i = 0;
			for (Services services : loanAccount.getServiceList()) {
				System.out.println((i + 1) + "." + services.getServiceName());
				i++;
			}

			String depositChoice = scanner.next();

			if (depositChoice.compareToIgnoreCase("cashdeposit") == 0) {
				System.out.println("Enter Amount to be deposited: ");
				double depositAmt = scanner.nextDouble();
				account.setAccountBalance(depositAmt + account.getAccountBalance());
			} else if (depositChoice.compareToIgnoreCase("chequedeposit") == 0) {
				System.out.println("Cheque Deposit is chargeable by 0.3%: ");

				System.out.println("Enter Amount to be deposited:");
				double chequeAmt = scanner.nextDouble();
				double chargeableAmt = (loanAccount.getChequeDeposit() / 100) * chequeAmt;
				double total = (chequeAmt + account.getAccountBalance()) - chargeableAmt;
				account.setAccountBalance(total + account.getAccountBalance());
			}

		}

	}

	public static void withdrawMoney(Account account) {
		Scanner scanner = new Scanner(System.in);
		if (!(account.getProduct() instanceof SavingsMaxAccount)) {
			System.out.println("Enter Amount to be withdrawn: ");
			double withdrawAmt = scanner.nextDouble();
			if (withdrawAmt < account.getAccountBalance()) {
				account.setAccountBalance(account.getAccountBalance() - withdrawAmt);
			} else {
				System.out.println("Insufficient Funds");
			}
		} else {
			SavingsMaxAccount savingsMaxAccount = null;
			savingsMaxAccount = (SavingsMaxAccount) account.getProduct();
			System.out.println("Enter Amount to be withdrawn: ");
			double withdrawAmt = scanner.nextDouble();
			if (withdrawAmt < (account.getAccountBalance() - 1000)) {
				account.setAccountBalance(account.getAccountBalance() - withdrawAmt);
			} else {
				System.out.println("₹1000 should be kept as minimum");
			}

		}
	}

}
