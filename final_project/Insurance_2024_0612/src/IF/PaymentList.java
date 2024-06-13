package IF;

import java.util.ArrayList;

import domain.Payment;

public interface PaymentList {

	public boolean add(Payment Payment);
	public boolean delete(int paymentID);
	public Payment retrieve(int paymentID);
	public boolean update(Payment payment, int paymentID);
	public ArrayList<Payment> retrieveByCustomerID(int customerID);
	public ArrayList<Payment> retrieveAll();

}