import api.Course;
import api.MeetingTime;
import api.Section;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;



public class Organizer {

    public static void sort(String data){ ;
        String[] rawCourses = StringUtils.substringsBetween(data, "\"campusLocations\":", "courseNotes");
        for (String r: rawCourses) {
            Course c = new Course();
            c.setTitle(StringUtils.substringBetween(r, "title\":\"", "\","));
            c.setIndex(StringUtils.substringBetween(r, "courseString\":\"", "\","));
            c.setSubjectCode(StringUtils.substringBetween(r, "\"subject\": \"", "\","));
            c.setLevel(StringUtils.substringBetween(r, "level\": \"", "\","));
            c.setSubjectDescription(StringUtils.substringBetween(r, "subjectDescription\":\"", "\","));
            c.setCourseCode(StringUtils.substringBetween(r, "\"course\": \"", "\","));
            c.setCredits(StringUtils.substringBetween(r, "credits\":", ","));
            c.setOpenSections(StringUtils.substringBetween(r, "openSections\":", ","));
            c.setSections(sortSections(StringUtils.substringBetween(r, "\"sections\":", "unitNotes")));
            Parser.masterCourseList.add(c);
        }
    }

    public static ArrayList<Section> sortSections(String rawCourse){
        ArrayList<Section> sections = new ArrayList<Section>();
        rawCourse = rawCourse.replace("\":[", "");
        String rawSections[] = StringUtils.substringsBetween(rawCourse, "sectionEligibility", "]}");

        for (String r: rawSections) {
            Section s = new Section();
            //System.out.println(r);
            s.setSectionID(StringUtils.substringBetween(r, "number\":\"", "\","));
            s.setSectionType(StringUtils.substringBetween(r, "sectionCourseType\":\"", "\","));
            s.setStatus(StringUtils.substringBetween(r, "openStatusText\":\"", "\","));
            s.setExamCode(StringUtils.substringBetween(r, "examCode\":\"", "\","));
            s.setMeetingTimes(sortMeetingTimes(StringUtils.substringBetween(r, "meetingTimes", "]")));
            sections.add(s);
            Parser.masterSectionList.add(s);
        }

        return sections;
    }

    public static ArrayList<MeetingTime> sortMeetingTimes(String section){
        //System.err.println(section);
        ArrayList<MeetingTime> meetingTimes = new ArrayList<MeetingTime>();
        String times[] = StringUtils.substringsBetween(section, "{", "}");
        for (String t: times) {
                MeetingTime m = new MeetingTime();
                m.setCampusName(StringUtils.substringBetween(t, "campusName\":\"", "\","));
                m.setCampusAbbrev(StringUtils.substringBetween(t, "campusAbbrev\":\"", "\","));
                m.setBuildingCode(StringUtils.substringBetween(t, "buildingCode\":\"", "\","));
                m.setRoomNumber(StringUtils.substringBetween(t, "roomNumber\":\"", "\","));
                m.setMeetingDay(StringUtils.substringBetween(t, "meetingDay\":\"", "\","));
                m.setStartTime(StringUtils.substringBetween(t, "startTimeMilitary\":\"", "\","));
                m.setEndTime(StringUtils.substringBetween(t, "endTimeMilitary\":\"", "\","));
                meetingTimes.add(m);
                Parser.masterMeetingTimeList.add(m);
        }

        return meetingTimes;
    }

}
