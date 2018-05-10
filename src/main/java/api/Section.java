package api;

import java.util.ArrayList;

public class Section {
    public String sectionID;
    public String sectionType; //0 - normal, 1 - hybrid, 2 - online
    public String status;
    public ArrayList<MeetingTime> meetingTimes;
    public ArrayList<Instructor> instructors;
    public String examCode;
    public String sectionNotes;
    public String sectionCommments;

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<MeetingTime> getMeetingTimes() {
        return meetingTimes;
    }

    public void setMeetingTimes(ArrayList<MeetingTime> meetingTimes) {
        this.meetingTimes = meetingTimes;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(ArrayList<Instructor> instructors) {
        this.instructors = instructors;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getSectionNotes() {
        return sectionNotes;
    }

    public void setSectionNotes(String sectionNotes) {
        this.sectionNotes = sectionNotes;
    }

    public String getSectionCommments() {
        return sectionCommments;
    }

    public void setSectionCommments(String sectionCommments) {
        this.sectionCommments = sectionCommments;
    }
}
