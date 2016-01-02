package ec.gob.iess.cuartomaquinas.servlet.migracion;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ec.gob.iess.cuartomaquinas.db.ManejadorDiesel;
import ec.gob.iess.cuartomaquinas.dto.ReplicacionDatosDieselDTO;

/**
 * Servlet implementation class ReceptorDatosDiesel
 */
@WebServlet("/ReceptorDatosDiesel")
public class ReceptorDatosDiesel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReceptorDatosDiesel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  
		  Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		  ReplicacionDatosDieselDTO datosDiesel = gSon.fromJson(jb.toString(), ReplicacionDatosDieselDTO.class);
		  
		  ManejadorDiesel manejadorDiesel = new ManejadorDiesel();
		  manejadorDiesel.guardarRegistrosDatosDiesel(datosDiesel);
	}

}
