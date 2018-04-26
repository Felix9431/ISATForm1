/*
 * Need to be in MVC Form
 * Need input validation
 * Need automated email and text-responses
 * Need JavaDoc
 * Need a printout of the number of submitted forms with the relevant details
 */
package com.example.IST312_Final;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		// Create the VerticalLayout (layout)
		final VerticalLayout layout = new VerticalLayout();	 

		// Create the contents for the question panel
		// Create the firstName TextField where the user enters their first name
		TextField firstName = new TextField("First Name"); //required
		firstName.setDescription("Enter your first name");
			
		// Create the lastName TextField where the user enters their last name
		TextField lastName = new TextField("Last Name"); //required
		lastName.setDescription("Enter your last name");
		
		// Create the email TextField where the user enters their email address
		// NOTE: Needs an exception, needs to be in the form of ----@-----.com
		TextField email = new TextField("Email"); //required
		email.setDescription("Enter your email");
		
		// Create the phone TextField where the user enters their phone number
		// NOTE: Needs an exception, needs to be in the form of xxx-xxx-xxxx
		TextField phone = new TextField("Phone");
		phone.setDescription("Enter your phone number");

		// Create the ComboBox program
		ComboBox<String> program = new ComboBox<>("Program");  //required
		// Set the items that can be selected for the program ComboBox
		program.setItems("Information Systems Technologies Online Program",
				"Information Systems Technologies On-Campus Program");
		// Set the PopupWidth so that the information will fit
		program.setPopupWidth("Information Systems Technologies On-Campus Program");
		// Create the event listener for the program ComboBox
		program.addSelectionListener(event ->
		layout.addComponent(new Label("Selected " +
				event.getSelectedItem().orElse("none"))));      

		// Create the term ComboBox
		ComboBox<String> term = new ComboBox<>("Term");  //required
		// Set the items that can be selected for the term ComboBox
		term.setItems("Summer 2018", "Fall 2018", "Spring 2019");
		// Set the PopupWidth so that the information will fit
		term.setPopupWidth("Summer 2018");
		// Create the event listener for the term ComboBox
		term.addSelectionListener(event ->
		layout.addComponent(new Label("Selected " +
				event.getSelectedItem().orElse("none"))));
		
		// Create the info TextField where the user can enter any further information
		TextField info = new TextField("Provide us with any further information you"
				+ " think may be important");
		
		/*
		 * Create the salukiSpam TextField where the user has to type "S@luki"
		 * in order to help prevent spam
		 */
		TextField salukiSpam = new TextField("Type S@luki - This is to prevent spam");  //required

		// Create the clear form Button
		Button clearForm = new Button("Clear Form");
		// Create the event listener for the clearForm button
		clearForm.addClickListener(e -> {
			firstName.setValue("");
			lastName.setValue("");
			email.setValue("");
			phone.setValue("");
			program.setValue(null);
			term.setValue(null);
		});

		// Create the submit Button
		Button submit = new Button("Submit");
		// Create the event listener for the submit button
		submit.addClickListener(e -> {
			layout.addComponent(new Label("Thanks " + firstName.getValue() 
			+ ", it works!"));
		});

		// Create the Panel (questionsPanel)
		Panel questionsPanel = new Panel("Have Questions?");
		// Add the style to the questionsPanel
		questionsPanel.addStyleName("mypanelexample");
		// Make the questionsPanel shrink to fit the content that will be added to it
		questionsPanel.setSizeUndefined();
		// Add the questionsPanel to the VerticleLayout (layout)
		layout.addComponent(questionsPanel);

		// Create the FormLayout (formInfo) and add the content
		FormLayout formInfo = new FormLayout();
		formInfo.addStyleName("mypanelcontent");
		formInfo.addComponent(firstName);
		formInfo.addComponent(lastName);
		formInfo.addComponent(email);
		formInfo.addComponent(phone);
		formInfo.addComponent(program);
		formInfo.addComponent(term);
		formInfo.addComponent(info);
		formInfo.addComponent(salukiSpam);
		formInfo.addComponent(clearForm);
		formInfo.addComponent(submit);
		formInfo.setSizeUndefined(); // Shrink to fit
		formInfo.setMargin(true);
		// Add the FormLayout (formInfo) content to the panel (questionsPanel)
		questionsPanel.setContent(formInfo);

		// Add the content to the layout
		layout.addComponents(questionsPanel);

		// Finalizes the layout
		setContent(layout);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
