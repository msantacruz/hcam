package ec.gob.iess.cuartomaquinas.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.gob.iess.cuartomaquinas.db.ManejadorDiesel;
import ec.gob.iess.cuartomaquinas.dto.EstadoBombasDTO;

/**
 * Servlet implementation class ActualizarEstadoBombas
 */
@WebServlet("/ActualizarEstadoBombasDiesel")
public class ActualizarEstadoBombasDiesel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarEstadoBombasDiesel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		
		ManejadorDiesel manejadorDiesel =  new ManejadorDiesel();
		EstadoBombasDTO estadoBombasDTO = manejadorDiesel.buscarUltimoValorBombas();
		
		response.getWriter().write(new Gson().toJson(estadoBombasDTO));
	}
}
