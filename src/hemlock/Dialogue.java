package hemlock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class Dialogue
{

	// ================================================================================
	// Main Menu
	// ================================================================================

	// contains ascii text banner and version number
	public static void bannerText()
	{
		TextDemo.print(offset(4) + "    __  __               __           __  \r\n" + offset(4)
				+ "   / / / /__  ____ ___  / /___  _____/ /__\r\n" + offset(4)
				+ "  / /_/ / _ \\/ __ `__ \\/ / __ \\/ ___/ //_/\r\n" + offset(4)
				+ " / __  /  __/ / / / / / / /_/ / /__/ ,<   \r\n" + offset(4)
				+ "/_/ /_/\\___/_/ /_/ /_/_/\\____/\\___/_/|_|  \r\n" + offset(4)
				+ "    / / / (_)___ _/ /_                     \r\n" + offset(4)
				+ "   / /_/ / / __ `/ __ \\   version 0.30     \r\n" + offset(4)
				+ "  / __  / / /_/ / / / /                    \r\n" + offset(4)
				+ " /_/ /_/_/\\__, /_/ /_/                     \r\n" + offset(4)
				+ "        /____/                    ");
	}

	// main menu text
	public static void mainMenuText()
	{
		divider();
		String o3 = offset(3);
		String o4 = offset(4);
		TextDemo.print(o3 + "Main menu: \n" + o3 + "Choose an option (please use specific text commands):");
		if (Game.world.getAllGirls() != null)
		{
			TextDemo.print(o4 + "- (c)ontinue\n\n" + o4 + "- (n)ew game\n" + o4 + "- (l)oad\n" + o4 + "- (q)uit\n\n"
					+ o4 + "- credits");
		}
		else
		{
			TextDemo.print(o4 + "- (n)ew game\n" + o4 + "- (l)oad\n" + o4 + "- (q)uit\n\n" + o4 + "- credits");
		}
	}

	// credits
	public static void credits()
	{
		String o2 = offset(2);

		TextDemo.print("\nThese are all the people that worked on this little game. \n\n" + "\tCredits:\n" + o2
				+ "adelta261 - creator/writer/programmer\n" + o2 + "toppers - writer" +
				o2 + "seifens - programmer");
	}

	// ================================================================================
	// Help page
	// ================================================================================

	public static void help()
	{
		String o2 = offset(2);
		TextDemo.print("\nWelcome to the help page!\n\n" + "\tList of commands:\n\n"
				+ "\tBetween-scene Menu Shortcuts (some of these, marked with * are NOT available during scenes):\n"
				+ o2 + "(m)enu - goes to main menu.*        \t(s)ave - save your game (limited)*\n" + o2
				+ "eat [name] - eat the character\n" + o2 
				+ "move to [location name] - move to a specified location\n" + o2 
				+ "lookup [name] - find a character, specify name  \t(l)oad - load a game*            \n" + "\n"
				+ "\tUseful commands:\n" + o2 + "girls - shows list of all girls     \tme - shows the player\n" + o2
				+ "preds - shows list of all predators \ttime - shows the in-game time\n" + o2
				+ "green - shows list of all dead girls\tdate - shows the in-game date/day");
	}

	// ================================================================================
	// Context menu texts
	// ================================================================================

	// text that displays when returned to between-scene menu
	public static void gameMenuText()
	{
		divider();
		TextDemo.print("Use the (h)elp command if you get stuck.\n" + "Type (b)egin to begin a new scene.");
	}

	// text that displays information at the end of the scene
	public static void afterActionReport()
	{
		Girl player = Game.world.getScenePlayer();
		TextDemo.print("This scene, you ate " + Game.world.getGirlsEaten() + " girl(s)!");
		if (Game.world.getGainMode() == true)
		{
			TextDemo.print("You gain " + (player.getPlayerWeight() - player.getPlayerLastWeight()) + " kgs, or "
					+ Functions.metricToImperialWeight(player.getPlayerWeight() - player.getPlayerLastWeight())
					+ " lbs.");
		}
	}

	// text that displays information at the start of the scene
	public static void daysPassed()
	{
		int days = Game.world.getDays();
		int lastDay = Game.world.getLastDay();
		
		if (days > 0)
		{
			TextDemo.print("It has been " + (days - lastDay) / 5 + " week(s) and "
					+ (days - lastDay) % 5 + " day(s) since your last romp.\n"
					+ "The school year began " + days / 5 + " week(s) and " + days % 5 + " ago.");
			
			if (days < 25 && days >= 0)
			{
				TextDemo.print("It is the month of September.");
			}
			else if (days < 50 && days >= 25)
			{
				TextDemo.print("It is the month of November.");
			}
			else if (days < 70 && days >= 50)
			{
				TextDemo.print("It is the month of December.");
			}
			else if (days < 95 && days >= 70)
			{
				TextDemo.print("It is the month of January.");
			}
			else if (days < 115 && days >= 95)
			{
				TextDemo.print("It is the month of February.");
			}
			else if (days < 140 && days >= 115)
			{
				TextDemo.print("It is the month of March.");
			}
			else if (days < 162 && days >= 140)
			{
				TextDemo.print("It is the month of April.");
			}
			else if (days < 184 && days >= 162)
			{
				TextDemo.print("It is the month of May.");
			}
			else if (days < 200 && days >= 184)
			{
				TextDemo.print("It is the month of June. The last month of school.");
			}
			TextDemo.print("");
			Game.world.setLastDay(days);

			if (Game.world.getGainMode() == true)
			{
				Girl girl = Game.world.getScenePlayer();
				TextDemo.print(
						"You have gained " + (girl.getPlayerWeight() - girl.getPlayerLastWeight()) + " kgs, or "
								+ Functions.metricToImperialWeight(girl.getPlayerWeight() - girl.getPlayerLastWeight())
								+ " lbs. since your last scene and "
								+ (girl.getPlayerWeight() - girl.getPlayerStartingWeight()) + " kgs, or "
								+ Functions
										.metricToImperialWeight(girl.getPlayerWeight() - girl.getPlayerStartingWeight())
								+ " lbs. since the start of the school year.");
			}
		}
	}

	// prints a value that corresponds with a certain event between scenes
	public static void daysInBetween(int num)
	{
		switch (num)
		{
		case 0:
			System.out.print("A girl was eaten in the passing days! Check the green with 'green'.");
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:
		case 21:
		case 22:
		case 23:
		case 24:
			break;

		}
	}

	// ================================================================================
	// Game over
	// ================================================================================

	public static void gameOver()
	{
		TextDemo.print("\nYou are dead. Not big surprise.");
	}

	// ================================================================================
	// Scene dialogues
	// ================================================================================

	public static void mealInterrupted(Girl girl, Girl teacher)
	{
		TextDemo.print("'Stop! Spit her out!' says " + teacher.getGirlDisplayName() + ". You let " + girl.getGirlDisplayName()
				+ " go! She runs for the hills. " + teacher.getGirlDisplayName()
				+ " sends you to class. Your rampage is over for today. You have been sent to class.");
	}

	public static void mealInterruptedTeacher(Girl girl, Girl teacher)
	{
		TextDemo.print("'Eating a member is staff is punishible by a permanent detention!' says " + teacher.getGirlDisplayName()
				+ ".\n" + girl.getGirlDisplayName() + " is pulled from you. She fixes her hair." + "\n" + teacher.getGirlDisplayName()
				+ " eats you." + "You are dead. Not a big surprise.");
	}

	public static void teacherEatingLuck()
	{
		TextDemo.print(
				"Despite what you've heard, once you release the teacher in your jaws, while they seem upset, they take pity on you.\n"
						+ "\"Just a lapse of judgement, perhaps. We were all young once.\" Odds are that that was a slim escape.");
	}

	public static void girlEatingLuck(Girl girl, Girl teacher, Girl player, int num)
	{
		TextDemo.print("You let " + girl.getGirlDisplayName() + " go. 'Thank you, " + player.getGirlDisplayName() + ". Now hurry along to class,' says "
				+ teacher.getGirlDisplayName()
				+ " You count your blessings that you weren't led to class on your own, and now wait to act. What do you do now?");
	}

	// ================================================================================
	// Preset lists
	// ================================================================================

	public static void presetList()
	{
		TextDemo.print("\nChoose from this list of presets:" + "\n"
				+ "\n\t- Debra: Chilled, Slacker half-naga. (furs disabled, weight gain disabled)."
				+ "\n\t- Michelle: Preppy, Slacker full-naga. (furs disabled, weight gain disabled)."
				+ "\n\t- Nee: Bully, Cheerleading neko. (furs disabled, weight gain enabled)"
				+ "\n\t- Graisse: Bully, Slacker anthro feline. (furs enabled, weight gain enabled)"
				+ "\n\t- Lucy: Preppy, Drama anthro canine. (furs enabled, weight gain enabled)");
	}

	// ================================================================================
	// Printing individual characters
	// ================================================================================

	// overview the player gets after character creation, shows differently
	// depending on gain mode
	// TODO: ADD FUR MODE UPGRADE
	public static void creationOverview(Girl player)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Character overview -");
		sb.append("\n\tName: " + player.getGirlName() + " " + player.getGirlLastName());
		sb.append("\n\tHouse: " + player.getGirlHouse());
		sb.append("\n\tGroup: " + player.getGirlClub());

		sb.append("\nAppearance -");
		sb.append("\n\tRace: " + player.getGirlRace());
		sb.append("\n\tSkin Colour: " + player.getGirlSkinColour());
		sb.append("\n\tHair Colour: " + player.getGirlHairColour());
		sb.append("\n\tHair Type: " + player.getGirlHairType());

		if (Game.world.getGainMode() == true)
		{
			sb.append("\n\tHeight: " + player.getPlayerHeight() + " cm, or "
					+ Functions.metricToImperialHeight(player.getPlayerHeight()));
			sb.append("\n\tWeight: " + player.getPlayerWeight() + " kg, or "
					+ Functions.metricToImperialWeight(player.getPlayerWeight()) + " lbs.");
		}

		sb.append("\nStats -");
		sb.append("\n\tClass: " + player.getGirlClass());
		sb.append("\n\tCharisma: " + Functions.convertStars(player.getCharisma()));
		sb.append("\n\tIntellgence: " + Functions.convertStars(player.getIntelligence()));
		sb.append("\n\tPerception: " + Functions.convertStars(player.getPerception()));
		sb.append("\n\tStrength: " + Functions.convertStars(player.getStrength()));
		sb.append("\n\tLuck: " + Functions.convertStars(player.getLuck()));

		TextDemo.print(sb.toString());
	}

	// overview the player gets on calling, shows differently depending on gain
	// mode, also shows some stats
	// TODO: ADD FUR MODE UPGRADE
	public static void playerOverview()
	{
		Girl girl = Game.world.getScenePlayer();
		if (Game.world.getGainMode() == true)
		{
			TextDemo.print("\nCharacter overview:" + "\n\nName: " + girl.getGirlName() + " "
					+ girl.getGirlLastName() + "\nHouse: " + girl.getGirlHouse() + "\nGroup: "
					+ girl.getGirlClub() + "\n\n\tYou are a " + girl.getGirlRace() + " with "
					+ girl.getGirlSkinColour() + " skin, " + girl.getGirlHairColour() + " hair, styled "
					+ girl.getGirlHairType() + " and your body is " + girl.getGirlBodyType() + "."
					+ "\n\n\tYou are " + girl.getPlayerHeight() + " cm, or "
					+ Functions.metricToImperialHeight(girl.getPlayerHeight()) + " tall, and you weigh "
					+ girl.getPlayerWeight() + " kg, or " + Functions.metricToImperialWeight(girl.getPlayerWeight())
					+ " lbs.");

			TextDemo.print(
					"\tYou have gained " + (girl.getPlayerWeight() - girl.getPlayerStartingWeight()) + " kgs, or "
							+ Functions.metricToImperialWeight(girl.getPlayerWeight() - girl.getPlayerStartingWeight())
							+ " lbs. since the start of the school year." + "\n\n\tStats -" + "\n\t\tClass: "
							+ girl.getGirlClass() + "\n\t\tCharisma: " + Functions.convertStars(girl.getCharisma())
							+ "\n\t\tIntellgence: " + Functions.convertStars(girl.getIntelligence())
							+ "\n\t\tPerception: " + Functions.convertStars(girl.getPerception()) + "\n\t\tStrength: "
							+ Functions.convertStars(girl.getStrength()) + "\n\t\tLuck: "
							+ Functions.convertStars(girl.getLuck()));
			if (girl.getVictims() == null)
			{
				TextDemo.print("\n\tFellow students eaten: 0");
			}
			else
			{
				TextDemo.print("\n\tFellow students eaten: " + girl.getVictims().toArray().length);
			}
		}
		else
		{
			TextDemo.print(
					"\nCharacter overview:" + "\n\nName: " + girl.getGirlName() + " " + girl.getGirlLastName()
							+ "\nHouse: " + girl.getGirlHouse() + "\nGroup: " + girl.getGirlClub()
							+ "\n\n\tYou are a " + girl.getGirlRace() + " with " + girl.getGirlSkinColour()
							+ " skin, " + girl.getGirlHairColour() + " hair, styled " + girl.getGirlHairType()
							+ " and your body is " + girl.getGirlBodyType() + "." + "\n\n\tStats -" + "\n\t\tClass: "
							+ girl.getGirlClass() + "\n\t\tCharisma: " + Functions.convertStars(girl.getCharisma())
							+ "\n\t\tIntellgence: " + Functions.convertStars(girl.getIntelligence())
							+ "\n\t\tPerception: " + Functions.convertStars(girl.getPerception()) + "\n\t\tStrength: "
							+ Functions.convertStars(girl.getStrength()) + "\n\t\tLuck: "
							+ Functions.convertStars(girl.getLuck()));
			if (girl.getVictims() == null)
			{
				TextDemo.print("\n\tFellow students eaten: 0");
			}
			else
			{
				TextDemo.print("\n\tFellow students eaten: " + girl.getVictims().toArray().length);
			}
		}

	}

	// overview of a girl, varies with alive or dead
	// TODO: ADD FUR MODE UPGRADE
	public static void girlOverview(Girl girl)
	{
		String o1 = offset(1);

		// teacher conditional
		if (girl.getTeacher() == true)
		{
			TextDemo.print(nl(2) + o1 + "Miss " + girl.getGirlName() + " " + girl.getGirlLastName()
					+ Functions.convertStars(girl.getGirlStars()) + nl(2) + o1 + "A " + girl.getGirlMajorPersonality()
					+ " woman who is " + girl.getGirlMinorPersonality() + "." + nl(1) + o1 + "Role in staff: "
					+ girl.getGirlSubject());
		}
		else
		{
			TextDemo.print(nl(2) + o1 + girl.getGirlName() + " " + girl.getGirlLastName()
					+ Functions.convertStars(girl.getGirlStars()) + nl(2) + o1 + "A " + girl.getGirlMajorPersonality()
					+ " girl who is " + girl.getGirlMinorPersonality() + "." + nl(1) + o1 + "House: "
					+ girl.getGirlHouse() + nl(1) + o1 + "Group: " + girl.getGirlClub());
		}

		// fur mode conditional
		if (Game.world.getFurMode() == true)
		{
			TextDemo.print(nl(1) + o1 + girl.getGirlName() + " is an anthro " + girl.getGirlRace() + ". She has "
					+ girl.getGirlSkinColour() + " coloured fur and " + girl.getGirlHairColour() + " hair, styled "
					+ (girl.getGirlHairType() + ". Her body is " + (girl.getGirlBodyType() + ".")));
		}
		else
		{
			TextDemo.print(nl(1) + o1 + girl.getGirlName() + " is a " + girl.getGirlRace() + ". She has "
					+ girl.getGirlHairColour() + " hair, styled "
					+ (girl.getGirlHairType() + ". Her body is " + (girl.getGirlBodyType() + ".")));
		}

		// predator conditional
		if (girl.getGirlPredator() == true)
		{
			TextDemo.print(nl(1) + o1 + girl.getGirlName() + " is a predator.");
			// no victims conditional
			if (girl.getVictims() == null)
			{
				TextDemo.print(o1 + "Fellow students eaten: 0");
			}
			else
			{
				TextDemo.print(o1 + "Fellow students eaten: " + girl.getVictims().toArray().length);
			}
		}

		// alive conditional
		if (girl.getGirlAlive() == false)
		{
			TextDemo.print(nl(2) + o1 + girl.getGirlName() + " was eaten by " + girl.getGirlEatenBy());
		}

	}

	// ================================================================================
	// Misc reusables
	// ================================================================================

	public static String tabPadding(String string, int tabs)
	{
		tabs = tabs * 8;
		int length = string.length();
		int diff = tabs - length;
		if (diff > 0)
		{
			for (int i = 0; i < diff; i++)
			{
				string = string + " ";
			}
		}
		else
		{
			TextDemo.print(" not enough tabs");
		}
		return string;
	}

	public static String offset(int num)
	{
		switch (num)
		{
		case 2:
			return "\t\t";
		case 3:
			return "\t\t\t";
		case 4:
			return "\t\t\t\t";
		case 5:
			return "\t\t\t\t\t";
		}
		return "\t";
	}

	public static void divider()
	{
		TextDemo.print("\n\n"
				+ "  .--.      .--.      .--.      .--.      .-'.      .--.      .--.      .--.      .--.      .`-.      .-\r\n"
				+ ":::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::\r\n"
				+ "'      `--'      `--'      `--'      `--'      `.-'      `--'      `--'      `--'      `-.'      `--'");
	}

	public static String nl(int num)
	{
		switch (num)
		{
		case 2:
			return "\n\n";
		case 3:
			return "\n\n\n";
		case 4:
			return "\n\n\n\n";
		case 5:
			return "\n\n\n\n\n";
		}
		return "\n";
	}

	public static void invalidInput()
	{
		TextDemo.print("Invalid input.");
	}

	// ================================================================================
	// Printing dialogue
	// ================================================================================

	public static void printDialogue(ArrayList<String> dialogue)
	{
		Random rand = new Random();
		int j = rand.nextInt(dialogue.toArray().length);
		TextDemo.print(dialogue.get(j));
	}

	// ================================================================================
	// location dialogue
	// ================================================================================

	static URL urlLocationDialogueFile = Girl.class.getResource("resources/locationDialogue.txt");

	//TODO add more locations
	public static ArrayList<String> loadLocationDialogue(String type)
	{
		Girl girl = new Girl();
		ArrayList<String> data = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(urlLocationDialogueFile.openStream())))
		{
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null)
			{
				switch (type)
				{
				case "hallways":
					if (sCurrentLine.equals("---START_HALLWAYS_DIALOGUE---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_HALLWAYS_DIALOGUE---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "locker room":
					if (sCurrentLine.equals("---START_LOCKER_ROOM_DIALOGUE---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_LOCKER_ROOM_DIALOGUE---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "library":
					if (sCurrentLine.equals("---START_LIBRARY_DIALOGUE---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_LIBRARY_DIALOGUE---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "next":
					break;
				}
			}

		}
		catch (IOException e)
		{
			TextDemo.error(e);
		}
		return data;
	}

	// ================================================================================
	// vore dialogue
	// ================================================================================

	private ArrayList<ArrayList<String>> eatingGirlDialogue;

	public void setEatingGirlDialogue(
			/** ArrayList<String> eatingGirlDialogue **/
			Girl girl)
	{
		this.eatingGirlDialogue = new ArrayList<ArrayList<String>>();

		for (int i = 0; i < 14; i++)
		{
			this.eatingGirlDialogue.add(setGirlVoreDialogue(girl, i));
		}
	}

	public ArrayList<ArrayList<String>> getEatingGirlDialogue()
	{
		return this.eatingGirlDialogue;
	}

	public static ArrayList<String> setGirlVoreDialogue(Girl girl, int i)
	{
		ArrayList<String> temp = new ArrayList<String>();
		String[] arr = new String[]
		{ "beginHF", "beginFF", "endHF", "endFF", "headReached", "neckReached", "shouldersReached", "chestReached",
				"waistReached", "hipsReached", "thighsReached", "kneesReached", "anklesReached", "feetReached" };
		temp = loadVoreDialogue(arr[i], girl);
		return temp;
	}

	public static String chooseRandomString(ArrayList<String> arrList)
	{
		Random rand = new Random();
		String string = arrList.get(rand.nextInt(arrList.toArray().length));
		return string + " ";
	}

	static URL urlVoreDialogueFile = Girl.class.getResource("resources/voreDialogue.txt");

	public static ArrayList<String> loadVoreDialogue(String type, Girl girl)
	{
		ArrayList<String> data = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(urlVoreDialogueFile.openStream())))
		{
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null)
			{
				switch (type)
				{
				case "beginHF":
					if (sCurrentLine.equals("---START_BEGIN_GIRL_EATING_HF_DIALOGUE---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_BEGIN_GIRL_EATING_HF_DIALOGUE---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "beginFF":
					if (sCurrentLine.equals("---START_BEGIN_GIRL_EATING_FF_DIALOGUE---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_BEGIN_GIRL_EATING_FF_DIALOGUE---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "endHF":
					if (sCurrentLine.equals("---START_FINISH_GIRL_EATING_HF_DIALOGUE---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_FINISH_GIRL_EATING_HF_DIALOGUE---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "endFF":
					if (sCurrentLine.equals("---START_FINISH_GIRL_EATING_FF_DIALOGUE---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_FINISH_GIRL_EATING_FF_DIALOGUE---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "headReached":
					if (sCurrentLine.equals("---START_GIRL_EATING_HEAD_REACHED---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_GIRL_EATING_HEAD_REACHED---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "neckReached":
					if (sCurrentLine.equals("---START_GIRL_EATING_NECK_REACHED---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_GIRL_EATING_NECK_REACHED---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "shouldersReached":
					if (sCurrentLine.equals("---START_GIRL_EATING_SHOULDERS_REACHED---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_GIRL_EATING_SHOULDERS_REACHED---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "chestReached":
					if (sCurrentLine.equals("---START_GIRL_EATING_CHEST_REACHED---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_GIRL_EATING_CHEST_REACHED---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "waistReached":
					if (sCurrentLine.equals("---START_GIRL_EATING_WAIST_REACHED---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_GIRL_EATING_WAIST_REACHED---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "hipsReached":
					if (sCurrentLine.equals("---START_GIRL_EATING_HIPS_REACHED---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_GIRL_EATING_HIPS_REACHED---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "thighsReached":
					if (sCurrentLine.equals("---START_GIRL_EATING_THIGHS_REACHED---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_GIRL_EATING_THIGHS_REACHED---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "kneesReached":
					if (sCurrentLine.equals("---START_GIRL_EATING_KNEES_REACHED---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_GIRL_EATING_KNEES_REACHED---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "anklesReached":
					if (sCurrentLine.equals("---START_GIRL_EATING_ANKLES_REACHED---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_GIRL_EATING_ANKLES_REACHED---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "feetReached":
					if (sCurrentLine.equals("---START_GIRL_EATING_FEET_REACHED---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_GIRL_EATING_FEET_REACHED---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "next":
					break;
				}
			}

		}
		catch (IOException e)
		{
			TextDemo.error(e);
		}
		return data;
	}

	// ================================================================================
	// loading files
	// ================================================================================

	static URL urlDialogueFile = Girl.class.getResource("resources/dialogue.txt");

	public void setIntroDialogue(Girl girl)
	{
		this.introDialogue = loadDialogue("girlIntro", girl);
	}

	public ArrayList<String> getIntroDialogue()
	{
		return this.introDialogue;
	}

	public static String keyWords(String input, Girl girl)
	{
		String output = input;
		Girl player = Game.world.getScenePlayer();

		switch (input)
		{
		// girl
		case "girlName":
			output = girl.getGirlDisplayName();
			break;
		case "girlHairColour":
			output = girl.getGirlHairColour();
			break;
		case "girlHairType":
			output = girl.getGirlHairType();
			break;
		case "girlBodyType":
			output = girl.getGirlBodyType();
			break;
		case "girlRace":
			output = girl.getGirlRace();
			break;
		case "girlSkinColour":
			output = girl.getGirlSkinColour();
			break;
		case "girlClub":
			output = girl.getGirlClub();
			break;
		case "girlHouse":
			output = girl.getGirlHouse();
			break;
		case "girlHouseColour":
			output = girl.getGirlHouseColour();
			break;
		// player
		case "playerName":
			output = player.getGirlName();
			break;
		case "playerLastName":
			output = player.getGirlLastName();
			break;
		case "playerRace":
			output = player.getGirlRace();
			break;
		case "playerSkinColour":
			output = player.getGirlSkinColour();
			break;
		case "playerHairColour":
			output = player.getGirlHairColour();
			break;
		case "playerHairType":
			output = player.getGirlHairType();
			break;
		case "playerBodyType":
			output = player.getGirlBodyType();
			break;
		case "playerClub":
			output = player.getGirlClub();
			break;
		case "playerClass":
			output = player.getGirlClass();
			break;
		case "playerHouse":
			output = player.getGirlHouse();
			break;
		// location
		case "locationName":
			output = Game.world.getLocation().getLocationName();
			break;
		}
		return output;
	}

	public static String personaliseGirl(String string, Girl girl)
	{
		String str = string;
		ArrayList<String> before = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(str, "[]-_()/\\\"':;|., !<>", true);
		while (st.hasMoreElements())
		{
			before.add((String) st.nextElement());
		}
		String finished = "";
		for (int i = 0; i < before.toArray().length; i++)
		{
			finished = finished + keyWords(before.get(i), girl);
		}
		return finished;
	}

	private ArrayList<String> introDialogue;

	public static ArrayList<String> loadDialogue(String type, Girl girl)
	{
		ArrayList<String> data = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(urlDialogueFile.openStream())))
		{
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null)
			{
				switch (type)
				{
				case "girlIntro":
					if (sCurrentLine.equals("---START_GIRL_INTRO_DIALOGUE---"))
					{
						while ((sCurrentLine = br.readLine()) != null)
						{
							if (sCurrentLine.equals("---END_GIRL_INTRO_DIALOGUE---"))
							{
								break;
							}
							else
							{
								sCurrentLine = personaliseGirl(sCurrentLine, girl);
								data.add(sCurrentLine);
							}
						}
					}
					break;
				case "next":
					break;
				}
			}

		}
		catch (IOException e)
		{
			TextDemo.error(e);
		}
		return data;
	}
}
