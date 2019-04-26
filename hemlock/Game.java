package hemlock;

//utilities

import java.util.Scanner;

public class Game
{
	public static Dialogue dialogue = new Dialogue();
	public static World world;

	public static void main(String[] args)
	{

		// ================================================================================
		// Initializing essential variables and elements || only performed once
		// ================================================================================

		// GUI
		TextDemo.createAndShowGUI();

		// INITIALISING SCENE
		world = (World)SaveManager.loadObject("auto.sav");
		if (world == null)
		{
			world = new World();
		}

		// MAIN MENU AND CHARACTER CREATION
		Inputs.mainMenu(false);

		// ================================================================================
		// GAME LOOP
		// ================================================================================

		// BOOLEANS FOR GAME (only run once)
		boolean playerDead = false;

		while (true)
		{
			Dialogue.gameMenuText();
			while (playerDead == false)
			{
				if (world.getScenePlayer().getGirlAlive() == false)
				{
					TextDemo.print("You are dead! Better luck next time.");
					playerDead = true;
				}
				// inputs
				Inputs.menuInputs();
			}
			Inputs.menuInputsDead();
		}
	}

	// ================================================================================
	// Character creation, called from main menu. New game
	// ================================================================================

	public static void characterCreation(boolean playerCreated)
	{
		Scanner in = new Scanner(System.in);
		while (playerCreated == false)
		{
			// run character creation
			CharacterCreation.createCharacter();
			while (playerCreated == false)
			{
				TextDemo.print(
						"If you would like to create your character again, please enter (r)edo. \nIf you are happy with your character, type in the phrase (b)egin.");
				String input = in.nextLine().toLowerCase();
				if (input.equals("redo") || input.equals("r"))
				{
					// do nothing, let loop restart
				}
				else if (input.equals("begin") || input.equals("b"))
				{

					playerCreated = true;
					break;
				}
				else
				{
					TextDemo.print(
							"\nPlease enter either 'redo' to create your character again, or 'begin' to begin the game. \n");
					input = in.nextLine();
				}
			}
		}
	}

}
