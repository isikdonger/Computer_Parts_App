package Computer;
import HardwareComponent.*;

import java.lang.reflect.Field;
import java.util.*;

public class Notebook extends Computer {
	private String screenResolution;
	
	public Notebook() {}
	
	public Notebook(String brand, double devicePrice, CPU cpu, GPU gpu, RAM[] ram, SSD[] ssd, Motherboard motherboard,
			PowerSupply powerSupply, Case Case, String screenResolution) {
		super(brand, devicePrice, cpu, gpu, ram, ssd, motherboard, powerSupply, Case);
		this.screenResolution = screenResolution;
	}

	// For test purposes
	public Notebook(String brand, double devicePrice, CPU cpu) {
		this.brand = brand;
		this.devicePrice = devicePrice;
		this.cpu = cpu;
	}
	// End Test

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
	
	public String getScreenResolution() {
		return screenResolution;
	}
	
	@Override
	public Computer buildComputer() {
		return null;
	}

	@Override
	public String toString() {
		return "Notebook:\nscreenResolution: " + screenResolution + "\nbrand: " + brand + "\ndevicePrice: "
				+ devicePrice + "\ncpu: " + cpu + "\ngpu: " + gpu + "\nram: " + Arrays.toString(ram) + "\nssd: "
				+ Arrays.toString(ssd) + "\nmotherboard: " + motherboard + "\npowerSupply: " + powerSupply + "\nCase: "
				+ Case;
	}
}
