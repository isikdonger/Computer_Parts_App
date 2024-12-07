package System_and_Interface;
import Computer.*;
import HardwareComponent.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HardwareSystem {
	private static ArrayList<Computer> Computers;
	private static ArrayList<HardwareComponent> HardwareComponents;
	private static final List<String> BRAND_ORDER = Arrays.asList("Nvidia", "AMD", "Intel", "Samsung", "Kingston", "G_SKILL", "Asus", "MSI", "ASROCK", "Gigabyte", "Seagate", "Lian Li", "EVGA", "Seasonic", "Cooler Master", "Corsair", "Thermaltake");
	private static final List<String> ARCHITECTURE_ORDER = Arrays.asList("Zen 5", "Raptor Lake", "Zen 4", "Alder Lake");
	private static final List<String> TECNHOLOGY_ORDER = Arrays.asList("DDR5", "DDR4");
	private static final List<String> EFFICIENCY_ORDER = Arrays.asList("80+ Titanium", "80+ Platinum", "80+ Gold", "80+ Silver", "80+ Bronze");
	private static final List<String> IOPORTS_ORDER = Arrays.asList("VGA", "HDMI", "Display Port", "USB 2.0", "USB 3.0", "USB 3.1", "USB 3.2");
	private static final Map<String, List<String>> ATTRIBUTE_ORDERS = Map.ofEntries(
		Map.entry("brand", BRAND_ORDER),
		Map.entry("architecture", ARCHITECTURE_ORDER),
		Map.entry("technology", TECNHOLOGY_ORDER),
		Map.entry("efficiency", EFFICIENCY_ORDER),
		Map.entry("ioPorts", IOPORTS_ORDDER)
	);
	private static final String FILENAME = "data.txt";
	
	public static boolean readFileData() throws FileNotFoundException {
		File file = new File(FILENAME);
		if (!file.exists()) {
			System.out.println("File does not exists!");
			return false;
		}

		Scanner input = new Scanner(file);
		
		while (input.hasNext()) {
			String[] data = input.nextLine().split("::");
			if (data[0].equalsIgnoreCase("HardwareComponent")) {
				HardwareComponent hardW = null;
				switch (data[1]) {
				case "CPU":
					hardW = new CPU(Double.parseDouble(data[2]), data[3], data[4], 
							Double.parseDouble(data[5]), Double.parseDouble(data[6]), 
							Integer.parseInt(data[7]), data[8], Integer.parseInt(data[9]), 
							Integer.parseInt(data[10]), Integer.parseInt(data[11]),
							Integer.parseInt(data[12]), data[13]);
					break;
				case "RAM":
					hardW = new RAM(Double.parseDouble(data[2]), data[3], data[4], 
							Integer.parseInt(data[5]), data[6], Integer.parseInt(data[7]));
					break;
				case "Motherboard":
					String[] arr = data[10].split("-");
					hardW = new Motherboard(Double.parseDouble(data[2]), data[3], data[4], 
							data[5], Integer.parseInt(data[6]), Integer.parseInt(data[7]),
							Integer.parseInt(data[8]), Integer.parseInt(data[9]), 
							arr, Integer.parseInt(data[11]), Boolean.parseBoolean(data[12]),
							Boolean.parseBoolean(data[13]), Boolean.parseBoolean(data[14]), 
							data);
					break;
				case "SSD":
					hardW = new SSD(Double.parseDouble(data[2]), data[3], data[4],
						Integer.parseInt(data[5]), Integer.parseInt(data[6]), 
						Integer.parseInt(data[7]), data[8], data[9]);
					break;
				case "GPU":
					hardW = new GPU(Double.parseDouble(data[2]), data[3], data[4],
							Double.parseDouble(data[5]), Double.parseDouble(data[6]), 
							Integer.parseInt(data[7]), data[8], data[9], 
							Integer.parseInt(data[10]), Integer.parseInt(data[11]));
					break;
				case "Case":
					hardW = new Case(Double.parseDouble(data[2]), data[3], data[4],
							data[5], data[6]);
					break;
				case "PowerSupply":
					hardW = new PowerSupply(Double.parseDouble(data[2]), data[3], data[4],
							Integer.parseInt(data[5]), data[6], data[7]);
				}
				HardwareComponents.add(hardW);
			}
			else {
				Computer comp = null;
				switch ((data[1])) {
				case "Laptop":
					break;
				case "PersonalComputer":
					comp = new PersonalComputer();
					break;
				case "Notebook":
				}
				Computers.add(comp);
			}
		}
		input.close();
		return true;
	}
	
	public static boolean addData() {
		return false;
	}
	
	public static Object compare(Object o1, Object o2) {
		return null;
	}
	
	public static ArrayList<Computer> getComputers() {
		return Computers;
	}
	
	public static ArrayList<PersonalComputer> getPersonalComputers() {
		ArrayList<PersonalComputer> computers = new ArrayList<PersonalComputer>();
		for (Computer computer: Computers) {
			if (computer instanceof PersonalComputer) {
				computers.add((PersonalComputer)computer);
			}
		}
		return computers;
	}
	
	public static ArrayList<Laptop> getLaptops() {
		ArrayList<Laptop> computers = new ArrayList<Laptop>();
		for (Computer computer: Computers) {
			if (computer instanceof Laptop) {
				computers.add((Laptop)computer);
			}
		}
		return computers;
	}
	
	public static ArrayList<Notebook> getNotebooks() {
		ArrayList<Notebook> computers = new ArrayList<Notebook>();
		for (Computer computer: Computers) {
			if (computer instanceof Notebook) {
				computers.add((Notebook)computer);
			}
		}
		return computers;
	}
	
	public static ArrayList<HardwareComponent> getHardwareComponents() {
		return HardwareComponents;
	}
	
	public static ArrayList<ProcessingUnit> getProcessingUnits() {
		ArrayList<ProcessingUnit> components = new ArrayList<ProcessingUnit>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof ProcessingUnit) {
				components.add((ProcessingUnit)component);
			}
		}
		return components;
	}
	
	public static ArrayList<CPU> getCPUs() {
		ArrayList<CPU> components = new ArrayList<CPU>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof CPU) {
				components.add((CPU)component);
			}
		}
		return components;
	}
	
	public static ArrayList<GPU> getGPUs() {
		ArrayList<GPU> components = new ArrayList<GPU>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof GPU) {
				components.add((GPU)component);
			}
		}
		return components;
	}
	
	public static ArrayList<MemoryUnit> getMemoryUnits() {
		ArrayList<MemoryUnit> components = new ArrayList<MemoryUnit>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof MemoryUnit) {
				components.add((MemoryUnit)component);
			}
		}
		return components;
	}
	
	public static ArrayList<RAM> getRAMs() {
		ArrayList<RAM> components = new ArrayList<RAM>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof RAM) {
				components.add((RAM)component);
			}
		}
		return components;
	}
	
	public static ArrayList<SSD> getSSDs() {
		ArrayList<SSD> components = new ArrayList<SSD>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof SSD) {
				components.add((SSD)component);
			}
		}
		return components;
	}
	
	public static ArrayList<Motherboard> getMotherboards() {
		ArrayList<Motherboard> components = new ArrayList<Motherboard>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof Motherboard) {
				components.add((Motherboard)component);
			}
		}
		return components;
	}
	
	public static ArrayList<PowerSupply> getPowerSupplys() {
		ArrayList<PowerSupply> components = new ArrayList<PowerSupply>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof PowerSupply) {
				components.add((PowerSupply)component);
			}
		}
		return components;
	}
	
	public static ArrayList<Case> getCases() {
		ArrayList<Case> components = new ArrayList<Case>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof Case) {
				components.add((Case)component);
			}
		}
		return components;
	}
	
	public static Object findHardwarePart(Object part) {
		if (part instanceof HardwareComponent) {
			for (HardwareComponent component: HardwareComponents) {
				if (component.equals(part)) {
					return component;
				}
			}
		}
		else if (part instanceof Computer) {
			for (Computer computer: Computers) {
				if (computer.equals(part)) {
					return computer;
				}
			}
		}
		return null;
	}
	
	public static boolean removeHardwarePart(Object part) {
		if (part instanceof HardwareComponent) {
			HardwareComponents.remove(part);
			return true;
		}
		else if (part instanceof Computer) {
			Computers.remove(part);
			return true;
		}
		return false;
	}
	
	public static String displayHardwarePart(HardwareComponent part) {
		return part.toString();
	}
	
	public static String displayHardwarePart(Computer part) {
		return part.toString();
	}
	
	public static <T> String displayHardwarePartList(ArrayList<T> partList) {
		String str = "";
		for (Object part: partList) {
			if (part instanceof HardwareComponent || part instanceof Computer) {
				str += part.toString();
			}
		}
		return str;
	}
}
