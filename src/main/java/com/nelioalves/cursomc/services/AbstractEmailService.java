package com.nelioalves.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.nelioalves.cursomc.domain.Pedido;

/*
 * template method voce consegue implementar um metodo baseado em metodos abstratos que depois q serao implmentados pelas implementacao da interfaces
 */
public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}") // pegando rementente de propriedades
	private String  sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		senEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		// TODO Auto-generated method stub
		SimpleMailMessage sm = new SimpleMailMessage();
		
		sm.setTo(obj.getCliente().getEmail()); // destinatario
		sm.setFrom(sender); // REMENTENTE
		sm.setSubject("pedido confimardo! Codigo: "+obj.getId()); // assunto
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());// transforma o objeto em sttring
		return sm;
	}


}
