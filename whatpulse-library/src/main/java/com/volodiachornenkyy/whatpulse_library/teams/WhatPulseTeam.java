package com.volodiachornenkyy.whatpulse_library.teams;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.volodiachornenkyy.whatpulse_library.shared.WhatPulseRanks;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WhatPulseTeam {

    @JsonProperty("TeamID")
    private String teamId;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Users")
    private String usersAmount;

    @JsonProperty("Keys")
    private String keys;

    @JsonProperty("Clicks")
    private String clicks;

    @JsonProperty("DownloadMB")
    private String downloadMb;

    @JsonProperty("UploadMB")
    private String uploadMB;

    @JsonProperty("Download")
    private String download;

    @JsonProperty("Upload")
    private String upload;

    @JsonProperty("UptimeSeconds")
    private String uptimeSeconds;

    @JsonProperty("UptimeShort")
    private String uptimeShort;

    @JsonProperty("UptimeLong")
    private String uptimeLong;

    @JsonProperty("Ranks")
    private WhatPulseRanks ranks;

    @JsonProperty("DateFormed")
    private String dateFormed;

    @JsonProperty("DateFormedUnixTimestamp")
    private String dateFormedUnixTimestamp;

    @JsonProperty("Founder")
    private String founder;

    @JsonProperty("GeneratedTime")
    private String generatedTime;

    // TODO add Members

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsersAmount() {
        return usersAmount;
    }

    public void setUsersAmount(String usersAmount) {
        this.usersAmount = usersAmount;
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

    public String getDownloadMb() {
        return downloadMb;
    }

    public void setDownloadMb(String downloadMb) {
        this.downloadMb = downloadMb;
    }

    public String getUploadMB() {
        return uploadMB;
    }

    public void setUploadMB(String uploadMB) {
        this.uploadMB = uploadMB;
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

    public String getUptimeSeconds() {
        return uptimeSeconds;
    }

    public void setUptimeSeconds(String uptimeSeconds) {
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

    public WhatPulseRanks getRanks() {
        return ranks;
    }

    public void setRanks(WhatPulseRanks ranks) {
        this.ranks = ranks;
    }

    public String getDateFormed() {
        return dateFormed;
    }

    public void setDateFormed(String dateFormed) {
        this.dateFormed = dateFormed;
    }

    public String getDateFormedUnixTimestamp() {
        return dateFormedUnixTimestamp;
    }

    public void setDateFormedUnixTimestamp(String dateFormedUnixTimestamp) {
        this.dateFormedUnixTimestamp = dateFormedUnixTimestamp;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(String generatedTime) {
        this.generatedTime = generatedTime;
    }

    @Override
    public String toString() {
        return "WhatPulseTeam{" +
                "teamId='" + teamId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", usersAmount='" + usersAmount + '\'' +
                ", keys='" + keys + '\'' +
                ", clicks='" + clicks + '\'' +
                ", downloadMb='" + downloadMb + '\'' +
                ", uploadMB='" + uploadMB + '\'' +
                ", download='" + download + '\'' +
                ", upload='" + upload + '\'' +
                ", uptimeSeconds='" + uptimeSeconds + '\'' +
                ", uptimeShort='" + uptimeShort + '\'' +
                ", uptimeLong='" + uptimeLong + '\'' +
                ", ranks=" + ranks +
                ", dateFormed='" + dateFormed + '\'' +
                ", dateFormedUnixTimestamp='" + dateFormedUnixTimestamp + '\'' +
                ", founder='" + founder + '\'' +
                ", generatedTime='" + generatedTime + '\'' +
                '}';
    }
}
