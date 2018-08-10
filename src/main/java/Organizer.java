import api.Course;
import api.MeetingTime;
import api.Section;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;



public class Organizer {

    public static void sort(String data){
        String[] rawCourses = StringUtils.substringsBetween(data, "\"campusLocations\":", "courseNotes");
        for (String r: rawCourses) {
            Course c = new Course();
            if(StringUtils.substringBetween(r, "expandedTitle\":\"", "\",").length() == 0){
                c.setTitle(StringUtils.substringBetween(r, "title\":\"", "\","));
            } else {
                c.setTitle(StringUtils.substringBetween(r, "expandedTitle\":\"", "\","));
            }
            c.setIndex(StringUtils.substringBetween(r, "courseString\":\"", "\","));
            c.setSubjectCode(StringUtils.substringBetween(r, "\"subject\": \"", "\","));
            c.setLevel(StringUtils.substringBetween(r, "level\": \"", "\","));
            c.setSubjectDescription(StringUtils.substringBetween(r, "subjectDescription\":\"", "\","));
            c.setCourseCode(StringUtils.substringBetween(r, "\"course\": \"", "\","));
            //c.setCoreCode(StringUtils.substringBetween(r, "coreCode\":\"", "\""));
            if(StringUtils.substringBetween(r, "credits\":", ",").equals("null")){
                c.setCredits("0");
            } else {
                c.setCredits(StringUtils.substringBetween(r, "credits\":", ","));
            }
            c.setOpenSections(StringUtils.substringBetween(r, "openSections\":", ","));

            c.setSections(sortSections(c.getIndex(), StringUtils.substringBetween(r, "\"sections\":", "unitNotes")));
            if(StringUtils.substringBetween(r, "coreCodes\":[", "]").length() > 0){
                c.setSatisfyCore(true);
                c.setCoreCodes(sortCoreCodes(StringUtils.substringBetween(r, "coreCodes\":[", "]")));
            } else {
                c.setSatisfyCore(false);
                ArrayList<String> emptyCodes = new ArrayList<String>();
                c.setCoreCodes(emptyCodes);
            }
            System.out.println(r);

            //System.out.println(StringUtils.substringAfter(r, "coreCodes\":["));
            //System.out.println(StringUtils.substringBetween(r, "coreCodes\":[", "]").length());


            Parser.masterCourseList.add(c);
        }
    }

    public static ArrayList<String> sortCoreCodes(String data){
        ArrayList<String> ccs = new ArrayList<String>();
        System.out.println(data);
        String codes[] = StringUtils.substringsBetween(data, "description\":\"", "\"");
        for (String c: codes) {
            if(c.equals("Contemporary Challenges")){
                ccs.add("CC");
            }
            if(c.equals("Historical Analysis")){
                ccs.add("HST");
            }
            if(c.equals("Social Analysis")){
                ccs.add("SCL");
            }
            if(c.equals("Natural Sciences")){
                ccs.add("NS");
            }
            if(c.equals("Quantitative Information")){
                ccs.add("QQ");
            }
            if(c.equals("Philosophical and Theoretical Issues")){
                ccs.add("AHo");
            }
            if(c.equals("Nature of Languages")){
                ccs.add("AHq");
            }
            if(c.equals("Mathematical or Formal Reasoning")){
                ccs.add("QR");
            }
            if(c.equals("Critical Creative Expression")){
                ccs.add("AHr");
            }
            if(c.equals("Arts and Literatures")){
                ccs.add("AHp");
            }
            if(c.equals("Writing and Communication in a Discipline")){
                ccs.add("WCd");
            }
            if(c.equals("Writing and Communication, Revision")){
                ccs.add("WCr");
            }
            if(c.equals("Information Technology and Research")){
                ccs.add("ITR");
            }

        }
        return ccs;
    }

    public static ArrayList<Section> sortSections(String cindex, String rawCourse){
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
            s.setMeetingTimes(sortMeetingTimes(cindex, StringUtils.substringBetween(r, "meetingTimes", "]")));
            sections.add(s);
            Parser.masterSectionList.add(s);
        }

        return sections;
    }

    public static ArrayList<MeetingTime> sortMeetingTimes(String cindex, String section){
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
                m.setTimeUnit(m.getStartTime() + " - " + m.getEndTime());
                //m.setTimeUnit(cindex + " - " + m.getMeetingDay() + " " + m.getStartTime() + " - " + m.getEndTime());
                meetingTimes.add(m);
                Parser.masterMeetingTimeList.add(m);
        }

        return meetingTimes;
    }

}
