package org.jfree.data.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.jfree.data.*;
import org.junit.*;
import org.jmock.Expectations;
import org.jmock.Mockery;

/**
 * Test cases for DataUtilities class
 */
public class DataUtilitiesTest extends DataUtilities {
	
	//Methods Tested: All
	
	private Mockery mockingContext = new Mockery(); 
	private final Values2D values = mockingContext.mock(Values2D.class);
	private final KeyedValues keyedValues = mockingContext.mock(KeyedValues.class);
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		mockingContext.checking(new Expectations() { 
		{ 
			
		one(values).getRowCount(); will(returnValue(4));
		one(values).getColumnCount(); will(returnValue(3));
		
		one(values).getValue(0, 0); will(returnValue(-5));
		one(values).getValue(1, 0); will(returnValue(3)); 
		one(values).getValue(2, 0); will(returnValue(2));
		
		one(values).getValue(0, 1); will(returnValue(-5));
		one(values).getValue(1, 1); will(returnValue(7)); 
		one(values).getValue(2, 1); will(returnValue(5.5)); 
		
		one(values).getValue(0, 2); will(returnValue(0));
		one(values).getValue(1, 2); will(returnValue(-10)); 
		one(values).getValue(2, 2); will(returnValue(3)); 
		
		one(values).getValue(3, 0); will(returnValue(0));
		one(values).getValue(3, 1); will(returnValue(0.0)); 
		one(values).getValue(3, 2); will(returnValue(0.00)); 
		
		one(values).getValue(0, 3); will(returnValue(0));
		one(values).getValue(1, 3); will(returnValue(0.0)); 
		one(values).getValue(2, 3); will(returnValue(0.00));
		one(values).getValue(3, 3); will(returnValue(0.000));
		
		/*
		 * Table created above and used in test
		 [-5, -5, 0, 0]
		 [ 3, 7, -10, 0.0]
		 [2, 5.5, 3, 0.00]
		 [0, 0.0, 0.00, 0.000] 
		*/
		
		
		//Input
		// Key    Value
		//  0		5
		//  1		9
		//	2		2
					
		//Expected Output
		// Key	  Value
		//	0	  0.3125
		//  1	  0.875
		//  2	   1.0
		List<Integer> keyList = new ArrayList<>();
		Collections.addAll(keyList, 0, 1, 2);
		allowing(keyedValues).getKeys(); will(returnValue(keyList));

		allowing(keyedValues).getItemCount(); will(returnValue(3));
		
		allowing(keyedValues).getIndex(new Integer(0)); will(returnValue(0));
		allowing(keyedValues).getIndex(new Integer(1)); will(returnValue(1));
		allowing(keyedValues).getIndex(new Integer(2)); will(returnValue(2));

		allowing(keyedValues).getKey(0); will(returnValue(new Integer(0)));
		allowing(keyedValues).getKey(1); will(returnValue(new Integer(1)));
		allowing(keyedValues).getKey(2); will(returnValue(new Integer(2)));		
				
		allowing(keyedValues).getValue(0); will(returnValue(5)); //(5 / 16)
		allowing(keyedValues).getValue(1); will(returnValue(9));  //((5 + 9) / 16)
		allowing(keyedValues).getValue(2); will(returnValue(2));   //((5 + 9 + 2) / 16)
				
		}
		});
	}
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  CalculateColumnMethod Tests
	  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

	
	
	@Test
	public void testCalculateColumnTotalForZeroSum() { //check if a column can sum to zero
		double value = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(0,value,0.001);
	}
	
	@Test
	public void testCalculateColumnTotalForPositiveSum() { //check if doubles can be added properly
		double value = DataUtilities.calculateColumnTotal(values, 1);
		assertEquals(7.5,value,0.001);
	}
	
	@Test
	public void testCalculateColumnTotalForNegativeSum() { //check if a sum can be negative 
		double value = DataUtilities.calculateColumnTotal(values, 2);
		assertEquals(-7,value,0.001);
	}
	
	@Test
	public void testCalculateColumnTotalForAllZeros() { //check if a column of zeros adds to zero 
		double value = DataUtilities.calculateColumnTotal(values, 3);
		assertEquals(0,value,0.001);
	}
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  CalculateRowMethod Tests
	  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

	@Test
	public void testCalculateRowTotalForNegativeRow() { //check if a sum can be negative 
		double value = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(-10,value,0.001);
	}
	
	@Test
	public void testCalculateRowTotalForProperArithmatic() { //check if a row  can sum to zero 
		double value = DataUtilities.calculateRowTotal(values, 1);
		assertEquals(0,value,0.001);
	}
	
	@Test
	public void testCalculateRowTotalForPositiveRow() { //check if doubles are added properly 
		double value = DataUtilities.calculateRowTotal(values, 2);
		assertEquals(10,value,0.001);
	}
	
	@Test
	public void testCalculateRowTotalForAllZeros() { //check if a row of zeros can sum to zero 
		double value = DataUtilities.calculateRowTotal(values, 3);
		assertEquals(0,value,0.001);
	}
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  CreateNumberArray Method Tests
	  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

	@Test 
	public void testCreateNumberArrayForEmptyArray()
	{
		double[] myArg = new double[0];
		Number[] expected = new Number[0];
		Number[] array = DataUtilities.createNumberArray(myArg);
		assertArrayEquals(expected,array);
	}
	
	@Test
	public void testCreateNumberArrayCorrectOutputForPositives() { //tests if createNumberArray outputs the correct expected array
		double[] myArg = {1.0,1.4,0.9,42,3.0};
		Number[] expected = {1.0,1.4,0.9,42.0,3.0};
		Number[] array = DataUtilities.createNumberArray(myArg);
		assertArrayEquals(expected,array);
		//failed for different reason (last element is usually deleted)
	}
	
	@Test
	public void testCreateNumberArrayCorrectOutputForZeros() { //tests if an array of all 0 return an array of all 0
		double[] myArg = {0.0,0.0,0.0};
		Number[] expected = {0.0,0.0,0.0};
		Number array[] = DataUtilities.createNumberArray(myArg);
		assertArrayEquals(expected,array);
		//last element is usually deleted in new array
	}
	
	@Test
	public void testCreateNumberArrayCorrectOutputForNegatives() { //tests if createNumberArray outputs the correct expected array
		double[] myArg = {-1.0,-1.4,-0.9,-42,-3.0};
		Number[] expected = {-1.0,-1.4,-0.9,-42.0,-3.0};
		Number[] array = DataUtilities.createNumberArray(myArg);
	
		assertArrayEquals(expected,array);
		//failed for different reason (last element is usually deleted)
	}
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  CreateNumberArray2D Method Tests
	  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

	@Test
	public void testCreateNumberArray2DCorrectOutputForPositives() { //tests if an array of all negatives return an array of all negatives
		double[][] myArg = {{1.0},{1.0},{1.0}};
		Number[][] expected = {{1.0},{1.0},{1.0}};
		Number[][] array = DataUtilities.createNumberArray2D(myArg);


		assertArrayEquals(expected,array);
		//failed for different reason (last element is usually deleted)
	}
	
	@Test
	public void testCreateNumberArray2DCorrectOutputForZeros() { //tests if an array of all zeros return an array of all zeros
		double[][] myArg = {{0.0},{0.0},{0.0}};
		Number[][] expected = {{0.0},{0.0},{0.0}};
		Number[][] array = DataUtilities.createNumberArray2D(myArg);
		assertArrayEquals(expected,array);
		//failed for different reason (last element is usually deleted)
	}
	@Test
	public void testCreateNumberArray2DCorrectOutputForNegatives() { //tests if an array of all negatives return an array of all negatives
		double[][] myArg = {{-1.0},{-1.0},{-1.0}};
		Number[][] expected = {{-1.0},{-1.0},{-1.0}};
		Number[][] array = DataUtilities.createNumberArray2D(myArg);
		
		assertArrayEquals(expected,array);
		//failed for different reason (last element is usually deleted)
	}
	
	@Test 
	public void testCreateNumberArray2DOnEmptyArray()
	{
		double[][] myArg = new double[0][0];
		Number[][] expected = new Number[0][0];
		Number[][] array = DataUtilities.createNumberArray2D(myArg);
		assertArrayEquals(expected,array);
	}
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  GetCumulativePercentages Method Tests
	  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	
	@Test
	public void testGetCumulativePercentagesCorrectOutput() { //tests if getCumulativePercentages outputs correct values
		DefaultKeyedValues expected = new DefaultKeyedValues();
		expected.addValue(new Integer(0),  0.3125);
		expected.addValue(new Integer(1), 0.875);
		expected.addValue(new Integer(2), 1.0);
	
		KeyedValues actual = DataUtilities.getCumulativePercentages(keyedValues);

		assertTrue(expected.equals(actual));	  //false - getCumulativePercentages returns wrong calculations	
	}


}
