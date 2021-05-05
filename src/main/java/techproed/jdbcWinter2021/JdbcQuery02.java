package techproed.jdbcWinter2021;

import java.sql.*; // Tum SQL kutuphanesini ekledim cunku ugrasmak istemiyoruz (tavsiye degildir tool siser)

public class JdbcQuery02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String yol = "jdbc:oracle:thin:@localhost:1521/XE"; // kolaylik olsun diye string haline getirdik 
															// (degisken gibi tanimladik) bu kullanim daha yaygin
		                                                    // her defasinda yol'u yazmaktaksa yol yazariz


		Class.forName("oracle.jdbc.driver.OracleDriver"); 
		
														  // ilk isimiz driver'i yuklemek
														  // add trows declaration yaptik 
														  // (ya bulamazsam hatasi verdi)
		
		// java dis bir kaynaga erisecekse bunu handle etmek icin exception firlatir
		
		
		Connection con = DriverManager.getConnection( yol , "hr", "hr"); // add trows declaration yaptik 
		  																 // (ya bulamazsam hatasi verdi)
		
		Statement st = con.createStatement();
		
		
		/* =======================================================================
		  ORNEK1: Bolumler tablosundaki tum kayitlari listeleyen bir sorgu yaziniz.
		 ========================================================================*/
		
		
		//ResultSet bolumlerTablosu = st.executeQuery("SELECT * FROM bolumler");
		
		
		String selectQuery = "SELECT * FROM bolumler";
		ResultSet tablo1 = st.executeQuery(selectQuery);
		
		
		while (tablo1.next()) { // tablo1.next() ==> tablo1 de next eleman varmi diye bak
			
			System.out.println(tablo1.getInt(1) + " " + tablo1.getString(2) + " " + tablo1.getString(3));
			
			//System.out.println(tablo1.getInt("bolum_id") 
					//+ " " + tablo1.getString("bolum_isim") 
					//+ " " + tablo1.getString("konum"));
		}
		
		
		
		System.out.println("========================================================");
		
		/* =======================================================================
		  ORNEK2: SATIS ve MUHASABE bolumlerinde calisan personelin isimlerini ve
		  maaslarini maas sirali olarak listeleyiniz
		 ========================================================================*/
		 
		
		String q2 ="SELECT personel_isim,maas "
				+ "FROM personel "
				+ "WHERE bolum_id IN(10,30) "
				+ "ORDER BY maas DESC";
		
		
		ResultSet tablo2 = st.executeQuery(q2);
		
		
		while (tablo2.next()) {
			System.out.println("ISIM : " + tablo2.getString(1) + "\t" +  "MAAS : " +tablo2.getString(2));
		}
		
		
		con.close();
		st.close();
		tablo1.close();
		tablo2.close();
		
		

	}

	
}
