package Dao;

import Model.Odontologo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class OdontologoDAOH2 implements iDao<Odontologo>{
    private static final Logger logger =Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_INSERT = "INSERT INTO ODONTOLOGOS (NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM ODONTOLOGOS";

        @Override
        public Odontologo guardar(Odontologo odontologo) {
            Connection connection = null;
            Odontologo odontologoARetornar = null;
            try {
                connection = BD.getConnection();
                connection.setAutoCommit(false);

                PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, odontologo.getNumeroMatricula());
                preparedStatement.setString(2, odontologo.getNombre());
                preparedStatement.setString(3, odontologo.getApellido());
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    odontologoARetornar = new Odontologo(id,odontologo.getNumeroMatricula(), odontologo.getApellido(), odontologo.getNombre());
                }
                logger.info("odontologo guardado: " + odontologoARetornar);

                connection.commit();
                connection.setAutoCommit(true);
            } catch (Exception e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        logger.info(ex.getMessage());
                        ex.printStackTrace();
                    }
                }
                logger.info(e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.info(e.getMessage());
                    e.printStackTrace();
                }
            }

            return odontologoARetornar;
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
            List<Odontologo> odontologos = new ArrayList<>();
            Connection connection = null;
            try {
                connection = BD.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
                while (resultSet.next()) {
                    Integer idDevuelto = resultSet.getInt(1);
                    String numeroMatricula = resultSet.getString(2);
                    String nombre = resultSet.getString(3);
                    String apellido = resultSet.getString(4);
                    Odontologo odontologo = new Odontologo(idDevuelto, numeroMatricula, nombre, apellido);

                    logger.info("odontologo listado: " + odontologo);
                    odontologos.add(odontologo);
                }

            } catch (Exception e) {
                logger.info(e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.info(e.getMessage());
                    e.printStackTrace();
                }
            }
            return odontologos;
        }

}