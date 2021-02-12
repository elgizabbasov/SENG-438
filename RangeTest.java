/**
 * 
 */
package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeTest  {
	
	private Range range;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		range = new Range(0,10);
	}



	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  Contain Method Tests
	  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	@Test
	public void testContainAgainstUpperBoundary() { 
		assertEquals("The upper boundary value of 10 should be contained in the range",
					true, range.contains(10.0));
	}
	
	@Test
	public void testContainAgainstLowerBoundary() { 
		assertEquals("The lower boundary value of 0 should be contained in the range",
					true, range.contains(0.0));
	}
	
	@Test
	public void testContainAgainstValueBelowUpperBoundary() {
		assertEquals("A value just below the upper boundary of 9.99 should be contained in the range",
					true, range.contains(9.99));
	}
	
	@Test
	public void testContainAgainstValueAboveLowerBoundary() {
		assertEquals("A value just above the lower boundary of 0.01 should be contained in the range",
					true, range.contains(0.01));
	}
	
	@Test
	public void testContainAgainstValueBelowLowerBoundary() {
		assertEquals("A value just below the lower boundary of -0.1 should not be contained in the range",
					false, range.contains(-0.01));
	}
	
	@Test
	public void testContainAgainstValueAboveUpperBoundary() {
		assertEquals("A value just above the upper boundary of 0.1 should not be contained in the range",
					false, range.contains(10.01));
	}
	
	@Test
	public void testContainsAgainstValueBelowRange() {
		assertEquals("A value below the range of -1 should not be contained in the range",
					false, range.contains(-1.0));
	}
	
	@Test
	public void testContainsAgainstValueAboveRange() {
		assertEquals("A value above the range of 11 should not be contained in the range",
				false, range.contains(11.0));
	}
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  GetLength Method Tests
	  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	@Test
	public void testGetLengthReturnsProperLength() {
		assertEquals("The range should be precisely equal to 10", 10.0, range.getLength(), 0.0);
	}
	
	@Test
	public void testGetLengthDoesNotReturnBelowCorrectValue() {
		assertFalse("The range should not be below 10", range.getLength() < 10.0);
	}
	
	@Test
	public void testGetLengthDoesNotReturnAboveCorrectValue() { //tests whether boundaries near the length are considered to be true even if false
		assertFalse("The range should not be above 10",range.getLength() > 10.0);
	}
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  GetCentralValue Method Tests
	  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	@Test
	public void testGetCentralValue() {
		assertEquals("The central value of the range should be exactly 5", 5, range.getCentralValue(), 0.0);
	}
	
	@Test
	public void testGetCentralValueIsNotBelowCorrectValue() { 
		assertFalse("The central value should not be below 5", range.getCentralValue() < 5.0);
	}
	
	@Test
	public void testGetCentralValueIsNotAboveCorrectValue() {
		assertFalse("The central value should not be above 5",range.getCentralValue() > 5.0);
	}
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  GetLowerBound Method Tests
	  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	@Test
	public void testGetLowerBoundValue() {
		assertEquals("The lower bound should be exactly 0", 0.0, range.getLowerBound(), 0.0);
	}
	
	@Test
	public void testGetLowerBoundValueIsNotAboveCorrectValue() { 
		assertFalse("The lower bound should not be above 0", range.getLowerBound() > 0.0);
	}
	
	@Test
	public void testGetLowerBoundValueIsNotBelowCorrectValue() { 
		assertFalse("The lower bound should not be below 0" ,range.getLowerBound() < 0.0);
	}
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  GetUpperBound Method Tests
	  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	@Test
	public void testGetUpperBoundValue() {
		assertEquals("The upper bound should be exactly 10", 10.0, range.getUpperBound(), 0.0);
	}
	
	@Test
	public void testGetUpperBoundValueIsNotAboveCorrectValue() { 
		assertFalse("The upper bound should not be above 10",range.getUpperBound() > 10.0);
	}
	
	@Test
	public void testGetUpperBoundValueIsNotBelowCorrectValue() { 
		assertFalse("The upper bound should not be below 10",range.getUpperBound() < 10.0);
	}
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		range = null;
	}
}
