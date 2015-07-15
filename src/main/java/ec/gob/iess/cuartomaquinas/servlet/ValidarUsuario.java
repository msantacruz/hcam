package ec.gob.iess.cuartomaquinas.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.gob.iess.cuartomaquinas.db.ManejadorUsuario;

/**
 * Servlet implementation class ValidarUsuario
 */
@WebServlet("/ValidarUsuario")
public class ValidarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String usuario = request.getParameter("usuario");
		String clave = request.getParameter("clave");
		
		ManejadorUsuario manejadorUsuario =  new ManejadorUsuario();
		boolean resultado = manejadorUsuario.validarUsuario(usuario, clave);
		
		response.getWriter().write(new Gson().toJson(resultado));
	}

}
