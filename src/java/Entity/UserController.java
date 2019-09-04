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
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    @EJB
//    private Entity.EnterpriseFacade enterprise_ejbFacade;
    private Entity.UserFacade ejbFacade;
    private List<User> items = null;
    private User selected;
    //是否处于登录状态
    private boolean is_log_in;
    private String username;
    private String password;
    private User current_user;

    //调用该函数执行登录操作
    public String login(String urlString) {
        User temp = getFacade().getValidateLogin(username, password);

        if (temp != null) {
            if (temp.getVerifyState() == 0) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("该企业尚未通过验证"));
                return urlString;
            } else {
                current_user=temp;
                is_log_in = true;
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("成功登录"));

                //判断此时user的角色，要判断！！！
                return "/index.xhtml";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("用户名与密码不匹配，请重新输入"));
        }

        return urlString;
    }

    //调用该函数执行用户注册操作
    public String user_signup() {
        User temp = getFacade().getIsDuplicate(username);

        if (temp == null) {
            getFacade().createUser(username, password);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("注册成功"));
            return "/login.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("用户名已存在，请重新输入"));
        }

        return "/user_signup.xhtml";
    }

    //调用该函数执行企业账号注册操作，转到企业信息填写页面
    public String enterprise_signup() {
        User temp = getFacade().getIsDuplicate(username);

        if (temp != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("用户名已存在，请重新输入"));
        } else {
            current_user = getFacade().createEnterprise(username, password);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("请输入企业信息"));

            return "/enterprise_signup_2.xhtml";
        }

        return "/enterprise_signup_1.xhtml";
    }

    public User getSelected() {
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserFacade getFacade() {
        return ejbFacade;
    }

    public User prepareCreate() {
        selected = new User();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<User> getItems() {
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

    public User getUser(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<User> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<User> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getCurrent_user() {
        return current_user;
    }

    public void setCurrent_user(User current_user) {
        this.current_user = current_user;
    }

    public void setIs_log_in(boolean is_log_in) {
        this.is_log_in = is_log_in;
    }

    @FacesConverter(forClass = User.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userController");
            return controller.getUser(getKey(value));
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
            if (object instanceof User) {
                User o = (User) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), User.class.getName()});
                return null;
            }
        }

    }

}
