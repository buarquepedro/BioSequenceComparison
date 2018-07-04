package ahocorasick_algorithm;

public class Result implements Comparable<Result> {

	private int index;
	private String word;

	public Result(int index, String word) {
		this.index = index;
		this.word = word;
	}

	public int getIndex() {
		return index;
	}

	public String getWord() {
		return word;
	}

	public String toString() {
		return "index: " + this.index + "  pattern : " + this.word;
	}

	public static Result empty() {
		return new Result(-1, "");
	}

	@Override
	public int compareTo(Result o) {
		return this.word.compareToIgnoreCase(o.getWord());
	}

}
