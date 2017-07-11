package pp2017.team10.server.map;

public class TestDatabase {

	/*
	 * @author Burcu Akcay
	 * 
	 * Testumgebung: Hier wird unsere Datenbank getestet. Wird die Datenbank
	 * erstellt? Wird die Tabelle erstellt? Verbindet es zu unserer Datenbank?
	 * Existiert der User? Füge neuen User ein
	 */

	public static void main(String[] args) {

		DatabaseBurcu test = new DatabaseBurcu();
		DatabaseBurcu.createNewDatabase("gruppe10");
		DatabaseBurcu.createNewTable();
		DatabaseBurcu.connect();
		test.isUser("burcuakcay");

	}

}
