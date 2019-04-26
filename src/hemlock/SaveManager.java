package hemlock;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class SaveManager {
	
	public static Object loadObject(String fileName)
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
			Object ret = ois.readObject();
			ois.close();
			
			return ret;
		}
		catch (Exception e)
		{
			//TextDemo.error("Error loading file " + fileName, e);
		}
		
		return null;
	}
	
	public static void saveObject(Object ob, String fileName)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(ob);
			oos.close();
		}
		catch (Exception e)
		{
			TextDemo.error("Error saving file " + fileName, e);
		}
	}
}
