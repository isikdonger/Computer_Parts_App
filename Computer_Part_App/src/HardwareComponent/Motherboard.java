package HardwareComponent;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

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
	private static final String FILENAME = "cpuCompatibility.txt";
	
	public Motherboard(int modelNumber, String brand, String model, String releaseDate, double recommendedPrice,
			String chipset, int memorySlots, int maxMemory, int hdmiPorts, int displayPorts, String[] storageSlot,
			int ethernetCapacity, boolean wifi, boolean bluetooth, boolean soundCard, String[] ioPorts) {
		super(modelNumber, brand, model, releaseDate, recommendedPrice);
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

	public boolean cpuCompatibility(CPU cpu) throws FileNotFoundException {
		File file = new File(FILENAME);
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			String[] line = scanner.nextLine().split("::");
			String chipset = line[0];
			if (this.chipset.equalsIgnoreCase(chipset)) {
				for (int i=1;i<line.length;i++) {
					String architecture = line[i];
					if (cpu.getArchitecture().equalsIgnoreCase(architecture)) {
						return true;
					}
				}
			}
		}
		return false;
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

	@Override
	public String toString() {
		return super.toString() + "Component Type: Motherboard\nChipset: " + chipset + "\nMemory Slots: " + memorySlots + "\nMax Memory: " + maxMemory
				+ "\nHdmi Ports: " + hdmiPorts + "\nDisplay Ports: " + displayPorts + "\nStorage Slots: "
				+ Arrays.toString(storageSlot) + "\nEthernet Capacity: " + ethernetCapacity + "\nWifi: " + wifi
				+ "\nBluetooth: " + bluetooth + "\nSound Card: " + soundCard + "\nIO Ports: " + Arrays.toString(ioPorts) + "\n\n";
	}
	
	public String arrayWrite(String[] arr) {
		String outp = "";
		for (String s : arr) {
			outp += s + " ";
		}
		outp.trim();
		return null;
	}
	

	public String toFile() {
		return "HardwareComponent::Motherboard::"
				+ super.toFile() 
				+ chipset + "::"
				+ memorySlots + "::"
				+ maxMemory + "::"
				+ hdmiPorts + "::"
				+ displayPorts + "::"
				+ arrayWrite(storageSlot) + "::"
				+ ethernetCapacity + "::"
				+ wifi + "::"
				+ bluetooth + "::"
				+ soundCard + "::"
				+ arrayWrite(ioPorts) + "||";
	}
}
