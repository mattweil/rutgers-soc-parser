import api.Course;
import api.MeetingTime;

public class Commands {

    public static void rucore(){
        for (Course c: Parser.masterCourseList) {
            MongoDB.insertCoreClassData(c);
        }
    }

    public static void ruclass(){
        MongoDB.connect();
        for (MeetingTime mt: Parser.newTimes) {
            MongoDB.insertClassTime(Parser.year, Parser.semester, Parser.campus, mt);
        }
    }

}
