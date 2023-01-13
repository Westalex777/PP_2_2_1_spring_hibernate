package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        List<Car> cars = Arrays.asList(new Car("Mercedes", "E600"),
                new Car("BMW", "X5"),
                new Car("Audi", "A8"),
                new Car("BMW", "535"));

        List<User> users = Arrays.asList(new User("User1", "Lastname1", "user1@mail.ru"),
                new User("User2", "Lastname2", "user2@mail.ru"),
                new User("User3", "Lastname3", "user3@mail.ru"),
                new User("User4", "Lastname4", "user4@mail.ru"));

        for (int i = 0; i < users.size(); i++) {
            users.get(i).setCar(cars.get(i));
            userService.add(users.get(i));
        }

        users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(user.getCar());
            System.out.println();
        }

        System.out.println("--------------------------------");
        System.out.println("Вывод User по модели и серии Car : " +
                userService.getUserOwnerCar("BMW", "X5"));

        context.close();
    }
}
