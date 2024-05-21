package personas.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import personas.dto.PersonaDTO;

public class PersonaDAOJDBC implements PersonaDAO {
    private Connection userConn;
    private final String SQL_SELECT = "SELECT id_persona, nombre, apellido FROM personas ORDER BY id_persona";
    private final String SQL_INSERT = "INSERT INTO personas(nombre,apellido) VALUES(?,?)";
    private final String SQL_UPDATE = "UPDATE personas SET nombre = ?, apellido=? WHERE id_persona = ?";
    private final String SQL_DELETE = "DELETE FROM personas WHERE id_persona=?";

    public PersonaDAOJDBC() {
    }

    public PersonaDAOJDBC(Connection conn) {
        this.userConn = conn;
    }

    // metodo select
    public List<PersonaDTO> select() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PersonaDTO persona = null;
        List<PersonaDTO> personasLista = new ArrayList<PersonaDTO>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.conectarBD();
            System.out.println("\n ejecutando query: " + SQL_SELECT);
            pstmt = conn.prepareStatement(SQL_SELECT);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                persona = new PersonaDTO();
                persona.setId(id);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                personasLista.add(persona);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(pstmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return personasLista;
    }

    // metodo insert
    public int insert(PersonaDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.conectarBD();
            System.out.println("\n ejecutando query: " + SQL_INSERT);
            pstmt = conn.prepareStatement(SQL_INSERT);
            int index = 1; // contaodr de parametros(columnas)
            pstmt.setString(index++, persona.getNombre());
            pstmt.setString(index, persona.getApellido());
            rows = pstmt.executeUpdate();
            System.out.println("registros insertados: " + rows);

        } finally {
            Conexion.close(pstmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    // metodo update
    public int update(PersonaDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.conectarBD();
            System.out.println("\n ejecutando query: " + SQL_UPDATE);
            pstmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1; // contaodr de parametros(columnas)
            pstmt.setString(index++, persona.getNombre());
            pstmt.setString(index++, persona.getApellido());
            pstmt.setInt(index, persona.getId());
            rows = pstmt.executeUpdate();
            System.out.println("registros actualizados: " + rows);

        } finally {
            Conexion.close(pstmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    // metodo delete
    public int delete(PersonaDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.conectarBD();
            System.out.println("\n ejecutando query: " + SQL_DELETE);
            pstmt = conn.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, persona.getId());
            rows = pstmt.executeUpdate();
            System.out.println("registros eliminados: " + rows);

        } finally {
            Conexion.close(pstmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }
}
