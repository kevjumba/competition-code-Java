package Algorithms;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class Algorithms {
	
	public static void main(String [] args) throws FileNotFoundException{
		Scanner sc = new Scanner(System.in);

		int size = sc.nextInt();

		int [] array = new int[size];
		for(int i=0;i<size;i++){
			array[i]=sc.nextInt();
		}

		quickSort(array, 0, size-1);
//		swap(array, 0, 9);
		display(array);
	}
	/*
	 * randomized quick3
	 */
	public static void quickSort(int [] array, int left, int right){
		//runs average nlogn
		if (left>=right) return;
		int pivot = ThreadLocalRandom.current().nextInt(left, right);
		swap(array, left, pivot); //put pivot at the beginning of sorting region
		
		Pair pair = Partition(array, left, right);
		quickSort(array, left, pair.getFirst()-1);
		quickSort(array, pair.getSecond()+1, right);
		//middle region for everything = to the pivot
	}
	
	private static Pair Partition(int[] array, int left, int right) {
		int j = left, k=left;
		int x = array[left]; 
		for(int i=left+1;i<=right;i++){
			if(array[i]<=x){
				j+=1;
				k++;
				swap(array, j, i);
			}
			else if(array[i]==x){
				k++;
				swap(array, k, i);
			}
			
		}
		swap(array, left, j);
		return new Pair(j, k);
	}

	private static void display(int [] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
	}
	private static void swap(int [] array, int l, int k){
		int temp = array[l];
		array[l]=array[k];
		array[k]=temp;
	}
	
	private static class Pair {
		private int n1;
		private int n2;
		public Pair(int n1, int n2){
			this.n1=n1;
			this.n2=n2;
		}
		public int getFirst(){
			return n1;
		}
		public int getSecond(){
			return n2;
		}
	}
}
