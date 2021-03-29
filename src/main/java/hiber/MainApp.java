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
      CarService carService = context.getBean(CarService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));


      Car car2 = new Car("audi", 3);
      if(car2 == null) {
         carService.add(car2);
         System.out.println("11111111car2 == null1111111");
      }
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
//         Car car2 = new Car("audi", 3);
//         carService.add(car2);
         if(user.getCar() == null) {
            user.setCar(car2);
            System.out.println("!!!user.getCar() == null!!!");
         }
         //System.out.println("Car = "+user.getCar());
         userService.update(user);
         //carService.delete(car2);
      }

      //carService.delete(car2);


//      carService.add(new Car("Mazda", 7));
//
//      List<Car> cars = carService.listCars();
//      for (Car car : cars) {
//         System.out.println("Id = "+car.getId());
//         System.out.println("Model = "+car.getModel());
//         System.out.println("Series = "+car.getSeries());
//         System.out.println();
//      }
//
//     // User user = carService.add(new Car("BMW", 5));
//
//      User user666 = new User("User666", "Lastname666", "user666@mail.ru");
//      Car carUser666 = new Car("BMW", 5);
//
//      user666.setCar(carUser666);

      context.close();
   }
}
