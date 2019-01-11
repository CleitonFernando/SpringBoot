package com.nelioalves.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.nelioalves.cursomc.domain.Pedido;

/*
 * email de serviços 
 */
public interface EmailService {

		public void sendOrderConfirmationEmail(Pedido obj); /*confirmacao de email*/
		
		public void sendEmail(SimpleMailMessage msg); /* enviar email*/
		
		/*ENVIAR O EMAIL HTML*/
		void sendOrderConfirmationHtmlEmail(Pedido obj);
		
		void sendHtmlEmail(MimeMessage msg);
}
