package auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
 
@Entity
@Table(name="role")
public class Role implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "role_seq", sequenceName = "role_seq")   
    @GeneratedValue(generator = "role_seq", strategy = GenerationType.IDENTITY)
	@Column(name="id_role")
	private Long idRole;
	@Column(name="name")
    private String name;
	public Long getIdRole() {
		return idRole;
	}
	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", name=" + name + "]";
	}
}