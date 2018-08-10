package api;

public class MeetingTime {
    public String courseIndex;
    public String campusName;
    public String campusAbbrev;
    public String buildingCode;
    public String roomNumber;
    public String meetingDay;
    public String startTime;
    public String endTime;
    public String timeUnit;

    public String getCourseIndex() {
        return courseIndex;
    }

    public void setCourseIndex(String courseIndex) {
        this.courseIndex = courseIndex;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getCampusAbbrev() {
        return campusAbbrev;
    }

    public void setCampusAbbrev(String campusAbbrev) {
        this.campusAbbrev = campusAbbrev;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getMeetingDay() {
        return meetingDay;
    }

    public void setMeetingDay(String meetingDay) {
        this.meetingDay = meetingDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimeUnit() {
        return timeUnit;
    }
}
