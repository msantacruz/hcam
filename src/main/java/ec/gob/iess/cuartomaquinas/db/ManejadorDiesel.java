package ec.gob.iess.cuartomaquinas.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.gob.iess.cuartomaquinas.dto.EstadisticaMovimientoDieselDTO;
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
	
	public List<EstadisticaMovimientoDieselDTO> buscarEstadistica(int mes, int anio){
		
		List<EstadisticaMovimientoDieselDTO>lista = new ArrayList<EstadisticaMovimientoDieselDTO>(); 

			Connection conn = null;

			try {
				conn = GestorConexion.obtenerConexion();
				PreparedStatement ps = conn
						.prepareStatement("select * from movimiento_diesel_estadistica where date_part('year',fecha) = ? and date_part('month',fecha) = ?");
				ps.setInt(1, anio);
				ps.setInt(2, mes);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					EstadisticaMovimientoDieselDTO estadisticaMovimientoDieselDTO = new EstadisticaMovimientoDieselDTO();
					estadisticaMovimientoDieselDTO.setFecha(rs.getTimestamp("fecha"));
					estadisticaMovimientoDieselDTO.setAcumuladoTanque1(rs.getDouble("acumulado_tanque1"));
					estadisticaMovimientoDieselDTO.setAcumuladoTanque2(rs.getDouble("acumulado_tanque2"));
					estadisticaMovimientoDieselDTO.setTotal(rs.getDouble("Total"));
					estadisticaMovimientoDieselDTO.setDescarga(rs.getDouble("descarga"));
					estadisticaMovimientoDieselDTO.setTemperatura(rs.getDouble("temperatura"));
					estadisticaMovimientoDieselDTO.setSalida(rs.getDouble("salida"));
					estadisticaMovimientoDieselDTO.setAlarma(rs.getString("alarma"));
					lista.add(estadisticaMovimientoDieselDTO);
					
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
			return lista;
		}
}
