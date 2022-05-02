# mil-sym-android
[![Build Status](https://travis-ci.org/missioncommand/mil-sym-android.svg?branch=master)](https://travis-ci.org/missioncommand/mil-sym-android)

## About

mil-sym-android is an Android port of the Java-based MIL-STD rendering libraries that have been used in US Army Mission Command software for years.  In November 2013 Mission Command was given the approval to release and maintain these libraries as public open source.  

[Android Renderer Developer's Guide Wiki](https://github.com/missioncommand/mil-sym-android/wiki)  
[Google Group Discussion Forum](https://groups.google.com/forum/#!forum/mission-command-milstd-renderer)  

### MIL-STD-2525
---
The [MIL-STD-2525] standard defines how to visualize military symbology.  This project provides support for the entire MIL-STD-2525B Change II plus USAS 13-14 and MIL-STD-2525C.  

### Project Structure
---
mil-sym-android has a namespace structure that resembles the java layout although differs where we needed to implement java functionality that wasn't available in the Dalvik VM.


### Tech
---

Eclipse with Android plugins
or
Android Studio
or
Gradle based build system.  


### Building
---

Prerequisites:
* Android SDK is installed
* ```ANDROID_HOME``` environment variable pointing to location of the Android SDK
* Android build-tools v23.0.3

Build:
````
./gradlew build
````

Build and install to Maven local:
````
./gradlew build publishToMavenLocal
````
  
With JCenter not supporting new releases, we've moved to Maven Central.  
https://s01.oss.sonatype.org/#nexus-search;quick~mil-sym-android  
https://repo1.maven.org/maven2/io/github/missioncommand/mil-sym-android-renderer/  
