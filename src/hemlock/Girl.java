package hemlock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Girl implements Serializable
{
	private String name;
	private String lastName;
	private String displayName;
	private String house;
	private String houseColour;
	private String club;
	private String subject;
	private String majorPersonality;
	private String minorPersonality;
	private String race;
	private String skinColour;
	private String hairColour;
	private String hairType;
	private String bodyType;
	private int stars;
	private Boolean alive;
	private String eatenBy;
	private Boolean predator;
	private Boolean teacher;
	private ArrayList<Girl> victims;

	private String playerClass;
	private int charisma;
	private int intelligence;
	private int luck;
	private int perception;
	private int strength;
	private double startingHeight;
	private double startingWeight;
	private double lastHeight;
	private double lastWeight;
	private double height;
	private double weight;

	// ================================================================================
	// Girl constructor
	// ================================================================================
	
	public Girl()
	{
		// names
		this.name = "none";
		this.lastName = "none";
		this.displayName = "";

		// appearance
		this.bodyType = "none";
		this.race = "none";
		this.skinColour = "none";
		this.hairColour = "none";
		this.hairType = "none";

		// groups
		this.house = "none";
		this.club = "none";

		// traits
		this.majorPersonality = "none";
		this.minorPersonality = "none";

		// entity state
		this.alive = true;
		this.eatenBy = "none";
		this.predator = false;
		this.teacher = false;
		this.victims = new ArrayList<Girl>();
		this.stars = 0;

		// set stats
		this.charisma = 0;
		this.intelligence = 0;
		this.luck = 0;
		this.perception = 0;
		this.strength = 0;
		this.startingHeight = 1;
		this.startingWeight = 1;
		this.lastHeight = 1;
		this.lastWeight = 1;
		this.height = 1;
		this.weight = 1;
	}

	// ================================================================================
	// Function that generates a girl
	// ================================================================================

	private void randomize()
	{
		setGirlName(randomOption(girlNames));
		setGirlLastName(randomOption(girlLastNames));
		setGirlMajorPersonality(randomOption(girlMajorPersonality));
		setGirlMinorPersonality(randomOption(girlMinorPersonality));
		if (Game.world.getFurMode())
		{
			setGirlRace(randomOption(girlFurRace));
			setGirlSkinColour(randomOption(girlFurColour));
		}
		else
		{
			setGirlRace("human");
			setGirlSkinColour(randomOption(girlSkinColour));
		}
		setGirlHairColour(randomOption(girlHairColour));
		setGirlHairType(randomOption(girlHairType));
		setGirlBodyType(randomOption(girlBodyType));
		setGirlPredator(Math.random() > 0.94);

		setGirlDisplayName(getGirlName());
	}
	
	public void randomizeStudent()
	{
		randomize();

		setGirlHouse(randomOption(girlHouses));
		setGirlClass(randomOption(girlClasses));
		setGirlClub(randomOption(girlClub));
	}
	
	public void randomizeTeacher(int subjectIndex)
	{
		randomize();
		setGirlDisplayName("Miss " + getGirlLastName());
		setSubject(randomOption(girlTeacherJobs));
		setGirlTeacher(true);
		setGirlPredator(true);
	}
	
	public void randomizePlayer()
	{
		randomizeStudent();
		setGirlPredator(true);
	}

	// ================================================================================
	// Girl Setters
	// ================================================================================

	// names
	public void setGirlName(String string)
	{
		this.name = TextUtils.capitalize(string);
	}

	public void setGirlLastName(String string)
	{
		this.lastName = TextUtils.capitalize(string);
	}

	public void setGirlDisplayName(String string)
	{
		this.displayName = string;
	}
	
	public void setSubject(String string)
	{
		this.subject = string;
	}

	public void setGirlEatenBy(String eatenBy)
	{
		this.eatenBy = eatenBy;
	}

	public void setGirlPredator(boolean predator)
	{
		this.predator = predator;
	}

	public void setGirlTeacher(boolean teacher)
	{
		this.teacher = teacher;
	}

	public void randomizeHouse()
	{
	}
	
	public void setGirlHouse(String value)
	{
		this.house = value;
		
		int houseIndex = girlHouses.indexOf(this.house);
		this.houseColour = girlHouseColours.get(houseIndex);
	}
		
	public void setGirlClass(String value)
	{
		this.playerClass = value;

		switch (this.playerClass)
		{
		case "preppy":
			setCharisma(5);
			setIntelligence(3);
			setLuck(1);
			setPerception(4);
			setStrength(2);
			break;
		case "chilled":
			setCharisma(4);
			setIntelligence(2);
			setLuck(5);
			setPerception(1);
			setStrength(3);
			break;
		case "nerdy":
			setCharisma(2);
			setIntelligence(5);
			setLuck(3);
			setPerception(4);
			setStrength(1);
			break;
		case "bully":
			setCharisma(3);
			setIntelligence(2);
			setLuck(1);
			setPerception(4);
			setStrength(5);
			break;
		}
	}
	
	public void randomizeGirlClub()
	{
		setGirlClub(girlClub.get(new Random().nextInt(girlClub.size())));
	}

	public void setGirlClub(String club)
	{
		this.club = club;
	}

	public void setGirlMajorPersonality(String string)
	{
		this.majorPersonality = string;
	}

	public void setGirlMinorPersonality(String string)
	{
		this.minorPersonality = string;
	}

	public void setGirlRace(String string)
	{
		this.race = string;
	}

	public void setGirlHairColour(String string)
	{
		this.hairColour = string;
	}

	public void setGirlBodyType(String string)
	{
		this.bodyType = string;
	}

	public void setGirlHairType(String string)
	{
		this.hairType = string;
	}

	public void setGirlSkinColour(String string)
	{
		this.skinColour = string;
	}

	public void setGirlStars(int stars)
	{
		this.stars = stars;
	}

	public void setGirlAlive(boolean alive)
	{
		this.alive = alive;
	}

	// ================================================================================
	// Girl Getters
	// ================================================================================

	public boolean getTeacher()
	{
		return this.teacher;
	}

	public String getGirlDisplayName()
	{
		return this.displayName;
	}

	public String getGirlEatenBy()
	{
		return this.eatenBy;
	}

	public String getGirlSkinColour()
	{
		return this.skinColour;
	}

	public ArrayList<Girl> getVictims()
	{
		return this.victims;
	}

	public String getGirlClub()
	{
		return this.club;
	}

	public String getGirlHouse()
	{
		return this.house;
	}

	public String getGirlHouseColour()
	{
		return this.houseColour;
	}

	public String getGirlMajorPersonality()
	{
		return this.majorPersonality;
	}

	public String getGirlMinorPersonality()
	{
		return this.minorPersonality;
	}

	public String getGirlRace()
	{
		return this.race;
	}

	public String getGirlHairColour()
	{
		return this.hairColour;
	}

	public String getGirlHairType()
	{
		return this.hairType;
	}

	public String getGirlName()
	{
		return this.name;
	}

	public String getGirlLastName()
	{
		return this.lastName;
	}

	public int getGirlStars()
	{
		return this.stars;
	}

	public void addStar()
	{
		++this.stars;
	}
	
	public Boolean getGirlAlive()
	{
		return this.alive;
	}

	public Boolean getGirlPredator()
	{
		return this.predator;
	}

	public String getGirlBodyType()
	{
		return this.bodyType;
	}

	public String getGirlSubject()
	{
		return this.subject;
	}

	// ================================================================================
	// Player SETTERS
	// ================================================================================

	// player stats

	public void setCharisma(int num)
	{
		this.charisma = num;
	}

	public void setIntelligence(int num)
	{
		this.intelligence = num;
	}

	public void setLuck(int num)
	{
		this.luck = num;
	}

	public void setPerception(int num)
	{
		this.perception = num;
	}

	public void setStrength(int num)
	{
		this.strength = num;
	}

	// weight gain stats

	public void setPlayerHeight(double height)
	{
		this.height = height;
	}

	public void setPlayerWeight(double weight)
	{
		this.weight = weight;
	}

	public void setPlayerLastHeight(double height)
	{
		this.lastHeight = height;
	}

	public void setPlayerLastWeight(double weight)
	{
		this.lastWeight = weight;
	}

	public void setPlayerStartingHeight(double height)
	{
		this.startingHeight = height;
	}

	public void setPlayerStartingWeight(double weight)
	{
		this.startingWeight = weight;
	}

	// ================================================================================
	// Player GETTERS
	// ================================================================================

	// stats
	public String getGirlClass()
	{
		return this.playerClass;
	}

	public int getCharisma()
	{
		return this.charisma;
	}

	public int getIntelligence()
	{
		return this.intelligence;
	}

	public int getLuck()
	{
		return this.luck;
	}

	public int getPerception()
	{
		return this.perception;
	}

	public int getStrength()
	{
		return this.strength;
	}

	// weight gain stats

	public double getPlayerHeight()
	{
		return this.height;
	}

	public double getPlayerWeight()
	{
		return this.weight;
	}

	public double getPlayerStartingHeight()
	{
		return this.startingHeight;
	}

	public double getPlayerStartingWeight()
	{
		return this.startingWeight;
	}

	public double getPlayerLastHeight()
	{
		return this.lastHeight;
	}

	public double getPlayerLastWeight()
	{
		return this.lastWeight;
	}

	// ================================================================================
	// Reading from file
	// ================================================================================
	
	public static final transient ArrayList<String> girlNames = loadOptionList("FIRST_NAMES");
	public static final transient ArrayList<String> girlLastNames = loadOptionList("LAST_NAMES");
	public static final transient ArrayList<String> girlMajorPersonality = loadOptionList("MAJOR_PERSONALITY");
	public static final transient ArrayList<String> girlMinorPersonality = loadOptionList("MINOR_PERSONALITY");
	public static final transient ArrayList<String> girlRace = loadOptionList("RACE");
	public static final transient ArrayList<String> girlSkinColour = loadOptionList("SKIN_COLOUR");
	public static final transient ArrayList<String> girlBodyType = loadOptionList("BODY_TYPE");
	public static final transient ArrayList<String> girlHairColour = loadOptionList("HAIR_COLOUR");
	public static final transient ArrayList<String> girlHairType = loadOptionList("HAIR_TYPE");
	public static final transient ArrayList<String> girlClub = loadOptionList("CLUBS");

	public static final transient ArrayList<String> girlFurRace = loadOptionList("FUR_RACE");
	public static final transient ArrayList<String> girlFurColour = loadOptionList("FUR_COLOUR");

	public static final transient ArrayList<String> girlHouses = new ArrayList<String>(Arrays.asList(new String[] { "Dagonbelch", "Tendriltake", "Lloigorsloush" }));
	public static final transient ArrayList<String> girlHouseColours = new ArrayList<String>(Arrays.asList(new String[] { "red", "green", "blue" }));
	
	public static final transient ArrayList<String> girlClasses = new ArrayList<String>(Arrays.asList(new String[] { "preppy", "chilled", "nerdy", "bully" }));

	// Miss X is a/an
	public static final transient ArrayList<String> girlTeacherJobs = loadOptionList("TEACHER_ROLE");

	public static ArrayList<String> loadOptionList(String type)
	{
		ArrayList<String> data = new ArrayList<String>();

		URL urlDetailFile = Girl.class.getResource("resources/girlMaster.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(urlDetailFile.openStream())))
		{
			final String startToken = "---START_" + type + "---";
			final String endToken = "---END_" + type + "---";
			
			boolean reading = false;
			String line;
			while ((line = br.readLine()) != null)
			{
				line = line.trim();
				if (!reading && line.equals(startToken))
				{
					reading = true;
				}
				else if (reading && line.equals(endToken))
				{
					break;
				}
				else if (reading)
				{
					data.add(line.toLowerCase());
				}
			}
		}
		catch (Exception e)
		{
			TextDemo.error(e);
		}
		
		return data;
	}
	
	public static String randomOption(ArrayList<String> options)
	{
		return options.get(new Random().nextInt(options.size()));
	}
	
	public static String listOptions(ArrayList<String> options)
	{
		StringBuilder sb = new StringBuilder();
		Iterator<String> iter = options.iterator();
		
		while (iter.hasNext())
		{
			sb.append(iter.next());
			if (iter.hasNext())
			{
				sb.append(", ");
			}
		}
		
		return sb.toString();
	}
}
