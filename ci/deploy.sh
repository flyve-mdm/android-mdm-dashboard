#!/bin/bash

if [[ "$TRAVIS_BRANCH" == "develop" && "$TRAVIS_PULL_REQUEST" == "false" ]];
then
    cd ci
    tar -zxvf google.tar.gz
    cd ..
    fastlane android alpha storepass:'$KEYSTORE' keypass:'$ALIAS'
fi

if [[ "$TRAVIS_BRANCH" == "feature/init" ]];
then
    cd ci
    tar -zxvf google.tar.gz
    cd ..
    fastlane android alpha storepass:'$KEYSTORE' keypass:'$ALIAS'
fi