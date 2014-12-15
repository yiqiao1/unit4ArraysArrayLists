import javax.swing.JFrame;
import java.util.Scanner;

/**
 * Class that contains the main method for the program and creates the frame containing the component.
 * 
 * @author @gcschmit
 * @version 19 July 2014
 */
public class RadarViewer
{
    /**
     * main method for the program which creates and configures the frame for the program
     *
     */
    public static void main(String[] args) throws InterruptedException
    {
        // create the radar, set the monster location, and perform the initial scan
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
        radar.setNoiseFraction(0.01);
        radar.setMonsterLocation(monRow, monCol);
        radar.setMonsterVelocity(monDX, monDY);
        
        radar.scan();
          
        JFrame frame = new JFrame();
        
        frame.setTitle("Signals in Noise Lab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // a frame contains a single component; create the radar component and add it to the frame
        RadarComponent component = new RadarComponent(radar);
        frame.add(component);
        
        // set the size of the frame to encompass the contained component
        frame.pack();
        
        // make the frame visible which will result in the paintComponent method being invoked on the
        //  component.
        frame.setVisible(true);
        
        // perform 100 scans of the radar with a slight pause between each
        // after each scan, instruct the Java Run-Time to redraw the window
        while (radar.scan() == true)
        {
            Thread.sleep(100);
            
            radar.scan();
            
            frame.repaint();
        }
        
        System.out.print(radar.returnVelocity());
    }
}
