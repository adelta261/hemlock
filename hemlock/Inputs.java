package hemlock;

import java.util.Random;
import java.util.Scanner;

public class Inputs
{

	static Scanner in = new Scanner(System.in);

	// ================================================================================
	// Main Menu, called before game loop, but can be called any time
	// ================================================================================

	public static void mainMenu(boolean playerCreated)
	{
		Dialogue.bannerText();
		function:
		while (true)
		{
			Dialogue.mainMenuText();
			
			String input = in.nextLine().trim().toLowerCase();
			switch (input)
			{
			case "new":
				if (!playerCreated)
				{
					Game.characterCreation(playerCreated);
				}
				break function;
			case "load":
				// TODO: LOADING
				break function;
			case "quit":
				System.exit(0);
				break function;
			case "credits":
				Dialogue.credits();
				break;
			case "continue":
				break function;
			case "debug":
				CharacterCreation.debugStart();
				playerCreated = true;
				break function;}
		}	
	}
	
	

	public static void menuInputsDead()
	{
		String input = in.nextLine().toLowerCase();
		if (input.length() >= 1)
		{
			switch (input)
			{
			case "help":
				Dialogue.help();
				break;
			case "menu":
				mainMenu(true);
				break;
			case "people":
				Functions.printCohort();
				break;
			// bring up player stats
			case "player":
				Dialogue.playerOverview();
				break;
			// search for girl
			case "lookup":
				TextDemo.print("Who would you like to look up?");
				input = in.nextLine();
				Functions.girlLookup(input);
				break;
			// bring up green
			case "green":
				Functions.printGreen();
				break;
			// bring up preds
			case "predators":
				Functions.printPredators();
				break;
			// time/date/week
			case "time":
				break;
			// start scene
			case "b":
			case "begin":
				TextDemo.print("You cannot play anymore because you are dead! Sorry!");
				break;
			}
		}
	}

	public static void menuInputs()
	{
		String input = in.nextLine().toLowerCase();
		if (input.length() >= 1)
		{
			switch (input)
			{
			case "help":
				Dialogue.help();
				break;
			case "menu":
				mainMenu(true);
				break;
			case "people":
				Functions.printCohort();
				break;
			// bring up player stats
			case "player":
				Dialogue.playerOverview();
				break;
			// search for girl
			case "lookup":
				TextDemo.print("Who would you like to look up?");
				input = in.nextLine();
				Functions.girlLookup(input);
				break;
			// bring up green
			case "green":
				Functions.printGreen();
				break;
			// bring up preds
			case "predators":
				Functions.printPredators();
				break;
			// time/date/week
			case "time":
				break;
			// start scene
			case "b":
			case "begin":
				if (Game.world.getScenePlayer().getGirlAlive() == false)
				{
					TextDemo.print("You cannot play anymore because you are dead! Sorry!");
				}
				else if (Game.world.getScenePlayer().getGirlAlive() == true)
				{
					Dialogue.divider();
					Dialogue.daysPassed();
					Game.world.finishDay();
				}
				break;
			}
		}
	}

	public static void sceneInputs()
	{
		Random rand = new Random();
		String input = in.nextLine().toLowerCase();

		if (input.length() >= 1)
		{
			switch (input)
			{
			// bring up girl list
			case "people":
				Functions.printCohort();
				break;
			// bring up player stats
			case "menu":
				TextDemo.print("You cannot access the main menu while in a scene.");
				break;
			case "player":
				Dialogue.playerOverview();
				break;
			// bring up green
			case "green":
				Functions.printGreen();
				break;
			// bring up preds
			case "predators":
				Functions.printPredators();
				break;
			// time/date/week
			case "time":
				break;
			// actions
			case "look":
				TextDemo.print("You take a look around.");
				Functions.listSceneGirls();
				break;
			case "wait":
				TextDemo.print("You wait for an opportunity.");
				Functions.manipulateWindowTransition(1);
				break;
			case "stop":
				Functions.endScene();
				break;

			}
			multipleArgumentHandler(input);
		}
		return;
	}

	public static void multipleArgumentHandler(String input)
	{
		if (input.startsWith("lookup")) {
			Functions.girlLookup(chopperTwoArgs(input, "lookup"));
			
		} else if (input.startsWith("eat")) {
			Functions.beginEatingGirl(chopperTwoArgs(input, "eat"));
			
		} else if (input.startsWith("move to")) {
			Functions.moveLocation(chopperTwoArgs(input, "move to"));
		}
	}

	public static String chopperTwoArgs(String in, String remove)
	{
		return in.substring(remove.length()).trim();
	}

	public static int eatingInputs()
	{
		// TextDemo.printf("Keep eating (eat) or let her go (stop)? ");
		String input = in.nextLine().toLowerCase();
		if (input.length() >= 1)
		{
			switch (input)
			{
			case "e":
			case "eat":
				return 1;
			case "s":
			case "stop":
				return 2;
			}
		}
		return 0;
	}
}
