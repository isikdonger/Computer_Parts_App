package HardwareComponent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import Interface.HardwarePart;

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
	
	public Object getValue(String fieldName) throws IllegalAccessException, InvocationTargetException {
		Class clazz = this.getClass();
		while (clazz != null && clazz != Object.class) {
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method: methods) {
				String getter = ("get"+fieldName).toLowerCase();
				if (method.getName().toLowerCase().contains(getter)) {
					return method.invoke(this);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return null;
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
				+ "\nbrand: " + brand + "\n";
	}
}
