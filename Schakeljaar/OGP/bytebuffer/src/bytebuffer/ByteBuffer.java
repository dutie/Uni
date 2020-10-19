package bytebuffer;

public class ByteBuffer {
	/**
	 * @representationObject
	 */
	private byte[] array;
	private int offset;
	
	public byte[] getByteArray() {
		return array;
	}
	public int getOffset() {
		return offset;
	}
	
	public ByteBuffer(byte[] array) {
		this.array = array.clone();
	}

/**
 * @mutates | this
 * @param b
 */
	public void put(byte b) {
		array[offset] = b;
		offset++;
	}
}
