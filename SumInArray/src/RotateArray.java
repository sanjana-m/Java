import java.util.*;

public class RotateArray {
	int[] array;
	int size;
	
	public RotateArray(){
		this.array = new int[20];
		this.size = 0;
	}
	
	public int[] rotateLeft(int[] arr, int size){
		int first = arr[0];
		for(int i=0; i < size-1; i++)
			arr[i] = arr[i+1];
		arr[size-1] = first;
		return arr;
	}
	
	public int[] rotate(int[] arr, int size, int d){
		for(int i=0; i<d ; i++)
			arr = rotateLeft(arr,size);
		return arr;
	}
	
	public static void main(String[] args){
		RotateArray r = new RotateArray();
		int d = 0;

		Scanner key = new Scanner(System.in);
		System.out.println("Enter the size - ");
		r.size = key.nextInt();
		System.out.println("Enter the array elements - ");
		for(int i=0; i<r.size; i++)
			r.array[i] = key.nextInt();

		System.out.println("Enter the number to be rotated by - ");
		d = key.nextInt();
		
		r.array = r.rotate(r.array, r.size, d);
		System.out.println("The rotated array - \n");
		for(int i=0; i<r.size; i++)
			System.out.print("\t"+r.array[i]);		
	}
}
