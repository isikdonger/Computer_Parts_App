package Computer;
import HardwareComponent.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class Laptop extends Computer {
	private String screenResolution;

	public Laptop() {}

	public Laptop(String brand, double devicePrice, CPU cpu, GPU gpu, RAM[] ram, SSD[] ssd, Motherboard motherboard,
			PowerSupply powerSupply, Case Case, String screenResolution) {
		super(brand, devicePrice, cpu, gpu, ram, ssd, motherboard, powerSupply, Case);
		this.screenResolution = screenResolution;
	}

	public Field[] getAllFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<>();
		while (clazz != null && clazz != Object.class) { // Stop at Object class
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = clazz.getSuperclass();
		}
		return fields.toArray(new Field[0]);
	}


	// For test purposes
	public Laptop(String brand, double devicePrice, CPU cpu) {
		this.brand = brand;
		this.devicePrice = devicePrice;
		this.cpu = cpu;
	}
	// End Test
	@Override
	public <T> Map<String, T> getSuperClassValues() {
		// Call the `getValues()` implementation of the `Computer` class directly
		return super.getValues();
	}


	public String getScreenResolution() {
		return screenResolution;
	}
	
	@Override
	public Computer buildComputer() {
		return null;
	}
	
	@Override
	public String toString() {
		return "Laptop:\nscreenResolution: " + screenResolution + "\nbrand: " + brand + "\ndevicePrice: " + devicePrice
				+ "\ncpu: " + cpu + "\ngpu: " + gpu + "\nram: " + Arrays.toString(ram) + "\nssd: "
				+ Arrays.toString(ssd) + "\nmotherboard: " + motherboard + "\npowerSupply: " + powerSupply + "\nCase: "
				+ Case;
	}
}
