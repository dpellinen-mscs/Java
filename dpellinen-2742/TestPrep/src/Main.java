public class Main {

    public static void main(String[] args) {
        int i = 0;
        try {
            Class5 fiveC = new Class5(3, "fiveC", 4, "fiveC", 5, "fiveC");
            System.out.println(fiveC.toString());

        } catch (Exception e) {
            if (i == 5) {
                // Handle the exception when i is equal to 5
                System.out.println("Caught an exception and i is 5");
            } else {
                // Handle the exception for other cases
                System.out.println("Caught an exception, but i is not 5");
            }
        }
    }
}

