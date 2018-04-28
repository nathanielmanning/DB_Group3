package main;
import gui.*;

import mySQLInterface.DataBase;

public class runner {
	public static void main (String[] args) throws Exception
	{
		DataBase.getDataBase().setUpDatabaseConnection();
		Table.createTable("Test", null);
		Table.getTable().openWindow();
		Table.getTable().addMultiColumns(Table.getTable().getColNamesFromDB());
	
	}
}
