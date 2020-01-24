package com.macgarcia.gpweb.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.macgarcia.gpweb.model.Mes;

public class Ferramentas {
	
	public static String md5(String palavra) {
		String s = palavra;
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(s.getBytes(), 0, s.length());
			return new BigInteger(1, m.digest()).toString(16).toLowerCase();
		} catch (NoSuchAlgorithmException ex) {
		}
		return null;
	}
	
    /***
     * Método que retorna a descrição do mes a partir do seu numero.
     * @param codigoMes
     * @return 
     */
    public String getMesPorExtenso(int codigoMes) {
        switch(codigoMes) {
            case 1:   return "Janeiro";
            case 2:   return "Fevereiro";
            case 3:   return "Março";
            case 4:   return "Abril";
            case 5:   return "Maio";
            case 6:   return "Junho";
            case 7:   return "Julho";
            case 8:   return "Agosto";
            case 9:   return "Setembro";
            case 10:  return "Outubro";
            case 11:  return "Novembro";
            case 12:  return "Dezembro";
            default : return "";
        }
    }
    
    /***
     * Método que devolve a lista dos meses
     * @return 
     */
    public List<Mes> getMeses() {
        List<Mes> meses = new ArrayList<>();
       for(int i = 1; i <= 12; i++) {
           Mes m = new Mes(i, this.getMesPorExtenso(i));
           meses.add(m);
       }
       return meses;
    }
    
    public Mes getMes(Integer codigo) {
    	String mesExtenso = this.getMesPorExtenso(codigo);
    	return new Mes(codigo, mesExtenso);
    }
}
