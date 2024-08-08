package Service;

import Dao.OdontologoDAOH2;
import Dao.iDao;
import Model.Odontologo;

import java.util.List;

public class OdontologoService {

    private iDao<Odontologo> odontologoiDao;

    public OdontologoService() {
        odontologoiDao = new OdontologoDAOH2();
    }

    // Métodos CRUD manuales
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoiDao.guardar(odontologo);
    }

    public List<Odontologo> buscarTodos() {
        return odontologoiDao.buscarTodos();
    }
}
