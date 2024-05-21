package personas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    // private static String HOST = "jdbc:mariadb://localhost:3306/";
    private static String HOST = "jdbc:sqlite:D:/JavaGonzaloNivel3/drivers-jdbc/sga-nono.db";
    private static String BD = "sga-nono";
    private static String USER = "root";
    private static String PASSWORD = "";

    // conexion con la base de datos
    public static Connection conectarBD() {
        System.out.println("");
        System.out.println("conectando con la base de datos ");
        Connection conn = null;

        try {
            // conn = DriverManager.getConnection(HOST + BD, USER, PASSWORD);
            conn = DriverManager.getConnection(HOST);
            System.out.println("OKEY SE HA ESTABLICIDO CONESION CON LA BASE DE DATOS");
        } catch (SQLException sqle) {
            System.out.println("error no se pudo conectar con la bese de datos");
            System.out.println(sqle.getMessage());
            throw new RuntimeException();
        }

        return conn;
    }

    // cerrar objeto ResultSet
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            throw new RuntimeException(sqle);
        }
    }

    // cerrar preparedstatement
    public static void close(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            throw new RuntimeException(sqle);
        }
    }

    // cerrar conexion
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            throw new RuntimeException(sqle);
        }
    }

}
