import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public interface HardwarePart {
	public boolean isGetter(Method method);
	public <T> Map<String, T> getSuperClassValues();
	public <T> Map<String, T> getValues();
}
