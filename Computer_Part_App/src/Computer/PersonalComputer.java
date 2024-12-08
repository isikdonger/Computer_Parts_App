package Computer;
import HardwareComponent.*;

import java.lang.reflect.Field;
import java.util.*;

public class PersonalComputer extends Computer {
	private boolean monitorConnected;

	public PersonalComputer() {}

	public PersonalComputer(String brand, double devicePrice, CPU cpu, GPU gpu, RAM[] ram, SSD[] ssd,
			Motherboard motherboard, PowerSupply powerSupply, Case Case, boolean monitorConnected) {
		super(brand, devicePrice, cpu, gpu, ram, ssd, motherboard, powerSupply, Case);
		this.monitorConnected = monitorConnected;
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
	public <T> Map<String, T> getSuperClassValues() {
		return super.getValues();
	}

	public boolean isMonitorConnected() {
		return monitorConnected;
	}
	
	@Override
	public Computer buildComputer() {
		brand = null;
		devicePrice = 0.0;
		cpu = null;
		gpu = null;
		ram = null;
		ssd = null;
		motherboard = null;
		powerSupply = null;
		Case = null;
		return this;
	}

	@Override
	public String toString() {
		return "PersonalComputer:\nmonitorConnected: " + monitorConnected + "\nbrand: " + brand + "\ndevicePrice: "
				+ devicePrice + "\ncpu: " + cpu + "\ngpu: " + gpu + "\nram: " + Arrays.toString(ram) + "\nssd: "
				+ Arrays.toString(ssd) + "\nmotherboard: " + motherboard + "\npowerSupply: " + powerSupply + "\nCase: "
				+ Case;
	}
}
