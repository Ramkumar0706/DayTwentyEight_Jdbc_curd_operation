package com.jdbcProduct;

 import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;


public class ProductService {

	public static Properties ProductProperty() {
		FileReader reader=null;
		Properties pro=null;
		try {
		reader=new FileReader("config/Productconfig.properties");
		pro=new Properties();
		pro.load(reader);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pro;
	}
	public static Connection addConnection()  {
		Connection con=null;
		Properties pro=ProductProperty();
		try {
			Class.forName(pro.getProperty("driver"));
			con=DriverManager.getConnection(pro.getProperty("host"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public void addProduct(int id,String name,String brand,int price) {
		Connection con=addConnection();
		Properties pro=ProductProperty();
		try {
			PreparedStatement stmt=con.prepareStatement(pro.getProperty("addProduct"));
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setString(3, brand);
			stmt.setInt(4, price);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			System.out.println("succes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void findProductById(int id) {
		Connection con=addConnection();
		Properties pro=ProductProperty();
		try {
			PreparedStatement stmt=con.prepareStatement(pro.getProperty("findProductById"));
			stmt.setInt(1, id);
			ResultSet eq = stmt.executeQuery();
			eq.next();
			System.out.println(eq.getInt("productId")+" "+eq.getString("productName")+" "+eq.getString("productBrand")+" "+eq.getInt("productPrice"));
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateProductById(int id,int price) {
		Connection con=addConnection();
		Properties pro=ProductProperty();
		try {
			PreparedStatement stmt=con.prepareStatement(pro.getProperty("updateProductById"));
			stmt.setInt(1, price);
			stmt.setInt(2,id);
			stmt.executeUpdate();
			con.close();
			System.out.println("update success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteproductById(int id) {
		Connection con=addConnection();
		Properties pro=ProductProperty();
		try {
			PreparedStatement stmt=con.prepareStatement(pro.getProperty("deleteProductById"));
			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println("delete");
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void  findproductByName(String name) {
		Connection con=addConnection();
		Properties pro=ProductProperty();
		try {
			PreparedStatement stmt=con.prepareStatement(pro.getProperty("findProductByName"));
			stmt.setString(1, name);
			ResultSet eq = stmt.executeQuery();
			eq.next();
			System.out.println(eq.getInt("productId")+" "+eq.getString("productName")+" "+eq.getString("productBrand")+" "+eq.getInt("productPrice"));
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void findAllProduct() {
		Connection con=addConnection();
		Properties pro=ProductProperty();
		try {
			PreparedStatement stmt=con.prepareStatement(pro.getProperty("findAllProduct"));
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("productId")+" "+rs.getString("productName")+" "+rs.getString("productBrand")+" "+rs.getInt("productPrice"));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void findAllProductBetween(int lprice,int hprice) {
		Connection con=addConnection();
		Properties pro=ProductProperty();
		int hcount=0;
		int lcount=0;
		try {
			PreparedStatement stmt=con.prepareStatement(pro.getProperty("findAllProductBetween"));
			stmt.setInt(1, lprice);
			stmt.setInt(2, hprice);
			ResultSet rs=stmt.executeQuery();
	while(rs.next()) {
		if(lprice==rs.getInt("productPrice")) {
			lcount++;
		}
		else if(hprice==rs.getInt(hprice)) {
			hprice++;
		}
	}
			while(rs.next()) {
				if(lcount==1&&hcount==1) {
				System.out.println(rs.getInt("productId")+" "+rs.getString("productName")+" "+rs.getString("productBrand")+" "+rs.getInt("productPrice"));
			}
				else {
					System.out.println("no data");
				}
				
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}

}



