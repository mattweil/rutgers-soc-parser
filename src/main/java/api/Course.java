package api;

import java.util.ArrayList;

public class Course {
    public String title;
    public String index;
    public String level;
    public int schoolCode;
    public boolean satisfyCore;
    public String subjectCode;
    public String courseCode;
    public String credits;
    public String openSections;
    public String subjectDescription;
    public ArrayList<Course> prereqs;
    public ArrayList<Section> sections;
    public ArrayList<String> coreCodes;

    public boolean isSatisfyCore() {
        return satisfyCore;
    }

    public void setSatisfyCore(boolean satisfyCore) {
        this.satisfyCore = satisfyCore;
    }

    public ArrayList<String> getCoreCodes() {
        return coreCodes;
    }

    public void setCoreCodes(ArrayList<String> coreCodes) {
        this.coreCodes = coreCodes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(int schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getOpenSections() {
        return openSections;
    }

    public void setOpenSections(String openSections) {
        this.openSections = openSections;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public ArrayList<Course> getPrereqs() {
        return prereqs;
    }

    public void setPrereqs(ArrayList<Course> prereqs) {
        this.prereqs = prereqs;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }
}
