import java.util.*;

public class MaxSumNoAdjacent {
	public int[] arr;
	public int size;
	
	public MaxSumNoAdjacent(){
		this.arr = new int[20];
		this.size = 0;
	}
	
	public int[] returnSum(int[] arr, int pos, int sumpos, int sum){
		int[] res = new int[2];
		if(pos-sumpos ==1){
			int temp = (sum-arr[sumpos])+arr[pos];
			if(sum < temp){
				res[0] = temp;
				res[1] = pos;
				return res;
			}
			else{
				res[0] = sum;
				res[1] = sumpos;
				return res;
			}
		} // end of pos-sumpos=1
		else if(pos-sumpos > 1){
			int temp = (sum+arr[pos]);
			if(sum < temp){
				res[0] = temp;
				res[1] = pos;
				return res;
			}
			else{
				res[0] = sum;
				res[1] = sumpos;
				return res;
			}			
		} // end of else
		return res;
	}
	
	public int maxNoAdjSum(int[] arr, int size){
		int maxSum = 0;
		
		int incl_sum=0, excl_sum=0, incl_pos = 0, excl_pos=0;
		
		incl_sum = arr[0]; incl_pos = 0;
		excl_sum = arr[1]; excl_pos = 1;
		
		for(int i=2; i<size; i++){
			System.out.println("\nINCL - "+incl_sum+" | "+incl_pos+"  EXCL  - "+excl_sum+" | "+excl_pos);
			int[] temp1 = returnSum(arr,i,incl_pos,incl_sum);
			int[] temp2 = returnSum(arr,i,excl_pos,excl_sum);
			
			excl_sum = incl_sum; excl_pos = incl_pos;
			incl_sum = (temp1[0] >= temp2[0]) ? temp1[0] : temp2[0];
			incl_pos = (temp1[0] >= temp2[0]) ? temp1[1] : temp2[1];

		}// end of for
		maxSum = (incl_sum >= excl_sum)? incl_sum : excl_sum;

		return maxSum;
	}
	
	public static void main(String[] args){
		MaxSumNoAdjacent m = new MaxSumNoAdjacent();

		int d = 0;
		Scanner key = new Scanner(System.in);
		System.out.println("Enter the size - ");
		m.size = key.nextInt();
		System.out.println("Enter the array elements - ");
		for(int i=0; i<m.size; i++)
			m.arr[i] = key.nextInt();
		d = m.maxNoAdjSum(m.arr, m.size);
		
		System.out.println("----------\nSUM IS - "+d);

	}
}
