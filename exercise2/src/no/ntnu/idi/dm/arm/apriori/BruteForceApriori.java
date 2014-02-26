package no.ntnu.idi.dm.arm.apriori;

import java.util.List;

public class BruteForceApriori<V> extends BaseApriori<V> {

	public BruteForceApriori(List<ItemSet<V>> transactions) {
		super(transactions);
	}

	@Override
	public void apriori(Double minSupport) {
		// get 1-itemsets
		List<ItemSet<V>> candidates = getAllItemsetsOfSizeOne();
		System.out.println("Level 1:");
		System.out.println(candidates.size()+ " total, unique itemsets: "+ candidates.size());
		System.out.println("\t" + candidates);

		// number of candidates on lowest level
		int levels = candidates.size();
		
		pruneInfrequentCandidates(minSupport, candidates);

		// store level 1 candidates in our list of frequent itemsets
		frequentItemSets.put(1, candidates);

		// create the higher levels
		for (int i = 2;i<levels; i++) {
			System.out.println("Level " + i);

			// generate all sets
			candidates=aprioriGen(candidates);
			candidates=pruneInfrequentCandidates(minSupport, candidates);
			
			frequentItemSets.put(i, candidates);
			
			if (candidates.size() == 0) {
				System.out.println("We're done here, there's no more frequent itemsets");
				break;
			}

			System.out.println("\t" + candidates);
		}

	}
	
}
