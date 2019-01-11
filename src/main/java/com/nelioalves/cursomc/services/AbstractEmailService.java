package com.nelioalves.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.nelioalves.cursomc.domain.Pedido;

/*
 * template method voce consegue implementar um metodo baseado em metodos abstratos que depois q serao implmentados pelas implementacao da interfaces
 */
public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}") // pegando rementente de propriedades
	private String  sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
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
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context(); // constante do thymeleaf para acessar o template
		context.setVariable("pedido", obj);// apelido no template e valor
		return templateEngine.process("Email/confirmacaoPedido", context);// caminho retorna o html em formato string
		
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromPedido(obj); //prepara a a mensagem
			sendHtmlEmail(mm);
			
		}catch(MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
		
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		// TODO Auto-generated method stub
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh =new MimeMessageHelper(mimeMessage,true);
		mmh.setTo(obj.getCliente().getEmail());//destinario
		mmh.setFrom(sender); // remente
		mmh.setSubject("Pedido confirmado ! Código: " +obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj),true);
		
		return mimeMessage;
	}

}
