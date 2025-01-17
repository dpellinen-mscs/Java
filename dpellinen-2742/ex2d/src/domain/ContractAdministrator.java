package domain;

import java.time.LocalDateTime;

public class ContractAdministrator extends Administrator {
    private double monthlyRate;

    public ContractAdministrator(int personId, String firstName, String lastName, String userName,
                                 LocalDateTime birthDate, String ssn, String phone,
                                 LocalDateTime employmentStartDate, double monthlyRate){
        super(personId, firstName, lastName,userName,
                birthDate, ssn, phone, employmentStartDate);
        this.monthlyRate = monthlyRate;
    }


    @Override
    public String toString() {
        return super.toString() +
                " monthlyRate=" + monthlyRate + " grossPay=" + calcGrossPay();
    }

        private double getMonthlyRate() {return monthlyRate;}

        private void setMonthlyRate(double monthlyRate) {this.monthlyRate = monthlyRate;}

        @Override
        public double calcGrossPay() {
        return this.monthlyRate;
    }
}

