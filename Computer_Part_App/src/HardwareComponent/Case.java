package HardwareComponent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Arrays;
import java.util.List;

public class Case extends HardwareComponent {
	private String formFactor;
	private String material;
	private int durability;

	public Case(double recommendedPrice, String releaseDate, String brand, String formFactor, String material) {
		super(recommendedPrice, releaseDate, brand);
		this.formFactor = formFactor;
		this.material = material;
		this.durability = findDurability();
	}

	public Field[] getAllFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<>();
		while (clazz != null && clazz != Object.class) { // Stop at Object class
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = clazz.getSuperclass();
		}
		return fields.toArray(new Field[0]);
	}


	@Override
	public <T> Map<String, T> getSuperClassValues() {
		return super.getValues();
	}

	public String getMaterial() {
		return material;
	}
	public String getFormFactor() {
		return formFactor;
	}

	public int findDurability() {
		List<String> materialOrder = Arrays.asList("Plastic", "Glass", "Aluminum", "Steel");
		List<String> formOrder = Arrays.asList("Mini-ITX", "Micro-ATX", "E-ATX", "ATX");
		int index1 = materialOrder.indexOf("material");
		int index2 = formOrder.indexOf("formFactor");
		return index1 * index2;
	}

	@Override
	public String toString() {
		return "Case:\nformFactor: " + formFactor + "\nmaterial: " + material + "\ndurability: " + durability
				+ "\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: " + releaseDate + "\nbrand: " + brand;
	}
}
