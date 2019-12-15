package daos;
import models.Car;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface DataAccessOperations<T> {
    T findById(int id) throws SQLException;
    List<T> findAll() throws SQLException;
    T update(Car dto);
    T create(Car dto);
    T delete(int id);
}