import java.lang.reflect.Method;
import java.util.ArrayList;

public class SSD extends MemoryUnit {
	private int readSpeed;
	private int writeSpeed;
	private String formFactor;
	private String interfaceName;
	
	public SSD(double recommendedPrice, String releaseDate, String brand, int capacity, int readSpeed, int writeSpeed,
			String formFactor, String interfaceName) {
		super(recommendedPrice, releaseDate, brand, capacity);
		this.readSpeed = readSpeed;
		this.writeSpeed = writeSpeed;
		this.formFactor = formFactor;
		this.interfaceName = interfaceName;
	}

	public int getReadSpeed() {
		return readSpeed;
	}

	public int getWriteSpeed() {
		return writeSpeed;
	}

	public String getFormFactor() {
		return formFactor;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	@Override
	public String toString() {
		return "SSD:\nreadSpeed: " + readSpeed + "\nwriteSpeed: " + writeSpeed + "\nformFactor: " + formFactor
				+ "\ninterfaceName: " + interfaceName + "\ncapacity: " + capacity + "\nrecommendedPrice: "
				+ recommendedPrice + "\nreleaseDate: " + releaseDate + "\nbrand: " + brand;
	}
	
}
