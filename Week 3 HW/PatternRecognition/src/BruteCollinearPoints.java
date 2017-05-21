
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

public class BruteCollinearPoints {
    
    private Point[] points;
    private int pointsCount;
    private ArrayList<LineSegment> lineSegments;
    private LineSegment[] lineSegArray;
    
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] pointsInput)
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
        
        findCollinearPointsLineSegments();
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
    
    private void findCollinearPointsLineSegments()
    {
        Arrays.sort(points);

        for (int p = 0; p < pointsCount-3; p++)
        {   
            for (int q = p+1; q < pointsCount-2; q++)
            {
                for (int r = q+1; r < pointsCount-1; r++)
                {
                    for (int s = r+1; s < pointsCount; s++)
                    {
                        Point reference = points[p];
                        Point qPt = points[q];
                        Point rPt = points[r];
                        Point sPt = points[s];

                        if (reference.slopeTo(qPt) == reference.slopeTo(rPt) && 
                           reference.slopeTo(rPt) == reference.slopeTo(sPt))
                        {
                            lineSegments.add(new LineSegment(reference, sPt));
                        }
                    }
                }
            }
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
        lineSegArray = new LineSegment[numberOfSegments()];
        return lineSegments.toArray(lineSegArray);
    }
    
}
