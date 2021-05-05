package techproed.jdbcWinter2021;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BireyselCalisma {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		String yol = "jdbc:oracle:thin:@localhost:1521/XE";
		
		Connection con = DriverManager.getConnection( yol , "hr", "hr");
		
		Statement st = con.createStatement();
		
		String sqlKod = "SELECT * FROM bolumler";
		
		ResultSet tablo1 = st.executeQuery(sqlKod);
		
		while (tablo1.next()) {

			System.out.println(tablo1.getInt(1) + " " + tablo1.getString(2) + " " + tablo1.getString(3));

			// System.out.println( tablo1.getInt("bolum_id")
						// + " " + tablo1.getString("bolum_isim")
						// + " " + tablo1.getString("konum"));
		}

	}

}
