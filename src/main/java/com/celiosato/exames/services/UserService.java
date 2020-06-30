package com.celiosato.exames.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.celiosato.exames.security.UserSS;



public class UserService {

	//Metodo que retorna um usuario loogado
		public static UserSS authenticated() {
			try {
				return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			} catch (Exception e) {
				return null;
			}
		}

	}
