/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
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
    
    public void create_Enterprise(String EnterpriseName,String EnterpriseLogoPath,String EnterpriseAddress, String EnterpriseTel,User userid){
        Enterprise e = new Enterprise();
        e.setEnterpriseName(EnterpriseName);
        e.setEnterpriseLogoPath(EnterpriseLogoPath);
        e.setEnterpriseAddress(EnterpriseAddress);
        e.setEnterpriseTel(EnterpriseTel);
        e.setUserId(userid);
        em.persist(e);
    }
    
     //判断数据库中是否有企业名与输入的企业名相同
    public Enterprise getIsDuplicate(String EnterpriseName){
        Enterprise mEnterprise;
        try{
            mEnterprise = (Enterprise)em.createQuery("SELECT e FROM Enterprise e WHERE e.enterpriseName=:name")
                .setParameter("name", EnterpriseName)
                .getSingleResult();
            return mEnterprise;
        }catch (NoResultException e){
            return null;
        }
    }
}
