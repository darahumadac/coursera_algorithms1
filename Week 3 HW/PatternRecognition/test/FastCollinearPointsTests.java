
import edu.princeton.cs.algs4.In;
import junit.framework.Assert;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darah
 */
public class FastCollinearPointsTests {
    
    @Test(expected = NullPointerException.class)
    public void Null_Points_Given()
    {
        FastCollinearPoints fastPts = new FastCollinearPoints(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void Has_Null_Points_Given()
    {
        Point[] pts = new Point[3];
        pts[0] = new Point(0, 0);
        pts[1] = null;
        pts[2] = new Point(1, 3);
        
        FastCollinearPoints fastPts = new FastCollinearPoints(pts);   
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void HasDuplicate_Points_Given()
    {
        Point[] pts = new Point[5];
        pts[0] = new Point(2, 7);
        pts[1] = new Point(1, 9);
        pts[2] = new Point(2, 1);
        pts[3] = new Point(1, 9);
        pts[4] = new Point(0, 0);
        
        FastCollinearPoints fastPts = new FastCollinearPoints(pts);   
    }
    
    @Test
    public void Collinear()
    {
      Point[] pts = new Point[10];
      pts[0] = new Point(1, 1);
      pts[1] = new Point(2, 2);
      pts[2] = new Point(3, 3);
      pts[3] = new Point(4, 4);
      pts[4] = new Point(3, 2);
      pts[5] = new Point(4, 1);
      pts[6] = new Point(4, 3);
      pts[7] = new Point(4, 2);
      pts[8] = new Point(1, 5);
      pts[9] = new Point(2, 4);
      
      FastCollinearPoints fastPts = new FastCollinearPoints(pts);
      for(LineSegment ls : fastPts.segments())
      {
          System.out.println(ls.toString());
      }
      Assert.assertEquals(3, fastPts.numberOfSegments());

    }
    
    @Test
    public void Collinear_5pts_Horizontal()
    {
      Point[] pts = new Point[5];
      pts[0] = new Point(19000, 10000);
      pts[1] = new Point(18000, 10000);
      pts[2] = new Point(32000, 10000);
      pts[3] = new Point(21000, 10000);
      pts[4] = new Point(1234, 5678);
      
      System.out.println("--- Collinear 5pts ---");
      FastCollinearPoints fastPts = new FastCollinearPoints(pts);
      for(LineSegment ls : fastPts.segments())
      {
          System.out.println(ls.toString());
      }
      Assert.assertEquals(1, fastPts.numberOfSegments());

    }
    
    @Test
    public void Collinear_8pts()
    {
      Point[] pts = new Point[8];
      pts[0] = new Point(10000, 0);
      pts[1] = new Point(0, 10000);
      pts[2] = new Point(3000, 7000);
      pts[3] = new Point(7000, 3000);
      pts[4] = new Point(20000, 21000);
      pts[5] = new Point(3000, 4000);
      pts[6] = new Point(14000, 15000);
      pts[7] = new Point(6000, 7000);
      
      System.out.println("--- Collinear 8pts ---");
      FastCollinearPoints fastPts = new FastCollinearPoints(pts);
      for(LineSegment ls : fastPts.segments())
      {
          System.out.println(ls.toString());
      }
      Assert.assertEquals(2, fastPts.numberOfSegments());

    }
    
    @Test
    public void Collinear_9pts()
    {
        Point[] pts = new Point[9];
        pts[0] = new Point(9000, 9000);
        pts[1] = new Point(8000, 8000);
        pts[2] = new Point(7000, 7000);
        pts[3] = new Point(6000, 6000);
        pts[4] = new Point(5000, 5000);
        pts[5] = new Point(4000, 4000);
        pts[6] = new Point(3000, 3000);
        pts[7] = new Point(2000, 2000);
        pts[8] = new Point(1000, 1000);
        
    System.out.println("--- Collinear 9pts ---");
      FastCollinearPoints fastPts = new FastCollinearPoints(pts);
      for(LineSegment ls : fastPts.segments())
      {
          System.out.println(ls.toString());
      }
      Assert.assertEquals(1, fastPts.numberOfSegments());

    }
    
    private void testFromFile(String filename, int expectedSegmentCount)
    {
        // read the n points from a file
        In in = new In(filename);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        // print the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            System.out.println(segment);
        }
        
        Assert.assertEquals(expectedSegmentCount, collinear.numberOfSegments());
    }
    
    @Test
    public void Collinear_InARow()
    {
        testFromFile("test/inarow.txt", 5);
    }
    
    @Test
    public void Collinear_Input300()
    {
        testFromFile("test/input300.txt", 6);
        
    }
    
    @Test
    public void Collinear_Input80()
    {
        testFromFile("test/input80.txt", 31);
        
    }
    
    @Test
    public void Collinear_Input9()
    {
        testFromFile("test/input9.txt", 1);
        
    }
    
    @Test
    public void Collinear_Input10()
    {
        testFromFile("test/input10.txt", 2);
        
    }
    
    @Test
    public void Collinear_Input50()
    {
        testFromFile("test/input50.txt", 7);
        
    }
    
}
