import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Computer {
	protected String brand;
	protected double devicePrice;
	protected CPU cpu;
	protected GPU gpu;
	protected RAM[] ram;
	protected SSD[] ssd;
	protected Motherboard motherboard;
	protected PowerSupply powerSupply;
	protected Case Case;
	
	public Computer(String brand, double devicePrice, CPU cpu, GPU gpu, RAM[] ram, SSD[] ssd, Motherboard motherboard,
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

	public String getBrand() {
		return brand;
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
		return "Computer:\nbrand: " + brand + "\ndevicePrice: " + devicePrice + "\ncpu: " + cpu + "\ngpu: " + gpu
				+ "\nram: " + Arrays.toString(ram) + "\nssd: " + Arrays.toString(ssd) + "\nmotherboard: " + motherboard
				+ "\npowerSupply: " + powerSupply + "\nCase: " + Case;
	}
}
