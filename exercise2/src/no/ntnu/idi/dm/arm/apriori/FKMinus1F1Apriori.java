package no.ntnu.idi.dm.arm.apriori;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FKMinus1F1Apriori<V> extends BaseApriori<V> {

	public FKMinus1F1Apriori(List<ItemSet<V>> transactions) {
		super(transactions);
	}

	@Override
	public List<ItemSet<V>> aprioriGen(List<ItemSet<V>> frequentCandidatesKMinus1) {
		Collections.sort(frequentCandidatesKMinus1);
		int allGeneratedCandidatesCounter = 0;
		Set<ItemSet<V>> frequentCandidateSet = new HashSet<ItemSet<V>>();
		
		for (ItemSet<V> frequentCandidatePrevLevel : frequentCandidatesKMinus1) {
			// Take each of the frequentCandidates from the previous level ... 
			
			for (ItemSet<V> frequentItem : frequent1Itemsets) {
				// ... and get each of the frequent single items.
				
				if (frequentCandidatePrevLevel.intersection(frequentItem).size() == 0) {
					// Does the set already contain this item? No?
					
					ItemSet<V> frequentCandidate = frequentCandidatePrevLevel.union(frequentItem);
					// Then go ahead and combine them!
					
					frequentCandidateSet.add(frequentCandidate); // Make sure you add it to your set of candidates!
					allGeneratedCandidatesCounter++; // Don't forget to count it!
					getAndCacheSupportForItemset(frequentCandidate); // Oh, and it has some support values you'll need.
				}
				
			}
		}
		
		return new LinkedList<ItemSet<V>>(frequentCandidateSet);
	}
}
