package Computer;
import System_and_Interface.HardwarePart;
import HardwareComponent.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

public abstract class Computer implements HardwarePart {
	protected static int count=0;
	protected String brand;
	protected double devicePrice;
	protected CPU cpu;
	protected GPU gpu;
	protected RAM[] ram;
	protected SSD[] ssd;
	protected Motherboard motherboard;
	protected PowerSupply powerSupply;
	protected Case Case;
	
	public Computer() {
		count++;
	}
	
	public Computer(String brand, double devicePrice, CPU cpu, GPU gpu, RAM[] ram, SSD[] ssd, Motherboard motherboard,
			PowerSupply powerSupply, Case Case) {
		this();
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
	
	@Override
	public boolean isGetter(Method method) {
		// TODO Auto-generated method stub
		return method.getName().endsWith("get");
	}

	@Override
	public <T> Map<String, T> getSuperClassValues() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public <T> Map<String, T>  getValues() {
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
	
	public abstract Computer buildComputer();
	
	public static int getCount() {
		return count;
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

	@Override
	public String toString() {
		return "Computer:\nbrand: " + brand + "\ndevicePrice: " + devicePrice + "\ncpu: " + cpu + "\ngpu: " + gpu
				+ "\nram: " + Arrays.toString(ram) + "\nssd: " + Arrays.toString(ssd) + "\nmotherboard: " + motherboard
				+ "\npowerSupply: " + powerSupply + "\nCase: " + Case;
	}
}
