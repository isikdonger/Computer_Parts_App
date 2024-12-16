package HardwareComponent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import Interface.HardwarePart;

public abstract class HardwareComponent implements HardwarePart, Comparable<HardwareComponent> {
	protected static int count=0;
	protected int modelNumber;
	protected String brand;
	protected String model;
	protected String releaseDate;
	protected double recommendedPrice;
	
	public HardwareComponent(int modelNumber, String brand, String model, String releaseDate, double recommendedPrice) {
		count++;
		this.modelNumber = modelNumber;
		this.brand = brand;
		this.model = model;
		this.releaseDate = releaseDate;
		this.recommendedPrice = recommendedPrice;
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

	public int getModelNumber() {
		return modelNumber;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}
	
	public String getModelName() {
		return modelNumber + ". " + brand + " " + model;
	}

	public double getRecommendedPrice() {
		return recommendedPrice;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(modelNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HardwareComponent other = (HardwareComponent) obj;
		return modelNumber == other.modelNumber;
	}
	
	public int compareTo(HardwareComponent o) {
		String name1 = brand + " " + model;
		String name2 = o.getBrand() + " " + o.getModel();
		return name1.compareToIgnoreCase(name2);
	}

	@Override
	public String toString() {
		return "Brand: " +  brand + "\nRecommended Price: " + recommendedPrice + "\nRelease Date: " + releaseDate;
	}
	
	public String toFile() {
		return modelNumber + "::" 
			    + brand + "::" 
				+ model + "::" 
			    + releaseDate + "::" 
				+ recommendedPrice + "::";
	}
	
}
