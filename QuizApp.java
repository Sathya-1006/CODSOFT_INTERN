import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp extends JFrame implements ActionListener {
	private String[][] questions = {
		    {"What is the size of an int in Java?", "2 bytes", "4 bytes", "8 bytes", "16 bytes"},
		    {"Which of the following is not a Java keyword?", "static", "Boolean", "void", "private"},
		    {"Which method must be implemented by all threads in Java?", "start()", "run()", "stop()", "main()"},
		    {"What is the default value of a boolean variable in Java?", "true", "false", "0", "1"},
		    {"Which of the following is used to find and fix bugs in the Java programs?", "JVM", "JRE", "JDK", "JDB"}
		};

private String[] correctAnswers = {"4 bytes", "Boolean", "run()", "false", "JDB"};
    private String[] userAnswers = new String[questions.length];
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int timeLeft = 10; // 10 seconds for each question

    private JLabel questionLabel;
    private JRadioButton[] options = new JRadioButton[4];
    private JButton submitButton;
    private JLabel timerLabel;
    private Timer timer;

    public QuizApp() {
        setTitle("Quiz Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        ButtonGroup optionsGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            optionsGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        timerLabel = new JLabel("Time left: " + timeLeft);
        bottomPanel.add(timerLabel, BorderLayout.WEST);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        bottomPanel.add(submitButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        loadQuestion();

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft);
                if (timeLeft <= 0) {
                    timer.stop();
                    checkAnswer();
                }
            }
        });
        timer.start();
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            String[] currentQuestion = questions[currentQuestionIndex];
            questionLabel.setText(currentQuestion[0]);
            for (int i = 0; i < 4; i++) {
                options[i].setText(currentQuestion[i + 1]);
                options[i].setSelected(false);
            }
        } else {
            showResults();
        }
    }

    private void checkAnswer() {
        timer.stop();
        String selectedAnswer = null;
        for (JRadioButton option : options) {
            if (option.isSelected()) {
                selectedAnswer = option.getText();
                break;
            }
        }
        userAnswers[currentQuestionIndex] = selectedAnswer != null ? selectedAnswer : "None";

        if (selectedAnswer != null && selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
            score++;
        }
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            timeLeft = 10;
            loadQuestion();
            timer.start();
        } else {
            showResults();
        }
    }

    private void showResults() {
        StringBuilder resultSummary = new StringBuilder();
        for (int i = 0; i < questions.length; i++) {
            resultSummary.append(questions[i][0]).append("\n");
            resultSummary.append("Your answer: ").append(userAnswers[i]).append("\n");
            resultSummary.append("Correct answer: ").append(correctAnswers[i]).append("\n\n");
        }
        JOptionPane.showMessageDialog(this, "Final Score: " + score + "\n" + resultSummary.toString(), "Results", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            checkAnswer();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizApp().setVisible(true);
            }
        });
    }
}
