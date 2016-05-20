package br.com.rino.bean;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

import br.com.rino.dao.ConfigGeneralDAO;
import br.com.rino.dao.PhotoDAO;
import br.com.rino.entity.ConfigGeneral;
import br.com.rino.entity.Photo;
import br.com.rino.util.JSFUtil;

@ManagedBean(name = "photoBean")
@SessionScoped
public class PhotoBean {

	private Photo photo = new Photo();
	private PhotoDAO photoDAO = new PhotoDAO();
	private ConfigGeneralDAO configGeneralDAO = new ConfigGeneralDAO();
	private String image;
	private String token;
	private String url;
	
	public void prepareUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void editPhoto(String nomeFoto) {
		this.setPhoto(photoDAO.edit(nomeFoto));
	}
	
	public void editPhoto() {
		this.setPhoto(photoDAO.edit(image));
	}

	public void token(String code){
		token = code;
		
//		Facebook facebook = new FacebookTemplate(code);
//		
//		FacebookLink link = new FacebookLink("http://www.springsource.org/spring-social", 
//		        "Spring Social", 
//		        "The Spring Social Project", 
//		        "Spring Social is an extension to Spring to enable applications to connect with service providers.");
//		
//		facebook.feedOperations().postLink("I'm trying out Spring Social!", link);

		//JSFUtil.redirect("https://www.facebook.com/logout.php?next=http%3A%2F%2Flocalhost:8090%2Frino-gallery%2Fservlet%2FendPhoto&access_token="+code);
		
	}
	
	public void deletePhoto(Photo photo) {

		photoDAO.delete(photo);

		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
				"Registro deletado com sucesso!");
		context.addMessage("messages", message);
	}

	public void sendMailAll() throws IOException, AddressException, MessagingException {
		List<Photo> photoList = this.listPhotoPending();
		if (photoList != null) {
			Iterator<Photo> photoIterator = photoList.iterator();
			while (photoIterator.hasNext()) {
				Photo photo = photoIterator.next();
				this.sendMailAttach(photo);
			}
		}
	}

	public void sendMailAttach(Photo photo) throws AddressException, MessagingException {
		if (photo.getEmail() != null && !"".equalsIgnoreCase(photo.getEmail())) {

			final ConfigGeneral configGeneral = configGeneralDAO.getList().get(0);

			Properties props = new Properties();

			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", configGeneral.getEmailHost());
			props.put("mail.smtp.socketFactory.port", configGeneral.getEmailPort());
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", configGeneral.getEmailPort());
			props.put("mail.smtp.ssl.enable", false);
			props.put("mail.smtp.starttls.enable", false);

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(configGeneral.getEmailSender(), configGeneral.getEmailPassword());
				}
			});


			Transport transport = session.getTransport();

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(configGeneral.getEmailSender())); // Remetente

			Address[] toUser = InternetAddress // Destinat�rio(s)
					.parse(photo.getEmail());

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(configGeneral.getEmailSubject() + " - " + photo.getNomeEvento());// Assunto

			//
			// This HTML mail have to 2 part, the BODY and the embedded image
			//
			MimeMultipart multipart = new MimeMultipart("related");

			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "<h4>" + configGeneral.getEmailBody() + "</h4>";
			htmlText += "<h1>" + photo.getNomeEvento() + "</1>";
			htmlText += "<h3>" + photo.getDescricaoEvento() + "</h3>";
			htmlText += "<h4><a href='http://www.rinomachine.com.br/rino-gallery/app/gallery/photo.xhtml?file="
					+ photo.getNomeFoto() + "'>Ver a foto pelo site!</a></h4>";
			htmlText += "<img src=\"cid:image\">";

			messageBodyPart.setContent(htmlText, "text/html");

			// add it
			multipart.addBodyPart(messageBodyPart);

			// second part (the image)
			messageBodyPart = new MimeBodyPart();

			ByteArrayDataSource rawData = new ByteArrayDataSource(photo.getFile(), "application/octet-stream");
			DataHandler data = new DataHandler(rawData);

			messageBodyPart.setDataHandler(data);
			messageBodyPart.setHeader("Content-ID", "<image>");

			// add it
			multipart.addBodyPart(messageBodyPart);

			// put everything together
			message.setContent(multipart);

			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();

			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage messageFaces = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"E-mail enviado com sucesso para (" + photo.getEmail() + ")!");
			context.addMessage("messages", messageFaces);
		}
		
		photo.setPendente("");
		photoDAO.update(photo);
	}

	public void sendMail(String destinatarios) {

		final ConfigGeneral configGeneral = configGeneralDAO.getList().get(0);

		Properties props = new Properties();
		/** Par�metros de conex�o com servidor Gmail */
		props.put("mail.smtp.host", configGeneral.getEmailHost());
		props.put("mail.smtp.socketFactory.port", configGeneral.getEmailPort());
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", configGeneral.getEmailPort());

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(configGeneral.getEmailSender(), configGeneral.getEmailPassword());
			}
		});

		/** Ativa Debug para sess�o */
		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(configGeneral.getEmailSender())); // Remetente

			Address[] toUser = InternetAddress // Destinat�rio(s)
					.parse(destinatarios);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(configGeneral.getEmailSubject());// Assunto
			message.setText(configGeneral.getEmailBody());
			// message.s

			/** M�todo para enviar a mensagem criada */
			Transport.send(message);

			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage messageFaces = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"E-mail enviado com sucesso para (" + destinatarios + ")!");
			context.addMessage("messages", messageFaces);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Photo> listPhoto() {
		return photoDAO.getList();
	}

	public List<Photo> listPhotoPending() {
		return photoDAO.getListPending();
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
}
