import java.lang.reflect.Method;
import java.util.ArrayList;

public class ProcessingUnit extends HardwareComponent {
	protected double baseClockSpeed;
	protected double turboClockSpeed;
	protected String architecture;
	protected int benchmarkScore;
	
	public ProcessingUnit(double recommendedPrice, String releaseDate, String brand, double baseClockSpeed, double turboClockSpeed, String architecture, int benchmarkScore) {
		super(recommendedPrice, releaseDate, brand);
		this.baseClockSpeed = baseClockSpeed;
		this.turboClockSpeed = turboClockSpeed;
		this.architecture = architecture;
		this.benchmarkScore = benchmarkScore;
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

	public String getArchitecture() {
		return architecture;
	}

	public int getBenchmarkScore() {
		return benchmarkScore;
	}

	@Override
	public String toString() {
		return "ProcessingUnit:\nbaseClockSpeed: " + baseClockSpeed + "\nturboClockSpeed: " + turboClockSpeed
				+ "\narchitecture: " + architecture + "\nbenchmarkScore: " + benchmarkScore + "\nrecommendedPrice: "
				+ recommendedPrice + "\nreleaseDate: " + releaseDate + "\nbrand: " + brand;
	}
	
}
