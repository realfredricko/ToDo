# ToDos

## Overview
This is a todos app project that allows users record their tasks for easy remembrance. It follows a modern Android architecture using Jetpack Compose for the user interface, ViewModel for managing data, Dagger/Hilt for dependency injection and MVVM for a clean and maintainable codebase.


## Technologies Used
- **Jetpack Compose**: Used for building the user interface, providing a modern and declarative approach to UI development.
- **ViewModel**: Manages UI-related data and communicates with the data layer.
- **Dagger/Hilt**: For dependency injection, ensuring a modular and maintainable codebase.
- **MVVM Architecture**: Ensures separation of concerns and maintainability of the codebase.
- **Clean Architecture**: Promotes a clean separation of layers, making the app more testable and scalable.

## Architecture Overview
The project follows the principles of Clean Architecture, consisting of three main layers:

1. **Presentation Layer**: Jetpack Compose components and ViewModels reside here, responsible for rendering UI and handling user interactions.

2. **Domain Layer**: Contains business logic, use cases, and domain models. It's independent of the Android framework.

3. **Data Layer**: Manages data sources, repositories, and network communication. Retrofit is used to fetch data from a remote server.

## Dependencies
- [Dagger/Hilt](https://dagger.dev/hilt/): For dependency injection.
- [Jetpack Compose](https://developer.android.com/jetpack/compose): For building the UI.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel): For managing UI-related data.
- [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines): For handling asynchronous operations.

## Architecture
The app is built using the Modular MVVM architectural pattern and makes heavy use of a couple of Android Jetpack components. MVVM allows for the separation of concern which also makes testing easier.
![Clean architecture](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)


## Getting started
To get a local copy up and running follow these simple example steps.
   ```bash
   git clone  https://github.com/realfredricko/To-Dos.git

 ```
   
 
## 🤝 Contribution
Contributions, issues, and feature requests are welcome!
- Create a new branch for your feature or bug fix:
  
  `git checkout -b feature-name`

- Make your changes and commit them with a descriptive commit message:
  
  `git commit -m "Add feature XYZ"`
- Push your changes
  
  `git push origin feature-name`

- Create a PR to the **master** branch
