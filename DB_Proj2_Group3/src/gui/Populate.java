/**********************************************************
 * Populates the database with information added into the *
 * database.                                              *
 * @author Emmitt Frankenberry                            *
 *                                                        *
 **********************************************************/
public class Populate {

	String s = "Insert into PLAYER(Login, Password, Email) values (‘Big D’, ‘NotPassword’, ‘nub1337@aol.com’);Insert into PLAYER_CHARACTER(Player_Login, Name, Current_HP, Max_HP, Strength, Stamina) values (‘Big D’, ‘NotPassword’, 7, 10, 5, 3);Insert into ITEM(ID, Name, Volume, Weight, Description) values (5, ‘Loser’, 5, 6, ‘Never again’);Insert into WORN_ITEMS (Player_Name, Item_ID) values (‘NotPassword’, 5);Insert into INVENTORY (Player_Name, Item_ID) values (‘NotPassword’, 5);Insert into ABILITY (ID, Attack/Benefit, StatTarget, HitPoints, TimeToExecute) values (7, attack, Health, 7, 1);Insert into WEAPON (Item_ID, Type, Stored, Hand, Ability_id) values (5, ‘low_level’, false, 0, 7);Insert into Armor(Item_ID, Type, Stored, Protection, WornOn) values (5, ‘low_level’, false, 6, ‘body’);Insert into Container (Item_ID, Weight_Limit, Volume_Limit, Slots) values (5, 36, 40, ‘hands’);Insert into CONTAINER_ITEMS (Container_ID, Item_ID) values (5, 5);Insert into CREATURE (ID, Ability_ID, Max_HP, Current_HP, Protection, Strength, Stamina) values(9, 7, 10, 3, 5, 2, 2);";
	
	/******************************************************
	 * Fills the tables up with 1 item each.              *
	 ******************************************************/
	public void populate(){
		DataBase.getDataBase().AddData(s);
	}
}
