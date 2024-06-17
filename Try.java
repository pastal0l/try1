import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.Timer;





class BackgroundPanel extends JPanel {
   private Image backgroundImage;


   public BackgroundPanel(String imagePath) {
       this.backgroundImage = new ImageIcon(imagePath).getImage();
   }
   @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
   }
}


public class Try {
   static JFrame gameSelectionFrame;
   private static JFrame gameWindowFrame;
   private static int playerScore = 0;
   private static int computerScore = 0;
   private static String playerChoice;
   private static String computerChoice;
   private static String currentGame;
    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> createAndShowGameSelectionGUI());
   }


   private static void createAndShowGameSelectionGUI() {
       gameSelectionFrame = new JFrame("Game Selection");
       gameSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       gameSelectionFrame.setSize(600, 400);
       BackgroundPanel backgroundPanel = new BackgroundPanel("mainbackground.jpeg");
       backgroundPanel.setLayout(new GridBagLayout());
       gameSelectionFrame.setContentPane(backgroundPanel);
       JLabel titleLabel = new JLabel("What games will we play today?", SwingConstants.CENTER);
       titleLabel.setFont(new Font("Serif", Font.ITALIC, 34));
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.gridx = 0;
       gbc.gridy = 0;
       gbc.insets = new Insets(20, 0, 30, 0);
       gbc.anchor = GridBagConstraints.NORTH;
       backgroundPanel.add(titleLabel, gbc);
       JPanel gameButtonsPanel = new JPanel(new GridLayout(3, 1, 0, 20));
       gameButtonsPanel.setOpaque(false);
       gameButtonsPanel.add(createGameButton("Rock Paper Scissors", "rock_paper_scissors_icon.png"));
       gameButtonsPanel.add(createGameButton("Who wants to be a millionaire!", "millionaire_icon.png"));
       gameButtonsPanel.add(createGameButton("Don't copy me!", "dont_copy_me_icon.png"));
       gameButtonsPanel.add(createGameButton("Tic-Tac-Toe", "Tic_tac_toe.png"));
       gbc.gridy = 1;
       gbc.insets = new Insets(0, 0, 0, 0);
       backgroundPanel.add(gameButtonsPanel, gbc);
       gameSelectionFrame.setVisible(true);
       gameSelectionFrame.setLocationRelativeTo(null);
   }








   private static JButton createGameButton(String gameTitle, String iconFileName) {
       ImageIcon icon = new ImageIcon(iconFileName);
       Image img = icon.getImage();
       Image scaledImg = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
       icon = new ImageIcon(scaledImg);
       JButton gameButton = new JButton(gameTitle, icon);
       gameButton.setHorizontalTextPosition(SwingConstants.RIGHT);
       gameButton.setVerticalTextPosition(SwingConstants.CENTER);
       gameButton.setFont(new Font("Serif", Font.PLAIN, 16));
       gameButton.addActionListener(e -> {
           createAndShowGameWindow(gameTitle);
           gameSelectionFrame.setVisible(false);
       });
       return gameButton;
   }








   private static void createAndShowGameWindow(String title) {
    currentGame = title;
    gameWindowFrame = new JFrame(title);
    gameWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameWindowFrame.setSize(600, 400);

    // Determine and set background image based on game title
    String backgroundImageFile = getBackgroundImageForGame(title);
    BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImageFile);
    backgroundPanel.setLayout(new BorderLayout());
    gameWindowFrame.setContentPane(backgroundPanel);

    // Create a JPanel for title with highlight background
    JPanel titlePanel = new JPanel(new BorderLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the highlight background
            g.setColor(new Color(255, 255, 255, 150)); // Semi-transparent white
            g.fillRect(0, 70, getWidth(), getHeight());
        }
    };
    titlePanel.setOpaque(false); // Make the panel transparent

    JLabel newTitleLabel = new JLabel("Game: " + title, SwingConstants.CENTER);
    newTitleLabel.setFont(new Font("Serif", Font.BOLD, 24));
    titlePanel.add(newTitleLabel, BorderLayout.CENTER);
    
    // Add the title panel to the background panel
    backgroundPanel.add(titlePanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setOpaque(false);
    JButton backButton = new JButton("Back");
    buttonPanel.add(backButton);
    JButton startButton = new JButton("Start");
    buttonPanel.add(startButton);
    backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

    // Handle window closing event
    gameWindowFrame.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            gameSelectionFrame.setVisible(true);
        }
    });

    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameWindowFrame.dispose();
            gameSelectionFrame.setVisible(true);
        }
    });

    startButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameWindowFrame.dispose();
            if (currentGame.equals("Rock Paper Scissors")) {
                createAndShowRockPaperScissorsGame();
            } else if (currentGame.equals("Who wants to be a millionaire!")) {
                createAndShowWhoWantsToBeAMillionaire();
            } else if (currentGame.equals("Tic-Tac-Toe")){
                createAndShowTicTacToe();
            }
             else {
                createAndShowGuessingGame();
            }
        }
    });

    gameWindowFrame.setLocationRelativeTo(null);
    gameWindowFrame.setVisible(true);
    gameSelectionFrame.setVisible(false);
}

