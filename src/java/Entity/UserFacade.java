/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databag;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author VictorChan
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "datawebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    public User getCurUser(int id){
        User curUser;
    try{   
//        curEnterprise= (Enterprise)em.createQuery("SELECT e FROM Enterprise e WHERE e.enterpriseName=:curEnterpriseName")
//                .setParameter("curEnterpriseName",curEnterpriseName)
//                .getSingleResult();
//         return curEnterprise;
         curUser= (User)em.createQuery("SELECT u FROM User u WHERE u.id=:id")
              .setParameter("id",id)
              .getSingleResult();
         return curUser;
    }
    catch (NoResultException e){
        return null;
    }
   }
}
