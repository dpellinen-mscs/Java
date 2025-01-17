package dataaccess;

import domain.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PeopleJSONParser {
    private static String json ="{}";

    public static void readFile (String path) throws IOException{
        try(BufferedReader reader = new BufferedReader(
                new FileReader(path)))
        {
            String line = "";
            StringBuilder sbJSON = new StringBuilder(400);
            while ((line = reader.readLine())!= null) {
                sbJSON.append(line + "\n");
        }
            json = sbJSON.toString();
        }
    }

    public static ArrayList<Person> getPeople() {
        ArrayList<Person> people = new ArrayList<Person>();
        JSONObject obj = new JSONObject(json);
        JSONArray jsonPeople = obj.getJSONArray("people");
        if(jsonPeople != null) {
            for (int i = 0; i < jsonPeople.length(); i++) {
                JSONObject jsonPerson= (JSONObject) jsonPeople.get(i);
                String subclass = jsonPerson.getString("subclass");
                switch (subclass) {
                    case "person":
                        people.add(getPerson(jsonPerson));
                        break;
                    case "tenant":
                        people.add(getTenant(jsonPerson));
                        break;
                    case "contractAdministrator":
                        people.add(getContractAdministrator(jsonPerson));
                        break;
                    case "hourlyAdministrator":
                        people.add(getHourlyAdministrator(jsonPerson));
                        break;

                }
            }
        }

        return people;
    }

    public static Person getPerson(JSONObject jsonPerson) {
//        JSONObject jsonPerson = new JSONObject(json);
        int personId = jsonPerson.getInt("personId");
        String lastName = jsonPerson.getString("lastName");
        String firstName = jsonPerson.getString("firstName");
        String userName = jsonPerson.getString("userName");

        Person person = null;
         person = new Person(personId, firstName, lastName, userName);
        return person;
    }

    public static Tenant getTenant(JSONObject jsonTenant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy/MM/dd");

//        JSONObject jsonTenant = new JSONObject(json);
        int personId = jsonTenant.getInt("personId");
        String lastName = jsonTenant.getString("lastName");
        String firstName = jsonTenant.getString("firstName");
        String userName = jsonTenant.getString("userName");
        String strBirthDate = jsonTenant.getString("birthDate");


        String ssn = jsonTenant.getString("ssn");
        String phone = jsonTenant.getString("phone");
        String employer = jsonTenant.getString("employer");
        String occupation = jsonTenant.getString("occupation");
        String strGrossPay = String.valueOf (jsonTenant.getDouble("grossPay"));
        String strEmploymentStartDate = jsonTenant.getString("employmentStartDate");

        LocalDate birthDate = LocalDate.parse(strBirthDate, formatter);
        LocalDateTime birthDateTime = LocalDateTime.of(birthDate, LocalTime.of(0, 0));

        double grossPay = Double.parseDouble(strGrossPay);
        LocalDate employmentStartDate = LocalDate.parse(strEmploymentStartDate, formatter);
        LocalDateTime employmentStartDateTime = LocalDateTime.of(employmentStartDate, LocalTime.of(0,0));

        Tenant tenant = null;
        tenant = new Tenant(personId, firstName, lastName, userName,birthDateTime, ssn, phone, employer, occupation, grossPay, employmentStartDateTime);
        return tenant;
    }

    public static ContractAdministrator getContractAdministrator(JSONObject jsonContractAdmin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy/MM/dd");

//        JSONObject jsonContractAdmin = new JSONObject(json);
        int personId = jsonContractAdmin.getInt("personId");
        String lastName = jsonContractAdmin.getString("lastName");
        String firstName = jsonContractAdmin.getString("firstName");
        String userName = jsonContractAdmin.getString("userName");
        String strBirthDate = jsonContractAdmin.getString("birthDate");

        String ssn = jsonContractAdmin.getString("ssn");
        String phone = jsonContractAdmin.getString("phone");
        String strEmploymentStartDate = jsonContractAdmin.getString("employmentStartDate");
        double monthlyRate = jsonContractAdmin.getDouble("monthlyRate");

        LocalDate birthDate = LocalDate.parse(strBirthDate, formatter);
        LocalDateTime birthDateTime = LocalDateTime.of(birthDate, LocalTime.of(0, 0));

        LocalDate employmentStartDate = LocalDate.parse(strEmploymentStartDate, formatter);
        LocalDateTime employmentStartDateTime = LocalDateTime.of(employmentStartDate, LocalTime.of(0, 0));

        ContractAdministrator contractAdministrator = new ContractAdministrator(personId, firstName, lastName, userName, birthDateTime, ssn, phone, employmentStartDateTime, monthlyRate);
        return contractAdministrator;
    }


    public static HourlyAdministrator getHourlyAdministrator(JSONObject jsonHourlyAdmin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mma");


//        JSONObject obj = new JSONObject(json);
//        JSONObject jsonHourlyAdmin = obj.getJSONObject("hourlyAdministrator");
        int personId = jsonHourlyAdmin.getInt("personId");
        String lastName = jsonHourlyAdmin.getString("lastName");
        String firstName = jsonHourlyAdmin.getString("firstName");
        String userName = jsonHourlyAdmin.getString("userName");
        String strBirthDate = jsonHourlyAdmin.getString("birthDate");

        String ssn = jsonHourlyAdmin.getString("ssn");
        String phone = jsonHourlyAdmin.getString("phone");
        String strEmploymentStartDate = jsonHourlyAdmin.getString("employmentStartDate");
        double hourlyRate = jsonHourlyAdmin.getDouble("hourlyRate");

        LocalDate birthDate = LocalDate.parse(strBirthDate, formatter);
        LocalDateTime birthDateTime = LocalDateTime.of(birthDate, LocalTime.of(0, 0));

        LocalDate employmentStartDate = LocalDate.parse(strEmploymentStartDate, formatter);
        LocalDateTime employmentStartDateTime = LocalDateTime.of(employmentStartDate, LocalTime.of(0, 0));

//        JSONArray timeCardsArray = obj.getJSONObject("hourlyAdministrator").getJSONArray("timeCards");
//        List<TimeCard> timeCards = new ArrayList<>();
//
//        for (int i = 0; i < timeCardsArray.length(); i++) {
//            JSONObject timeCardObj = timeCardsArray.getJSONObject(i);
//            int id = timeCardObj.getInt("id");
//            String startDateTimeStr = timeCardObj.getString("startDateTime");
//            String endDateTimeStr = timeCardObj.getString("endDateTime");
//
//            LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeStr, formatter);
//            LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeStr, formatter);
//
//            timeCards.add(new TimeCard(id, startDateTime, endDateTime));
//        }

        HourlyAdministrator  hourlyAdministrator = null;
        hourlyAdministrator = new HourlyAdministrator(personId, firstName, lastName, userName,
                birthDateTime, ssn, phone, employmentStartDateTime, hourlyRate);

        //Add TimeCards
        JSONArray jsonTimeCards = jsonHourlyAdmin.getJSONArray("timeCards");
        if(jsonTimeCards != null) {
            for (int i = 0; i < jsonTimeCards.length(); i++) {
                JSONObject jsonTimeCard = (JSONObject) jsonTimeCards.get(i);
                int id = jsonTimeCard.getInt("id");
                LocalDateTime startDateTime =
                        LocalDateTime.parse(jsonTimeCard.getString("startDateTime"), dtFormatter);
                LocalDateTime endDateTime =
                        LocalDateTime.parse(jsonTimeCard.getString("endDateTime"), dtFormatter);
                hourlyAdministrator.addTimeCard(id, startDateTime, endDateTime);
            }
        }
        return hourlyAdministrator;
    }

}
