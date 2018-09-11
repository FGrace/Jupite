import java.util.*;
public class manager extends Employee {
	private int age;
	private static String name;
	
	public static void main(String[] args) {
		Employee e1 = new manager();
		e1.setName("Jack");
		manager e2 = new manager();
		e2.setName("Rose");
		System.out.println(e2.name);
		Comparator<Integer> comparator = new Comparator<Integer>();
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer i1,Integer i2) {
				return i1 > i2 ? 1 : -1;
			}
		});
		List<Integer> list = new ArrayList<>();

	}
	
	static class innerClass{
		public String getEmployeeName() {
			return name;
		}
		public int getEmployeeAge() {
			return (new manager()).age;
		}
	}
	
	
	@Override
	void getname() {
		// TODO Auto-generated method stub
		
	}
	
/*	@Override
	public String setName(String name) {
		return name;
	}*/
	
	

}
