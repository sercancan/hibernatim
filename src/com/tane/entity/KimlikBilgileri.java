
package com.tane.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


@Table
@Entity
@NamedQueries({
@NamedQuery(name = "kimlikbilgi.findAll",query = "FROM KimlikBilgileri"),
@NamedQuery(name = "kimlikbilgi.findByID",query = "FROM KimlikBilgileri WHERE numara = :numara"),
@NamedQuery(name = "kimlikbilgi.findByCiltNO",query = "FROM KimlikBilgileri WHERE ciltno = :ciltno"),
@NamedQuery(name = "kimlikbilgi.findBySiraNO",query = "from KimlikBilgileri where sirano = :sirano"),
@NamedQuery(name = "kimlikbilgi.findKimlikNO",query = "from KimlikBilgileri WHERE kimlikno = :kimlikno"),
@NamedQuery(name = "kimlikbilgi.findAileSiraNO",query = "FROM KimlikBilgileri WHERE ailesirano = :ailesirano")
})
public class KimlikBilgileri {
    
    
    @Id
    @TableGenerator(name = "ektablokimlik",table = "tblkimlikbilgi",
                        pkColumnName = "KimlikBilgilerTablosu",pkColumnValue = "Son Kayıt ID",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE ,generator = "ektablokimlik")
    @Column(name = "numara")
    private int id;
    @Column(name = "ciltno")
    @Basic
    private String ciltno;
    @Column(name = "sirano")
    @Basic
    private String sirano;
    @Column(name = "kimlikno")
    @Basic
    private String kimlikno;
    @Column(name = "ailesirano")
    @Basic
    private String ailesirano;

    public KimlikBilgileri() {
    }

    public KimlikBilgileri(String ciltno, String sirano, String kimlikno, String ailesirano) {
        this.ciltno = ciltno;
        this.sirano = sirano;
        this.kimlikno = kimlikno;
        this.ailesirano = ailesirano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCiltno() {
        return ciltno;
    }

    public void setCiltno(String ciltno) {
        this.ciltno = ciltno;
    }

    public String getSirano() {
        return sirano;
    }

    public void setSirano(String sirano) {
        this.sirano = sirano;
    }

    public String getKimlikno() {
        return kimlikno;
    }

    public void setKimlikno(String kimlikno) {
        this.kimlikno = kimlikno;
    }

    public String getAilesirano() {
        return ailesirano;
    }

    public void setAilesirano(String ailesirano) {
        this.ailesirano = ailesirano;
    }

    @Override
    public String toString() {
        return "KimlikBilgileri{" + "id=" + id + ", ciltno=" + ciltno + ", sirano=" + sirano + ", kimlikno=" + kimlikno + ", ailesirano=" + ailesirano + '}';
    }

    
    
    
    
    
    
    
    
}
