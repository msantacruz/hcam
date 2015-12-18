package ec.gob.iess.cuartomaquinas.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.gob.iess.cuartomaquinas.dto.ConsumoVaporDTO;
import ec.gob.iess.cuartomaquinas.dto.VaporDTO;

public class ManejadorVapor {

	public List<VaporDTO> buscarEstadistica(int mes, int anio) {

		List<VaporDTO> lista = new ArrayList<VaporDTO>();

		Connection conn = null;

		try {
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from vapor where date_part('year',fecha) = ? and date_part('month',fecha) = ?");
			ps.setInt(1, anio);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				VaporDTO vaporDTO = new VaporDTO();
				vaporDTO.setEstado(rs.getString("estado"));
				vaporDTO.setFecha(rs.getTimestamp("fecha"));
				vaporDTO.setFlujo(rs.getDouble("flujo"));
				vaporDTO.setPresion(rs.getDouble("presion"));
				vaporDTO.setTemperatura(rs.getDouble("temperatura"));
				vaporDTO.setValvula(rs.getString("valvula"));
				lista.add(vaporDTO);
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
	
	public List<ConsumoVaporDTO> buscarEstadistica1(int mes, int anio) {

		List<ConsumoVaporDTO> listaConsumo = new ArrayList<ConsumoVaporDTO>();
		
		
		Connection conn = null;

		try {
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from consumo_vapor where date_part('year',fecha) = ? and date_part('month',fecha) = ?");
			ps.setInt(1, anio);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ConsumoVaporDTO consumoVaporDTO = new ConsumoVaporDTO();
				consumoVaporDTO.setFecha(rs.getTimestamp("fecha"));
				consumoVaporDTO.setConsumo_total(rs.getDouble("consumo_total"));
				consumoVaporDTO.setConsumo_cb1(rs.getDouble("consumo_cb1"));
				consumoVaporDTO.setConsumo_tanques_agua_caliente(rs.getDouble("consumo_tanques_agua_caliente"));
				consumoVaporDTO.setConsumo_cocina(rs.getDouble("consumo_cocina"));
				consumoVaporDTO.setConsumo_patologia_laboratorio_central(rs.getDouble("consumo_patologia_laboratorio_central"));
				consumoVaporDTO.setConsumo_obstetricia(rs.getDouble("consumo_obstetricia"));
				consumoVaporDTO.setConsumo_biberones(rs.getDouble("consumo_biberones"));
				consumoVaporDTO.setConsumo_piscina(rs.getDouble("consumo_piscina"));
				consumoVaporDTO.setConsumo_quirofano_sala_partos(rs.getDouble("consumo_quirofano_sala_partos"));
				consumoVaporDTO.setConsumo_lavanderia_comedor(rs.getDouble("consumo_lavanderia_comedor"));
				consumoVaporDTO.setConsumo_lavanderia(rs.getDouble("consumo_lavanderia"));
				consumoVaporDTO.setConsumo_cb2_cb4(rs.getDouble("consumo_cb2_cb4"));
				consumoVaporDTO.setConsumo_cb3_cb5(rs.getDouble("consumo_cb3_cb5"));
				consumoVaporDTO.setConsumo_quemados(rs.getDouble("consumo_quemados"));
				listaConsumo.add(consumoVaporDTO);
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

}
