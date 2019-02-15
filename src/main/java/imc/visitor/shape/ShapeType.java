package imc.visitor.shape;

/**
 * Represents the types of the geometric shapes
 * 
 * @author bakenov
 *
 */
public enum ShapeType {

	CIRCLE('C'), RECTANGLE('R'), TRIANGLE('T');

	private final char code;

	ShapeType(char code) {
		this.code = code;
	}

	/**
	 * Returns the code of the shape
	 * 
	 * @return the code of the shape
	 */
	public char getCode() {
		return code;
	}

	/**
	 * Returns the shape type for specified code
	 * 
	 * @param code the shape code
	 * @return the shape type
	 */
	public static ShapeType getTypeByChar(char code) {
		for (ShapeType t : values()) {
			if (t.code == code)
				return t;
		}
		return null;
	}
}
