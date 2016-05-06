import java.util.*;


public class SearchInSortedPivotedArray {

	public int findPivot(List<Integer> array, int low, int high){
		int pivot = -1;
		int mid = 0;
		int low1 = low, high1 = high;
		if(array.size() == 0)
			return -1;
		while((low < high) && (high <= high1) && (low >= low1)){
			mid = (low+high)/2;
			if((mid <high1) && (array.get(mid) > array.get(mid+1)))
				return mid+1;
			else if((mid > low1) && (array.get(mid) < array.get(mid-1)))
				return mid;
			if(array.get(low) < array.get(mid))
				return findPivot(array,mid+1,high);
			else 
				return findPivot(array,low,mid-1);
		}
		
		return pivot;
	}
	
	public int searchPivot(List<Integer> array, int num){
		int index = -1, pivot = 0;		
		pivot = findPivot(array,0,array.size()-1); // pivot has index of rotation
		if(pivot == -1)
			return -1;
		
		BinarySearch b = new BinarySearch();
		index = b.binarySearchWithIndex(array, 0, pivot-1, num);
		if(index == -1)
			index = b.binarySearchWithIndex(array, pivot, array.size()-1, num);			
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

		SearchInSortedPivotedArray s= new SearchInSortedPivotedArray();
		int index = s.searchPivot(array, num);
		if(index == -1)
			System.out.println("The number is not there in the array");
		else
			System.out.println("The index is - "+index);
	}
}
