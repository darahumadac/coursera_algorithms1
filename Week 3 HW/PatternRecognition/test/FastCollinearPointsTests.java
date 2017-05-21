
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
        FastCollinearPoints brutePts = new FastCollinearPoints(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void Has_Null_Points_Given()
    {
        Point[] pts = new Point[3];
        pts[0] = new Point(0, 0);
        pts[1] = null;
        pts[2] = new Point(1, 3);
        
        FastCollinearPoints brutePts = new FastCollinearPoints(pts);   
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
        
        FastCollinearPoints brutePts = new FastCollinearPoints(pts);   
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
      
      FastCollinearPoints brutePts = new FastCollinearPoints(pts);
      for(LineSegment ls : brutePts.segments())
      {
          System.out.println(ls.toString());
      }
      Assert.assertEquals(3, brutePts.numberOfSegments());

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
      FastCollinearPoints brutePts = new FastCollinearPoints(pts);
      for(LineSegment ls : brutePts.segments())
      {
          System.out.println(ls.toString());
      }
      Assert.assertEquals(1, brutePts.numberOfSegments());

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
      FastCollinearPoints brutePts = new FastCollinearPoints(pts);
      for(LineSegment ls : brutePts.segments())
      {
          System.out.println(ls.toString());
      }
      Assert.assertEquals(2, brutePts.numberOfSegments());

    }
    
}
