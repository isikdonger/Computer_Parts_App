package HardwareComponent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RAM extends MemoryUnit {
	private String technology;
	private int transferSpeed;

	public RAM(int modelNumber, String brand, String model, String releaseDate, double recommendedPrice, int capacity,
			String technology, int transferSpeed) {
		super(modelNumber, brand, model, releaseDate, recommendedPrice, capacity);
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
		return "Component Type: RAM\n" + super.toString() + "Technology: " + technology + "\nTransfer Speed: " + transferSpeed + "\n\n";
	}
	
}
