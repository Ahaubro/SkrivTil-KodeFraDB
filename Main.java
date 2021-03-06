package com.company;

import java.sql.*;
import java.util.*;
import java.io.*;

public class Main {
    public static void main (String [] args) throws FileNotFoundException, SQLException {

        ArrayList<Person> listFraDB = new ArrayList<>();
        ArrayList<Person> list = new ArrayList<>();
        ArrayList<Contract> contracts = new ArrayList<>();
        ArrayList<LuxuryCar> luksusListen = new ArrayList<>();
        ArrayList<SportCar> sportList = new ArrayList<>();
        ArrayList<FamilyCar> familyCars = new ArrayList<>();
        ArrayList<Car> car = new ArrayList<>();

        læsFraDBPersonTilArray(list);

        boolean stop = true;
        Scanner scan = new Scanner(System.in);

        System.out.println("Heeelllo friend! There is " + list.size() + " Costumer and " + car.size() + " Cars in our data-sheet.. ");
        while(stop){
            boolean stopcase = true;
            System.out.println("\n ****** The Menu ******:\n1. Create private or company rental \n2. Create fam, sport or luxury car  \n3. Print car or person \n4. Make Contract \n5. Quit...");

            switch(scan.next()){
                case "1":
                    while(stopcase){
                        System.out.println("Private or company");
                        String svar = scan.next();
                        if(svar.equalsIgnoreCase("Private")){
                            createPrivateRental(scan, listFraDB);
                            stopcase = false;
                        }else if(svar.equalsIgnoreCase("Company")){
                            createCompanyRental(scan, listFraDB);
                            stopcase = false;
                        }else if(svar.equalsIgnoreCase("Quit")){
                            stopcase = false;
                        }else{
                            System.out.println("Sorry something wrent wrong - Try again my friend !!");
                        }
                    }
                    break;

                case "2":
                    System.out.println("Create Sports car, Family car or Luxury car");

                    while(stopcase){
                        String svar = scan.nextLine();

                        if(svar.equalsIgnoreCase("Family Car")){
                            createFamilyCar(scan, familyCars);
                            stopcase = false;
                        }else if(svar.equalsIgnoreCase("Sports Car")){
                            createSportCar(scan, sportList);
                            stopcase = false;
                        }else if(svar.equalsIgnoreCase("Luxury Car")){
                            createLuxuryCar(scan, luksusListen);
                            stopcase = false;
                        }else if(svar.equalsIgnoreCase("Quit")){
                            stopcase = false;
                        }else{
                            System.out.println("Sorry something wrent wrong - Try again my friend !!");
                        }
                    }
                    break;


                case "3":
                    System.out.println("What do you want to print? \"Person\" or \"Car\"?");
                    while(stopcase){

                        String svar = scan.next();
                        if(svar.equalsIgnoreCase("Person")){
                            System.out.println("Select what you want to print:\n1. All persons\n2. All private persons\n3. All company persons\n4. All Persons in database");
                            printPerson(listFraDB, scan);
                            stopcase = false;
                        }else if(svar.equalsIgnoreCase("Car")){
                            System.out.println("Select what you want to print:\n1. All Cars\n2. All Family Cars in database \n3. All Sports Cars in database\n4. All Luxury Cars in database");
                            printCars(car, scan);
                            stopcase = false;
                        }else{
                            System.out.println("You typed something wrong, try again");
                        }
                    }


                    break;

                case "4":
                    while(stopcase){
                        System.out.println("Create Conctarct Yes/No");
                        String svar = scan.next();
                        if(svar.equalsIgnoreCase("Yes")){
                            makeContract(scan, listFraDB, car, contracts);
                            stopcase = false;
                        }else if(svar.equalsIgnoreCase("No")){
                            stopcase = false;
                        }else if(svar.equalsIgnoreCase("Quit")){
                            stopcase = false;

                        }}

                    break;


                case "5":
                    System.out.println("Programmet lukkes");
                    //writeToFile(listFraDB);
                    //writeToCarFile(car);
                    stop = false;
                    break;

                default:
                    System.out.println("Sorry something wrent wrong - Try again my friend !!");
                    break;
            }
        }

    }


