

import mySQLInterface.DataBase;

public class main {
	public static void main (String[] args) throws Exception
	{
		DataBase.getDataBase().setUpDatabaseConnection();
		String test[] = {"fsd"};
		Table.createTable("test", null);
		Table.getTable().openWindow();
		Table.getTable().addMultiColumns(Table.getTable().getColNamesFromDB());
	
	}
}
