package web.dao;


import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.*;
import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Repository
@Transactional


public class UserDaoImpl implements UserDao {


  @PersistenceContext
  private EntityManager em;

    @Override
    public User saveUser(User user) {
      if(user.getId()==null) {
        em.persist(user);
      } else {
        em.merge(user);
      }
      return user;
    }

  @Override
  public void delete(User user) {
      User mergeUser=em.merge(user);
      em.remove(mergeUser);
  }


  @Transactional(readOnly=true)
    @Override
    public List<User> getAllUsers() {
    return em.createNamedQuery(User.FIND_ALL, User.class).getResultList();
    }

    @Override
    public void cleanUsersTable() {}

  @Override
  public User findUserbyId(Long id) {
    TypedQuery<User> query = em.createNamedQuery (User.FIND_USER_BY_ID, User.class);
    query.setParameter("id",id);
    return query.getSingleResult();
  }
}
