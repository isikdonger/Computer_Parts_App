package HardwareComponent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PowerSupply extends HardwareComponent {
	private int wattage;
	private String formFactor;
	private String efficiencyTier;
	
	public PowerSupply(double recommendedPrice, String releaseDate, String brand, int wattage, String formFactor,
			String efficiencyTier) {
		super(recommendedPrice, releaseDate, brand);
		this.wattage = wattage;
		this.formFactor = formFactor;
		this.efficiencyTier = efficiencyTier;
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

	public int getWattage() {
		return wattage;
	}

	public String getFormFactor() {
		return formFactor;
	}

	public String getEfficiencyTier() {
		return efficiencyTier;
	}

	@Override
	public String toString() {
		return "PowerSupply:\nwattage: " + wattage + "\nformFactor: " + formFactor + "\nefficiencyTier: "
				+ efficiencyTier + "\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: " + releaseDate
				+ "\nbrand: " + brand;
	}
}
