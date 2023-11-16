package com.ilp.entity;

import java.util.ArrayList;

public class SavingsMaxAccount extends Product {
	private double minBalance = 1000;

	public double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	public SavingsMaxAccount(String productCode, String productName, ArrayList<Services> serviceList,
			double minBalance) {
		super(productCode, productName, serviceList);
		this.minBalance = minBalance;
	}

	

}
