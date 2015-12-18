package ec.gob.iess.cuartomaquinas.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManejadorUsuario {

	public boolean validarUsuario(String usuario, String clave) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = GestorConexion.obtenerConexion();
			ps = conn
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
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e1) {}
			if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {	}
		}
		return false;
	}
}
