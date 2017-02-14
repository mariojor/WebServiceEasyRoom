package br.com.droid.util;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Random;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SendEmail {

	public static void enviarEmail(String emailUsuario, String novaSenha)
			throws EmailException {
		System.setProperty("http.proxyPort", "portaDoSeuProxy");
		Authenticator.setDefault(new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("easyroomquartos@gmail.com",
						"ete#2aderico".toCharArray());
			}
		});

		SimpleEmail email = new SimpleEmail();
		// Utilize o hostname do seu provedor de email
		System.out.println("alterando hostname...");
		email.setHostName("smtp.gmail.com");
		// Quando a porta utilizada não é a padrão (gmail = 465)
		email.setSmtpPort(465);
		// Adicione os destinatários
		email.addTo(emailUsuario, "EasyRoom");
		// Configure o seu email do qual enviará
		email.setFrom("easyroomquartos@gmail.com", "EasyRoom");
		// Adicione um assunto
		email.setSubject("Easy Room Nova Senha");
		// Adicione a mensagem do email
		email.setMsg("Sua nova senha:" + novaSenha);
		// Para autenticar no servidor é necessário chamar os dois métodos
		// abaixo
		System.out.println("autenticando...");
		email.setSSL(true);
		email.setAuthentication("easyroomquartos@gmail.com", "ete#2aderico");
		System.out.println("enviando...");
		email.send();
		System.out.println("Email enviado!");
	}

	public static String gerarSenhaAleatoria() {

		String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZabcdefghijlmnopqrstuvxz0123456789";

		Random random = new Random();

		String armazenaChaves = "";
		int index = -1;
		for (int i = 0; i < 9; i++) {
			index = random.nextInt(letras.length());
			armazenaChaves += letras.substring(index, index + 1);
		}
		return armazenaChaves;
	}

}
