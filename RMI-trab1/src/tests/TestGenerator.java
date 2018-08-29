package tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import shared.Compute;
import shared.Compute.SortType;

public class TestGenerator {
	
	public static Collection<Object[]> getTests(){
		ArrayList<Object[]> a = new ArrayList<Object[]>();
		adiciona(a, SortType.Insertion, true, true, false);
		adiciona(a, SortType.Merge, true, true, true);
		adiciona(a, SortType.Selection, true, true, false);
		adiciona(a, SortType.Random, true, false, false);
		adiciona(a, SortType.Bubble, true, true, false);
		adiciona(a, SortType.Heap, true, true, true);
		adiciona(a, SortType.Quick, true, true, true);
		adiciona(a, SortType.Radix, true, true, true);
		adiciona(a, SortType.Counting, false, true, false);
		adiciona(a, SortType.BinaryTree, true, true, true);
		adiciona(a, SortType.Stooge, true, true, false);
		return a;
	}

	private static void adiciona(ArrayList<Object[]> a, SortType s, boolean bigElements, boolean bigArray, boolean giantTest) {
		Random rand = new Random();
		{
			a.add(new Object[]{s, new Integer[]{6,-1,3,2}});
			a.add(new Object[]{s, new Integer[]{6,3,-1,2}});
			a.add(new Object[]{s, new Integer[]{6,-3,1,2}});
			a.add(new Object[]{s, new Integer[]{}});
			a.add(new Object[]{s, null});
			a.add(new Object[]{s, new Integer[]{-6,-2,-8,-3}});
			a.add(new Object[]{s, new Integer[]{-1,3,2}});
			a.add(new Object[]{s, new Integer[]{6,3,6,3,1,3}});
			a.add(new Object[]{s, new Integer[]{-1,-7,-5,-92,-62}});
			a.add(new Object[]{s, new Integer[]{4}});
			a.add(new Object[]{s, new Integer[]{3,3}});
			a.add(new Object[]{s, new Integer[]{1,2}});
			a.add(new Object[]{s, new Integer[]{2,1}});
			a.add(new Object[]{s, new Integer[]{4,3,2,1}});
			a.add(new Object[]{s, new Integer[]{1,3,2,4}});
		}
		if(bigElements) {
			a.add(new Object[]{s, new Integer[]{60000000,-1000000,30000,2000000000}});
			a.add(new Object[]{s, new Integer[]{6000000,3,-100000,2000000}});
			a.add(new Object[]{s, new Integer[]{-100000000,300000000,2}});
			a.add(new Object[]{s, new Integer[]{600000000,3000000,600000000,300000000,-100000000,300000000}});
			a.add(new Object[]{s, new Integer[]{600000000,3000000,-600000000,300000000,100000000,300000000}});
			a.add(new Object[]{s, new Integer[]{400000000}});
			a.add(new Object[]{s, new Integer[]{300000000,-300000000}});
			a.add(new Object[]{s, new Integer[]{200000000, 2000000000}});
			a.add(new Object[]{s, new Integer[]{200000000, 1000000000}});
		}
		if(bigArray) {
			for(int total = 0; total < 15; total++) {
				int n = rand.nextInt(100) + 200;
				Integer[] vet = new Integer[n];
				for(int i = 0; i < n; i++) {
					vet[i] = rand.nextInt(4*n) - 2*n;
				}
				a.add(new Object[]{s, vet});
			}
		}
		if(bigElements && bigArray) {
			for(int total = 0; total < 15; total++) {
				int n = rand.nextInt(100) + 200;
				Integer[] vet = new Integer[n];
				for(int i = 0; i < n; i++) {
					vet[i] = rand.nextInt();
				}
				a.add(new Object[]{s, vet});
			}
		}
		if(giantTest) {
			int n = 200000;
			Integer[] vet;
			vet = new Integer[n];
			for(int i = 0; i < n; i++) {
				vet[i] = rand.nextInt();
			}
			a.add(new Object[]{s, vet});
			vet = new Integer[n];
			for(int i = 0; i < n; i++) {
				vet[i] = (i-n/2)*1000;
			}
			a.add(new Object[]{s, vet});
			vet = new Integer[n];
			for(int i = 0; i < n; i++) {
				vet[i] = (n/2-i)*1000;
			}
			a.add(new Object[]{s, vet});
		}
	}
}
