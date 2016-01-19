package routecarboncalculatorapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author 
 */
public class RouteCarbonCalculator {

    private String routeID = "";
    private double distanceByFoot = 0;
    private double distanceByBus = 0;
    private double co2FromBus = 0;
    private double distanceByTram = 0;
    private double co2FromTram = 0;
    private double distanceByTrain = 0;
    private double co2FromTrain = 0;
    private double distanceByMetro = 0;
    private double co2FromMetro = 0;
    private double distanceByFerry = 0;
    private int hopCount = 0;
    private double co2Total = 0;
    private double co2FromCar = 0;

    private static final int busCO2 = 80;
    private static final int tramCO2 = 30;
    private static final int trainCO2 = 22;
    private static final int metroCO2 = 13;
    private static final int carCO2 = 179;

    private void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    private boolean setDistance(int typeOfTravel, double distance) {
        // bus   = [1,3,4,5,8,9,10,11,21,22,23,24,25,36,38,39]
        // train = [12,13]
        // tram  = [2]
        // metro = [6]
        // ferry = [7]

        if (typeOfTravel == 0) {
            this.distanceByFoot += distance;
            return true;
        } else if (typeOfTravel == 1) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 3) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 4) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 5) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 8) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 9) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 10) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 11) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 21) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 22) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 23) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 24) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 25) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 36) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 38) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 39) {
            this.distanceByBus += distance;
            return true;
        } else if (typeOfTravel == 2) {
            this.distanceByTram += distance;
            return true;
        } else if (typeOfTravel == 12) {
            this.distanceByTrain += distance;
            return true;
        } else if (typeOfTravel == 13) {
            this.distanceByTrain += distance;
            return true;
        } else if (typeOfTravel == 6) {
            this.distanceByMetro += distance;
            return true;
        } else if (typeOfTravel == 7) {
            this.distanceByFerry += distance;
            return true;
        } else {
            System.out.println("No such type of travel: " + typeOfTravel);
            return false;
        }
    }

    public void printDistancesToFile(PrintWriter pw) throws IOException {
        calculateCO2ForRoute();

        pw.print(routeID + ";");
        pw.print(this.distanceByFoot + ";");
        pw.print(this.distanceByBus + ";");
        pw.print(this.co2FromBus + ";");
        pw.print(this.distanceByTram + ";");
        pw.print(this.co2FromTram + ";");
        pw.print(this.distanceByTrain + ";");
        pw.print(this.co2FromTrain + ";");
        pw.print(this.distanceByMetro + ";");
        pw.print(this.co2FromMetro + ";");
        pw.print(this.distanceByFerry + ";");
        pw.print(this.hopCount + ";");
        pw.print(this.co2Total + ";");
        pw.print(this.co2FromCar + ";");
        pw.println();
    }

    private void calculateCO2ForRoute() {
        this.co2FromBus = (this.distanceByBus / 1000) * busCO2;
        this.co2FromMetro = (this.distanceByMetro / 1000) * metroCO2;
        this.co2FromTrain = (this.distanceByTrain / 1000) * trainCO2;
        this.co2FromTram = (this.distanceByTram / 1000) * tramCO2;
        this.co2Total = this.co2FromBus + this.co2FromMetro + this.co2FromTrain + this.co2FromTram;
        this.co2FromCar = (this.distanceByFoot + this.distanceByBus + this.distanceByTram + this.distanceByTrain + this.distanceByMetro
                + this.distanceByFerry) / 1000 * carCO2;

    }

    public boolean processRow(String sRow, HashMap<String, Integer> tunnistusTaulukko) {
        Scanner s = new Scanner(sRow);
        ArrayList<String> splitRow = new ArrayList<String>();

        s.useDelimiter(";");

        while (s.hasNext()) {
            splitRow.add(s.next());
        }
        s.close();

        this.routeID = splitRow.get(2) + "_" + splitRow.get(3);

        //Vanhaa mallia: setDistance(0, Double.valueOf(splitRow.get(27)));
        //System.out.println("walk distance: " + splitRow.get(15));
        setDistance(0, Double.valueOf(splitRow.get(14)));

        for (int i = 0; i < splitRow.size() - 1; i++) {
            if (splitRow.get(i).equals("LINE")) {
                try {
                    //System.out.println("1 Kulkumuoto.: " + tunnistusTaulukko.get(splitRow.get(i+4)));
                    //System.out.println("1 Matka......: " + Double.valueOf(splitRow.get(i+3)));
                    //vanha: if (setDistance(tunnistusTaulukko.get(splitRow.get(i+1)), Double.valueOf(splitRow.get(i+4))) == true)
                    if (setDistance(tunnistusTaulukko.get(splitRow.get(i + 4)), Double.valueOf(splitRow.get(i + 3))) == true) {
                        hopCount++;
                    } else {
                        //System.out.println("Fucked up 1");
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                    try {
                        //Pituus ei löytynyt odotetusta paikasta, koitetaan toisaalta.
                        //System.out.println("2 Kulkumuoto.: " + tunnistusTaulukko.get(splitRow.get(i+4)));
                        //System.out.println("2 Matka......: " + Double.valueOf(splitRow.get(i+3)));						
                        if (setDistance(tunnistusTaulukko.get(splitRow.get(i + 4)), Double.valueOf(splitRow.get(i + 3))) == true) {
                            hopCount++;
                        } else {
                            //System.out.println("Fucked up 2");
                            return false;
                        }
                    } catch (Exception ee) {
                        ee.printStackTrace();
                        //System.out.println("Fucked up 3");
                        return false;
                    }

                }
            }
        }
        return true;
    }
}
