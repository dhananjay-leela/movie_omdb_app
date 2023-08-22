# Movie Social

**ScreenShot**

<img src="https://www.linkpicture.com/q/Screenshot_20230822-131516.png" width="50%" height="50%">

<img src="https://imgtr.ee/images/2023/08/22/4310eb066918acd72085674fa8c01e91.png" width="50%" height="50%">

## Build Versions and Configurations
The android project is run under the following configurations

**Gradle Plugin** v 8.1.0

**Gradle version** v8.0

**JAVA** Java 8

## Architecture
The android Architecture follows the MVVM pattern defined by the Android Jetpack Architecture component (https://developer.android.com/jetpack/docs/guide).

**In the Android app a separation of concerns is adhered to as so:**

**UI (Fragment / Activity)** - only contain logic that handles the UI and operating system interactions.

**ViewModel** - only communicate with data via a repository, they should never touch the database or external source directly

**Repository** - only contain logic that handles the retrieval and storage of data (Be that from an external source or SQLite) they should not contain any 'android' code.

## Dependency Injection
Dependency injection is satisfied via Koin (https://insert-koin.io/). There is a Dependencies.kt file at the app root where the app dependencies are declared, this is initialized in the Application class. Try and keep all dependency declarations in the Dependencies.kt file, however if a dependency requires an object that can only be created at runtime declare the dependency as high up as possible, preferably the Activity.

## Issues and Future features
- Currently the Login/signup is based on Shared Preferneces which a local storage and wont presist when the app is deleted.

- Multiple API call are made currently, which could be further optimised, by loading by each page triggered by user scroll behaviour.

- Currently fetches are made per year, when you click on Load more the next year movies are fetched and added to the list.
