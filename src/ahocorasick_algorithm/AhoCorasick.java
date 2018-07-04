package ahocorasick_algorithm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AhoCorasick {

	private TrieNode root;
	private String[] words;

	public AhoCorasick(String[] keywords) {
		this.words = keywords;
	}

	public void buildTrie() {

		root = new TrieNode(null, ' ');

		for (String p : words) {

			TrieNode nd = root;

			for (char c : p.toCharArray()) {
				TrieNode newNode = null;

				for (TrieNode trans : nd.getTransitionsArray()) {
					if (trans.getLetter() == c) {
						newNode = trans;
						break;
					}
				}

				if (newNode == null) {
					newNode = new TrieNode(nd, c);
					nd.insertTransition(newNode);
				}

				nd = newNode;
			}

			nd.add(p);
		}

		// failure functions
		ArrayList<TrieNode> nodes = new ArrayList<>();

		// level 1 nodes - fail to root node
		for (TrieNode nd : root.getTransitionsArray()) {
			nd.setFailure(root);

			for (TrieNode trans : nd.getTransitionsArray()) {
				nodes.add(trans);
			}
		}

		// BFS
		while (nodes.size() != 0) {

			ArrayList<TrieNode> newNodes = new ArrayList<>();

			for (TrieNode nd : nodes) {

				TrieNode r = nd.getParent().getFailure();
				char c = nd.getLetter();

				while (r != null && !r.containsTransition(c)) {
					r = r.getFailure();
				}

				if (r == null) {
					nd.setFailure(root);
				} else {
					nd.setFailure(r.getTransition(c));

					for (String result : nd.getFailure().getResults()) {
						nd.add(result);
					}

				}

				// add children to BFS list
				for (TrieNode child : nd.getTransitionsArray()) {
					newNodes.add(child);
				}

			}

			nodes = newNodes;
		}

		root.setFailure(root);
	}

	public Result[] find(String text) {

		ArrayList<Result> result = new ArrayList<>();
		TrieNode auxPointer = root;
		int index = 0;

		while (index < text.length()) {

			TrieNode trans = null;

			while (trans == null) {

				trans = auxPointer.getTransition(text.charAt(index));

				if (auxPointer == root) {
					break;
				}

				if (trans == null) {
					auxPointer = auxPointer.getFailure();
				}

			}

			if (trans != null) {
				auxPointer = trans;
			}

			for (String found : auxPointer.getResults()) {
				result.add(new Result(index - found.length() + 1, found));
			}

			index++;
		}

		return (Result[]) result.toArray(new Result[result.size()]);
	}

	public boolean contains(String text) {

		TrieNode auxPointer = root;
		int index = 0;

		while (index < text.length()) {
			TrieNode trans = null;

			while (trans == null) {

				trans = auxPointer.getTransition(text.charAt(index));

				if (auxPointer == root) {
					break;
				}

				if (trans == null) {
					auxPointer = auxPointer.getFailure();
				}
			}

			if (trans != null) {
				auxPointer = trans;
			}

			if (((CharSequence) auxPointer.getResults()).length() > 0) {
				return true;
			}

			index++;
		}
		return false;
	}

	public static void outPut(String[] p, String t) throws IOException {
		
		BufferedWriter outputWriter = null;
		outputWriter = new BufferedWriter(new FileWriter("out-pattern.txt"));

		p = null;

		try {
			p = Preprocess.preproccesPattern();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		t = "";

		try {
			t = Preprocess.preprocessText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AhoCorasick a1 = new AhoCorasick(p);
		a1.buildTrie();
		
		for (String s : p) {
			s = DNAprocessing.computeReverseComplement(s);
		}
		
		AhoCorasick a2 = new AhoCorasick(p);
		a2.buildTrie();

		Result[] d1 = a1.find(t);
		Result[] d2 = a2.find(t);

		
		int i = 0;
		for (Result r : d1) {
			outputWriter.write("(" + r.getIndex() + ", " + (i + 1) + ", 1)\n");
			i++;
		}
		
		i = 0;
		for (Result r : d2) {
			outputWriter.write("(" + r.getIndex() + ", " + (i + 1) + ", -1)\n");
			i++;
		}
		
		outputWriter.flush();
		outputWriter.close();
	}

	public static void main(String[] args) {
		String[] p = null;
		String t = "";
		try {
			p = Preprocess.preproccesPattern();
			t = Preprocess.preprocessText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			AhoCorasick.outPut(p, t);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
