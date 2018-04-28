package main;
import gui.*;

import mySQLInterface.DataBase;

public class runner {
	public static void main (String[] args) throws Exception
	{
		DataBase.getDataBase().setUpDatabaseConnection();
		MainMenu.createMainMenuModule().openWindow();
	
	}
}
