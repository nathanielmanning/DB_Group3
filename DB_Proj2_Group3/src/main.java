
public class main {
	public static void main (String[] args)
	{
		String names[] = {"Player", "Login", "Password", "Character", "new col"};
		Table.createTable("TABLE", names);
		Table.getTable().openWindow();
	}
}
