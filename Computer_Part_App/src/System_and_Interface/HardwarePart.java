package System_and_Interface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public interface HardwarePart {
	public boolean isGetter(Method method);
	public  <T> Map<String, T> getSuperClassValues();
	public  <T> Map<String, T> getValues();
	public Field[] getAllFields(Class<?> clazz);
}
