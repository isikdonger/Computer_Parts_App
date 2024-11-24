import java.util.Arrays;

public class Notebook extends Computer {
	private String screenResolution;
	
	public Notebook(String brand, double devicePrice, CPU cpu, GPU gpu, RAM[] ram, SSD[] ssd, Motherboard motherboard,
			PowerSupply powerSupply, Case Case, String screenResolution) {
		super(brand, devicePrice, cpu, gpu, ram, ssd, motherboard, powerSupply, Case);
		this.screenResolution = screenResolution;
	}
	
	public String getScreenResolution() {
		return screenResolution;
	}

	@Override
	public String toString() {
		return "Notebook:\nscreenResolution: " + screenResolution + "\nbrand: " + brand + "\ndevicePrice: "
				+ devicePrice + "\ncpu: " + cpu + "\ngpu: " + gpu + "\nram: " + Arrays.toString(ram) + "\nssd: "
				+ Arrays.toString(ssd) + "\nmotherboard: " + motherboard + "\npowerSupply: " + powerSupply + "\nCase: "
				+ Case;
	}
}
