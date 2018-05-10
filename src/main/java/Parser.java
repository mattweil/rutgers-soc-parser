import api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public static ArrayList<Course> masterCourseList = new ArrayList<Course>();
    public static ArrayList<Section> masterSectionList = new ArrayList<Section>();
    public static ArrayList<MeetingTime> masterMeetingTimeList = new ArrayList<MeetingTime>();
    public static ArrayList<Instructor> masterInstructorList  = new ArrayList<Instructor>();

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
        //System.out.println("Enter a campus: ");

        requestAndSortData(year, semester, campus);

        for (Course c: masterCourseList) {
            System.out.println(c.getIndex());
            ArrayList<Section> sec = c.getSections();
            for (Section s: sec) {
                System.out.println(s.getSectionID());
                ArrayList<MeetingTime> m = s.getMeetingTimes();
                for (MeetingTime top: m) {
                    System.out.println(top.getMeetingDay());
                    System.out.println(top.getStartTime());
                    System.out.println(top.getEndTime());
                }
            }
        }

        r.close();
    }

    public static void requestAndSortData(int year, int semester, String campus) throws IOException {
        String bulkData = Request.get("courses.gz?year=" + year + "&term=" + semester + "&campus=" + campus);
        Organizer.sort(bulkData);
    }

}
