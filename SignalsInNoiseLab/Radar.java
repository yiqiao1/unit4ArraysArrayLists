import java.util.Scanner;

/**
 * The model for radar scan and accumulator
 * 
 * @author @gcschmit
 * @version 19 July 2014
 */
public class Radar
{
    
    // stores whether each cell triggered detection for the current scan of the radar
    private boolean[][] currentScan;
    private boolean[][] previousScan;
    
    // value of each cell is incremented for each scan in which that cell triggers detection 
    private int[][] accumulator;
    
    // current location of the monster
    private int currentLocationRow;
    private int currentLocationCol;

    // velocity of the monster
    private int monsterDX;
    private int monsterDY;

    // probability that a cell will trigger a false detection (must be >= 0 and < 1)
    private double noiseFraction;
    
    // number of scans of the radar since construction
    private int numScans;

    /**
     * Constructor for objects of class Radar
     * 
     * @param   rows    the number of rows in the radar grid
     * @param   cols    the number of columns in the radar grid
     */
    public Radar(int rows, int cols)
    {
        // initialize instance variables
        currentScan = new boolean[rows][cols]; // elements will be set to false
        previousScan = new boolean[rows][cols];
        
        accumulator = new int[11][11]; // elements will be set to 0
        
        // randomly set the location of the monster (can be explicity set through the
        //  setMonsterLocation method
        currentLocationRow = (int)(Math.random() * rows);
        currentLocationCol = (int)(Math.random() * cols);
        
        noiseFraction = 0.05;
        numScans= 0;
    }
    
