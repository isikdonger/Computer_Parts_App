import java.lang.reflect.Method;
import java.util.ArrayList;

public class RAM extends MemoryUnit {
	private String technology;
	private int transferSpeed;

	public RAM(double recommendedPrice, String releaseDate, String brand, int capacity, String technology,
			int transferSpeed) {
		super(recommendedPrice, releaseDate, brand, capacity);
		this.technology = technology;
		this.transferSpeed = transferSpeed;
	}

	public String getTechnology() {
		return technology;
	}

	public int getTransferSpeed() {
		return transferSpeed;
	}

	@Override
	public String toString() {
		return "RAM:\ntechnology: " + technology + "\ntransferSpeed: " + transferSpeed + "\ncapacity: " + capacity
				+ "\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: " + releaseDate + "\nbrand: " + brand;
	}
	
}
