package fallhomework;

/**
 * @invar | this != null
 */
public class Portal implements Transformation{
	
	/**
	 * @invar | this != null
	 * @peerObject
	 */
	public Portal pair;

	public Portal() {}
	
	/**
	 * @peerObject
	 */
	public Portal getPair() {
		return pair;
	}
	
	/**
	 * @mutates this, other
	 */
	public void setPair(Portal other) {
		this.pair = other;
		other.pair = this;
	}
	/**
	 * @mutates this, other
	 */
	public void clearPair() {
		this.pair = null;
		this.pair.pair = null;
	}

	public int apply(int value) {return value;}
	
	
}
