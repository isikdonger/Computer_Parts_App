package Computer;
import HardwareComponent.*;

import java.lang.reflect.Field;
import java.util.*;

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

	@Override
	public String toFile() {
		return "Computer::PersonelComputer::"+ devicePrice + "::" + brand +
				"||" + cpu + "||" + gpu + "||" + ram + "||" + ssd + "||" + motherboard + "||" + powerSupply +
				"||" + Case + "||" + monitorConnected + "\n";
	}
}
