## Download Apk to run app
Download Apk to run app [apk](https://github.com/m-rec/78671fc42fea6397d093df7505a405a758b4a837/blob/master/mini-mercai.apk)

## Mini Mercai 
- Project is based on **multi-modules & MVVM**.
- Project is using : 
  - **Kotlin** - Coding language
  - **Courotines** - Running task in thread
  - **Room** - Saving data with Sqlite wrapper
  - **Retrofit** - Network call
  - **Koin** - Dependency Inject
  - **Architecture component** - *Jetpack* Navigation, ViewModel, Databinding

### Module

* **App** - Main entry point
* **Data**
  * **Local** and **Remote** module 
  * **Repository** module to handle local and remote
* **Feature** - Home module is in under this module
* **Navigation** - Used to navigate between module
* **Common** -  Common Class used in this module



## MVVM & Databinding 

Using **Jetpack** component in whole project as per android guidelines

Using **ViewModel** to manage data even after *activity rotation* because viewmodel work with activity *lifecycle*



## Kotlin DSL

Using gradle with Kotlin DSL plugin for easy to navigate module and adding library or update in future easily
