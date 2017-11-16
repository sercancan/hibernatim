
package com.tane.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "hobiler")
public class Hobiler {
    
    @Id
    @TableGenerator(name = "tblhobi", table = "tblhobibilgi", 
                    pkColumnName = "HobilerGenelBilgi", pkColumnValue = "Son Kayıt ID",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE , generator = "tblhobi")
    @Column(name="numara")
    public int id;
    
    @Column(name = "hobiniz")
    @Basic
    private String hobi_tipi;

    public Hobiler() {
    }

    public Hobiler(String hobi_tipi) {
        this.hobi_tipi = hobi_tipi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHobi_tipi() {
        return hobi_tipi;
    }

    public void setHobi_tipi(String hobi_tipi) {
        this.hobi_tipi = hobi_tipi;
    }
    
    
}
