import java.util.*;

public class SmallestSecondSmallest {
	public int[] arr;
	public int size;
	public int small;
	public int small2;
	
	public SmallestSecondSmallest(){
		this.arr = new int[20];
		this.small = 0;
		this.small2 = 0;
		this.size = 0;
	}
	
	public void findSmall(){
		int small = this.arr[0];
		int small2 = 100000;
		
		int i=9999;
		for(int j=1; j<this.size; j++){
			i = this.arr[j];
			if(i<small){
				small2 = small;
				small = i;
			}
			else if(i>=small && i<small2)
				small2 = i;
		}
		this.small = small; 
		this.small2 = small2;
	}
	
	public static void main(String[] args){
		SmallestSecondSmallest s = new SmallestSecondSmallest();

		int d = 0;
		Scanner key = new Scanner(System.in);
		System.out.println("Enter the size - ");
		s.size = key.nextInt();
		System.out.println("Enter the array elements - ");
		for(int i=0; i<s.size; i++)
			s.arr[i] = key.nextInt();
		s.findSmall();
		System.out.println("\nSMALLEST - "+s.small+"  SECOND SMALLEST - "+s.small2);
	}
}
