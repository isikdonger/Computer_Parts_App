package HardwareComponent;
import System_and_Interface.HardwarePart;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class HardwareComponent implements HardwarePart {
	protected static int count=0;
	protected double recommendedPrice;
	protected String releaseDate;
	protected String brand;
	
	public HardwareComponent(double recommendedPrice, String releaseDate, String brand) {
		count++;
		this.recommendedPrice = recommendedPrice;
		this.releaseDate = releaseDate;
		this.brand = brand;
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
	
	public static int getCount() {
		return count;
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
}
