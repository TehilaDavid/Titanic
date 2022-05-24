import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainPanel extends JPanel {
    private JComboBox<String> pClassComboBox;
    private JComboBox<String> sexComboBox;
    private List<Passenger> passengerList;
    private JButton filterButton;
    private JTextField idMinTextField;
    private JTextField idMaxTextField;
    private JTextField nameTextField;
    private int classType;
    private String sexChoice;



    public MainPanel(int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE); //this is the path to the data file

        creatPassengerList(file);


        this.setLayout(null);
        this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width, height);

        JLabel passengerIdMinLabel = new JLabel("Passenger Id min: ");
        passengerIdMinLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(passengerIdMinLabel);

        this.idMinTextField = new JTextField();
        this.idMinTextField.setBounds(x + Constants.MARGIN_FROM_LEFT + Constants.LABEL_WIDTH, y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(this.idMinTextField);


        JLabel idMaxLabel = new JLabel("Passenger Id max: ");
        idMaxLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y + Constants.LABEL_HEIGHT + 5, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(idMaxLabel);


        this.idMaxTextField = new JTextField();
        this.idMaxTextField.setBounds(x + Constants.MARGIN_FROM_LEFT + Constants.LABEL_WIDTH, y + Constants.LABEL_HEIGHT + 5, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(this.idMaxTextField);


        JLabel pClassLabel = new JLabel("Passenger class: ");
        pClassLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y + 2 * (Constants.LABEL_HEIGHT + 5), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(pClassLabel);


        this.pClassComboBox = new JComboBox<>(Constants.PASSENGER_CLASS_OPTIONS);
        this.pClassComboBox.setBounds(pClassLabel.getX() + pClassLabel.getWidth() + 1, pClassLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        this.add(this.pClassComboBox);
        this.pClassComboBox.addActionListener((e) -> {
            this.classType = this.pClassComboBox.getSelectedIndex();
        });

        JLabel nameLabel = new JLabel("Passenger name: ");
        nameLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y + 3 * (Constants.LABEL_HEIGHT + 5), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(nameLabel);


        this.nameTextField = new JTextField();
        this.nameTextField.setBounds(nameLabel.getX() + nameLabel.getWidth() + 1, nameLabel.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(this.nameTextField);


        JLabel sexLabel = new JLabel("Passenger sex: ");
        sexLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y + 4 * (Constants.LABEL_HEIGHT + 5), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(sexLabel);


        this.sexComboBox = new JComboBox<>(Constants.PASSENGER_SEX_OPTIONS);
        this.sexComboBox.setBounds(sexLabel.getX() + sexLabel.getWidth() + 1, sexLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        this.add(this.sexComboBox);
        this.sexComboBox.addActionListener((e) -> {
            this.sexChoice = (String) this.sexComboBox.getSelectedItem();
            System.out.println(this.sexChoice);
        });



        this.filterButton = new JButton("filter");
        this.filterButton.setBounds(x + Constants.MARGIN_FROM_LEFT, y + 4 * (Constants.LABEL_HEIGHT + 5) + 50, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(filterButton);




        this.filterButton.addActionListener((e) -> {
            int minId = 0;
            try {
                minId = Integer.parseInt(this.idMinTextField.getText());
            }catch (NumberFormatException exception) {
//                exception.printStackTrace();
            }

            int maxId = this.passengerList.size();
            try {
                maxId = Integer.parseInt(this.idMaxTextField.getText());
            }catch (NumberFormatException exception) {
//                exception.printStackTrace();
            }


            int finalMinId = minId;
            int finalMaxId = maxId;
            List<Passenger> list = this.passengerList
                    .stream()
                    .filter(passenger -> passenger.isIdInRange(finalMinId, finalMaxId))
                    .filter(passenger -> passenger.isNameCorrect(this.nameTextField.getText()))
                    .filter(passenger -> passenger.isSameClass(this.classType))
                    .filter(passenger -> passenger.isSameSex(this.sexChoice))
                    .collect(Collectors.toList());
            System.out.println(list);

        });

    }






    public void creatPassengerList(File file) {
        String lineData;
        this.passengerList = new ArrayList<>();
        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(file);

                scanner.nextLine();
                while (scanner.hasNextLine()) {
                    lineData = scanner.nextLine();
                    Passenger passenger = new Passenger(lineData);
                    this.passengerList.add(passenger);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}