// Method to get background image filename based on game title
private static String getBackgroundImageForGame(String gameTitle) {
    switch (gameTitle) {
        case "Rock Paper Scissors":
            return "rps_background.jpeg";
        case "Who wants to be a millionaire!":
            return "capsule_616x353.jpeg";
        case "Don't copy me!":
            return "guessgame2.jpeg";
        case "Tic-Tac-Toe":
            return "tic.jpeg";
        default:
            return "default_background.jpeg";
    }
}


   private static void createAndShowRockPaperScissorsGame() {
       gameWindowFrame.dispose();
       gameWindowFrame = new JFrame("Rock Paper Scissors");
       gameWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       gameWindowFrame.setSize(600, 400);
       BackgroundPanel backgroundPanel = new BackgroundPanel("rock_paper_scissors_background.jpeg");
       backgroundPanel.setLayout(new BorderLayout());
       gameWindowFrame.setContentPane(backgroundPanel);
       JLabel rulesLabel = new JLabel("Win against a computer 3 times in a row. Good luck!");
       rulesLabel.setFont(new Font("Arial", Font.PLAIN, 14));

       JPanel rulesPanel = new JPanel(new BorderLayout());
       rulesPanel.add(rulesLabel, BorderLayout.CENTER);
       rulesLabel.setForeground(Color.BLACK);

       JOptionPane.showMessageDialog(rulesPanel, "Win against a computer 3 times in a row. Good luck!", computerChoice, JOptionPane.PLAIN_MESSAGE, new ImageIcon("rock_paper_scissors_icon.png"));


       
       JLabel titleLabel = new JLabel("Choose an option", SwingConstants.CENTER);
       titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
       backgroundPanel.add(titleLabel, BorderLayout.NORTH);
       JPanel buttonPanel = new JPanel(new FlowLayout());
       buttonPanel.setOpaque(false);
       JButton rockButton = new JButton("Rock");
       buttonPanel.add(rockButton);
       JButton paperButton = new JButton("Paper");
       buttonPanel.add(paperButton);
       JButton scissorsButton = new JButton("Scissors");
       buttonPanel.add(scissorsButton);
       backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
       rockButton.addActionListener(e -> {
           playerChoice = "Rock";
           playGameRPC();
       });
       paperButton.addActionListener(e -> {
           playerChoice = "Paper";
           playGameRPC();
       });
       scissorsButton.addActionListener(e -> {
           playerChoice = "Scissors";
           playGameRPC();
       });
       gameWindowFrame.setVisible(true);
       gameWindowFrame.setLocationRelativeTo(null);
   }





   private static void playGameRPC() {
       Random random = new Random();
       int computerChoiceIndex = random.nextInt(3);
       String[] choices = {"Rock", "Paper", "Scissors"};
       computerChoice = choices[computerChoiceIndex];
    // Show animation of hands playing
       showAnimation(playerChoice, computerChoice);
    // Determine the winner
       String result = determineWinner(playerChoice, computerChoice);
       if (result.equals("You win!")) {
           playerScore++;
       } else if (result.equals("You lose!")) {
           computerScore++;
       }
       String message = "You chose: " + playerChoice + "\nComputer chose: " + computerChoice + "\n" + result +
               "\nPlayer Score: " + playerScore + "\nComputer Score: " + computerScore;
       if (result.equals("You win!")) {
           JOptionPane.showMessageDialog(gameWindowFrame, message, "You Win!", JOptionPane.INFORMATION_MESSAGE);
       } else if (result.equals("You lose!")) {
           JOptionPane.showMessageDialog(gameWindowFrame, message, "You Lose!", JOptionPane.ERROR_MESSAGE);
       } else {
           JOptionPane.showMessageDialog(gameWindowFrame, message, "It's a tie!", JOptionPane.PLAIN_MESSAGE);
       }
       if (playerScore == 3) {
           gameWindowFrame.dispose();
           JOptionPane.showMessageDialog(gameSelectionFrame, "Congratulations! You won against a computer!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
           gameSelectionFrame.setVisible(true);
       } else if (computerScore == 3) {
        gameWindowFrame.dispose();
        JOptionPane.showMessageDialog(gameSelectionFrame, "You Lose! Try again next time...", "Game Over", JOptionPane.ERROR_MESSAGE);
        gameSelectionFrame.setVisible(true);
       }
       playerChoice = null;
   }








   private static void showAnimation(String playerChoice, String computerChoice) {
       JFrame animationFrame = new JFrame("Rock Paper Scissors Animation");
       animationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       animationFrame.setSize(400, 400);
       animationFrame.setLayout(new BorderLayout());

       JPanel animationPanel = new JPanel() {
           private int frameCount = 0;
           private Timer timer;

           @Override
           protected void paintComponent(Graphics g) {
               super.paintComponent(g);
               Graphics2D g2d = (Graphics2D) g;
               // Clear the previous drawing
               g2d.setColor(Color.WHITE);
               g2d.fillRect(0, 0, getWidth(), getHeight());
               // Draw the player and computer hands
               drawHand(g2d, playerChoice, 50, 100);
               drawHand(g2d, computerChoice, 250, 100);
               frameCount++;
               if (frameCount > 10) {
                   timer.stop();
                   animationFrame.dispose();
               }
           }

           {
               timer = new Timer(100, e -> repaint());
               timer.start();
           }
       };
       animationFrame.add(animationPanel, BorderLayout.CENTER);
       animationFrame.setLocationRelativeTo(null);
       animationFrame.setVisible(true);
   }








   private static void drawHand(Graphics2D g2d, String choice, int x, int y) {
       switch (choice) {
           case "Rock":
               drawRock(g2d, x, y);
               break;
           case "Paper":
               drawPaper(g2d, x, y);
               break;
           case "Scissors":
               drawScissors(g2d, x, y);
               break;
       }
   }








   private static void drawRock(Graphics2D g2d, int x, int y) {
       g2d.setColor(Color.GRAY);
       g2d.fillOval(x, y, 50, 50);
   }








   private static void drawPaper(Graphics2D g2d, int x, int y) {
       g2d.setColor(Color.LIGHT_GRAY);
g2d.fillRect(x, y, 60, 80);
   }








   private static void drawScissors(Graphics2D g2d, int x, int y) {
       g2d.setColor(Color.DARK_GRAY);
       g2d.setStroke(new BasicStroke(5));
       g2d.drawLine(x, y, x + 30, y + 60);
       g2d.drawLine(x + 30, y, x, y + 60);
   }








   public static void showResultWindow(String message, boolean result, String currentGame) {
       JFrame resultFrame = new JFrame("Game Result");
       resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       resultFrame.setSize(400, 200);
       resultFrame.setLayout(new BorderLayout());
       BackgroundPanel backgroundPanel = new BackgroundPanel("mainbackground.jpeg");
       backgroundPanel.setLayout(new BorderLayout());
       resultFrame.setContentPane(backgroundPanel);
       JLabel resultLabel = new JLabel(message, SwingConstants.CENTER);
       resultLabel.setFont(new Font("Serif", Font.BOLD, 18));
       backgroundPanel.add(resultLabel, BorderLayout.CENTER);
       JPanel buttonPanel = new JPanel(new FlowLayout());
       buttonPanel.setOpaque(false);
       JButton playAgainButton = new JButton("Play Again");
       buttonPanel.add(playAgainButton);
       JButton goToHomeButton = new JButton("Go to Home");
       buttonPanel.add(goToHomeButton);
       backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
       playAgainButton.addActionListener(e -> {
           gameWindowFrame.dispose();
           resultFrame.setVisible(false);
           if (currentGame.equals("Rock Paper Scissors")) {
               createAndShowRockPaperScissorsGame();
           } else if (currentGame.equals("Who wants to be a millionaire!")) {
               createAndShowWhoWantsToBeAMillionaire();
           } else {
               createAndShowGuessingGame();
           }
       });
       goToHomeButton.addActionListener(e -> {
           gameWindowFrame.dispose();
           resultFrame.setVisible(false);
           gameSelectionFrame.setVisible(true);
       });
       resultFrame.setLocationRelativeTo(null);
       resultFrame.setVisible(true);
   }








   private static String determineWinner(String playerChoice, String computerChoice) {
       if (playerChoice.equals(computerChoice)) {
           return "It's a tie!";
       } else if (playerChoice.equals("Rock") && computerChoice.equals("Scissors") ||
               playerChoice.equals("Paper") && computerChoice.equals("Rock") ||
               playerChoice.equals("Scissors") && computerChoice.equals("Paper")) {
           return "You win!";
       } else {
           return "You lose!";
       }
   }



   private static void createAndShowWhoWantsToBeAMillionaire() {
    gameWindowFrame.dispose();
       MillionaireGame millionaireGame = new MillionaireGame(gameSelectionFrame);
       millionaireGame.setVisible(true);
   }

   private static void createAndShowTicTacToe(){
    gameWindowFrame.dispose();
    TicTacToe tictactoeGame = new TicTacToe(gameSelectionFrame);
    tictactoeGame.setVisible(true);
   }


   private static void createAndShowGuessingGame() {
    gameWindowFrame.dispose();
       GuessingGame guessingGame = new GuessingGame(gameSelectionFrame);
       guessingGame.setVisible(true);
   }
}


