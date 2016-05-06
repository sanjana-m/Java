import java.util.*;

public class MedianOfArrays {
	public int[] arr1;
	public int[] arr2;
	public int size;

	public MedianOfArrays(){
		this.arr1 = new int[20];
		this.arr2 = new int[20];
		this.size = 0;
	}
	
	public int returnMedian(int[] array, int size){
		int med = -1;
		if(size%2 == 0)
			med = (array[(size/2)-1] + array[(size/2)])/2;
		else
			med = array[(size/2)];
		return med;
	}
	
	public int medianOfArrays(int[] arr1, int[] arr2, int size){
		int med = -1, med1=0, med2=0, i=0 ;
		
		while(size > 2){
			med1 = returnMedian(arr1, size);
			med2 = returnMedian(arr2, size);
			
			if(med1 == med2)
				return med1;
			else if(med1 > med2){
				//second half of arr2 and first half of arr1
				System.out.println("\nNew arrays - \n");
				int[] dummy2 = new int[(size-(size/2))];
				int k=0;
				for(i=(size/2); i<size; i++){
					dummy2[k++] = arr2[i];
					System.out.print("\t"+arr2[i]);
				}
				
				System.out.print("\n");
				int[] dummy1 = new int[k];
				for(i=0; i<k; i++){
					dummy1[i] = arr1[i];
					System.out.print("\t"+dummy1[i]);
				}
				return medianOfArrays(dummy1,dummy2,i);
			}
			else if(med1 < med2){
				//second half of arr1 and first half of arr2
				System.out.println("New arrays - \n");
				int[] dummy1 = new int[(size-(size/2))];
				int k=0;
				for(i=(size/2); i<size; i++){
					dummy1[k++] = arr1[i];
					System.out.print("\t"+arr1[i]);
				}
				
				System.out.print("\n");
				int[] dummy2 = new int[k];
				for(i=0; i<k; i++){
					dummy2[i] = arr2[i];
					System.out.print("\t"+dummy2[i]);
				}
				return medianOfArrays(dummy1,dummy2,i);				
			}
		}
		if(size == 2){
			return ((arr1[0] > arr2[0] ? arr1[0] : arr2[0])+(arr1[1] < arr2[1] ? arr1[1] : arr2[1]))/2;
		}
		
		return med;
	}
	
	public static void main(String[] args){
		MedianOfArrays m = new MedianOfArrays();
		
		Scanner key = new Scanner(System.in);
		System.out.println("\nEnter the size of the arrays - ");
		m.size = key.nextInt();
		
		System.out.println("\nEnter the elements of first array - ");
		for(int i=0; i< m.size; i++)
			m.arr1[i] = key.nextInt();
		
		System.out.println("\nEnter the elements of second array - ");
		for(int i=0; i< m.size; i++)
			m.arr2[i] = key.nextInt();
	
		int median = m.medianOfArrays(m.arr1, m.arr2, m.size);
		System.out.println("\nMEDIAN - "+median);
	}
}
