package ec.edu.ups.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: BillDetail
 *
 */
@Entity
@Table(name = "BILL_DETAILS")
public class BillDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "det_id")
	private int detId;
	
	@Column(name = "det_amount")
	private int detAmount;
	
	@Column(name = "det_unit_price", precision = 12, scale = 2)
	private double detUnitPrice;
	
	@Column(name = "det_total", precision = 12, scale = 2)
	private double detTotal;
	
	@Column(name = "det_deleted", columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean detDeleted;

	@ManyToOne
	@JoinColumn
	private BillHead detBillHead;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "proBillDetail")
	private Product detProduct;
	
	public BillDetail() {
		super();
	}

	public int getDetId() {
		return detId;
	}

	public void setDetId(int detId) {
		this.detId = detId;
	}

	public int getDetAmount() {
		return detAmount;
	}

	public void setDetAmount(int detAmount) {
		this.detAmount = detAmount;
	}

	public double getDetUnitPrice() {
		return detUnitPrice;
	}

	public void setDetUnitPrice(double detUnitPrice) {
		this.detUnitPrice = detUnitPrice;
	}

	public double getDetTotal() {
		return detTotal;
	}

	public void setDetTotal(double detTotal) {
		this.detTotal = detTotal;
	}

	public boolean isDetDeleted() {
		return detDeleted;
	}

	public void setDetDeleted(boolean detDeleted) {
		this.detDeleted = detDeleted;
	}

	public BillHead getDetBillHead() {
		return detBillHead;
	}

	public void setDetBillHead(BillHead detBillHead) {
		this.detBillHead = detBillHead;
	}

	public Product getDetProduct() {
		return detProduct;
	}

	public void setDetProduct(Product detProduct) {
		this.detProduct = detProduct;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + detAmount;
		result = prime * result + ((detBillHead == null) ? 0 : detBillHead.hashCode());
		result = prime * result + (detDeleted ? 1231 : 1237);
		result = prime * result + detId;
		result = prime * result + ((detProduct == null) ? 0 : detProduct.hashCode());
		long temp;
		temp = Double.doubleToLongBits(detTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(detUnitPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		BillDetail other = (BillDetail) obj;
		if (detAmount != other.detAmount)
			return false;
		if (detBillHead == null) {
			if (other.detBillHead != null)
				return false;
		} else if (!detBillHead.equals(other.detBillHead))
			return false;
		if (detDeleted != other.detDeleted)
			return false;
		if (detId != other.detId)
			return false;
		if (detProduct == null) {
			if (other.detProduct != null)
				return false;
		} else if (!detProduct.equals(other.detProduct))
			return false;
		if (Double.doubleToLongBits(detTotal) != Double.doubleToLongBits(other.detTotal))
			return false;
		if (Double.doubleToLongBits(detUnitPrice) != Double.doubleToLongBits(other.detUnitPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BillDetail [detId=" + detId + ", detAmount=" + detAmount + ", detUnitPrice=" + detUnitPrice
				+ ", detTotal=" + detTotal + ", detDeleted=" + detDeleted + ", detBillHead=" + detBillHead
				+ ", detProduct=" + detProduct + "]";
	}
	
}
