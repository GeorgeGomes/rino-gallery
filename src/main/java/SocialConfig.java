
 
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;

import br.com.rino.util.JSFUtil;
 
/**
 *
 * @author Lessandro
 */
@ManagedBean(name = "socialConfig")
@SessionScoped
public class SocialConfig implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constantes referenciadas na classe Constantes.java
    public static final String MESSAGE_PROPERTIES_PATH = "br.com.pyramides.boundle.message";
    public static final String PAGINA_AUTENTICACAO_LOGIN = "/loginAuthenticate.xhtml";
    public static final String APP_ID = "SEU_APP_ID";
    public static final String APP_SECRET = "SEU_APP_SECRET";
    public static final String PAGINA_LOGIN = "/login.xhtml";
    public void autenticarSpringComFacebook() { 
        try {
        	FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("632009673603742", "c454f81ed99b660988b56b030502f5a0");
            OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
            OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
            oAuth2Parameters.setScope("public_profile");
            oAuth2Parameters.add("display", "popup");
            String serverPath =  "http://localhost:8090/rino-gallery/app/token.xhtml";
            oAuth2Parameters.setRedirectUri(serverPath);
            String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
            
            JSFUtil.redirect(authorizeUrl);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
 
}