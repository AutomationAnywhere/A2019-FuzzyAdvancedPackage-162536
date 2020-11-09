package com.automationanywhere.botcommand.sk.tokenzier;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public  class FuzzyRatio {
	
	public static Integer SimpleRatioMatch(String str1, String str2) {
		return (FuzzySearch.ratio(str1,str2));
	}
	
	public static Integer  PartialRatioMatch(String str1, String str2) {
		return (FuzzySearch.partialRatio(str1,str2));
	}	
	
	public static Integer  TokenSortRatioMatch(String str1, String str2) {
		return (FuzzySearch.tokenSortRatio(str1,str2));
	}
	
	public static Integer  TokenSetRatioMatch(String str1, String str2) {
		return (FuzzySearch.tokenSetRatio(str1,str2));
	}
	
	public static Integer WeightedRatioMatch(String str1, String str2) {
		return FuzzySearch.weightedRatio(str1,str2) ;
	}
	
	

}
