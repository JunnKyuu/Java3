package domain;

public class CardPayment {
	private String cardNum;
	private String cvcNum;
	private String password;

	public CardPayment(){}
	public String toString() {
		return "CardPayment{" +
	            "  cardNum=" + cardNum +
	            ", cvcNum=" + cvcNum + '\'' +
	            ", password=" + password +
	            '}';
	}
	public void finalize() throws Throwable {}
	public void updateCardPaymentInfo(CardPayment cardPayment){}
	public String getCardNum() {return cardNum;}
	public String getCvcNum() {return cvcNum;}
	public String getPassword() {return password;}
	public void setCvcNum(String cvcNum) {this.cvcNum = cvcNum;}
	public void setCardNum(String cardNum) {this.cardNum = cardNum;}
	public void setPassword(String password) {this.password = password;}
}