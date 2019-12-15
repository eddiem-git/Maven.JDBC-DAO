package daos;

import models.Car;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] arg){
        DataAccessor dao = new DataAccessor();
        System.out.println("starting");
        try{
            List<Car> cars = dao.findAll();
            for(Car each : cars){
                System.out.println(each.toString() + "nothing");
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("not working");
        }
    }
}
