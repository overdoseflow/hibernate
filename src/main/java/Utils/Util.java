package Utils;

import java.util.Base64;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Util {
	
	static public String control(HttpServletRequest req, String pageName) 
	{
		
		//cerez kontrol
		if (req.getCookies() != null) {
			Cookie[] dizi = req.getCookies();
			for (Cookie item : dizi) {
				if (item.getName().equals("remember")) {
					String val = item.getValue();
					req.getSession().setAttribute("kulid", val);
					break;
				}
			}	
		}

		
		
		String map = "";
		boolean statu = req.getSession().getAttribute("kulid") != null;
		if (statu) {
			map = pageName;
		}
		else {
			map = "redirect:/";
		}
		return map;
	}
	
	public static String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
	
	
	public static String sifrele(String data, int i) {
		byte[] dizi = null;
		Random rd = new Random();
		int ri = rd.nextInt(899) + 100;
		for (int j = 0; j < i; j++) {
			dizi = Base64.getEncoder().encode(data.getBytes());
			data = new String(dizi);
		}
		String sifrelenmis = new String(dizi) + MD5("" + ri);
		System.out.println("sifrelenmis" + sifrelenmis);
		return sifrelenmis;
	}

	public static String sifreCoz(String data, int i) {
		byte[] dizi = null;
		data = data.substring(0, data.length() - 32);
		for (int j = 0; j < i; j++) {
			dizi = Base64.getDecoder().decode(data.getBytes());
			data = new String(dizi);
		}
		String cozulmus = new String(dizi);
		System.out.println("cozulmus" + cozulmus);
		return cozulmus;
	}

}
