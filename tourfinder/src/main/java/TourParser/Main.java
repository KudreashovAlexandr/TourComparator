package TourParser;

import me.tongfei.progressbar.ProgressBar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static TourParser.Operator.*;

public class Main {
    public static void main(String[] args){


//
//        OperatorCocostur ct = new OperatorCocostur();
//        OperatorMeteortravel gc = new OperatorMeteortravel();
//        OperatorExplore ex = new OperatorExplore();
//        OperatorAlpimarin am = new OperatorAlpimarin();
//        OperatorCityBreak cb = new OperatorCityBreak();
//
//        ProgressBar pb = new ProgressBar("Find appropriate tours...", 100);
//
//        pb.start();
//
//        List<Tour> output = ct.parseWithParameters(destTest, 0, 100000);
//        pb.stepTo(20);
//        output.addAll(am.parseWithParameters(destTest, 1, 12000));
//        pb.stepTo(40);
//        output.addAll(cb.parseWithParameters(destTest, 1, 12000));
//        pb.stepTo(60);
//        output.addAll(gc.parseWithParameters(destTest, 1, 12000));
//        pb.stepTo(80);
//        output.addAll(ex.parseWithParameters(destTest, 1, 12000));
//        pb.stepTo(100);
//        pb.stop();

        Date d1 = new Date();

        List<Operator> userInputForOperatorsList = Arrays.asList(ALPIMARIN, CITYBREAK, COCOSTUR, EXPLORE, METEORTRAVEL); // this is what I expect to get from user input form of operators selection
        List<Destination> userInputForDestinationList = new ArrayList<Destination>();
        userInputForDestinationList.add(Destination.USA);
        userInputForDestinationList.add(Destination.CZECH);
        userInputForDestinationList.add(Destination.BULGARIA);
        userInputForDestinationList.add(Destination.ROMANIA);
        userInputForDestinationList.add(Destination.MOLDOVA);
        userInputForDestinationList.add(Destination.GERMANY);
        userInputForDestinationList.add(Destination.BELGIUM);
        userInputForDestinationList.add(Destination.FRANCE);
        userInputForDestinationList.add(Destination.GREECE);
        userInputForDestinationList.add(Destination.UKRAINE);
        userInputForDestinationList.add(Destination.TURKEY);
        userInputForDestinationList.add(Destination.AUSTRIA);
        userInputForDestinationList.add(Destination.EGYPT);
        userInputForDestinationList.add(Destination.ITALY);
        userInputForDestinationList.add(Destination.SPAIN);
        userInputForDestinationList.add(Destination.RUSSIA);
        userInputForDestinationList.add(Destination.MONTENEGRO);  // this is what I expect to get from user input form of destinations selection

        int minPrice = 100; // expected to get prom the price selector
        int maxPrice = 1000; // expected to get prom the price selector

        List<Tour> output = new ArrayList<>(Helper.getResults(userInputForOperatorsList, userInputForDestinationList, minPrice, maxPrice)); //expected result for the output

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