class MillionaireGame extends JFrame {
    private JFrame gameSelectionFrame;
    private JLabel questionLabel;
    private JButton option1;
    private JButton option2;
    private JButton option3;
    private JButton option4;
    private JButton fiftyFiftyButton;
    private JButton hintButton;
    private String correctAnswer;
    private int currentQuestion = 0;
    private int score = 0;
    private String[] questionSet;
    private String[][] optionSet;
    private String[] answerSet;
    private final int[] prizeAmounts = {
        100, 400, 500, 1500, 2500, 5000, 40000, 50000, 150000, 250000, 250000, 250000
    };

    private int fiftyFiftyUsed = 0;
    private final int maxFiftyFiftyUses = 2;
    private int hintUsed = 0;
    private final int maxHintUses = 2;
    private String[] hints;  // Array to hold hints for each question

    private Timer timer;
    private int timeLeft = 20; 




    private String[][] allQuestionSets = {
        {
            "1. What video game characters specialize in plumbing?",
            "2. What network is Lightning McQueen from?",
            "3. What year was Thomas The Tank Engine created?",
            "4. Who is the creator of Pokémon?",
            "5. What year was Fortnite created?",
            "6. How much does a Lamborghini aventador cost?",
            "7. What car is the fastest car in the world? (as of 2023)",
            "8. Where traditionally do birds fly in winter?",
            "9. Which year was python language developed?",
            "10. What city hosted the 2002 Olympic Games?",
            "11. The fear of insects is known as what?",
            "12. The Earth is approximately how many miles away from the sun?"
        },
        {
            "1. In A Bug's Life, What type of insect is Hopper?",
            "2. Who invented the lightbulb?",
            "3. When was Rosa Parks born?",
            "4. What year was Pluto declared 'not a planet'?",
            "5. How many characters were vaporized in Super Smash Bros. World Of Light?",
            "6. What are the names of Thomas The Tank Engine's faithful coaches?",
            "7. What is Yellowcake Uranium?",
            "8. Where was the first example of paper money used?",
            "9. Which of the following was considered one of the Seven Ancient Wonders?",
            "10. Who is generally considered the inventor of the motor car?",
            "11. Which of the following men does not have a chemical element named for him?",
            "12. According to the Population Reference Bureau, what is the approximate number of people who have ever lived on earth?"
        },
        {
            "1. What color are oranges?",
            "2. Are spiders insects?",
            "3. Raleigh is the capital of which State?",
            "4. What pet is the most popular pet in the USA?",
            "5. How fast can a falcon fly?",
            "6. What snake is the most venomous in the world?",
            "7. How much pressure does a Nile Crocodile bite down with?",
            "8. Which of the following languages has the longest alphabet?",
            "9. Which flies a green, white, and orange (in that order) tricolor flag?",
            "10. How many days make up a non-leap year in the Islamic calendar?",
            "11. Neurologists believe that the brain's medial ventral prefrontal cortex is activated when you do what?",
            "12. Who is credited with inventing the first mass-produced helicopter?"
        }
    };

