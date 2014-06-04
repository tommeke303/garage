/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Domain;

import Validation.OnSerienummerUpdate;
import Validation.ValidNummerplaat;
import Validation.ValidSerienummer;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author thomas
 */
@Entity
@Table(name="TBL_WAGEN")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@NamedQueries({
    @NamedQuery(name="Wagen.findAll", query = "SELECT w FROM Wagen w")
})
public abstract class Wagen implements Serializable {
    @Id
    @ValidNummerplaat
    private String nummerplaat;
    
    @ValidSerienummer(groups={OnSerienummerUpdate.class})
    private String serienummer;
    @NotNull
    @OneToMany
    private int GARAGE_ID;
    
    public Wagen(){}

    public String getNummerplaat() {
        return nummerplaat;
    }

    public void setNummerplaat(String nummerplaat) {
        this.nummerplaat = nummerplaat;
    }

    public String getSerienummer() {
        return serienummer;
    }

    public void setSerienummer(String serienummer) {
        this.serienummer = serienummer;
    }

    public void setGARAGE_ID(int GARAGE_ID) {
        this.GARAGE_ID = GARAGE_ID;
    }

    public int getGARAGE_ID() {
        return GARAGE_ID;
    }
    
    
    
    
    
    
}
