import java.util.*;

public class NumGenerator {
	private Map<Character,Integer> map = new HashMap<>();
	private int totalWeight = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumGenerator generator = new NumGenerator();
		generator.offer(new Entry('A',3));
		generator.offer(new Entry('B',6));
		generator.offer(new Entry('C',9));

		

		int counterOne = 0;
		int counterTwo = 0;
		int counterThree = 0;

		for(int i = 0;i < 10000;i++) {
			char ch = generator.poll();
			if(ch == 'A') {
				counterOne++;
			}else if(ch == 'B'){
				counterTwo++;
			}else {
				counterThree++;
			}
		}
		System.out.println("one/two: " + (double)counterOne/counterTwo + " one/three: "+ (double)counterOne/counterThree);

		

	}
	
	public void offer(Entry e) {
			if(map.containsKey(e.key)) {
				totalWeight -= map.get(e.key);
				if(e.freq == 0) {
					map.remove(e.key);
		//			System.out.println("Remove character " + e.key +" from map.");
					System.exit(0);
				}else {
		//			System.out.println("Add character " + e.key +" into map with a weight: " + e.freq);
				}
			}
			map.put(e.key, e.freq);
			totalWeight += e.freq;
		//	System.out.println("Total weight: " + totalWeight);

	}
	
	public char poll() {
		//using a random number generator
		Random rand = new Random();
		double randomNum = rand.nextDouble() * totalWeight;
	//	System.out.println("randomNum: " + randomNum);
		int count = 0;
		for(Map.Entry<Character, Integer> entry : map.entrySet()) {
			if(randomNum < entry.getValue() + count) {
		//		System.out.println("The char polled out is: " + entry.getKey());
				return entry.getKey();
			}else {
				count += entry.getValue();			
			}
		}
		return '0';
	}

	static class Entry{
		char key;
		int freq;
		public Entry(char key,int freq) {
			this.key = key;
			this.freq = freq;
		}
	}
}
