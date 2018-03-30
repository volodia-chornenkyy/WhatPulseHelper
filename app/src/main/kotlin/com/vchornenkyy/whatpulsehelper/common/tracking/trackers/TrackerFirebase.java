package com.vchornenkyy.whatpulsehelper.common.tracking.trackers;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.vchornenkyy.whatpulsehelper.common.tracking.IEventTracker;
import com.vchornenkyy.whatpulsehelper.common.tracking.Screens;

import org.jetbrains.annotations.NotNull;

public class TrackerFirebase implements IEventTracker {

    private final FirebaseAnalytics analytics;

    public TrackerFirebase(Context c) {
        analytics = FirebaseAnalytics.getInstance(c);
    }

    @Override
    public void login() {
        analytics.logEvent(FirebaseAnalytics.Event.LOGIN, null);
    }

    @Override
    public void logout() {
        analytics.logEvent("logout", null);
    }

    @Override
    public void profileOpened() {
        trackScreen(Screens.PROFILE);
    }

    @Override
    public void computersOpened() {
        trackScreen(Screens.COMPUTERS);
    }

    @Override
    public void teamsOpened() {
        trackScreen(Screens.TEAMS);
    }

    @Override
    public void orientationChanged(@NotNull String orientation) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.VALUE, orientation);
        analytics.logEvent("orientation_change", bundle);
    }

    @Override
    public void trackScreen(@NonNull String screen) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.VALUE, screen);
        analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}
