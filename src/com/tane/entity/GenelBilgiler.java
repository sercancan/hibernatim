
package com.tane.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;


@Table(name = "genelbilgiler")
@Entity
//@SecondaryTable(name = "ikinci_GenelBilgiler")
//HQL Sorgularını boyle yazabilirsiniz 
@NamedQueries({
@NamedQuery(name = "genelbilgiler.findall",query = "from GenelBilgiler"),
@NamedQuery(name = "genelbilgiler.findByAd",query = "from GenelBilgiler WHERE adiniz = :adiniz"),
@NamedQuery(name = "genelbilgiler.finByYas",query = "from GenelBilgiler WHERE yasiniz = :yasiniz"),
@NamedQuery(name = "genelbilgiler.findById",query = "from GenelBilgiler WHERE numara = :numara")
})

//DOGAL SQL Kodlarınıda bu Anotasyonlar ile yazabilirsiniz.
@NamedNativeQueries({
@NamedNativeQuery(name = "nativeSorgu1",query = "SELECT * FROM GenelBilgiler",resultClass = GenelBilgiler.class),
@NamedNativeQuery(name = "nativeSorgu2",query = "SELECT * FROM GenelBilgiler WHERE yasiniz = :yasiniz",resultClass = GenelBilgiler.class),
@NamedNativeQuery(name = "nativeSorgu3",query = "SELECT * FROM GenelBilgiler WHERE numara = :numara",resultClass = GenelBilgiler.class)
})

public class GenelBilgiler {
    