    private String[][][] allOptionSets = {
        {
            {"Mario and Luigi", "Sonic and Tails", "Mega Man and Samus", "Sans and Papyrus"},
            {"PBS Kids", "Nicktoons", "Cartoon Network", "Disney Pixar"},
            {"2003", "1929", "1942", "1887"},
            {"Hiroyuki Sanada", "Akira Kurosawa", "Satoshi Tajiri", "Takashi Miike"},
            {"2007", "2019", "1998", "2017"},
            {"$417,650", "$317,550", "$255,300", "$550,250"},
            {"Koenigsegg Jesko Absolut", "Hennessey Venom", "Lamborghini Aventador", "Bugatti Chiron"},
            {"North", "East", "South", "West"},
            {"1992", "1989", "2001", "1991"},
            {"Tokyo", "Beijing", "Sydney", "Qatar"},
            {"Arachnophobia", "Entomophobia", "Ailurophobia", "Lilapsophobia"},
            {"9.3 million", "39 million", "93 million", "193 million"}
        },
        {
            {"Ladybug", "Grasshopper", "Tick", "Yellow Jacket"},
            {"Thomas Edison", "George Washington Carver", "Willy Wonka", "Julius Caesar"},
            {"2006", "1903", "1927", "1913"},
            {"2006", "1936", "1942", "1929"},
            {"23", "67", "73", "12"},
            {"Donald and Douglas", "Mario and Luigi", "Annie and Clarabel", "Sonic and Tails"},
            {"A Uranium Concentrate Powder", "A Piece of a Jigsaw Puzzle", "An Ingredient for Chocolate", "None of The Above"},
            {"China", "Turkey", "Greece", "Germany"},
            {"Colosseum", "Great Wall of China", "Colossus of Rhodes", "Wadi Rum"},
            {"Henry Ford", "Henry M. Leland", "Karl Benz", "Karl Rapp"},
            {"Albert Einstein", "Niels Bohr", "Isaac Newton", "Enrico Fermi"},
            {"50 billion", "1 trillion", "100 billion", "5 trillion"}
        },
        {
            {"Blue", "Black", "Orange", "Green"},
            {"Yes", "No", "Sometimes", "Only in movies"},
            {"South Dakota", "North Carolina", "Mississippi", "Tennessee"},
            {"Dogs", "Cats", "Fish", "Hamsters"},
            {"240 mph", "88 mph", "165 mph", "36 mph"},
            {"The Western Diamondback Rattlesnake", "The King Cobra", "The Black Mamba", "The Inland Taipan"},
            {"25,000 lbs per sq inch", "6,000 lbs per sq inch", "245,400 lbs per sq inch", "3,500 lbs per sq inch"},
            {"Greek", "Arabic", "French", "Russian"},
            {"Ireland", "Ivory Coast", "Italy", "India"},
            {"365", "354", "376", "400"},
            {"Have a panic attack", "Remember a name", "Get a joke", "Listen to music"},
            {"Igor Sikorsky", "Elmer Sperry", "Ferdinand von Zeppelin", "Gottlieb Daimler"}
        }
    };

