package pp2017.team10.server.map;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*In dieser Klasse wird die Datenbank für unser Spiel erzeugt.
 * Die Datenbank habe ich mit dem SQLite Benutzerhandbuch von der Homepage http://www.sqlitetutorial.net erstellt. 
 * Dies war im Ilias Kurs in dem Ordner: Materialien zum Inhaltlichen angegeben. Zudem habe ich den SQLite JDBC Driver in der 
 * Version 3.8.11.2 heruntergeladen, um damit arbeiten zu können, welches in der PDF "Organisatorisches" als externe Bibliothek erlaubt wird. 
 * 
 * @author Burcu Akcay
 */

public class DatabaseBurcu {

	boolean isUser;
	boolean newUser;
	public String user;
	public String password;

	public static Connection con = null;

	/*
	 * source: sqlitetutorial.net
	 * http://www.sqlitetutorial.net/sqlite-java/create-database/
	 * 
	 * 
	 * Die Methode createNewDatabase erstellt die Datenbank für unser Spiel.
	 */

	public static void createNewDatabase(String fileName) {

		String url = "jdbc:sqlite:" + fileName;

		try (Connection con = DriverManager.getConnection(url)) {
			if (con != null) {
				DatabaseMetaData meta = con.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * source: sqlitetutorial.net
	 * http://www.sqlitetutorial.net/sqlite-java/create-table/
	 * 
	 * Die Methode createNewTable erstellt die notwendige Tabelle unserer
	 * Datenbank.
	 */

	public static void createNewTable() {
		// SQLite connection string
		String url = "jdbc:sqlite:gruppe10";

		// SQL statement for creating a new table
		String sql = "CREATE TABLE IF NOT EXISTS gruppe10(\n" + "	user String PRIMARY KEY,\n" + "	password String\n" // SQL
																														// Abfrage
				+ ");";

		try (Connection con = DriverManager.getConnection(url); Statement stmt = con.createStatement()) {
			// create a new table
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	/*
	 * source: sqlitetutorial.net
	 * http://www.sqlitetutorial.net/sqlite-java/select/
	 *
	 * Die Methode connect() stellt die Verbindung zu unserer Datenbank her.
	 */

	public static Connection connect() {

		String url = "jdbc:sqlite:gruppe10";

		try {
			con = DriverManager.getConnection(url); // Verbindung herstellen
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return con;
	}

	/*
	 * @author Burcu Akcay
	 *
	 * Die Methode isUser überprüft ob der eingegebene User schon in der
	 * Datenbank steht. Wenn er schon drinsteht, dann soll er die Spielerdaten
	 * ausgeben. Für den Fall, dass er nicht drinsteht, soll er
	 * "User does not exist ausgeben". Daraufhin erfolgt dann die weitere
	 * Methode insert().
	 **/
	public boolean isUser(String user) {

		boolean isUser = false;

		try {
			Statement statement = con.createStatement();
			String request = "SELECT user FROM gruppe10 WHERE user='" + user + "'"; // SQL
																					// Abfrage
			ResultSet resultset = statement.executeQuery(request);

			while (resultset.next()) {

				System.out.println(resultset.getString("user"));
				System.out.println("User exist");
			}

		} catch (SQLException e) {
			System.out.println("User does not exist");
		}

		return isUser;

	}

	/*
	 * source: sqlitetutorial.net
	 * http://www.sqlitetutorial.net/sqlite-java/insert/
	 * 
	 * Diese Methode soll eintreten, wenn ein User nicht in der Datenbank
	 * existiert. Das heißt diese Methode fügt den User mit Passwort in die
	 * Datenbank ein.
	 */
	public void insert(String user, String password) {
		this.user = user;
		this.password = password;

		String sql = "INSERT INTO gruppe10(user,password) VALUES('" + user + "', '" + password + "')"; // SQL
																										// Abfrage

		try (Connection con = DatabaseBurcu.connect(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
