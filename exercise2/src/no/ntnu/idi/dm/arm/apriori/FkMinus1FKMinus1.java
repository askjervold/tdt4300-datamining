package no.ntnu.idi.dm.arm.apriori;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FkMinus1FKMinus1<V> extends BaseApriori<V> {

	public FkMinus1FKMinus1(List<ItemSet<V>> transactions) {
		super(transactions);
	}

	@Override
	public List<ItemSet<V>> aprioriGen(List<ItemSet<V>> frequentCandidatesKMinus1) {

		Collections.sort(frequentCandidatesKMinus1);
		int allGeneratedCandidatesCounter = 0;
		Set<ItemSet<V>> frequentCandidateSet = new HashSet<ItemSet<V>>();

		for (int i = 0; i < frequentCandidatesKMinus1.size(); i++) {
			// Take each of the frequentCandidates from the previous level ...
			ItemSet<V> prevLevelCandidate1 = frequentCandidatesKMinus1.get(i); // You should hold on to it.
			
			int currentK = prevLevelCandidate1.size() + 1; // Oh, and what level are we on?
			
			for (int j = i + 1; j < frequentCandidatesKMinus1.size(); j++) {
				// ... and get the candidates after it.
				ItemSet<V> prevLevelCandidate2 = frequentCandidatesKMinus1.get(j);
				
				if (prevLevelCandidate1.firstKItemsIdentical(prevLevelCandidate2) == currentK - 2) {
					// We want the first K-2 items of each set to be the same. Are they?
					
					ItemSet<V> frequentCandidate = prevLevelCandidate1.union(prevLevelCandidate2);
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
