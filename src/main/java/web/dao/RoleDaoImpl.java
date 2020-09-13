package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Role findByName(String name) {
        TypedQuery<Role> query = em.createNamedQuery(Role.FIND_BY_NAME,Role.class);
        query.setParameter("roleName",name);
        return  query.getSingleResult();
    }
    @Override
    public Role findById(Long Id) {
        TypedQuery<Role> query = em.createNamedQuery(Role.FIND_BY_ID,Role.class);
        query.setParameter("id",Id);
        return  query.getSingleResult();
    }

    @Override
    public void delete(Role role) {
    }

    @Override
    public Role saveRole(Role role) {
        if(role.getId()==null) {
            em.persist(role);
        } else {
            em.merge(role);
        }
        return role;
    }
}
