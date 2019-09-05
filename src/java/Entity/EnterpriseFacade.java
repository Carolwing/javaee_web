/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 王淳铮
 */
@Stateless
public class EnterpriseFacade extends AbstractFacade<Enterprise> {

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnterpriseFacade() {
        super(Enterprise.class);
    }
    
    public Enterprise getCurEnterprise(String curEnterpriseName){
        Enterprise curEnterprise;
    try{   
//        curEnterprise= (Enterprise)em.createQuery("SELECT e FROM Enterprise e WHERE e.enterpriseName=:curEnterpriseName")
//                .setParameter("curEnterpriseName",curEnterpriseName)
//                .getSingleResult();
//         return curEnterprise;
         curEnterprise= (Enterprise)em.createQuery("SELECT e FROM Enterprise e WHERE e.enterpriseName=:curEnterpriseName")
              .setParameter("curEnterpriseName",curEnterpriseName)
              .getSingleResult();
         return curEnterprise;
    }
    catch (NoResultException e){
        return null;
    }
   }
}
  
    



