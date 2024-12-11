package Computer;
import HardwareComponent.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class Laptop extends Computer {
	private String screenResolution;

	public Laptop() {}

	public Laptop(int modelNumber, String brand, String model, double devicePrice, CPU cpu, GPU gpu, RAM[] ram, SSD[] ssd, Motherboard motherboard,
			PowerSupply powerSupply, Case Case, String screenResolution) {
		super(modelNumber, brand, model, devicePrice, cpu, gpu, ram, ssd, motherboard, powerSupply, Case);
		this.screenResolution = screenResolution;
	}

	// For test purposes
	/*
	public Laptop(String brand, double devicePrice, CPU cpu) {
		this.brand = brand;
		this.devicePrice = devicePrice;
		this.cpu = cpu;
	}
	*/
	// End Test


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

	@Override
	public String toFile() {
		return "Computer::Laptop::"+ devicePrice + "::" + brand +
				"||" + cpu + "||" + gpu + "||" + ram + "||" + ssd + "||" + motherboard + "||" + powerSupply +
				"||" + Case + "||" + screenResolution + "\n";
	}
}
