package ec.gob.iess.cuartomaquinas.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ec.gob.iess.cuartomaquinas.dto.AguaDTO;
import ec.gob.iess.cuartomaquinas.dto.ConsumoAguaDTO;
import ec.gob.iess.cuartomaquinas.dto.ConsumoMesAguaDTO;
import ec.gob.iess.cuartomaquinas.dto.EstadoBombasDTO;
import ec.gob.iess.cuartomaquinas.dto.PresionFlujoDTO;
import ec.gob.iess.cuartomaquinas.dto.ReplicacionAguaDTO;
import ec.gob.iess.cuartomaquinas.dto.ReplicacionConsumoAguaDTO;

public class ManejadorAgua {

	public List<AguaDTO> buscarEstadistica(int mes, int anio) {

		List<AguaDTO> lista = new ArrayList<AguaDTO>();

		Connection conn = null;

		try {
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from agua where date_part('year',fecha) = ? and date_part('month',fecha) = ?");
			ps.setInt(1, anio);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AguaDTO aguaDTO = new AguaDTO();
				aguaDTO.setFecha(rs.getTimestamp("fecha"));
				aguaDTO.setPresion(rs.getDouble("presion"));
				aguaDTO.setFlujo(rs.getDouble("flujo"));
				aguaDTO.setBomba_1(rs.getString("bomba_1"));
				aguaDTO.setBomba_2(rs.getString("bomba_2"));
				aguaDTO.setBomba_3(rs.getString("bomba_3"));
				aguaDTO.setAlarma(rs.getString("alarma"));
				lista.add(aguaDTO);
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

	public List<ConsumoAguaDTO> buscarEstadistica1(int mes, int anio) {

		List<ConsumoAguaDTO> listaConsumo = new ArrayList<ConsumoAguaDTO>();

		Connection conn = null;

		try {
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from consumo_agua where date_part('year',fecha) = ? and date_part('month',fecha) = ?");
			ps.setInt(1, anio);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ConsumoAguaDTO consumoAguaDTO = new ConsumoAguaDTO();
				consumoAguaDTO.setFecha(rs.getTimestamp("fecha"));
				consumoAguaDTO.setConsumo(rs.getDouble("consumo"));
				listaConsumo.add(consumoAguaDTO);
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
		return listaConsumo;
	}

	public List<ConsumoMesAguaDTO> buscarEstadistica2(int anio) {
		List<ConsumoMesAguaDTO> listaConsumoMes = new ArrayList<ConsumoMesAguaDTO>();
		Connection conn = null;
		try {
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from consumo_mes_agua where date_part('year',fecha) = ?");
			ps.setInt(1, anio);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ConsumoMesAguaDTO consumoMesAguaDTO = new ConsumoMesAguaDTO();
				consumoMesAguaDTO.setFecha(rs.getTimestamp("fecha"));
				consumoMesAguaDTO.setConsumo_total_mes(rs
						.getDouble("consumo_total_mes"));
				listaConsumoMes.add(consumoMesAguaDTO);
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
		return listaConsumoMes;
	}

	public Boolean guardarRegistrosAgua(List<ReplicacionAguaDTO> lista) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = GestorConexion.obtenerConexion();
			ps = conn
					.prepareStatement("INSERT INTO agua(id, fecha, presion, flujo, bomba_1, bomba_2, bomba_3, alarma)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			for(ReplicacionAguaDTO replicAgua: lista) {
				ps.setLong(1, replicAgua.getId());
				ps.setTimestamp(2, new Timestamp(replicAgua.getFecha().getTime()));
				ps.setBigDecimal(3, replicAgua.getPresion());
				ps.setBigDecimal(4, replicAgua.getFlujo());
				ps.setString(5, replicAgua.getBomba1());
				ps.setString(6, replicAgua.getBomba2());
				ps.setString(7, replicAgua.getBomba3());
				ps.setString(8, replicAgua.getAlarma());
				ps.executeUpdate();	
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (ps != null) 
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Boolean guardarRegistrosConsumoAgua(List<ReplicacionConsumoAguaDTO> lista) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement psVerificacion = null;
		PreparedStatement psUpdate = null;
		ResultSet rs = null;
		try {
			conn = GestorConexion.obtenerConexion();
			psVerificacion =  conn.prepareStatement("select * from consumo_agua where id=?");
			ps = conn
					.prepareStatement("INSERT INTO consumo_agua(id, fecha, consumo)"
							+ " VALUES (?, ?, ?)");
			psUpdate = conn
					.prepareStatement("update consumo_agua set fecha=?, consumo=? where id=?");
			for(ReplicacionConsumoAguaDTO replicAgua: lista) {
				psVerificacion.setLong(1, replicAgua.getId());
				rs = psVerificacion.executeQuery();
				if (rs.next()) {
					psUpdate.setLong(3, replicAgua.getId());
					psUpdate.setTimestamp(1, new Timestamp(replicAgua.getFecha().getTime()));
					psUpdate.setBigDecimal(2, replicAgua.getConsumo());
					psUpdate.executeUpdate();	
				} else {
					ps.setLong(1, replicAgua.getId());
					ps.setTimestamp(2, new Timestamp(replicAgua.getFecha().getTime()));
					ps.setBigDecimal(3, replicAgua.getConsumo());
					ps.executeUpdate();	
				}
					
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (ps != null) 
					ps.close();
				if (psVerificacion != null) 
					psVerificacion.close();
				if (psUpdate != null) 
					psUpdate.close();
				if (rs != null) 
					rs.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Boolean guardarRegistrosConsumoMesAgua(List<ReplicacionConsumoAguaDTO> lista) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement psVerificacion = null;
		PreparedStatement psUpdate = null;
		ResultSet rs = null;
		try {
			conn = GestorConexion.obtenerConexion();
			psVerificacion =  conn.prepareStatement("select * from consumo_mes_agua where id=?");
			ps = conn
					.prepareStatement("INSERT INTO consumo_mes_agua(id, fecha, consumo_total_mes)"
							+ " VALUES (?, ?, ?)");
			psUpdate = conn
					.prepareStatement("update consumo_mes_agua set fecha=?, consumo_total_mes=? where id=?");
			for(ReplicacionConsumoAguaDTO replicAgua: lista) {
				psVerificacion.setLong(1, replicAgua.getId());
				rs = psVerificacion.executeQuery();
				if (rs.next()) {
					psUpdate.setLong(3, replicAgua.getId());
					psUpdate.setTimestamp(1, new Timestamp(replicAgua.getFecha().getTime()));
					psUpdate.setBigDecimal(2, replicAgua.getConsumo());
					psUpdate.executeUpdate();
				} else {
					ps.setLong(1, replicAgua.getId());
					ps.setTimestamp(2, new Timestamp(replicAgua.getFecha().getTime()));
					ps.setBigDecimal(3, replicAgua.getConsumo());
					ps.executeUpdate();	
				}
					
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (ps != null) 
					ps.close();
				if (psVerificacion != null) 
					psVerificacion.close();
				if (psUpdate != null) 
					psUpdate.close();
				if (rs != null) 
					rs.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void guardarRegistroPresion(PresionFlujoDTO presion) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement psBorrarAnteriores = null;
		PreparedStatement psVerificacion = null;
		ResultSet rs = null;
		try {
			conn = GestorConexion.obtenerConexion();
			ps = conn
					.prepareStatement("INSERT INTO presion_flujo(id, fecha, presion, flujo)"
							+ " VALUES (?, ?, ?, ?)");
			
			psVerificacion =  conn.prepareStatement("select * from presion_flujo where id=?");
			
			psVerificacion.setLong(1, presion.getId());
			rs = psVerificacion.executeQuery();
			if (!rs.next()) {
				ps.setLong(1, presion.getId());
				ps.setTimestamp(2, new Timestamp(presion.getFecha().getTime()));
				ps.setString(3, presion.getPresion());
				ps.setString(4, presion.getFlujo());
				ps.executeUpdate();	
				
				psBorrarAnteriores = conn.prepareStatement("delete from presion_flujo where id <> ?");
				psBorrarAnteriores.setLong(1, presion.getId());
				psBorrarAnteriores.executeUpdate();
	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) 
					ps.close();
				if (psVerificacion != null) 
					psVerificacion.close();
				if (rs != null) 
					rs.close();
				if (psBorrarAnteriores != null)
					psBorrarAnteriores.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void guardarRegistroEstadoBombas(EstadoBombasDTO estadoBombas) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement psBorrarAnteriores = null;
		PreparedStatement psVerificacion = null;
		ResultSet rs = null;
		try {
			conn = GestorConexion.obtenerConexion();
			ps = conn
					.prepareStatement("INSERT INTO estado_bombas(id, fecha, bomba1, bomba2, bomba3, alarma, bajapresion, altapresion)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
			psVerificacion =  conn.prepareStatement("select * from estado_bombas where id=?");
			
			psVerificacion.setLong(1, estadoBombas.getId());
			rs = psVerificacion.executeQuery();
			if (!rs.next()) {
				ps.setLong(1, estadoBombas.getId());
				ps.setTimestamp(2, new Timestamp(estadoBombas.getFecha().getTime()));
				ps.setBoolean(3, estadoBombas.isBomba1());
				ps.setBoolean(4, estadoBombas.isBomba2());
				ps.setBoolean(5, estadoBombas.isBomba3());
				ps.setBoolean(6, estadoBombas.isAlarma());
				ps.setBoolean(7, estadoBombas.isBajaPresion());
				ps.setBoolean(8, estadoBombas.isAltaPresion());
				ps.executeUpdate();	
				
				psBorrarAnteriores = conn.prepareStatement("delete from estado_bombas where id <> ?");
				psBorrarAnteriores.setLong(1, estadoBombas.getId());
				psBorrarAnteriores.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) 
					ps.close();
				if (psVerificacion != null) 
					psVerificacion.close();
				if (rs != null) 
					rs.close();
				if (psBorrarAnteriores != null)
					psBorrarAnteriores.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
