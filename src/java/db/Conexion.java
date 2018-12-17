package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author elpoeta
 */
public class Conexion {
    private static Conexion INSTANCE = null;
     private static String LABASE = "jdbc:postgresql://localhost/up_img_db";
     private static String LABASEUSUARIO = "elpoeta";
     private static String LABASECLAVE = "elpoeta";

	
	public static Conexion getInstance() throws ClassNotFoundException, IOException, SQLException {
         if (INSTANCE == null) {
             INSTANCE = new Conexion();
         }
         return INSTANCE;
     }
     private Conexion() throws ClassNotFoundException,
             IOException, SQLException {
     }

     public Connection getConnection() throws ClassNotFoundException,
             IOException, SQLException {
           Class.forName("org.postgresql.Driver"); 
         return DriverManager.getConnection(LABASE, LABASEUSUARIO, LABASECLAVE);
     }
}
