package Dao;

import Model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoListDAO implements iDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoListDAO.class);
    private List<Odontologo> odontologos = new ArrayList<>();

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Integer id = odontologos.size() + 1; // Generar un ID basado en el tamaño actual de la lista
        odontologo.setId(id);
        odontologos.add(odontologo);
        logger.info("Odontólogo guardado: " + odontologo);
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Odontologo> buscarTodos() {
        return new ArrayList<>(odontologos);
    }
}
