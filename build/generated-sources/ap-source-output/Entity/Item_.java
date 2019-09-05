package Entity;

import Entity.Cart;
import Entity.Enterprise;
import Entity.OrderDetail;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-05T15:39:52")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, String> itemPicPath;
    public static volatile CollectionAttribute<Item, Cart> cartCollection;
    public static volatile SingularAttribute<Item, String> itemName;
    public static volatile CollectionAttribute<Item, OrderDetail> orderDetailCollection;
    public static volatile SingularAttribute<Item, String> itemTag;
    public static volatile SingularAttribute<Item, Integer> itemPrice;
    public static volatile SingularAttribute<Item, Integer> id;
    public static volatile SingularAttribute<Item, Enterprise> enterpriseId;
    public static volatile SingularAttribute<Item, Integer> stock;
    public static volatile SingularAttribute<Item, Integer> itemSaleNum;

}