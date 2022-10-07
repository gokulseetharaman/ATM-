package com.atm;

import java.sql.*;
import java.util.Scanner;

public class Atm {

	public static int totalMoney;
	public static ResultSet rs;
	public static Scanner sc = new Scanner(System.in);

	public static void Loadcash() throws ClassNotFoundException, SQLException { // Task 1 : Load cash to ATM
		System.out.println("how many 2000");
		int tth = sc.nextInt();
		System.out.println("how many 500");
		int fh = sc.nextInt();
		System.out.println("how many 200");
		int th = sc.nextInt();
		System.out.println("how many 100");
		int hun = sc.nextInt();
		totalMoney = (tth * 2000) + (fh * 500) + (th * 200) + (hun * 100);
		System.out.println(totalMoney);// total money available in the atm
	}

	public static void Customer() throws ClassNotFoundException, SQLException {// Maintain Customer Details
		int AccNo = 1;
		Connection con = DButil.getConnection();
		PreparedStatement pst = con.prepareStatement("INSERT INTO ATM12 VALUES(?,?,?,?)");
		pst.setInt(1, AccNo);
		String name = sc.next();
		pst.setString(2, name);
		int PinNo = sc.nextInt();
		pst.setInt(3, PinNo);
		int AccBal = sc.nextInt();
		pst.setInt(4, AccBal);
		pst.execute();
		AccNo++;
	}

	public static void checkBalance() throws SQLException, ClassNotFoundException {
		System.out.println("ENTER THE ACC_NO ");
		int acc_no = sc.nextInt();
		System.out.println("ENTER THE PIN NUMBER ");
		int pin_no = sc.nextInt();
		int pinNo = 0, accBal = 0;
		Connection con = DButil.getConnection();
		PreparedStatement pst = con.prepareStatement("select PinNo ,AccB from CUSTOMER where ACC_NO=?");
		pst.setInt(1, acc_no);
		rs = pst.executeQuery();
		while (rs.next()) {
			pinNo = rs.getInt("PIN_NUMBER");
			accBal = rs.getInt("ACC_BALANCE");
		}
		if (pinNo == pin_no) {
			System.out.print("YOUR ACC NO " + acc_no + " BALANCE " + accBal);
		} else {
			System.out.println("CHECK YOUR PIN TRY AGAIN");
		}
	}

	public static void withdrawl() throws SQLException {
		System.out.println("ENTER THE ACC_NO ");
		int acc_num = sc.nextInt();
		System.out.println("ENTER THE PIN NUMBER ");
		int pin_num = sc.nextInt();
		System.out.println("enter the ammount will be withdrawl");
		int ab = sc.nextInt();
		int pinNo = rs.getInt("PIN_NUMBER");
		int accBal = rs.getInt("ACC_BALANCE");
		if (pinNo == pin_num) {
			if (ab % 100 == 0) {
				totalMoney = ab - totalMoney;
			}
		}

	}

	public static void transer() throws SQLException {
		System.out.println("ENTER THE ACC_NO ");
		int acc_numb = sc.nextInt();
		System.out.println("ENTER THE PIN NUMBER ");
		int pin_numb = sc.nextInt();
		int pinNo = rs.getInt("PIN_NUMBER");
		int accBal = rs.getInt("ACC_BALANCE");
		int tran = sc.nextInt();
		if (tran < 10000 && tran < accBal) {
			totalMoney = tran - totalMoney;
		}

	}

	public static void Balance() {

		System.out.println("the money avilabeli in the ATM " + totalMoney);
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		System.out.println("1.loadcash\n2.custdetails\3.atmoperation\n4.exit");
		int ch = sc.nextInt();
		switch (ch) {
		case 1:
			Loadcash();
			break;

		case 2:
			Customer();
			break;
		case 3:
			System.out.println("1.checkbalance\n2.withdraw\n3.transfer\n4.exit process");
			int cho = sc.nextInt();
			switch (cho) {
			case 1:

				checkBalance();
				break;

			case 2:
				withdrawl();
				break;
			case 3:
				transer();
				break;
			case 4:
				Balance();
				break;
			default:
				System.out.println("error 404: not found");
			}
		default:
			System.out.println("error 404: not found");
		}
	}

}
