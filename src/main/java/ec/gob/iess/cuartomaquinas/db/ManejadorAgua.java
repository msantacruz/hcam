package ec.gob.iess.cuartomaquinas.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.gob.iess.cuartomaquinas.dto.AguaDTO;


public class ManejadorAgua {

	public List<AguaDTO> buscarEstadistica(int mes, int anio) {

		List<AguaDTO> lista = new ArrayList<AguaDTO>();

		Connection conn = null;

		try {
			conn = GestorConexion.obtenerConexion();
			PreparedStatement ps = conn
					.prepareStatement("select * from vapor where date_part('year',fecha) = ? and date_part('month',fecha) = ?");
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

}
