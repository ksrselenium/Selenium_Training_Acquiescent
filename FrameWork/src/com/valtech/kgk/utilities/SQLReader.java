package com.valtech.kgk.utilities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.sql.Statement;
import java.util.Properties;



public class SQLReader

{
	DataReader readdata=new DataReader();
    public Properties SQL= readdata.readSQL();
      
	
	public String[][] sql(String query) 
	
	{
	
	int rowCount = 0;
	int k,l;
	int columnCount = 0;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String[][] tabArray = null;
	Connection conn = null;

	try
	{
	Class.forName(driver).newInstance();// create object of Driver
	
	conn = DriverManager.getConnection("jdbc:sqlserver://172.19.12.34:1433;databaseName=Matrix;selectMethod=cursor",  
	"indiaTest", "agh78_1");
	// at this point connection will be established

	query=SQL.getProperty("regnum");
	Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	ResultSet rs = stmt.executeQuery(query);
	// Get Column count
	ResultSetMetaData rs_metaData = rs.getMetaData();
			columnCount = rs_metaData.getColumnCount();
			System.out.println("the coloumn count is:" + columnCount);
	// Get Row count						
			while (rs.next()) {
				
				rowCount++;
				System.out.println("value is "+rs.getString(1));
			}
			System.out.println("The row count is: "+rowCount);
										
			rs.beforeFirst();
			
			tabArray = new String[rowCount][columnCount];
			
			for (int row = 0; row < rowCount; row++)
			{
				rs.next();

				for (int col = 1; (col <=(columnCount)); col++) 
				{
					tabArray[row][col-1] = rs.getString(col);
					//System.out.println("the  data is : " + tabArray[row][col - 1]);
				}
			
			}
									
			for (int i=0;i<rowCount;i++)
			{ for(int j=0;j<columnCount;j++)
			  {
				System.out.println("the  data is : " + tabArray[i][j]);
			   }
	         }
			
	stmt.close();
	conn.close();

	} 
	
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	return tabArray;	
	}
}
