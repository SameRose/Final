package pkgApp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.util.NumberUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import pkgApp.RetirementApp;
import pkgCore.Retirement;




public class RetirementController implements Initializable {
	@FXML
	private RetirementApp mainApp = null;

	@FXML 
	private TextField txtYearsToWork;
	
	@FXML 
	private TextField txtAnnualReturnWorking;
	
	@FXML
	private TextField txtYearsRetired;
	
	@FXML
	private TextField txtAnnualReturnRetired;
	@FXML
	private TextField txtRequiredIncome;
	@FXML
	private TextField txtMonthlySSI;
	@FXML
	private TextField txtTotalAmountSaved;
	@FXML
	private TextField txtAmountToSave;
	@FXML
	private Button Clear;
	@FXML
	private Button Calc;

	@FXML
	public RetirementApp getMainApp() {
		return mainApp;
	}
	
	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	
	public void btnClear(ActionEvent event) {
		System.out.println("Clear pressed");

		
		

		txtYearsToWork.clear();
		txtAnnualReturnWorking.clear();
		txtYearsRetired.clear();
		txtAnnualReturnRetired.clear();
		txtRequiredIncome.clear();
		txtMonthlySSI.clear();
		txtTotalAmountSaved.clear();
		txtAmountToSave.clear();
	}
	
	

	
	
	

	@FXML
	public void btnCalculate(ActionEvent event) {

	

		if(isInputValid())
		{Retirement r = new Retirement((int) Integer.valueOf(txtYearsToWork.getText()),
				(double) Double.valueOf(txtAnnualReturnWorking.getText()),
				(int) Integer.valueOf(txtYearsRetired.getText()),
				(double) Double.valueOf(txtAnnualReturnRetired.getText()),
				(double) Double.valueOf(txtRequiredIncome.getText()), (double) Double.valueOf(txtMonthlySSI.getText()));

		txtTotalAmountSaved.setText(Double.toString(r.TotalAmountSaved()));

		txtAmountToSave.setText(Double.toString(r.AmountToSave()));
		}

	}
	
	
	
	
	
    private boolean isInputValid() {
        String errorMessage = "";

        try {
            Integer.parseInt(txtYearsToWork.getText());
        } catch (NumberFormatException e) {
            errorMessage += "Years To Work must be an integer!\n"; 
        }
        
        try {
            Integer.parseInt(txtYearsRetired.getText());
        } catch (NumberFormatException e) {
            errorMessage += "Years Retired must be an integer!\n"; 
        }
        
        try {
            if(Double.parseDouble(txtAnnualReturnWorking.getText())<0 || Double.parseDouble(txtAnnualReturnWorking.getText())>.2) {
            	errorMessage += "Annual Return Working must be between 0 and 0.20!\n";
            }
            
        } catch (NumberFormatException e) {
            errorMessage += "Annual Return Working must be a number!\n"; 
        }
        
        try {
        	if(Double.parseDouble(txtAnnualReturnRetired.getText())<0 || Double.parseDouble(txtAnnualReturnRetired.getText())>.03) {
            	errorMessage += "Annual Return Working must be between 0 and 0.03!\n";
            }
        } catch (NumberFormatException e) {
            errorMessage += "Annual Return Retired must be a number!\n"; 
        }
        
        try {
            Double.parseDouble(txtRequiredIncome.getText());
        } catch (NumberFormatException e) {
            errorMessage += "Required Income must be a number!\n"; 
        }

        try {
            Double.parseDouble(txtMonthlySSI.getText());
        } catch (NumberFormatException e) {
            errorMessage += "Monthly SSI must be a number!\n"; 
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
           // alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
	

}
