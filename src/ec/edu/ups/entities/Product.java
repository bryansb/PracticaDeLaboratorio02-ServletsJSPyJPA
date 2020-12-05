package ec.edu.ups.entities;

import java.io.Serializable;
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
 * Entity implementation class for Entity: Product
 *
 */
@Entity
@Table(name="PRODUCTS")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_id")
	private int proId;
	
	@Column(name = "pro_name", length = 255, nullable = false)
	private String proName;
	
	@Column(name = "pro_stock")
	private int proStock;
	
	@Column(name = "pro_price", precision = 12, scale = 2)
	private double proPrice;
	
	@Column(name = "pro_deleted", columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean proDeleted;

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "detProduct")
	private List<BillDetail> proBillDetails;
	
	@ManyToOne
	@JoinColumn
	private Company proCompany;
	
	@ManyToOne
	@JoinColumn
	private Category proCategory;
	
	public Product() {
		super();
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getProStock() {
		return proStock;
	}

	public void setProStock(int proStock) {
		this.proStock = proStock;
	}

	public double getProPrice() {
		return proPrice;
	}

	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}

	public boolean isProDeleted() {
		return proDeleted;
	}

	public void setProDeleted(boolean proDeleted) {
		this.proDeleted = proDeleted;
	}

	public List<BillDetail> getProBillDetail() {
		return proBillDetails;
	}

	public void setProBillDetail(List<BillDetail> proBillDetails) {
		this.proBillDetails = proBillDetails;
	}

	public Company getProCompany() {
		return proCompany;
	}

	public void setProCompany(Company proCompany) {
		this.proCompany = proCompany;
	}

	public Category getProCategory() {
		return proCategory;
	}

	public void setProCategory(Category proCategory) {
		this.proCategory = proCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((proBillDetails == null) ? 0 : proBillDetails.hashCode());
		result = prime * result + ((proCategory == null) ? 0 : proCategory.hashCode());
		result = prime * result + ((proCompany == null) ? 0 : proCompany.hashCode());
		result = prime * result + (proDeleted ? 1231 : 1237);
		result = prime * result + proId;
		result = prime * result + ((proName == null) ? 0 : proName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(proPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + proStock;
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
		Product other = (Product) obj;
		if (proBillDetails == null) {
			if (other.proBillDetails != null)
				return false;
		} else if (!proBillDetails.equals(other.proBillDetails))
			return false;
		if (proCategory == null) {
			if (other.proCategory != null)
				return false;
		} else if (!proCategory.equals(other.proCategory))
			return false;
		if (proCompany == null) {
			if (other.proCompany != null)
				return false;
		} else if (!proCompany.equals(other.proCompany))
			return false;
		if (proDeleted != other.proDeleted)
			return false;
		if (proId != other.proId)
			return false;
		if (proName == null) {
			if (other.proName != null)
				return false;
		} else if (!proName.equals(other.proName))
			return false;
		if (Double.doubleToLongBits(proPrice) != Double.doubleToLongBits(other.proPrice))
			return false;
		if (proStock != other.proStock)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [proId=" + proId + ", proName=" + proName + ", proStock=" + proStock + ", proPrice=" + proPrice
				+ ", proDeleted=" + proDeleted + ", proBillDetail=" + proBillDetails + ", proCompany=" + proCompany
				+ ", proCategory=" + proCategory + "]";
	}
   
}
