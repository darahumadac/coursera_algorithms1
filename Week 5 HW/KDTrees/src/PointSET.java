
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darah
 */
public class PointSET {
    
    private SET<Point2D> points;
    
    // construct an empty set of points 
    public PointSET()
    {
        points = new SET<>();
    }
    
    // is the set empty? 
    public boolean isEmpty()
    {
        return points.isEmpty();
    }
    
    // number of points in the set 
    public int size()
    {
        return points.size();
    }
    
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p)
    {   
        points.add(p);
    }
    
    // does the set contain point p? 
    public boolean contains(Point2D p)
    {
        return points.contains(p);
    }
    
    // draw all points to standard draw 
    public void draw()
    {
        for (Point2D point : points)
        {
            point.draw();
        }
    }
    
    // all points that are inside the rectangle 
    public Iterable<Point2D> range(RectHV rect)
    {
        ArrayList<Point2D> pointsInRect = new ArrayList<>();
        for (Point2D point : points)
        {
            if (rect.contains(point))
            {
                pointsInRect.add(point);
            }
        }
        
        return pointsInRect;
    }
    
    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p)
    {
        Point2D nearestNeighbor = null;
        for (Point2D neighbor : points)
        {
            if (nearestNeighbor == null ||
                    neighbor.distanceTo(p) < nearestNeighbor.distanceTo(p))
            {
                nearestNeighbor = neighbor;
            }
        }
        
        return nearestNeighbor;
    }

    // unit testing of the methods (optional) 
    public static void main(String[] args)
    {
    
    }
}
