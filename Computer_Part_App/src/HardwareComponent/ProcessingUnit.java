package HardwareComponent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public abstract class ProcessingUnit extends HardwareComponent {
	protected double baseClockSpeed;
	protected double turboClockSpeed;
	protected int benchmarkScore;
	
	public ProcessingUnit(int modelNumber, String brand, String model, String releaseDate, double recommendedPrice,
			double baseClockSpeed, double turboClockSpeed, int benchmarkScore) {
		super(modelNumber, brand, model, releaseDate, recommendedPrice);
		this.baseClockSpeed = baseClockSpeed;
		this.turboClockSpeed = turboClockSpeed;
		this.benchmarkScore = benchmarkScore;
	}

	public int getBenchmarkScore() {
		return benchmarkScore;
	}

	public double getBaseClockSpeed() {
		return baseClockSpeed;
	}

	public double getTurboClockSpeed() {
		return turboClockSpeed;
	}

	@Override
	public String toString() {
		return super.toString() + "Base Clock Speed: " + baseClockSpeed + "\nTurbo Clock Speed: " + turboClockSpeed
				+ "\nBenchmark Score: " + benchmarkScore;
	}
	
	public String toFile() {
		return super.toFile() 
				+ baseClockSpeed + "::"
				+ turboClockSpeed + "::"
				+ benchmarkScore + "::";
	}
}
