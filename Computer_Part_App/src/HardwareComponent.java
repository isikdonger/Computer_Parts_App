import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

public class HardwareComponent {
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
	
	private boolean isGetter(Method method) {
		return method.getName().startsWith("get");
	}
	
	public <T> ArrayList<T> getValues() {
		ArrayList<T> values = new ArrayList<T>();
		Class<?> currentClass = this.getClass();
		while (currentClass!=Objects.class) {
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
			currentClass = currentClass.getSuperclass();
		}
		return values;
	}

	@Override
	public String toString() {
		return "HardwareComponent:\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: " + releaseDate
				+ "\nbrand: " + brand;
	}
}
