package naive_algorithm;

public abstract class NaiveAlgorithm {

	public static void bruteForceSearch(String p, String t) {
		int i = 0;
		int j = 0;

		while (i <= t.length() - p.length()) {
			while ((j < p.length()) && (p.charAt(j) == t.charAt(i + j))) {
				
				if (j == p.length() - 1) {
					System.out.println("Match at position : "  +  i);
				}
				
				j++;
			}
			j = 0;
			i++;
		}
	}

	public static void main(String[] args) {
		String p = "ab";
		String t = "abcdaba";
		NaiveAlgorithm.bruteForceSearch(p, t);
	}

}
