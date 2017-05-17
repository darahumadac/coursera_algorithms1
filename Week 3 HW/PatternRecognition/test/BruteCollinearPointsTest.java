
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
public class BruteCollinearPointsTest {
    
    @Test(expected = NullPointerException.class)
    public void Null_Points_Given()
    {
        BruteCollinearPoints brutePts = new BruteCollinearPoints(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void Has_Null_Points_Given()
    {
        Point[] pts = new Point[3];
        pts[0] = new Point(0, 0);
        pts[1] = null;
        pts[2] = new Point(1, 3);
        
        BruteCollinearPoints brutePts = new BruteCollinearPoints(pts);   
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
        
        BruteCollinearPoints brutePts = new BruteCollinearPoints(pts);   
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
      
      BruteCollinearPoints brutePts = new BruteCollinearPoints(pts);
      for(LineSegment ls : brutePts.segments())
      {
          System.out.println(ls.toString());
      }
      Assert.assertEquals(3, brutePts.numberOfSegments());

    }
    
}
