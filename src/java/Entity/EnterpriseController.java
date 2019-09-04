package Entity;

import Entity.util.JsfUtil;
import Entity.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("enterpriseController")
@SessionScoped
public class EnterpriseController implements Serializable {

    @EJB
    private Entity.EnterpriseFacade ejbFacade;
    private List<Enterprise> items = null;
    private Enterprise selected;

    //当前企业状态
    private boolean is_login = false;
    private Enterprise curEnterprise;
    private String EnterpriseName;
    private String EnterpriseLogoPath;
    private String EnterpriseAddress;
    private String EnterpriseTel;

    //创建一个User为当前账号的企业
    public String createEnterprise(User userid) {
        Enterprise temp = getFacade().getIsDuplicate(EnterpriseName);

        if (temp == null) {
            getFacade().create_Enterprise(EnterpriseName, EnterpriseLogoPath, EnterpriseAddress, EnterpriseTel, userid);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("企业注册申请已提交，请等待审核"));
            return "/login.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("企业名称已存在，请重新输入"));
            return "/enterprise_signup_2.xhtml";
        }
    }

    public EnterpriseController() {
    }

    public Enterprise getSelected() {
        return selected;
    }

    public void setSelected(Enterprise selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EnterpriseFacade getFacade() {
        return ejbFacade;
    }

    public Enterprise prepareCreate() {
        selected = new Enterprise();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EnterpriseCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EnterpriseUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EnterpriseDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Enterprise> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Enterprise getEnterprise(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Enterprise> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Enterprise> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public Enterprise getCurEnterprise() {
        return curEnterprise;
    }

    public void setCurEnterprise(Enterprise curEnterprise) {
        this.curEnterprise = curEnterprise;
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String EnterpriseName) {
        this.EnterpriseName = EnterpriseName;
    }

    public String getEnterpriseLogoPath() {
        return EnterpriseLogoPath;
    }

    public void setEnterpriseLogoPath(String EnterpriseLogoPath) {
        this.EnterpriseLogoPath = EnterpriseLogoPath;
    }

    public String getEnterpriseAddress() {
        return EnterpriseAddress;
    }

    public void setEnterpriseAddress(String EnterpriseAddress) {
        this.EnterpriseAddress = EnterpriseAddress;
    }

    public String getEnterpriseTel() {
        return EnterpriseTel;
    }

    public void setEnterpriseTel(String EnterpriseTel) {
        this.EnterpriseTel = EnterpriseTel;
    }

    @FacesConverter(forClass = Enterprise.class)
    public static class EnterpriseControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EnterpriseController controller = (EnterpriseController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "enterpriseController");
            return controller.getEnterprise(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Enterprise) {
                Enterprise o = (Enterprise) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Enterprise.class.getName()});
                return null;
            }
        }

    }

}
