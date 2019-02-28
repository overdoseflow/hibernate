package com.calisma.gun;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Hiber.HibernateUtil;
import Utils.Util;
import pojo.Kisi;

@Controller
public class HomeController {

	SessionFactory sf = HibernateUtil.getSessionFactory();
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		 Session sesi = sf.openSession();
		 Transaction tr = sesi.beginTransaction();
		 
		 Kisi kl = new Kisi();
		 kl.setKid(Integer.MAX_VALUE);
		 kl.setKname("Kemal");
		 kl.setKsurname("Bilirim");
		 kl.setKmail("kemal@kemal.com");
		 kl.setKpassword(Util.sifrele("12345", 5));
		 
		 int ekleid = (Integer) sesi.save(kl);
		 System.out.println("eklenen id " + ekleid);
		 tr.commit();
		 sesi.close();
		 
		return "giris";
	}
	
	@RequestMapping(value = "/sifreCoz", method = RequestMethod.GET)
	public String sifreCoz() {
		Session sesi = sf.openSession();
		
		Kisi kl = sesi.load(Kisi.class, 3);
		System.out.println("Şifre "+ kl.getKpassword());
		
		System.out.println("Çözülmüş Şifre " + Util.sifreCoz(kl.getKpassword(), 5));
		
		return "giris";
	}
	
	@RequestMapping(value = "/hiberInsert", method = RequestMethod.POST)
	private String hiberInsert(Kisi kl) {
		Session sesi = sf.openSession();
		Transaction tr = sesi.beginTransaction();
		kl.setKpassword(Util.sifrele(kl.getKpassword(), 5));
		sesi.save(kl);
		tr.commit();
		sesi.close();

		
		
		return "giris";

	}
	
	@ResponseBody
	@RequestMapping(value = "/ajaxKayit", method = RequestMethod.POST, produces ="text/html;charset = utf-8")
	private String ajaxInsert(Kisi kl) {
		Session sesi = sf.openSession();
		Transaction tr = sesi.beginTransaction();
		kl.setKpassword(Util.sifrele(kl.getKpassword(), 5));
		int id = (Integer) sesi.save(kl);
		sesi.save(kl);
		tr.commit();
		sesi.close();
		
		String cevap = "";
		if (id >0 ) {
			cevap = kl.getKname() + " " + kl.getKsurname() + " Kayıt işlmeminiz başarılı";
			
		}else {
			cevap = "hata var";
		}

		
		
		return cevap;

	}
}
