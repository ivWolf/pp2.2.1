package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCarModelAndSeries(String carModel, String carSeries) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT u\n" +
                    "FROM User u\n" +
                    "JOIN u.car c\n" +
                    "WHERE c.model = :carModel\n" +
                    "AND c.series = :carSeries";
            Query query = session.createQuery(hql);
            query.setParameter("carModel", carModel);
            query.setParameter("carSeries", carSeries);
            List<User> users = query.list();
            return users.get(0);
        }
    }
}