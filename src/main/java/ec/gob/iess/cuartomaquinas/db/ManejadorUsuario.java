package ec.gob.iess.cuartomaquinas.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManejadorUsuario {

	public boolean validarUsuario(String usuario, String clave) {
		Connection conn = null;

		try {
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from usuario where nombre=?");
			ps.setString(1, usuario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String claveBase = rs.getString("clave");
				if (claveBase.equals(clave)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
