package sample;

import java.time.LocalDate;

public class Dane {
    public String title,description;
    public Object priority;
    public LocalDate date;

    public String toString(){
        return (" Title: "+title+ " Description: "+description+" Priority: "+priority+" Date: "+date);
    }

}

