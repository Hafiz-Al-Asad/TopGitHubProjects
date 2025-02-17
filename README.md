![Screenshot from 2025-02-17 19-30-16](https://github.com/user-attachments/assets/f4dab66e-df01-41a3-b9b4-096664340bc9)📌 Project Name
GitHub Top Repositories

📝 Description
This is an Android app built using Java and MVVM architecture that fetches and displays the top trending repositories on GitHub. The app uses the GitHub REST API to retrieve repository data and presents it in a clean and structured way.

📸 Screenshots
![WhatsApp Image 2025-02-17 at 7 36 12 PM](https://github.com/user-attachments/assets/2a489aad-2934-4721-805b-66e53ce5b4f9)


🎯 Features
✅ Fetches and displays trending GitHub repositories
✅ Implements MVVM architecture for better separation of concerns
✅ Uses Retrofit for network calls
✅ Uses LiveData and ViewModel for reactive UI updates
✅ Implements Room Database for offline storage
✅ Follows best practices for Android development

🛠 **Tech Stack**
- **Language:** Java  
- **Architecture:** MVVM (Model-View-ViewModel)  
- **Networking:** Retrofit + Gson  
- **Asynchronous Operations:** RxJava  
- **UI Components:** RecyclerView, ViewBinding, DataBinding  

📂 Project Structure
📦 githubrepositorysearch  
 ┣ 📂 app  
 ┃ ┣ 📂 manifests  
 ┃ ┣ 📂 java  
 ┃ ┃ ┣ 📂 com.hafiz.githubrepositorysearch  
 ┃ ┃ ┃ ┣ 📂 constant         # Constants used across the app  
 ┃ ┃ ┃ ┣ 📂 exception        # Custom exception handling  
 ┃ ┃ ┃ ┣ 📂 model            # Data models  
 ┃ ┃ ┃ ┣ 📂 network          # API calls and networking logic  
 ┃ ┃ ┃ ┣ 📂 repository       # Data repository layer  
 ┃ ┃ ┃ ┣ 📂 setting          # Settings-related components  
 ┃ ┃ ┃ ┣ 📂 ui               # UI components  
 ┃ ┃ ┃ ┃ ┣ 📂 activity       # Activities  
 ┃ ┃ ┃ ┃ ┣ 📂 fragment       # Fragments  
 ┃ ┃ ┃ ┃ ┣ 📂 common         # Shared UI components  
 ┃ ┃ ┃ ┣ 📂 util             # Utility classes and helpers  
 ┃ ┃ ┃ ┣ 📂 viewmodel        # ViewModels for MVVM architecture  
 ┃ ┃ ┣ 📂 com.hafiz.githubrepositorysearch (androidTest) # Instrumentation tests  
 ┃ ┃ ┣ 📂 com.hafiz.githubrepositorysearch (test)        # Unit tests  
 ┃ ┣ 📂 assets  
 ┃ ┣ 📂 res  
 ┃ ┃ ┣ 📂 drawable         # XML drawables (icons, shapes)  
 ┃ ┃ ┣ 📂 layout           # XML layouts  
 ┃ ┃ ┣ 📂 menu             # Menu XML files  
 ┃ ┃ ┣ 📂 mipmap           # Launcher icons  
 ┃ ┃ ┣ 📂 navigation       # Navigation components  
 ┃ ┃ ┣ 📂 values           # Colors, strings, dimensions, themes  
 ┃ ┃ ┣ 📂 xml              # Additional XML configurations  
 ┣ 📜 .gitignore  
 ┣ 📜 README.md  
 ┣ 📜 LICENSE (if applicable)  
 ┣ 📜 build.gradle  
🔧 Setup & Installation
1️⃣ Clone the repository
git clone https://github.com/your-username/github-top-repositories.git

2️⃣ Open the project in Android Studio
3️⃣ Build & Run the project on an emulator or a real device

🚀 API Configuration
This app uses the GitHub REST API. If required, you may need to generate a GitHub Personal Access Token and add it in local.properties:
GITHUB_API_KEY="your_personal_access_token"

📜 License
This project is licensed under the MIT License. See the LICENSE file for more details.
