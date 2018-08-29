package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;

public class Verify {
	public static void verify(String message, Integer[] originalVet, Integer[] sortedVet, Comparator<Integer> comp) {
		if(originalVet == null) {
			assertNull(message, sortedVet);
			return;
		}
		
		int n = originalVet.length;
		
		assertNotNull(message, sortedVet);
		assertEquals(message, originalVet.length, sortedVet.length);
		
		for(int i = 0; i < n - 1; i++)
			assertTrue(message, comp.compare(sortedVet[i], sortedVet[i+1]) <= 0);
		
		Arrays.sort(originalVet, comp);
		for(int i = 0; i < n; i++)
			assertEquals(message, originalVet[i],sortedVet[i]);
	}
}
