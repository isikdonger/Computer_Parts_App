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

	public CPU(double recommendedPrice, String releaseDate, String brand, double baseClockSpeed, double turboClockSpeed,
			int benchmarkScore, String architecture, int threadCount, int coreCount, int memoryChannel,
			int maxMemorySize, String ramCompatibility) {
		super(recommendedPrice, releaseDate, brand, baseClockSpeed, turboClockSpeed, benchmarkScore);
		this.architecture = architecture;
		this.threadCount = threadCount;
		this.coreCount = coreCount;
		this.memoryChannel = memoryChannel;
		this.maxMemorySize = maxMemorySize;
		this.ramCompatibility = ramCompatibility;
	}

	public Field[] getAllFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<>();
		while (clazz != null && clazz != Object.class) { // Stop at Object class
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = clazz.getSuperclass();
		}
		return fields.toArray(new Field[0]);
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
		return "CPU:\nthreadCount: " + threadCount + "\ncoreCount: " + coreCount + "\nmemoryChannel: " + memoryChannel
				+ "\nmaxMemorySize: " + maxMemorySize + "\nramCompatibility: " + ramCompatibility + "\nbaseClockSpeed: "
				+ baseClockSpeed + "\nturboClockSpeed: " + turboClockSpeed + "\nArchitecture: " + architecture
				+ "\nbenchmarkScore: " + benchmarkScore + "\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: "
				+ releaseDate + "\nbrand: " + brand;
	}
	
}
