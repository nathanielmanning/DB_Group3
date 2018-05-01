package main;
import gui.*;
import mySQLInterface.CreateTables;
import mySQLInterface.DataBase;

public class runner {
	public static void main (String[] args) throws Exception
	{
		DataBase.getDataBase().setUpDatabaseConnection();
		CreateTables.makeTables();
		MainMenu.createMainMenuModule().openWindow();
	}
}
