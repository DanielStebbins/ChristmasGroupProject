public class Gift
{
	int lowAge, highAge, inStock;
	double price;
	String name;

	public Gift(int a1, int a2, int s, double p, String n)
	{
		lowAge = a1;
		highAge = a2;
		inStock = s;
		price = p;
		name = n;
	}

	public int getLowAge()
	{
		return lowAge;
	}

	public int getHighAge()
	{
		return highAge;
	}

	public int getInStock()
	{
		return inStock;
	}

	public void setInStock(int s)
	{
		inStock = s;
	}

	public double getPrice()
	{
		return price;
	}

	public String getName()
	{
		return name;
	}
}