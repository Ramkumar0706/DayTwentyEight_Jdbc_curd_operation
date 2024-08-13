package com.jdbcProduct;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;



public class App 
{
	public static void main( String[] args )
	{


		ProductService ps=new ProductService();
		Scanner sc=new Scanner(System.in);
		boolean rs=true;
		while(rs) {
			System.out.println("enter the number to perform TO ENTER THE BETWEEN (1 TO 7 ) operation");
			System.out.println("1:To add the products \n "
					+ "2:To find the product by Id \n "
					+ "3:To update product by id"
					+ "\n 4:To find product by name \n "
					+ "5:To delete the product by id \n "
					+ "6:To find all product details \n "
					+ "7:To find products between particular prices \n "
					+ "0:To Exit");
			int s=sc.nextInt();
			if(s<=7) {
				switch(s){
				case 1:
					System.out.println("enter the full data to insert");
					ps.addProduct(sc.nextInt(), sc.next(), sc.next(),sc.nextInt());
					break;
				case 2:
					System.out.println("find product by Id. please enter Id");
					ps.findProductById(sc.nextInt());
					break;
				case 3:
					System.out.println("update product of price");
					ps.updateProductById(sc.nextInt(), sc.nextInt());
					break;
				case 4:
					System.out.println("find product by Name. please enter Name");
					ps.findproductByName(sc.next());
					break;
				case 5:
					System.out.println("deleteproduct by Id. please enter Id");
					ps.deleteproductById(sc.nextInt());
					break;
				case 6:
					ps.findAllProduct();
					break;
				case 7:
					System.out.println("find product by price in between, please enter the lowest price and highest price");
					ps.findAllProductBetween(sc.nextInt(),sc.nextInt() );
					break;
				case 0:
					rs=false;
					System.out.println("Program Terminated");

				}
			}
			else {
				System.out.println("pls enter number in between ( 1 to 7)");
			}
		}
	}
}









