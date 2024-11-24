import java.lang.reflect.Method;
import java.util.ArrayList;

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

	public int getWattage() {
		return wattage;
	}

	public String getFormFactor() {
		return formFactor;
	}

	public String getEfficiencyTier() {
		return efficiencyTier;
	}
	
	public <T> ArrayList<T> getMethods() {
		ArrayList<T> values = new ArrayList<T>();
		Method[] methods = this.getClass().getDeclaredMethods();
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

	@Override
	public String toString() {
		return "PowerSupply:\nwattage: " + wattage + "\nformFactor: " + formFactor + "\nefficiencyTier: "
				+ efficiencyTier + "\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: " + releaseDate
				+ "\nbrand: " + brand;
	}
}
