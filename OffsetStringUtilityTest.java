package com.marketLogic.StringUtility.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.marketLogic.StringUtility.OffsetStringUtility;

/**
 * This is the Junit Test class for OffsetStringUtility class
 * @author Neha Gautam
 *
 */
public class OffsetStringUtilityTest {
		
	@Test
	public void testStringOffset()
	{
		String inputString = "abcde";
		String offset = "2";
		String evalString=OffsetStringUtility.encodeStringOffset(inputString.toCharArray(), offset);
		assertTrue("cdefg".equals(evalString));

	}
	
	@Test
	public void testStringsplChar()
	{
		String inputString = "abcd98#";
		String offset = "1";
		String evalString = OffsetStringUtility.encodeStringOffset(inputString.toCharArray(), offset);
		assertTrue("cdef98#".equals(evalString));

	}
	
	@Test
	public void testStingCaps()
	{
		String inputString = "AbCdE48#";
		String offset = "1";
		String evalString = OffsetStringUtility.encodeStringOffset(inputString.toCharArray(), offset);
		assertTrue("BcDeF48#".equals(evalString));

	}
	
	@Test
	public void testStringNegativeOffset()
	{
		String inputString = "AbCdE48#";
		String offset = "-1";
		String evalString = OffsetStringUtility.encodeStringOffset(inputString.toCharArray(), offset);
		assertTrue("ZaBcD48#".equals(evalString));

	}	
	
}
