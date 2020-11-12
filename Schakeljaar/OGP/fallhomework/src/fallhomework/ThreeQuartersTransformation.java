package fallhomework;

public class ThreeQuartersTransformation extends Portal implements Transformation {

	public ThreeQuartersTransformation() {}
	/**
	 * @pre | value > 0
	 */
	@Override
	public int apply(int value) {
		return Math.round(value*(3/4));
	}

}
