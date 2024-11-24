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
	
	public <T> ArrayList<T> getSuperCLassMethods() {
		ArrayList<T> values = new ArrayList();
		Method[] methods = this.getClass().getSuperclass().getDeclaredMethods();
		for (Method method: methods) {
			if(isGetter(method)) {
				try {
					T value = (T)method.invoke(this);
					values.add(value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return values;
	}

	public String getTechnology() {
		return technology;
	}

	public int getTransferSpeed() {
		return transferSpeed;
	}
	
	public <T> ArrayList<T> getMethods() {
		ArrayList<T> values = new ArrayList<T>();
		Method[] methods = this.getClass().getDeclaredMethods();
		for (Method method: methods) {
			if(isGetter(method)) {
				try {
					T value = (T)method.invoke(this);
					values.add(value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return values;
	}

	@Override
	public String toString() {
		return "RAM:\ntechnology: " + technology + "\ntransferSpeed: " + transferSpeed + "\ncapacity: " + capacity
				+ "\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: " + releaseDate + "\nbrand: " + brand;
	}
	
}
