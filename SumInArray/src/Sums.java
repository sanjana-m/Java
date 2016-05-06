import java.util.*;

public class Sums {
	public List<Integer> array;
	public int sum;
	public HashMap<Integer, Boolean> hash;
	
	public Sums(){
		array = new ArrayList<Integer>();
		sum = 0;
		hash = new HashMap<Integer, Boolean>();
	}
	
	public void readArray(){
		System.out.println("Enter the array elements (-99 to break) - ");
		Scanner key = new Scanner(System.in);
		int x=0;
		while(true){
			x = key.nextInt();
			if(x == -99)
				break;
			this.array.add(x);
			this.hash.put(x, true);
		}
	}
	
	public void readSum(){
		System.out.println("Enter the sum - ");
		Scanner key = new Scanner(System.in);
		this.sum = key.nextInt();
	}
	
	public void sumInArray(){
		int j=-1;
		for(int i=0; i< this.array.size(); i++){
			if(this.hash.get(this.sum - this.array.get(i)) == true){
				j=1;
				System.out.println("THERE IS A SUM - " + this.array.get(i) + "  "+ (this.sum - this.array.get(i)));
				break;
			}
		}
		if(j== -1)
			System.out.println("THERE IS NO SUM");
	}
	
	public static void main(String[] args){
		Sums s = new Sums();
		s.readArray();
		s.readSum();
		s.sumInArray();
	}

}
