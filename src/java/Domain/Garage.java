/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Validation.OnNaamUpdate;
import Validation.ValidNaam;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author thomas
 */
@Entity
@Table(name = "TBL_GARAGE")
@NamedQueries({
    @NamedQuery(name = "Garage.findAll", query = "select g from Garage g")
})
public class Garage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ValidNaam(groups = {OnNaamUpdate.class})
    private String naam;

    @OneToMany
    @JoinTable(name="TBL_WAGEN")
    private List<BMW> wagensBMW;
    
    @OneToMany
    @JoinTable(name="TBL_WAGEN")
    private List<Renault> wagensRenault;

    public Garage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public List<BMW> getWagensBMW() {
        return wagensBMW;
    }

    public void setWagensBMW(List<BMW> wagensBMW) {
        this.wagensBMW = wagensBMW;
    }

    public List<Renault> getWagensRenault() {
        return wagensRenault;
    }

    public void setWagensRenault(List<Renault> wagensRenault) {
        this.wagensRenault = wagensRenault;
    }
    
    public void addWagenBMW(BMW b){
        this.wagensBMW.add(b);
    }
    
    public void addWagenRenault(Renault r){
        this.wagensRenault.add(r);
    }
    

}
