

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Write a description of test class RadarTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RadarTest
{
    /** description of instance variable x (add comment for each instance variable) */
    private int x;

    /**
     * Default constructor for objects of class RadarTest
     */
    public RadarTest()
    {
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testReturnVelocity()
    {
        Radar testRadar = new Radar(75, 34);
        testRadar.setMonsterLocation(0, 0);
        testRadar.setMonsterVelocity(1, 1);
        
        while (testRadar.scan() == true)
        {
            testRadar.scan();
        }
        
        int[] velocity = testRadar.returnVelocity();
        int dx = velocity[0];
        int dy = velocity[1];
        assertEquals(1, dx, 1e-6);
        assertEquals(1, dy, 1e-6);
    }
    
    @Test
    public void testReturnVelocity2()
    {
        Radar testRadar = new Radar(60, 90);
        testRadar.setMonsterLocation(1, 2);
        testRadar.setMonsterVelocity(2, 3);
        
        while (testRadar.scan() == true)
        {
            testRadar.scan();
        }
        
        int[] velocity = testRadar.returnVelocity();
        int dx = velocity[0];
        int dy = velocity[1];
        assertEquals(2, dx, 1e-6);
        assertEquals(3, dy, 1e-6);
    }
    
    @Test
    public void testReturnVelocity3()
    {
        Radar testRadar = new Radar(140, 100);
        testRadar.setMonsterLocation(120, 95);
        testRadar.setMonsterVelocity(-3, 1);
        
        while (testRadar.scan() == true)
        {
            testRadar.scan();
        }
        
        int[] velocity = testRadar.returnVelocity();
        int dx = velocity[0];
        int dy = velocity[1];
        assertEquals(-3, dx, 1e-6);
        assertEquals(1, dy, 1e-6);
    }
}