    //Metoder
    public static void makeContract(Scanner scan, ArrayList<Person> list, ArrayList <Car> carmodellist, ArrayList<Contract> contraclist) throws FileNotFoundException, SQLException {
        //PERSON
        læsFraDBPersonTilArray(list);
        System.out.println("Choose Costumer by number");

        for(int i = 0; i < list.size(); i++){
            System.out.println(i + ". " + list.get(i).nameOfDriver);
        }

        int costumerchoise = scan.nextInt();
        int costumerid = list.get(costumerchoise).id;

        //CAR

        System.out.println("Choose Car by number");

        for(int i = 0; i < carmodellist.size(); i++){
            Car car = carmodellist.get(i);
            System.out.println(i + ". " + car.brand + " - " + car.model);
        }

        int carchoise = scan.nextInt();
        String carmodel = carmodellist.get(carchoise).model;

        System.out.println("Enter from-date & -time ");
        String fromdate = scan.next();

        System.out.println("Enter to-date & -time ");
        String todate = scan.next();

        System.out.println("Enter max km. ");
        int maxkm = scan.nextInt();

        System.out.println("Enter km. ");
        int km = scan.nextInt();

        System.out.println("Enter registration number ");
        String registrationnumber = scan.next();

        Contract newContract = new Contract (costumerid, carmodel, fromdate, todate, maxkm, km, registrationnumber);
        contraclist.add(newContract);
        objectTilDBContract(contraclist);

    }

    public static void createPrivateRental(Scanner scan, ArrayList<Person> list) throws FileNotFoundException, SQLException {
        System.out.println("Remember to exchange space with \"-\" ");

        System.out.println("Enter name of driver: ");
        String temp = scan.nextLine();
        String nameOfDriver = scan.nextLine();

        System.out.println("Enter address: ");
        String temp1 = scan.nextLine();
        String adress = scan.nextLine();

        System.out.println("Enter postnumber: ");
        int postNumber = scan.nextInt();

        System.out.println("Enter city: ");
        String city = scan.next();

        System.out.println("Enter mobilenumber: ");
        int mobilenumber = scan.nextInt();

        System.out.println("Enter phone number: ");
        int phone = scan.nextInt();

        System.out.println("Enter e-mail: ");
        String eMail = scan.next();

        System.out.println("Enter drivers licence number; ");
        String dln = scan.next();

        System.out.println("Enter driver since date");
        String dsd = scan.next();

        //laver et kunde ID her:
        String symbol = "P";
        int id = list.size() +1;

        PrivateRental pr = new PrivateRental(symbol, id, nameOfDriver, adress, postNumber, city, mobilenumber, phone, eMail, dln, dsd);
        list.add(pr);
        objectTilDBPerson(list);
    }


    public static void createCompanyRental(Scanner scan, ArrayList<Person> list) throws FileNotFoundException, SQLException {
        System.out.println("Remember to exchange space with \"-\" ");
        System.out.println("Enter name of driver: ");
        String temp = scan.nextLine();
        String nameOfDriver = scan.nextLine();

        System.out.println("Enter address: ");
        String adress = scan.nextLine();

        System.out.println("Enter postnumber: ");
        int postNumber = scan.nextInt();

        System.out.println("Enter city: ");
        String city = scan.next();

        System.out.println("Enter mobilenumber: ");
        int mobilenumber = scan.nextInt();

        System.out.println("Enter phone number: ");
        int phone = scan.nextInt();

        System.out.println("Enter e-mail: ");
        String eMail = scan.next();

        System.out.println("Enter company name: ");
        String cn = scan.next();

        System.out.println("Enter company address: ");
        String ca = scan.next();

        System.out.println("Enter company phone number: ");
        int cnr = scan.nextInt();

        System.out.println("Enter company CRN: ");
        int crn = scan.nextInt();

        //laver et kunde ID her:
        String symbol = "C";
        int id = list.size();

        CompanyRental cr = new CompanyRental(symbol, id, nameOfDriver, adress, postNumber, city, mobilenumber, phone, eMail, cn, ca, cnr, crn);
        list.add(cr); // Her tilføjes objectet cr som er af typen companyrental som er subclass til superclassen Person til arraylisten
        //writeToFile(cr1);//Her gemmes Arraylisten til filen

        objectTilDBPerson(list);

    }

