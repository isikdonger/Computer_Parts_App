package HardwareComponent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CPU extends ProcessingUnit {
	protected String architecture;
	private int threadCount;
	private int coreCount;
	private int memoryChannel;
	private int maxMemorySize;
	private String ramCompatibility;

	public CPU(int modelNumber, String brand, String model, String releaseDate, double recommendedPrice,
			double baseClockSpeed, double turboClockSpeed, int benchmarkScore, String architecture, int threadCount,
			int coreCount, int memoryChannel, int maxMemorySize, String ramCompatibility) {
		super(modelNumber, brand, model, releaseDate, recommendedPrice, baseClockSpeed, turboClockSpeed,
				benchmarkScore);
		this.architecture = architecture;
		this.threadCount = threadCount;
		this.coreCount = coreCount;
		this.memoryChannel = memoryChannel;
		this.maxMemorySize = maxMemorySize;
		this.ramCompatibility = ramCompatibility;
	}

	public String getArchitecture() {
		return architecture;
	}

	public int getMemoryChannel() {
		return memoryChannel;
	}

	public void setMemoryChannel(int memoryChannel) {
		this.memoryChannel = memoryChannel;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public int getCoreCount() {
		return coreCount;
	}

	public int getMaxMemorySize() {
		return maxMemorySize;
	}

	public String getRamCompatibility() {
		return ramCompatibility;
	}

	@Override
	public String toString() {
		return "Component Type: CPU\n" + super.toString() + "\nArchitecture: " + architecture + "\nThread Count: " + threadCount + "\nCore Count: " + coreCount
				+ "\nMemory Channel: " + memoryChannel + "\nMax Memory Size: " + maxMemorySize + "\nRam Compatibility: "
				+ ramCompatibility + "\n\n";
	}
	
	public String toFile() {
		return "HardwareComponent::CPU::"
				+ super.toFile() 
				+ architecture + "::"
				+ threadCount + "::"
				+ coreCount + "::" 
				+ memoryChannel + "::" 
				+ maxMemorySize + "::" 
				+ ramCompatibility + "||";
	}
	
}