    private String[][] allAnswerSets = {
        {
            "Mario and Luigi",
            "Disney Pixar",
            "1942",
            "Satoshi Tajiri",
            "2017",
            "$417,650",
            "Koenigsegg Jesko Absolut",
            "South",
            "1991",
            "Sydney",
            "Entomophobia",
            "93 million"
        },
        {
            "Grasshopper",
            "Thomas Edison",
            "1913",
            "2006",
            "73",
            "Annie and Clarabel",
            "A Uranium Concentrate Powder",
            "China",
            "Colossus of Rhodes",
            "Karl Benz",
            "Isaac Newton",
            "100 billion"
        },
        {
            "Orange",
            "No",
            "North Carolina",
            "Fish",
            "240 mph",
            "The Inland Taipan",
            "6,000 lbs per sq inch",
            "Russian",
            "Ireland",
            "354",
            "Get a joke",
            "Igor Sikorsky"
        }
    };

    private String[][] allHints = {
        {"Red and Green", "Toy Story", "Inbetween the years that World-War2 happened", "S", "Trump's inauguration", "50x8353", "made in Sweden", "antarctica", "20", "Oceania", "Enter", "Quatre vingt treize"},
        {"Green", "T", "23", "8", "21", "abc", "U3O8", "Panda", "Greek name", "Car+l", "Apple", "11 0s"},
        {"red and yellow", "are planes bird?", "Nice car!", "Gill", "How many hours is there in a day", "Somehow the answer reminds me of this city....", "2 times 3", "This country was in a very cold war", "Leprechauns", "eh? why are there 11 days off in the calendar?", "Hate the ones dads make", "got scores from the sky!"}
    };


    
    public MillionaireGame(JFrame gameSelectionFrame) {
        Random random = new Random();
        int selectedSet = random.nextInt(allQuestionSets.length);
        questionSet = allQuestionSets[selectedSet];
        optionSet = allOptionSets[selectedSet];
        answerSet = allAnswerSets[selectedSet];
        hints = allHints[selectedSet];
        this.gameSelectionFrame = gameSelectionFrame;
        createUI();
        setTitle("Who Wants to Be a Millionaire?");
    
        JLabel rulesLabel = new JLabel("<html><body style='width: 500px; text-align: left;'>Rules of the Game:<br><br>" +
                "The Who Wants to Be a Millionaire questions are structured according to five different Levels with each level increasing in difficulty. Each level contains three questions.<br>" +
                "Questions that are grouped into the same level will all be of similar difficulty.<br><br>" +
                "Question 1 $100<br>" +
                "Question 2 $500<br>" +
                "Question 3 $1,000<br>" +
                "Question 4 $2,500<br>" +
                "Question 5 $5,000<br><br>" +
                "Question 6 $10,000<br>" +
                "Question 7 $50,000<br>" +
                "Question 8 $100,000<br>" +
                "Question 9 $250,000<br>" +
                "Question 10 $500,000<br><br>" +
                "Question 11 $750,000<br>" +
                "Question 12 $1,000,000<br><br>" +
                "50/50 : removes two wrong answers from the multiple-choice selection, leaving the contestant with only one correct and one incorrect option. This means they have a 50/50 chance.<br><br>" +
                "Ask for a hint : Contestants can ask for a hint to the correct answer that they can use at any point during the game. They can use this option twice.<br><br>" +
                "</body></html>");
    
        rulesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    
        JPanel rulesPanel = new JPanel(new BorderLayout());
        rulesPanel.add(rulesLabel, BorderLayout.CENTER);
        rulesLabel.setForeground(Color.BLACK);
    
        JOptionPane.showMessageDialog(this, rulesPanel, "Rules of the Game", JOptionPane.PLAIN_MESSAGE, new ImageIcon("capsule_616x353.jpeg"));
    
        setQuestion();
    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 550);
        setLocationRelativeTo(null);
        setResizable(false);
    
