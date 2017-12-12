Public class Kids
{
	String name = "";
	String behavior = "";
	int age = 0;

	public Kids(String n, String b, int a)
	{
		name = n;
		behavior = b;
		age = a;
	}

	public String GetName()
	{
		return name;
	}

	public String GetBehavior()
	{
		return behavior;
	}

	public int Age()
	{
		return age;
	}
}
