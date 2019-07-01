public class Main {

    HashMap map = new HashMap();
    HashSet set1 = new HashSet();

    public static void main(String[] args) throws NoSuchElementException {

        Main main = new Main();
        main.map.put("Adam", 1);
        main.map.put("Bartek", 22);
        main.map.put("Cyprian", 333);
        main.map.put("Daria", 4444);
        main.map.put("Daria", 4444);

        //System.out.println(main.map.get("Adam"));
        main.map.remove("Bartek");
        //main.map.remove("Alan");

        System.out.println(main.map.toString());
        System.out.println("Iterator przez klucz: " + main.map.iteratorByKeys("Daria"));
        System.out.println("Iterator przez wartość: " + main.map.iteratorByValues(4444));
        System.out.println();

        main.set1.add("Ewa");
        main.set1.add("Franek");
        main.set1.add("Grzegorz");
        main.set1.add("Hania");

        main.set1.add("Franek");
        main.set1.remove("Franek");
        main.set1.add("Franek");

        System.out.println(main.set1.toString());

    }
}