    @Column(name = "numara")
    @Id
    @TableGenerator(name = "ektablo",table = "tblgenelbilgi",
                    pkColumnName = "GenelBilgilerTablosu",pkColumnValue = "Son Kayıt ID",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "ektablo")
    private int numara; 
    // COLUMN ISMI ,hangi tablloda tutulacagı ve null olmaması
    @Column(name = "adiniz",nullable=false)  
    //BU ANOTASYON erişimi degişken üzerinden zorlama yapar
    @Access(value = AccessType.FIELD)
    @Basic 
    private String ad;
    @Column(name = "soyadiniz" , nullable=false , unique = false)
    @Access(value = AccessType.FIELD)
    @Basic
    private String soyad;
    @Column(name="yasiniz", nullable = false , unique = false)
    @Access(value= AccessType.FIELD)
    @Basic
    private String yas;
    @Column(name="sektor",nullable=false)
    @Access(value = AccessType.FIELD)
    @Basic
    private String sektor;
    @Column(name = "memleketiniz",nullable = false , unique = false)
    @Access(value=AccessType.FIELD)
    @Basic
    private String memleket;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "kayit_tarihi" , nullable = false)
    @Access(value=AccessType.FIELD)
    @Basic
    private Date tarih;
    @Column(name = "cinsiyet", nullable = false)
    @Basic
    private String cinsiyet;
    @Transient
    @Column(name = "ciltno")
    private int ciltno;
    @Transient
    @Column(name = "sirano")
    private int sirano;
    @Transient
    @Column(name = "kimlikno")
    private int kimlikno;
    
    @Type(type = "yes_no")
    @Basic
    @Column(name = "dunyaMi" ,nullable = false , unique = false  )
    private boolean dunyali_mi;
    
    @ElementCollection
    @CollectionTable(name = "epostalar" , joinColumns = @JoinColumn(name = "numara"))
    private List<String> epostalarin;
       
    @ElementCollection
    @CollectionTable(name = "telefonlar",joinColumns = @JoinColumn(name = "numara"))
    private List<String> telefonlar;
    
    @ElementCollection
    @CollectionTable(name = "isler",joinColumns = @JoinColumn(name = "numara"))
    private List<String> isler;
    
    @Embedded
    @AttributeOverrides({
    @AttributeOverride(name = "sehir", column = @Column(name = "EV_ADRES_SEHIR") ),
    @AttributeOverride(name = "cadde", column = @Column(name = "EV_ADRES_CADDE")),
    @AttributeOverride(name = "kapiNo",column = @Column(name = "EV_ADRES_KAPINO")),
    @AttributeOverride(name = "postaKodu",column = @Column(name = "EV_ADRES_POSTAKODU")),
    @AttributeOverride(name = "semt" , column = @Column(name = "EV_ADRES_SEMT"))
    })
     
   // @JoinTable(name = "adresTablosu",joinColumns = @JoinColumn(name = "numara"))
   // @GenericGenerator(name = "hilo-gen",strategy = "hilo")
   // @CollectionId(columns = {@Column(name = "adres_id")},generator = "hilo-gen",type=@Type(type="long"))
    private Adresler evAdres;
    
    @Embedded
    @AttributeOverrides({
    @AttributeOverride(name = "sehir",column = @Column(name = "IS_ADRES_SEHIR")),
    @AttributeOverride(name = "cadde",column = @Column(name = "IS_ADRES_CADDE")),
    @AttributeOverride(name = "kapiNo",column = @Column(name = "IS_ADRES_KAPINO")),
    @AttributeOverride(name = "postaKodu" , column = @Column(name = "IS_ADRES_POSTAKODU")),
    @AttributeOverride(name = "semt", column = @Column(name = "IS_ADRES_SEMT"))
    })
    private Adresler isAdres;
      
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kimlikbilgi_numara")
    private KimlikBilgileri kimlikbilgileri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "universite_numara")
    private Universite universite;
    
     
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "bilgi_hobi", 
         joinColumns = @JoinColumn(name = "bilgi_num"),inverseJoinColumns = @JoinColumn(name = "hobi_num"))
    private List<Hobiler> hobiler = new ArrayList<Hobiler>();
    
    public GenelBilgiler() {
    }

    public GenelBilgiler(String ad, String soyad, String yas, String sektor, String memleket, Date tarih,
                         String cinsiyet, int ciltno, int sirano, int kimlikno, boolean dunyali_mi,
                         List<String> epostalarin, List<String> telefonlar, List<String> isler, 
                         Adresler evAdres, Adresler isAdres,KimlikBilgileri kimlikbilgileri,Universite universite) {
        this.ad = ad;
        this.soyad = soyad;
        this.yas = yas;
        this.sektor = sektor;
        this.memleket = memleket;
        this.tarih = tarih;
        this.cinsiyet = cinsiyet;
        this.ciltno = ciltno;
        this.sirano = sirano;
        this.kimlikno = kimlikno;
        this.dunyali_mi = dunyali_mi;
        this.epostalarin = epostalarin;
        this.telefonlar = telefonlar;
        this.isler = isler;
       
        this.evAdres = evAdres;
        this.isAdres = isAdres;
        this.kimlikbilgileri = kimlikbilgileri;
        this.universite = universite;
    }

    public int getNumara() {
        return numara;
    }

    public void setNumara(int numara) {
        this.numara = numara;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getYas() {
        return yas;
    }

    public void setYas(String yas) {
        this.yas = yas;
    }

    public String getSektor() {
        return sektor;
    }

    public void setSektor(String sektor) {
        this.sektor = sektor;
    }

    public String getMemleket() {
        return memleket;
    }

    public void setMemleket(String memleket) {
        this.memleket = memleket;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public int getCiltno() {
        return ciltno;
    }

    public void setCiltno(int ciltno) {
        this.ciltno = ciltno;
    }

    public int getSirano() {
        return sirano;
    }

    public void setSirano(int sirano) {
        this.sirano = sirano;
    }

    public int getKimlikno() {
        return kimlikno;
    }

    public void setKimlikno(int kimlikno) {
        this.kimlikno = kimlikno;
    }

    public boolean isDunyali_mi() {
        return dunyali_mi;
    }

    public void setDunyali_mi(boolean dunyali_mi) {
        this.dunyali_mi = dunyali_mi;
    }

    public List<String> getEpostalarin() {
        return epostalarin;
    }

    public void setEpostalarin(List<String> epostalarin) {
        this.epostalarin = epostalarin;
    }

    public List<String> getTelefonlar() {
        return telefonlar;
    }

    public void setTelefonlar(List<String> telefonlar) {
        this.telefonlar = telefonlar;
    }

    public List<String> getIsler() {
        return isler;
    }

    public void setIsler(List<String> isler) {
        this.isler = isler;
    }

    public List<Hobiler> getHobiler() {
        return hobiler;
    }

    public void setHobiler(List<Hobiler> hobiler) {
        this.hobiler = hobiler;
    }

    public Adresler getEvAdres() {
        return evAdres;
    }

    public void setEvAdres(Adresler evAdres) {
        this.evAdres = evAdres;
    }

    public Adresler getIsAdres() {
        return isAdres;
    }

    public void setIsAdres(Adresler isAdres) {
        this.isAdres = isAdres;
    }

    public KimlikBilgileri getKimlikbilgileri() {
        return kimlikbilgileri;
    }

    public void setKimlikbilgileri(KimlikBilgileri kimlikbilgileri) {
        this.kimlikbilgileri = kimlikbilgileri;
    }

    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }
    
    
    

    @Override
    public String toString() {
        return "GenelBilgiler{" + "numara=" + numara + ", ad=" + ad + ", soyad=" + soyad + ", yas=" + yas + 
                ", sektor=" + sektor + ", memleket=" + memleket + ", tarih=" + tarih + 
                ", cinsiyet=" + cinsiyet + ", ciltno=" + ciltno + ", sirano=" + sirano + 
                ", kimlikno=" + kimlikno + ", dunyali_mi=" + dunyali_mi + ", epostalarin=" + epostalarin + 
                ", telefonlar=" + telefonlar + ", isler=" + isler + ", hobiler=" + hobiler + 
                ", evAdres=" + evAdres + ", isAdres=" + isAdres +"kimlikbilgileri="+kimlikbilgileri+ '}';
    }
 
    
    
    
    
}
