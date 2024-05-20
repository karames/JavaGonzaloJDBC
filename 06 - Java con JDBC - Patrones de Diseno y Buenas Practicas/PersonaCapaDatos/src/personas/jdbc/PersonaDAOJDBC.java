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
    // private final String SQL_INSERT = "";
    // private final String SQL_UPDATE = "";
    // private final String SQL_DELETE = "";

    public PersonaDAOJDBC() {
    }

    public PersonaDAOJDBC(Connection conn) {
        this.userConn = conn;
    }

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
}
