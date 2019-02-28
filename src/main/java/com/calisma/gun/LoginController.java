package com.calisma.gun;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Utils.DB;
import Utils.Util;

@Controller
public class LoginController {
	
	DB db = new DB();

	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	private String AdminLogin(@RequestParam String mail,
			@RequestParam String password,
			HttpServletRequest req,
			Model model, HttpServletResponse res) {
		String map = "";
		if (mail.equals("")) {
			model.addAttribute("hata", "Lütfen Mail Giriniz");
			map = "giris";
		}
		else if (password.equals("")) {
			model.addAttribute("hata", "Lütfen Şifre Giriniz");
			map = "giris";
		}
		else {
			
			try {
				String query = "select * from kisi where kmail = ? and kpassword = ?";
				PreparedStatement pre = db.preBaglan(query);
				pre.setString(1, mail);
				pre.setString(2, Util.MD5(password));
				ResultSet rs = pre.executeQuery();
				if (rs.next()) {
					//Kullancı başarılı giriş yaptı
					//oturum açılabilir
					req.getSession().setAttribute("kulid", rs.getString("kid"));
					//beni hatırla aksiyonu
					if (req.getParameter("remember") != null) {
						Cookie cerez = new Cookie("remember", rs.getString("kid"));
						cerez.setMaxAge(60);
						res.addCookie(cerez);
					}
					map = "redirect:profil";
				}else {
					model.addAttribute("hata", "Kullanıcı adı veya şifre hatalı");
					map = "giris";
				}
			} catch (Exception e) {
				System.out.println("DB Hatasi"+ e);
			}
			
		}
		
		return map;
	}
	
	// cıkıs yap
	@RequestMapping(value = "/cikis", method = RequestMethod.GET)
	public String cikis(HttpServletRequest req, HttpServletResponse res) {
		
		
		// tekil session silme
		req.getSession().removeAttribute("kulid");
		//bütün sessionları silme
		req.getSession().invalidate();
		
		//cerezleri sil
		Cookie cerez = new Cookie("remember", "");
		cerez.setMaxAge(0);
		res.addCookie(cerez);
		
		return "redirect:/";
	}
	
}
