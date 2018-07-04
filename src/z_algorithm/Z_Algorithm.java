package z_algorithm;

import java.util.Arrays;

public abstract class Z_Algorithm {

	public static int[] computeZ(String str) { // My personal version of Z -
												// Box Algorithm
		int l = 0;
		int r = 0;
		int z[] = new int[str.length()];

		for (int i = 1; i < str.length(); i++) {
			if (i > r) { // No Z-box overlaps i, then we do explicit comparisons
				// The we just perform explicit comparisons
				int n = 0;
				while ((i + n) < str.length() && (str.charAt(n) == str.charAt(i + n))) {
					n++;
				}
				z[i] = n;
				// check if my z[i] > 0
				if (z[i] > 0) {
					l = i;
					r = i + z[i] - 1;
				}
			} else { // There is a Z-box that overlaps i, and we analyze each
						// case
				int k = i - l;
				if (z[k] < r - i + 1) { // We can skip char comparisons
					z[i] = z[k];
				} else { // We have to proceed doing explicit comparison
					int q = r + 1;
					while (q < str.length() && (str.charAt(q) == str.charAt(q - i))) {
						q++;
					}
					z[i] = q - i;
					l = i;
					r = q - 1;
				}
			}
		}
		return z;
	}

	/*
	 * public static int[] computeZ(String str) { int l = 0; int r = 0; int z[]
	 * = new int[str.length()];
	 * 
	 * for (int i = 1; i < z.length; i++) { if (i > r) { l = r = i; while (r <
	 * z.length && str.charAt(r - l) == str.charAt(r)) { r++; } z[i] = r - l;
	 * r--; } else { int k = i - l; if (z[k] < r - i + 1) { z[i] = z[k]; } else
	 * { l = i; while (r < z.length && str.charAt(r - l) == str.charAt(r)) {
	 * r++; } z[i] = r - l; r--; } } } return z; }
	 */
	public static void main(String[] args) {
		String str = "abababcdabcd";
		System.out.println(Arrays.toString(Z_Algorithm.computeZ(str)));
	}
}
