package System_and_Main;
import Computer.*;
import HardwareComponent.*;
import Interface.HardwarePart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class HardwareSystem {
	private static ArrayList<HardwareComponent> HardwareComponents = new ArrayList<HardwareComponent>();
	private static ArrayList<Computer> Computers = new ArrayList<Computer>();
	private static final List<String> BRAND_ORDER = Arrays.asList("Nvidia", "AMD", "Intel", "Samsung", "Kingston", "G_SKILL", "Asus", "MSI", "ASROCK", "Gigabyte", "Seagate", "Lian Li", "EVGA", "Seasonic", "Cooler Master", "Corsair", "Thermaltake");
	private static final List<String> ARCHITECTURE_ORDER = Arrays.asList("Zen 5", "Raptor Lake", "Zen 4", "Alder Lake");
	private static final List<String> TECHNOLOGY_ORDER = Arrays.asList("DDR5", "DDR4", "M.2", "NVMe", "SATA");
	private static final List<String> EFFICIENCY_ORDER = Arrays.asList("80+ Titanium", "80+ Platinum", "80+ Gold", "80+ Silver", "80+ Bronze");
	private static final List<String> IOPORTS_ORDER = Arrays.asList("VGA", "HDMI", "Display Port", "USB 2.0", "USB 3.0", "USB 3.1", "USB 3.2");
	private static final List<String> FORM_ORDER = Arrays.asList("ATX", "E-ATX", "Micro-ATX", "Mini-ITX");
	private static final List<String> SSDFORM_ORDER = Arrays.asList("M.2", "mSATA", "U.2", "2.5");
	private static final List<String> MATERIAL_ORDER = Arrays.asList("Steel", "Aluminium", "Glass", "Plastic");
	private static final Map<String, List<String>> ATTRIBUTE_ORDERS = Map.ofEntries(
		Map.entry("brand", BRAND_ORDER),
		Map.entry("architecture", ARCHITECTURE_ORDER),
		Map.entry("technology", TECHNOLOGY_ORDER),
		Map.entry("ramCompatibility", TECHNOLOGY_ORDER),
		Map.entry("storageSlot", TECHNOLOGY_ORDER),
		Map.entry("interfaceName", TECHNOLOGY_ORDER),
		Map.entry("efficiencyTier", EFFICIENCY_ORDER),
		Map.entry("ioPorts", IOPORTS_ORDER),
		Map.entry("formFactor", FORM_ORDER),
		Map.entry("form", SSDFORM_ORDER),
		Map.entry("material", MATERIAL_ORDER)
	);
	private static final String HC_FILENAME = "data.txt";
	private static final String C_FILENAME = "computer.txt";
	
	public static boolean readComponentData() throws FileNotFoundException {
		File file = new File(HC_FILENAME);
		if (!file.exists()) {
			System.out.println("File does not exists!");
			return false;
		}

		Scanner input = new Scanner(file);
		
		while (input.hasNext()) {
			String[] data = input.nextLine().split("::");
			HardwareComponent hardW = createHardwareComponent(data);
			HardwareComponents.add(hardW);
		}
		
		input.close();
		return true;
	}
	
	public static boolean readComputerData() throws FileNotFoundException {
		File file = new File(C_FILENAME);
		if (!file.exists()) {
			System.out.println("File does not exists!");
			return false;
		}
		
		Scanner input = new Scanner(file);
		
		while (input.hasNext()) {
			Computer computer = null;
			String[] computerData = input.nextLine().split("\\|\\|");
			String[] computerInfo = computerData[0].split("::");
			HardwareComponent[] components = new HardwareComponent[7];
			ArrayList<RAM> rams = new ArrayList<RAM>();
			ArrayList<SSD> ssds = new ArrayList<SSD>();
			for (int i=0; i<components.length; i++) {
				if (computerData[i+1].contains("[")) {
					computerData[i+1] = computerData[i+1].substring(1, computerData[i+1].length()-1);
					String[] arrayData = computerData[i+1].split("<>");
					for (String arr: arrayData) {
						String[] componentData = arr.split("::");
						HardwareComponent component = createHardwareComponent(componentData);
						if (component instanceof SSD) {
							ssds.add((SSD)component);
						}
						else if (component instanceof RAM) {
							rams.add((RAM)component);
						}
					}
				}
				else {
					String[] componentData = computerData[i+1].split("::");
					components[i] = createHardwareComponent(componentData);
				}
			}
			RAM[] r = rams.toArray(new RAM[0]);
			SSD[] s = ssds.toArray(new SSD[0]);
			switch (computerInfo[1]) {
				case "Laptop":
					computer = new Laptop(computerInfo[2], Double.parseDouble(computerInfo[3]), 
							(CPU)components[0], (GPU)components[1], r, s,
							(Motherboard)components[4], (PowerSupply)components[5], (Case)components[6], 
							computerData[computerData.length-1]);
					break;
				case "PersonelComputer":
					computer = new PersonalComputer(computerInfo[2], Double.parseDouble(computerInfo[3]), 
							(CPU)components[0], (GPU)components[1], r, s,
							(Motherboard)components[4], (PowerSupply)components[5], (Case)components[6], 
							Boolean.parseBoolean(computerData[computerData.length-1]));
					break;
				case "Notebook":
					computer = new Notebook(computerInfo[2], Double.parseDouble(computerInfo[3]), 
							(CPU)components[0], (GPU)components[1], r, s,
							(Motherboard)components[4], (PowerSupply)components[5], (Case)components[6], 
							computerData[computerData.length-1]);
					break;
			}
			Computers.add(computer);
		}
		
		return true;
	}
	
	public static HardwareComponent createHardwareComponent(String[] data) {
		HardwareComponent hardW = null;
		switch (data[1]) {
			case "CPU":
				hardW = new CPU(Double.parseDouble(data[2]), data[3], data[4], 
						Double.parseDouble(data[5]), Double.parseDouble(data[6]), 
						parseInt(data[7]), data[8], parseInt(data[9]),
						parseInt(data[10]), parseInt(data[11]),
						parseInt(data[12]), data[13]);
				break;
			case "RAM":
				hardW = new RAM(Double.parseDouble(data[2]), data[3], data[4], 
						parseInt(data[5]), data[6], parseInt(data[7]));
				break;
			case "Motherboard":
				hardW = new Motherboard(Double.parseDouble(data[2]), data[3], data[4], 
						data[5], parseInt(data[6]), parseInt(data[7]),
						parseInt(data[8]), parseInt(data[9]),
						data[10].split("-"), parseInt(data[11]), Boolean.parseBoolean(data[12]),
						Boolean.parseBoolean(data[13]), Boolean.parseBoolean(data[14]), 
						data[15].split("-"));
				break;
			case "SSD":
				hardW = new SSD(Double.parseDouble(data[2]), data[3], data[4],
					parseInt(data[5]), parseInt(data[6]),
					parseInt(data[7]), data[8], data[9]);
				break;
			case "GPU":
				hardW = new GPU(Double.parseDouble(data[2]), data[3], data[4],
						Double.parseDouble(data[5]), Double.parseDouble(data[6]), 
						parseInt(data[7]), data[8], data[9],
						parseInt(data[10]), parseInt(data[11]));
				break;
			case "Case":
				hardW = new Case(Double.parseDouble(data[2]), data[3], data[4],
						data[5], data[6]);
				break;
			case "PowerSupply":
				hardW = new PowerSupply(Double.parseDouble(data[2]), data[3], data[4],
						parseInt(data[5]), data[6], data[7]);
		}
		return hardW;
	}
	
	public static boolean addData(Computer computer) throws FileNotFoundException {
		File file = new File(C_FILENAME);
		if (!file.exists()) {
			System.out.println("File does not exists!");
			return false;
		}
		else {
			PrintWriter pw = new PrintWriter(file);
			pw.append(computer.toFile());
			pw.close();
			
			return true;
		}
	}

	// Compare method for computers and components, gets two HardwarePart objects
	public static HardwarePart compare(HardwarePart p1, HardwarePart p2) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
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
				if (entry.getValue() instanceof HardwareComponent) {
					HardwareComponent comp1 = entry.getValue();
					HardwareComponent comp2 = componentsOfP2.get(entry.getKey());

					if (compareComponents(comp1, comp2, comp1.getAllFields(comp1.getClass()))) {
						pointsForComp1++;
					} else {
						pointsForComp2++;
					}
				}
				else {
					if(compareComponents(entry.getKey(), entry.getValue(), componentsOfP2.get(entry.getKey()))) {
						pointsForComp1++;
					}
					else {
						pointsForComp2++;
					}
				}
			}

			return pointsForComp1 > pointsForComp2 ? p1 : p2;
		}
		else if (p1 instanceof HardwareComponent) {
			return compareComponents((HardwareComponent)p1, (HardwareComponent)p2, p1.getAllFields(p1.getClass())) ? p1 : p2;
		}
		else { // If the type is neither Computer nor HardwareComponent
			System.out.println("Type not allowed for comparing.");
			return null;
		}
	}

	public static boolean compareComponents(Object type, Object c1, Object c2) {
		int points1 = 0, points2 = 0;
		if (c1 instanceof String) {
			String c1Str = (String) c1;
			String c2Str = (String) c2;

			if (type.equals("Brand")) {
				int indexP1 = BRAND_ORDER.indexOf(c1);
				int indexP2 = BRAND_ORDER.indexOf(c2);

				if (indexP1 < indexP2)
					points1++;
				else if (indexP1 > indexP2)
					points2++;
			}

		}
		else {
			// For others: bigger the value, better, create dynamic getter
				Double num1 = (Double) c1;
				Double num2 = (Double) c2;

				if (!(num1 == null || num2 == null))  {
					if (type.equals("devicePrice")) {
						if (num1 > num2) {
							points2++;
						}
						else if (num1 < num2) {
							points1++;
						}
					}
					else {
						if (num1 > num2) {
							points1++;
						}
						else if (num1 < num2) {
							points2++;
						}
					}
				}
		}
		return points1 > points2;
	}

	public static boolean compareComponents(HardwareComponent c1, HardwareComponent c2, Field[] fields) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		int points1 = 0;
		int points2 = 0;

		// Iterate through all fields and increment points of CPUs accordingly
		for (Field field : fields) {
			List<String> order_list = ATTRIBUTE_ORDERS.get(field.getName());
			
			//For field where there is an order list
			if (order_list!=null) {
				Object value1 = c1.getValue(field.getName());
				Object value2 = c2.getValue(field.getName());
				
				if (value1 instanceof String[] && value2 instanceof String[]) {
					int length = Math.min(((String[])value1).length, ((String[])value2).length);
					for (int i=0; i<length; i++) {
						int indexP1 = order_list.indexOf(value1);
						int indexP2 = order_list.indexOf(value2);		
						
						if (indexP1 < indexP2) {
							points1++;
						}
						else if (indexP1 > indexP2) {
							points2++;
						}
					}
					points1 += Math.max(0, ((String[]) value1).length - ((String[]) value2).length);
					points2 += Math.max(0, ((String[]) value2).length - ((String[]) value1).length);
				}
				else {
					int indexP1 = order_list.indexOf(value1);
					int indexP2 = order_list.indexOf(value2);		
					
					if (indexP1 < indexP2) {
						points1++;
					}
					else if (indexP1 > indexP2) {
						points2++;
					}
				}
			}
			// Special Condition for dates
			else if (field.getName().equals("releaseDate")) {
				// Assuming c1.getReleaseDate() and c2.getReleaseDate() return date strings in "dd/MM/yyyy" format
				String dateStr1 = c1.getReleaseDate();  // e.g., "12/08/2022"
				String dateStr2 = c2.getReleaseDate();  // e.g., "15/07/2021"
	
				// Define a DateTimeFormatter to parse the date strings
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
				// Parse the strings into LocalDate objects
				LocalDate date1 = LocalDate.parse(dateStr1, formatter);
				LocalDate date2 = LocalDate.parse(dateStr2, formatter);

				// Compare the two dates using isAfter() and isBefore()
				if (date1.isAfter(date2)) {
					points1++;
				}
				else if (date1.isBefore(date2)) {
					points2++;
				}
			}			
			else { // Integer, Double or Boolean values
				// Special condition for price
				if (field.getName().equals("recommendedPrice")) {
					 if (c1.getRecommendedPrice() > c2.getRecommendedPrice()) {
						 points2++;
					 } else if (c1.getRecommendedPrice() < c2.getRecommendedPrice()) {
						 points1++;
					 }
				 }
				// For others: bigger the value, better, create dynamic getter
				try {
					//String getterName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
					//Method getterMethod = c1.getClass().getMethod(getterName);

					// Get the values from both c1 and c2 using their getters
					Object value1 = c1.getValue(field.getName());
					Object value2 = c2.getValue(field.getName());

					// Compare values (adjust comparison logic as needed)
					if (value1 instanceof Integer) {
						if (((Integer)value1).compareTo(((Integer)value2)) > 0) {
							points1++;
						} else if (((Integer)value1).compareTo(((Integer)value2)) < 0) {
							points2++;
						}
					}
					if (value1 instanceof Double) {
						if (((Double)value1).compareTo(((Double)value2)) > 0) {
							points1++;
						} else if (((Double)value1).compareTo(((Double)value2)) < 0) {
							points2++;
						}
					}
					if (value1 instanceof Boolean) {
						if ((Boolean)value1) {
							points1++;
						}
						if ((Boolean)value2) {
							points2++;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		System.out.println(str);
		return str;
	}
}
