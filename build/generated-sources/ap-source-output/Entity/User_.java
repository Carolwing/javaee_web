package Entity;

import Entity.Cart;
import Entity.Enterprise;
import Entity.OrderDetail;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-04T02:35:15")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile CollectionAttribute<User, Cart> cartCollection;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> role;
    public static volatile CollectionAttribute<User, OrderDetail> orderDetailCollection;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile CollectionAttribute<User, Enterprise> enterpriseCollection;
    public static volatile SingularAttribute<User, Short> verifyState;

}