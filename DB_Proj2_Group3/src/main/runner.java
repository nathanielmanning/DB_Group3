package main;
import gui.*;
import mySQLInterface.CreateTables;
import mySQLInterface.DataBase;

public class runner {
	public static void main (String[] args) throws Exception
	{
		// set up DB connection
		DataBase.getDataBase().setUpDatabaseConnection(); 
		//CreateTables.makeTables();
		// open the main menu window
		MainMenu.createMainMenuModule().openWindow();
	}
}
