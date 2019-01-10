package com.nelioalves.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.nelioalves.cursomc.domain.Pedido;

/*
 * email de serviços 
 */
public interface EmailService {

		public void sendOrderConfirmationEmail(Pedido obj); /*confirmacao de email*/
		
		public void senEmail(SimpleMailMessage msg); /* enviar email*/
}
