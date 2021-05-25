import core.data.*;
import java.util.*;

public class Part4 {
    
    private String date;
    private double temp;

    public Part4(String date, double actual_mean_temp) {
        this.date = date;
        this.temp = actual_mean_temp;
    }

    public static void average(String date[], double meanTemp[]){
        double winter, summer, spring, fall, winterC, summerC,springC, fallC;
        winter = summer = spring = fall = winterC = summerC = springC = fallC = 0;
        for (int i = 0; i < date.length;i++){
            if (date[i].charAt(5) == '1' && date[i].charAt(6) == '2' || date[i].charAt(5) == '1' || date[i].charAt(5) == '2') {
                winter += meanTemp[i];
                winterC++;
            }
            else if (date[i].charAt(5) == '3' || date[i].charAt(5) == '4' || date[i].charAt(5) == '5') {
                spring += meanTemp[i];
                springC++;
            }
            else if (date[i].charAt(5) == '6' || date[i].charAt(5) == '7' || date[i].charAt(5) == '8') {
                summer += meanTemp[i];
                summerC++;
            }
            else {
                fall += meanTemp[i];
                fallC++;
            }
        }
        System.out.println("Winter: " + (int) (winter/winterC));
        System.out.println("Spring: " + (int) (spring/springC));
        System.out.println("Summer: " + (int) (summer/summerC));
        System.out.println("Fall: " + (int) (fall/fallC));
    }

    public static void main(String[] args) {

        String[] city = new String[]{"KHOU", "KNYC", "KPHL", "KSEA"};

        for (String localCity : city){
            DataSource ds = DataSource.connect(localCity + ".csv"); 
            ds.load();
            ArrayList<Part4> Weather = ds.fetchList("Part4", "date", "actual_mean_temp");
    
            String date[] = new String[Weather.size()];
            double meanTemp[] = new double[Weather.size()];
    
            for (int i =0; i < Weather.size();i++) {
                date[i] = Weather.get(i).date;
                meanTemp[i] = Weather.get(i).temp;
            }
    
            System.out.println("----" + localCity.substring(1) + "----");
            average(date, meanTemp);
        }

        
    }

}
