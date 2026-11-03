# Gemini Chat Application

An AI-powered chat application built using **Spring Boot, Spring AI, Google Gemini, Thymeleaf, and HTMX**. The application provides a conversational interface where users can interact with Google's Gemini AI model and receive intelligent, formatted responses.

## 🚀 Project Overview

The Gemini Chat Application is a web-based AI chatbot inspired by Google Gemini.

The application allows users to:
- Send messages to the Gemini AI model.
- Receive AI-generated responses.
- Interact with the AI through a dynamic chat interface.
- View formatted responses using Markdown.
- Maintain conversational context using Spring AI Chat Memory.
- Experience a responsive and interactive user interface.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java 17  | Backend programming language |
| Spring Boot 4.1.2 | Application framework |
| Spring AI 2.0.1 | Integration with AI models |
| Google Gemini | AI model for generating responses |
| Spring MVC | Handling HTTP requests |
| Thymeleaf | Server-side HTML rendering |
| HTMX | Dynamic frontend interactions |
| Tailwind CSS | UI styling |
| JavaScript | Markdown rendering and frontend behavior |
| Maven | Dependency management and build tool |
| Git & GitHub | Version control |

## 🏗️ Architecture

The application follows a simple client-server architecture.

```text
                  USER
                   |
                   v
          Chat Interface
        (HTML + Thymeleaf)
                   |
                   v
                  HTMX
                   |
                   v
          Spring MVC Controller
             ChatController
                   |
                   v
             Spring AI
               ChatClient
                   |
                   v
            Google Gemini
                   |
                   v
          AI Generated Response
                   |
                   v
          Thymeleaf Fragment
                   |
                   v
          HTMX Updates the UI
```

## ⚙️ How the Application Works

### 1. User Sends a Message

The user enters a prompt in the chat input field.

HTMX sends the message to the Spring Boot backend using an HTTP POST request.

```http
POST /api/chat
```

### 2. Spring Boot Receives the Request

The `ChatController` receives the user message using `@RequestParam`.

```java
@PostMapping("/api/chat")
public View generate(@RequestParam String message, Model model) {
    // Process user message
}
```

### 3. Spring AI Communicates with Gemini

The controller uses Spring AI's `ChatClient` to send the prompt to the Gemini model.

```java
String response = chatClient.prompt()
        .user(message)
        .call()
        .content();
```

Spring AI handles the communication with the configured Google Gemini model.

### 4. Gemini Generates a Response

Gemini processes the user's prompt and generates a response based on the input and available conversational context.

### 5. Backend Returns the Response

The generated response is added to the Thymeleaf model.

```java
model.addAttribute("response", response);
```

The backend returns a Thymeleaf fragment containing the response.

### 6. HTMX Updates the Chat Interface

HTMX receives the HTML fragment and inserts it into the chat container without reloading the entire webpage.

### 7. Markdown Formatting

JavaScript libraries such as Marked.js and DOMPurify are used to render Markdown content.

This allows AI responses to display:
- Headings
- Lists
- Bold and italic text
- Code blocks
- Inline code

## 🧠 Chat Memory

The application uses Spring AI Chat Memory to maintain conversational context.

Chat memory allows the AI model to consider previous messages in a conversation when generating new responses.

The application uses:

- `MessageWindowChatMemory`
- `MessageChatMemoryAdvisor`
- `ChatMemory.CONVERSATION_ID`

The conversation ID identifies a particular conversation, allowing the memory advisor to retrieve its message history.

The message window is configured to retain up to 20 messages.

## 📂 Project Structure

```text
gemini/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/springai/gemini/
│   │   │       ├── GeminiApplication.java
│   │   │       └── controller/
│   │   │           └── ChatController.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── templates/
│   │       │   ├── index.html
│   │       │   └── response.html
│   │       └── static/
│   │
│   └── test/
│
├── .gitignore
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## 🔧 Installation and Setup

### Prerequisites

Make sure you have installed:

- Java 17 or higher
- Maven
- Spring Tool Suite (STS) or IntelliJ IDEA
- Google Gemini API Key

### Step 1: Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/gemini.git
```

Navigate to the project directory:

```bash
cd gemini
```

### Step 2: Configure the Gemini API Key

Get your Gemini API key from:

https://aistudio.google.com/apikey

Set the API key as an environment variable.

**Windows Command Prompt:**

```cmd
set GEMINI_API_KEY=your_api_key_here
```

**Windows PowerShell:**

```powershell
$env:GEMINI_API_KEY="your_api_key_here"
```

### Step 3: Configure application.properties

```properties
spring.application.name=gemini

spring.ai.google.genai.api-key=${GEMINI_API_KEY}

server.port=8082
```

Configure the Gemini model property according to the model supported by your Spring AI version.

### Step 4: Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run `GeminiApplication.java` directly from STS.

### Step 5: Open the Application

Visit:

http://localhost:8082/

Start chatting with Gemini AI.

## 🔐 Security

- API keys are stored in environment variables.
- API keys should never be committed to GitHub.
- The `.gitignore` file should exclude sensitive configuration files.
- DOMPurify is used to sanitize rendered Markdown HTML.

## 🎯 Learning Objectives

This project demonstrates:

- Building AI-powered applications using Spring Boot.
- Integrating Google Gemini using Spring AI.
- Understanding the Spring AI ChatClient API.
- Implementing dynamic UI updates using HTMX.
- Rendering server-side HTML using Thymeleaf.
- Managing conversational context using Chat Memory.
- Building REST-style HTTP endpoints.
- Understanding client-server communication.

## 🔮 Future Enhancements

- User authentication and authorization.
- Separate conversations for different users.
- Chat history persistence.
- New chat and conversation management.
- Streaming AI responses.
- Image-based prompts.
- Responsive mobile UI improvements.
- Deployment to a cloud platform.

## 👨‍💻 Author

Developed as a learning project to explore AI integration with Java and Spring Boot.

## 📄 License

This project is intended for educational and learning purposes.
