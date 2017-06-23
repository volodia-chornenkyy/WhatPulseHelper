#!/usr/bin/env bash

if ! ./gradlew jacocoTestReport; then
    git checkout -b reports/test_fail;
    git add -f app/build/reports/tests/;
    git commit -m "Failed tests report [ci skip]";
    git push --set-upstream origin reports/test_fail;
fi