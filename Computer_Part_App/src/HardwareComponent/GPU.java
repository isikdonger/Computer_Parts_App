package HardwareComponent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GPU extends ProcessingUnit {
	private String gpuType;
	private int vram;
	private int psu;

	public GPU(int modelNumber, String brand, String model, String releaseDate, double recommendedPrice,
			double baseClockSpeed, double turboClockSpeed, int benchmarkScore, String gpuType, int vram,
			int psu) {
		super(modelNumber, brand, model, releaseDate, recommendedPrice, baseClockSpeed, turboClockSpeed,
				benchmarkScore);
		this.gpuType = gpuType;
		this.vram = vram;
		this.psu = psu;
	}

	public String getGpuType() {
		return gpuType;
	}

	public int getVram() {
		return vram;
	}

	public int getPsu() {
		return psu;
	}

	@Override
	public String toString() {
		return "Component Type: GPU\n" + super.toString() + "Gpu Type: " + gpuType + "\nVram: " + vram + "\nPsu: " + psu + "\n\n";
	}
	
	public String toFile() {
		return "HardwareComponent::GPU::"
				+ super.toFile() 
				+ gpuType + "::"
				+ vram + "::"
				+ psu + "||";
	}
}
