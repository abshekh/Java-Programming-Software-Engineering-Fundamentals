import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.Point;
import edu.duke.Shape;

import java.io.File;
import java.util.Collection;

public class PerimeterAssignmentRunner {

    public double getPerimeter(Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints(Shape s) {
        int size = ((Collection) s.getPoints()).size();
        return size;
    }

    public double getAverageLength(Shape s) {
        double perimeter = getPerimeter(s);
        int numOfSides = getNumPoints(s);
        return perimeter / numOfSides;
    }

    public double getLargestSide(Shape s) {
        double maxSide = 0.0;

        Point prevPt = s.getLastPoint();

        for(Point p : s.getPoints()) {
           double size = prevPt.distance(p);
           maxSide = Math.max(maxSide, size);
           prevPt = p;
        }

        return maxSide;
    }

    public double getLargestX(Shape s) {
        double maxX = Double.MIN_VALUE;

        for(Point p : s.getPoints()) {
           maxX = Math.max(p.getX(), maxX);
        }

        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {

        double maxPerimeter = 0.0;

        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            maxPerimeter = Math.max(maxPerimeter, perimeter);
        }

        return maxPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        File temp = null;
        double maxPerimeter = 0.0;

        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if(perimeter > maxPerimeter) {
                temp = f;
                maxPerimeter = perimeter;
            }
        }

        if(temp == null) return "";

        return temp.getName();
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);

        int numOfPoints = getNumPoints(s);
        System.out.println("number of points = " + numOfPoints);

        double avgLength = getAverageLength(s);
        System.out.println("average length = " + avgLength);

        double largestSide = getLargestSide(s);
        System.out.println("largest side = " + largestSide);

        double largestX = getLargestX(s);
        System.out.println("largest X = " + largestX);


    }

    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        String largestPerimeterFile = getFileWithLargestPerimeter();
        System.out.println("largest perimeter file = " + largestPerimeterFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(6, 0));
        triangle.addPoint(new Point(3, 6));
        for (Point p : triangle.getPoints()) {
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
