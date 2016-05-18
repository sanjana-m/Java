import java.util.*;

public class MaxMinRecursion {
	public int[] getMax(int[] arr, int start, int end){
		int[] maxmin = new int[2];
		if(end-start ==0){
			maxmin[0] = arr[start];
			maxmin[1] = arr[start];
		}
		else{
			int mid= (start+end)/2;
			int[] max1 = getMax(arr,start,mid);
			int[] max2 = getMax(arr,mid+1,end);
			maxmin[0] = (max1[0] > max2[0]) ? max1[0] : max2[0];
			maxmin[1] = (max1[1] <= max2[1]) ? max1[1] : max2[1];
		}
		return maxmin;
	}
	
	public static void main(String[] args){
		MaxMinRecursion m = new MaxMinRecursion();
		int d = 0;
		Scanner key = new Scanner(System.in);
		System.out.println("Enter the size - ");
		int size = key.nextInt();
		System.out.println("Enter the array elements - ");
		int[] arr = new int[size];
		for(int i=0; i<size; i++)
			arr[i] = key.nextInt();
		int[] maxmin = m.getMax(arr, 0, size-1);
		System.out.println("MAX - "+maxmin[0]+" MIN - "+maxmin[1]);
	}
}
