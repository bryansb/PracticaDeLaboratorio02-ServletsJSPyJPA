package ec.edu.ups.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: BillHead
 *
 */
@Entity
@Table(name="BILL_HEADS")

public class BillHead implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int heaId;
	private double heaSubtotal;
	private double heaVat;
	private Calendar heaDate;
	private char heaStatus;
	private double heaTotal;
	private boolean heaDeleted;

	public BillHead() {
		super();
	}
   
}
