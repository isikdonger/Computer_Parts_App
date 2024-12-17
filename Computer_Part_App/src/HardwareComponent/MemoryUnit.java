package HardwareComponent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public abstract class MemoryUnit extends HardwareComponent {
	protected int capacity;

	public MemoryUnit(int modelNumber, String brand, String model, String releaseDate, double recommendedPrice,
			int capacity) {
		super(modelNumber, brand, model, releaseDate, recommendedPrice);
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public String toString() {
		return super.toString() + "Capacity: " + capacity;
	}
	
	public String toFile() {
		return super.toFile() + capacity + "::";
	}
}
