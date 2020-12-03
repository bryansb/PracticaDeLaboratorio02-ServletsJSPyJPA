package ec.edu.ups.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Company
 *
 */
@Entity
@Table(name="COMPANIES")
public class Company implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="com_id")
	private int comId;
	
	@Column(name="com_name", length=255, nullable=false, unique=true)
	private String comName;
	
	@Column(name="com_deleted", columnDefinition="BOOLEAN DEFAULT 0")
	private boolean comDeleted;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	private Set<User> users = new HashSet<User>();

	public Company() {
		super();
	}

	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public boolean isComDeleted() {
		return comDeleted;
	}

	public void setComDeleted(boolean comDeleted) {
		this.comDeleted = comDeleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (comDeleted ? 1231 : 1237);
		result = prime * result + comId;
		result = prime * result + ((comName == null) ? 0 : comName.hashCode());
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
		Company other = (Company) obj;
		if (comDeleted != other.comDeleted)
			return false;
		if (comId != other.comId)
			return false;
		if (comName == null) {
			if (other.comName != null)
				return false;
		} else if (!comName.equals(other.comName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Company [comId=" + comId + ", comName=" + comName + ", comDeleted=" + comDeleted + "]";
	}
	
}
