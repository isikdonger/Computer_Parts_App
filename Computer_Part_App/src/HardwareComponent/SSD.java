package HardwareComponent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SSD extends MemoryUnit {
	private int readSpeed;
	private int writeSpeed;
	private String form;
	private String interfaceName;
	
	public SSD(double recommendedPrice, String releaseDate, String brand, int capacity, int readSpeed, int writeSpeed,
			String formFactor, String interfaceName) {
		super(recommendedPrice, releaseDate, brand, capacity);
		this.readSpeed = readSpeed;
		this.writeSpeed = writeSpeed;
		this.form = formFactor;
		this.interfaceName = interfaceName;
	}

	public int getReadSpeed() {
		return readSpeed;
	}

	public int getWriteSpeed() {
		return writeSpeed;
	}

	public String getFormFactor() {
		return form;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	@Override
	public String toString() {
		return "SSD:\nreadSpeed: " + readSpeed + "\nwriteSpeed: " + writeSpeed + "\nformFactor: " + form
				+ "\ninterfaceName: " + interfaceName + "\ncapacity: " + capacity + "\nrecommendedPrice: "
				+ recommendedPrice + "\nreleaseDate: " + releaseDate + "\nbrand: " + brand;
	}
	
}
