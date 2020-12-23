### Sample App with MVVM Clean Architecture Coroutines, Koin, Jetpack Libs

[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.4.20-blue.svg)](https://kotlinlang.org)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=30)

## About

Sample project that build with MVVM clean architecture 
Unit tests are written with JUnit4, MockK, Truth, MockWebServer. Data, Domain, and ViewModel tests.

## Overview
* Gradle Kotlin DSL is used for setting up gradle files with `buildSrc` folder and extensions.
* Data module uses Retrofit and Room to provide Local and Remote data sources

## Built With 🛠

Some of the popular libraries and MVVM clean architecture used

* [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.

* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - Threads on steroids for Kotlin
* [Android JetPack](https://developer.android.com/jetpack) - Collection of libraries that help you design robust, testable, and maintainable apps.
  * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
* [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
* [Glide](https://github.com/bumptech/glide) - Image loading library.

* Architecture
    * Clean Architecture
    * MVVM
* Tests
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit5](https://junit.org/junit5/)) ([JUnit4](https://junit.org/junit4/))
    * [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) Mock server for testing Api requests with OkHttp and Retrofit
    * [Mockk](https://mockk.io/) Mockking library for Kotlin
    * [Truth](https://truth.dev) Assertion library
* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
