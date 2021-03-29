package hiber;

import hiber.config.AppConfig;
import hiber.dao.CarDao;
import hiber.dao.CarDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Василий", "Петров", "user1@mail.ru");
      User user2 = new User("Максим", "Сидоров", "user2@mail.ru");
      User user3 = new User("Станислав", "Петренко", "user3@mail.ru");
      User user4 = new User("Геннадий", "Сидоренко", "user4@mail.ru");

      Car car1 = new Car("BMW",5);
      Car car2 = new Car("AUDI",8);
      Car car3 = new Car("Kia",11);
      Car car4 = new Car("ВАЗ",1);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.println("Владелец "+user.getFirstName()+" "+
                 user.getLastName()+" "+
                 "Автомобиль "+user.getCar().getModel()+" "+
                 "Серии "+user.getCar().getSeries());
      }

      userService.userWithCar("ВАЗ", 1);
      System.out.println("Владелец автомобиля ВАЗ "+userService.userWithCar("ВАЗ", 1));

      context.close();
   }
}
