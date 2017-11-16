
package com.tane.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;


@Embeddable
public class Adresler {
    
  
    
    @Column
    private String sehir;
    @Column
    private String cadde;
    @Column
    private String kapiNo;
    @Column
    private String postaKodu;
    @Column
    private String semt;

    public Adresler()
    {
    }
    public Adresler(String sehir, String cadde, String kapiNo, String postaKodu, String semt) {
        this.sehir = sehir;
        this.cadde = cadde;
        this.kapiNo = kapiNo;
        this.postaKodu = postaKodu;
        this.semt = semt;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public String getCadde() {
        return cadde;
    }

    public void setCadde(String cadde) {
        this.cadde = cadde;
    }

    public String getKapiNo() {
        return kapiNo;
    }

    public void setKapiNo(String kapiNo) {
        this.kapiNo = kapiNo;
    }

    public String getPostaKodu() {
        return postaKodu;
    }

    public void setPostaKodu(String postaKodu) {
        this.postaKodu = postaKodu;
    }

    public String getSemt() {
        return semt;
    }

    public void setSemt(String semt) {
        this.semt = semt;
    }

  

    @Override
    public String toString() {
        return "Adresler{" + "sehir=" + sehir + ", cadde=" + cadde + ", kapiNo=" + kapiNo + ", postaKodu=" + postaKodu + ", semt=" + semt + '}';
    }
       
}
