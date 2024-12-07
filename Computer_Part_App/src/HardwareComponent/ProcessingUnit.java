package HardwareComponent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public abstract class ProcessingUnit extends HardwareComponent {
	protected double baseClockSpeed;
	protected double turboClockSpeed;
	protected int benchmarkScore;
	
	public ProcessingUnit(double recommendedPrice, String releaseDate, String brand, double baseClockSpeed, double turboClockSpeed, int benchmarkScore) {
		super(recommendedPrice, releaseDate, brand);
		this.baseClockSpeed = baseClockSpeed;
		this.turboClockSpeed = turboClockSpeed;
		this.benchmarkScore = benchmarkScore;
	}
	
	@Override
	public <T> Map<String, T> getSuperClassValues() {
		return super.getValues();
	}

	public int getBenchmarkScore() {
		return benchmarkScore;
	}
	
	@Override
	public String toString() {
		return "ProcessingUnit:\nbaseClockSpeed: " + baseClockSpeed + "\nturboClockSpeed: " + turboClockSpeed
				+ "\narchitecture: " + "\nbenchmarkScore: " + benchmarkScore + "\nrecommendedPrice: "
				+ recommendedPrice + "\nreleaseDate: " + releaseDate + "\nbrand: " + brand;
	}
}