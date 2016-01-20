package ec.gob.iess.cuartomaquinas.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



import ec.gob.iess.cuartomaquinas.dto.ConsumoDieselDTO;
import ec.gob.iess.cuartomaquinas.dto.ConsumoMesDieselDTO;
//import ec.gob.iess.cuartomaquinas.dto.EstadoBombasDTO;
import ec.gob.iess.cuartomaquinas.dto.MovimientoDieselDTO;
import ec.gob.iess.cuartomaquinas.dto.ReplicacionConsumoDieselDTO;
import ec.gob.iess.cuartomaquinas.dto.ReplicacionDatosDieselDTO;

public class ManejadorDiesel {

	public MovimientoDieselDTO buscarUltimoValor() {
		MovimientoDieselDTO movimientoDieselDTO = new MovimientoDieselDTO();

		Connection conn = null;

		try {
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from datos_plc_diesel order by id desc limit 1");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				movimientoDieselDTO.setFecha(rs.getTimestamp("fecha"));
				movimientoDieselDTO.setTemperatura(rs.getInt("temperatura"));
				movimientoDieselDTO.setBajo_tanque1(rs.getInt("bajo_tanque1"));
				movimientoDieselDTO.setAlto_tanque1(rs.getInt("alto_tanque1"));
				movimientoDieselDTO.setBajo_tanque2(rs.getInt("bajo_tanque2"));
				movimientoDieselDTO.setAlto_tanque2(rs.getInt("alto_tanque2"));
				movimientoDieselDTO.setPulsos_entrada(rs.getDouble("pulsos_entrada"));
				movimientoDieselDTO.setBomba_ingreso(rs.getInt("bomba_ingreso"));
				movimientoDieselDTO.setFrecuencia_entrada(rs.getInt("frecuencia_entrada"));
				movimientoDieselDTO.setBomba_tdiario(rs.getInt("bomba_tdiario"));
				movimientoDieselDTO.setGalones_salida(rs.getInt("galones_salida"));
				movimientoDieselDTO.setFracc_galonsalida(rs.getInt("fracc_galonsalida"));
				movimientoDieselDTO.setFrecuencia_salida(rs.getInt("frecuencia_salida"));
				movimientoDieselDTO.setFlujo_salida(rs.getInt("flujo_salida"));
				movimientoDieselDTO.setFracc_flujosalida(rs.getInt("fracc_flujosalida"));
				movimientoDieselDTO.setGalones_entrada(rs.getInt("galones_entrada"));
				movimientoDieselDTO.setFracc_galonentrada(rs.getInt("fracc_galonentrada"));
				movimientoDieselDTO.setFlujo_entrada(rs.getInt("flujo_entrada"));
				movimientoDieselDTO.setFracc_flujoentrada(rs.getInt("fracc_flujoentrada"));
				movimientoDieselDTO.setParo_emergencia(rs.getInt("paro_emergencia"));
				movimientoDieselDTO.setInicio_galont2(rs.getInt("inicio_galont2"));
				movimientoDieselDTO.setInicio_fraccgalont2(rs.getInt("inicio_fraccgalont2"));
				movimientoDieselDTO.setTotal_galont1(rs.getInt("total_galont1"));
				movimientoDieselDTO.setTotal_fraccgalont1(rs.getInt("total_fraccgalont1"));
				movimientoDieselDTO.setTotal_galont2(rs.getInt("total_galont2"));
				movimientoDieselDTO.setTotal_fraccgalont2(rs.getInt("total_fraccgalont2"));
				movimientoDieselDTO.setPedido_tanque(rs.getInt("pedido_tanque"));
				movimientoDieselDTO.setTanque_uso(rs.getInt("tanque_uso"));
				movimientoDieselDTO.setModo(rs.getInt("modo"));
				BigDecimal entero = rs.getBigDecimal("flujo_entrada");
				BigDecimal fraccion = rs.getBigDecimal("fracc_flujoentrada");
				fraccion = fraccion.divide(new BigDecimal (100));
				movimientoDieselDTO.setValor_flujo_entrada(entero.add(fraccion));
				entero = rs.getBigDecimal("flujo_salida");
				fraccion = rs.getBigDecimal("fracc_flujosalida");
				fraccion = fraccion.divide(new BigDecimal (100));
				movimientoDieselDTO.setValor_flujo_salida(entero.add(fraccion));
				entero = rs.getBigDecimal("galones_salida");
				fraccion = rs.getBigDecimal("fracc_galonsalida");
				fraccion = fraccion.divide(new BigDecimal (100));
				movimientoDieselDTO.setValor_total_salida(entero.add(fraccion));
				entero = rs.getBigDecimal("galones_entrada");
				fraccion = rs.getBigDecimal("fracc_galonentrada");
				fraccion = fraccion.divide(new BigDecimal (100));
				movimientoDieselDTO.setValor_total_entrada(entero.add(fraccion));
				entero = rs.getBigDecimal("total_galont1");
				fraccion = rs.getBigDecimal("total_fraccgalont1");
				fraccion = fraccion.divide(new BigDecimal (100));
				movimientoDieselDTO.setValor_total_tanque1(entero.add(fraccion));
				entero = rs.getBigDecimal("total_galont2");
				fraccion = rs.getBigDecimal("total_fraccgalont2");
				fraccion = fraccion.divide(new BigDecimal (100));
				movimientoDieselDTO.setValor_total_tanque2(entero.add(fraccion));
				//entero = rs.getBigDecimal("valor_total_tanque1");
				//fraccion = rs.getBigDecimal("valor_total_tanque2");
				movimientoDieselDTO.setValor_total_acumulado(movimientoDieselDTO.getValor_total_tanque1().add(movimientoDieselDTO.getValor_total_tanque2()));
				//movimientoDieselDTO.setValor_total_acumulado(entero.add(fraccion));
				entero = rs.getBigDecimal("temperatura");
				fraccion = entero.divide(new BigDecimal (10));
				movimientoDieselDTO.setValor_total_temperatura(fraccion);
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
	

	
	public List<MovimientoDieselDTO> buscarEstadisticaIngresos (int mes, int anio){
		List<MovimientoDieselDTO> lista = new ArrayList<MovimientoDieselDTO>();
		Connection conn = null;
		
		try{
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from datos_plc_diesel where date_part('year',fecha) = ? and date_part('month',fecha) = ? and bomba_ingreso = 10 order by fecha");
			ps.setInt(1, anio);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				MovimientoDieselDTO movimientoDieselDTO = new MovimientoDieselDTO();
				movimientoDieselDTO.setFecha(rs.getTimestamp("fecha"));
				movimientoDieselDTO.setTemperatura(rs.getInt("temperatura"));
				BigDecimal entero = rs.getBigDecimal("flujo_entrada");
				BigDecimal fraccion = rs.getBigDecimal("fracc_flujoentrada");
				fraccion = fraccion.divide(new BigDecimal (100));
				movimientoDieselDTO.setValor_flujo_entrada(entero.add(fraccion));;
				entero = rs.getBigDecimal("galones_entrada");
				fraccion = rs.getBigDecimal("fracc_galonentrada");
				fraccion = fraccion.divide(new BigDecimal (100));
				movimientoDieselDTO.setValor_total_entrada(entero.add(fraccion));
				movimientoDieselDTO.setPedido_tanque(rs.getInt("pedido_tanque"));
				entero = rs.getBigDecimal("temperatura");
				fraccion = entero.divide(new BigDecimal (10));
				movimientoDieselDTO.setValor_total_temperatura(fraccion);
				lista.add(movimientoDieselDTO);
			}
		}catch (SQLException e) {
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
	public List<MovimientoDieselDTO> buscarEstadisticaSalidas (int mes, int anio){
		List<MovimientoDieselDTO> lista = new ArrayList<MovimientoDieselDTO>();
		Connection conn = null;
		
		try{
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from datos_plc_diesel where date_part('year',fecha) = ? and date_part('month',fecha) = ? and bomba_tdiario = 10 order by fecha");
			ps.setInt(1, anio);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				MovimientoDieselDTO movimientoDieselDTO = new MovimientoDieselDTO();
				movimientoDieselDTO.setFecha(rs.getTimestamp("fecha"));
				BigDecimal entero = rs.getBigDecimal("flujo_salida");
				BigDecimal fraccion = rs.getBigDecimal("fracc_flujosalida");
				fraccion = fraccion.divide(new BigDecimal (100));
				movimientoDieselDTO.setValor_flujo_salida(entero.add(fraccion));
				entero = rs.getBigDecimal("galones_salida");
				fraccion = rs.getBigDecimal("fracc_galonsalida");
				fraccion = fraccion.divide(new BigDecimal (100));
				movimientoDieselDTO.setValor_total_salida(entero.add(fraccion));
				movimientoDieselDTO.setTanque_uso(rs.getInt("tanque_uso"));
				lista.add(movimientoDieselDTO);
			}
		}catch (SQLException e) {
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
	public List<MovimientoDieselDTO> buscarEstadisticaAlarmas (int mes, int anio){
		List<MovimientoDieselDTO> lista = new ArrayList<MovimientoDieselDTO>();
		Connection conn = null;
		
		try{
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from datos_plc_diesel where date_part('year',fecha) = ? and date_part('month',fecha) = ? and (bajo_tanque1 = 10 or bajo_tanque2 = 10) order by fecha");
			ps.setInt(1, anio);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				MovimientoDieselDTO movimientoDieselDTO = new MovimientoDieselDTO();
				movimientoDieselDTO.setFecha(rs.getTimestamp("fecha"));
				BigDecimal entero = rs.getBigDecimal("total_galont1");
				BigDecimal fraccion = rs.getBigDecimal("total_fraccgalont1");
				fraccion = fraccion.divide(new BigDecimal (100));
				movimientoDieselDTO.setValor_total_tanque1(entero.add(fraccion));
				entero = rs.getBigDecimal("total_galont2");
				fraccion = rs.getBigDecimal("total_fraccgalont2");
				fraccion = fraccion.divide(new BigDecimal (100));
				movimientoDieselDTO.setValor_total_tanque2(entero.add(fraccion));
				entero = rs.getBigDecimal("total_galont1");
				fraccion = rs.getBigDecimal("total_fraccgalont1");
				fraccion = fraccion.divide(new BigDecimal (100));
				movimientoDieselDTO.setValor_total_tanque1(entero.add(fraccion));
				entero = rs.getBigDecimal("total_galont2");
				fraccion = rs.getBigDecimal("total_fraccgalont2");
				fraccion = fraccion.divide(new BigDecimal (100));
				movimientoDieselDTO.setValor_total_tanque2(entero.add(fraccion));
				movimientoDieselDTO.setValor_total_acumulado(movimientoDieselDTO.getValor_total_tanque1().add(movimientoDieselDTO.getValor_total_tanque2()));
				movimientoDieselDTO.setBajo_tanque1(rs.getInt("bajo_tanque1"));
				movimientoDieselDTO.setBajo_tanque2(rs.getInt("bajo_tanque2"));
				movimientoDieselDTO.setAlto_tanque1(rs.getInt("alto_tanque1"));
				movimientoDieselDTO.setAlto_tanque2(rs.getInt("alto_tanque2"));
				movimientoDieselDTO.setParo_emergencia(rs.getInt("Paro_emergencia"));
				lista.add(movimientoDieselDTO);
			}
		}catch (SQLException e) {
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
	
	public Boolean guardarRegistrosConsumoDiesel(List<ReplicacionConsumoDieselDTO> lista) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement psVerificacion = null;
		PreparedStatement psUpdate = null;
		ResultSet rs = null;
		try {
			conn = GestorConexion.obtenerConexion();
			psVerificacion =  conn.prepareStatement("select * from consumo_diesel where id=?");
			ps = conn
					.prepareStatement("INSERT INTO consumo_diesel(id, fecha, consumo)"
							+ " VALUES (?, ?, ?)");
			psUpdate = conn
					.prepareStatement("update consumo_diesel set fecha=?, consumo=? where id=?");
			for(ReplicacionConsumoDieselDTO replicDiesel: lista) {
				psVerificacion.setLong(1, replicDiesel.getId());
				rs = psVerificacion.executeQuery();
				if (rs.next()) {
					psUpdate.setLong(3, replicDiesel.getId());
					psUpdate.setTimestamp(1, new Timestamp(replicDiesel.getFecha().getTime()));
					psUpdate.setBigDecimal(2, replicDiesel.getConsumo());
					psUpdate.executeUpdate();	
				} else {
					ps.setLong(1, replicDiesel.getId());
					ps.setTimestamp(2, new Timestamp(replicDiesel.getFecha().getTime()));
					ps.setBigDecimal(3, replicDiesel.getConsumo());
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
	
	public Boolean guardarRegistrosConsumoMesDiesel(List<ReplicacionConsumoDieselDTO> lista) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement psVerificacion = null;
		PreparedStatement psUpdate = null;
		ResultSet rs = null;
		try {
			conn = GestorConexion.obtenerConexion();
			psVerificacion =  conn.prepareStatement("select * from consumo_mes_diesel where id=?");
			ps = conn
					.prepareStatement("INSERT INTO consumo_mes_diesel(id, fecha, consumo_total_mes)"
							+ " VALUES (?, ?, ?)");
			psUpdate = conn
					.prepareStatement("update consumo_mes_diesel set fecha=?, consumo_total_mes=? where id=?");
			for(ReplicacionConsumoDieselDTO replicDiesel: lista) {
				psVerificacion.setLong(1, replicDiesel.getId());
				rs = psVerificacion.executeQuery();
				if (rs.next()) {
					psUpdate.setLong(3, replicDiesel.getId());
					psUpdate.setTimestamp(1, new Timestamp(replicDiesel.getFecha().getTime()));
					psUpdate.setBigDecimal(2, replicDiesel.getConsumo());
					psUpdate.executeUpdate();
				} else {
					ps.setLong(1, replicDiesel.getId());
					ps.setTimestamp(2, new Timestamp(replicDiesel.getFecha().getTime()));
					ps.setBigDecimal(3, replicDiesel.getConsumo());
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
	
	public List<ConsumoDieselDTO> buscarEstadisticaConsumo(int mes, int anio) {

		List<ConsumoDieselDTO> listaConsumo = new ArrayList<ConsumoDieselDTO>();

		Connection conn = null;

		try {
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from consumo_diesel where date_part('year',fecha) = ? and date_part('month',fecha) = ?");
			ps.setInt(1, anio);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ConsumoDieselDTO consumoDieselDTO = new ConsumoDieselDTO();
				consumoDieselDTO.setFecha(rs.getTimestamp("fecha"));
				consumoDieselDTO.setConsumo(rs.getDouble("consumo"));
				listaConsumo.add(consumoDieselDTO);
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
	
	public List<ConsumoMesDieselDTO> buscarEstadisticaConsumoMes(int anio) {
		List<ConsumoMesDieselDTO> listaConsumoMes = new ArrayList<ConsumoMesDieselDTO>();
		Connection conn = null;
		try {
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from consumo_mes_diesel where date_part('year',fecha) = ?");
			ps.setInt(1, anio);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ConsumoMesDieselDTO consumoMesDieselDTO = new ConsumoMesDieselDTO();
				consumoMesDieselDTO.setFecha(rs.getTimestamp("fecha"));
				consumoMesDieselDTO.setConsumo_total_mes(rs
						.getDouble("consumo_total_mes"));
				listaConsumoMes.add(consumoMesDieselDTO);
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

	public Boolean guardarRegistrosDatosDiesel(List<ReplicacionDatosDieselDTO> lista) {
		
		Connection conn = null;
		PreparedStatement psInsert = null;
		PreparedStatement psSelect = null;
		ResultSet rs = null;
		
		try {
			conn = GestorConexion.obtenerConexion();
			psSelect = conn.prepareStatement("select * from datos_plc_diesel where id = ?");
			
			psInsert = conn.prepareStatement("INSERT INTO datos_plc_diesel(id, fecha, temperatura, bajo_tanque1, alto_tanque1, bajo_tanque2, "
					+ "alto_tanque2, pulsos_entrada, bomba_ingreso, frecuencia_entrada,bomba_tdiario, galones_salida, fracc_galonsalida, frecuencia_salida, "
					+ "flujo_salida, fracc_flujosalida, galones_entrada, fracc_galonentrada, flujo_entrada, fracc_flujoentrada, paro_emergencia, inicio_galont1, "
					+ "inicio_fraccgalont1, inicio_galont2, inicio_fraccgalont2, total_galont1, total_fraccgalont1, total_galont2, total_fraccgalont2, pedido_tanque, "
					+ "tanque_uso, modo, reset_ingreso, consumo, fracc_consumo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			for (ReplicacionDatosDieselDTO datosDiesel : lista) {
				psSelect.setLong(1, datosDiesel.getId());
				rs = psSelect.executeQuery();
				if (!rs.next()) {
					psInsert.setLong(1, datosDiesel.getId());
					psInsert.setTimestamp(2, new Timestamp(datosDiesel.getFecha().getTime()));
					psInsert.setInt(3, datosDiesel.getTemperatura());
					psInsert.setInt(4, datosDiesel.getBajo_tanque1());
					psInsert.setInt(5, datosDiesel.getAlto_tanque1());
					psInsert.setInt(6, datosDiesel.getBajo_tanque2());
					psInsert.setInt(7, datosDiesel.getAlto_tanque2());
					psInsert.setDouble(8, datosDiesel.getPulsos_entrada());
					psInsert.setInt(9, datosDiesel.getBomba_ingreso());
					psInsert.setInt(10, datosDiesel.getFrecuencia_entrada());
					psInsert.setInt(11, datosDiesel.getBomba_tdiario());
					psInsert.setInt(12, datosDiesel.getGalones_salida());
					psInsert.setInt(13, datosDiesel.getFracc_galonsalida());
					psInsert.setInt(14, datosDiesel.getFrecuencia_salida());
					psInsert.setInt(15, datosDiesel.getFlujo_salida());
					psInsert.setInt(16, datosDiesel.getFracc_flujosalida());
					psInsert.setInt(17, datosDiesel.getGalones_entrada());
					psInsert.setInt(18, datosDiesel.getFracc_galonentrada());
					psInsert.setInt(19, datosDiesel.getFlujo_entrada());
					psInsert.setInt(20, datosDiesel.getFracc_flujoentrada());
					psInsert.setInt(21, datosDiesel.getParo_emergencia());
					psInsert.setInt(22, datosDiesel.getInicio_galont1());
					psInsert.setInt(23, datosDiesel.getInicio_fraccgalont1());
					psInsert.setInt(24, datosDiesel.getInicio_galont2());
					psInsert.setInt(25, datosDiesel.getInicio_fraccgalont2());
					psInsert.setInt(26, datosDiesel.getTotal_galont1());
					psInsert.setInt(27, datosDiesel.getTotal_fraccgalont1());
					psInsert.setInt(28, datosDiesel.getTotal_galont2());
					psInsert.setInt(29, datosDiesel.getTotal_fraccgalont2());
					psInsert.setInt(30, datosDiesel.getPedido_tanque());
					psInsert.setInt(31, datosDiesel.getTanque_uso());
					psInsert.setInt(32, datosDiesel.getModo());
					psInsert.setInt(33, datosDiesel.getResetIngreso());
					psInsert.setInt(34, datosDiesel.getConsumo());
					psInsert.setInt(35, datosDiesel.getFraccConsumo());
					
					psInsert.execute();
				}
				
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psInsert != null) 
					psInsert.close();
				if (psSelect != null)
					psSelect.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) { }
		}		
	}
}
