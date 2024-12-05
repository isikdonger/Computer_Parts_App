package HardwareComponent;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class CPU extends ProcessingUnit {
	private int threadCount;
	private int coreCount;
	private int memoryChannel;
	private int maxMemorySize;
	private String ramCompatibility;

	public CPU(double recommendedPrice, String releaseDate, String brand, double baseClockSpeed, double turboClockSpeed,
			String architecture, int benchmarkScore, int threadCount, int coreCount,
			int memoryChannel, int maxMemorySize, String ramCompatibility) {
		super(recommendedPrice, releaseDate, brand, baseClockSpeed,turboClockSpeed, architecture, benchmarkScore);
		this.threadCount = threadCount;
		this.coreCount = coreCount;
		this.memoryChannel = memoryChannel;
		this.maxMemorySize = maxMemorySize;
		this.ramCompatibility = ramCompatibility;
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
		return "CPU:\nthreadCount: " + threadCount + "\ncoreCount: " + coreCount + "\nmemoryChannel: " + memoryChannel
				+ "\nmaxMemorySize: " + maxMemorySize + "\nramCompatibility: " + ramCompatibility + "\nbaseClockSpeed: "
				+ baseClockSpeed + "\nturboClockSpeed: " + turboClockSpeed + "\narchitecture: " + architecture
				+ "\nbenchmarkScore: " + benchmarkScore + "\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: "
				+ releaseDate + "\nbrand: " + brand;
	}
	
}
