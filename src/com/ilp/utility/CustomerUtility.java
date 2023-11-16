package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Services;
import com.ilp.service.CustomerAccountConfiguration;
import com.ilp.service.ProductConfiguration;
//import com.ilp.service.ServicesService;

public class CustomerUtility {

	public static void main(String[] args) {
		ArrayList<Product> productList = new ArrayList<Product>();
		Customer customer = null;
		ArrayList<Services> serviceList = new ArrayList<Services>();
		Scanner scanner = new Scanner(System.in);

		char goToMainMenu;
		do {
			System.out.println("1. Create Account 2.Display Accounts 3.Transaction Bills 4.Create service 5.Create Product 6.Manage Accounts 7.Exit");
			int mainMenuChoice = scanner.nextInt();
			switch (mainMenuChoice) {
			case 1:
				customer = CustomerAccountConfiguration.createCustomer(productList);
				break;
			case 2:
				CustomerAccountConfiguration.displayCustomer(customer);
				break;
			case 3:
				CustomerAccountConfiguration.transactCustomer(customer);
				break;
			case 4:
				serviceList = ProductConfiguration.createServices();
				break;
			case 5:
				productList = ProductConfiguration.createProduct(serviceList);
				break;
			case 6:
				CustomerAccountConfiguration.manageAccounts(customer);
				break;
			case 7:
				System.exit(0);
				break;

			}
			System.out.println("Go back to main menu (y/n)");
			goToMainMenu = scanner.next().charAt(0);
		} while (goToMainMenu == 'y');

	}

}
