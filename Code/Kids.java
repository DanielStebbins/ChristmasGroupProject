public class Kids
{
	String name = "";
	boolean behavior;
	int age = 0;

	public Kids(String n, boolean b, int a)
	{
		name = n;
		behavior = b;
		age = a;
	}

	public String getName()
	{
		return name;
	}

	public boolean getBehavior()
	{
		return behavior;
	}

	public int getAge()
	{
		return age;
	}

	public String toString()
	{
		String out = "\nName: " + name;
		out += "\tNice? " + behavior;
		out += "\tAge: " + age;

		return out;
	}
}