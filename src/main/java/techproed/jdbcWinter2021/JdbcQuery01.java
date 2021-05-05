package techproed.jdbcWinter2021;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcQuery01 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// 1) Ilgili driver'i yuklemeliyiz.
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2) Baglanti olusturmaliyiz.
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "hr", "hr");
		
		/*
		 * Baglanti olusturmak icin Connection nesnesi olusturmaliyiz(Connectoin bir interface dir) 
		 * ve DriverManager.getConnection() method'u ile olusturuyoruz
		 * 
		 * import edilmelidir yoksa hata verir
		 * 
		 * 
		 * "hr", "hr");  ==> kullanici adi ve sifresidir
		 * 
		 * Ne ile baglandigine göre degisir(Sisteme bakmak gerekir)
		 * 
		 * 1) 	jdbc:oracle:thin:@localhost:1521/ORCL ==> Windows icin 
		 * 		jdbc:oracle:thin:@localhost:1521/XE ==> Windows icin
		 * 		jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain ==> Mac icin (Docker
		 * 		olanlar)
		 * 
		 * 
		 * 4) method,fonksiyonlari eklerken hata verirse
		 * "throws ClassNotFoundException, SQLException" yani throws declaration yapiniz
		 * 
		 * 
		 * 5) import java.sql.*; ==> yaparsaniz komple sql kutuphanesini ekler (tavsiye degildir tool siser)
		 * 
		 * 
		 * 
		 */

		// 3) SQL komutlari icin bir Statement nesnesi olustur
		Statement st = con.createStatement();
		
		
		/*
		 * 
		 * import edilmelidir yoksa hata verir.
		 * 
		 * Statement nesnesi olusturuyoruz (Statement bir interface 'dir)
		 * 
		 * createStatement() methodu con objesinden yeni bir obje olusturuyor ==> con.createStatement();
		 * 
		 * Statement st ==> yani  yeni bir  st adinda Statement  objesi olusturuldu
		 * 
		 * 
		 * ve bu st isimli objesini SQL ifadeleri yazabilmek icin kullaniyoruz
		 * 
		 */
		
		
		
		
		

		// 4) SQL ifadelerini yazabiliriz
		// (Personel tablosundaki personel_id'si 7369 olan personelin adini
		// sorgulayiniz)

		ResultSet isim = st.executeQuery("SELECT personel_isim, maas FROM personel WHERE personel_id=7369");
		
		
		/*
		 * import edilmelidir yoksa hata verir.
		 * 
		 * executeQuery() method'u bize SQL den bir komut kumesi döndurur
		 * 
		 * ResultSet adi ustunde bir set'dir
		 * 
		 * 
		 * SQL de "SELECT personel_isim, maas FROM personel WHERE personel_id=7369"
		 * bu kodun karsiligi olan bir tablo veya  verinin olup olmadigina göre hata verebilir
		 * böyle bir durumda  (egitim asamasinda) taployu drop edip create ile tekrar olusturup
		 * commit komutu ile tekrardan deneyebilirsin. DIKKAT !! COMMIT komutu veriyi veri tabanini 
		 * kesin olarak gönderir. (yani bu artik kesin ben bundan eminin  deyip veri tabanina göndermek gibi dusun)
		 * 
		 * 
		 */
		
		
				

		// 5) Sonuclari aldik ve istedik. VE yazdirmak istiyoruz
		
		
		/*
		 * Resultlarim birden cok loop'la almam gerekli;
		     => bir deger olsa bile kursor donmesi icin next() olmasi lazim yani loop
		 */
		
		
		while (isim.next()) {
			System.out.println("Personel Adi : " + isim.getString("personel_isim"));
			System.out.println("Personel Adi : " + isim.getString(1) + " Maas :" + isim.getNString(2) ); 
			
			// anternatif yol  1 => 1. sutunu tanimlar yani personel_isim
							// 2 => 2. sutunu tanimlar yani	maas
		
		}
		
		/*
		 * Artik yazdirma asamasindayiz. genellikle While döngusu kullanilir
		 * isim.next() methodu true oldugu surece  body calisir
		 * next() bir sonraki imlece geciren method'dur ( bir deger olsa bile kursor donmesi icin next() olmasi lazim yani loop)
		 * 
		 * 
		 * isim.getString("personel_isim") ==>  () icindeki kisim ile get..... kisimlarin data tipi uyumlu olmalidir
		 * 
		 */
		

		// 6) Olusturulan nesneleri bellekten kaldiralim.
		con.close();
		st.close();
		isim.close();
		
		
			
		
		
		/*
		 
		  
		DERS OZETI:
		
		
		
		
		
	1.KISIM: MAVEN PROJE OLUSTURMA
		
		1-file
		
		2-/new project
		 
		3-/mavenproject(wizarddan), cikmazsa other dan bak
		
		4-/next
		
		5-/catalog'dan internal i sec,
		 
		filter'da maven-archetype-quickstart 1.1 i sec(en yuksek versiyon ne ise)
		
		6-/next
		
		7-/groupid     (FIRMA ADI):techproed 
          artifactid: (PROJE ADI):JdbcDersler
		
		8-/finish
		





	2.KISIM:POM AYARLARI ve PROJE UPDATE ETMEK
		
		    <dependencies>
		        <dependency>
		            <groupId>junit</groupId>
		            <artifactId>junit</artifactId>
		            <version>4.11</version>
		        </dependency>
		        <dependency>
		            <groupId>com.oracle.database.jdbc</groupId>
		            <artifactId>ojdbc10</artifactId>
		            <version>19.8.0.0</version>
		        </dependency>
		    </dependencies>
		    
		    
		    POM AYARLARINA ORACLE DATABASE JDBC yi ekliyoruz
		   (ben scope kismini sildim junit'de cunku kullanmiyacagiz. dedi hoca) 
		    
		    
			
			
			
			Maven project olusturup Pom lari duzenledikten sonra save all yapiyoruz
		    sonra source yapip FORMAT yapiyoruz yine save varsa save edin.
		
		
		
			PROJE GUNCELLEMESI: projenin ustune sag tiklayip
			1-/Maven
			2-/UpdateProject i secin.
		
		
		
		
		
	3.KISIM: 	Projenize gidin src deki sizin icin olusturulan paketi silin siz kendiniz
				istediginiz isimde paket olusturun.Ornek:JdbcTurkceDersler
				
		Paketin altina main method secerek class olusturun. Ornek:JdbcQuery01
	
	
	
	
	
	
	
	4.KISIM: SQL Structure nasil olacak? VERY IMPORTANT!!
	
	
	
		JDBC kullanarak nasil bir connection olusturabilirsin?
		
		
		              STRUCTURE OF JDBC
		              
		  1)	import java.sql.*; (under package,import)
		   		==> yaparsaniz komple sql kutuphanesini ekler (tavsiye degildir tool siser)
		  
		  
		  
		             
		  2)	Class.forName("oracle.jdbc.driver.OracleDriver");(choose throw declaration)
		  		----class ismini ekle demek...
		    
		    
		    
		    
		  3)	for Mac: Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain","oracleusername","oraclepassword");
		  		for Windows:Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl","oracleusername","oraclepassword");
		  		(choose throw declaration)
		  		
		  		(JDBC DE sadece 1 tane exception vardir oda SQLException dir.)
		  
		  
		  
		  
		  4)  Statement st = con.createStatement();
		  
		  
		  
		  5)  ResultSet rs=st.executeQuery("SELECT * FROM tableName");
		  									=> bu kisim soruya gore degisir
		  									=> String q1="SELECT * FROM .....";   gibide ayri yazilip sonra
		  									=> ResultSet rs=st.executeQuery(q1); seklinde de yazilinabilinir.
		  
		     
		  
		  6)  => table da 1.olusturulan sutun string'se rs.getString(1), 
		  		number'sa rs.getInt(1) 1--->sutunnosu date ise rs.getDate(sutunno)
		      
		      
		      ornek: 2.sutun int number'sa rs.getInt(2);
		      		
		      yada soyle yapabilirsin 
		      
		      ornek: Select isim, id, maas;
		                       
		                => rs.getString("isim"), rs.getString("id"),rs.getDouble("maas")
		      yada;
		       			=> rs.getString(1), rs.getString(2),rs.getDouble(3) 
		      seklinde yazariz
		      
		      tabii bunlari loop'un icine ve systemout'un icinde yazacagiz.
		     
		     
		     
		     
		      Resultlarim birden cok loop'la almam gerekli;
		      => bir deger olsa bile kursor donmesi icin next() olmasi lazim yani loop
		     
		      while(rs.next()) {
		      System.out.println(rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getInt(3));
		      }
		  
		  7)  Butun connectionlari kapatmamiz gerekir, guvenlik acisindan.
		      
		      con.close();
		      st.close();
		      rs.close();
		      
		      
		      
		  IMPORTANT: Her zaman ilk 4 asama ayni....
		  
		  */

	}

}