        startTimer(); // Start the timer after the rule window is closed
    }
    
    
   
    private void createUI() {
        startTimer();
        BackgroundPanel backgroundPanel = new BackgroundPanel("maxresdefault.jpeg");
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        questionLabel = new JLabel();
        questionLabel.setForeground(new Color(169, 169, 169)); // Ensure the text is visible on the background
        questionLabel.setFont(new Font("Serif", Font.BOLD, 18)); // Adjust font size and style
        backgroundPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(2, 2, 10, 10));
        optionsPanel.setOpaque(false); // Make the options panel transparent
        option1 = new JButton();
        option2 = new JButton();
        option3 = new JButton();
        option4 = new JButton();
        Color purple = new Color(186, 85, 211); // Light Purple
        Color grayText = new Color(255, 255, 255); // White color
        option1.setBackground(purple);
        option1.setForeground(grayText);
        option1.setOpaque(true);
        option1.setBorderPainted(false);

        option2.setBackground(purple);
        option2.setForeground(grayText);
        option2.setOpaque(true);
        option2.setBorderPainted(false);

        option3.setBackground(purple);
        option3.setForeground(grayText);
        option3.setOpaque(true);
        option3.setBorderPainted(false);

        option4.setBackground(purple);
        option4.setForeground(grayText);
        option4.setOpaque(true);
        option4.setBorderPainted(false);

        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);
        backgroundPanel.add(optionsPanel, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setOpaque(false); // Make the button panel transparent
        fiftyFiftyButton = new JButton("50/50");
        hintButton = new JButton("Ask for a Hint");
        buttonPanel.add(fiftyFiftyButton);
        buttonPanel.add(hintButton);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        fiftyFiftyButton.addActionListener(e -> useFiftyFifty());
        hintButton.addActionListener(e -> useHint());

        setQuestion();
        option1.addActionListener(e -> checkAnswer(option1.getText()));
        option2.addActionListener(e -> checkAnswer(option2.getText()));
        option3.addActionListener(e -> checkAnswer(option3.getText()));
        option4.addActionListener(e -> checkAnswer(option4.getText()));
    }
 


    private void setQuestion() {
        if (timer != null) {
            timer.stop();
        }
        timeLeft = 20;
        updateQuestionLabel();
        option1.setText(optionSet[currentQuestion][0]);
        option2.setText(optionSet[currentQuestion][1]);
        option3.setText(optionSet[currentQuestion][2]);
        option4.setText(optionSet[currentQuestion][3]);
        correctAnswer = answerSet[currentQuestion];
        enableOptions();
    }


