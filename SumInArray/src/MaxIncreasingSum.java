import java.util.*;

public class MaxIncreasingSum {
	int[] arr;
	int size;
	
	public MaxIncreasingSum(int size){
		this.size = size;
		this.arr = new int[size];
	}
	
	public int sumArray(int[] arr, int size){
		if(size==0)
			return 0;
		int sum = 0;
		for(int i=0; i<size; i++)
			sum += arr[i];
		return sum;
	}
	
	public int retMaxSum(int[] arr, int size){
		int sum=0, i=size-1, sel=0, rej=0;
		if(size == 0)
			return 0;
		if(size == 1)
			return arr[0];
		int[] select = new int[size];
		int[] reject = new int[size];
		
		while(i>=0){
			if(sel == 0)
				select[sel++] = arr[i];
			else{
				if(sumArray(select,sel) < sumArray(reject, rej)){
					//SWAP SHIT
					System.out.println("\nINSIDE SWAP - "+sel+"  "+rej);
					int[] temp = new int[sel];
					for(int j=0; j<sel; j++){
						temp[j] = select[j];
						select[j] = 0;
					}
					for(int j=0; j<rej; j++){
						select[j] = reject[j];
						reject[j] = 0;
					}
					for(int j=0; j<sel; j++)
						reject[j] = temp[j];
					int temp1 = rej;
					rej = sel;
					sel = temp1;
					System.out.println("\nEND SWAP - "+sel+"  "+rej);
				}// end of swap
				
				if(select[sel-1] >= arr[i])
					select[sel++] =arr[i];
				else{
					if(rej == 0)
						reject[rej++] = arr[i];
					else{
						if(reject[rej-1] >= arr[i])
							reject[rej++] = arr[i];
						else{
							if(arr[i] >= sumArray(reject,rej)){
								for(int j=0; j<rej; j++)
									reject[j] = 0;
								reject[0] = arr[i];
								rej=1;
							}
						}// else for rej=0
					}
				}// end of rej < arr[i]
			} // end of sel=0 else
			i--;
		}// end of while
		
		sum = (sumArray(select,sel) > sumArray(reject,rej)) ? sumArray(select,sel) : sumArray(reject,rej);
		return sum;
	}
	
	public static void main(String[] args){
		Scanner key = new Scanner(System.in);
		System.out.println("Enter the size - ");
		MaxIncreasingSum m = new MaxIncreasingSum(key.nextInt());
		System.out.println("Enter the array elements - ");
		for(int i=0; i<m.size; i++)
			m.arr[i] = key.nextInt();
		System.out.println("MAX INCREASING SUM - "+ m.retMaxSum(m.arr, m.size));
	}
}
