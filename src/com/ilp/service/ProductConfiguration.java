package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.CurrentAccount;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Services;

public class ProductConfiguration {

	public static ArrayList<Product> createProduct(ArrayList<Services> serviceList) {
		Scanner scanner = new Scanner(System.in);
		char goBackToMenu;
		ArrayList<Product> productList = new ArrayList<Product>();
		do {
			System.out.println("Products Available!");
			System.out.println("1.Savings Max Account");
			System.out.println("2.Current Account");
			System.out.println("3.Loan Account");
			System.out.println("Enter you choice:");
			
			int choiceProduct = scanner.nextInt();
			if(choiceProduct == 1) {
				productList.add(createSavingsMaxAccount(serviceList));
			}
			else if(choiceProduct == 2) {
				productList.add(createCurrentAccount(serviceList));
			}
			else if(choiceProduct == 3) {
				productList.add(createLoanAccount(serviceList));
			}
			System.out.println("Do you want to go back and add more Products?");
			goBackToMenu = scanner.next().charAt(0);
			
		}while(goBackToMenu=='y');
		return productList;
	}
	public static SavingsMaxAccount createSavingsMaxAccount(ArrayList<Services> serviceList) {
		Scanner scanner = new Scanner(System.in);
		int serviceChoice;
		char addMoreService;
		ArrayList<Services> addedServicesList = new ArrayList<Services>();
		do {
			int index = 1;
			System.out.println("Choose the Services to be added");
			for (Services services : serviceList) {
				System.out.println(index + ". " + services.getServiceName());
				index++;
			}
			serviceChoice = scanner.nextInt();
			if(serviceList.get(serviceChoice - 1).getServiceName().compareToIgnoreCase("cashdeposit")==0||serviceList.get(serviceChoice - 1).getServiceName().compareToIgnoreCase("atmwithdrawal")==0||serviceList.get(serviceChoice - 1).getServiceName().compareToIgnoreCase("onlinebanking")==0)
				addedServicesList.add(serviceList.get(serviceChoice - 1));
			else
				System.out.println("This account cannot have this service");
			System.out.println("Do you want to choose more services?(y/n) : -");
			
			addMoreService = scanner.next().charAt(0);
		} while (addMoreService == 'y');
		return new SavingsMaxAccount("P101","SavingsMaxAccount",addedServicesList,1000);
	}
	public static CurrentAccount createCurrentAccount(ArrayList<Services> serviceList) {
		Scanner scanner = new Scanner(System.in);
		int serviceChoice;
		char addMoreService;
		ArrayList<Services> addedServicesList = new ArrayList<Services>();
		do {
			int index = 1;
			System.out.println("Choose the Services to be added");
			for (Services services : serviceList) {
				System.out.println(index + ". " + services.getServiceName());
				index++;
			}
			serviceChoice = scanner.nextInt();
			if(serviceList.get(serviceChoice - 1).getServiceName().compareToIgnoreCase("cashdeposit")==0||serviceList.get(serviceChoice - 1).getServiceName().compareToIgnoreCase("atmwithdrawal")==0||serviceList.get(serviceChoice - 1).getServiceName().compareToIgnoreCase("onlinebanking")==0||serviceList.get(serviceChoice - 1).getServiceName().compareToIgnoreCase("mobilebanking")==0)
				addedServicesList.add(serviceList.get(serviceChoice - 1));
			else
				System.out.println("This account cannot have this service");
			System.out.println("Do you want to choose more services?(y/n) : -");
			addMoreService = scanner.next().charAt(0);
		} while (addMoreService == 'y');
		return new CurrentAccount("P102","CurrentAccount",addedServicesList);
	}
	public static LoanAccount createLoanAccount(ArrayList<Services> serviceList) {
		Scanner scanner = new Scanner(System.in);
		int serviceChoice;
		char addMoreService;
		ArrayList<Services> addedServicesList = new ArrayList<Services>();
		do {
			int index = 1;
			System.out.println("Choose the Services to be added");
			for (Services services : serviceList) {
				System.out.println(index + ". " + services.getServiceName());
				index++;
			}
			serviceChoice = scanner.nextInt();
			if(serviceList.get(serviceChoice - 1).getServiceName().compareToIgnoreCase("cashdeposit")==0||serviceList.get(serviceChoice - 1).getServiceName().compareToIgnoreCase("chequedeposit")==0)
				addedServicesList.add(serviceList.get(serviceChoice - 1));
			else
				System.out.println("This account cannot have this service");
			System.out.println("Do you want to choose more services?(y/n) : -");
			addMoreService = scanner.next().charAt(0);
		} while (addMoreService == 'y');
		return new LoanAccount("P103","LoanAccount",addedServicesList,0.3);
	}

	public static ArrayList<Services> createServices() {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Services> servicesList = new ArrayList<Services>();

		char choice;
		do {
			System.out.println("Enter Service Code: ");
			String serviceCode = scanner.next();
			System.out.println("Enter Service Name: ");
			String serviceName = scanner.next();
			System.out.println("Enter Rate of Service: ");
			double serviceRate = scanner.nextDouble();
			Services service = new Services(serviceCode, serviceName, serviceRate);
			servicesList.add(service);
			System.out.println("Do you want to add another service? (y/n)");
			choice = scanner.next().charAt(0);
		} while (choice == 'y');

		return servicesList;
	}
}
