package com.BlobOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.JDBCUtil.JdbcUtil;

public class ImageInsertion {

	public static void main(String[] args) {
		// Resources used
				Connection connection = null;
				PreparedStatement pstmt = null;
				Scanner scanner = null;

				// variables used
				int id=0;
				String name = null;
				String imageLoc = null;

				try {
					connection = JdbcUtil.getJdbcConnection();

					String sqlInsertQuery = "insert into persons(id,name,image) values(?,?,?)";
					if (connection != null)
						pstmt = connection.prepareStatement(sqlInsertQuery);

					if (pstmt != null) {
						scanner = new Scanner(System.in);

						// collecting the inputs
						if (scanner != null) {
							System.out.println("enter id::");
							id=scanner.nextInt();
							System.out.print("Enter the username :: ");
							name = scanner.next();

							System.out.print("Enter the image location :: ");
							imageLoc = scanner.next();//  D:\images\nitin.JPG
						}

						// Set the input values to Query
						pstmt.setInt(1, id);
						pstmt.setString(2, name);
						pstmt.setBinaryStream(3, new FileInputStream(new File(imageLoc)));

						// execute the query
						int rowAffected = pstmt.executeUpdate();
						System.out.println("No of rows inserted inserted is :: " + rowAffected);

					}

				} catch (SQLException | IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {

					try {
						JdbcUtil.cleanUp(connection, pstmt, null);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					scanner.close();
				}

			}

		

	}


