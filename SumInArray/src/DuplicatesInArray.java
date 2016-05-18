import java.util.*;

public class DuplicatesInArray {
	public int[] arr;
	public HashMap<Integer,Integer> hash;
	public int size;
	
	public DuplicatesInArray(){
		this.arr = new int[20];
		this.hash = new HashMap<Integer, Integer>();
		this.size =0;
	}
	
	public void findDuplicates(){
		for(int i=0; i<this.size; i++){
			if(this.hash.get(this.arr[i]) == null)
				hash.put(this.arr[i],1);
			else{
				int temp = hash.get(this.arr[i]);
				hash.remove(this.arr[i]);
				hash.put(this.arr[i], temp+1);
			}
		}
	}
	
	public static void main(String[] args){
		DuplicatesInArray d = new DuplicatesInArray();

		Scanner key = new Scanner(System.in);
		System.out.println("Enter the size - ");
		d.size = key.nextInt();
		System.out.println("Enter the array elements - ");
		for(int i=0; i<d.size; i++)
			d.arr[i] = key.nextInt();

		d.findDuplicates();
		System.out.println("Duplicate elements - ");
		
		Set s= d.hash.keySet();
		Iterator i = s.iterator();
		
		int x=-1;
		while(i.hasNext()){
			x = (Integer) i.next();
			if(d.hash.get(x) > 1)
				System.out.print(x+"\t");
		}
	}
}
