package ec.gob.iess.cuartomaquinas.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

}
