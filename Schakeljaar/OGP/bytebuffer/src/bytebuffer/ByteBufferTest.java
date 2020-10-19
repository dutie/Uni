package bytebuffer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ByteBufferTest {

	@Test
	void test() {
	byte[] array = {0,0,0,0};
	ByteBuffer buffer = new ByteBuffer(array);
	buffer.put((byte)1);
	buffer.put((byte)2);
	buffer.put((byte)3);
	buffer.put((byte)4);
	assertArrayEquals(new byte[] {1,2,3,4}, 
			buffer.getByteArray());

	assertEquals(0, array[0]);
	}

}
