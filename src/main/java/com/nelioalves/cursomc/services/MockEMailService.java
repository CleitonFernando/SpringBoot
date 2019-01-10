package com.nelioalves.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEMailService extends AbstractEmailService{

	private static final Logger LOG = LoggerFactory.getLogger(MockEMailService.class); /// USAR O LOG DO JAVA
	@Override
	public void senEmail(SimpleMailMessage msg) {
		// TODO Auto-generated method stub
		LOG.info("simulando envio de email...");
		LOG.info(msg.toString());
		LOG.info("EMAIL ENVIADO");
	}

}
