package ahocorasick_algorithm;

import java.util.ArrayList;
import java.util.Hashtable;

public class TrieNode {

	private char letter;
	private TrieNode parent;
	private TrieNode failure;
	private ArrayList<String> results;
	private TrieNode[] transitionsArray;
	private String[] resultsAr;
	private Hashtable<Character, TrieNode> transitionHash;

	public TrieNode(TrieNode parent, char c) {
		this.letter = c;
		this.parent = parent;
		this.results = new ArrayList<>();
		this.resultsAr = new String[] {};
		this.transitionsArray = new TrieNode[] {};
		this.transitionHash = new Hashtable<Character, TrieNode>();
	}

	public void add(String result) {
		if (!results.contains(result)) {
			results.add(result);
			resultsAr = (String[]) results.toArray(resultsAr);
		}
	}

	public void insertTransition(TrieNode node) {
		transitionHash.put(node.getLetter(), node);
		TrieNode[] ar = new TrieNode[transitionHash.values().size()];
		transitionHash.values().toArray(ar);
		transitionsArray = ar;
	}

	public TrieNode getTransition(char c) {
		return (TrieNode) transitionHash.get(c);
	}

	public boolean containsTransition(char c) {
		return getTransition(c) != null;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public TrieNode getParent() {
		return parent;
	}

	public void setParent(TrieNode parent) {
		this.parent = parent;
	}

	public TrieNode getFailure() {
		return failure;
	}

	public void setFailure(TrieNode failure) {
		this.failure = failure;
	}

	public ArrayList<String> getResults() {
		return results;
	}

	public void setResults(ArrayList<String> results) {
		this.results = results;
	}

	public TrieNode[] getTransitionsArray() {
		return transitionsArray;
	}

	public void setTransitionsArray(TrieNode[] transitionsArray) {
		this.transitionsArray = transitionsArray;
	}

	public String[] getResultsAr() {
		return resultsAr;
	}

	public void setResultsAr(String[] resultsAr) {
		this.resultsAr = resultsAr;
	}

	public Hashtable<Character, TrieNode> getTransitionHash() {
		return transitionHash;
	}

	public void setTransitionHash(Hashtable<Character, TrieNode> transitionHash) {
		this.transitionHash = transitionHash;
	}

}
