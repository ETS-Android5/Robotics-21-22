//
// build.gradle in TeamCode
//
// Most of the definitions for building your module reside in a common, shared
// file 'build.common.gradle'. Being factored in this way makes it easier to
// integrate updates to the FTC into your code. If you really need to customize
// the build definitions, you can place those customizations in this file, but
// please think carefully as to whether such customizations are really necessary
// before doing so.


// Custom definitions may go here

// Include common definitions from above.
apply(from = "../build.common.gradle")
apply(plugin = "kotlin-android")
apply(from = "../build.dependencies.gradle")

android {
    compileSdkVersion(31)
    defaultConfig {
        targetSdkVersion(31)
    }
    androidResources {
        noCompress("tflite")
    }
    // Specifies one flavor dimension.
    flavorDimensions("defaultFlavor")
    productFlavors {
        common {
            applicationIdSuffix(".common")
            versionNameSuffix("-common")
        }
        everything {
            applicationIdSuffix ".everything"
            versionNameSuffix "-everything"
        }
        example {
            applicationIdSuffix(".example")
            versionNameSuffix("-example")
        }
        robots {
            applicationIdSuffix(".robots")
            versionNameSuffix("-robots")
        }
        machines {
            applicationIdSuffix(".machines")
            versionNameSuffix("-machines")
        }
        gadgets {
            applicationIdSuffix(".gadgets")
            versionNameSuffix("-gadgets")
        }
    }
}

kotlin {
    sourceSets {
        main.kotlin {
            include("org/firstinspires/ftc/common")
        }
        everything.kotlin {
            srcDir 'src/main/kotlin'
        }
        example.kotlin {
            srcDir 'src/main/kotlin'
            include 'org/firstinspires/ftc/example'
        }
        robots.kotlin {
            srcDir 'src/main/kotlin'
            include 'org/firstinspires/ftc/robots'
        }
        machines.kotlin {
            srcDir 'src/main/kotlin'
            include 'org/firstinspires/ftc/machines'
        }
        gadgets.kotlin {
            srcDir 'src/main/kotlin'
            include 'org/firstinspires/ftc/gadgets'
        }
    }
}

dependencies {
    implementation project(':FtcRobotController')
    annotationProcessor files('lib/OpModeAnnotationProcessor.jar')
    implementation "androidx.core:core-ktx:+"
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
}
repositories {
    mavenCentral()
}
