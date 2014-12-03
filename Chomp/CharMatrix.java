// Implements a 2-D array of characters

public class CharMatrix
{
    // Fields:
    private char[][] charGrid;
    
    // Constructor: creates a grid with dimensions rows, cols,
    // and fills it with spaces
    public CharMatrix(int rows, int cols)
    {
        char[][] strGrid = new char[rows][cols];
        
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < cols; c++)
            {
            charGrid[r][c] = ' ';
            }
        }
    }
    
    // Constructor: creates a grid with dimensions rows , cols ,
    // and fills it with the fill character
    public CharMatrix(int rows, int cols, char fill)
    {
        char[][] charGrid = new char[rows][cols];
        
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < cols; c++)
            {
            charGrid[r][c] = fill;
            }
        }
    }
    
    // Returns the number of rows in grid
    public int numRows()
    {
        return charGrid.length;
    }
    
    // Returns the number of columns in grid
    public int numCols()
    {
        return charGrid[1].length;
    }
    
    // Returns the character at row, col location
    public char charAt(int row, int col)
    {
        return charGrid[row][col];
    }
    
    // Sets the character at row, col location to ch
    public void setCharAt(int row, int col, char ch)
    {
        charGrid[row][col] = ch;
    }
    
    // Returns true if the character at row, col is a space,
    // false otherwise
    public boolean isEmpty(int row, int col)
    {
        if(charGrid[row][col] == ' ')
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    // Fills the given rectangle with fill characters.
    // row0, col0 is the upper left corner and row1, col1 is the
    // lower right corner of the rectangle.
    public void fillRect(int row0, int col0, int row1, int col1, char fill)
    {
        int length = row1-row0+1;
        int width = col1-col0+1;
        char[][] grid = new char[length][width];
        
        for( ; row0 <= row1; row0++ )
        {
            for( int col_count = col0; col_count <= col1; col_count++ )
            {
                charGrid[row0][col0] = fill;
            }
        }
    }
    
    // Fills the given rectangle with SPACE characters.
    // row0, col0 is the upper left corner and row1, col1 is the
    // lower right corner of the rectangle.
    public void clearRect(int row0, int col0, int row1, int col1)
    {
        int length = row1-row0+1;
        int width = col1-col0+1;
        char[][] grid = new char[length][width];
        
        for( ; row0 <= row1; row0++ )
        {
            for( int col_count = col0; col_count <= col1; col_count++ )
            {
                charGrid[row0][col0] = ' ';
            }
        }
    }
    
    // Returns the count of all non-space characters in row 
    public int countInRow(int row)
    {
        int count = 0;
        
        for( int i = 0; i < charGrid[row].length; i++ )
        {
            if( charGrid[row][i] != ' ' )
            {
                count += 1;
            }
        }
        
        return count;
    }
    
    // Returns the count of all non-space characters in col 
    public int countInCol(int col)
    {
        int count = 0;
        
        for( int row = 0; row < charGrid.length; row++ )
        {
            if( charGrid[row][col] != ' ' )
            {
                count += 1;
            }
        }
        
        return count;
    }
}