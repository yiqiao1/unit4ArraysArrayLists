public class ArrayAlgorithms
{
    private int[] values;
    
    public ArrayAlgorithms()
    {
        this.values = new int[10];
    }
    
    public String toString()
    {
        String str = "[";
        
        for (int i = 0; i < values.length; i++)
        {
            if (i > 0)
            {
                str += ", ";
            }
            
            str += this.values[i];
        }
        
        str += " ]";
        return str;
    }
    
    public void fillWithSquares()
    {
        for (int i = 0; i < values.length; i++)
        {
            this.values[i] = i*i;
        }
    }
    
    public double getAverage()
    {
        double sum = 0;
        
        for (int val : this.values)
        {
            sum += val;
        }
        
        double average = sum / this.values.length;
        return average;
    }
    
    public int getIndexOfMaximum()
    {
        int maximum = this.values[0];
        int indexOfMaximum = 0;
        
        for (int i = 0; i < this.values.length; i++)
        {
            if (values[i] > maximum)
            {
                maximum = values[i];
                indexOfMaximum = i;
            }
        }
        
        return indexOfMaximum;
    }
}