import Dao.BD;
import Model.Odontologo;
import Service.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OdontologoTestService {

    private OdontologoService odontologoService;

    @BeforeEach
    public void setUp() {
        // Inicializa el servicio de odontólogo
        odontologoService = new OdontologoService();

        // Opcionalmente, puedes limpiar la base de datos o cargar datos de prueba aquí
    }

    @Test
    @DisplayName("Testear búsqueda de todos los odontólogos")
    public void testBusquedaTodos() {
        BD.crearTablas();
        // Guarda algunos odontólogos
        odontologoService.guardarOdontologo(new Odontologo("123", "Sarid", "Herrera"));
        odontologoService.guardarOdontologo(new Odontologo("124", "Yuri", "Ochoa"));

        // Busca todos los odontólogos
        List<Odontologo> odontologos = odontologoService.buscarTodos();

        // Verifica el tamaño de la lista
        assertEquals(4, odontologos.size(), "El número de odontólogos debería ser 4.");
    }
}
