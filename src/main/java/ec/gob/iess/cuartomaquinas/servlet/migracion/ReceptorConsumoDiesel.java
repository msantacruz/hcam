package ec.gob.iess.cuartomaquinas.servlet.migracion;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ec.gob.iess.cuartomaquinas.db.ManejadorDiesel;
import ec.gob.iess.cuartomaquinas.dto.ReplicacionConsumoDieselDTO;

/**
 * Servlet implementation class ReceptorConsumoDiesel
 */
@WebServlet("/ReceptorConsumoDiesel")
public class ReceptorConsumoDiesel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReceptorConsumoDiesel() {
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
		  
		  Type listType = new TypeToken<ArrayList<ReplicacionConsumoDieselDTO>>() { }.getType();
          
		  Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		  List<ReplicacionConsumoDieselDTO> lista = gSon.fromJson(jb.toString(), listType);
		  
		  ManejadorDiesel manejadorDiesel = new ManejadorDiesel();
		  Boolean res = manejadorDiesel.guardarRegistrosConsumoDiesel(lista);
		  if (!res) {
			  response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		  }
	}

}
