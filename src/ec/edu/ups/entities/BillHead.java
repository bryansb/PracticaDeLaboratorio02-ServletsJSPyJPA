package ec.edu.ups.entities;

import java.io.Serializable;
import java.util.Calendar;
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

import ec.edu.ups.resources.Constants;

/**
 * Entity implementation class for Entity: BillHead
 *
 */
@Entity
@Table(name = "BILL_HEADS")
public class BillHead implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hea_id")
	private int heaId;
	
	@Column(name = "hea_subtotal")
	private double heaSubtotal;
	
	@Column(name = "hea_vat")
	private double heaVat;
	
	@Column(name = "hea_date")
	private Calendar heaDate;
	
	@Column(name = "hea_status", columnDefinition = "VARCHAR(1) DEFAULT 'C'")
	private char heaStatus;
	
	@Column(name = "hea_total")
	private double heaTotal;
	
	@Column(name = "hea_deleted", nullable = false, unique = false, columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean heaDeleted;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "detBillHead")
	private List<BillDetail> heaBillDetails;
	
	@ManyToOne
	@JoinColumn
	private User heaUser;
	

	public BillHead() {
		super();
		this.heaStatus = 'C';
	}

	public int getHeaId() {
		return heaId;
	}

	public void setHeaId(int heaId) {
		this.heaId = heaId;
	}

	public double getHeaSubtotal() {
		return heaSubtotal;
	}

	public void setHeaSubtotal(double heaSubtotal) {
		this.heaSubtotal = heaSubtotal;
	}

	public double getHeaVat() {
		return heaVat;
	}

	public void setHeaVat(double heaVat) {
		this.heaVat = heaVat;
	}

	public Calendar getHeaDate() {
		return heaDate;
	}

	public void setHeaDate(Calendar heaDate) {
		this.heaDate = heaDate;
	}

	public char getHeaStatus() {
		return heaStatus;
	}

	public void setHeaStatus(char heaStatus) {
		this.heaStatus = heaStatus;
	}

	public double getHeaTotal() {
		return heaTotal;
	}

	public void setHeaTotal(double heaTotal) {
		this.heaTotal = heaTotal;
	}

	public boolean isHeaDeleted() {
		return heaDeleted;
	}

	public void setHeaDeleted(boolean heaDeleted) {
		this.heaDeleted = heaDeleted;
	}

	public List<BillDetail> getHeaBillDetails() {
		return heaBillDetails;
	}

	public void setHeaBillDetails(List<BillDetail> heaBillDeatail) {
		this.heaBillDetails = heaBillDeatail;
	}

	public User getHeaUser() {
		return heaUser;
	}

	public void setHeaUser(User heaUser) {
		this.heaUser = heaUser;
	}
	
	public boolean calcualteTotal() {
		double heaSubtotal = 0.0;
		double heaVat = 0.0;
		double heaTotal = 0.0;
		try {
			for(BillDetail bd : this.heaBillDetails) {
				if(!bd.isDetDeleted()) {
					heaSubtotal += bd.getDetTotal();
				}
			}
			heaSubtotal = Math.round(heaSubtotal * 100.0)/100.0;
			setHeaSubtotal(heaSubtotal);
			heaVat = heaSubtotal * Constants.IVA;
			heaVat = Math.round(heaVat * 100.0)/100.0;
			setHeaVat(heaVat);
			heaTotal = heaSubtotal + heaVat;
			heaTotal = Math.round(heaTotal * 100.0)/100.0;
			setHeaTotal(heaTotal);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((heaBillDetails == null) ? 0 : heaBillDetails.hashCode());
		result = prime * result + ((heaDate == null) ? 0 : heaDate.hashCode());
		result = prime * result + (heaDeleted ? 1231 : 1237);
		result = prime * result + heaId;
		result = prime * result + heaStatus;
		long temp;
		temp = Double.doubleToLongBits(heaSubtotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(heaTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((heaUser == null) ? 0 : heaUser.hashCode());
		temp = Double.doubleToLongBits(heaVat);
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
		BillHead other = (BillHead) obj;
		if (heaBillDetails == null) {
			if (other.heaBillDetails != null)
				return false;
		} else if (!heaBillDetails.equals(other.heaBillDetails))
			return false;
		if (heaDate == null) {
			if (other.heaDate != null)
				return false;
		} else if (!heaDate.equals(other.heaDate))
			return false;
		if (heaDeleted != other.heaDeleted)
			return false;
		if (heaId != other.heaId)
			return false;
		if (heaStatus != other.heaStatus)
			return false;
		if (Double.doubleToLongBits(heaSubtotal) != Double.doubleToLongBits(other.heaSubtotal))
			return false;
		if (Double.doubleToLongBits(heaTotal) != Double.doubleToLongBits(other.heaTotal))
			return false;
		if (heaUser == null) {
			if (other.heaUser != null)
				return false;
		} else if (!heaUser.equals(other.heaUser))
			return false;
		if (Double.doubleToLongBits(heaVat) != Double.doubleToLongBits(other.heaVat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BillHead [heaId=" + heaId + ", heaSubtotal=" + heaSubtotal + ", heaVat=" + heaVat + ", heaDate="
				+ heaDate + ", heaStatus=" + heaStatus + ", heaTotal=" + heaTotal + ", heaDeleted=" + heaDeleted
				+ ", heaBillDetails=" + heaBillDetails + ", heaUser=" + heaUser + "]";
	}
	
}
