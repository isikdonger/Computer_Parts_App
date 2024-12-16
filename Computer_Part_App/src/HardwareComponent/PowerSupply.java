package HardwareComponent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PowerSupply extends HardwareComponent {
	private int wattage;
	private String formFactor;
	private String efficiencyTier;

	public PowerSupply(int modelNumber, String brand, String model, String releaseDate, double recommendedPrice,
			int wattage, String formFactor, String efficiencyTier) {
		super(modelNumber, brand, model, releaseDate, recommendedPrice);
		this.wattage = wattage;
		this.formFactor = formFactor;
		this.efficiencyTier = efficiencyTier;
	}

	public int getWattage() {
		return wattage;
	}

	public String getFormFactor() {
		return formFactor;
	}

	public String getEfficiencyTier() {
		return efficiencyTier;
	}

	@Override
	public String toString() {
		return super.toString() + "Component Type: Power Supply\nWattage: " + wattage + "\nForm Factor: " + formFactor + "\nEfficiency Tier: "
				+ efficiencyTier + "\n\n";
	}
	
	public String fileString() {
		return "HardwareComponent::PowerSupply::"
				+ super.fileString() 
				+ wattage + "::"
				+ formFactor + "::"
				+ efficiencyTier + "||";
	}
}
