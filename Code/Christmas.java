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
	public static final String FILENAME = "List.txt";
	
	public static void main(String[] args) throws FileNotFoundException
	{
		//Daniel - ArrayLists and FileIO.
		//Raw gift file input.
		ArrayList<Gift> gifts = new ArrayList<Gift>();

		//Final gifts (Parallel with the kids array).
		ArrayList<Gift> finalGifts = new ArrayList<Gift>();

		//Raw kids file input.
		ArrayList<Kids> kids = new ArrayList<Kids>();

		//Declaring scanner
		Scanner reader = new Scanner(System.in);

		//Chad - User input
		int daysTillChrist;
		double budget = 0.00;

		System.out.println("How many days till Christmas?");
 		daysTillChrist = reader.nextInt();

 		System.out.println("What's your budget?");
		budget = reader.nextDouble();

		//Daniel - Put things into methods
		giftReader(gifts);
		//System.out.println(gifts);

		kidReader(kids);
		//System.out.println(kids);

		removeKids(kids);
		//System.out.println(kids);

		//System.out.println(kids.size());
		double amountPerKid = budget/kids.size();

		System.out.println(amountPerKid);
		if(amountPerKid < 4.99 || daysTillChrist < 3)
		{
			System.out.println("NO GIFTS!");
		}
		else
		{
			removeGifts(kids, gifts, budget, daysTillChrist, amountPerKid);
			System.out.println(gifts);

			kidGifts(kids, gifts, finalGifts, budget);
			for(int i = 0; i < kids.size(); i++)
			{
				System.out.println(kids.get(i).getName() + " gets " + finalGifts.get(i).getName());
			}
			checkPrice(finalGifts);
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(List)))
		{
			for(int i = 0; i < kids.size(); i++)
			{
				String content = kids.get(i).getName() + " gets " + finalGifts.get(i).getName();
				bw.write(content);
			}
			
			System.out.println("Done");
		}
			
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
		}
	}

	//Chad and Luke - remove kids based on age and behavior
	public static void removeKids(ArrayList<Kids> k)
	{
		ArrayList<Kids> toRemove = new ArrayList<Kids>();
		for(Kids x: k)
		{
			if (x.getAge() >= 15 && x.getBehavior() == false)
				toRemove.add(x);
		}
		k.removeAll(toRemove);
	}

	//Chad and Luke - removes gifts based on price and days to build
	//a = amountPerKid d = daysTillChrist b = budget
	public static void removeGifts(ArrayList<Kids> k, ArrayList<Gift> g, double b, int d, double a)
	{
		double range = 0, amountPerKidLow = 0;
		ArrayList<Gift> toRemove = new ArrayList<Gift>();
		toRemove.addAll(g);

		range = a * .3;
		amountPerKidLow = a - range;
		
		while(toRemove.size() >= g.size() - 2)
		{
			System.out.println("Amount: " + a + "\t" + "Lowest: " + amountPerKidLow);
			toRemove.clear();
			for(Gift gift: g)
			{
				if (gift.getPrice() > a || gift.getPrice() < amountPerKidLow || gift.getDays() > d)
				{
					System.out.println("Removed " + gift.getName() + " Price of " + gift.getPrice());
					toRemove.add(gift);
				}
				else
				{
					System.out.println("Keep " + gift.getName() + " " + gift.getPrice());
				}
			}
			amountPerKidLow -= 10;
			a += range;
		}
		
		g.removeAll(toRemove);
	}

	//Daniel - narrows down gifts for each kid based on age range.
	public static void kidGifts(ArrayList<Kids> k, ArrayList<Gift> g, ArrayList<Gift> fG, double b)
	{
		int left = k.size();
		for(Kids a: k)
		{
			//Copy of the gift list to be narrowed down for each kid.
			ArrayList<Gift> currentGifts = new ArrayList<Gift>();
			currentGifts.addAll(g);
			ArrayList<Gift> toRemove = new ArrayList<Gift>();

			System.out.println(a);
			int age = a.getAge();

			for(Gift c: currentGifts)
			{
				if(age > c.getHighAge() || age < c.getLowAge())
				{
					toRemove.add(c);
				}
			}
			currentGifts.removeAll(toRemove);
			System.out.println(currentGifts);
			Gift temp = randGift(currentGifts, b, left);
			fG.add(temp);
			b -= temp.getPrice();
			left--;
			System.out.println("There are " + left + "kids left on the list and " + b + " dollars in the budget.");
		}
	}

	//Chad - Picks a random gift from the list of gifts that can be given to one kid.
	public static Gift randGift(ArrayList<Gift> cG, double cB, int l)
	{
		double max = 0, min = 10000;
		int maxIndex = 0, minIndex = 0;
		for(int i = 0; i < cG.size(); i++)
		{
			if(cG.get(i).getPrice() > max)
			{
				max = cG.get(i).getPrice();
				maxIndex = i;
			}
			if(cG.get(i).getPrice() < min)
			{
				min = cG.get(i).getPrice();
				minIndex = i;
			}
		}
		
		System.out.println("The max for this group is: " + cG.get(maxIndex).getName());
		System.out.println("The min for this group is: " + cG.get(minIndex).getName());
		System.out.println("The average is: " + cB / l);
		if(max  * .98 < cB / l)
		{
			System.out.println("Defulted to: " + cG.get(maxIndex).getName());
			return cG.get(maxIndex);
		}
		else if(min * 1.4 > cB / l)
		{
			System.out.println("Defulted to: " + cG.get(minIndex).getName());
			return cG.get(minIndex);
		}
		else 
		{
			System.out.println();
			Random rand = new Random();
			int number = rand.nextInt(cG.size());
			return cG.get(number);
		}
	}
	
	public static void checkPrice(ArrayList<Gift> fG)
	{
		double total = 0;
		
		for(Gift g: fG)
		{
			total += g.getPrice();
		}
		System.out.println("I hope " + total + " is within your budget!");
	}
	
	
}
