import java.util.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class Solution {
	

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] prices = {2, 3, 2, 1, 4, 5, 2, 11};
		s.maxProfit(prices);
				
		
	}
	
	public int maxProfit(int[] prices) {
	     if(prices == null || prices.length <= 1){
	          return 0;
	     }
	    
	    int[] profitL = new int[prices.length];
	    int[] profitR = new int[prices.length];
	    int buy = prices[0];
	    int maxProfit = 0;
	    for(int i = 1; i < prices.length;i++){
	      if(prices[i] < buy){
	        buy = prices[i];
	      }else {
	        maxProfit = Math.max(prices[i] - buy,maxProfit);
	      }
	      profitL[i] = maxProfit;
	//      System.out.println(i + "th element is:  " + profitL[i]);
	    }
	    
	    int sell = prices[prices.length - 1];
	    maxProfit = 0;
	    int res = 0;
	    for(int i = prices.length - 2; i >= 0; i--){
	      if(prices[i] > sell){
	        sell = prices[i];
	      }else {
	        maxProfit = Math.max(sell - prices[i],maxProfit);
	      }
	       profitR[i] = maxProfit;
		      System.out.println(i + "th element in right is:  " + profitR[i]);
		      if(i - 1 >= 0) {
			      System.out.println(i - 1 + "th element in left is:  " + profitL[i - 1]);
		      }

	      res = Math.max(res,(i - 1 >= 0) ? profitL[i - 1] : 0 + profitR[i]);
	      System.out.println(i + "th res  is:  " + res);

	      
	      
	    }
	    
	    return res;
	  }
	
	

	
	  
}
