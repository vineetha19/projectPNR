package com.vmax.railwayinfo;

/**
 * Created by welcome on 3/29/2018.
 */

public class PNRBean {

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getJourneyClassCode() {
        return journeyClassCode;
    }

    public void setJourneyClassCode(String journeyClassCode) {
        this.journeyClassCode = journeyClassCode;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getBoardingPoint() {
        return boardingPoint;
    }

    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    private String doj;

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    private String pnr;
    private String fromName;
    private String toName;
    private String journeyClassCode;
    private String trainNumber;
    private String boardingPoint;

    public String getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(String trainNum) {
        this.trainNum = trainNum;
    }

    private String trainNum;

    public String getTotalPassengers() {
        return totalPassengers;
    }

    public void setTotalPassengers(String totalPassengers) {
        this.totalPassengers = totalPassengers;
    }

    private String totalPassengers;
}
