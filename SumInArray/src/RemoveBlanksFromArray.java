import java.util.*;

public class RemoveBlanksFromArray {
	public int[] array;
	
	public RemoveBlanksFromArray(){
		this.array = new int[20];
	}

	public void swap(int[] array, int x, int y){
		int temp=0;
		temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
	
	public void removeBlanks(int size){
		int i=0,j=0;
		for(i=0; i<size; i++){
			if(this.array[i] == -1){
				j=i+1;
				while((this.array[j] == -1) && (j < size))
					j++;
				if(j == (size - 1)){
					if(this.array[j] != -1)
						swap(this.array,i,j);
					else if(this.array[j] == -1)
						break; // No more non negative elements
				}
				else if(j < size){
					swap(this.array,i,j);
				}
			} // end of if
		}// end of for
	}
	
	public static void main(String[] args){
		RemoveBlanksFromArray r = new RemoveBlanksFromArray();
		System.out.println("Enter the array elements (-99 to break) - ");
		Scanner key = new Scanner(System.in);
		int x=0,i=0;
		while(true){
			x = key.nextInt();
			if(x == -99)
				break;
			r.array[i] = x;
			i++;
		}
		r.removeBlanks(i);

		System.out.println("\n\nArray Now - ");
		for(int j=0; j<i; j++)
			System.out.print("\t"+r.array[j]);
			
	}
	
}
