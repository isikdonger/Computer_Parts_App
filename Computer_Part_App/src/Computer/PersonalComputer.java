package Computer;
import HardwareComponent.*;
import Interface.HardwarePart;

import java.lang.reflect.Field;
import java.util.*;

import javax.swing.JFrame;

public class PersonalComputer extends Computer {
	private boolean monitorConnected;

	public PersonalComputer() {}

	public PersonalComputer(int modelNumber, String brand, String model, double devicePrice, CPU cpu, GPU gpu, RAM[] ram, SSD[] ssd,
			Motherboard motherboard, PowerSupply powerSupply, Case Case, boolean monitorConnected) {
		super(modelNumber, brand, model, devicePrice, cpu, gpu, ram, ssd, motherboard, powerSupply, Case);
		this.monitorConnected = monitorConnected;
	}
	
	public boolean isMonitorConnected() {
		return monitorConnected;
	}
	
	@Override
	public double buildComputer(CPU cpu, GPU gpu, RAM ram, int ramAmount, SSD ssd, int ssdAmount, Motherboard motherboard,
			PowerSupply powerSupply, Case Case, boolean monitor) {
		this.modelNumber = 42 + count;
		this.brand = "NoBrand";
		this.model = "PersonelBuild";
		this.cpu = cpu;
		this.gpu = gpu;
		RAM[] rams = new RAM[ramAmount];
		for (int i=0;i<ramAmount;i++) {
			rams[i]=ram;
		}
		this.ram = rams;
		SSD[] ssds = new SSD[ssdAmount];
		for (int i=0;i<ssdAmount;i++) {
			ssds[i]=ssd;
		}
		this.ssd = ssds;
		this.motherboard = motherboard;
		this.powerSupply = powerSupply;
		this.Case = Case;
		this.monitorConnected = monitor;
		devicePrice = cpu.getRecommendedPrice() + gpu.getRecommendedPrice() + ram.getRecommendedPrice() * ramAmount + ssd.getRecommendedPrice() * ssdAmount +
				motherboard.getRecommendedPrice() + powerSupply.getRecommendedPrice() + Case.getRecommendedPrice();
		return devicePrice;
	}
	
	private String arrayWrite(Object[] arr) {
		String outp = "[";
		
		for (Object o : arr) {
			if (o instanceof RAM) {
				outp += ((RAM) o).toFile() + "<>";
			}
			else if (o instanceof SSD) {
				outp += ((SSD) o).toFile() + "<>";
			}
		}
		outp = outp.substring(0, outp.length()-2) + "]||";
		
		return outp;
	}
	
	public String toFile() {
		return "Computer::PersonalComputer::"
				+ modelNumber + "::" 
			    + brand + "::" 
				+ model + "::" 
				+ devicePrice + "||"
				+ cpu.toFile()
				+ gpu.toFile()
				+ arrayWrite(ram)
				+ arrayWrite(ssd)
				+ motherboard.toFile()
				+ powerSupply.toFile()
				+ Case.toFile()
				+ monitorConnected;
	}

	@Override
	public String toString() {
		return "Computer Type: Personel Computer\n" + super.toString() + "Monitor Status: " + monitorConnected + "\n\n";
	}
}
