
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
public class PointSetTests {
    
    @Test
    public void NearestNeighbor_10points()
    {
        drawNearestNeighbor("circle10.txt");   
    }
    
    @Test
    public void NearestNeighbor_100points()
    {
        drawNearestNeighbor("circle100.txt");
    }
    
    @Test
    public void NearestNeighbor_10Kpoints()
    {
        drawNearestNeighbor("input10K.txt");
    }
    
    
    private void drawNearestNeighbor(String filename)
    {
        NearestNeighborVisualizer.main(new String[]{"test/kdtree-test/kdtree/"+filename});
    }
    
}
