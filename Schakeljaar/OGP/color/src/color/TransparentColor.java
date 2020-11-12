package color;

public class TransparentColor extends Color {

	public final int opacity;
	
	public TransparentColor(int red, int green, int blue, int opacity) {
		super(red,green,blue);
		this.opacity = opacity;
	}
	@Override
	public String toString() {return "rgba("+red + ", "+green+", " + blue+ ", " + opacity+")";}
	@Override
	public boolean equals(Object other) {
		return super.equals(other) && // this. is binded to this object dynamically and super. is binded statically
				//other instanceof TransparentColor && //commented out due to more efficient code in super
				((TransparentColor)other).opacity == opacity;
	}
	
}
