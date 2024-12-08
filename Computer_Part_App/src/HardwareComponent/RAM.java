package HardwareComponent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RAM extends MemoryUnit {
	private String technology;
	private int transferSpeed;

	public RAM(double recommendedPrice, String releaseDate, String brand, int capacity, String technology,
			int transferSpeed) {
		super(recommendedPrice, releaseDate, brand, capacity);
		this.technology = technology;
		this.transferSpeed = transferSpeed;
	}

	public Field[] getAllFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<>();
		while (clazz != null && clazz != Object.class) { // Stop at Object class
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = clazz.getSuperclass();
		}
		return fields.toArray(new Field[0]);
	}


	public String getTechnology() {
		return technology;
	}

	public int getTransferSpeed() {
		return transferSpeed;
	}

	@Override
	public String toString() {
		return "RAM:\ntechnology: " + technology + "\ntransferSpeed: " + transferSpeed + "\ncapacity: " + capacity
				+ "\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: " + releaseDate + "\nbrand: " + brand;
	}
	
}
