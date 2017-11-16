
package hibernate39.advanced.pkg2;

import com.tane.entity.Adresler;
import com.tane.entity.GenelBilgiler;
import com.tane.entity.Hobiler;
import com.tane.entity.KimlikBilgileri;
import com.tane.entity.Universite;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class Hibernate39Advanced2 {

   
    public static void main(String[] args) {
      
         
        
        Adresler evadres = new Adresler("İstanbul","Esenler","9","34259","tabya yolu");
        Adresler isadres = new Adresler("istanbul","göngören","48","34789","köy içi");
        
        List<String> postalar = new ArrayList<String>();
                     postalar.add("sinansoylu@yahoo.com");
                     postalar.add("sinansoylu@gmail.com");
                     postalar.add("sinansoylu@kendis.com");
        List<String> telefonlar = new ArrayList<String>();
                     telefonlar.add("0548 785 83 22");
                     telefonlar.add("0546 244 75 77");
                     telefonlar.add("0543 165 14 56");
        List<String> isler = new ArrayList<String>();
                     isler.add("Giyim magazasında satış elemanı");
                     isler.add("Kataloger");
                     isler.add("temizlik malzeme satışı");
                     
     
        KimlikBilgileri kimlikbilgileri1 = new KimlikBilgileri("00578","0483","0548","008784");
        Universite universite = new Universite("Aydın","Adnan Menderes Üniversitesi",new Date());
        
        GenelBilgiler bilgiler = new GenelBilgiler("sinan","soylu","25","Ticaret","Sinop",new Date(),"Erkek",4587,
                                                    268,589521,true,postalar,telefonlar,isler,
                                                    evadres,isadres,kimlikbilgileri1,universite);
        
        Hobiler hobi1 = new Hobiler("yemek yemek");
        Hobiler hobi2 = new Hobiler("müzik dinlemek");
        Hobiler hobi3 = new Hobiler("gülmek");
        
        bilgiler.getHobiler().add(hobi1);
        bilgiler.getHobiler().add(hobi2);
        bilgiler.getHobiler().add(hobi3);
        
        
       
        SessionFactory faktor = new Configuration().configure("ayar.cfg.xml").buildSessionFactory();
        Session session = faktor.openSession();
        Transaction  trans = session.beginTransaction();
        
        try{
        
        
         session.clear();
       
        session.save(kimlikbilgileri1);
        session.save(hobi1);
        session.save(hobi2);
        session.save(hobi3);
        session.save(universite);
        session.save(bilgiler);
        
        trans.commit();
               
            }catch(Exception e){
                System.out.println("HATA  :"+e.getMessage());
                 trans.rollback();
            }finally{
                 
                    if(session != null)
                        session.close();
            }
        
       /* 
        //Query query = session.getNamedQuery("genelbilgiler.findByAd").setString("adiniz", "sinan");
        Query query = session.getNamedQuery("nativeSorgu3").setInteger("numara", 1);
        List<GenelBilgiler> ilah = query.list();
        for(GenelBilgiler hk : ilah)
        {
            System.out.println("Sehir :"+hk.getEvAdres().getSehir());
            System.out.println("İsim : "+hk.getAd());
            System.out.println("Soyad: "+hk.getSoyad());
            System.out.println("Yas : "+hk.getYas()); 
           
      Query query2 = session.getNamedQuery("kimlikbilgi.findByID").setInteger("numara", hk.getKimlikbilgileri().getId());
      List<KimlikBilgileri> kimlikbilgi = query2.list();
            for(KimlikBilgileri km : kimlikbilgi)
            {
                System.out.println("TC : "+km.getKimlikno());
            }
      Query query3 = session.getNamedQuery("uni.findById").setInteger("numara", hk.getUniversite().getId());
      List<Universite> uniebilgi = query3.list();
           for(Universite un : uniebilgi)
           {
               System.out.println(un.getUniad());
           } 
        }//for
        */
        
        
       
        
        
        
    }
    
}
