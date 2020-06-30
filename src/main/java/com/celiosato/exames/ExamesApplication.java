package com.celiosato.exames;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.celiosato.exames.domain.Eritrograma;
import com.celiosato.exames.domain.Usuario;
import com.celiosato.exames.domain.enums.Perfil;
import com.celiosato.exames.domain.enums.TipoUsuario;
import com.celiosato.exames.repositories.EritrogramaRepository;
import com.celiosato.exames.repositories.UsuarioRepository;

@SpringBootApplication
public class ExamesApplication implements CommandLineRunner{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EritrogramaRepository eritrogramaRepository;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario user1 = new Usuario(null, "Celio Sato", "10872972407", passEncoder.encode("123"),"mago70@gmail.com", "92981293679", TipoUsuario.ADMINISTRADOR);
		user1.addPerfil(Perfil.ADMIN);
		
		Usuario user2 = new Usuario(null, "Midori Yano", "27685498725", passEncoder.encode("123"),"midori@gmail.com", "92981457587", TipoUsuario.ADMINISTRADOR);
		user2.addPerfil(Perfil.ADMIN);
		
		Usuario user3 = new Usuario(null, "Bruna Yumi Sato", "32165498744", passEncoder.encode("123"),"bruna@gmail.com", "92984578658", TipoUsuario.PACIENTE);
		user3.addPerfil(Perfil.ADMIN);
		
		Usuario user4 = new Usuario(null, "Felipe Yuto Sato", "12345678999", passEncoder.encode("123"),"felipe@gmail.com", "92933665588", TipoUsuario.PACIENTE);
		Usuario user5 = new Usuario(null, "Igor Yuske Sato", "22255588877", passEncoder.encode("123"),"igor@gmail.com", "92955887744", TipoUsuario.PACIENTE);
		Usuario user6 = new Usuario(null, "Miya Yano", "10872972407", passEncoder.encode("123"),"mago701@gmail.com", "92981293679", TipoUsuario.ADMINISTRADOR);
		Usuario user7 = new Usuario(null, "Eme Yano", "27685498725", passEncoder.encode("123"),"midori1@gmail.com", "92981457587", TipoUsuario.ADMINISTRADOR);
		Usuario user8 = new Usuario(null, "Yuki Yano", "32165498744", passEncoder.encode("123"),"bruna1@gmail.com", "92984578658", TipoUsuario.PACIENTE);
		Usuario user9 = new Usuario(null, "Yuji Yano", "12345678999", passEncoder.encode("123"),"felipe1@gmail.com", "92933665588", TipoUsuario.PACIENTE);
		Usuario user10 = new Usuario(null, "Alvineide Yano", "22255588877", passEncoder.encode("123"),"igor1@gmail.com", "92955887744", TipoUsuario.PACIENTE);
		
		Eritrograma eri1 = new Eritrograma(null, 12.6, 58.9, "Teste para primeiro exame", user1);
		Eritrograma eri2 = new Eritrograma(null, 98.0, 14.7, "Teste para segundo exame", user2);
		Eritrograma eri3 = new Eritrograma(null, 65.2, 34.0, "Teste para terceiro exame", user3);
		
		
		usuarioRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));
		eritrogramaRepository.saveAll(Arrays.asList(eri1, eri2, eri3));
		
	}
}