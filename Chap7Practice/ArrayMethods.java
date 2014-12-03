public class ArrayMethods
{
    private int[] values;

    /**
     * Default constructor for objects of class ArrayMethods
     */
    public ArrayMethods(int[] initialValues)
    {
        values = initialValues;
    }

    public void swapFirstAndLast()
    {
        int first = values[0];
        values[0] = values[values.length - 1];
        values[values.length - 1] = first;
    }

    public void shiftRight()
    {      
        int last = values[values.length - 1];
        
        for (int i = values.length - 1; i > 0; i--)
        {
            values[i] = values[i - 1];
        }
        
        values[0] = last;
    }
    
    public void evenToZero()
    {
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] % 2 == 0)
            {
                values[i] = 0;
            }
        }
    }
    
    public void replaceWithLargerNeighbor()
    {
        int[] newValues = new int[values.length];
        newValues[0] = values[0];
        newValues[newValues.length - 1] = values[values.length - 1];
        
        for (int i = 1; i < values.length - 1; i++)
        {
            if (values[i - 1] > values[i + 1])
            {
                newValues[i] = values[i - 1];
            }
            else if (values[i + 1] > values[i - 1])
            {
                newValues[i] = values[i + 1];
            }
            else
            {
                newValues[i] = values[i - 1];
            }
        }
        
        values = newValues;
    }
    
    public void replaceWithLargerNeighbor2()
    {
        int prevValue = values[0];
        
        for (int i = 1; i < values.length - 1; i++)
        {
            int temp = values[i];
            
            if (prevValue > values[i + 1])
            {
                values[i] = prevValue;
            }
            else
            {
                values[i] = values[i + 1];
            }
            
            prevValue = temp;
        }
    }
    
    public void removeMiddle()
    {
        int[] newValues = new int[values.length];
        
        if (values.length % 2 != 0)
        {
            for (int i = 0; i < values.length; i++)
            {
                if (i == (values.length - 1)/2)
                {
                    i++;
                }
                
                newValues[i] = values[i];
            }
        }
        else
        {
            for (int i = 0; i < values.length; i++)
            {
                while (i == (values.length/2) || i == ((values.length/2) - 1))
                {
                    i++;
                }
                
                newValues[i] = values[i];
            }
        }
        
        values = newValues;
    }
    
    public void evenToFront()
    {
        int[] newValues = new int[values.length];
        int numOfEven = 0;
        int countBackward = values.length - 1;
        
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] % 2 == 0)
            {
                newValues[numOfEven] = values[i];
                numOfEven++;
            }
        }
        
        for (int i = values.length - 1; i >= 0; i--)
        {
            if (values[i] % 2 != 0)
            {
                newValues[countBackward] = values[i];
                countBackward--;
            }
        }
    }
}
