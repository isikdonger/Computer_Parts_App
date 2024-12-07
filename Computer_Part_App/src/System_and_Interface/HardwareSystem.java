package System_and_Interface;
import Computer.*;
import HardwareComponent.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	private static final List<String> TECHNOLOGY_ORDER = Arrays.asList("DDR5", "DDR4", "NVMe", "SATA");
	private static final List<String> EFFICIENCY_ORDER = Arrays.asList("80+ Titanium", "80+ Platinum", "80+ Gold", "80+ Silver", "80+ Bronze");
	private static final List<String> IOPORTS_ORDER = Arrays.asList("VGA", "HDMI", "Display Port", "USB 2.0", "USB 3.0", "USB 3.1", "USB 3.2");
	private static final List<String> MATERIAL_ORDER = Arrays.asList("Steel", "Aluminium", "Glass", "Plastic");
	private static final Map<String, List<String>> ATTRIBUTE_ORDERS = Map.ofEntries(
		Map.entry("brand", BRAND_ORDER),
		Map.entry("architecture", ARCHITECTURE_ORDER),
		Map.entry("technology", TECHNOLOGY_ORDER),
		Map.entry("efficiency", EFFICIENCY_ORDER),
		Map.entry("ioPorts", IOPORTS_ORDER),
			Map.entry("materials", MATERIAL_ORDER)
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

	// Compare method for computers and components, gets two HardwarePart objects
	public static HardwarePart compare(HardwarePart p1, HardwarePart p2) {
		// Base case for type matching, p1 and p2 object should be instantiated from the same class
		if (!(p1.getClass().equals(p2.getClass()))) {
			System.out.println("Type mismatch");
			return null;
		}

		// Comparison system for computers is by point,
		// which is incremented by one if one component is better than the other
		if (p1 instanceof Computer) {
			int pointsForComp1 = 0, pointsForComp2 = 0;

			Map<String, HardwareComponent> componentsOfP1 = p1.getValues(); // Put the components of p1 in a Map Structure
			Map<String, HardwareComponent> componentsOfP2 = p2.getValues(); // Put the components of p2 in a Map Structure

			for (Map.Entry<String, HardwareComponent> entry : componentsOfP1.entrySet()) {
				HardwareComponent comp1 = entry.getValue();
				HardwareComponent comp2 = componentsOfP2.get(entry.getKey());

				if (compareComponents(comp1, comp2, comp1.getClass().getDeclaredFields())) {
					pointsForComp1++;
				} else {
					pointsForComp2++;
				}
			}

			return pointsForComp1 > pointsForComp2 ? p1 : p2;
		}
		else if (p1 instanceof HardwareComponent) {
			return compareComponents((HardwareComponent)p1, (HardwareComponent)p2, p1.getClass().getDeclaredFields()) ? p1 : p2;
		}
		else { // If the type is neither Computer nor HardwareComponent
			System.out.println("Type not allowed for comparing.");
			return null;
		}
	}

	public static boolean compareComponents(HardwareComponent c1, HardwareComponent c2, Field[] fields) {
		int points1 = 0;
		int points2 = 0;

		// Iterate through all fields and increment points of CPUs accordingly
		for (Field field : fields) {
			field.setAccessible(true); // Make private fields accessible

			// Special Condition for Brand, checks the BRAND_ORDER array (Every component has this attribute)
			if (field.getName().equals("brand")) {
				int indexP1 = BRAND_ORDER.indexOf(c1.getBrand());
				int indexP2 = BRAND_ORDER.indexOf(c2.getBrand());

				if (indexP1 < indexP2)
					points1++;
				else if (indexP1 > indexP2)
					points2++;
			}
			// Special Condition for Architecture, ARCHITECTURE_ORDER the order array (Only CPU has this attribute)
			else if (c1 instanceof CPU && field.getName().equals("architecture")) {
				int indexP1 = ARCHITECTURE_ORDER.indexOf(((CPU) c1).getArchitecture());
				int indexP2 = ARCHITECTURE_ORDER.indexOf(((CPU) c2).getArchitecture());

				if (indexP1 < indexP2)
					points1++;
				else if (indexP1 > indexP2)
					points2++;
			}
			// Special Condition for Ram Compatibility, checks the TECHNOLOGY_ORDER array (Only CPU has this attribute)
			else if (c1 instanceof CPU && field.getName().equals("ramCompatibility")) {
				int indexP1 = TECHNOLOGY_ORDER.indexOf(((CPU) c1).getRamCompatibility());
				int indexP2 = TECHNOLOGY_ORDER.indexOf(((CPU) c2).getRamCompatibility());

				if (indexP1 < indexP2)
					points1++;
				else if (indexP1 > indexP2)
					points2++;
			}
			else if (c1 instanceof RAM && field.getName().equals("technology")) { //(RAM and SSD)
				int indexP1 = TECHNOLOGY_ORDER.indexOf(((RAM) c1).getTechnology());
				int indexP2 = TECHNOLOGY_ORDER.indexOf(((RAM) c2).getTechnology());

				if (indexP1 < indexP2)
					points1++;
				else if (indexP1 > indexP2)
					points2++;
			}
			// Special Condition for interface, checks the TECHNOLOGY_ORDER array (SSD)
			else if (c1 instanceof SSD && field.getName().equals("interfaceName")) {
				int indexP1 = TECHNOLOGY_ORDER.indexOf(((SSD) c1).getInterfaceName());
				int indexP2 = TECHNOLOGY_ORDER.indexOf(((SSD) c2).getInterfaceName());

				if (indexP1 < indexP2)
					points1++;
				else if (indexP1 > indexP2)
					points2++;
			}
			// Special Condition for Material, checks the MATERIAL_ORDER array (Only Case has this attribute)
			else if (c1 instanceof Case && field.getName().equals("material")) {
				int indexP1 = MATERIAL_ORDER.indexOf(((Case) c1).getMaterial());
				int indexP2 = MATERIAL_ORDER.indexOf(((Case) c2).getMaterial());

				if (indexP1 < indexP2)
					points1++;
				else if (indexP1 > indexP2)
					points2++;
			}
			// Special Condition for Efficiency, checks the EFFICIENCY_ORDER array ( Only Power Supply has this attribute)
			else if (c1 instanceof PowerSupply && field.getName().equals("efficiency_tier")) {
				int indexP1 = EFFICIENCY_ORDER.indexOf(((PowerSupply) c1).getEfficiencyTier());
				int indexP2 = EFFICIENCY_ORDER.indexOf(((PowerSupply) c2).getEfficiencyTier());

				if (indexP1 < indexP2)
					points1++;
				else if (indexP1 > indexP2)
					points2++;
			}

			// Special Condition for dates
			else if (field.getName().equals("releaseDate")) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				LocalDate date1 = LocalDate.parse(c1.getReleaseDate().toString(), formatter);
				LocalDate date2 = LocalDate.parse(c2.getReleaseDate().toString(), formatter);

				if (date1.isAfter(date2))
					points1++;
				else if (date1.isBefore(date2))
					points2++;
				}
			else {
				// For others

				/*if (value1.compareTo(value2) > 0)
					points1++;
				else
					points2++;*/
			}
		}
		// 1 if c1 has won, 0 if c2 has won
		return points1 > points2;
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
