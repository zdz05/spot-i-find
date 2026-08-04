package org.example.spotifindbackend.song;

import jakarta.persistence.*;

@Entity
@Table(name = "song_details")
public class SongDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer position;
    private String positionChange;
    private String artistAndTitle;
    private Double days;
    private Double peakPosition;
    private String peakCount;
    private Double streams;
    private Double streamsChange;
    private Double sevenDayStreams;
    private Double sevenDayChange;
    private Double totalStreams;
    private String countryUrl;

    public SongDetails() {
    }

    public SongDetails(String artistAndTitle) {
        this.artistAndTitle = artistAndTitle;
    }

    public SongDetails(Integer position, String positionChange, String artistAndTitle, double days, double peakPosition, String peakCount, double streams, double streamsChange, double sevenDayStreams, double sevenDayChange, double totalStreams, String countryUrl) {
        this.position = position;
        this.positionChange = positionChange;
        this.artistAndTitle = artistAndTitle;
        this.days = days;
        this.peakPosition = peakPosition;
        this.peakCount = peakCount;
        this.streams = streams;
        this.streamsChange = streamsChange;
        this.sevenDayStreams = sevenDayStreams;
        this.sevenDayChange = sevenDayChange;
        this.totalStreams = totalStreams;
        this.countryUrl = countryUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getPositionChange() {
        return positionChange;
    }

    public void setPositionChange(String positionChange) {
        this.positionChange = positionChange;
    }

    public String getArtistAndTitle() {
        return artistAndTitle;
    }

    public void setArtistAndTitle(String artistAndTitle) {
        this.artistAndTitle = artistAndTitle;
    }

    public double getDays() {
        return days;
    }

    public void setDays(double days) {
        this.days = days;
    }

    public double getPeakPosition() {
        return peakPosition;
    }

    public void setPeakPosition(double peakPosition) {
        this.peakPosition = peakPosition;
    }

    public String getPeakCount() {
        return peakCount;
    }

    public void setPeakCount(String peakCount) {
        this.peakCount = peakCount;
    }

    public double getStreams() {
        return streams;
    }

    public void setStreams(double streams) {
        this.streams = streams;
    }

    public double getStreamsChange() {
        return streamsChange;
    }

    public void setStreamsChange(double streamsChange) {
        this.streamsChange = streamsChange;
    }

    public double getSevenDayStreams() {
        return sevenDayStreams;
    }

    public void setSevenDayStreams(double sevenDayStreams) {
        this.sevenDayStreams = sevenDayStreams;
    }

    public double getSevenDayChange() {
        return sevenDayChange;
    }

    public void setSevenDayChange(double sevenDayChange) {
        this.sevenDayChange = sevenDayChange;
    }

    public double getTotalStreams() {
        return totalStreams;
    }

    public void setTotalStreams(double totalStreams) {
        this.totalStreams = totalStreams;
    }

    public String getCountryUrl() {
        return countryUrl;
    }

    public void setCountryUrl(String countryUrl) {
        this.countryUrl = countryUrl;
    }

    @Override
    public String toString() {
        return "SongDetails{" +
                "id=" + id +
                ", position=" + position +
                ", positionChange='" + positionChange + '\'' +
                ", artistAndTitle='" + artistAndTitle + '\'' +
                ", days=" + days +
                ", peakPosition=" + peakPosition +
                ", peakCount='" + peakCount + '\'' +
                ", streams=" + streams +
                ", streamsChange=" + streamsChange +
                ", sevenDayStreams=" + sevenDayStreams +
                ", sevenDayChange=" + sevenDayChange +
                ", totalStreams=" + totalStreams +
                ", countryUrl='" + countryUrl + '\'' +
                '}';
    }
}
