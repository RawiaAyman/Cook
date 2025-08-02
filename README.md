# 🍽️ Recipe App

A full-featured Android recipe application built using **Kotlin**, **Navigation Component**, **Room**, and **Retrofit**, following clean architecture practices.

## 📱 Features

### ✅ Authentication Flow
- Splash screen with login-state check via SharedPreferences
- Login and Sign Up using local Room database
- Users remain signed in until they manually sign out
- Separate AuthActivity for login-related screens

### 🏠 Main Screens (RecipeActivity)
- **Home Fragment**: Displays random recipes fetched from [TheMealDB API](https://www.themealdb.com/api.php)
- **Search Fragment**: Allows filtering by Name, Category, Area, and Ingredients
- **Favorites Fragment**: Shows saved favorite recipes (stored locally)

### 🍽️ Recipe Detail
- Shows image, name, category, area, and short instructions
- Button to expand full recipe instructions
- Button to save/unsave recipe as favorite
- Embedded YouTube video window overlay

### 🔍 Search
- Dropdown to select type (Name, Category, Area, Ingredient)
- Auto-suggestions for category, area, and ingredients
- Clicking a result navigates to the detail page

### ⭐ Favorites
- Displays all saved recipes
- Swipe or tap to remove

### 🎥 YouTube Video Window
- Embedded WebView shows a YouTube video if the recipe has one
- Appears as a small overlay, just like YouTube's mini-player

### 📋 About Page
- Shows team members and a short description of the app

### ⚙️ Option Menu
- Sign Out (clears login state)
- About the Creator (navigates to AboutFragment)

---

## 🛠️ Tech Stack

| Layer            | Library/Tool               |
|------------------|----------------------------|
| Language         | Kotlin                     |
| UI               | AndroidX, Material Design  |
| Architecture     | MVVM                       |
| Navigation       | Navigation Component       |
| Networking       | Retrofit, Moshi, OkHttp    |
| Local Storage    | Room, SharedPreferences    |
| Image Loading    | Glide                      |
| Video Embeds     | WebView (YouTube)          |
| Dependency Mgmt  | Gradle                     |

---
## 📁 Project Structure

com.example.myapplication
│
├── Data/ → Data Layer
│ ├── adapter/ → RecyclerView adapters
│ ├── local/ → Room DB, DAO, and local persistence
│ ├── model/ → Data classes (Meal, User, etc.)
│ ├── network/ → API service, Retrofit helper
│ └── repo/ → Data repositories (optional for scalability)
│
├── ui/ → Presentation Layer
│ ├── auth/ → AuthActivity, Login & SignUp screens
│ ├── detail/ → RecipeDetailFragment
│ ├── favorite/ → FavoriteFragment
│ ├── home/ → HomeFragment & AboutFragment
│ ├── search/ → SearchFragment & related UI
│ ├── splash/ → SplashFragment
│ └── utils/ → PreferenceHelper and utility classes
│
└── MainActivity.kt → RecipeActivity to show the remaining fragments.

---

## 🧩 Features

- **🔐 Authentication** with local Room DB
- **💾 User session** using SharedPreferences
- **🔎 Smart Search** by Name, Category, Area, or Ingredients
- **🎬 Embedded YouTube Video** for recipes
- **⭐ Favorite system** with persistent storage
- **📋 About Page** for team details
- **🌊 Responsive UI** using Material Design
- **📡 API** powered by [TheMealDB](https://themealdb.com)

---

## 🚀 Getting Started

### Requirements
- Android Studio Giraffe or newer
- Kotlin 1.9+
- Gradle 8+
- Internet connection

### Steps
1. Clone the repository
2. Open in Android Studio
3. Run on an emulator or device

---

## 🔐 Login Persistence

- **SignupFragment** sets username in `PreferenceHelper`
- **HomeFragment** displays welcome using `getUserName()`
- Logout via toolbar clears login state and SharedPreferences

---

## 🧠 Architecture

This app follows MVVM principles:

UI (Fragment)
⇅
ViewModel
⇅
Repository (optional)
⇅
Room / Retrofit API



All modules are encapsulated inside `Data` and `ui` for clean separation.

---

## 🎨 Theme

The app uses a consistent color theme:

| Color Name              | Hex        | Usage                          |
|-------------------------|------------|---------------------------------|
| `dishdash_red`          | `#F85A63`  | Primary action / buttons        |
| `light_background`      | `#FDECEC`  | Screens & container backgrounds |
| `black`                 | `#FF000000`| Text                            |
| `white`                 | `#FFFFFFFF`| Icons, text, cards              |
| `circle_white_bg`       | `#FFFFFF`  | Rounded elements                |
| `background_light_pink` | `#FFE6E6`  | Light accent background         |
| `input_field_background`| `#FFCDD2`  | TextField backgrounds           |
| `input_hint_color`      | `#DBABB0`  | Hint text color                 |
| `error_red`             | `#B00020`  | Snackbar / error text           |

---

## 📋 Screens

- Splash screen
- Auth screens (Login / SignUp)
- Home screen (random recipes)
- Search screen with dropdown filtering
- Favorites screen
- Recipe detail with:
  - Expandable instructions
  - YouTube video overlay
  - Favorite button
- About the Creator (team introduction)

---

## 🧑‍💻 Created By

This app was developed collaboratively by a dedicated team as part of a university/training project focused on full-featured mobile application development.

---

## 📄 License

This project is open for educational use only. Commercial redistribution is not allowed.
