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

	public SSD(int modelNumber, String brand, String model, String releaseDate, double recommendedPrice, int capacity,
			int readSpeed, int writeSpeed, String form, String interfaceName) {
		super(modelNumber, brand, model, releaseDate, recommendedPrice, capacity);
		this.readSpeed = readSpeed;
		this.writeSpeed = writeSpeed;
		this.form = form;
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
		return "Component Type: SSD\n" + super.toString() + "Read Speed: " + readSpeed + "\nWrite Speed: " + writeSpeed + "\nForm Factor: " + form + "\nInterface Name: "
				+ interfaceName + "\n\n";
	}
	
	public String fileString() {
		return "HardwareComponent::SSD::"
				+ super.fileString() 
				+ readSpeed + "::"
				+ writeSpeed + "::"
				+ form + "::"
				+ interfaceName;
	}
	
}
