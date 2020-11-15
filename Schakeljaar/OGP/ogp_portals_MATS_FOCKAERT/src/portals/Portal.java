package portals;

/**
 * @invar | getPair() == null || getPair().getPair() == this
 */
public class Portal{
	/**
	 * @invar | pair == null || pair.pair == this
	 * @peerObject
	 */
	private Portal pair;
	private Transformation tf;
	
	/**
	 * @post | getPair() == null
	 */
	public Portal(Transformation tf) {
		this.tf = tf;
	}
	/**
	 * @throws IllegalArgumentException | other == null
	 * @throws IllegalArgumentException | getPair() != null
	 * @throws IllegalArgumentException | other.getPair() != null
	 * 
	 * @mutates | this, other
	 * 
	 * @post | getPair() == other
	 */
	public void setPair(Portal other) {
		if(other == null) {
			throw new IllegalArgumentException("No portal to pair provided.");
		}
		if(this.pair != null) {
			throw new IllegalArgumentException("This portal is already paired to another.");		
			}
		if (other.pair != null) {
			throw new IllegalArgumentException("The other portal is already paired to another.");
		}
//		if override of pairs is allowed:
//		if(this.pair != null) {
//			this.pair.removePair();
//		}
		
		this.pair = other;
		other.pair = this;
		
	}
	/**
	 * @peerObject
	 */
	public Portal getPair() {
		return this.pair;
	}
	/**
	 * @throws IllegalArgumentException | getPair() == null
	 * @mutates | this
	 * @post | getPair() == null
	 * @post | old(getPair()).getPair() == null
	 */
	public void removePair() {
		pair.pair = null;
		this.pair = null;
	}

	/**
	 * Returns a size of an object scaled by the portal's transformation
	 * 
	 * @throws IllegalArgumentException if objSize negative
	 *   | 0 > objSize
	 */
	public int transform(int objSize) {
		if(0 > objSize)
			throw new IllegalArgumentException("Size of object must be positive.");
		return tf.apply(objSize);
	}
	
	
}
