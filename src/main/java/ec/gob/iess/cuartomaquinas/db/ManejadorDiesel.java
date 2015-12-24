package ec.gob.iess.cuartomaquinas.db;

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
	
	/*public EstadoBombasDTO buscarUltimoValorBombas() {
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
	}*/
	
	public List<EstadisticaMovimientoDieselDTO> buscarEstadistica(int mes, int anio){
		
		List<EstadisticaMovimientoDieselDTO>lista = new ArrayList<EstadisticaMovimientoDieselDTO>(); 

			Connection conn = null;

			try {
				conn = GestorConexion.obtenerConexion();
				PreparedStatement ps = conn
						.prepareStatement("select * from movimiento_diesel where date_part('year',fecha) = ? and date_part('month',fecha) = ?");
				ps.setInt(1, anio);
				ps.setInt(2, mes);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					EstadisticaMovimientoDieselDTO estadisticaMovimientoDieselDTO = new EstadisticaMovimientoDieselDTO();
					estadisticaMovimientoDieselDTO.setFecha(rs.getTimestamp("fecha"));
					estadisticaMovimientoDieselDTO.setAcumuladoTanque1(rs.getDouble("acumulado_tanque1"));
					estadisticaMovimientoDieselDTO.setAcumuladoTanque2(rs.getDouble("acumulado_tanque2"));
					estadisticaMovimientoDieselDTO.setTotal(rs.getDouble("acumulado_tanque1") + rs.getDouble("acumulado_tanque2"));
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
	public List<ConsumoDieselDTO> buscarConsumo(int mes, int anio){
		
		List<ConsumoDieselDTO>lista = new ArrayList<ConsumoDieselDTO>(); 

			Connection conn = null;

			try {
				conn = GestorConexion.obtenerConexion();

				Calendar mycal = new GregorianCalendar(anio, mes, 1);

				int diasDelMes = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
				
				for(int i=1; i<+diasDelMes; i++) {
					PreparedStatement ps = conn
							.prepareStatement("select * from movimiento_diesel where date_part('year',fecha) = ? "
									+ " and date_part('month',fecha) = ? and date_part('day',fecha) = ? order by fecha desc limit 1");
					ps.setInt(1, anio);
					ps.setInt(2, mes);
					ps.setInt(3, i);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						ConsumoDieselDTO consumoDieselDTO = new ConsumoDieselDTO();
						consumoDieselDTO.setFecha(rs.getTimestamp("fecha"));
						consumoDieselDTO.setTotal(rs.getDouble("acumulado_tanque1") + rs.getDouble("acumulado_tanque2"));
						lista.add(consumoDieselDTO);
					}	
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
	public List<ConsumoMesDieselDTO> buscarEstadistica2 (int mes, int anio){
		   List<ConsumoMesDieselDTO> listaConsumoMes = new ArrayList<ConsumoMesDieselDTO> ();
		   Connection conn = null;
		try{
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from consumo_mes_diesel where date_part('year',fecha) = ?  and date_part('month',fecha) = ?  ");
			ps.setInt(1, anio);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				ConsumoMesDieselDTO consumoMesDieselDTO = new ConsumoMesDieselDTO();
				consumoMesDieselDTO.setFecha(rs.getTimestamp("fecha"));
				consumoMesDieselDTO.setConsumo_total_mes(rs.getDouble("consumo_total_mes"));
				listaConsumoMes.add(consumoMesDieselDTO);
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaConsumoMes;
	}
}
