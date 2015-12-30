package ec.gob.iess.cuartomaquinas.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ec.gob.iess.cuartomaquinas.dto.ConsumoDieselDTO;
import ec.gob.iess.cuartomaquinas.dto.ConsumoMesDieselDTO;
import ec.gob.iess.cuartomaquinas.dto.EstadisticaMovimientoDieselDTO;
//import ec.gob.iess.cuartomaquinas.dto.EstadoBombasDTO;
import ec.gob.iess.cuartomaquinas.dto.MovimientoDieselDTO;

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
				entero = rs.getBigDecimal("valor_total_tanque1");
				fraccion = rs.getBigDecimal("valor_total_tanque2");
				movimientoDieselDTO.setValor_total_acumulado(entero.add(fraccion));
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
					.prepareStatement("select * from datos_plc_diesel where date_part('year',fecha) = ? and date_part('month',fecha) = ? and bomba_ingreso = 10");
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
					.prepareStatement("select * from datos_plc_diesel where date_part('year',fecha) = ? and date_part('month',fecha) = ? and bomba_tdiario = 10");
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
					.prepareStatement("select * from datos_plc_diesel where date_part('year',fecha) = ? and date_part('month',fecha) = ? and (bajo_tanque1 = 10 or bajo_tanque2 = 10)");
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
	
	

}
