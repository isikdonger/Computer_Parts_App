package Computer;
import HardwareComponent.*;

import java.lang.reflect.Field;
import java.util.*;

import javax.swing.JFrame;

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
	public double buildComputer(CPU cpu, GPU gpu, RAM ram, int ramAmount, SSD ssd, int ssdAmount, Motherboard motherboard,
			PowerSupply powerSupply, Case Case, boolean monitor) {
		return 0.0;
	}

	@Override
	public String toString() {
		return "Computer Type: Notebook\n" + super.toString() + "Screen Resolution: " + screenResolution + "\n\n";
	}
}
