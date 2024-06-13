
package daoList;

import java.util.ArrayList;

import IF.RuleList;
import domain.Contract;
import domain.Rule;

public class RuleListImpl implements RuleList {

	private ArrayList<Rule> ruleList;
	public Rule m_Rule;

	public RuleListImpl(){ ruleList = new ArrayList<Rule>();}
	public void finalize() throws Throwable {}
	public void update(int ruleID, Rule rule){}
	public boolean add(Rule rule){
		for(Rule rule1 : ruleList) {
			if(rule1.getRuleID() == rule.getRuleID()) return false;
		}
		return this.ruleList.add(rule);
	}

	public Rule retrieveById(int ruleID){
		for(Rule rule1 : ruleList) {
			if(rule1.getRuleID() == ruleID) return rule1;
		}
		return null;
	}
	
	public ArrayList<Rule> retrieveAll() {return ruleList;}
	@Override
	public boolean deleteById(int ruleID) {
		Rule deleteRule = new Rule();
		for(Rule rule : ruleList) {
			if(rule.getRuleID() == ruleID) {
				deleteRule=rule;
			}
		}
		return ruleList.remove(deleteRule);
	}
}