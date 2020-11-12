package color;

import javax.management.RuntimeErrorException;

public class Color {
	public final int red, green, blue;
	public Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	public int getHue() {throw new RuntimeException("");}
	public int getSaturation() {throw new RuntimeException("");}
	public int getValue() {throw new RuntimeException("");}
	@Override
	public String toString() {return "rgb("+red + ", "+green+", " + blue+")";}
	@Override
	public boolean equals(Object other) {
		//return other instanceof Color && // commented out due to more efficient line below
		return other.getClass() == this.getClass() &&
				((Color)other).red == red &&
				((Color)other).green == green &&
				((Color)other).blue == blue;
	}
	
}
