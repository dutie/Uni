package rationalNumber;

public class MoreMath {

	public static long absExact(long x) {
		return Math.abs(x);
	}
	
	public static long gcd(long a, long b){
		
		if(a == 0) return b;
		if(b == 0) return a;
		
		if(a < b ) return gcd(b%a, a);
		
		return gcd(a % b, b);
	}
}
