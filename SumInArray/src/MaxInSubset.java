import java.util.*;

public class MaxInSubset {
	ArrayList<Integer> window;
	int[] arr;
	int size;
	int max, maxpos;
	
	public MaxInSubset(int size){
		this.arr = new int[size];
		this.window = new ArrayList<Integer>();
		this.size = size;
		this.max = -999;
		this.maxpos = -1;
	}
	
	public void initialize(int k){
		for(int i=0; i<k; i++){
			if(i==0){
				this.window.add(i);
				this.max = arr[i]; this.maxpos = i;
			}
			else{
				if(this.arr[i] > this.arr[i-1])
					this.window.add(i);
				if(this.arr[i] > max){
					this.max = arr[i]; this.maxpos = i;
				}
			}
		}
	}
	
	public int pop(int n){
		int i=0, large=-1;
		if(this.window.size() == 0){
			this.max = -999;
			this.maxpos = -1;
			return -999;
		}
		while((this.window.get(i) < n) && (this.window.size() > 0))
			large = this.window.remove(i);
		return large;
	}
	
	public void calcMax(){
		if(this.window.size() == 0){
			this.max = -999;
			this.maxpos = -1;
			return;
		}
		this.max = -999;
		int i=0;
		while(i < this.window.size()){
			if(this.arr[this.window.get(i)] > this.max){
				this.max = this.arr[this.window.get(i)];
				this.maxpos = i;
			}
		}
	}
	
	public void maxSubArrays(int k){
		this.initialize(k);

		System.out.println("I - " + (k-1)+ " MAX - "+this.max+ " MAXPOS- "+this.maxpos);

		int pop = -1, large=-99;
		for(int i=k; i<this.size; i++){
			pop = i-k;
			large = this.pop(pop);
			if(large != -999){
				// If array has at least 1 element
				if(this.maxpos <= large){
					// Compute new maximum and maxpos
					this.calcMax();
				}
			}
			
			if(this.arr[i] > this.arr[i-1])
				window.add(i);
			if(this.arr[i] > this.max){
				this.max = this.arr[i];
				maxpos = i;
			}
			
			System.out.println("I - "+i+" MAX - "+this.max+" MAXPOS- "+this.maxpos);
		}
	}
	
	public static void main(String[] args){
		Scanner key = new Scanner(System.in);
		System.out.println("Enter the size - ");
		MaxInSubset m = new MaxInSubset(key.nextInt());

		System.out.println("Enter the array elements - ");
		for(int i=0; i<m.size; i++)
			m.arr[i] = key.nextInt();
		
		System.out.println("Enter size of subset - ");
		m.maxSubArrays(key.nextInt());
	}
}
