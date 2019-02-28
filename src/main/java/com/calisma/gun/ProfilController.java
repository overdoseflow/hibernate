package com.calisma.gun;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Utils.Util;

@Controller
public class ProfilController {

	@RequestMapping(value = "/profil", method = RequestMethod.GET)
	public String profilOpen(HttpServletRequest req) {
		return Util.control(req, "profil");
	}
}
