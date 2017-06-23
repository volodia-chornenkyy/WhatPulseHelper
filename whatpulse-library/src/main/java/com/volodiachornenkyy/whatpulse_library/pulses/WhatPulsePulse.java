package com.volodiachornenkyy.whatpulse_library.pulses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WhatPulsePulse {

    @JsonProperty("Timedate")
    private String timeDate;

    @JsonProperty("Timestamp")
    private long timestamp;

    @JsonProperty("UserId")
    private String userId;

    @JsonProperty("Username")
    private String username;

    @JsonProperty("Computer")
    private String computer;

    @JsonProperty("OS")
    private String os;

    @JsonProperty("Keys")
    private String keys;

    @JsonProperty("Clicks")
    private String clicks;

    @JsonProperty("DownloadMB")
    private long downloadMb;

    @JsonProperty("UploadMB")
    private long uploadMb;

    @JsonProperty("Download")
    private String download;

    @JsonProperty("Upload")
    private String upload;

    @JsonProperty("UptimeSeconds")
    private long uptimeSeconds;

    @JsonProperty("UptimeShort")
    private String uptimeShort;

    @JsonProperty("UptimeLong")
    private String uptimeLong;

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComputer() {
        return computer;
    }

    public void setComputer(String computer) {
        this.computer = computer;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public long getDownloadMb() {
        return downloadMb;
    }

    public void setDownloadMb(long downloadMb) {
        this.downloadMb = downloadMb;
    }

    public long getUploadMb() {
        return uploadMb;
    }

    public void setUploadMb(long uploadMb) {
        this.uploadMb = uploadMb;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public long getUptimeSeconds() {
        return uptimeSeconds;
    }

    public void setUptimeSeconds(long uptimeSeconds) {
        this.uptimeSeconds = uptimeSeconds;
    }

    public String getUptimeShort() {
        return uptimeShort;
    }

    public void setUptimeShort(String uptimeShort) {
        this.uptimeShort = uptimeShort;
    }

    public String getUptimeLong() {
        return uptimeLong;
    }

    public void setUptimeLong(String uptimeLong) {
        this.uptimeLong = uptimeLong;
    }

    @Override
    public String toString() {
        return "WhatPulsePulse{" +
                "timeDate='" + timeDate + '\'' +
                ", timestamp=" + timestamp +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", computer='" + computer + '\'' +
                ", os='" + os + '\'' +
                ", keys='" + keys + '\'' +
                ", clicks='" + clicks + '\'' +
                ", downloadMb=" + downloadMb +
                ", uploadMb=" + uploadMb +
                ", download='" + download + '\'' +
                ", upload='" + upload + '\'' +
                ", uptimeSeconds=" + uptimeSeconds +
                ", uptimeShort='" + uptimeShort + '\'' +
                ", uptimeLong='" + uptimeLong + '\'' +
                '}';
    }
}
