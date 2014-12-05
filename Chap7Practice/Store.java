import java.util.Scanner;

public class Store
{
    private ArrayList<String> customers = new ArrayList<String>();
    private ArrayList<Double> sales = new ArrayList<Double>();
    
    private String name;
    private double price;

    public Store(ArrayList<String> customers, ArrayList<Double> sales)
    {
        
    }

    public void addSale(String customerName, double amount)
    {
        this.customers.add(customerName);
        this.sales.add(amount);
    }

    public String nameOfBestCustomer()
    {
        int bestCustomer = 0;
        int counter = 0;
        
        for (int i = 0; i < sales.size(); i++)
        {
            if (sales[i] > counter)
            {
                counter = sales[i];
                bestCustomer = i;
            }
        }
        
        return customers[bestCustomer];
    }
    
    public static void main(String[] args)
    {
        Store store = new Store(
    }
}
