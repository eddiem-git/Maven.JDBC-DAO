package daos;

import models.Car;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DataAccessor implements DataAccessOperations {
    //find a car by ID and return it.
    public Car findById(int id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cars WHERE id = " + id);
            if (resultSet.next()) {
                return extractCarFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Find car by id failed ");
            e.printStackTrace();
            connection.close();
        }
        return null;
    }
    //find All and return list of cars.
    public List<Car> findAll() throws SQLException {
        List<Car> cars = new ArrayList<Car>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cars");
            while (resultSet.next()) {
                Car car = extractCarFromResultSet(resultSet);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            System.out.println("Find all cars, Failed");
            e.printStackTrace();
            connection.close();
        }
        return null;
    }
    public Boolean update(Car car) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE cars SET make=?, model=?, year=?, color=?, vin=? WHERE id=?");
            ps.setString(1, car.getMake());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getYear());
            ps.setString(4, car.getColor());
            ps.setString(5, car.getVin());
            ps.setInt(6, car.getId());
            int i = ps.executeUpdate();
            if (i == 1)
                return true;
        } catch (SQLException e) {
            System.out.println("Update failed");
            e.printStackTrace();
        }
        return false;
    }
    public Boolean create(Car car) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO cars VALUES (DEFAULT, ?, ?, ?, ?, ?)");
            ps.setString(1, car.getMake());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getYear());
            ps.setString(4, car.getColor());
            ps.setString(5, car.getVin());
            int i = ps.executeUpdate();
            return (i == 1);
        } catch (SQLException e) {
            System.out.println("create car FAILED");
            e.printStackTrace();
        }
        return false;
    }
    public Boolean delete(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate("DELETE FROM cars WHERE id=" + id);
            return (i == 1);
        } catch (SQLException e) {
            System.out.println("delete FAILED");
            e.printStackTrace();
        }
        return false;
    }
    private Car extractCarFromResultSet(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setMake(rs.getString("make"));
        car.setModel(rs.getString("model"));
        car.setYear(rs.getString("year"));
        car.setColor(rs.getString("color"));
        car.setVin(rs.getString("vin"));
        return car;
    }
}