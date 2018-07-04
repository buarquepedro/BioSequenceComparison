package ahocorasick_algorithm;

public abstract class DNAprocessing {
	
	private static String computeComplement (String dna) {
		dna = dna.replaceAll("A", "Z");
		dna = dna.replaceAll("T", "A");
		dna = dna.replaceAll("Z", "T");
		dna = dna.replaceAll("C", "S");
		dna = dna.replaceAll("G", "C");
		dna = dna.replaceAll("S", "G");
		return dna;
	}
	
	public static String computeReverseComplement(String dna) {
		StringBuffer str = new StringBuffer(DNAprocessing.computeComplement(dna));
		return str.reverse().toString();
	}

}
