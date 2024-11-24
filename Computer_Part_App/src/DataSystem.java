import java.util.ArrayList;

public class DataSystem {
	private static ArrayList<Computer> Computers;
	private static ArrayList<HardwareComponent> HardwareComponents;
	
	public static ArrayList<Computer> getComputers() {
		return Computers;
	}
	
	public static ArrayList<PersonelComputer> getPersonelComputers() {
		ArrayList<PersonelComputer> computers = new ArrayList<PersonelComputer>();
		for (Computer computer: Computers) {
			if (computer instanceof PersonelComputer) {
				computers.add((PersonelComputer)computer);
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
}
