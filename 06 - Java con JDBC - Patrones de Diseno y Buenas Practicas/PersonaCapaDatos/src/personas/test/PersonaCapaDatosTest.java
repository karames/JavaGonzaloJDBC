package personas.test;

import java.util.List;
import java.sql.SQLException;
import personas.dto.PersonaDTO;
import personas.jdbc.PersonaDAO;
import personas.jdbc.PersonaDAOJDBC;

public class PersonaCapaDatosTest {
    public static void main(String[] args) throws Exception {
        PersonaDAO personaDao = new PersonaDAOJDBC();
        try {
            // select
            List<PersonaDTO> personasLista = personaDao.select();
            for (PersonaDTO persona : personasLista) {
                System.out.print(persona);
                System.out.println();
            }

            // insertar
            // PersonaDTO persona = new PersonaDTO();
            // persona.setNombre("Gonzalo");
            // persona.setApellido("de Dios");
            // personaDao.insert(persona);

            // update
            // PersonaDTO persona = new PersonaDTO();
            // persona.setId(3);
            // persona.setNombre("GONZALO");
            // persona.setApellido("Rodriguez de dios");
            // personaDao.update(persona);

            // delete
            // personaDao.delete(new PersonaDTO(1));

        } catch (SQLException sqle) {
            System.out.println("Excepción en la capa de prueba");
            sqle.printStackTrace();
        }
    }
}
