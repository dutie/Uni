package fallhomework;

public interface TwoThirdsTransformation extends Portal implements Transformation {


	public TwoThirdsTransformation() {}
	/**
	 * @throws IllegalArgumentException | value <= 0
	 */
	@Override
	public int apply(int value) {
		if(value < 0) {
			throw new IllegalArgumentException("Value must be larger than 0.");
		}
		return Math.round(value*(2/3));
	}

}
