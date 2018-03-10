package com.valtech.kgk.utilities;



import generalFun.GeneralFun;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import jxl.read.biff.BiffException;


public class DataReader 
{

public  Properties CONFIG;
public  Properties OR;
public  Properties TEXT;
public  Statement stmt;
public  ResultSet rs;
public  Properties SQL;

String oldpath = getClass().getClassLoader().getResource(".").getPath();		
String drive =oldpath.substring(1, 2)+":";
	
	// To retrieve data from excel ---Need to pass Testdata excel name , sheetname and column & rows number 
	public  String excel(String exlname,String sheetname,int colnum,int rownum) throws BiffException, IOException
	{  
				
		FileInputStream f2=new FileInputStream(drive+readConfig().getProperty(exlname));
	    Workbook w2=Workbook.getWorkbook(f2);	             
		Sheet s2= w2.getSheet(sheetname);
		System.out.println("colnum="+colnum);
		System.out.println("rownum="+rownum);
				
		String retvalue=s2.getCell(colnum,rownum).getContents();
		System.out.println("retvalue="+retvalue);
		return retvalue;
		
		
	}
	
	public void sql() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
	System.out.println("reading from SQL");	
	Connection conn = null;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	Class.forName(driver).newInstance();// create object of Driver
	conn = DriverManager.getConnection("jdbc:sqlserver://172.19.12.34:1433;databaseName=Matrix;selectMethod=cursor","indiaTest","agh78_1");
	// at this point connection will be established
	stmt = conn.createStatement();
	
	}
	
	
	public Properties readConfig() 
	{  try
	   {
	
		FileInputStream file=new FileInputStream(drive+"\\KGKProject_Workspace\\KGKTestAutomation\\resource\\Config.properties");
		CONFIG=new Properties();	
		CONFIG.load(file);
	   }
	   catch (Exception e)
	   {
		   System.out.println("Config File missing");
	   }
		return CONFIG;
		
	}
	
	
	public Properties readObjectRepository() 
	{  try
	   {
	
		FileInputStream file=new FileInputStream(drive+"\\KGKProject_Workspace\\KGKTestAutomation\\resource\\OR.properties");
		OR=new Properties();	
		OR.load(file);
	   }
	   catch (Exception e)
	   {
		   System.out.println("Repository file missing");
	   }
		return OR;
		
	}
	
	
	public Properties readText() 
	{
		try
		{
			readConfig();
			FileInputStream file=new FileInputStream(drive+readConfig().getProperty("Textpath"));
			TEXT=new Properties();	
			TEXT.load(file);
		}
		catch(Exception e)
		{
			System.out.println("text File Missing");
		}
		
			return TEXT;
		}
	
	public Properties readSQL() {
		try
		{
			readConfig();
			FileInputStream file=new FileInputStream(readConfig().getProperty("sqlpath"));
			SQL=new Properties();	
			SQL.load(file);
		}
		catch(Exception e)
		{
			System.out.println("sql some problem");
		}
		
			return SQL;
		}

	
	
	/*
	 * This Method is used to read the data from XSL File.
	 */

	public  String[][] getTableArray(String xlFilePath, String sheetName,
			String tableName) throws Exception {
		String[][] tabArray = null;

		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
		Sheet sheet = workbook.getSheet(sheetName);
		int startRow, startCol, endRow, endCol, ci, cj;
		Cell tableStart = sheet.findCell(tableName);
		startRow = tableStart.getRow();
		startCol = tableStart.getColumn();

		Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1,
				100, 64000, false);

		endRow = tableEnd.getRow();
		endCol = tableEnd.getColumn();
		System.out.println("startRow=" + startRow + ", endRow=" + endRow + ", "
				+ "startCol=" + startCol + ", endCol=" + endCol);
		tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
		ci = 0;

		for (int i = startRow + 1; i < endRow; i++, ci++) {
			cj = 0;
			for (int j = startCol + 1; j < endCol; j++, cj++) {
				tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				System.out.println("returning values");
			}
		}

		return (tabArray);
	}
	
	
	}



