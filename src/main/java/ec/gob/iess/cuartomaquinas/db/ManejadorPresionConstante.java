package ec.gob.iess.cuartomaquinas.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ec.gob.iess.cuartomaquinas.dto.EstadoBombasDTO;
import ec.gob.iess.cuartomaquinas.dto.PresionFlujoDTO;
import ec.gob.iess.cuartomaquinas.dto.TipoAlarmaAguaDTO;

public class ManejadorPresionConstante {

	public PresionFlujoDTO buscarUltimoValor() {
		PresionFlujoDTO presionFlujoDTO = new PresionFlujoDTO();

		Connection conn = null;

		try {
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from presion_flujo order by id desc limit 1");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				presionFlujoDTO.setPresion(rs.getString("presion"));
				presionFlujoDTO.setFlujo(rs.getString("flujo"));
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
		return presionFlujoDTO;
	}
	
	public EstadoBombasDTO buscarUltimoValorBombas() {
		EstadoBombasDTO estadoBombasDTO = new EstadoBombasDTO();

		Connection conn = null;

		try {
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from estado_bombas order by id desc limit 1");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				estadoBombasDTO.setBomba1(rs.getBoolean("bomba1"));
				estadoBombasDTO.setBomba2(rs.getBoolean("bomba2"));
				estadoBombasDTO.setBomba3(rs.getBoolean("bomba3"));
				estadoBombasDTO.setAlarma(rs.getBoolean("alarma"));
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


public TipoAlarmaAguaDTO buscarUltimoValorTipoAlarma() {
	TipoAlarmaAguaDTO tipoAlarmaAguaDTO = new TipoAlarmaAguaDTO();

	Connection conn = null;

	try {
		conn = GestorConexion.obtenerConexion();
		PreparedStatement ps = conn
				.prepareStatement("select * from estado_bombas order by id desc limit 1");
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			tipoAlarmaAguaDTO.setBajapresion(rs.getBoolean("bajapresion"));
			tipoAlarmaAguaDTO.setAltapresion(rs.getBoolean("altapresion"));

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
	return tipoAlarmaAguaDTO;
}
}
