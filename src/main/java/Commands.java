import api.Course;

public class Commands {

    public static void rucore(){
        for (Course c: Parser.masterCourseList) {
            MongoDB.insertCoreClassData(c);
        }
    }
}
