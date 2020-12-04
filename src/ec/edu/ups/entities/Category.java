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
 * Entity implementation class for Entity: Category
 *
 */
@Entity
@Table(name="CATEGORIES")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cat_id")
	private int catId;
	
	@Column(name="cat_name", length=255, nullable=false, unique=true)
	private String catName;
	
	@Column(name="cat_deleted", columnDefinition="BOOLEAN DEFAULT 0")
	private boolean catDeleted;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "proCategory")
	private List<Product> catProducts = new ArrayList<Product>();

	public Category() {
		super();
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public boolean isCatDeleted() {
		return catDeleted;
	}

	public void setCatDeleted(boolean catDeleted) {
		this.catDeleted = catDeleted;
	}

	public List<Product> getCatProducts() {
		return catProducts;
	}

	public void setCatProducts(List<Product> catProducts) {
		this.catProducts = catProducts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (catDeleted ? 1231 : 1237);
		result = prime * result + catId;
		result = prime * result + ((catName == null) ? 0 : catName.hashCode());
		result = prime * result + ((catProducts == null) ? 0 : catProducts.hashCode());
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
		Category other = (Category) obj;
		if (catDeleted != other.catDeleted)
			return false;
		if (catId != other.catId)
			return false;
		if (catName == null) {
			if (other.catName != null)
				return false;
		} else if (!catName.equals(other.catName))
			return false;
		if (catProducts == null) {
			if (other.catProducts != null)
				return false;
		} else if (!catProducts.equals(other.catProducts))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [catId=" + catId + ", catName=" + catName + ", catDeleted=" + catDeleted + "]";
	}
	
}