    public static void createSportCar(Scanner scan, ArrayList<SportCar> sportList) throws SQLException {
        System.out.println("Remember to exchange space with \"-\" ");
        String type = "SportsCar";
        System.out.println("Enter what brand you want: ");
        String brand = scan.next();

        System.out.println("Enter what model you want: ");
        String model = scan.next();

        System.out.println("Enter what fueltype you want: ");
        String fuelType = scan.next();

        System.out.println("Enter licens plate number:");
        String licensPlate = scan.next();


        System.out.println("Enter registration month/year: ");
        String regYearAndMonth = scan.next();

        System.out.println("Enter distance driven: ");
        int drivenKM = scan.nextInt();

        System.out.println("Enter the geartype: ");
        String gearType = scan.next();

        System.out.println("Enter amount of horsepower: ");
        String horsePower = scan.next();

        SportCar sc = new SportCar(type, brand, model, fuelType, licensPlate, regYearAndMonth, drivenKM, gearType, horsePower);
        sportList.add(sc);

        objectTilDBSportCar(sportList);

    }

    public static void createFamilyCar(Scanner scan, ArrayList<FamilyCar> familyCars) throws SQLException {
        System.out.println("Remember to exchange space with \"-\" ");
        String type = "FamilyCar";
        System.out.println("Enter what brand you want: ");
        String brand = scan.next();

        System.out.println("Enter what model you want: ");
        String model = scan.next();

        System.out.println("Enter what fueltype you want: ");
        String fuelType = scan.next();

        System.out.println("Enter license plate number: ");
        String plate = scan.next();

        System.out.println("Enter registration month/year: ");
        String regYearAndMonth = scan.next();

        System.out.println("Enter distance driven: ");
        int drivenKM = scan.nextInt();

        System.out.println("Enter the geartype: ");
        String gearType = scan.next();

        System.out.println("Does the car have Air Con?: ");
        String hasAirCon = scan.next();

        System.out.println("Does the car have Cruise Control: ");
        String hasCC = scan.next();

        System.out.println("Enter the amount of seats: ");
        String seatNumber = scan.next();

        FamilyCar fc = new FamilyCar(type, brand, model, fuelType, plate, regYearAndMonth, drivenKM, gearType, hasAirCon, hasCC, seatNumber);
        familyCars.add(fc);
        System.out.println(brand + " " + model + " have been created");

        objectTilDBFamilyCar(familyCars);
    }

    public static void createLuxuryCar(Scanner scan, ArrayList<LuxuryCar> luksusListen) throws SQLException {
        System.out.println("Remember to exchange space with \"-\" ");
        String type = "LuxuryCar";
        System.out.println("Enter what brand you want: ");
        String brand = scan.next();

        System.out.println("Enter what model you want: ");
        String model = scan.next();

        System.out.println("Enter what fueltype you want: ");
        String fuelType = scan.next();

        System.out.println("Enter license plate number: ");
        String licensePlate = scan.next();


        System.out.println("Enter registration month/year: ");
        String regYearAndMonth = scan.next();

        System.out.println("Enter distance driven: ");
        int drivenKM = scan.nextInt();

        System.out.println("Enter the geartype: ");
        String gearType = scan.next();

        System.out.println("Does the car have aircorndition? ");
        String airCon = scan.next();

        System.out.println("Enter CC size: ");
        String hasCC = scan.next();

        System.out.println("Enter type of interior: ");
        String interior = scan.next();

        System.out.println("Enter CCM size: ");
        String CCM = scan.next();

        LuxuryCar lc = new LuxuryCar(type, brand, model, fuelType, licensePlate, regYearAndMonth, drivenKM, gearType, airCon, hasCC, interior, CCM);
        luksusListen.add(lc);

        objectTilDBLuxCar(luksusListen);
    }

