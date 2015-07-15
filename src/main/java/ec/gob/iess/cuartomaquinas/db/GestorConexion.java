package ec.gob.iess.cuartomaquinas.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class GestorConexion {

	
	public static Connection obtenerConexion() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(
					//"jdbc:postgresql://127.6.9.130:5432/hcam", "adminbwhvjhr", "xnw5Px3WqUi6");
					"jdbc:postgresql://localhost:5432/hcam", "adminbwhvjhr", "xnw5Px3WqUi6");
			return connection;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			// Look up our data source
			DataSource ds = (DataSource)
			  envCtx.lookup("jdbc/PostgreSQLDS");

			// Allocate and use a connection from the pool
			return ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		return null;
	}

}
