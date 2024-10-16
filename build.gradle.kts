// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    ///hilt
    id("com.google.dagger.hilt.android") version "2.52" apply false
    ///devtools
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}