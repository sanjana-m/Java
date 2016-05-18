import java.util.*;

public class Sort012 {
	int[] arr;
	int size;
	
	public Sort012(int size){
		this.size = size;
		this.arr = new int[size];
	}
	
	public void swap(int i, int j){
		if(i==j)
			return;
		int temp = this.arr[i];
		this.arr[i] = this.arr[j];
		this.arr[j] = temp;
	}
	
	public void sort(){
		int low=0, mid=0, high=size-1;
		while(mid<=high){
			System.out.println(mid+" - "+arr[mid]);
			if(this.arr[mid] == 0){
				swap(low,mid);
				low++;
				mid++;
			}
			else if(this.arr[mid] == 1)
				mid++;
			else if(this.arr[mid] == 2){
				swap(mid, high);
				high--;
			}
		}
	}
	
	public static void main(String[] args){
		Scanner key = new Scanner(System.in);
		System.out.println("Enter the size - ");
		Sort012 s = new Sort012(key.nextInt());
		System.out.println("Enter the array elements - ");
		for(int i=0; i<s.size; i++)
			s.arr[i] = key.nextInt();
		s.sort();
		System.out.println("ARRAY AFTER SORT - \n");
		for(int i=0; i<s.size; i++)
			System.out.print(s.arr[i]+"\t");
	}
}
