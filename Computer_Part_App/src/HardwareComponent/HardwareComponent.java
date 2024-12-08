package HardwareComponent;
import System_and_Interface.HardwarePart;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	
	public Field[] getAllFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<>();
		while (clazz != null && clazz != Object.class) { // Stop at Object class
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = clazz.getSuperclass();
		}
		return fields.toArray(new Field[0]);
	}
	
	@Override
	public boolean isGetter(Method method) {
		return false;
	}

	@Override
	public <T> Map<String, T> getValues() {
		return null;
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

	public String getBrand() { return brand; }

	@Override
	public String toString() {
		return "HardwareComponent:\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: " + releaseDate
				+ "\nbrand: " + brand;
	}
}
