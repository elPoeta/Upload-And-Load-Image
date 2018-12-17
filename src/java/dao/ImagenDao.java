package dao;

import db.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Imagen;

/**
 *
 * @author elpoeta
 */
public class ImagenDao {

    private static ImagenDao INSTANCE = null;
    private final static String SQL_IMAGENES_SELECT = "SELECT * FROM imagenes WHERE id = ?;";
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

    public Imagen obtenerImagenes(Integer id) throws ClassNotFoundException, IOException, SQLException {
        Imagen imagenes = null;
        Connection conexion = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;

        try {
            conexion = Conexion.getInstance().getConnection();
            ptsmt = conexion.prepareStatement(SQL_IMAGENES_SELECT);
            ptsmt.setInt(1, id);
            rs = ptsmt.executeQuery();

            if (rs.next()) {
                try {
                    imagenes = new Imagen();
                    imagenes.setId(rs.getInt("id"));
                    imagenes.setImg(rs.getString("img"));

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

    public static void insertar(Imagen img)
            throws ClassNotFoundException,
            IOException, SQLException {
        Connection c = null;
        PreparedStatement ptsmt = null;

        try {
            c = Conexion.getInstance().getConnection();
            ptsmt = c.prepareStatement(SQL_IMAGEN_INSERT);
            ptsmt.setString(1, img.getImg());

            ptsmt.execute();
        } finally {
            try {
                ptsmt.close();
            } finally {
                c.close();
            }
        }

    }
}
