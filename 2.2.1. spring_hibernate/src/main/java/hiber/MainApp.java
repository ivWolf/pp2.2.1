package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarServiceImp;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarServiceImp carServiceImp = context.getBean(CarServiceImp.class);

        userService.add(new User("Aemond", "Targarien", "Aemond@vesteros.com"));
        userService.add(new User("Daemon", "Targarien", "Daemon@vesteros.com"));
        userService.add(new User("Rhaenira", "Targarien", "Rhaenira@vesteros.com"));
        userService.add(new User("Rhaenis", "Targarien", "Rhaenis@vesteros.com"));

        Car car = new Car("dragon", "x");
        carServiceImp.add(car);

        User user1 = new User("Aemond", "Targarien", "Aemond@vesteros.com");
        car.setUser(user1);
        user1.setCar(car);
        userService.add(user1);

        User userByCarModelAndSeries = userService.getUserByCarModelAndSeries("dragon", "x");

        System.out.println("Id = " + userByCarModelAndSeries.getId());
        System.out.println("First Name = " + userByCarModelAndSeries.getFirstName());
        System.out.println("Last Name = " + userByCarModelAndSeries.getLastName());
        System.out.println("Email = " + userByCarModelAndSeries.getEmail());
        System.out.println("Car = " + userByCarModelAndSeries.getCar());
        System.out.println();

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
        context.close();
    }
}