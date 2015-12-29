package ec.gob.iess.cuartomaquinas.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.gob.iess.cuartomaquinas.db.ManejadorVapor;
import ec.gob.iess.cuartomaquinas.dto.VaporDTO;

/**
 * Servlet implementation class ActualizarPresion
 */
@WebServlet("/ActualizarVapor")

public class ActualizarVapor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActualizarVapor(){
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		ManejadorVapor manejadorVapor = new ManejadorVapor();
		List<VaporDTO> lista = manejadorVapor.buscarUltimoValor();
		response.getWriter().write(new Gson().toJson(lista));
	}

}
