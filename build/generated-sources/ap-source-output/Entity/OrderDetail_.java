package Entity;

import Entity.Item;
import Entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-05T15:39:52")
@StaticMetamodel(OrderDetail.class)
public class OrderDetail_ { 

    public static volatile SingularAttribute<OrderDetail, Item> itemId;
    public static volatile SingularAttribute<OrderDetail, Date> orderTime;
    public static volatile SingularAttribute<OrderDetail, Integer> orderItemNum;
    public static volatile SingularAttribute<OrderDetail, Integer> id;
    public static volatile SingularAttribute<OrderDetail, User> userId;

}