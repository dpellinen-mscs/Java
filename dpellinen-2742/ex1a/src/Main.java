import domain.GDate;

public class Main {
    public static void main(String[] args) {
        //conzstructors
        GDate date1 = new GDate();
        System.out.println("GDate(): \t\t\t" + date1);
        GDate date2 = new GDate(2000, 1, 1);
        System.out.println("GDate(2000, 1, 1): \t" + date1);
        GDate date3 = new GDate(date1);

        GDate date4 = new GDate(2451545);

        // comparisons
        System.out.print(date1);
        System.out.print(date1.equals(date3) ? " = " : " != ");
        System.out.println(date3);

    }
}