/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpac;

import Entity.Item;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author 67506
 */
@Named("goodParameter")
@SessionScoped
public class GoodParameter implements Serializable {
    private Item item;

    public String itemDetail(Item item) {
        this.item = item;
        return "GoodDetail.xhtml";
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
