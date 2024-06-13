
package daoList;

import java.util.ArrayList;

import IF.PaymentList;
import domain.Payment;

public class PaymentListImpl implements PaymentList {

	private ArrayList<Payment> paymentList;
	public Payment m_Payment;

	public PaymentListImpl(){
		this.paymentList = new ArrayList<>();

	}

	public void finalize() throws Throwable {

	}

	public boolean add(Payment Payment){
		for (Payment payment : paymentList) {
			if (payment.getContractID() == Payment.getContractID())
				return false;
		}
		this.paymentList.add(Payment);
		return true;
	}

	public boolean delete(int paymentID){
		for (Payment payment : paymentList) {
			if (payment.getPaymentID() == paymentID) {
				paymentList.remove(payment);
				return true;
			}
		}
		return false;
	}

	public Payment retrieve(int paymentID){
		for (Payment payment: paymentList) {
			if (payment.getPaymentID() == paymentID) {
				return payment;
			}
		}
		return null;
	}
	
	public ArrayList<Payment> retrieveAll(){
		return this.paymentList;
	}
	
	public ArrayList<Payment> retrieveByCustomerID(int customerID){
		ArrayList<Payment> customerPayment = new ArrayList<>();
		for (Payment payment: paymentList) {
			if (payment.getCustomerID() == customerID) {
				customerPayment.add(payment);
			}
		}
		return customerPayment;
	}

	public boolean update(Payment payment, int paymentID){
		return false;
	}
}