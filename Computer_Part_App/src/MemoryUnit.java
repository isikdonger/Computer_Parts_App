import java.lang.reflect.Method;
import java.util.ArrayList;

public class MemoryUnit extends HardwareComponent {
	protected int capacity;

	public MemoryUnit(double recommendedPrice, String releaseDate, String brand, int capacity) {
		super(recommendedPrice, releaseDate, brand);
		this.capacity = capacity;
	}
	
	public <T> ArrayList<T> getSuperCLassMethods() {
		ArrayList<T> values = new ArrayList<T>();
		Method[] methods = this.getClass().getSuperclass().getDeclaredMethods();
		for (Method method: methods) {
			if(isGetter(method)) {
				try {
					T value = (T)method.invoke(this);
					values.add(value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return values;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public String toString() {
		return "MemoryUnity:\ncapacity: " + capacity + "\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: "
				+ releaseDate + "\nbrand: " + brand;
	}
	
}
