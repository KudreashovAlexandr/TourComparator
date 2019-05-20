package TourParser;

import me.tongfei.progressbar.ProgressBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args){

        List<Destination> destTest = new ArrayList<Destination>();
        destTest.add(Destination.USA);
        destTest.add(Destination.CZECH);
        destTest.add(Destination.BULGARIA);
        destTest.add(Destination.ROMANIA);
        destTest.add(Destination.MOLDOVA);
        destTest.add(Destination.GERMANY);
        destTest.add(Destination.BELGIUM);
        destTest.add(Destination.FRANCE);
        destTest.add(Destination.GREECE);
        destTest.add(Destination.UKRAINE);
        destTest.add(Destination.TURKEY);
        destTest.add(Destination.AUSTRIA);
        destTest.add(Destination.EGYPT);
        destTest.add(Destination.ITALY);
        destTest.add(Destination.SPAIN);
        destTest.add(Destination.RUSSIA);
        destTest.add(Destination.MONTENEGRO);


        Date d1 = new Date();

        OperatorCocostur ct = new OperatorCocostur();
        OperatorMeteortravel gc = new OperatorMeteortravel();
        OperatorExplore ex = new OperatorExplore();
        OperatorAlpimarin am = new OperatorAlpimarin();
        OperatorCityBreak cb = new OperatorCityBreak();

        ProgressBar pb = new ProgressBar("Find appropriate tours...", 100);

        pb.start();

        List<Tour> output = ct.parseWithParameters(destTest, 0, 100000);
        pb.stepTo(20);
        output.addAll(am.parseWithParameters(destTest, 1, 12000));
        pb.stepTo(40);
        output.addAll(cb.parseWithParameters(destTest, 1, 12000));
        pb.stepTo(60);
        output.addAll(gc.parseWithParameters(destTest, 1, 12000));
        pb.stepTo(80);
        output.addAll(ex.parseWithParameters(destTest, 1, 12000));
        pb.stepTo(100);
        pb.stop();


        for (int i = 0; i < output.size(); i++){
            System.out.println(
                            "\nPrice - " + output.get(i).getPrice() +
                            "\nDestination - " + output.get(i).getDestination() +
                            "\nDaysPeriod - " + output.get(i).getPeriodInDays() +
                            "\nSummary - " + output.get(i).getSummary() +
                            "\nLink - " + output.get(i).getDirectLink()
            );
        }


        System.out.println("Size:" + output.size());
        System.out.println("Start:" + d1.toString());
        System.out.println("Stop:" + new Date().toString());

    }
}
