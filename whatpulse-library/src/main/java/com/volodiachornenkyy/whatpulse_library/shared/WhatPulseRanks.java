package com.volodiachornenkyy.whatpulse_library.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WhatPulseRanks {

    @JsonProperty("Uptime")
    private String uptime;

    @JsonProperty("Upload")
    private String upload;

    @JsonProperty("Download")
    private String download;

    @JsonProperty("Keys")
    private String keys;

    @JsonProperty("Clicks")
    private String clicks;

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
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

    @Override
    public String toString() {
        return "WhatPulseRanks{" +
                "uptime='" + uptime + '\'' +
                ", upload='" + upload + '\'' +
                ", download='" + download + '\'' +
                ", keys='" + keys + '\'' +
                ", clicks='" + clicks + '\'' +
                '}';
    }
}