package Computer;
import HardwareComponent.*;
import java.util.Arrays;
import java.util.Map;

public class PersonelComputer extends Computer {
	private boolean monitorConnected;

	public PersonelComputer() {}

	public PersonelComputer(String brand, double devicePrice, CPU cpu, GPU gpu, RAM[] ram, SSD[] ssd,
			Motherboard motherboard, PowerSupply powerSupply, Case Case, boolean monitorConnected) {
		super(brand, devicePrice, cpu, gpu, ram, ssd, motherboard, powerSupply, Case);
		this.monitorConnected = monitorConnected;
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
		return "PersonelComputer:\nmonitorConnected: " + monitorConnected + "\nbrand: " + brand + "\ndevicePrice: "
				+ devicePrice + "\ncpu: " + cpu + "\ngpu: " + gpu + "\nram: " + Arrays.toString(ram) + "\nssd: "
				+ Arrays.toString(ssd) + "\nmotherboard: " + motherboard + "\npowerSupply: " + powerSupply + "\nCase: "
				+ Case;
	}
}
