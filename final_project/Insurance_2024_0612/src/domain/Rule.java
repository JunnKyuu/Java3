package domain;

public class Rule {
	private String ruleDetail;
	private int ruleID;
	private String ruleName;

	public Rule(){}
	public void finalize() throws Throwable {}
	public String getRuleDetail() {return ruleDetail;}
	public int getRuleID() {return ruleID;}
	public String getRuleName() {return ruleName;}
	public void setRuleDetail(String ruleDetail) {this.ruleDetail = ruleDetail;}
	public void setRuleID(int ruleID) {this.ruleID = ruleID;}
	public void setRuleName(String ruleName) {this.ruleName = ruleName;}

}