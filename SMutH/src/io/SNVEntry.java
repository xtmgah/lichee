/**
 * Class: VCFEntry
 * Constructor: VCFEntry(String entry)
 * ----
 * This class represents a particular VCF entry from a 
 * VCF file. Note that all samples are 0-indexed.
 */
package io;


public abstract class  SNVEntry {
	
		
	/* Instance Variables */
	 protected String row;

	 protected String chrom;
	 protected int pos;
	 protected char ref;
	 protected char alt;	
		
	 protected String[] genotype;

	
	public String getChromosome(){
		return chrom;
	}
	
	public int getChromNum(){
		if (chrom.charAt(3) == 'X') return 23;
		if (chrom.charAt(3) == 'Y') return 24;
		return new Integer(chrom.substring(3)).intValue();
	}
	
	
	/**
	 * Function: getPosition()
	 * Usage: String pos = entry.getPos()
	 * ----
	 * Returns the position of the entry as a string
	 * @return
	 */
	public int getPosition(){
		return pos;
	}
	
	/**
	 * Function: getRefChar()
	 * Usage: char ref = entry.getRefChar()
	 * ----
	 * Returns the reference allele of the entry
	 * 
	 * @return the reference allele as a char
	 */
	public char getRefChar(){
		return ref;
	}
	
	/**
	 * Function: getAltChar()
	 * Usage: char alt = entry.getAltChar()
	 * ----
	 * Returns the alternate allele of the entry
	 * 
	 * @return the alternate allele as a char
	 */
	public char getAltChar(){
		return alt;
	}
	
	
	/**
	 * Function: getGenotype(int sample)
	 * Usage: String genotype = entry.getGenotype(sample)
	 * ----
	 * Returns the particular genotype of a sample from the entry.
	 * The genotype is generated by GATK.
	 * @param sample	the particular sample from the entry
	 * @return	the genotype of the sample of the entry as a string
	 */
	public String getGenotype(int sample){
		return genotype[sample];
	}
	
	/**
	 * Function: getGATK(int sample)
	 * Usage: String gatk = entry.getGATK(sample)
	 * ----
	 * Returns the GATK code for a sample. The GATK code is
	 * found by checking whether the genotype is equivalent to "0/0"
	 * for each sample. If so, 0 is appended as the GATK code
	 * for that sample; otherwise, 1 is appended. At the end,
	 * one will have a binary code of length equal to the number of 
	 * samples for the entry.
	 * @return	The GATK code for an entry as a string
	 */
	public String getGroup(){
		String result = "";
		//System.out.println(this.toString());
		for (int i = 0; i < genotype.length; i++){
			if (genotype[i].equals("0/0")) result += "0";
			else result += "1";
			//System.out.println(result);
		}
		return result;
	}

// -------Old Probability Formula-------
//	public double getConversionProb(int sample){
//		int a = getAlleleCount(sample, 1);
//		int d = getReadDepth(sample);
//		return nCr(d, a) * Math.pow(BASE_ERROR, a) * Math.pow((1 - BASE_ERROR), d - a);
//	}
// -------End-------
	
	/**
	 * Function: getProb(int sample, int d, int k)
	 * Usage: double prob = entry.getProb(sample, d, k)
	 * ----
	 * This function calculates the probability of a sample
	 * being called incorrectly. Is used by getSumProb (which
	 * acts as a wrapper).
	 * 
	 * @param sample	The particular sample of the entry
	 * @param d			The allele depth of the sample
	 * @param k			The minor allele count
	 * @return	The probability as a double
	 */
	

	
	public abstract double getAAF(int i) ;

	
	public String toString(){
		return row;
	}
}
