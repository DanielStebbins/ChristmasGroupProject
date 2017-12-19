/*
Chad Scies, Luke Bowman, Daniel Stebbins
This project is our own work, C.S., L.B., D.S.
This is the class to hold values for the gift file.
This class was made by Daniel only.
*/
public class Gift
{
	int lowAge, highAge, days;
	double price;
	String name;

	public Gift(String n, int a1, int a2, double p, int d)
	{
		name = n;
		lowAge = a1;
		highAge = a2;
		price = p;
		days = d;
	}

	public int getLowAge()
	{
		return lowAge;
	}

	public int getHighAge()
	{
		return highAge;
	}

	public int getDays()
	{
		return days;
	}

	public double getPrice()
	{
		return price;
	}

	public String getName()
	{
		return name;
	}

	public String toString()
	{
		String out = "Name: " + name;
		out += "\nLow Age: " + lowAge;
		out += "\nHigh Age: " + highAge;
		out += "\nPrice: " + price;
		out += "\nDays: " + days;

		return out;
	}
}