import api.*;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public static ArrayList<Course> masterCourseList = new ArrayList<Course>();
    public static ArrayList<Section> masterSectionList = new ArrayList<Section>();
    public static ArrayList<MeetingTime> masterMeetingTimeList = new ArrayList<MeetingTime>();
    public static ArrayList<MeetingTime> newTimes = new ArrayList<MeetingTime>();

    public static int year;
    public static int semester;
    public static String campus = "NB";

    public static void main(String[] args) throws IOException {
        Scanner r = new Scanner(System.in);
        System.out.println("RUTGERS SOC PARSER.....");
        System.out.println("Enter year: ");
        year = r.nextInt();
        System.out.println("Enter a semester: ");
        System.out.println("[9 = FALL | 0 = WINTER | 1 = SPRING | 7 = SUMMER]");
        semester = r.nextInt();
        requestAndSortData(year, semester, campus);
        waitForCommand();

        r.close();
    }

    public static void requestAndSortData(int year, int semester, String campus) throws IOException {
        String bulkData = Request.get("courses.gz?year=" + year + "&term=" + semester + "&campus=" + campus);
        Organizer.sort(bulkData);
        removeNull();
    }

    public static void removeNull(){
        for (MeetingTime mt: masterMeetingTimeList) {
            if(mt.getBuildingCode() != null && mt.getMeetingDay() != null && mt.getRoomNumber() != null && mt.getTimeUnit() != null){
               newTimes.add(mt);
            }
        }

    }

    public static void waitForCommand() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String command = input.readLine().toLowerCase();

        if (command.startsWith("rucore")) {
            Commands.rucore();
        }

        if (command.startsWith("ruclass")) {
            Commands.ruclass();
        }
        else {
            System.out.println("Invalid command usage, type 'help' for more information.");
            waitForCommand();
        }



    }



}
