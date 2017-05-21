
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

//examines 4 points at a time and checks whether they all lie on the same 
//line segment, returning all such line segments. To check whether the 4 points 
//p, q, r, and s are collinear, check whether the three slopes between p and q,
//between p and r, and between p and s are all equal.
public class BruteCollinearPoints {
    
    private Point[] points;
    private int pointsCount;
    private ArrayList<LineSegment> lineSegments;
    private LineSegment[] lineSegArray;
    
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points)
    {
        if(points == null || containsNullPoint(points))
        {
            throw new NullPointerException();
        }
        
        if(hasDuplicatePoint(points))
        {
            throw new IllegalArgumentException();
        }
        
        this.points = points;
        pointsCount = points.length;
        
        lineSegments = new ArrayList<>();
        
        findCollinearPointsLineSegments();
    }
    
    private boolean hasDuplicatePoint(Point[] points)
    {
        Point[] temp = Arrays.copyOf(points, points.length);
        Arrays.sort(temp);
        for(int i = 1; i < temp.length; i++)
        {
            if(temp[i-1].compareTo(temp[i]) == 0)
            {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean containsNullPoint(Point[] points)
    {
        for(Point p : points)
        {
            if(p == null)
            {
                return true;
            }
        }
        return false;
    }
    
    private void findCollinearPointsLineSegments()
    {
        Arrays.sort(points);

        for(int p = 0; p < pointsCount-3; p++)
        {   
            for(int q = p+1; q < pointsCount-2; q++)
            {
                for(int r = q+1; r < pointsCount-1; r++)
                {
                    for(int s = r+1; s < pointsCount; s++)
                    {
                        Point reference = points[p];
                        Point qPt = points[q];
                        Point rPt = points[r];
                        Point sPt = points[s];

                        if(reference.slopeTo(qPt) == reference.slopeTo(rPt) && 
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
