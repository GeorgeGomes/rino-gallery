package auth.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq")   
    @GeneratedValue(generator = "user_seq", strategy = GenerationType.IDENTITY)
	@Column(name="id_user", nullable=false)
	private Long idUser;
	
	@Column(name="username", nullable=false)
    private String username;
	
	@Column(name="password", nullable=false)
    private String password;
	
	@Column(name="company", nullable=true)
	private String company;
	
	@Column(name="email", nullable=false)
	private String email;
	
    @Column(name = "enable", nullable=false)
    private boolean enable;
    
    @Column(name = "token", nullable=false)
    private String token;
    
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
    		name="user_role", 
    		joinColumns={@JoinColumn(name="id_user")}, 
    		inverseJoinColumns={@JoinColumn(name="id_role")}
    		)
    private List<Role> roles;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", username=" + username + ", password=" + password + ", company=" + company
				+ ", email=" + email + ", enable=" + enable + ", token=" + token + ", roles=" + roles + "]";
	}

}
