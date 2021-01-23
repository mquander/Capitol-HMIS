package com.hmis.util;

import java.io.InputStream;
import java.sql.*;
import java.sql.DriverManager;
import java.util.Properties;

public class DbUtil {
	 
	private static Connection dbConnection = null;

	 public static Connection getConnection() throws ClassNotFoundException {
		 //Class.forName("com.mysql.jdbc.Driver");
		 
		 if (dbConnection != null) {
	          return dbConnection;
	      } else {
	          try {
	              InputStream inputStream = DbUtil.class.getClassLoader()
	                      .getResourceAsStream("db.properties");
	              Properties properties = new Properties();
	              if (properties != null) {
	                  properties.load(inputStream);

	                  String dbDriver = properties.getProperty("dbDriver");
	                  String connectionUrl = properties
	                          .getProperty("connectionUrl");
	                  String userName = properties.getProperty("userName");
	                  String password = properties.getProperty("password");

	                  Class.forName(dbDriver);
	                  dbConnection = DriverManager.getConnection(connectionUrl,
	                          userName, password);
	              }
	          } catch (Exception e) {
	              e.printStackTrace();
	          }
	          return dbConnection;
	      }
	  }

}
