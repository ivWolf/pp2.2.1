package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    public User getUserByCarModelAndSeries(String carModel, String carSeries) throws SQLException {
        return userDao.getUserByCarModelAndSeries(carModel, carSeries);
    }
}