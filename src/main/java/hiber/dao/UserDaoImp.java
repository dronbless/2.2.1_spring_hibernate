package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      String comm = "from User";
      TypedQuery<User> query=sessionFactory.getCurrentSession()
              .createQuery(comm);
      return query.getResultList();
   }

   @Override
   public User getUserWithCar(String model, int series) {
      String comm = "from User as user where user.car.model = :model and user.car.series = :series";
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery(comm, User.class)
              .setParameter("model",model)
              .setParameter("series",series);
      return query.getResultList().get(0);
   }
}