private void startTimer() {
         timer = new Timer(1000, e -> {
                timeLeft--;
                updateQuestionLabel(); // Update label to show remaining time
                if (timeLeft <= 0) {
                    timer.stop();
                    handleTimeOut(); // Handle time out logic
                }
            });
            timer.start();
        }


      private void updateQuestionLabel() {
        questionLabel.setText(questionSet[currentQuestion] + " (Time left: " + timeLeft + "s)");
    }

    private void handleTimeOut() {
        JOptionPane.showMessageDialog(this, "Time's up! You didn't choose an answer in time. You only got:$ "+ score, "Time's Up", JOptionPane.WARNING_MESSAGE);
        this.dispose();
        gameSelectionFrame.setVisible(true);
        // You can add additional code here to handle what happens when the time runs out, such as ending the game or moving to the next question
    }





    private void enableOptions() {
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
    }

    private void disableOptions(String... options) {
        for (String option : options) {
            if (option1.getText().equals(option)) {
                option1.setEnabled(false);
            } else if (option2.getText().equals(option)) {
                option2.setEnabled(false);
            } else if (option3.getText().equals(option)) {
                option3.setEnabled(false);
            } else if (option4.getText().equals(option)) {
                option4.setEnabled(false);
            }
        }
    }

    private void useFiftyFifty() {
        if (fiftyFiftyUsed < maxFiftyFiftyUses) {
            fiftyFiftyUsed++;
            String[] options = {
                option1.getText(),
                option2.getText(),
                option3.getText(),
                option4.getText()
            };
            int correctIndex = -1;
            for (int i = 0; i < options.length; i++) {
                if (options[i].equals(correctAnswer)) {
                    correctIndex = i;
                    break;
                }
            }

            // Create an array to hold the incorrect options
            String[] incorrectOptions = new String[3];
            int incorrectCount = 0;

            for (int i = 0; i < options.length; i++) {
                if (i != correctIndex) {
                    incorrectOptions[incorrectCount++] = options[i];
                }
            }

            // Randomly select two incorrect options to disable
            Random rand = new Random();
            int firstToRemove = rand.nextInt(3);
            int secondToRemove;
            do {
                secondToRemove = rand.nextInt(3);
            } while (secondToRemove == firstToRemove);

            disableOptions(
                incorrectOptions[firstToRemove],
                incorrectOptions[secondToRemove]
            );

        } else {
            JOptionPane.showMessageDialog(this, "You have already used 50/50 twice.", "50/50 Used Up", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void useHint() {
        if (hintUsed < maxHintUses) {
            hintUsed++;
            JOptionPane.showMessageDialog(this, hints[currentQuestion], "Hint", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "You have already used hints twice.", "Hints Used Up", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void checkAnswer(String selectedOption) {
        if (timer != null) {
            timer.stop();
        }
        if (selectedOption.equals(correctAnswer)) {
            score += prizeAmounts[currentQuestion];
            currentQuestion++;
            if (currentQuestion < questionSet.length) {
                setQuestion();
            } else {
                JOptionPane.showMessageDialog(this, "Congratulations! You won: $" + score, "Game Over", JOptionPane.PLAIN_MESSAGE);
                this.dispose();
                gameSelectionFrame.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wrong answer! You only got: $" + score, "Game Over", JOptionPane.PLAIN_MESSAGE);
            this.dispose();
            gameSelectionFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame gameSelectionFrame = new JFrame("Game Selection");
            gameSelectionFrame.setSize(400, 300);
            gameSelectionFrame.setLocationRelativeTo(null);
            gameSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JButton startButton = new JButton("Start Game");
            startButton.addActionListener(e -> {
                MillionaireGame game = new MillionaireGame(gameSelectionFrame);
                game.setVisible(true);
                gameSelectionFrame.setVisible(false);
            });

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(startButton, BorderLayout.CENTER);

            gameSelectionFrame.add(panel);
            gameSelectionFrame.setVisible(true);
        });
    }
}


class GuessingGame extends JFrame implements ActionListener {
   private static JFrame gameSelectionFrame;
   private JLabel promptLabel;
   private JButton[] buttons;
   private int roundCount;
   private String[] prompts = {
       "Think of a color:",
       "Think of a country in East Asia:",
       "Think of a city in America:",
       "Think of a pastry:",
       "Think of a drink:",
       "Think of a religion:",
       "Think of a dessert:",
       "Think of a fictional monster:",
       "Think of an avenger:",
       "Think of a shoe brand:",
       "Think of a book genre:",
       "Think of a musical instrument:",
       "Think of a famous mountain:"
   };








   private String[][] options = {
       {"Red", "Blue", "Yellow", "White"},
       {"Taiwan", "North Korea", "South Korea", "China"},
       {"New York", "Los Angeles", "Washington DC", "Orlando"},
       {"Cake", "Pie", "Bread", "Croissant"},
       {"Water", "Soda", "Coffee", "Tea"},
       {"Muslim","Christian", "Buddist", "Taoism"},
       {"Ice cream", "Pudding", "Brownie", "Candy"},
       {"Vampires", "Zombies", "Mummies", "Frankenstein"},
       {"Thor", "Iron Man", "Captain America", "Spiderman"},
       {"Nike", "Adidas", "Puma", "Crocs"},
       {"Everest","Kilimanjaro","Fuji","Matterhorn"},
       {"Piano","Guitar","Violin","Flute"},
       {"Mystery","Fantasy","Romance","Science fiction"}
   };


   private JLabel timerLabel;
   private Timer gameTimer;
   private int timeLeft;

   @SuppressWarnings("static-access")



public GuessingGame(JFrame gameSelectionFrame) {
       super("Guessing Game");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(600, 350);
       setLayout(new BorderLayout());
       setLocationRelativeTo(null);

       JLabel rulesLabel = new JLabel("Rules");
       rulesLabel.setFont(new Font("Arial", Font.PLAIN, 14));

       JPanel rulesPanel = new JPanel(new BorderLayout());
       rulesPanel.add(rulesLabel, BorderLayout.CENTER);
       rulesLabel.setForeground(Color.BLACK);

       JOptionPane.showMessageDialog(rulesPanel, "DON'T think like a computer. Win 5 times!", getTitle(), JOptionPane.PLAIN_MESSAGE, new ImageIcon("dont_copy_me_icon.png"));


       BackgroundPanel backgroundPanel = new BackgroundPanel("guessgame.jpeg");
       backgroundPanel.setLayout(new BorderLayout());
       setContentPane(backgroundPanel);


        promptLabel = new JLabel();
        promptLabel.setForeground(new Color(255, 255, 153)); // Set text color to be visible on the background
        promptLabel.setFont(new Font("Serif", Font.BOLD, 21)); // Set font size and style
        backgroundPanel.add(promptLabel, BorderLayout.NORTH);
       JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 100));
       buttonPanel.setOpaque(false);
       buttons = new JButton[4];

       Color[] buttonColors = {
        new Color(65, 105, 225), 
        new Color (65, 105, 225), 
        new Color(65, 105, 225), // dark blue
        new Color(65, 105, 225) 
    };

    Color textColor = new Color(255, 255, 153);

       for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(String.valueOf((char) (i + 'A')));
            buttons[i].setPreferredSize(new Dimension(50, 30)); // Set preferred size for buttons
            buttons[i].setBackground(buttonColors[i]); // Set the background color of the buttons
            buttons[i].setForeground(textColor);
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(false);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }


       add(buttonPanel, BorderLayout.CENTER);
       timerLabel = new JLabel();
       timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
       timerLabel.setFont(new Font("Serif", Font.BOLD, 18));
       timerLabel.setForeground(Color.RED);
       backgroundPanel.add(timerLabel, BorderLayout.SOUTH);

       this.gameSelectionFrame = gameSelectionFrame;
       roundCount = 0;
       gameTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timeLeft--;
            timerLabel.setText("Time left: " + timeLeft + " seconds");
           

            if (timeLeft <= 0) {
                JOptionPane.showMessageDialog(GuessingGame.this, "Time's up! You lose!", "Game Over", JOptionPane.ERROR_MESSAGE);
                gameTimer.stop();
                dispose();
                gameSelectionFrame.setVisible(true);
            }
        }
    });

    newRound();
}





   private void newRound() {
       if (roundCount >= 5) {
           JOptionPane.showMessageDialog(this, "Congratulations! You won!", "You Win!", JOptionPane.INFORMATION_MESSAGE);
           this.dispose();
           gameSelectionFrame.setVisible(true);
       }
    
 
       int promptIndex = new Random().nextInt(prompts.length);
       promptLabel.setText(prompts[promptIndex]);
 
       // Shuffle options
       List<String> shuffledOptions = new ArrayList<>(Arrays.asList(options[promptIndex]));
       Collections.shuffle(shuffledOptions);
       for (int i = 0; i < buttons.length; i++) {
           buttons[i].setText(shuffledOptions.get(i));
       }
    // Start the timer for 10 seconds
    timeLeft = 10;
    timerLabel.setText("Time left: " + timeLeft + " seconds");
    gameTimer.start();

    roundCount++;
}



   @Override
   public void actionPerformed(ActionEvent e) {
    gameTimer.stop();
       JButton button = (JButton) e.getSource();
       String userChoice = button.getText();
       String computerChoice = buttons[new Random().nextInt(buttons.length)].getText();
       if (userChoice.equals(computerChoice)) {
           JOptionPane.showMessageDialog(this, "You guessed the same as the computer! You lose!", "You Lose!", JOptionPane.ERROR_MESSAGE);
           this.dispose();
           gameSelectionFrame.setVisible(true);
       } else {
           JOptionPane.showMessageDialog(this, "Correct! You guessed a different option.", "Good Job!", JOptionPane.INFORMATION_MESSAGE);
           newRound();
       }
   }

   

   public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> new GuessingGame(gameSelectionFrame).setVisible(true));
   }
}




