import java.lang.reflect.Method;
import java.util.ArrayList;

public class Case extends HardwareComponent {
	private String formFactor;
	private String material;
	private String durability;
	
	public Case(double recommendedPrice, String releaseDate, String brand, String formFactor, String material,
			String durability) {
		super(recommendedPrice, releaseDate, brand);
		this.formFactor = formFactor;
		this.material = material;
		this.durability = findDurability();
	}
	
	public Case(double recommendedPrice, String releaseDate, String brand) {
		super(recommendedPrice, releaseDate, brand);
		// TODO Auto-generated constructor stub
	}
	
	public String getFormFactor() {
		return formFactor;
	}

	public String findDurability() {
		return "strong";
	}

	@Override
	public String toString() {
		return "Case:\nformFactor: " + formFactor + "\nmaterial: " + material + "\ndurability: " + durability
				+ "\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: " + releaseDate + "\nbrand: " + brand;
	}
}
