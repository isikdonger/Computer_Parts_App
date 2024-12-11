package Computer;
import HardwareComponent.*;

import java.lang.reflect.Field;
import java.util.*;

public class Notebook extends Computer {
	private String screenResolution;
	
	public Notebook() {}
	
	public Notebook(int modelNumber, String brand, String model, double devicePrice, CPU cpu, GPU gpu, RAM[] ram, SSD[] ssd, Motherboard motherboard,
			PowerSupply powerSupply, Case Case, String screenResolution) {
		super(modelNumber, brand, model, devicePrice, cpu, gpu, ram, ssd, motherboard, powerSupply, Case);
		this.screenResolution = screenResolution;
	}

	// For test purposes
	/*
	public Notebook(String brand, double devicePrice, CPU cpu) {
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
		return "Notebook:\nscreenResolution: " + screenResolution + "\nbrand: " + brand + "\ndevicePrice: "
				+ devicePrice + "\ncpu: " + cpu + "\ngpu: " + gpu + "\nram: " + Arrays.toString(ram) + "\nssd: "
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
