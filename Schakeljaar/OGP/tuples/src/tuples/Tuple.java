package tuples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/**
 * 
 * @author matsf
 * Each instance of this class represents a tuple that can contain multiple types of objects
 * and can have variable lenght
 * 
 * @immutable
 */
final class Tuple {
    private final List<Object> elements = new ArrayList<>();
    /**
     * Initializes the object by collection all the elements.
     *
     */
    public Tuple (Object[] input) {
        Collections.addAll(this.elements, input);
    }
    /**
     * Returns the length of the Tuple
     */
    public int getLength() {
        return this.elements.size();
    }
    /**
     * Returns the object at the given index
     * @throws ArrayIndexOutOfBoundsException when index is not in the length of the array
     * 			| !(index > this.getLength() || 0 < index)
     * @post This object's element at the given index will be returned.
     */
    public Object getElement(int index) {
        if (index < 0 || index >= this.elements.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return this.elements.get(index);
    }

    /**
    * Returns whether this object is conceptually equal to the given object.
    *
    * The implementation of this method in class java.lang.Object returns whether
    * this object and the given object are the same object.
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return Objects.equals(elements, tuple.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }
}

	
	

