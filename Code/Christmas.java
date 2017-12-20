/*
Chad Scies, Luke Bowman, Daniel Stebbins
This project is our own work, C.S., L.B., D.S.
This is the class to hold values for the kids file.
*/
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
public class Christmas
{
	public static void main(String[] args) throws FileNotFoundException
	{
		//Daniel - ArrayLists and FileIO.
		//Raw gift file input.
		ArrayList<Gift> gifts = new ArrayList<Gift>();

		//Copy of the gift list to be narrowed down for each kid.
		ArrayList<Gift> currentGifts = new ArrayList<Gift>();

		//Final gifts (Parallel with the kids array).
		ArrayList<Gift> finalGifts = new ArrayList<Gift>();

		//Raw kids file input.
		ArrayList<Kids> kids = new ArrayList<Kids>();

		//Declaraing scanner
		Scanner reader = new Scanner(System.in);

		//Chad - User input
		int daysTillChrist;
		int counter = 0;
		double budget = 0.00;

		System.out.println("How many days till Christmas?");
 		daysTillChrist = reader.nextInt();

 		System.out.println("What's your budget?");
		budget = reader.nextDouble();



		//Daniel - Put things into methods
		giftReader(gifts);
		System.out.println(gifts);

		kidReader(kids);
		System.out.println(kids);

		double amountPerKid = budget/kids.size();

		removeKids(kids);
		System.out.println(kids);

		System.out.println(amountPerKid);
		if(amountPerKid < 4.99 || daysTillChrist < 2)
		{
			System.out.println("NO GIFTS!");
		}
		else
		{
			removeGifts(kids, gifts, budget, daysTillChrist, amountPerKid);
			System.out.println(gifts);
		}
 	}

	//Daniel - reading in gifts (Takes gift ArrayList and returns it full).
 	public static void giftReader(ArrayList<Gift> g) throws FileNotFoundException
 	{
		Scanner fileReader1 = new Scanner(new File("Gifts.txt"));
		while(fileReader1.hasNextLine())
		{
			//Super dumb way of circumvent the .nextInt line problem.
			g.add(new Gift(fileReader1.nextLine(),
							    Integer.parseInt(fileReader1.nextLine()),
								Integer.parseInt(fileReader1.nextLine()),
								Double.parseDouble(fileReader1.nextLine()),
								Integer.parseInt(fileReader1.nextLine())));
		}
	}

	//Daniel - reading in kids (Takes kids ArrayList and returns it full).
 	public static void kidReader(ArrayList<Kids> k) throws FileNotFoundException
	{
		Scanner fileReader2 = new Scanner(new File("Kids.txt"));
		while(fileReader2.hasNextLine())
		{
			String current = fileReader2.nextLine();

			int firstComma = current.indexOf(",");
			String name = current.substring(0, firstComma);

			int secondComma = current.lastIndexOf(",");
			String niceness = current.substring(firstComma + 2, secondComma);

			boolean nice = false;
			if(niceness.equals("nice"))
				nice = true;
			else
				nice = false;

			int age = Integer.parseInt(current.substring(secondComma + 2));

			k.add(new Kids(name, nice, age));
			//System.out.println(kids.get(counter));
			//counter++;

		}
	}

	//Chad and Luke - remove kids based on age and behavior
	public static void removeKids(ArrayList<Kids> k)
	{
		for(int x = 0; x < k.size(); x++)
		{
			if (k.get(x).getAge() >= 15 && k.get(x).getBehavior() == false)
				k.remove(x);
		}
	}

	//Chad and Luke - removes gifts based on price and days to build
	public static void removeGifts(ArrayList<Kids> k, ArrayList<Gift> g, double b, int d, double a)
	{
		double range = a * .2;
		double amountPerKidLow = a - range;
		int loop = 0;

		for(int i = 0; i < g.size(); i++)
		{
			System.out.println(g.get(i).getPrice());
			if (g.get(i).getPrice() > a)
			{
				System.out.println("Removed " + g.get(i).getName());
				g.remove(i);
			}
			else if(g.get(i).getPrice() < amountPerKidLow)
			{
				System.out.println("Removed " + g.get(i).getName());
				g.remove(i);
			}
			else if(g.get(i).getDays() < d)
			{
				System.out.println("Removed " + g.get(i).getName());
				g.remove(i);
			}
		}
	}

	//Daniel - narrows down gifts for each kid based on age range.
	public static Gift kidGifts(ArrayList<Kids> k, ArrayList<Gift> g)
	{
		for(Kids a: k)
		{
			ArrayList<Gift> currentGifts = g;

			System.out.println(a);
			int age = a.getAge();

			for(Gift b: currentGifts)
			{
				if(age > b.getHighAge() || age < b.getLowAge())
				{
					currentGifts.remove(b);
				}
			}

			System.out.println(currentGifts);
		}
    	Gift gift = new Gift("Placeholder", 1, 2, 1.99, 1);
    	return gift;
	}

	//Chad - Picks a random gift from the list of gifts that can be given to one kid.
	public static Gift randGift(ArrayList<Kids> k, ArrayList<Gift> cG)
	{
		Random rand = new Random();
		int number = rand.nextInt(cG.size());
		return cG.get(number);
	}
}