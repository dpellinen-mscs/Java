package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HourlyAdministrator extends Administrator {
    private double hourlyRate;
    private ArrayList<TimeCard> timeCards = new ArrayList<>();

    public HourlyAdministrator(int personId, String firstName, String lastName, String userName, LocalDateTime birthDate,
                               String ssn, String phone, LocalDateTime employmentStartDate, double hourlyRate) {
        super(personId, firstName, lastName,userName,
                birthDate, ssn, phone, employmentStartDate);
        this.hourlyRate = hourlyRate;
         this.timeCards = timeCards;
    }

    public void addTimeCard(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.timeCards.add(new TimeCard(startDateTime, endDateTime));
    }

    public void addTimeCard(int id, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.timeCards.add(new TimeCard(id, startDateTime, endDateTime));
    }

    public TimeCard removeTimeCard(int index) {
        TimeCard timeCard = null;
        if (index >= 0 && index < this.timeCards.size()) {
            timeCard = this.timeCards.get(index).copy();
            this.timeCards.remove(index);
        }
        return timeCard;
    }

    public TimeCard getTimeCard(int index) {
        TimeCard timeCard = null;
        if (index < this.timeCards.size())
            timeCard = timeCards.get(index).copy();
        return timeCard;
    }

    public ArrayList<TimeCard> getTimeCards() {
        ArrayList<TimeCard> timeCards = new ArrayList<>();
        for (TimeCard timeCard : this.timeCards) {
            timeCards.add(timeCard.copy());
        }
        return timeCards;
    }
    @Override
    public String toString() {
        return super.toString() +
                " hourlyRate=" + hourlyRate + " total hours=" + calcTotalHours() +
                " gross pay=" + calcGrossPay();
    }

    public double calcTotalHours() {
        double totalHours = 0.0;
        for (TimeCard timeCard : timeCards) {
            totalHours += timeCard.calcHours();
        }
        return totalHours;
//        return 45;
    }

    @Override
    public double calcGrossPay() {
        return this.calcTotalHours() * this.hourlyRate;
    }
}
