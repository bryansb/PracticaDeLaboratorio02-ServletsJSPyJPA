package ec.edu.ups.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name="USERS")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="use_id")
	private int useId;
	
	@Column(name="use_email", length=255, nullable=false, unique=true)
	private String useEmail;
	
	@Column(name="use_username", length=255, nullable=false, unique=true)
	private String useUsername;
	
	@Column(name="use_password", length=255, nullable=false)
	private String usePassword;
	
	@Column(name="use_name", length=255, nullable=false)
	private String useName;
	
	@Column(name="use_lastname", length=255, nullable=false)
	private String useLastname;
	
	@Column(name="use_role", length=255, nullable=false)
	private char useRole;
	
	@Column(name="use_deleted", columnDefinition="BOOLEAN DEFAULT 0")
	private boolean useDeleted;
	
	@ManyToOne
	@JoinColumn
	private Company useCompany;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "heaUser")
	private List<BillHead> useBillHeads = new ArrayList<BillHead>();
	
	public User() {
		super();
	}

	public int getUseId() {
		return useId;
	}

	public void setUseId(int useId) {
		this.useId = useId;
	}

	public String getUseEmail() {
		return useEmail;
	}

	public void setUseEmail(String useEmail) {
		this.useEmail = useEmail;
	}

	public String getUsePassword() {
		return usePassword;
	}

	public void setUsePassword(String usePassword) {
		this.usePassword = usePassword;
	}

	public String getUseName() {
		return useName;
	}

	public void setUseName(String useName) {
		this.useName = useName;
	}

	public String getUseLastname() {
		return useLastname;
	}

	public void setUseLastname(String useLastname) {
		this.useLastname = useLastname;
	}

	public char getUseRole() {
		return useRole;
	}

	public void setUseRole(char useRole) {
		this.useRole = useRole;
	}

	public boolean isUseDeleted() {
		return useDeleted;
	}

	public void setUseDeleted(boolean useDeleted) {
		this.useDeleted = useDeleted;
	}
	
	public String getUseUsername() {
		return useUsername;
	}

	public void setUseUsername(String useUsername) {
		this.useUsername = useUsername;
	}

	public Company getUseCompany() {
		return useCompany;
	}

	public void setUseCompany(Company useCompany) {
		this.useCompany = useCompany;
	}
	
	public List<BillHead> getUseBillHeads() {
		return useBillHeads;
	}

	public void setUseBillHeads(List<BillHead> useBillHeads) {
		this.useBillHeads = useBillHeads;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((useBillHeads == null) ? 0 : useBillHeads.hashCode());
		result = prime * result + ((useCompany == null) ? 0 : useCompany.hashCode());
		result = prime * result + (useDeleted ? 1231 : 1237);
		result = prime * result + ((useEmail == null) ? 0 : useEmail.hashCode());
		result = prime * result + useId;
		result = prime * result + ((useLastname == null) ? 0 : useLastname.hashCode());
		result = prime * result + ((useName == null) ? 0 : useName.hashCode());
		result = prime * result + ((usePassword == null) ? 0 : usePassword.hashCode());
		result = prime * result + useRole;
		result = prime * result + ((useUsername == null) ? 0 : useUsername.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (useBillHeads == null) {
			if (other.useBillHeads != null)
				return false;
		} else if (!useBillHeads.equals(other.useBillHeads))
			return false;
		if (useCompany == null) {
			if (other.useCompany != null)
				return false;
		} else if (!useCompany.equals(other.useCompany))
			return false;
		if (useDeleted != other.useDeleted)
			return false;
		if (useEmail == null) {
			if (other.useEmail != null)
				return false;
		} else if (!useEmail.equals(other.useEmail))
			return false;
		if (useId != other.useId)
			return false;
		if (useLastname == null) {
			if (other.useLastname != null)
				return false;
		} else if (!useLastname.equals(other.useLastname))
			return false;
		if (useName == null) {
			if (other.useName != null)
				return false;
		} else if (!useName.equals(other.useName))
			return false;
		if (usePassword == null) {
			if (other.usePassword != null)
				return false;
		} else if (!usePassword.equals(other.usePassword))
			return false;
		if (useRole != other.useRole)
			return false;
		if (useUsername == null) {
			if (other.useUsername != null)
				return false;
		} else if (!useUsername.equals(other.useUsername))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [useId=" + useId + ", useEmail=" + useEmail + ", useUsername=" + useUsername + ", usePassword="
				+ usePassword + ", useName=" + useName + ", useLastname=" + useLastname + ", useRole=" + useRole
				+ ", useDeleted=" + useDeleted + ", useCompany=" + useCompany + ", useBillHeads=" + useBillHeads + "]";
	}
}
