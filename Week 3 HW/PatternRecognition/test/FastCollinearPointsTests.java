
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
    public void Collinear_6pts_Horizontal()
    {
      Point[] pts = new Point[6];
      pts[0] = new Point(19000, 10000);
      pts[1] = new Point(18000, 10000);
      pts[2] = new Point(32000, 10000);
      pts[3] = new Point(21000, 10000);
      pts[4] = new Point(1234, 5678);
      pts[5] = new Point(14000, 10000);
      
      System.out.println("--- Collinear 6pts ---");
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
    
}
