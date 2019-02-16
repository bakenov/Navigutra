package imc.visitor.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import imc.visitor.shape.IShape;
import imc.visitor.shape.factory.ShapeFactory;
import imc.visitor.shape.visitor.AreaVisitor;
import imc.visitor.shape.visitor.PerimeterVisitor;
import imc.visitor.utils.DistanceUnits;

/**
 * The application that makes arbitrary instances of the geometric shapes and
 * applies the AreaVisitor.
 * 
 * @author bakenov
 *
 */
public class Application {

	private static final int DEFAULT_NUMBER_SHAPES = 20;
	private static final DistanceUnits DEFAULT_UNITS = DistanceUnits.METER;

	private static final double MAX_RADIUS = 10;
	private static final double MAX_SIDE = 20;
	private static final double MAX_ANGLE = 180;

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	private final int numberShapes;
	private final ShapeFactory factory;
	private final Random rand;
	private final List<IShape> shapes;
	private final AreaVisitor areaVisitor = new AreaVisitor(
			DistanceUnits.CENTIMETER);
	private final PerimeterVisitor pVisitor = new PerimeterVisitor(
			DistanceUnits.METER);

	/**
	 * Constructs the application
	 * 
	 * @param args the application arguments
	 */
	public Application(String[] args) {
		numberShapes = getNumberShapes(args);
		factory = new ShapeFactory(DEFAULT_UNITS);
		rand = new Random();
		shapes = new ArrayList<>();
	}

	private int getNumberShapes(String[] args) {
		int retVal = DEFAULT_NUMBER_SHAPES;
		if (args != null) {
			int n = args.length;
			if (n == 1) {
				retVal = Integer.parseInt(args[0]);
			}
		}
		return retVal;
	}

	private void buildShapes() {
		rand.ints(numberShapes, 0, 3).forEach(this::buildShape);
		log.info("shapes : {}", shapes);
	}

	private void buildShape(int selection) {
		IShape shape = null;
		switch (selection) {
		case 0:
			// build circle
			shape = factory.createShape(getRandomValue(MAX_RADIUS));
			break;
		case 1:
			// build rectangle
			shape = factory.createShape(getRandomValue(MAX_SIDE),
					getRandomValue(MAX_SIDE));
			break;
		case 2:
			// build triangle
			shape = factory.createShape(getRandomValue(MAX_SIDE),
					getRandomValue(MAX_SIDE), getRandomValue(MAX_ANGLE));
			break;
		default:
		}
		if (shape != null)
			shapes.add(shape);
	}

	private double getRandomValue(double max) {
		return rand.nextDouble() * max;
	}

	/**
	 * Starts the application
	 */
	public void start() {
		buildShapes();
		shapes.forEach((s) -> s.accept(areaVisitor));
		shapes.forEach((s) -> s.accept(pVisitor));

		log.info("total area is : {} in square {}", areaVisitor.getTotalArea(),
				areaVisitor.getUnits().getSymbol());
		log.info("total perimeter is : {} in {}", pVisitor.getTotalLength(),
				pVisitor.getUnits().getSymbol());
	}

	public static void main(String[] args) {
		Application app = new Application(args);
		app.start();
	}
}
