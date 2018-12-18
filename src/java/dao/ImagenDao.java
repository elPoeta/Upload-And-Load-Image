package dao;

import db.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Imagen;

/**
 *
 * @author elpoeta
 */
public class ImagenDao {

    private static ImagenDao INSTANCE = null;
    private final static String SQL_IMAGENES_SELECT = "SELECT * FROM imagenes;";
    //private final static String SQL_IMAGEN_SELECT = "SELECT * FROM imagenes WHERE id = ?;";
    private final static String SQL_IMAGEN_INSERT = "INSERT INTO imagenes (img)values(?);";

    private ImagenDao() throws ClassNotFoundException,
            IOException, SQLException {

    }

    public static ImagenDao getInstance() throws ClassNotFoundException,
            IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new ImagenDao();
        }
        return INSTANCE;
    }

    public ArrayList<Imagen> obtenerImagenes() throws ClassNotFoundException, IOException, SQLException {
        ArrayList<Imagen> imagenes = new ArrayList();
        Connection conexion = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        try {
            conexion = Conexion.getInstance().getConnection();
            ptsmt = conexion.prepareStatement(SQL_IMAGENES_SELECT);
            rs = ptsmt.executeQuery();
            Imagen imagen = null;
            while (rs.next()) {
                try {
                    imagen = new Imagen();
                    imagen.setId(rs.getInt("id"));
                    imagen.setImg(rs.getString("img"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                imagenes.add(imagen);
            }
        } finally {
            try {
                rs.close();
            } finally {
                try {
                    ptsmt.close();
                } finally {
                    conexion.close();
                }
            }
        }

        return imagenes;
    }

    /*
    
    public Imagen obtenerImagenes(Integer id) throws ClassNotFoundException, IOException, SQLException {
        Imagen imagenes = new Imagen();
        Connection conexion = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;

        try {
            conexion = Conexion.getInstance().getConnection();
            ptsmt = conexion.prepareStatement(SQL_IMAGENES_SELECT);
            //ptsmt.setInt(1, id);
            rs = ptsmt.executeQuery();

            while (rs.next()) {
                try {
                    
                    imagenes.setId(rs.getInt("id"));
                   // imagenes.setImg(rs.getString("img"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        } finally {
            try {
                rs.close();
            } finally {
                try {
                    ptsmt.close();
                } finally {
                    conexion.close();
                }
            }
        }

        return imagenes;
    }
     */
    public static void insertar(ArrayList<String> img)
            throws ClassNotFoundException,
            IOException, SQLException {
        Connection c = null;
        PreparedStatement ptsmt = null;

        try {
            c = Conexion.getInstance().getConnection();
            ptsmt = c.prepareStatement(SQL_IMAGEN_INSERT);

            for (String imgBase64 : img) {
                ptsmt.setString(1, imgBase64);

                ptsmt.execute();
            }

        } finally {
            try {
                ptsmt.close();
            } finally {
                c.close();
            }
        }

    }
}
