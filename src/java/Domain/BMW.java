/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author thomas
 */
@Entity
@NamedQueries({
    @NamedQuery(name="BMW.findAll", query="SELECT b from BMW b")
})
public class BMW extends Wagen implements Serializable{
   @NotNull
   private String type;
   @NotNull
   private String kleur;
   public BMW(){};

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }   
}
