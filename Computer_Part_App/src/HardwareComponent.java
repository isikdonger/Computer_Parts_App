import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public abstract class HardwareComponent implements HardwarePart {
	protected double recommendedPrice;
	protected String releaseDate;
	protected String brand;
	
	public HardwareComponent(double recommendedPrice, String releaseDate, String brand) {
		this.recommendedPrice = recommendedPrice;
		this.releaseDate = releaseDate;
		this.brand = brand;
	}

	public double getRecommendedPrice() {
		return recommendedPrice;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	@Override
	public String toString() {
		return "HardwareComponent:\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: " + releaseDate
				+ "\nbrand: " + brand;
	}

	@Override
	public boolean isGetter(Method method) {
		// TODO Auto-generated method stub
		return method.getName().endsWith("get");
	}

	@Override
	public <T> Map<String, T> getSuperClassValues() {
		return null;
	}

	@Override
	public <T> Map<String, T> getValues() {
		Map<String, T>  values = this.getSuperClassValues();
		Method[] methods = this.getClass().getSuperclass().getDeclaredMethods();
		for (Method method: methods) {
			if(isGetter(method) && !(method.getName().endsWith("Values"))) {
				try {
					T value = (T)method.invoke(this);
					values.put(method.getName().substring(3), value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return values;
	}
}
