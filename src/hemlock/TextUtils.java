package hemlock;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

	public static String ResolveToken(Object actor, String token)
	{
		if (actor == null)
		{
			return "{UNKNOWN ACTOR}";
		}
		
		Class<?> actorClass = actor.getClass();
		try
		{
			try
			{
				Field actorField = actorClass.getDeclaredField(token);
				return actorField.get(actor).toString();
			}
			catch (NoSuchFieldException nsfe)
			{
				try
				{
					Method actorMethod = actorClass.getMethod(token);
					return actorMethod.invoke(actor).toString();
				}
				catch (NoSuchMethodException nsme)
				{
				}
			}
		}
		catch (Exception e)
		{
			TextDemo.error(e);
		}

		return "{UNDEFINED TOKEN}";
	}

	private static final Pattern tokenPattern = Pattern.compile("\\{([\\w]+)\\.([\\w]+)\\}");
	
	public static String ReplaceTokens(String source, Hashtable<String,Object> actors)
	{
		// Scan the string for patterns matching {object.property}
		Matcher tokenMatches = tokenPattern.matcher(source);
		
		StringBuilder sb = new StringBuilder();
		
		int lastChar = 0;
		
		while (tokenMatches.find())
		{
			String actor = tokenMatches.group(1);
			String token = tokenMatches.group(2);
			
			sb.append(source, lastChar, tokenMatches.start());
			sb.append(ResolveToken(actors.get(actor),token));
			lastChar = tokenMatches.end();
		}

		sb.append(source, lastChar, source.length());
		
		return sb.toString();
	}
	
	public static String capitalize(String str)
	{
		return str.substring(0,1).toUpperCase() + str.substring(1);
	}
}