    //tager alle værdierne ud af en person og gemmer det på en linje, hvert field er sepererret af en "|"
    /*public static void writeToFile(ArrayList<Person> list)throws FileNotFoundException{
        PrintStream write = new PrintStream(new File("Customers.txt"));//Printstream sætter det over i filen

        for(Person per:list){
            write.println(per.toFileFormat());

        }
        write.close();
    }

    public static void writeToFileContract(ArrayList<Contract> list)throws FileNotFoundException{
        PrintStream write = new PrintStream(new File("Contracts.txt"));

        for(Contract contract:list){
            write.println(contract.toFileFormat());

        }
        write.close();
    }*/

    // Starter programmet op i den state som den sluttede i
    //Først tager den hele filen ind i en scanner. Har den en linje vil vi godt tage fat i den
    /*public static ArrayList<Person> readFromFile()throws FileNotFoundException{

        Scanner scan = new Scanner(new File("Customers.txt"));
        ArrayList<Person> list = new ArrayList<>();
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            Scanner lineScan = new Scanner(line);//tager hele linjen ind i en ny scanner

            String type = lineScan.next();
            int id = lineScan.nextInt();
            String nameOfDriver = lineScan.next();
            String adress = lineScan.next();
            int postNumber = lineScan.nextInt();
            String city = lineScan.next();
            int mobilePhone = lineScan.nextInt();
            int phone = lineScan.nextInt();
            String eMail = lineScan.next();

            if(type.equals("PrivateRental")){
                String s = "P";
                String driversLN = lineScan.next();
                String driverSD = lineScan.next();
                PrivateRental per = new PrivateRental(s, id, nameOfDriver, adress, postNumber, city, mobilePhone, phone, eMail, driversLN, driverSD);
                list.add(per);
            }
            else if(type.equals("CompanyRental")){
                String s = "C";
                String companyName = lineScan.next();
                String companyAdress = lineScan.next();
                int companyPhonenumber = lineScan.nextInt();
                int companyCRN = lineScan.nextInt();
                CompanyRental compy = new CompanyRental(s, id, nameOfDriver, adress, postNumber, city, mobilePhone, phone, eMail, companyName, companyAdress , companyPhonenumber , companyCRN );
                list.add(compy);
            } else{
                System.out.println("Sorry! Something went wrong my friend... ");
            }

        }
        return list;

    }

    public static void writeToCarFile(ArrayList<Car> list)throws FileNotFoundException{
        PrintStream write = new PrintStream(new File("Cars.txt"));//Printstream sætter det over i filen

        for(Car per : list){
            write.println(per.type + " " + per.brand + " " + per.model + " "+ per.fuelType + " " + per.plate + " " + per.regYearAndMonth + " " + per.drivenKM + " " + per.gearType);

        }
        write.close();

    }
    public static ArrayList<Car> readFromCarFile()throws FileNotFoundException{
        Scanner scan = new Scanner(new File("Cars.txt"));
        ArrayList<Car> list = new ArrayList<>();
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            Scanner lineScan = new Scanner(line);//tager hele linjen ind i en ny scanner

            String type = lineScan.next();
            String brand = lineScan.next();
            String model = lineScan.next();
            String fuelType = lineScan.next();
            String plate = lineScan.next();
            String regYearAndMonth = lineScan.next();
            int drivenKM = lineScan.nextInt();
            String gearType = lineScan.next();


            Car car = new Car(type, brand, model, fuelType, plate, regYearAndMonth, drivenKM, gearType);
            list.add(car);
        }
        return list;

    }
/*   public static void printMyCar(ArrayList<Car> l1){
      for(int i = 0; i < l1.size(); i++){
         System.out.println(l1.get(i));
         System.out.println("\n---------------------\n");
      }
   }
   public static void printMyPerson(ArrayList<Person> l1){
      for(int i = 0; i < l1.size(); i++){
         System.out.println(l1.get(i));
         System.out.println("\n---------------------\n");
      }
   }*/

