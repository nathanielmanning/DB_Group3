package mySQLInterface;

public class CreateTables {
	public static void makeTables() throws Exception
	{
		DataBase.getDataBase().AddData("CREATE TABLE PLAYER(" +
				"Login			CHAR(15)	NOT NULL CHECK (LEN(Login) >= 6 AND LEN(Login) <= 15),"+
				"Password		CHAR(15)	NOT NULL CHECK (LEN(Password) >= 6 AND LEN(Password) <= 15),"+
				"Email			CHAR(20)	NOT NULL CHECK (LEN(Email) >= 1 AND LEN(Email) <= 20),"+
				"PRIMARY KEY(Login));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE PLAYER_CHARACTER("+
				"Player_Login		CHAR(15)	NOT NULL CHECK (LEN(Player_Login) >= 6 AND LEN(Player_Login) <= 15),"+
				"Name			CHAR(15)	NOT NULL CHECK (LEN(Name) >= 1 AND LEN(Name) <= 15),"+
				"Current_HP		INT		NOT NULL CHECK (Current_HP >= 0 AND Current_HP <= 100),"+
				"Max_HP		INT		NOT NULL CHECK (Max_HP = 100),"+
				"Strength		INT		NOT NULL CHECK (Strength >= 0 AND Strength <= 100),"+
				"Stamina		INT		NOT NULL CHECK (Stamina >= 0 AND Stamina <= 100),"+
				"PRIMARY KEY(Name),"+
				"FOREIGN KEY(Player_Login) REFERENCES PLAYER(Login));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE ITEM("+
				"ID			INT		NOT NULL CHECK (ID >= 0 AND ID <= 1000),"+
				"Name		CHAR(15)	NOT NULL CHECK (LEN(Name) >= 1 AND LEN(Name) <= 15),"+
				"Volume		INT		NOT NULL CHECK (Volume >= 0 AND Volume <= 100),"+
				"Weight		INT		NOT NULL CHECK (Weight >= 0 AND Weight <= 100),"+
				"Description		CHAR(200)	NOT NULL CHECK (LEN(Description) >= 0 AND LEN(Description) <= 200),"+
				"PRIMARY KEY(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE WORN_ITEMS("+
				"Player_Name		CHAR(15)	NOT NULL CHECK (LEN(Player_Name) >= 1 AND LEN(Player_Name) <= 15),"+
				"Item_ID		INT		NOT NULL CHECK (Item_ID >= 0 AND Item_ID <= 1000),"+
				"FOREIGN KEY(Player_Name) REFERENCES PLAYER_CHARACTER(Name),"+
				"FOREIGN KEY(Item_ID) REFERENCES ITEM(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE INVENTORY("+
				"Player_Name		CHAR(15)	NOT NULL CHECK (LEN(Player_Name) >= 1 AND LEN(Player_Name) <= 15),"+
				"Item_ID		INT		NOT NULL CHECK (Item_ID >= 0 AND Item_ID <= 1000),"+
				"FOREIGN KEY(Player_Name) REFERENCES PLAYER_CHARACTER(Name),"+
				"FOREIGN KEY(Item_ID) REFERENCES ITEM(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE ABILITY("+
				"ID			INT		NOT NULL CHECK (ID >= 0 AND ID <= 1000),"+
				"AttackOrBenefit		CHAR(7)	NOT NULL CHECK (AttackOrBenefit IN( ‘Attack’, ‘Benefit’)),"+
				"StatTarget		CHAR(15)	NOT NULL CHECK (StatTarget IN (‘Health’, ‘Stamina’, ‘Strength’)),"+
				"HitPoints		INT		NOT NULL CHECK (HitPoints >= 0 AND HitPoints <= 100),"+
				"TimeToExecute	FLOAT(5,4)	NOT NULL CHECK (TimeToExecute >= 0 AND TimeToExecute <= 10),"+
				"PRIMARY KEY(ID));");	
	
	
		DataBase.getDataBase().AddData("CREATE TABLE WEAPON("+
				"Item_ID		INT		NOT NULL CHECK (Item_ID >= 0 AND Item_ID <= 1000),"+
				"Type			CHAR(15)	NOT NULL CHECK (Type IN(‘low_level’, ‘med_level’, ‘high_level’)),"+
				"Stored			BOOL		NOT NULL,"+
				"Hand			INT		NOT NULL CHECK (Hand >= 0 AND Hand <= 1),"+
				"Ability_id		INT,		NOT NULL CHECK (Ability_id >= 0 AND Ability_id <= 1000),"+
				"FOREIGN KEY(Item_ID) REFERENCES ITEM(ID),"+
				"FOREIGN KEY(Ability_ID) REFERENCES ABILITY(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE ARMOR("+
				"Item_ID		INT		NOT NULL CHECK (Item_ID >= 0 AND Item_ID <= 1000),"+
				"Type			CHAR(15)	NOT NULL CHECK (Type IN(‘low_level’, ‘med_level’, ‘high_level’)),"+
				"Stored			BOOL		NOT NULL,"+
				"Protection		INT		NOT NULL CHECK (Protection >= 0 AND Protection <= 1000),"+
				"WornOn		CHAR(15)	CHECK (WornOn IN(‘body’, ‘head’, ‘feet’)),"+
				"FOREIGN KEY(Item_ID) REFERENCES ITEM(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE CONTAINER("+
				"Item_ID		INT		NOT NULL CHECK (Item_ID >= 0 AND Item_ID <= 1000),"+
				"Weight_Limit		INT		NOT NULL CHECK (Weight_Limit >= 0 AND Weight_Limit <= 100),"+
				"Volume_Limit		INT		NOT NULL CHECK (Volume_Limit >= 0 AND Volume_Limit <= 100),"+
				"Slots 			CHAR(15)	CHECK (Slots IN(‘hands’, ‘belts’, ‘back’)),"+
				"FOREIGN KEY(Item_ID) REFERENCES ITEM(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE CONTAINER_ITEMS("+
				"Container_ID		INT		NOT NULL CHECK (Container_ID >= 0 AND Container_ID <= 1000),"+
				"Item_ID		INT		NOT NULL CHECK (Item_Id >= 0 AND Item_ID <= 1000),"+
				"FOREIGN KEY(Container_ID) REFERENCES ITEM(ID),"+
				"FOREIGN KEY(Item_ID) REFERENCES ITEM(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE LOCATION("+
				"ID			INT		NOT NULL CHECK (ID >= 0 AND ID <= 1000),"+
				"Size			FLOAT	(5,4)	NOT NULL,"+
				"Type			CHAR(15)	NOT NULL CHECK (Type IN(‘forest’, ‘desert’, ‘snow’, ‘mountains’)),"+
				"PRIMARY KEY(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE EXIT_LOCATIONS("+
				"Location_ID	INT	NOT NULL CHECK (Location_ID >= 0 AND Location_ID <= 1000),"+
				"Leads_To	INT	NOT NULL CHECK (Leads_To >= 0 AND Leads_To <= 1000),"+
				"FOREIGN KEY(Location_ID) REFERENCES LOCATION(ID),"+
				"FOREIGN KEY(Leads_To) REFERENCES LOCATION(ID));");
	
		DataBase.getDataBase().AddData("CREATE TABLE CREATURE("+
				"ID			INT		NOT NULL CHECK (ID >= 0 AND ID <= 10000),"+
				"Ability_ID		INT		NOT NULL CHECK (Ability_ID >= 0 AND Ability_ID <= 1000),"+
				"Max_HP		INT		NOT NULL CHECK (Max_HP = 100),"+
				"Current_HP		INT		NOT NULL CHECK (Current_HP >= 0 AND Current_HP <= 100),"+
				"Protection		INT		NOT NULL CHECK (Protection >= 0 AND Protection <= 100),"+
				"Strength		INT		NOT NULL CHECK (Strength >= 0 AND Strength <= 100),"+
				"Stamina		INT 		NOT NULL CHECK (Stamina >= 0 AND Stamina <= 100),"+
				"PRIMARY KEY(ID),"+
				"FOREIGN KEY(Ability_ID) REFERENCES ABILITY(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE CHATES("+
				"CID			INT		NOT NULL CHECK (CID >= 0 AND CID <= 1000),"+
				"CHates		CHAR(15)	CHECK(LEN(CHates) >= 1 AND LEN(CHates) <= 15),"+
				"FOREIGN KEY(CID) REFERENCES CREATURE(ID),"+
				"FOREIGN KEY(CHates) REFERENCES PLAYER_CHARACTER(Name));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE CLIKES("+
				"CID			INT		NOT NULL CHECK (CID >= 0 AND CID <= 1000),"+
				"CLikes			CHAR(15) 	CHECK(LEN(CLikes) >= 1 AND LEN(CLikes) <= 15),"+
				"FOREIGN KEY(CID) REFERENCES CREATURE(ID),"+
				"FOREIGN KEY(CLikes) REFERENCES PLAYER_CHARACTER(Name));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE CHATESC("+
				"CID			INT		NOT NULL CHECK (CID >= 0 AND CID <= 1000),"+
				"CHates		INT		NOT NULL CHECK (CHates >= 0 AND CHates <= 1000),"+
				"FOREIGN KEY(CID) REFERENCES CREATURE(ID),"+
				"FOREIGN KEY(CHates) REFERENCES CREATURE(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE CLIKESC("+
				"CID			INT		NOT NULL CHECK (CID >= 0 AND CID <= 1000),"+
				"CLikes			INT		NOT NULL CHECK (CLikes >= 0 AND CLikes <= 1000),"+
				"FOREIGN KEY(CID) REFERENCES CREATURE(ID),"+
				"FOREIGN KEY(CLikes) REFERENCES CREATURE(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE CDROPS("+
				"CID			INT		NOT NULL CHECK (CID >= 0 AND CID <= 1000),"+
				"Item_ID		INT		NOT NULL CHECK (Item_ID >= 0 AND Item_ID <= 1000),"+
				"FOREIGN KEY(CID) REFERENCES CREATURE(ID),"+
				"FOREIGN KEY(Item_ID) REFERENCES ITEM(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE CALLOWEDAREAS("+
				"CID			INT		NOT NULL CHECK (CID >= 0 AND CID <= 1000),"+
				"Location_ID		INT		NOT NULL CHECK (Location_ID >= 0 AND Location_ID <= 1000),"+
				"FOREIGN KEY(CID) REFERENCES CREATURE(ID),"+
				"FOREIGN KEY(Location_ID) REFERENCES LOCATION(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE MODERATOR("+
				"Login			CHAR(15)	CHECK (LEN(Login) >= 6 AND LEN(Login) <= 15),"+
				"Password		CHAR(15) 	CHECK (LEN(Password) >= 6 AND LEN(Password) <=15),"+
				"Email			CHAR(50)	CHECK (LEN(Email) >= 1 AND LEN(Email) <= 50),"+
				"Abilities		CHAR(100)	CHECK (LEN(Abilities) >= 1 AND LEN(Abilities) <= 100),"+
				"PRIMARY KEY(Login));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE MANAGER("+
				"Login			CHAR(15)	CHECK (LEN(Login) >= 6 AND LEN(Login) <= 15),"+
				"Password		CHAR(15) 	CHECK (LEN(Password) >= 6 AND LEN(Password) <=15),"+
				"Email			CHAR(50)	CHECK (LEN(Email) >= 1 AND LEN(Email) <= 50),"+
				"Abilities		CHAR(100)	CHECK (LEN(Abilities) >= 1 AND LEN(Abilities) <= 100),"+
				"PRIMARY KEY(Login));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE LOCATION_CHARACTER("+
				"Location_ID		INT		NOT NULL CHECK (Location_ID >= 0 AND Location_ID <= 1000),"+
				"Character_Name	CHAR(15)	NOT NULL CHECK (LEN(Character_Name) >= 1 AND LEN(Character_Name) <= 15),"+
				"FOREIGN KEY(Location_ID) REFERENCES LOCATION(ID),"+
				"FOREIGN KEY(Character_Name) REFERENCES PLAYER_CHARACTER(Name));");
	
	
	
		DataBase.getDataBase().AddData("CREATE TABLE LOCATION_CREATURE("+
				"Location_ID		INT		NOT NULL CHECK (Location_ID >= 0 AND Location_ID <= 1000),"+
				"Creature_ID		INT		NOT NULL CHECK (Creature_ID >= 0 AND Creature_ID <= 1000),"+
				"FOREIGN KEY(Location_ID) REFERENCES LOCATION(ID),"+
				"FOREIGN KEY(Creature_ID) REFERENCES CREATURE(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE LOCATION_ITEMS("+
				"Location_ID		INT		NOT NULL CHECK (Location_ID >= 0 AND Location_ID <= 1000),"+
				"Item_ID		INT		NOT NULL CHECK (Item_ID >= 0 AND Item_ID <= 1000),"+
				"FOREIGN KEY(Location_ID) REFERENCES LOCATION(ID),"+
				"FOREIGN KEY(Items_ID) REFERENCES ITEM(ID));");
	
	
		DataBase.getDataBase().AddData("CREATE TABLE ITEM_MODEL_VERTICES("+
				"Item_ID		INT		NOT NULL CHECK (Item_ID >= 0 AND Item_ID <= 1000)"+
				"Vertex			FLOAT(10)	NOT NULL,"+
				"FOREIGN KEY(Item_ID) REFERENCES ITEM(ID));");
	}

}
