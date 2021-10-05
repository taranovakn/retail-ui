package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.NuGetPublishStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetPublish
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'SeleniumTesting_Publish'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("SeleniumTesting_Publish")) {
    expectSteps {
        nuGetInstaller {
            name = "Install"
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            projects = "packages/react-ui-testing/SeleniumTesting.sln"
        }
        dotnetBuild {
            name = "Build"
            projects = "packages/react-ui-testing/SeleniumTesting/SeleniumTesting.csproj"
            configuration = "Release"
            versionSuffix = "%teamcity.build.branch%"
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
        nuGetPublish {
            name = "Publish"
            toolPath = "%teamcity.tool.NuGet.CommandLine.4.9.2%"
            packages = "packages/react-ui-testing/Output/*.nupkg"
            serverUrl = "https://api.nuget.org/v3/index.json"
            apiKey = "credentialsJSON:bd776d48-3dea-45bb-95d2-f28cdfb5e1aa"
        }
    }
    steps {
        update<NuGetPublishStep>(2) {
            clearConditions()
            apiKey = "credentialsJSON:025012f8-2dc9-43bf-9e45-d5dabbcf7258"
        }
    }
}