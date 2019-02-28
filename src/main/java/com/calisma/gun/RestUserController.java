package com.calisma.gun;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Hiber.HibernateUtil;
import pojo.Kisi;

@RestController
@RequestMapping(value = "/api")
public class RestUserController {
	
	SessionFactory sf = HibernateUtil.getSessionFactory();
	
	@RequestMapping(value ="/alluser", method= RequestMethod.POST)
	public HashMap<String, Object> AllUser(@RequestParam String data) {
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		
		Session sesi = sf.openSession();
		List<Kisi> kls = sesi.createQuery("from Kisi where kname = ?").setParameter(0, data).list();
		
		hm.put("durum", true);
		hm.put("mesaj", "İşlem Başarılı");
		hm.put("kisiler", kls);
		
		//hm.put("Adi", "Bilmem");
		
		return hm;
	}
	

}
