package System_and_Main;
import Computer.*;
import GUI.CompareFrame;
import HardwareComponent.*;
import Interface.HardwarePart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static java.lang.Integer.parseInt;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class HardwareSystem {
	private static HashSet<HardwareComponent> HardwareComponents = new HashSet<HardwareComponent>();
	private static HashSet<Computer> Computers = new HashSet<Computer>();
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
					computer = new Laptop(Integer.parseInt(computerInfo[2]), computerInfo[3], computerInfo[4], 
							Double.parseDouble(computerInfo[5]), (CPU)components[0], (GPU)components[1], r, s,
							(Motherboard)components[4], (PowerSupply)components[5], (Case)components[6], 
							computerData[computerData.length-1]);
					break;
				case "PersonalComputer":
					computer = new PersonalComputer(Integer.parseInt(computerInfo[2]), computerInfo[3], computerInfo[4],
							Double.parseDouble(computerInfo[5]), 
							(CPU)components[0], (GPU)components[1], r, s,
							(Motherboard)components[4], (PowerSupply)components[5], (Case)components[6], 
							Boolean.parseBoolean(computerData[computerData.length-1]));
					break;
				case "Notebook":
					computer = new Notebook(Integer.parseInt(computerInfo[2]), computerInfo[3], computerInfo[4],
							Double.parseDouble(computerInfo[5]), 
							(CPU)components[0], (GPU)components[1], r, s,
							(Motherboard)components[4], (PowerSupply)components[5], (Case)components[6], 
							computerData[computerData.length-1]);
					break;
			}
			Computers.add(computer);
		}
		
		return true;
	}
	
	private static HardwareComponent createHardwareComponent(String[] data) {
		HardwareComponent hardW = null;
		switch (data[1]) {
			case "CPU":
				hardW = new CPU(Integer.parseInt(data[2]), data[3], data[4], 
						data[5], Double.parseDouble(data[6]),
						Double.parseDouble(data[7]), Double.parseDouble(data[8]), 
						parseInt(data[9]), data[10], parseInt(data[11]),
						parseInt(data[12]), parseInt(data[13]),
						parseInt(data[14]), data[15]);
				break;
			case "GPU":
				hardW = new GPU(Integer.parseInt(data[2]), data[3], data[4], 
						data[5], Double.parseDouble(data[6]),
						Double.parseDouble(data[7]), Double.parseDouble(data[8]), 
						parseInt(data[9]), data[10],
						parseInt(data[11]), parseInt(data[12]));
				break;
			case "RAM":
				hardW = new RAM(Integer.parseInt(data[2]), data[3], data[4], 
						data[5], Double.parseDouble(data[6]), 
						Integer.parseInt(data[7]), data[8], parseInt(data[9]));
				break;
			case "Motherboard":
				hardW = new Motherboard(Integer.parseInt(data[2]), data[3], data[4], 
						data[5], Double.parseDouble(data[6]), 
						data[7], parseInt(data[8]), parseInt(data[9]),
						parseInt(data[10]), parseInt(data[11]),
						data[12].split("-"), parseInt(data[13]), Boolean.parseBoolean(data[14]),
						Boolean.parseBoolean(data[15]), Boolean.parseBoolean(data[16]), 
						data[17].split("-"));
				break;
			case "SSD":
				hardW = new SSD(Integer.parseInt(data[2]), data[3], data[4], 
						data[5], Double.parseDouble(data[6]),
					parseInt(data[7]), parseInt(data[8]),
					parseInt(data[9]), data[10], data[11]);
				break;
			case "PowerSupply":
				hardW = new PowerSupply(Integer.parseInt(data[2]), data[3], data[4], 
						data[5], Double.parseDouble(data[6]),
						parseInt(data[7]), data[8], data[9]);
				break;
			case "Case":
				hardW = new Case(Integer.parseInt(data[2]), data[3], data[4], 
						data[5], Double.parseDouble(data[6]),
						data[7], data[8]);
				break;
		}
		return hardW;
	}
	
	private static boolean addData(PersonalComputer computer) throws IOException {
		File file = new File(C_FILENAME);
		FileWriter fw = null;
		PrintWriter pw = null;
		
		if (!file.exists()) {
			System.out.println("File does not exists!");
			return false;
		}
		else {
			fw = new FileWriter(file, true);
			pw = new PrintWriter(fw);
			String str = computer.toFile();
			pw.println(str);
			pw.close();
			return true;
		}
	}

	// Compare method for computers and components, gets two HardwarePart objects
	public static HardwarePart compare(CompareFrame frame, HardwarePart p1, HardwarePart p2) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {	
		// Base case for type matching, p1 and p2 object should be instantiated from the same class
		if (!(p1.getClass().getSuperclass().equals(p2.getClass().getSuperclass()))) {
			System.out.println("Type mismatch");
			return null;
		}

		// Comparison system for computers is by point,
		// which is incremented by one if one component is better than the other
		if (p1 instanceof Computer) {
			int pointsForComp1 = 0, pointsForComp2 = 0, curInd = 0;
			JLabel[][] labels = frame.getLabels();

			Map<String, Object> componentsOfP1 = p1.getValues(); // Put the components of p1 in a Map Structure
			Map<String, Object> componentsOfP2 = p2.getValues(); // Put the components of p2 in a Map Structure

			for (Map.Entry<String, Object> entry : componentsOfP1.entrySet()) {
				if (entry.getValue().getClass().isArray()) {
					MemoryUnit[] arr1 = (MemoryUnit[]) entry.getValue();
					MemoryUnit[] arr2 = (MemoryUnit[]) componentsOfP2.get(entry.getKey());
					int length = Math.min(arr1.length, arr2.length);
					
					labels[curInd][0].setText(entry.getValue().getClass().getName().substring(entry.getValue().getClass().getName().length() - 4, entry.getValue().getClass().getName().length() - 1));
					labels[curInd][0].setVisible(true);
					labels[curInd][1].setText(arr1[0].getModelName());
					labels[curInd][1].setVisible(true);
					labels[curInd][2].setText(arr2[0].getModelName());
					labels[curInd][2].setVisible(true);

					// Compare common elements
			    	for (int i = 0; i < length; i++) {
				        if (compareComponents(frame, arr1[i], arr2[i], arr1[i].getAllFields(arr1[i].getClass()), true)) {
				            pointsForComp1++;
				            labels[curInd][1].setForeground(Color.green);
							labels[curInd][2].setForeground(Color.red);
				        }
				        else {
				            pointsForComp2++;
				            labels[curInd][1].setForeground(Color.red);
							labels[curInd][2].setForeground(Color.green);
				        }
			    	}
			    	
				    // Assign extra points for the larger array
				    if (arr1.length > arr2.length) {
				        pointsForComp1 += arr1.length - arr2.length;
				    }
				    else if (arr2.length > arr1.length) {
				        pointsForComp2 += arr2.length - arr1.length;
				    }
				}
				else if (entry.getValue() instanceof HardwareComponent) {
					HardwareComponent comp1 = (HardwareComponent) entry.getValue();
					HardwareComponent comp2 = (HardwareComponent) componentsOfP2.get(entry.getKey());
					
					labels[curInd][0].setText(entry.getValue().getClass().getName().substring(entry.getValue().getClass().getName().indexOf(".")+  1));
					labels[curInd][0].setVisible(true);
					labels[curInd][1].setText(comp1.getModelName());
					labels[curInd][1].setVisible(true);
					labels[curInd][2].setText(comp2.getModelName());
					labels[curInd][2].setVisible(true);

					if (compareComponents(frame, comp1, comp2, comp1.getAllFields(comp1.getClass()), true)) {
						pointsForComp1++;
						labels[curInd][1].setForeground(Color.green);
						labels[curInd][2].setForeground(Color.red);
					} else {
						pointsForComp2++;
						labels[curInd][1].setForeground(Color.red);
						labels[curInd][2].setForeground(Color.green);
					}
				}
				else {
					if (!entry.getKey().equals("ModelNumber")) {
						labels[curInd][0].setText(entry.getKey());
						labels[curInd][0].setVisible(true);
						labels[curInd][1].setText(entry.getValue().toString());
						labels[curInd][1].setVisible(true);
						labels[curInd][2].setText(componentsOfP2.get(entry.getKey()).toString());
						labels[curInd][2].setVisible(true);
					}
					
					if(compareComponents(entry.getKey(), entry.getValue(), componentsOfP2.get(entry.getKey()))) {
						pointsForComp1++;
						labels[curInd][1].setForeground(Color.green);
						labels[curInd][2].setForeground(Color.red);
					}
					else {
						pointsForComp2++;
						labels[curInd][1].setForeground(Color.red);
						labels[curInd][2].setForeground(Color.green);
					}
				}
				curInd++;
			}
			GridBagConstraints compare = frame.getGbc_compareBtn();
			compare.gridy = curInd + 7;
			GridBagConstraints back = frame.getGbc_backBtn();
			back.gridy = curInd + 7;
			frame.getContentPane().add(frame.getCompareBtn(), compare);
			frame.getContentPane().add(frame.getBackBtn(), back);
			return pointsForComp1 > pointsForComp2 ? p1 : p2;
		}
		else if (p1 instanceof HardwareComponent) {
			return compareComponents(frame, (HardwareComponent)p1, (HardwareComponent)p2, p1.getAllFields(p1.getClass()), false) ? p1 : p2;
		}
		else { // If the type is neither Computer nor HardwareComponent
			System.out.println("Type not allowed for comparing.");
			return null;
		}
	}

	private static boolean compareComponents(Object type, Object c1, Object c2) {
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
			if (!type.equals("ModelNumber")) {
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
		}
		return points1 > points2;
	}

	private static boolean compareComponents(CompareFrame frame, HardwareComponent c1, HardwareComponent c2, Field[] fields, boolean isComputer) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		int points1 = 0;
		int points2 = 0;
		int curInd = 0;
		JLabel[][] labels = frame.getLabels();

		// Iterate through all fields and increment points of CPUs accordingly
		for (Field field : fields) {
			List<String> order_list = ATTRIBUTE_ORDERS.get(field.getName());
			
			//For field where there is an order list
			if (order_list!=null) {
				Object value1 = c1.getValue(field.getName());
				Object value2 = c2.getValue(field.getName());
				
				if (value1 instanceof String[] && value2 instanceof String[]) {
					int length = Math.min(((String[])value1).length, ((String[])value2).length);
					
					if (!isComputer) {
						labels[curInd][0].setText(field.getName());
						labels[curInd][0].setVisible(true);
						labels[curInd][1].setText(((String[])value1)[0].toString());
						labels[curInd][1].setVisible(true);
						labels[curInd][2].setText(((String[])value2)[0].toString());
						labels[curInd][2].setVisible(true);
					}
					
					for (int i=0; i<length; i++) {
						int indexP1 = order_list.indexOf(value1);
						int indexP2 = order_list.indexOf(value2);		
						
						if (indexP1 < indexP2) {
							points1++;
							if (!isComputer) {
								labels[curInd][1].setForeground(Color.green);
								labels[curInd][2].setForeground(Color.red);
							}
						}
						else if (indexP1 > indexP2) {
							points2++;
							if (!isComputer) {
								labels[curInd][1].setForeground(Color.red);
								labels[curInd][2].setForeground(Color.green);
							}
						}
					}
					points1 += Math.max(0, ((String[]) value1).length - ((String[]) value2).length);
					points2 += Math.max(0, ((String[]) value2).length - ((String[]) value1).length);
				}
				else {
					int indexP1 = order_list.indexOf(value1);
					int indexP2 = order_list.indexOf(value2);
					
					if (!isComputer) {
						labels[curInd][0].setText(field.getName());
						labels[curInd][0].setVisible(true);
						labels[curInd][1].setText(value1.toString());
						labels[curInd][1].setVisible(true);
						labels[curInd][2].setText(value2.toString());
						labels[curInd][2].setVisible(true);
					}
					
					if (indexP1 < indexP2) {
						points1++;
						if (!isComputer) {
							labels[curInd][1].setForeground(Color.green);
							labels[curInd][2].setForeground(Color.red);
						}
					}
					else if (indexP1 > indexP2) {
						points2++;
						if (!isComputer) {
							labels[curInd][1].setForeground(Color.red);
							labels[curInd][2].setForeground(Color.green);
						}
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
				
				if (!isComputer) {
					labels[curInd][0].setText(field.getName());
					labels[curInd][0].setVisible(true);
					labels[curInd][1].setText(date1.toString());
					labels[curInd][1].setVisible(true);
					labels[curInd][2].setText(date2.toString());
					labels[curInd][2].setVisible(true);
				}

				// Compare the two dates using isAfter() and isBefore()
				if (date1.isAfter(date2)) {
					points1++;
					if (!isComputer) {
						labels[curInd][1].setForeground(Color.green);
						labels[curInd][2].setForeground(Color.red);
					}
				}
				else if (date1.isBefore(date2)) {
					points2++;
					if (!isComputer) {
						labels[curInd][1].setForeground(Color.red);
						labels[curInd][2].setForeground(Color.green);
					}
				}
			}			
			else { // Integer, Double or Boolean values
				// Special condition for price
				if (field.getName().equals("recommendedPrice")) {
					if (!isComputer) {
						labels[curInd][0].setText(field.getName());
						labels[curInd][0].setVisible(true);
						labels[curInd][1].setText(Double.toString(c1.getRecommendedPrice()));
						labels[curInd][1].setVisible(true);
						labels[curInd][2].setText(Double.toString(c2.getRecommendedPrice()));
						labels[curInd][2].setVisible(true);
					}
					
					 if (c1.getRecommendedPrice() > c2.getRecommendedPrice()) {
						 points2++;
						 if (!isComputer) {
							 labels[curInd][1].setForeground(Color.red);
							 labels[curInd][2].setForeground(Color.green);
						 }
					 } else if (c1.getRecommendedPrice() < c2.getRecommendedPrice()) {
						 points1++;
						 if (!isComputer) {
							 labels[curInd][1].setForeground(Color.green);
							 labels[curInd][2].setForeground(Color.red);
						 }
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
						if (!isComputer) {
							labels[curInd][0].setText(field.getName());
							labels[curInd][0].setVisible(true);
							labels[curInd][1].setText(Integer.toString((Integer)value1));
							labels[curInd][1].setVisible(true);
							labels[curInd][2].setText(Integer.toString((Integer)value2));
							labels[curInd][2].setVisible(true);
						}
						
						if (((Integer)value1).compareTo(((Integer)value2)) > 0) {
							points1++;
							if (!isComputer) {
								labels[curInd][1].setForeground(Color.green);
								labels[curInd][2].setForeground(Color.red);
							}
						} else if (((Integer)value1).compareTo(((Integer)value2)) < 0) {
							points2++;
							if (!isComputer) {
								labels[curInd][1].setForeground(Color.red);
								labels[curInd][2].setForeground(Color.green);
							}
						}
					}
					if (value1 instanceof Double) {
						if (!isComputer) {
							labels[curInd][0].setText(field.getName());
							labels[curInd][0].setVisible(true);
							labels[curInd][1].setText(Double.toString((Double)value1));
							labels[curInd][1].setVisible(true);
							labels[curInd][2].setText(Double.toString((Double)value2));
							labels[curInd][2].setVisible(true);
						}
						
						if (((Double)value1).compareTo(((Double)value2)) > 0) {
							points1++;
							if (!isComputer) {
								labels[curInd][1].setForeground(Color.green);
								labels[curInd][2].setForeground(Color.red);
							}
						} else if (((Double)value1).compareTo(((Double)value2)) < 0) {
							points2++;
							if (!isComputer) {
								labels[curInd][1].setForeground(Color.red);
								labels[curInd][2].setForeground(Color.green);
							}
						}
					}
					if (value1 instanceof Boolean) {
						if (!isComputer) {
							labels[curInd][0].setText(field.getName());
							labels[curInd][0].setVisible(true);
							labels[curInd][1].setText(Boolean.toString((Boolean)value1));
							labels[curInd][1].setVisible(true);
							labels[curInd][2].setText(Boolean.toString((Boolean)value2));
							labels[curInd][2].setVisible(true);
						}
						
						if ((Boolean)value1) {
							points1++;
							if (!isComputer) {
								labels[curInd][1].setForeground(Color.green);
							}
						}
						else if (!isComputer) {
							labels[curInd][1].setForeground(Color.red);
						}
						if ((Boolean)value2) {
							points2++;
							if (!isComputer) {
								labels[curInd][2].setForeground(Color.green);
							}
						}
						else if (!isComputer) {
							labels[curInd][2].setForeground(Color.red);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			curInd++;
		}
		// 1 if c1 has won, 0 if c2 has won
		if (!isComputer) {
			GridBagConstraints compare = frame.getGbc_compareBtn();
			compare.gridy = curInd + 7;
			GridBagConstraints back = frame.getGbc_backBtn();
			back.gridy = curInd + 7;
			frame.getContentPane().add(frame.getCompareBtn(), compare);
			frame.getContentPane().add(frame.getBackBtn(), back);
		}
		return points1 > points2;
	}
	
	public static double buildComputer(CPU cpu, GPU gpu, RAM ram, int ramAmount, SSD ssd, int ssdAmount, Motherboard motherboard,
			PowerSupply powerSupply, Case Case, boolean monitor) {
		PersonalComputer pc = new PersonalComputer();
		Double price = pc.buildComputer(cpu, gpu, ram, ramAmount, ssd, ssdAmount, motherboard, powerSupply, Case, monitor);
		Computers.add(pc);
		try {
			addData(pc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return price;
	}

	public static HashSet<Computer> getComputers() {
		return Computers;
	}
	
	public static String[] getComputersArray(Computer object) {
		TreeSet<Computer> set = new TreeSet<Computer>();
		set.addAll(Computers);
		if (object!=null)  {
			set.remove(object);
		}
		String[] arr = new String[set.size()];
		int i=0;
		for (Computer c: set) {
			arr[i] = c.getModelName();
			i++;
		}
		return arr;
	}
	
	public static HashSet<PersonalComputer> getPersonalComputers() {
		HashSet<PersonalComputer> computers = new HashSet<PersonalComputer>();
		for (Computer computer: Computers) {
			if (computer instanceof PersonalComputer) {
				computers.add((PersonalComputer)computer);
			}
		}
		return computers;
	}
	
	public static HashSet<Laptop> getLaptops() {
		HashSet<Laptop> computers = new HashSet<Laptop>();
		for (Computer computer: Computers) {
			if (computer instanceof Laptop) {
				computers.add((Laptop)computer);
			}
		}
		return computers;
	}
	
	public static HashSet<Notebook> getNotebooks() {
		HashSet<Notebook> computers = new HashSet<Notebook>();
		for (Computer computer: Computers) {
			if (computer instanceof Notebook) {
				computers.add((Notebook)computer);
			}
		}
		return computers;
	}
	
	public static HashSet<HardwareComponent> getHardwareComponents() {
		return HardwareComponents;
	}
	
	public static String[] getHardwareComponentsArray(HardwareComponent object) {
		TreeSet<HardwareComponent> set = new TreeSet<HardwareComponent>();
		set.addAll(HardwareComponents);
		if (object!=null)  { set.remove(object); }
		String[] arr = new String[set.size()];
		int i=0;
		for (HardwareComponent c: set) {
			arr[i] = c.getModelName();
			i++;
		}
		return arr;
	}
	
	public static HashSet<ProcessingUnit> getProcessingUnits() {
		HashSet<ProcessingUnit> components = new HashSet<ProcessingUnit>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof ProcessingUnit) {
				components.add((ProcessingUnit)component);
			}
		}
		return components;
	}
	
	public static String[] getCPUs(Motherboard mb) {
		HashSet<CPU> components = new HashSet<CPU>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof CPU) {
				try {
					if (mb.cpuCompatibility((CPU)component)) {
						components.add((CPU)component);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		String[] str = new String[components.size()];
		int i=0;
		for (HardwareComponent component: components) {
			str[i]=component.getModelName();
			i++;
		}
		return str;
	}
	
	// Added for getting array of CPUs
	public static String[] getCPUs() {
		HashSet<CPU> components = new HashSet<CPU>();
		
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof CPU) {
				components.add((CPU)component);
			}
		}
		
		String[] str = new String[components.size()];
		int i=0;
		for (HardwareComponent component: components) {
			str[i]=component.getModelName();
			i++;
		}
		return str;
		
	}
	
	public static String[] getGPUs() {
		HashSet<GPU> components = new HashSet<GPU>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof GPU) {
				components.add((GPU)component);
			}
		}
		String[] str = new String[components.size()];
		int i=0;
		for (HardwareComponent component: components) {
			str[i]=component.getModelName();
			i++;
		}
		return str;
	}
	
	public static HashSet<MemoryUnit> getMemoryUnits() {
		HashSet<MemoryUnit> components = new HashSet<MemoryUnit>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof MemoryUnit) {
				components.add((MemoryUnit)component);
			}
		}
		return components;
	}
	
	public static String[] getRAMs(Motherboard mb, CPU cpu, int amount) {
		HashSet<RAM> components = new HashSet<RAM>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof RAM) {
				if (((RAM)component).getTechnology().equalsIgnoreCase(cpu.getRamCompatibility()) && ((RAM)component).getCapacity()<=mb.getMaxMemory()/amount) {
					components.add((RAM)component);
				}
			}
		}
		String[] str = new String[components.size()];
		int i=0;
		for (HardwareComponent component: components) {
			str[i]=component.getModelName();
			i++;
		}
		return str;
	}
	
	// Added for getting array of RAMs
	public static String[] getRAMs() {
		HashSet<RAM> components = new HashSet<RAM>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof RAM) {
					components.add((RAM)component);
				}
			}
		
		String[] str = new String[components.size()];
		int i=0;
		for (HardwareComponent component: components) {
			str[i]=component.getModelName();
			i++;
		}
		return str;
	}
	
	public static String[] getSSDs(Motherboard mb) {
		HashSet<SSD> components = new HashSet<SSD>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof SSD) {
				HashSet<String> slots = new HashSet<String>(Arrays.asList(mb.getStorageSlot()));
				if (slots.contains(((SSD)component).getInterfaceName())) {
					components.add((SSD)component);
				}
			}
		}
		String[] str = new String[components.size()];
		int i=0;
		for (HardwareComponent component: components) {
			str[i]=component.getModelName();
			i++;
		}
		return str;
	}
	
	// Added for getting array of SSDs
	public static String[] getSSDs() {
		HashSet<SSD> components = new HashSet<SSD>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof SSD) {
				components.add((SSD)component);
		
			}
		}
		String[] str = new String[components.size()];
		int i=0;
		for (HardwareComponent component: components) {
			str[i]=component.getModelName();
			i++;
		}
		return str;
	}
	
	public static String[] getMotherboards() {
		HashSet<Motherboard> components = new HashSet<Motherboard>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof Motherboard) {
				components.add((Motherboard)component);
			}
		}
		String[] str = new String[components.size()];
		int i=0;
		for (HardwareComponent component: components) {
			str[i]=component.getModelName();
			i++;
		}
		return str;
	}
	
	public static String[] getPowerSupplys(GPU gpu, Case Case) {
		HashSet<PowerSupply> components = new HashSet<PowerSupply>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof PowerSupply) {
				if (((PowerSupply)component).getWattage()>=gpu.getPsu() && ((PowerSupply)component).getFormFactor().equalsIgnoreCase(Case.getFormFactor())) {
					components.add((PowerSupply)component);
				}
			}
		}
		String[] str = new String[components.size()];
		int i=0;
		for (HardwareComponent component: components) {
			str[i]=component.getModelName();
			i++;
		}
		return str;
	}
	
	public static String[] getPowerSupplys() {
		HashSet<PowerSupply> components = new HashSet<PowerSupply>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof PowerSupply) {
		
					components.add((PowerSupply)component);
				}
			}
		
		String[] str = new String[components.size()];
		int i=0;
		for (HardwareComponent component: components) {
			str[i]=component.getModelName();
			i++;
		}
		return str;
	}
	
	public static String[] getCases() {
		HashSet<Case> components = new HashSet<Case>();
		for (HardwareComponent component: HardwareComponents) {
			if (component instanceof Case) {
				components.add((Case)component);
			}
		}
		String[] str = new String[components.size()];
		int i=0;
		for (HardwareComponent component: components) {
			str[i]=component.getModelName();
			i++;
		}
		return str;
	}
	
	public static HardwarePart findHardwarePart(int modelNumber) {
		for (Computer c: Computers) {
			if (c.getModelNumber()==modelNumber) {
				return c;
			}
		}
		for (HardwareComponent c: HardwareComponents) {
			if (c.getModelNumber()==modelNumber) {
				return c;
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
	
	public static String displayHardwarePart(HardwarePart part) {
		return part.toString();
	}
	
	public static String displayComponents() {
		String str = "";
		for (HardwareComponent part: HardwareComponents) {
			str += part.toString();
		}
		return str;
	}
	
	public static String displayComputers() {
		String str = "";
		for (Computer computer: Computers) {
			str += computer.toString();
		}
		return str;
	}
}
