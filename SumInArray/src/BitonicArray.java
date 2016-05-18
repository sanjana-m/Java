import java.util.*;

public class BitonicArray {
	int[] arr;
	int size;
	
	public BitonicArray(int size){
		this.size = size;
		this.arr = new int[size];
	}
	
	public void printLimit(int pivot){
		System.out.println("PIVOT - "+pivot);
		int start = pivot-1, end=pivot+1;
		
		start = (start < 0) ? 0 : start;
		end = (end > this.size-1) ? this.size-1 : end;
		
		while((start >0)&&(this.arr[start] >= this.arr[start-1]))
			start--;
		
		while((end <this.size-1)&&(this.arr[end] >= this.arr[end+1]))
			end++;
		
		System.out.printf("\nThe Subarray is of form - \n");
		for(int i=start; i<=end; i++)
			System.out.print(this.arr[i]+"\t");
	}
	
	public int findPivot(int start, int end){
		if(start <= end){
			if(end-start ==0)
				return start;
			if(end-start == 1){
				int ret = -1;
				ret = (this.arr[end] >= this.arr[start]) ? end : start;
				return ret;
			}
			int mid = (start+end)/2;
			if((mid > 0 && mid < this.size-1)){
				if((this.arr[mid] >= this.arr[mid-1]) && (this.arr[mid] >= this.arr[mid+1]))
					return mid;
				else if(this.arr[mid] < this.arr[mid+1])
					return findPivot(mid+1, end);
				else if(this.arr[mid-1] >= this.arr[mid])
					return findPivot(start,mid-1);
			}
		}
		return -1;
	}
	
	public static void main(String[] args){
		Scanner key = new Scanner(System.in);
		System.out.println("Enter the size - ");
		BitonicArray b = new BitonicArray(key.nextInt());
		System.out.println("Enter the array elements - ");
		for(int i=0; i<b.size; i++)
			b.arr[i] = key.nextInt();
		int pivot = -1;
		pivot = b.findPivot(0,b.size-1);
		b.printLimit(pivot);
	}
}
