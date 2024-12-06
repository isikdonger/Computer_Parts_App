package HardwareComponent;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class GPU extends ProcessingUnit {
	private String gpuName;
	private String gpuType;
	private int vram;
	private int psu;

	public GPU(double recommendedPrice, String releaseDate, String brand, double baseClockSpeed, double turboClockSpeed,
			int benchmarkScore, String gpuName, String gpuType, int vram, int psu) {
		super(recommendedPrice, releaseDate, brand, baseClockSpeed, turboClockSpeed, benchmarkScore);
		this.gpuName = gpuName;
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
		return "GPU:\ngpuType: " + gpuType + "\nvram: " + vram + "\npsu: " + psu + "\nbaseClockSpeed: " + baseClockSpeed
				+ "\nturboClockSpeed: " + turboClockSpeed + "\nName: " + gpuName + "\nbenchmarkScore: "
				+ benchmarkScore + "\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: " + releaseDate
				+ "\nbrand: " + brand;
	}
	
}
