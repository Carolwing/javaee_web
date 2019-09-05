package Entity;

import Entity.Item;
import Entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-05T13:32:45")
@StaticMetamodel(Enterprise.class)
public class Enterprise_ { 

    public static volatile SingularAttribute<Enterprise, String> enterpriseLogoPath;
    public static volatile CollectionAttribute<Enterprise, Item> itemCollection;
    public static volatile SingularAttribute<Enterprise, String> enterpriseAddress;
    public static volatile SingularAttribute<Enterprise, Integer> id;
    public static volatile SingularAttribute<Enterprise, String> enterpriseName;
    public static volatile SingularAttribute<Enterprise, User> userId;
    public static volatile SingularAttribute<Enterprise, String> enterpriseTel;

}