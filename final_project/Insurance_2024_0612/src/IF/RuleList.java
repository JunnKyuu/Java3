package IF;

import java.util.ArrayList;

import domain.Rule;

public interface RuleList {
	public boolean add(Rule rule);
	public Rule retrieveById(int ruleID);
	public void update(int ruleID, Rule rule);
	public ArrayList<Rule> retrieveAll();
	public boolean deleteById(int i);
}