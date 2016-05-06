import java.util.*;

public class BinarySearch {
	public int binarySearch(List<Integer> array, int num){
		int index = -1;
		int low=0, mid=0, high=0;
		high = array.size() - 1;
			
		while((low < high) && (low >=0) && (high <= (array.size()-1))){
			mid = (low + high) /2;
			if(num == array.get(mid)){
				index = 1;
				return mid;				
			}
			else if(num < array.get(mid))
				high = mid-1;
			else if(num > array.get(mid))
				low = mid+1;
		}
		return index;
	}
	
	public int binarySearchWithIndex(List<Integer> array, int low, int high, int num){
		int index = -1;
		int mid = 0;
		int size = high;
		int low1 = low;
		if(high-low == 0)
			if(array.get(low) == num)
				return low;
			else
				return -1;
		
		while((low < high) && (low >=low1) && (high <= size)){
			if(high-low == 1)
				if(array.get(low) == num)
					return low;
				else if(array.get(high) == num)
					return high;
				else 
					return -1;
			mid = (low + high) /2;
			if(num == array.get(mid)){
				index = 1;
				return mid;
			}
			else if(num < array.get(mid))
				high = mid-1;
			else if(num > array.get(mid))
				low = mid+1;
		}
		return index;
	}
	
	public static void main(String[] args){
		List<Integer> array = new ArrayList<Integer>();
		System.out.println("Enter the array elements (-99 to break) - ");
		Scanner key = new Scanner(System.in);
		int x=0;
		while(true){
			x = key.nextInt();
			if(x == -99)
				break;
			array.add(x);
		}
		int num = 0;
		System.out.println("Enter the number to be searched - ");
		num = key.nextInt();
		
		BinarySearch b = new BinarySearch();
		int index = b.binarySearchWithIndex(array,0,array.size(), num);
		if(index == -1)
			System.out.println("The number is not there in the array");
		else
			System.out.println("The index is - "+index);
	}
}