    /**
     * Performs a scan of the radar. Noise is injected into the grid and the accumulator is updated.
     * 
     */
    public boolean scan()
    {
        // copies previousScan into new array currentScan
        for (int row = 0; row < currentScan.length; row++)
        {
            for (int col = 0; col < currentScan[0].length; col++)
            {
                previousScan[row][col] = currentScan[row][col];
            }
        }
        
        // zero the current scan grid
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                currentScan[row][col] = false;
            }
        }
                
        // moves monster according to dy and dx
        if (currentLocationRow < (currentScan.length - monsterDX) && currentLocationCol < (currentScan[0].length - monsterDY))
        {
            moveMonster();
        }
        else
        {
            return false;
        }
        
        // inject noise into the grid
        injectNoise();
        
        // for every location in the previousScan grid...
        for (int row1 = 0; row1 < previousScan.length; row1++)
        {
            for (int col1 = 0; col1 < previousScan[0].length; col1++)
            {
                // if there is a possible monster at that location...
                if (previousScan[row1][col1] == true)
                {
                    // then for every location in the currentScan grid...
                    for(int row2 = 0; row2 < currentScan.length; row2++)
                    {
                        for(int col2 = 0; col2 < currentScan[0].length; col2++)
                        {
                            // if the distance between the locations on the two different grids is less than or equal to 5...
                            if (Math.abs(row1 - row2) <= 5 && Math.abs(col1 - col2) <= 5)
                            {
                                // and if there is also a possible monster at that location on the currentScan grid...
                                if (currentScan[row2][col2] == true)
                                {
                                    // then add one to that velocity in th accumulator grid!
                                    accumulator[row2 - row1 + 5][col2 - col1 + 5] += 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // keep track of the total number of scans
        numScans++;
        
        return true;
    }

    /**
     * Sets the location of the monster
     * 
     * @param   row     the row in which the monster is located
     * @param   col     the column in which the monster is located
     * @pre row and col must be within the bounds of the radar grid
     */
    public void setMonsterLocation(int row, int col)
    {
        // remember the row and col of the monster's location
        currentLocationRow = row;
        currentLocationCol = col;
        
        // update the radar grid to show that something was detected at the specified location
        currentScan[row][col] = true;
    }
    
    /**
     * 
     * @param   dx  the velocity of the monster in the x direction
     * @param   dy  the velocity of the monster in the y direction
     * @pre dx and dy must be within 5 pixels per time period each
     */
    public void setMonsterVelocity(int dx, int dy)
    {
        // remember the dy and dx velocity of the monster
        monsterDX = dx;
        monsterDY = dy;
    }
    
     /**
     * Sets the probability that a given cell will generate a false detection
     * 
     * @param   fraction    the probability that a given cell will generate a flase detection expressed
     *                      as a fraction (must be >= 0 and < 1)
     */
    public void setNoiseFraction(double fraction)
    {
        noiseFraction = fraction;
    }
    
    /**
     * Returns true if the specified location in the radar grid triggered a detection.
     * 
     * @param   row     the row of the location to query for detection
     * @param   col     the column of the location to query for detection
     * @return true if the specified location in the radar grid triggered a detection
     */
    public boolean isDetected(int row, int col)
    {
        return currentScan[row][col];
    }
    
    /**
     * Returns the number of times that the specified location in the radar grid has triggered a
     *  detection since the constructor of the radar object.
     * 
     * @param   row     the row of the location to query for accumulated detections
     * @param   col     the column of the location to query for accumulated detections
     * @return the number of times that the specified location in the radar grid has
     *          triggered a detection since the constructor of the radar object
     */
    public int getAccumulatedDetection(int row, int col)
    {
        return accumulator[row][col];
    }
    
    /**
     * Returns the number of rows in the radar grid
     * 
     * @return the number of rows in the radar grid
     */
    public int getNumRows()
    {
        return currentScan.length;
    }
    
    /**
     * Returns the number of columns in the radar grid
     * 
     * @return the number of columns in the radar grid
     */
    public int getNumCols()
    {
        return currentScan[0].length;
    }
    
    /**
     * Returns the number of scans that have been performed since the radar object was constructed
     * 
     * @return the number of scans that have been performed since the radar object was constructed
     */
    public int getNumScans()
    {
        return numScans;
    }
    
    /**
     * Sets cells as falsely triggering detection based on the specified probability
     * 
     */
    private void injectNoise()
    {
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                // each cell has the specified probablily of being a false positive
                if(Math.random() < noiseFraction)
                {
                    currentScan[row][col] = true;
                }
            }
        }
    }
    
    /**
     * Updates new position of monster according to its dy and dx velocity
     * 
     */
    private void moveMonster()
    {
        currentLocationRow += monsterDX;
        currentLocationCol += monsterDY;
        
        currentScan[currentLocationRow][currentLocationCol] = true;
    }    
    
    /**
     * Returns velocity of monster
     * 
     * @return  dx and dy velocity of monster
     */
    public String returnVelocity()
    {
        int largestTally = 0;
        int dy = 0;
        int dx = 0;
        
        for (int row = 0; row < accumulator.length; row++)
        {
            for (int col = 0; col < accumulator[0].length; col++)
            {
                if (accumulator[row][col] > largestTally)
                {
                    largestTally = accumulator[row][col];
                    dy = row - 5;
                    dx = col - 5;
                }
            }
        }
        
        String statement = "The dy of the monster is " + dy + " and the dx of the monster is " + dx + ".";
        return statement;
    }
    
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        
        int numRows;
        int numCols;
        int monRow;
        int monCol;
        int monDX;
        int monDY;
        
        System.out.print("Please enter the number of rows in the grid: ");
        numRows = in.nextInt();
        System.out.print("Please enter the number of columns in the grid: ");
        numCols = in.nextInt();
        
        System.out.print("Please enter the row the monster is in: ");
        monRow = in.nextInt();
        System.out.print("Please enter the column the monster is in: ");
        monCol = in.nextInt();
        
        System.out.print("Please enter the monster's velocity in the X direction: ");
        monDX = in.nextInt();
        System.out.print("Please enter the monster's velocity in the Y direction: ");
        monDY = in.nextInt();
        
        Radar radar = new Radar(numRows, numCols);
        radar.setMonsterLocation(monRow, monCol);
        radar.setMonsterVelocity(monDX, monDY);
        
        while (radar.scan() == true)
        {
            radar.scan();
        }
        
        System.out.println(radar.returnVelocity());
    }
}