class TicTacToe extends JFrame implements ActionListener { //evan
    private static JFrame gameSelectionFrame;
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static final int SIZE = 100;
    private static final int GAP = 10;
    private JButton[][] buttons;
    private int[][] values;
    private int currentPlayer;
    

    @SuppressWarnings("static-access")
    public TicTacToe(JFrame gameSelectionFrame) {
        
        buttons = new JButton[ROWS][COLS];
        values = new int[ROWS][COLS];
        currentPlayer = 1;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new GridLayout(ROWS, COLS, GAP, GAP));
        setLocationRelativeTo(null);

        JLabel rulesLabel = new JLabel("Rules");
        rulesLabel.setFont(new Font("Serif", Font.PLAIN, 14));
 
        JPanel rulesPanel = new JPanel(new BorderLayout());
        rulesPanel.add(rulesLabel, BorderLayout.CENTER);
        rulesLabel.setForeground(Color.BLACK);
 
        JOptionPane.showMessageDialog(rulesPanel, "<html>Grab a friend and PLAY!<br><br>X: Player 1   O: Player 2</html>", getTitle(), JOptionPane.PLAIN_MESSAGE, new ImageIcon("Tic-tac-toe.png"));

     
        BackgroundPanel backgroundPanel = new BackgroundPanel("tic.jpeg");
        backgroundPanel.setLayout(new GridLayout(ROWS, COLS, GAP, GAP));
        setContentPane(backgroundPanel);

        JPanel gridPanel = new JPanel(new GridLayout(ROWS, COLS, GAP, GAP));
        gridPanel.setOpaque(false); 

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(new Dimension(SIZE, SIZE));
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 30));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
        this.gameSelectionFrame = gameSelectionFrame;
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int row = getRow(button);
        int col = getCol(button);

        if (values[row][col] == 0) {
            values[row][col] = currentPlayer;
            button.setText(currentPlayer == 1 ? "X" : "O");
        }

        if (checkWin(values, currentPlayer)) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            
            this.dispose();
            gameSelectionFrame.setVisible(true);
        } else if (isFull(values)) {
            JOptionPane.showMessageDialog(this, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            
            this.dispose();
            gameSelectionFrame.setVisible(true);
        }
        else {
            currentPlayer = currentPlayer == 1 ? 2 : 1;
        }
    }

    private int getRow(JButton button) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (buttons[i][j] == button) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getCol(JButton button) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (buttons[i][j] == button) {
                    return j;
                }
            }
        }
        return -1;
    }

    private boolean checkWin(int[][] values, int player) {
        for (int i = 0; i < ROWS; i++) {
            if (values[i][0] == player && values[i][1] == player && values[i][2] == player) {
                return true;
            }
        }

        for (int j = 0; j < COLS; j++) {
            if (values[0][j] == player && values[1][j] == player && values[2][j] == player) {
                return true;
            }
        }

        if (values[0][0] == player && values[1][1] == player && values[2][2] == player) {
            return true;
        }

        if (values[0][2] == player && values[1][1] == player && values[2][0] == player) {
            return true;
        }

        return false;
    }

    private boolean isFull(int[][] values) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (values[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /*private void resetGame() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                values[i][j] = 0;
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 1;
    }*/

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe(gameSelectionFrame).setVisible(true));
    }
}
