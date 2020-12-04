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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "useCompany")
	private List<User> comUsers = new ArrayList<User>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "proCompany")
	private List<Product> comProducts = new ArrayList<Product>();	

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
	
	public List<User> getComUsers() {
		return comUsers;
	}

	public void setComUsers(List<User> users) {
		this.comUsers = users;
	}

	public List<Product> getComProducts() {
		return comProducts;
	}

	public void setComProducts(List<Product> products) {
		this.comProducts = products;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (comDeleted ? 1231 : 1237);
		result = prime * result + comId;
		result = prime * result + ((comName == null) ? 0 : comName.hashCode());
		result = prime * result + ((comProducts == null) ? 0 : comProducts.hashCode());
		result = prime * result + ((comUsers == null) ? 0 : comUsers.hashCode());
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
		if (comProducts == null) {
			if (other.comProducts != null)
				return false;
		} else if (!comProducts.equals(other.comProducts))
			return false;
		if (comUsers == null) {
			if (other.comUsers != null)
				return false;
		} else if (!comUsers.equals(other.comUsers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Company [comId=" + comId + ", comName=" + comName + ", comDeleted=" + comDeleted + "]";
	}
	
}
