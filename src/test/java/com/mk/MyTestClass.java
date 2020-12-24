package com.mk;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mk.junit.Repeat;
import com.mk.junit.RepeatRunner;

/**
 * @author manki.kim
 */
@RunWith(RepeatRunner.class)
public class MyTestClass {

	@Test
	@Repeat(10)
	public void testMyCode10Times() {

	}

	@Test
	@Repeat(5)
	public void testMyCode5Times() {

	}

	@Test
	public void testNotUsingRepeat() {

	}

	@Ignore
	@Test
	@Repeat(10)
	public void testCheckIgnore() {

	}
}
