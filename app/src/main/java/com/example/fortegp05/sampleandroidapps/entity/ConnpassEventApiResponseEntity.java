package com.example.fortegp05.sampleandroidapps.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ConnpassEventApiResponseEntity {

    Integer results_returned;
    Integer results_available;
    Integer results_start;
    ArrayList<Event> events;

    public class Event {
        Integer event_id;
        String title;
        @SerializedName("catch")
        String catcc;
        String description;
        String event_url;
        String hash_tag;
        String started_at;
        String ended_at;
        Integer limit;
        String event_type;
        Series series;
        String address;
        String place;
        Float lat;
        Float lon;
        Integer owner_id;
        String owner_nickname;
        String owner_display_name;
        Integer accepted;
        Integer waiting;
        String updated_at;

        public Integer getEvent_id() {
            return event_id;
        }

        public String getTitle() {
            return title;
        }

        public String getCatcc() {
            return catcc;
        }

        public String getDescription() {
            return description;
        }

        public String getEvent_url() {
            return event_url;
        }

        public String getHash_tag() {
            return hash_tag;
        }

        public String getStarted_at() {
            return started_at;
        }

        public String getEnded_at() {
            return ended_at;
        }

        public Integer getLimit() {
            return limit;
        }

        public String getEvent_type() {
            return event_type;
        }

        public Series getSeries() {
            return series;
        }

        public String getAddress() {
            return address;
        }

        public String getPlace() {
            return place;
        }

        public Float getLat() {
            return lat;
        }

        public Float getLon() {
            return lon;
        }

        public Integer getOwner_id() {
            return owner_id;
        }

        public String getOwner_nickname() {
            return owner_nickname;
        }

        public String getOwner_display_name() {
            return owner_display_name;
        }

        public Integer getAccepted() {
            return accepted;
        }

        public Integer getWaiting() {
            return waiting;
        }

        public String getUpdated_at() {
            return updated_at;
        }
    }

    public class Series {
        Integer id;
        String title;
        String url;

        public Integer getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
