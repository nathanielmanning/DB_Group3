package main;
import gui.*;

import mySQLInterface.DataBase;

public class runner {
	public static void main (String[] args) throws Exception
	{
		DataBase.getDataBase().setUpDatabaseConnection();
		Table.createTable("test", null);
		Table.getTable().openWindow();
		Table.getTable().addMultiColumns(Table.getTable().getColNamesFromDB());
	
	}
}
