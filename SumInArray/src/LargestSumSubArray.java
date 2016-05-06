import java.util.*;

public class LargestSumSubArray {
	public List<Integer> array;
	public int sum;
	
	public LargestSumSubArray(){
		this.array = new ArrayList<Integer>();
		this.sum = 0;
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
		}
	}
	
	public void maxSum(){
		int max_so_far=0;
		int max_sum = 0;
		int i=0;
		
		for(i=0; i<this.array.size(); i++){
			max_sum += this.array.get(i);
			if(max_sum < 0)
				max_sum = 0;
			if(max_so_far < max_sum)
				max_so_far = max_sum;
		}
		this.sum = max_so_far;
	}
	
	public static void main(String[] args){
		LargestSumSubArray l = new LargestSumSubArray();
		l.readArray();
		l.maxSum();
		System.out.println("The max sum of the array is - "+l.sum);
	}
	
}
