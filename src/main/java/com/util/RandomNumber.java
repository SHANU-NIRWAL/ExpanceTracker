package com.util;

import java.util.Random;

public class RandomNumber {
	public static String randomNumberGenerator() {
		Random random=new Random();
		String rnd=String.format("%04d",random.nextInt(1000));
		return rnd ;
	}

}
