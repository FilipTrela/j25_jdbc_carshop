package pl.sda.gdajava25;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    private MysqlConnection mysqlConnection;

    public CarDao() throws IOException, SQLException {
        mysqlConnection = new MysqlConnection();
        createTableIfNotExist();
    }

    private void createTableIfNotExist() throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CarQueries.CREATE_TABLE_QUERY)) {
                statement.execute();
            }
        }
    }

    public long insertCar(Car car) throws SQLException {
        long generatedId = 0;
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CarQueries.INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                setStatementByCarParameters(car, statement);
                statement.execute();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    generatedId = resultSet.getLong(1);
                    System.out.println("Samochód został utworzony z id: " + generatedId);
                }
            }
        }
        return generatedId;
    }

    private void setStatementByCarParameters(Car car, PreparedStatement statement) throws SQLException {
        statement.setString(1, car.getNr_rejstracyjny());
        statement.setLong(2, car.getPrzebieg());
        statement.setString(3, car.getMarka_model());
        statement.setInt(4, car.getRocznik());
        statement.setString(5, String.valueOf(car.getTyp()));
        statement.setString(6, car.getNazwisko_właściciela());
    }

    public boolean deleteCarById(Long id) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CarQueries.DELETE_BY_ID_QUERY)) {
                statement.setLong(1, id);
                int affectedRecords = statement.executeUpdate();
                if (isDelete(affectedRecords)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isDelete(int affectedRecords) {
        return affectedRecords > 0;
    }

    public boolean deleteCarByNrRej(String nr_rej) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CarQueries.DELETE_BY_NR_REJ_QUERY)) {
                statement.setString(1, nr_rej);
                int affectedRecords = statement.executeUpdate();
                if (isDelete(affectedRecords)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Car> getListAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CarQueries.LIST_ALL_CARS)) {
                ResultSet resultSet = statement.executeQuery();
                loadMultipleCarsResultSet(cars, resultSet);
            }
        }
        return cars;
    }

    public List<Car> getCarListByNrRej(String nr_rej) throws SQLException {
        return getCarListByQuery(CarQueries.SHOW_BY_NR_REJ, nr_rej);
    }

    public List<Car> getCarListByName(String naz_właściciela) throws SQLException {
        return getCarListByQuery(CarQueries.SHOW_BY_NAZWISKO, naz_właściciela);
    }

    public List<Car> getCarListByMrkMod(String mar_mod) throws SQLException {
        return getCarListByQuery(CarQueries.SHOW_BY_MARKA_MODEL, mar_mod);
    }

    private List<Car> getCarListByQuery(String query, String statmentParam) throws SQLException {
        List<Car> cars = new ArrayList<>();
        String param = "%" + statmentParam + "%";
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, param);
                ResultSet resultSet = statement.executeQuery();
                loadMultipleCarsResultSet(cars, resultSet);
            }
        }
        return cars;
    }


    private void loadMultipleCarsResultSet(List<Car> cars, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Car car = makeCar(resultSet);
            cars.add(car);
        }
    }

    private Car makeCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong(1));
        car.setNr_rejstracyjny(resultSet.getString(2));
        car.setPrzebieg(resultSet.getLong(3));
        car.setMarka_model(resultSet.getString(4));
        car.setRocznik(resultSet.getInt(5));
        car.setTyp(Type.valueOf(resultSet.getString(6)));
        car.setNazwisko_właściciela(resultSet.getString(7));
        return car;
    }
}
