package com.volodiachornenkyy.whatpulse_library.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.volodiachornenkyy.whatpulse_library.shared.WhatPulseComputer;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WhatPulseUser {

    @JsonProperty("UptimeLong")
    private String uptimeLong;

    @JsonProperty("Homepage")
    private String homepage;

    @JsonProperty("AvCPS")
    private String avClicksPerSecond;

    @JsonProperty("Pulses")
    private String pulses;

    @JsonProperty("UserID")
    private String userId;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("AvKeysPerPulse")
    private String avKeysPerPulse;

    @JsonProperty("Upload")
    private String upload;

    @JsonProperty("LastPulse")
    private String lastPulse;

    @JsonProperty("Keys")
    private String keys;

    @JsonProperty("Clicks")
    private String clicks;

    @JsonProperty("DownloadMB")
    private String downloadMb;

    @JsonProperty("AvKPS")
    private String avKeysPerSecond;

    @JsonProperty("AvClicksPerPulse")
    private String avClicksPerPulse;

    @JsonProperty("DateJoined")
    private String dateJoined;

    @JsonProperty("UptimeSeconds")
    private String uptimeSeconds;

    @JsonProperty("LastPulseUnixTimestamp")
    private String lastPulseUnixTimestamp;

    @JsonProperty("AccountName")
    private String accountName;

    @JsonProperty("UploadMB")
    private String uploadMB;

    @JsonProperty("Download")
    private String download;

    @JsonProperty("tld")
    private String tld;

    @JsonProperty("DateJoinedUnixTimestamp")
    private String dateJoinedUnixTimestamp;

    @JsonProperty("UptimeShort")
    private String uptimeShort;

    @JsonProperty("GeneratedTime")
    private String generatedTime;

    @JsonProperty("Computers")
    private Map<String, WhatPulseComputer> computers;

    // TODO team

    public String getUptimeLong() {
        return uptimeLong;
    }

    public void setUptimeLong(String uptimeLong) {
        this.uptimeLong = uptimeLong;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getAvClicksPerSecond() {
        return avClicksPerSecond;
    }

    public void setAvClicksPerSecond(String avClicksPerSecond) {
        this.avClicksPerSecond = avClicksPerSecond;
    }

    public String getPulses() {
        return pulses;
    }

    public void setPulses(String pulses) {
        this.pulses = pulses;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvKeysPerPulse() {
        return avKeysPerPulse;
    }

    public void setAvKeysPerPulse(String avKeysPerPulse) {
        this.avKeysPerPulse = avKeysPerPulse;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public String getLastPulse() {
        return lastPulse;
    }

    public void setLastPulse(String lastPulse) {
        this.lastPulse = lastPulse;
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

    public String getAvKeysPerSecond() {
        return avKeysPerSecond;
    }

    public void setAvKeysPerSecond(String avKeysPerSecond) {
        this.avKeysPerSecond = avKeysPerSecond;
    }

    public String getAvClicksPerPulse() {
        return avClicksPerPulse;
    }

    public void setAvClicksPerPulse(String avClicksPerPulse) {
        this.avClicksPerPulse = avClicksPerPulse;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getUptimeSeconds() {
        return uptimeSeconds;
    }

    public void setUptimeSeconds(String uptimeSeconds) {
        this.uptimeSeconds = uptimeSeconds;
    }

    public String getLastPulseUnixTimestamp() {
        return lastPulseUnixTimestamp;
    }

    public void setLastPulseUnixTimestamp(String lastPulseUnixTimestamp) {
        this.lastPulseUnixTimestamp = lastPulseUnixTimestamp;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public String getTld() {
        return tld;
    }

    public void setTld(String tld) {
        this.tld = tld;
    }

    public String getDateJoinedUnixTimestamp() {
        return dateJoinedUnixTimestamp;
    }

    public void setDateJoinedUnixTimestamp(String dateJoinedUnixTimestamp) {
        this.dateJoinedUnixTimestamp = dateJoinedUnixTimestamp;
    }

    public String getUptimeShort() {
        return uptimeShort;
    }

    public void setUptimeShort(String uptimeShort) {
        this.uptimeShort = uptimeShort;
    }

    public String getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(String generatedTime) {
        this.generatedTime = generatedTime;
    }

    public Map<String, WhatPulseComputer> getComputers() {
        return computers;
    }

    public void setComputers(Map<String, WhatPulseComputer> computers) {
        this.computers = computers;
    }

    @Override
    public String toString() {
        return "WhatPulseUser{" +
                "uptimeLong='" + uptimeLong + '\'' +
                ", homepage='" + homepage + '\'' +
                ", avClicksPerSecond='" + avClicksPerSecond + '\'' +
                ", pulses='" + pulses + '\'' +
                ", userId='" + userId + '\'' +
                ", country='" + country + '\'' +
                ", avKeysPerPulse='" + avKeysPerPulse + '\'' +
                ", upload='" + upload + '\'' +
                ", lastPulse='" + lastPulse + '\'' +
                ", keys='" + keys + '\'' +
                ", clicks='" + clicks + '\'' +
                ", downloadMb='" + downloadMb + '\'' +
                ", avKeysPerSecond='" + avKeysPerSecond + '\'' +
                ", avClicksPerPulse='" + avClicksPerPulse + '\'' +
                ", dateJoined='" + dateJoined + '\'' +
                ", uptimeSeconds='" + uptimeSeconds + '\'' +
                ", lastPulseUnixTimestamp='" + lastPulseUnixTimestamp + '\'' +
                ", accountName='" + accountName + '\'' +
                ", uploadMB='" + uploadMB + '\'' +
                ", download='" + download + '\'' +
                ", tld='" + tld + '\'' +
                ", dateJoinedUnixTimestamp='" + dateJoinedUnixTimestamp + '\'' +
                ", uptimeShort='" + uptimeShort + '\'' +
                ", generatedTime='" + generatedTime + '\'' +
                ", computers=" + computers +
                '}';
    }
}
