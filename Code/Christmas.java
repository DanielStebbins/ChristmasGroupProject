import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
public class Christmas
{
	public static void main(String[] args) throws FileNotFoundException
	{
		ArrayList<Gift> gifts = new ArrayList<Gift>();
		ArrayList<Gift> currentGifts = new ArrayList<Gift>();
		ArrayList<Kids> kids = new ArrayList<Kids>();

		Scanner reader = new Scanner(System.in);
		Scanner fileReader1 = new Scanner(new File("Gifts.txt"));
		Scanner fileReader2 = new Scanner(new File("Kids.txt"));
		int daysTillChrist;
		int counter = 0;
		double budget = 0.00;

		System.out.println("How many days till Christmas?");
 		daysTillChrist = reader.nextInt();

 		System.out.println("What's your budget?");
		budget = reader.nextDouble();

		gifts = giftReader(gifts, fileReader1);
		kids = kidReader(kids, fileReader2);
		kids = removeKids(kids);
		gifts = removeGifts(kids, gifts, budget, daysTillChrist);
    
    double range = 0.0;
		double amountPerKid = 0.00;
		range = amountPerKid / .2;
		
		double amountPerKidLow = amountPerKid - range;
		int i = 0;
		while(i != kids.size)
		{
			int number;
			Random rand = new Random();
      	number = rand.nextInt(currentGifts.size);
			i++;
		}
 	}

 	public static ArrayList<Gift> giftReader(ArrayList<Gift> g, Scanner r)
 	{
		while(r.hasNextLine())
		{
			g.add(new Gift(r.nextLine(),
							    Integer.parseInt(r.nextLine()),
								Integer.parseInt(r.nextLine()),
								Double.parseDouble(r.nextLine()),
								Integer.parseInt(r.nextLine())));
			//System.out.println(gifts.get(counter));
			//counter++;
		}

		return g;
	}

 	public static ArrayList<Kids> kidReader(ArrayList<Kids> k, Scanner r)
	{
		while(r.hasNextLine())
		{
			String current = r.nextLine();

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
		return k;
	}

	public static ArrayList<Kids> removeKids(ArrayList<Kids> k)
	{
		for(int x = 0; x <= k.size(); x++)
		{
			if (k.get(x).getAge() >= 15 && k.get(x).getBehavior() == false)
				k.remove(x);
		}

		return k;
	}

	public static ArrayList<Gift> removeGifts(ArrayList<Kids> k, ArrayList<Gift> g, double b, int d)
	{
		double amountPerKid = 0.00;
		amountPerKid = b/k.size();

		for(int x = 0; x <= g.size(); x++)
		{
			if (g.get(x).getPrice() > amountPerKid)
				g.remove(x);

			if (g.get(x).getDays() > d)
				g.remove(x);
		}

		return g;
	}

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
    return gift;
	}
}

