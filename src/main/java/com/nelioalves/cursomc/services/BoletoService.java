package com.nelioalves.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto,Date instanteDoPedio ) {
		
		// nos dias atuais voce utilizaria o webservice para gerar o boleto
		Calendar cal = Calendar.getInstance();// instancia uma classe do tipo calendario
		cal.setTime(instanteDoPedio); // set o dia atual de hoje
		cal.add(Calendar.DAY_OF_MONTH, 7); // acrescenta 7 dias
		pagto.setDataVencimento(cal.getTime());/// e seta 7 dias  
	}
}
