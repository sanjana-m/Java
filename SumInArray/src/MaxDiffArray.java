import java.util.*;

public class MaxDiffArray {
	public int[] arr;
	public int size;

	public MaxDiffArray(int size){
		this.arr = new int[size];
		this.size = size;
	}
	
	public int maxDiff(int[] arr, int i, int j){
		if(i < j){
			if(arr[i] < arr[j])
				return j-i;
			else{
				return (maxDiff(arr,i+1,j) > maxDiff(arr,i,j-1) ? maxDiff(arr,i+1,j) : maxDiff(arr,i,j-1));
			}			
		}
		else
			return -1;
	}
	
	public static void main(String[] args){
		Scanner key = new Scanner(System.in);
		System.out.println("Enter the size - ");
		MaxDiffArray m = new MaxDiffArray(key.nextInt());
		
		System.out.println("Enter the array elements - ");
		for(int i=0; i<m.size; i++)
			m.arr[i] = key.nextInt();
		
		int diff = m.maxDiff(m.arr, 0, m.size-1);
		System.out.println("MAX DIFF - "+diff); 
	}
}
