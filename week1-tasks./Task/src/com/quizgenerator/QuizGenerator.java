package com.quizgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizGenerator {

    private static List<Quiz> quizzes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Quiz Generator Application");
            System.out.println("1. Create a new quiz");
            System.out.println("2. Add a question to a quiz");
            System.out.println("3. Take a quiz");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createQuiz();
                    break;
                case 2:
                    addQuestionToQuiz();
                    break;
                case 3:
                    takeQuiz();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createQuiz() {
        System.out.print("Enter quiz title: ");
        String title = scanner.nextLine();
        Quiz quiz = new Quiz(title);
        quizzes.add(quiz);
        System.out.println("Quiz '" + title + "' created successfully.");
    }

    private static void addQuestionToQuiz() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available. Create a quiz first.");
            return;
        }

        System.out.println("Available quizzes:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getTitle());
        }

        System.out.print("Choose a quiz to add a question to: ");
        int quizIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (quizIndex < 0 || quizIndex >= quizzes.size()) {
            System.out.println("Invalid quiz choice. Please try again.");
            return;
        }

        Quiz selectedQuiz = quizzes.get(quizIndex);

        System.out.print("Enter question text: ");
        String questionText = scanner.nextLine();

        List<String> options = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            System.out.print("Enter option " + i + ": ");
            options.add(scanner.nextLine());
        }

        System.out.print("Enter the number of the correct option (1-4): ");
        int correctAnswerIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (correctAnswerIndex < 0 || correctAnswerIndex >= options.size()) {
            System.out.println("Invalid correct answer choice. Please try again.");
            return;
        }

        Question question = new Question(questionText, options, correctAnswerIndex);
        selectedQuiz.addQuestion(question);
        System.out.println("Question added successfully to quiz '" + selectedQuiz.getTitle() + "'.");
    }

    private static void takeQuiz() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available. Create a quiz first.");
            return;
        }

        System.out.println("Available quizzes:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getTitle());
        }

        System.out.print("Choose a quiz to take: ");
        int quizIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (quizIndex < 0 || quizIndex >= quizzes.size()) {
            System.out.println("Invalid quiz choice. Please try again.");
            return;
        }

        Quiz selectedQuiz = quizzes.get(quizIndex);
        List<Question> questions = selectedQuiz.getQuestions();

        int score = 0;

        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            System.out.print("Your answer: ");
            int userAnswer = scanner.nextInt() - 1;

            if (userAnswer == question.getCorrectAnswerIndex()) {
                score++;
            }
        }

        System.out.println("Quiz completed! Your score: " + score + "/" + questions.size());
    }
}
