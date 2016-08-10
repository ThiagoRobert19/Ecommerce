package br.com.caelum.vraptor.controller;

import javax.inject.Inject;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.simplemail.Mailer;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class MailerController {
	private final Result result;
	private final Validator validator;
	private final Mailer mailer;
	
	@Inject
	public MailerController(Result result, Validator validator, Mailer mailer) {
		this.result = result;
		this.validator = validator;
		this.mailer = mailer;
	}
	
	public MailerController() {
		this(null, null, null);
	}
	@Post("/enviaEmail")
	public void enviaPedidoDeNovosItens(String correio_eletronico) throws EmailException {
		System.out.println("Email#########################");
		System.out.println(correio_eletronico);
		Email email = new SimpleEmail();
		email.setSubject("New Shoes");
		email.setMsg("Seja bem vindo ao New Shoes,\n "
				+ "Ao realizar o cadastro de email você está aceitando automaticamente\n "
				+ "a receber e-mails com nossas novidades!\n"
				+ " Aproveite as promoções");
		email.addTo(correio_eletronico);
		mailer.send(email);
		result.redirectTo(IndexController.class).index();
	}
}
