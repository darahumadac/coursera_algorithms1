
import org.junit.Assert;
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
public class PointTests {
    
    @Test
    public void CompareTo_Point_Y_Is_LessThan()
    {
        Point point = new Point(3, 4);
        Point that = new Point(3, 5);
        
        Assert.assertEquals(-1, point.compareTo(that));
    }
    
    @Test
    public void CompareTo_Point_Y_Is_GreaterThan()
    {
        Point point = new Point(3, 5);
        Point that = new Point(3, 4);
        
        Assert.assertEquals(1, point.compareTo(that));
    }
    
    @Test
    public void CompareTo_Point_Y_Is_Equal_X_Is_LessThan()
    {
        Point point = new Point(2, 5);
        Point that = new Point(3, 5);
        
        Assert.assertEquals(-1, point.compareTo(that));
    }
    
    @Test
    public void CompareTo_Point_Y_Is_Equal_X_Is_GreaterThan()
    {
        Point point = new Point(3, 5);
        Point that = new Point(2, 5);
        
        Assert.assertEquals(1, point.compareTo(that));
    }
    
    @Test
    public void CompareTo_Points_Are_SameCoordinates()
    {
        Point point = new Point(3, 5);
        Point that = new Point(3, 5);
        
        Assert.assertEquals(0, point.compareTo(that));
    }
    
    @Test
    public void SlopeTo_HorizontalLine()
    {
        Point point = new Point(3, 5);
        Point that = new Point(6, 5);
        
        Assert.assertEquals(+0.0, point.slopeTo(that), 0.0);
    }
    
    @Test
    public void SlopeTo_VerticalLine()
    {
        Point point = new Point(3, 5);
        Point that = new Point(3, 2);
        
        Assert.assertEquals(Double.POSITIVE_INFINITY, point.slopeTo(that), 0.0);
    }
    
    @Test
    public void SlopeTo_SamePoint()
    {
        Point point = new Point(3, 5);
        Point that = new Point(3, 5);
        
        Assert.assertEquals(Double.NEGATIVE_INFINITY, point.slopeTo(that), 0.0);
    }
    
    @Test
    public void SlopeTo_DiagonalLine()
    {
        Point point = new Point(10, 3);
        Point that = new Point(5, 10);
        
        Assert.assertEquals(7.0/-5, point.slopeTo(that), 0.0);
    }
    
    @Test
    public void SlopeOrder_FirstPoint_LessThan()
    {
        Point reference = new Point(5,7);
        Point firstPoint = new Point(5,7); //same point; negative infinity
        Point secondPoint = new Point(10,7); //horizontal line
        
        Assert.assertEquals(-1, reference.slopeOrder().compare(firstPoint, secondPoint));
    }
    
    @Test
    public void SlopeOrder_FirstPoint_GreaterThan()
    {
        Point reference = new Point(5,7);
        Point firstPoint = new Point(5,10); //vertical line
        Point secondPoint = new Point(10,7); //horizontal line
        
        Assert.assertEquals(1, reference.slopeOrder().compare(firstPoint, secondPoint));
    }
    
    @Test
    public void SlopeOrder_FirstPoint_EqualWith_SecondPoint()
    {
        Point reference = new Point(5,7);
        Point firstPoint = new Point(7,10); //vertical line
        Point secondPoint = new Point(7,10); //horizontal line
        
        Assert.assertEquals(0, reference.slopeOrder().compare(firstPoint, secondPoint));
    }
    
    //Corner Case
    @Test
    public void CompareTo_MaxLimits()
    {
        Point point = new Point(0, 32766);
        Point that = new Point(0, 32767);
        
        Assert.assertEquals(-1, point.compareTo(that));
    }
    
    @Test
    public void SlopeTo_MaxLimits()
    {
        Point point = new Point(10, 32760);
        Point that = new Point(0, 32767);
        
        Assert.assertEquals(7.0/-10, point.slopeTo(that), 0.0);
    }
    
}
