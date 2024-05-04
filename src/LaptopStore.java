// LaptopStore.java
import java.util.*;

public class LaptopStore {
    private List<Laptop> laptops;

    public LaptopStore() {
        laptops = new ArrayList<>();
    }

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }

    public void filterLaptops() {
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("5 - поиск");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество критериев фильтрации:");
        int numCriteria = scanner.nextInt();

        Map<String, Object> filterCriteria = new HashMap<>();

        for (int i = 0; i < numCriteria; i++) {
            System.out.println("Введите номер критерия:");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Введите минимальное значение ОЗУ:");
                    int minRam = scanner.nextInt();
                    filterCriteria.put("ram", minRam);
                    break;
                case 2:
                    System.out.println("Введите минимальное значение объема ЖД:");
                    int minStorage = scanner.nextInt();
                    filterCriteria.put("storage", minStorage);
                    break;
                case 3:
                    System.out.println("Введите операционную систему:");
                    String os = scanner.next();
                    filterCriteria.put("os", os);
                    break;
                case 4:
                    System.out.println("Введите цвет:");
                    String color = scanner.next();
                    filterCriteria.put("color", color);
                    break;
                case 5:
                    System.out.println("Введите поисковый запрос:");
                    String searchQuery = scanner.next();
                    filterCriteria.put("search", searchQuery);
                    break;
                default:
                    System.out.println("Неверный выбор критерия.");
                    return;
            }
        }

        List<Laptop> filteredLaptops = new ArrayList<>();
        for (Laptop laptop : laptops) {
            boolean passesFilter = true;
            for (Map.Entry<String, Object> entry : filterCriteria.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                switch (key) {
                    case "ram":
                        if (laptop.getRam() < (int) value) {
                            passesFilter = false;
                            break;
                        }
                        break;
                    case "storage":
                        if (laptop.getStorage() < (int) value) {
                            passesFilter = false;
                            break;
                        }
                        break;
                    case "os":
                        if (!laptop.getOs().equalsIgnoreCase((String) value)) {
                            passesFilter = false;
                            break;
                        }
                        break;
                    case "color":
                        if (!laptop.getColor().equalsIgnoreCase((String) value)) {
                            passesFilter = false;
                            break;
                        }
                        break;
                    case "search":
                        if (!laptop.toString().toLowerCase().contains(value.toString().toLowerCase())) {
                            passesFilter = false;
                            break;
                        }
                        break;
                }

                if (!passesFilter) break;
            }

            if (passesFilter) filteredLaptops.add(laptop);
        }

        System.out.println("Отфильтрованные ноутбуки:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }

    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();
        store.addLaptop(new Laptop("Apple", 8, 256, "macOS", "Silver"));
        store.addLaptop(new Laptop("Dell", 16, 512, "Windows", "Black"));
        store.addLaptop(new Laptop("HP", 8, 128, "Windows", "Silver"));
        store.addLaptop(new Laptop("Acer", 4, 512, "Linux", "Red"));

        store.filterLaptops();
    }
}