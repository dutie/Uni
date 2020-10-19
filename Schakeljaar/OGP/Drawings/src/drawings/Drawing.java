package drawings;

import java.util.Arrays;
import java.util.stream.Collectors;

class Point {
	int x,y;
	
	Point(int x, int y){this.x = x; this.y =y;}
	
	String asText() {return "(";}
}

abstract class Shape{
	abstract String asText();
	
}

class Circle extends Shape{
	Point center;
	int radius;
	
	Circle(Point center, int radius){this.center = center; this.radius = radius;}
	
	String asText() {//overrides asText in Shape
		return "(" + center.asText() + "," + radius + ")";
	}
}

class Polygon extends Shape{
	Point[] vertices;
	
	Polygon(Point[] vertices){this.vertices = vertices.clone();}
	
	String asText() {// overrides asText in Shape
		return "(" +  Arrays.stream(vertices).map(p -> p.asText()).collect(Collectors.joining(", ")) + ")";
	}
}

public class Drawing {

	Shape[] shapes;
	
	String asText() {
		String result = "";
		for(int i=0; i < shapes.length; i++) {
			result += " ";
			Shape shape = shapes[i];
//		if(shape instanceof Circle) {
			result += shape.asText();
//		} else {
//			result += ((Polygon) shape).asText();
//		}
		}
	}
}
