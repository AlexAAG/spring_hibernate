package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;

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

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      for (User user : userService.listUsers()) {
         System.out.println("Владелец "+user.getFirstName()+" "+
                 user.getLastName()+" "+
                 "Автомобиль "+user.getCar().getModel()+" "+
                 "Серии "+user.getCar().getSeries());
      }

      System.out.println("Владелец автомобиля ВАЗ "
              +userService.userWithCar("ВАЗ", 1).getFirstName()+" "
              +userService.userWithCar("ВАЗ", 1).getLastName()+" "
      );

      context.close();
   }
}
