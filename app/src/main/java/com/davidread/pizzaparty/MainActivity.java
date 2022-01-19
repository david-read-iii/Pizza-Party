package com.davidread.pizzaparty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * {@link MainActivity} is an activity class that shows a form to the user for calculating the
 * number of pizzas they should order.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * {@link EditText} to get the number of people from the user.
     */
    private EditText numberOfPeopleEditText;

    /**
     * {@link EditText} to get the slices per pizza from the user.
     */
    private EditText slicesPerPizzaEditText;

    /**
     * {@link RadioGroup} to get the hunger level of the group from the user.
     */
    private RadioGroup hungryRadioGroup;

    /**
     * {@link RadioGroup} to get the pizza thickness from the user.
     */
    private RadioGroup pizzaThicknessRadioGroup;

    /**
     * {@link EditText} to get how the user's day is going.
     */
    private EditText dayRatingEditText;

    /**
     * {@link TextView} to show the result of the pizza calculation.
     */
    private TextView totalPizzasTextView;

    /**
     * Callback method invoked when the activity is initially created. It simply inflates the layout
     * and initializes all {@link View} objects used in the form.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to all Views used in the form.
        numberOfPeopleEditText = findViewById(R.id.number_of_people_edit_text);
        slicesPerPizzaEditText = findViewById(R.id.slices_per_pizza_edit_text);
        hungryRadioGroup = findViewById(R.id.hungry_radio_group);
        pizzaThicknessRadioGroup = findViewById(R.id.pizza_thickness_radio_group);
        dayRatingEditText = findViewById(R.id.day_rating_edit_text);
        totalPizzasTextView = findViewById(R.id.total_pizzas_text_view);
    }

    /**
     * Fills all {@link View} objects in the form with dummy values.
     */
    public void dummyValuesClick(View view) {
        numberOfPeopleEditText.setText("10");
        slicesPerPizzaEditText.setText("8");
        hungryRadioGroup.check(R.id.light_radio_button);
        pizzaThicknessRadioGroup.check(R.id.thin_radio_button);
        dayRatingEditText.setText("5");
        totalPizzasTextView.setText(getString(R.string.total_pizzas_empty_field));
    }

    /**
     * Removes all values from the {@link View} objects in the form.
     */
    public void resetClick(View view) {
        numberOfPeopleEditText.setText("");
        slicesPerPizzaEditText.setText("");
        hungryRadioGroup.check(R.id.light_radio_button);
        pizzaThicknessRadioGroup.check(R.id.thin_radio_button);
        dayRatingEditText.setText("");
        totalPizzasTextView.setText(getString(R.string.total_pizzas_empty_field));
    }

    /**
     * Calculates the total pizzas each person should receive given the values from the {@link View}
     * objects in the form. The computed value is put in {@link MainActivity#totalPizzasTextView}.
     * If a field in the form has an invalid value, the field is set in an error state and the
     * function returns early.
     */
    public void calculateClick(View view) {

        // Get number of people from EditText.
        String numberOfPeopleString = numberOfPeopleEditText.getText().toString();
        int numberOfPeople;
        try {
            numberOfPeople = Integer.parseInt(numberOfPeopleString);
            numberOfPeopleEditText.setError(null);
        } catch (NumberFormatException e) {
            // Set error on EditText and return early if the number of people int cannot be parsed.
            numberOfPeopleEditText.setError("");
            return;
        }

        // Get slices per pizza from EditText.
        String slicesPerPizzaString = slicesPerPizzaEditText.getText().toString();
        int slicesPerPizza;
        try {
            slicesPerPizza = Integer.parseInt(slicesPerPizzaString);
            slicesPerPizzaEditText.setError(null);
        } catch (NumberFormatException e) {
            // Set error on EditText and return early if the slices per pizza int cannot be parsed.
            slicesPerPizzaEditText.setError("");
            return;
        }

        /* Calculate slices per person. Hunger level determines the base slices per person and pizza
         * thickness determines whether a slice per person will be deducted. */
        int slicesPerPerson = 0;

        int hungryRadioGroupCheckedId = hungryRadioGroup.getCheckedRadioButtonId();
        if (hungryRadioGroupCheckedId == R.id.light_radio_button) {
            slicesPerPerson = 2;
        } else if (hungryRadioGroupCheckedId == R.id.medium_radio_button) {
            slicesPerPerson = 3;
        } else if (hungryRadioGroupCheckedId == R.id.ravenous_radio_button) {
            slicesPerPerson = 4;
        }

        int pizzaThicknessRadioGroupCheckedId = pizzaThicknessRadioGroup.getCheckedRadioButtonId();
        if (pizzaThicknessRadioGroupCheckedId == R.id.thick_radio_button && slicesPerPerson > 0) {
            slicesPerPerson--;
        }

        /* Calculate whether the user qualifies for an extra two pizzas. This happens when they are
         * having a bad day. */
        int extraPizzas;

        String dayRatingString = dayRatingEditText.getText().toString();
        int dayRating;
        try {
            dayRating = Integer.parseInt(dayRatingString);
            dayRatingEditText.setError(null);
        } catch (NumberFormatException e) {
            // Set error on EditText and return early if the day rating int cannot be parsed.
            dayRatingEditText.setError("");
            return;
        }

        if (dayRating < 0 || dayRating > 10) {
            // Set error on EditText and return early if the day rating int is not in the appropriate range.
            dayRatingEditText.setError("");
            return;
        }

        if (dayRating <= 4) {
            extraPizzas = 2;
        } else {
            extraPizzas = 0;
        }

        // Calculate total pizzas.
        int totalPizzas = (int) Math.ceil(numberOfPeople * slicesPerPerson / (double) slicesPerPizza) + extraPizzas;

        // Update total pizzas TextView.
        totalPizzasTextView.setText(getString(R.string.total_pizzas_field, totalPizzas));
    }
}