    public static void printPerson(ArrayList<Person> list, Scanner scan){
        String svar = scan.next();
        if(svar.equals("1")){
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i));
                System.out.println("\n---------------------\n");
            }
        }else if(svar.equals("2")){
            for(Person st : list){
                if(st.symbol.startsWith("P")){
                    System.out.println(st);
                    System.out.println("\n---------------------\n");
                }
            }
        }else if(svar.equalsIgnoreCase("3")){
            for(Person st : list){
                if(st.symbol.startsWith("C")){
                    System.out.println(st);
                    System.out.println("\n---------------------\n");
                }

            }
        } else if(svar.equalsIgnoreCase("4")) {
            læsFraDBPersonMedPrint(list);
        } else{
            System.out.println("You typed something wrong, try again.");
        }
    }

    public static void printCars(ArrayList<Car> c, Scanner scan){
        String svar = scan.next();
        if(svar.equalsIgnoreCase("1")){
            for(int i = 0; i < c.size(); i++){
                System.out.println(c.get(i));
                System.out.println("\n---------------------\n");
            }
        }else if(svar.equalsIgnoreCase("2")){
            læsFraDBFamily();
        }else if(svar.equalsIgnoreCase("3")){
            læsFraDBSport();
        }else if(svar.equalsIgnoreCase("4")){
            læsFraDBLux();
        }else{
            System.out.println("You typed something wrong, try again.");
        }
    }

    // Metode der tager en parameter overført ArrayList, og skriver indholdet ind i en database. (Person list)
    public static void objectTilDBPerson(ArrayList<Person> list) throws SQLException {
        try {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://127.0.0.1:3306/kailua?user=Her?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "Her", "0");

            String ind = "INSERT INTO Person (Symbol, nameOfDriver, Address, PostNumber, City, mobilePhone, phone, email)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(ind);

            for(int i = 0; i < list.size(); i++) {
                preparedStmt.setString(1,list.get(i).symbol);
                preparedStmt.setString(2,list.get(i).nameOfDriver);
                preparedStmt.setString(3,list.get(i).adress);
                preparedStmt.setInt   (4,list.get(i).postNumber);
                preparedStmt.setString(5,list.get(i).city);
                preparedStmt.setInt   (6,list.get(i).mobilePhone);
                preparedStmt.setInt   (7,list.get(i).phone);
                preparedStmt.setString(8,list.get(i).eMail);

                preparedStmt.executeUpdate();
            }

            conn.close();

        }catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    // Metode der tager en parameter overført ArrayList, og skriver indholdet ind i en database. (Contracts list)
    public static void objectTilDBContract(ArrayList<Contract> contracts) throws SQLException {
        try {

            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://127.0.0.1:3306/kailua?user=Her?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "Her", "0");

            // Mysql indsæt string
            String ind = "INSERT INTO contracts (CustomerID, CarID, startTime, endTime, maxKM, km, registrationNumber)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(ind);


            for(int i = 0; i < contracts.size(); i++) {
                preparedStmt.setInt   (1,contracts.get(i).costumerid);
                preparedStmt.setString(2,contracts.get(i).carid);
                preparedStmt.setString(3,contracts.get(i).fromdate);
                preparedStmt.setString(4,contracts.get(i).todate);
                preparedStmt.setInt   (5,contracts.get(i).maxkm);
                preparedStmt.setInt   (6,contracts.get(i).km);
                preparedStmt.setString(7,contracts.get(i).registrationnumber);

                // execute the preparedstatement
                preparedStmt.executeUpdate();

            }

            conn.close();

        }catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }


    // Metode der tager en parameter overført ArrayList, og skriver indholdet ind i en database. (luxuryCar list)
    public static void objectTilDBLuxCar(ArrayList<LuxuryCar> luksusListen) throws SQLException {
        try {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://127.0.0.1:3306/kailua?user=Her?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "Her", "0");

            // Mysql indsæt string
            String ind = "INSERT INTO luxurycar (Type, brand, model, fuelType, plate, regYearAndMonth, drivenKM, gearType, hasAirCon, hasCC," +
                    "interior, CCM)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(ind);


            for(int i = 0; i < luksusListen.size(); i++) {
                preparedStmt.setString(1,luksusListen.get(i).type);
                preparedStmt.setString(2,luksusListen.get(i).brand);
                preparedStmt.setString(3,luksusListen.get(i).model);
                preparedStmt.setString(4,luksusListen.get(i).fuelType);
                preparedStmt.setString(5,luksusListen.get(i).plate);
                preparedStmt.setString(6,luksusListen.get(i).regYearAndMonth);
                preparedStmt.setInt   (7,luksusListen.get(i).drivenKM);
                preparedStmt.setString(8,luksusListen.get(i).gearType);
                preparedStmt.setString(9,luksusListen.get(i).hasAirCon);
                preparedStmt.setString(10,luksusListen.get(i).hasCC);
                preparedStmt.setString(11,luksusListen.get(i).interior);
                preparedStmt.setString(12,luksusListen.get(i).CCM);

                // execute the preparedstatement
                preparedStmt.executeUpdate();

            }

            conn.close();

        }catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    // Metode der tager en parameter overført ArrayList, og skriver indholdet ind i en database. (sportsCar list)
    public static void objectTilDBSportCar(ArrayList<SportCar> sportList) throws SQLException {
        try {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://127.0.0.1:3306/kailua?user=Her?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "Her", "0");

            // Mysql indsæt string
            String ind = "INSERT INTO sportcar(Type, brand, model, fuelType, plate, regYearAndMonth, drivenKM, gearType, horsePower)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(ind);


            for(int i = 0; i < sportList.size(); i++) {
                preparedStmt.setString(1,sportList.get(i).type);
                preparedStmt.setString(2,sportList.get(i).brand);
                preparedStmt.setString(3,sportList.get(i).model);
                preparedStmt.setString(4,sportList.get(i).fuelType);
                preparedStmt.setString(5,sportList.get(i).plate);
                preparedStmt.setString(6,sportList.get(i).regYearAndMonth);
                preparedStmt.setInt   (7,sportList.get(i).drivenKM);
                preparedStmt.setString(8,sportList.get(i).gearType);
                preparedStmt.setString(9,sportList.get(i).horsePower);

                // execute the preparedstatement
                preparedStmt.executeUpdate();

            }

            conn.close();

        }catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    // Metode der tager en parameter overført ArrayList, og skriver indholdet ind i en database. (familyCar list)
    public static void objectTilDBFamilyCar(ArrayList<FamilyCar> familyCars) throws SQLException {
        try {
            // For at dette program virker, har jeg været nødt til at specificere database navnet i min url, altså "kailua"
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://127.0.0.1:3306/kailua?user=Her?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "Her", "0");

            // Mysql indsæt string
            String ind = "INSERT INTO familycar(Type, brand, model, fuelType, plate, regYearAndMonth, drivenKM, gearType, hasaircon, hasCC, seatNumber)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(ind);


            for(int i = 0; i < familyCars.size(); i++) {
                preparedStmt.setString(1,familyCars.get(i).type);
                preparedStmt.setString(2,familyCars.get(i).brand);
                preparedStmt.setString(3,familyCars.get(i).model);
                preparedStmt.setString(4,familyCars.get(i).fuelType);
                preparedStmt.setString(5,familyCars.get(i).plate);
                preparedStmt.setString(6,familyCars.get(i).regYearAndMonth);
                preparedStmt.setInt   (7,familyCars.get(i).drivenKM);
                preparedStmt.setString(8,familyCars.get(i).gearType);
                preparedStmt.setString(9,familyCars.get(i).hasAirCon);
                preparedStmt.setString(10,familyCars.get(i).hasCC);
                preparedStmt.setString(11,familyCars.get(i).seatNumber);

                // execute the preparedstatement
                preparedStmt.executeUpdate();

            }

            conn.close();

        }catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    // Metode der opretter person objekter fra databasen, og smider dem ind på vores person ArrayList
    public static void læsFraDBPersonTilArray(ArrayList<Person> listFraDB) {
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/?user=Her?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        try {
            Connection con;
            Statement s = null;
            Class.forName (JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL,"Her","0");
            s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM kailua.person;");

            if (rs != null)
                while (rs.next()) {
                    Person p2 = new Person(rs.getString("Symbol"),rs.getInt("PersonID"),rs.getString("nameOfdriver"), rs.getString("Address"), rs.getInt("PostNumber"), rs.getString("City"), rs.getInt("mobilePhone"), rs.getInt("phone"), rs.getString("Email"));
                    listFraDB.add(p2);
                }
            s.close();
            con.close();
        } catch (SQLException sqlex) {
            try{
                Connection con = null;
                System.out.println("Exception part 1: " + sqlex.getMessage());
                con.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){
                System.out.println("Exception!" + sql.getMessage());
            }
        }
        catch (ClassNotFoundException noClass) {
            System.err.println("Driver Class not found");
            System.out.println(noClass.getMessage());
            System.exit(1);  // terminate program
        }
    }

    // Metode der læser vores personer fra databasen, og printer indholdet i konsolen.
    public static void læsFraDBPersonMedPrint(ArrayList<Person> list) {
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/?user=Her?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        try {
            Connection con;
            Statement s = null;
            Class.forName (JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL,"Her","0");
            s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM kailua.person;");

            if (rs != null)
                while (rs.next()) {
                    System.out.println("\nPersons in the database: " + "\nID: " + rs.getInt("PersonID") + "\nSymbol: " + rs.getString("Symbol") +
                            "\nName of driver: " + rs.getString("nameOfDriver") + "\nAddress: " + rs.getString("Address") +
                            "\nZip code: " + rs.getInt("PostNumber") + "\nCity: " + rs.getString("City") +
                            "\nMobile phonenumber: " + rs.getInt("mobilePhone") + "\nPhonenumber: " + rs.getInt("phone") +
                            "\nEmail: " + rs.getString("email"));

                }
            s.close();
            con.close();
        } catch (SQLException sqlex) {
            try{
                Connection con = null;
                System.out.println("Exception part 1: " + sqlex.getMessage());
                con.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){
                System.out.println("Exception!" + sql.getMessage());
            }
        }
        catch (ClassNotFoundException noClass) {
            System.err.println("Driver Class not found");
            System.out.println(noClass.getMessage());
            System.exit(1);  // terminate program
        }
    }

    // Metode der læser vores luksus biler fra databsen, og printer dem i konsolen.
    public static void læsFraDBLux() {
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/?user=Her?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        try {
            Connection con;
            Statement s = null;
            Class.forName (JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL,"Her","0");
            s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM kailua.luxurycar;");

            if (rs != null)
                while (rs.next()) {
                    System.out.println("\nLuksus biler i databasen: " + "\nID: " + rs.getInt("LuxurycarID") +
                            "\nType: " + rs.getString("Type") + "\nBrand: " + rs.getString("Brand") +
                            "\nModel: " + rs.getString("Model") + "\nFuel type: " + rs.getString("FuelType") +
                            "\nPlate: " + rs.getString("plate") + "\nRegistration year/month: " + rs.getString("regYearAndMonth") +
                            "\nKM Driven: " + rs.getInt("drivenKM") + "\nGear type: " + rs.getString("GearType") +
                            "\nAircondition: " + rs.getString("hasAircon") + "\nCruise controle: " + rs.getString("hasCC") +
                            "\nInterior: " + rs.getString("interior"));
                }
            s.close();
            con.close();
        } catch (SQLException sqlex) {
            try{
                Connection con = null;
                System.out.println("Exception part 1: " + sqlex.getMessage());
                con.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){
                System.out.println("Exception!" + sql.getMessage());
            }
        }
        catch (ClassNotFoundException noClass) {
            System.err.println("Driver Class not found");
            System.out.println(noClass.getMessage());
            System.exit(1);  // terminate program
        }
    }

    // Metode der læser vores sports biler fra databasen og printer dem ud i konsolen.
    public static void læsFraDBSport() {
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/?user=Her?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        try {
            Connection con;
            Statement s = null;
            Class.forName (JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL,"Her","0");
            s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM kailua.sportcar;");

            if (rs != null)
                while (rs.next()) {
                    System.out.println("\nSports biler i databasen: " + "\nID: " + rs.getInt("SportCarID") +
                            "\nType: " + rs.getString("Type") + "\nBrand: " + rs.getString("Brand") +
                            "\nModel " + rs.getString("Model") + "\nFuel type: " + rs.getString("FuelType") +
                            "\nPlate: " + rs.getString("plate") + "\nRegistration year/month: " + rs.getString("regYearAndMonth") +
                            "\nKM driven: " + rs.getInt("drivenKM") + "\nGear type: " + rs.getString("GearType") +
                            "\nHorsepower: " + rs.getString("horsePower"));
                }
            s.close();
            con.close();
        } catch (SQLException sqlex) {
            try{
                Connection con = null;
                System.out.println("Exception part 1: " + sqlex.getMessage());
                con.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){
                System.out.println("Exception!" + sql.getMessage());
            }
        }
        catch (ClassNotFoundException noClass) {
            System.err.println("Driver Class not found");
            System.out.println(noClass.getMessage());
            System.exit(1);  // terminate program
        }
    }

    // Metode der læser vores familie biler fra databsen og printer dem i konsolen.
    public static void læsFraDBFamily() {
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/?user=Her?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        try {
            Connection con;
            Statement s = null;
            Class.forName (JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL,"Her","0");
            s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM kailua.familycar;");

            if (rs != null)
                while (rs.next()) {
                    System.out.println("\nFamilie biler i databasen: " + "\nID: " + rs.getInt("FamilyCarID") +
                            "\nType: " + rs.getString("Type") + "\nBrand: " + rs.getString("Brand") +
                            "\nModel: " + rs.getString("Model") + "\nFuel type: " + rs.getString("FuelType") +
                            "\nPlate: " + rs.getString("plate") + "\nRegistration y/m: " + rs.getString("regYearAndMonth") +
                            "\nKM driven: " + rs.getInt("drivenKM") + "\nGear type: " + rs.getString("GearType") +
                            "\nAircondition: " + rs.getString("hasAircon") + "\nCruise controle: " + rs.getString("hasCC") +
                            "\nNumber of seats: " + rs.getString("seatNumber"));
                }
            s.close();
            con.close();
        } catch (SQLException sqlex) {
            try{
                Connection con = null;
                System.out.println("Exception part 1: " + sqlex.getMessage());
                con.close();
                System.exit(1);  // terminate program
            }
            catch(SQLException sql){
                System.out.println("Exception!" + sql.getMessage());
            }
        }
        catch (ClassNotFoundException noClass) {
            System.err.println("Driver Class not found");
            System.out.println(noClass.getMessage());
            System.exit(1);  // terminate program
        }
    }
}
