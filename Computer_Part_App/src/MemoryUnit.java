import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public abstract class MemoryUnit extends HardwareComponent {
	protected int capacity;

	public MemoryUnit(double recommendedPrice, String releaseDate, String brand, int capacity) {
		super(recommendedPrice, releaseDate, brand);
		this.capacity = capacity;
	}
	
	@Override
	public <T> Map<String, T> getSuperClassValues() {
		return super.getValues();
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
