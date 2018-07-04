package kmp_algorithm;

import java.util.Arrays;

public abstract class KMP {

	private static int[] sp;

	public static int[] computeSPValues(String p) {
		int j = 0;
		int i = 1; // start index for string searching
		sp = new int[p.length()];
		sp[0] = 0; // By defition of SP-Value

		// start from the second char of the pattern to n -1
		while (i < p.length()) {
			if ((p.charAt(i) == p.charAt(j)) || ((p.charAt(i) != p.charAt(j)) && (match(p.charAt(i),p.charAt(j)))) ){
				sp[i] = j + 1;
				j++;
				i++;
			} else { // pattern.charAt(i) != pattern.charAt(j)
				if (j != 0) {
					j = sp[j - 1];
				} else { // j == 0
					sp[i] = 0;
					i++;
				}
			}
		}

		return sp;
	}
	
	public static boolean match (char a, char b) {
		if ((a == 'w' && b == 'a') || (a == 'w' && b == 't')) {
			return true;
		} else if ((b == 'w' && a == 'a') || (b == 'w' && a == 't')) {
			return true;
		} else if ((a == 's' && b == 'c') || (a == 's' && b == 'g')) {
			return true;
		} else if ((a == 'c' && b == 's') || (a == 'g' && b == 's')) {
			return true;
		} else {
			return false;
		}
	}

	public static void computeKMP(String P, String T) {
		int n = P.length();
		int m = T.length();
		int[] sp = KMP.computeSPValues(P);

		int i = 0; // current start of patter P on text T;
		int q = 0; // current offset into pattern (the shifted part index)

		while (i <= m - n + 1) {
			while ((q < n) && ((P.charAt(q) == T.charAt(i + q)) || ((P.charAt(q) != T.charAt(i + q)) && (match(P.charAt(q),T.charAt(i + q)))))) {
				q++;
			}
			
			if (q == n) {
				System.out.println("Match at position : " + i);
			}

			if ((P.charAt(0) != T.charAt(i)) && !(match(P.charAt(0), T.charAt(i)))) {
				i++;
				q = 0;
			} else {
				int j = q;
				i = i + j - sp[j - 1];
				q = sp[j - 1];
			}
		}
	}

	public static void main(String[] args) {
		String p = "aatgcaawtg";
		String t = "aatgcaaatcaaaaagc";
		System.out.println(Arrays.toString(KMP.computeSPValues(p)));
		KMP.computeKMP(p, t);
	}

}
