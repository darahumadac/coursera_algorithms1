
import java.util.ArrayList;
import java.util.Arrays;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darah
 */
public class FastCollinearPoints {

    private Point[] points;
    private int pointsCount;
    private ArrayList<LineSegment> lineSegments;
    private LineSegment[] lineSegmentArray;
    
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] pointsInput)
    {
        if (pointsInput == null || containsNullPoint(pointsInput))
        {
            throw new NullPointerException();
        }
        
        if (hasDuplicatePoint(pointsInput))
        {
            throw new IllegalArgumentException();
        }
        
        this.points = Arrays.copyOf(pointsInput, pointsInput.length);
        pointsCount = pointsInput.length;
        lineSegments = new ArrayList<>();
        
        findCollinearPoints();
    }
    
    private boolean containsNullPoint(Point[] pointsInput)
    {
        for (Point p : pointsInput)
        {
            if (p == null)
            {
                return true;
            }
        }
        return false;
    }
    
    private boolean hasDuplicatePoint(Point[] pointsInput)
    {
        Point[] temp = Arrays.copyOf(pointsInput, pointsInput.length);
        Arrays.sort(temp);
        
        for (int i = 1; i < temp.length; i++)
        {
            if (temp[i-1].compareTo(temp[i]) == 0)
            {
                return true;
            }
        }
        
        return false;
    }
    
    private void findCollinearPoints()
    {
        Arrays.sort(points);
        printPoints(points, "Sorted Points");
        
        ArrayList<Point> slopes;

        for (int p = 0; p < pointsCount; p++)
        {
            slopes = new ArrayList<>();
            
            for (int q = 0; q < pointsCount; q++)
            {
                if(p != q)
                {
                    slopes.add(points[q]);
                }
            }
            
            if (slopes.size() > 0)
            {
                slopes.sort(points[p].slopeOrder());
                printPoints(slopes, "Sorted Points by Slopes. Reference: " 
                        + points[p]);
                
                if(points[p].compareTo(slopes.get(0)) <= 0)
                {
                    setCollinearLineSegments(points[p], slopes);
                }
                
            }
            
        }
    }
    
    private void setCollinearLineSegments(Point reference, 
                                          ArrayList<Point> sortedPoints)
    {
        int sortedPointCount = sortedPoints.size();
        
        ArrayList<Point> adjacentPoints = new ArrayList<>();

        Point prev = sortedPoints.get(0);
        double prevSlope = reference.slopeTo(prev);
        adjacentPoints.add(prev);

        Point current;
        double currentSlope;
        
        for (int i = 1; i < sortedPointCount; i++)
        {
            current = sortedPoints.get(i);
            currentSlope = reference.slopeTo(current);
            
            if(reference.compareTo(prev) <= 0)
            {
                if (prevSlope == currentSlope)
                {
                    adjacentPoints.add(current);
                }
                else
                {
                    addNewLineSegment(reference, adjacentPoints);

                    adjacentPoints.clear();
                    adjacentPoints.add(current);
                }
            }
            
            prevSlope = currentSlope;
            prev = current;
        }
        
        addNewLineSegment(reference, adjacentPoints);
    }
    
    private void addNewLineSegment(Point reference, 
                                   ArrayList<Point> adjacentPoints)
    {
        int adjacentPointsCount = adjacentPoints.size();
        if (adjacentPointsCount >= 3)
        {
            lineSegments.add(new LineSegment(reference, 
                    adjacentPoints.get(adjacentPointsCount-1)));
        }
    }
    
    // the number of line segments
    public int numberOfSegments()
    {
        return lineSegments.size();
    }
    
    // the line segments
    public LineSegment[] segments()
    {
        lineSegmentArray = new LineSegment[numberOfSegments()];
        return lineSegments.toArray(lineSegmentArray);
    }
    
    private void printPoints(Point[] points, String message)
    {
        System.out.println(message);
        for(Point p : points)
        {
            System.out.print(p + "-> ");
        }
        System.out.println();
        System.out.println("--- End ---");
    }
    
    private void printPoints(ArrayList<Point> points, String message)
    {
        System.out.println(message);
        for(Point p : points)
        {
            System.out.print(p + "-> ");
        }
        System.out.println();
        System.out.println("--- End ---");
    }

    
}
