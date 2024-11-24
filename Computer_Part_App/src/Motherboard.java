import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class Motherboard extends HardwareComponent {
	private String chipset;
	private int memorySlots;
	private int maxMemory;
	private int hdmiPorts;
	private int displayPorts;
	private String[] storageSlot;
	private int ethernetCapacity;
	private boolean wifi;
	private boolean bluetooth;
	private boolean soundCard;
	private String[] ioPorts;

	public Motherboard(double recommendedPrice, String releaseDate, String brand, String chipset, int memorySlots,
			int maxMemory, int hdmiPorts, int displayPorts, String[] storageSlot, int ethernetCapacity, boolean wifi,
			boolean bluetooth, boolean soundCard, String[] ioPorts) {
		super(recommendedPrice, releaseDate, brand);
		this.chipset = chipset;
		this.memorySlots = memorySlots;
		this.maxMemory = maxMemory;
		this.hdmiPorts = hdmiPorts;
		this.displayPorts = displayPorts;
		this.storageSlot = storageSlot;
		this.ethernetCapacity = ethernetCapacity;
		this.wifi = wifi;
		this.bluetooth = bluetooth;
		this.soundCard = soundCard;
		this.ioPorts = ioPorts;
	}
	
	public <T> ArrayList<T> getSuperCLassMethods() {
		ArrayList<T> values = new ArrayList<T>();
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

	public String getChipset() {
		return chipset;
	}

	public int getMemorySlots() {
		return memorySlots;
	}

	public int getMaxMemory() {
		return maxMemory;
	}

	public int getHdmiPorts() {
		return hdmiPorts;
	}

	public int getDisplayPorts() {
		return displayPorts;
	}

	public String[] getStorageSlot() {
		return storageSlot;
	}

	public int getEthernetCapacity() {
		return ethernetCapacity;
	}

	public boolean isWifi() {
		return wifi;
	}

	public boolean isBluetooth() {
		return bluetooth;
	}

	public boolean isSoundCard() {
		return soundCard;
	}

	public String[] getIoPorts() {
		return ioPorts;
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
		return "Motherboard:\nchipset: " + chipset + "\nmemorySlots: " + memorySlots + "\nmaxMemory: " + maxMemory
				+ "\nhdmiPorts: " + hdmiPorts + "\ndisplayPorts: " + displayPorts + "\nstorageSlot: "
				+ Arrays.toString(storageSlot) + "\nethernetCapacity: " + ethernetCapacity + "\nwifi: " + wifi
				+ "\nbluetooth: " + bluetooth + "\nsoundCard: " + soundCard + "\nioPorts: " + Arrays.toString(ioPorts)
				+ "\nrecommendedPrice: " + recommendedPrice + "\nreleaseDate: " + releaseDate + "\nbrand: " + brand;
	}
}
