buildscript {
    repositories {
        google()
    }
    dependencies {
        val navVersion = "2.7.7"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    }
}

plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
}

// Define version numbers for dependencies using Kotlin variables
val androidXVersion = "1.0.0"
val androidXTestCoreVersion = "1.4.0"
val androidXTestExtKotlinRunnerVersion = "1.1.3"
val androidXTestRulesVersion = "1.2.0"
val androidXAnnotations = "1.3.0"
val appCompatVersion = "1.4.0"
val archLifecycleVersion = "2.4.0"
val archTestingVersion = "2.1.0"
val coroutinesVersion = "1.5.2" // Ensure only one version is declared
val cardVersion = "1.0.0"
val dexMakerVersion = "2.12.1"
val espressoVersion = "3.4.0"
val fragmentKtxVersion = "1.4.0"
val hamcrestVersion = "1.3"
val junitVersion = "4.13.2"
val materialVersion = "1.4.0"
val recyclerViewVersion = "1.2.1"
val robolectricVersion = "4.5.1"
val rulesVersion = "1.0.1"
val swipeRefreshLayoutVersion = "1.1.0"
val timberVersion = "4.7.1"
val truthVersion = "1.1.2"
