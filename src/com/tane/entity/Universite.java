
package com.tane.entity;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "universiteler")
@NamedQueries({
@NamedQuery(name = "uni.findAll",query="from Universite"),
@NamedQuery(name = "uni.findById",query = "from Universite WHERE numara = :numara"),
@NamedQuery(name = "uni.findBySehir",query = "from Universite WHERE universite_sehri = :sehir"),
@NamedQuery(name = "uni.findByUniAdi",query = "from Universite WHERE universite_adi = :adi")
})
public class Universite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "numara")
    private int id;
    
    @Column(name = "universite_sehri")
    @Basic
    private String unisehir;
    @Column(name = "universite_adi")
    @Basic    
    private String uniad;
    
    @Temporal(value = TemporalType.DATE)
    @Transient
    @Column(name = "kurulus_yili")
    private Date kurulusyil;

    public Universite() {
    }

    public Universite(String unisehir, String uniad, Date kurulusyil) {
        this.unisehir = unisehir;
        this.uniad = uniad;
        this.kurulusyil = kurulusyil;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnisehir() {
        return unisehir;
    }

    public void setUnisehir(String unisehir) {
        this.unisehir = unisehir;
    }

    public String getUniad() {
        return uniad;
    }

    public void setUniad(String uniad) {
        this.uniad = uniad;
    }

    public Date getKurulusyil() {
        return kurulusyil;
    }

    public void setKurulusyil(Date kurulusyil) {
        this.kurulusyil = kurulusyil;
    }
    
    
    
    
    
    
    
}
