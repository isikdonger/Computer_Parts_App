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
	
	public <T> ArrayList<T> getSuperCLassMethods() {
		ArrayList<T> values = new ArrayList<T>();
		Method[] methods = this.getClass().getSuperclass().getDeclaredMethods();
		for (Method method: methods) {
			if(isGetter(method)) {
				try {
					T value = (T)method.invoke(this);
					values.add(value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return values;
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
	
	public <T> ArrayList<T> getMethods() {
		ArrayList<T> values = new ArrayList<T>();
		Method[] methods = this.getClass().getDeclaredMethods();
		for (Method method: methods) {
			if(isGetter(method)) {
				try {
					T value = (T)method.invoke(this);
					values.add(value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return values;
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
