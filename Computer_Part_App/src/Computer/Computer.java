package Computer;
import HardwareComponent.*;
import Interface.HardwarePart;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Computer implements HardwarePart, Comparable<Computer> {
	protected static int count=0;
	protected int modelNumber;
	protected String brand;
	protected String model;
	protected double devicePrice;
	protected CPU cpu;
	protected GPU gpu;
	protected RAM[] ram;
	protected SSD[] ssd;
	protected Motherboard motherboard;
	protected PowerSupply powerSupply;
	protected Case Case;
	
	public Computer() {}
	
	public Computer(int modelNumber, String brand, String model, double devicePrice, CPU cpu, GPU gpu, RAM[] ram, SSD[] ssd, Motherboard motherboard,
			PowerSupply powerSupply, Case Case) {
		this.brand = brand;
		this.devicePrice = devicePrice;
		this.cpu = cpu;
		this.gpu = gpu;
		this.ram = ram;
		this.ssd = ssd;
		this.motherboard = motherboard;
		this.powerSupply = powerSupply;
		this.Case = Case;
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
		return method.getName().startsWith("get") && method.getParameterCount() == 0;
	}

	// Returns a map of values from the current class, including the superclass
	public <T> Map<String, T> getValues() {
		Map<String, T> values = new HashMap<>(); // Initialize the map

		Method[] methods = this.getClass().getSuperclass().getDeclaredMethods(); // Get all declared methods
		for (Method method : methods) {
			if (isGetter(method)) { // Check if it's a getter
				try {
					// Skip recursive methods
					if (method.getName().equals("getValues") || method.getName().equals("getCount") || method.getName().equals("getSuperClassValues")
							|| method.getName().equals("getValue") 
							|| method.getName().equals("getRamCapacity") 
							|| method.getName().equals("getSsdCapacity")) {
						continue;
					}
					T value = (T) method.invoke(this); // Invoke the getter
					values.put(method.getName().substring(3), value); // Use the property name (without "get")
				} catch (Exception e) {
					e.printStackTrace(); // Log any errors
				}
			}
		}
		return values;
	}

	public abstract Computer buildComputer();
	public abstract String toFile();
	
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

	public double getDevicePrice() {
		return devicePrice;
	}

	public CPU getCpu() {
		return cpu;
	}

	public GPU getGpu() {
		return gpu;
	}

	public RAM[] getRam() {
		return ram;
	}

	public SSD[] getSsd() {
		return ssd;
	}

	public Motherboard getMotherboard() {
		return motherboard;
	}

	public PowerSupply getPowerSupply() {
		return powerSupply;
	}

	public Case getCase() {
		return Case;
	}
	
	public int getRamCapacity() {
		int total = 0;
		for (RAM r: ram) {
			total += r.getCapacity();
		}
		return total;
	}
	
	public int getSsdCapacity() {
		int total = 0;
		for (SSD s: ssd) {
			total += s.getCapacity();
		}
		return total;
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
		Computer other = (Computer) obj;
		return modelNumber == other.modelNumber;
	}
	
	@Override
	public int compareTo(Computer o) {
		String name1 = brand + " " + model;
		String name2 = o.getBrand() + " " + o.getModel();
		return name1.compareToIgnoreCase(name2);
	}

	@Override
	public String toString() {
		return "Computer:\nbrand: " + brand + "\ndevicePrice: " + devicePrice + "\ncpu: " + cpu + "\ngpu: " + gpu
				+ "\nram: " + Arrays.toString(ram) + "\nssd: " + Arrays.toString(ssd) + "\nmotherboard: " + motherboard
				+ "\npowerSupply: " + powerSupply + "\nCase: " + Case + "\n";
	}
}
