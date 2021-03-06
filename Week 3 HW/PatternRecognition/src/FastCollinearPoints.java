
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
        
        ArrayList<Point> slopes;
        for (int p = 0; p < pointsCount; p++)
        {
            slopes = new ArrayList<>();
            
            for (int q = 0; q < pointsCount; q++)
            {
                if (p != q)
                {
                    slopes.add(points[q]);
                }
            }
            
            if (slopes.size() > 0)
            {
                slopes.sort(points[p].slopeOrder());
                setCollinearLineSegments(points[p], slopes);
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
            
            prevSlope = currentSlope;
        }
        
        addNewLineSegment(reference, adjacentPoints);        
        
    }
    
    private void addNewLineSegment(Point reference, 
                                   ArrayList<Point> adjacentPoints)
    {
        int adjacentPointsCount = adjacentPoints.size();
        if (adjacentPointsCount >= 3 && 
                reference.compareTo(adjacentPoints.get(0)) <= 0)
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

}
