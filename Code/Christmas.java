/*
Chad Scies, Luke Bowman, Daniel Stebbins
This project is our own work, C.S., L.B., D.S.
This is the main of the project that calculates which kid gets which present based on file reading. It outputs the finished list to the file "List".
Things that went right: It got done! All members contributed.
Things that went wrong: Connecting parts from different members could have been smoother and required extra work.
*/
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Christmas
{	
	public static void main(String[] args) throws IOException
	{
		//Chad - File output
		final String FILENAME = "List.txt";
		
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
		
		//Calling methods for file input and removing older naughty kids.
		giftReader(gifts);
		kidReader(kids);
		removeKids(kids);

		double amountPerKid = budget/kids.size();

		//Checking to see if the values are low enough to crash the program
		if(amountPerKid < 4.99 || daysTillChrist < 3)
		{
			System.out.println("NO GIFTS!");
		}
		else
		{
			//Method to remove gifts that are too expensive or time consuming.
			removeGifts(kids, gifts, budget, daysTillChrist, amountPerKid);
			
			//Method that matches each kid with a narrowed down arraylist of possible gifts.
			kidGifts(kids, gifts, finalGifts, budget);
			
			//Method to print final list to a file.
			fileOutput(budget, FILENAME, kids, finalGifts);
		}
	}
			
	//Daniel - reading in gifts (Takes gift ArrayList and returns it full).
 	public static void giftReader(ArrayList<Gift> g) throws FileNotFoundException
 	{
		Scanner fileReader1 = new Scanner(new File("Gifts.txt"));
		while(fileReader1.hasNextLine())
		{
			//Super dumb way of circumvent the .nextInt line problem. Reads in name, low age, high age, price, and day amount and creates a gift object.
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
			//One line from the file to be worked on.
			String current = fileReader2.nextLine();

			//Finding name using the substring left of the first comma.
			int firstComma = current.indexOf(",");
			String name = current.substring(0, firstComma);

			//Niceness found using substring first comma to second comma.
			int secondComma = current.lastIndexOf(",");
			String niceness = current.substring(firstComma + 2, secondComma);

			//Turning string niceness into a boolean value.
			boolean nice = false;
			if(niceness.equals("nice"))
				nice = true;
			else
				nice = false;

			//Finding age using substring left of the second comma.
			int age = Integer.parseInt(current.substring(secondComma + 2));

			//Creating a new Kids object.
			k.add(new Kids(name, nice, age));
		}
	}

	//Chad and Luke - remove kids based on age and behavior
	public static void removeKids(ArrayList<Kids> k)
	{
		//Avoiding concurrent modification exception with a separate ArrayList as a marker.
		ArrayList<Kids> toRemove = new ArrayList<Kids>();
		
		//Looping through every kid.
		for(Kids x: k)
		{
			//Marking kids who are too old and naughty.
			if (x.getAge() >= 15 && x.getBehavior() == false)
				toRemove.add(x);
		}
		
		//Removing the marked ones
		k.removeAll(toRemove);
	}

	//Chad and Luke - removes gifts based on price and days to build
	//a = amountPerKid d = daysTillChrist b = budget
	public static void removeGifts(ArrayList<Kids> k, ArrayList<Gift> g, double b, int d, double a)
	{
		//Declarations
		double range = 0, amountPerKidLow = 0;
		ArrayList<Gift> toRemove = new ArrayList<Gift>();
		toRemove.addAll(g);

		range = a * .3;
		amountPerKidLow = a - range;
		
		//Requires at least 3 gifts to choose from to avoid empty sets for kids.
		while(toRemove.size() >= g.size() - 2)
		{
			toRemove.clear();
			for(Gift gift: g)
			{
				//If a gift is too expensive, too far bellow the budget, or takes too many days, remove it.
				if (gift.getPrice() > a || gift.getPrice() < amountPerKidLow || gift.getDays() > d)
				{
					toRemove.add(gift);
				}
			}
			//Widens the price range with each loop to ensure there are enough gifts to choose from.
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

			int age = a.getAge();

			//Removing gifts based on age.
			for(Gift c: currentGifts)
			{
				if(age > c.getHighAge() || age < c.getLowAge())
				{
					toRemove.add(c);
				}
			}
			currentGifts.removeAll(toRemove);
			
			//Getting a gift from the arraylist and adding it as the final gift for the kid.
			Gift temp = randGift(currentGifts, b, left);
			fG.add(temp);
			
			//Calculating new budget after subtracting the cost of the gift.
			b -= temp.getPrice();
			
			//Decrementing number of kids left.
			left--;
		}
	}

	//Chad and Luke - Picks a random gift from the list of gifts that can be given to one kid - takes the currentGift list for a kid, the budget, and how many kids are left.
	public static Gift randGift(ArrayList<Gift> cG, double cB, int l)
	{
		//Calculating gift with maximum and minimum price in the list.
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
		
		//Defaulting to the most or least expensive gift to balance the budget as needed - picks a random one if not needed.
		if(max  * .98 < cB / l)
		{
			return cG.get(maxIndex);
		}
		else if(min * 1.4 > cB / l)
		{
			return cG.get(minIndex);
		}
		else 
		{
			Random rand = new Random();
			int number = rand.nextInt(cG.size());
			return cG.get(number);
		}
	}
	
	//Daniel - Returns the price of all the final gifts combined.
	public static double checkPrice(ArrayList<Gift> fG)
	{
		double total = 0;
		
		//Loops through every gift.
		for(Gift g: fG)
		{
			//Adds to a total.
			total += g.getPrice();
		}
		
		return total;
	}
	
	//Chad - Outputs to file
	public static void fileOutput(double b, String f, ArrayList<Kids> k, ArrayList<Gift> fG) throws IOException
	{
		//Declaring buffered Writer
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		//Looping through every kid and every respective gift to print them
		for(int i = 0; i < k.size(); i++)
		{
			String content = k.get(i).getName() + " gets " + fG.get(i).getName();
			bw.write(content);
			bw.flush();
			bw.newLine();
		}
			
		double underBudget = b - checkPrice(fG);
		
		//Printing budget information
		bw.newLine();
		bw.write("The total amount spent was: $" + String.format("%.2f", checkPrice(fG)) + ". The cost was: $" + String.format("%.2f", underBudget) + " below buget");
		bw.flush();
		
		//End of program announcement.
		System.out.println("List of which kid gets which gift exported to the file: \"List\"\nMerry Christmas!");
	}
}
