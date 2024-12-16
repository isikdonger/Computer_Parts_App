package HardwareComponent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;


import java.util.Arrays;
import java.util.List;

public class Case extends HardwareComponent {
	private String formFactor;
	private String material;
	private int durability;

	public Case(int modelNumber, String brand, String model, String releaseDate, double recommendedPrice,
			String formFactor, String material) {
		super(modelNumber, brand, model, releaseDate, recommendedPrice);
		this.formFactor = formFactor;
		this.material = material;
		this.durability = findDurability();
	}
	
	public String getMaterial() {
		return material;
	}
	public String getFormFactor() {
		return formFactor;
	}

	public int findDurability() {
		List<String> materialOrder = Arrays.asList("Plastic", "Glass", "Aluminum", "Steel");
		List<String> formOrder = Arrays.asList("Mini-ITX", "Micro-ATX", "E-ATX", "ATX");
		int index1 = materialOrder.indexOf("material");
		int index2 = formOrder.indexOf("formFactor");
		return index1 * index2;
	}

	@Override
	public String toString() {
		return "Component Type: Case\n" + super.toString() + "Form Factor: " + formFactor + "\nMaterial: " + material + "\nDurability: " + durability + "\n\n";
	}
	
	public String fileString() {
		return "HardwareComponent::Case::"
			    + super.fileString() 
				+ formFactor + "::"
				+ material + "||";
	}
}
