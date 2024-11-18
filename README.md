# **MyTodo App**

MyTodo is a simple, yet powerful task management application built with **Jetpack Compose** and follows **Clean Architecture** principles. The app allows users to manage their tasks effectively by creating, updating, and deleting TODO items, ensuring a seamless and efficient workflow.

---

## **Features**

- Add new TODO items with a title and description.
- View a list of TODOs, each with a status indicator (done or not done).
- Update TODO details, including title, description, and completion status.
- Delete TODO items from either the details screen or via a long press on the list.
- Persist TODOs using a Room database for offline access.
- Modern UI built with Jetpack Compose.

---

## **Project Structure**

The project follows **Clean Architecture**, separating concerns into distinct layers:

### **1. Domain Layer**
Contains the core business logic and is independent of frameworks:
- **Models**: Defines `TodoItem`.
- **Use Cases**: Encapsulates specific business actions, such as:
  - `GetTodosUseCase`
  - `GetTodoByIdUseCase`
  - `CreateTodoUseCase`
  - `UpdateTodoUseCase`
  - `DeleteTodoUseCase`
- **Repository Interface**: Defines the contract for data operations.

### **2. Data Layer**
Handles data sources, including database and API interactions:
- **Room Database**:
  - Entity: `TodoEntity`
  - DAO: `TodoDao`
  - Implementation of `TodoRepository`.

### **3. Presentation Layer**
Responsible for UI and user interactions:
- **ViewModels**: Manages state and interacts with use cases.
  - `TodoListViewModel`
  - `TodoDetailsViewModel`
  - `TodoCreateViewModel`
- **Screens**: Composable functions for different screens:
  - `TodoListScreen`
  - `TodoDetailsScreen`
  - `TodoCreateScreen`
- **Navigation**: Uses Jetpack Compose's navigation component for screen transitions.

---

## **Technologies Used**

- **Kotlin**: Primary programming language.
- **Jetpack Compose**: Modern UI toolkit.
- **Room Database**: Local data persistence.
- **Hilt**: Dependency injection framework.
- **MockK**: For unit testing.
- **Kotlin Coroutines and Flows**: For asynchronous operations and reactive programming.
- **JUnit**: For unit testing.

