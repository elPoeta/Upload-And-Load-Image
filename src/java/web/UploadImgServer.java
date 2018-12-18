package web;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Imagen;
import dao.ImagenDao;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author elpoeta
 */
@WebServlet(name = "UploadImgServer", urlPatterns = {"/UploadImgServer"})
public class UploadImgServer extends HttpServlet {

    final static Gson CONVERTIR = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {

            //   TreeMap parametro = CONVERTIR.fromJson(request.getParameter("q"), TreeMap.class);
            //     Integer id = Integer.parseInt(String.valueOf(parametro.get("id")));
            //Imagen imagenes = ImagenDao.getInstance().obtenerImagenes(id);
            ArrayList<Imagen> imagenes = ImagenDao.getInstance().obtenerImagenes();

            String resultado = CONVERTIR.toJson(imagenes);

            out.println("" + resultado);

        } catch (ClassNotFoundException ex) {
            out.println("Verificar:" + ex.getMessage());
        } catch (SQLException ex) {
            out.println("Verificar:" + ex.getMessage());
        } catch (Exception ex) {
            out.println("Verificar:" + ex.getMessage());
        } finally {
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String texto = request.getReader().readLine();

            ArrayList<String> imagenes = CONVERTIR.fromJson(texto, ArrayList.class);

            ImagenDao.getInstance();
            ImagenDao.insertar(imagenes);

            out.println(CONVERTIR.toJson("OK"));

        } catch (ClassNotFoundException ex) {
            out.println("Verificar: " + ex.getMessage());
            System.out.println("Class > " + ex.getMessage());
        } catch (SQLException ex) {
            out.println("Verificar:" + ex.getMessage());
            System.out.println("SQL > " + ex.getMessage());
        } catch (Exception ex) {
            out.println("Verificar:" + ex.getMessage());
            System.out.println("EXcep > " + ex.getMessage());
        } finally {
            out.close();
        }
    }

}
