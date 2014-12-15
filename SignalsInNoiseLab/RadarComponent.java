import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import javax.swing.JComponent;

/**
 * Class that is responsible for rendering the current radar scan image and the accumulated radar image.
 * 
 * @author @gcschmit
 * @version 19 July 2014
 */
public class RadarComponent extends JComponent
{
    static final int CELL_WIDTH = 5;
    static final int CELL_HEIGHT = 5;

    Radar radar;
    
    /**
     * Constructor
     *
     * @param    radar    the radar object containing the data from which to generate the images
     * @pre the specified radar object must be non-null
     * 
     */
    public RadarComponent(Radar radar)
    {
        this.radar = radar;
    }
    
    /**
     * Overrides JComponent's getPreferredSize method to return a size large enough to encapsulate
     *  two radar grids side by side
     *
     * @returns the preferred size for the component
     * 
     */
    public Dimension getPreferredSize()
    {
        // the component needs to be large enough to encapsulate two grids side by side
        final int WIDTH = radar.getNumCols() * CELL_WIDTH * 2;
        final int HEIGHT = radar.getNumRows() * CELL_HEIGHT;
        return new Dimension(WIDTH, HEIGHT);
    }
    
    /**
     * This method is invoked by the Java Run-Time whenever the component needs to be redrawn.
     * It does not need to be invoked explicitly. It draws both the current radar scan and the
     * accumulated radar image where the brightness of the cell is proportional to the number of
     * times that it has been detected in a scan.
     *
     * @param    g    the graphics object to be used for all graphics operations
     * 
     */
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        // cool radar-looking colors
        final Color DETECTED_COLOR = new Color(184, 254, 183);
        final Color UNDETECTED_COLOR = new Color(6, 63, 3);
        
        // draw the image for the current scan of the radar
        int rows = radar.getNumRows();
        int cols = radar.getNumCols();
        
        for(int row = 0; row < rows; row++)
        {
            for(int col = 0; col < cols; col++)
            {
                int x = col * CELL_WIDTH ;
                int y = row * CELL_HEIGHT ;
                
                Rectangle2D.Double rect = new Rectangle2D.Double(x, y, CELL_WIDTH , CELL_HEIGHT );
                
                if(radar.isDetected(row, col))
                {
                    g2.setColor(DETECTED_COLOR);
                }
                else
                {
                    g2.setColor(UNDETECTED_COLOR);
                }
                
                g2.fill(rect);
            }
        }
        
        // draw the image for the accumulated radar data to the right of the current scan image
        final int X_OFFSET = cols * CELL_WIDTH;
        
        // draw the image for the accumulated radar scans
        for(int row = 0; row < 11; row++)
        {
            for(int col = 0; col < 11; col++)
            {
                int x = X_OFFSET + col * CELL_WIDTH ;
                int y = row * CELL_HEIGHT ;
                
                Rectangle2D.Double rect = new Rectangle2D.Double(x, y, CELL_WIDTH, CELL_HEIGHT );
                
                float pixelValue = (float)(radar.getAccumulatedDetection(row, col)) / radar.getNumScans();

                // due to floating-point rounding issues, pixelValue can end up slightly greater than 1.0
                //  we'll cap it since the Color constructor requires values <= 1.0
                pixelValue = Math.min(pixelValue, 1.0f);
                
                g2.setColor(new Color(pixelValue, pixelValue, pixelValue));
                g2.fill(rect);
            }
        }
    }
}
