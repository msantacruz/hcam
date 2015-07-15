package ec.gob.iess.cuartomaquinas.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ec.gob.iess.cuartomaquinas.dto.EstadoBombasDTO;
import ec.gob.iess.cuartomaquinas.dto.MovimientoDieselDTO;

public class ManejadorDiesel {

	public MovimientoDieselDTO buscarUltimoValor() {
		MovimientoDieselDTO movimientoDieselDTO = new MovimientoDieselDTO();

		Connection conn = null;

		try {
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from movimiento_diesel order by id desc limit 1");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				movimientoDieselDTO.setDescarga(rs.getDouble("descarga"));
				movimientoDieselDTO.setSalida(rs.getDouble("salida"));
				movimientoDieselDTO.setTemperatura(rs.getDouble("temperatura"));
				movimientoDieselDTO.setAlarma(rs.getString("alarma"));
				movimientoDieselDTO.setAcumuladoTanque1(rs.getDouble("acumulado_tanque1"));
				movimientoDieselDTO.setAcumuladoTanque2(rs.getDouble("acumulado_tanque2"));
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
		return movimientoDieselDTO;
	}
	
	public EstadoBombasDTO buscarUltimoValorBombas() {
		EstadoBombasDTO estadoBombasDTO = new EstadoBombasDTO();

		Connection conn = null;

		try {
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from estado_bombas_diesel order by id desc limit 1");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				estadoBombasDTO.setBomba1(rs.getBoolean("bomba1"));
				estadoBombasDTO.setBomba2(rs.getBoolean("bomba2"));
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
		return estadoBombasDTO;
	}
}
