package com.davidread.pizzaparty.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.davidread.pizzaparty.R;
import com.davidread.pizzaparty.model.PizzaCalculator;

import java.text.NumberFormat;

/**
 * {@link MainActivity} is an activity class that shows a form to the user for calculating the
 * number of pizzas and total price of the pizzas.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * {@link EditText} to get the party size from the user.
     */
    private EditText partySizeEditText;

    /**
     * {@link EditText} to get the slices per pizza from the user.
     */
    private EditText slicesPerPizzaEditText;

    /**
     * {@link EditText} to get the price per pizza from the user.
     */
    private EditText pricePerPizzaEditText;

    /**
     * {@link RadioGroup} to get the party hunger level from the user.
     */
    private RadioGroup partyHungerLevelRadioGroup;

    /**
     * {@link RadioGroup} to get the pizza thickness from the user.
     */
    private RadioGroup pizzaThicknessRadioGroup;

    /**
     * {@link TextView} to show the total number of pizzas the user should order.
     */
    private TextView totalPizzasTextView;

    /**
     * {@link TextView} to show the total price the user should expect to pay for the pizzas.
     */
    private TextView totalPriceTextView;

    /**
     * Callback method invoked when the activity is initially created. It simply inflates the layout
     * and initializes all {@link View} objects used in the form.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to all Views used in the form.
        partySizeEditText = findViewById(R.id.party_size_edit_text);
        slicesPerPizzaEditText = findViewById(R.id.slices_per_pizza_edit_text);
        pricePerPizzaEditText = findViewById(R.id.price_per_pizza_edit_text);
        partyHungerLevelRadioGroup = findViewById(R.id.party_hunger_level_radio_group);
        pizzaThicknessRadioGroup = findViewById(R.id.pizza_thickness_radio_group);
        totalPizzasTextView = findViewById(R.id.total_pizzas_text_view);
        totalPriceTextView = findViewById(R.id.total_price_text_view);
    }

    /**
     * Fills all {@link View} objects in the form with dummy values.
     */
    public void dummyValuesClick(View view) {
        partySizeEditText.setText("10");
        partySizeEditText.setError(null);
        slicesPerPizzaEditText.setText("8");
        slicesPerPizzaEditText.setError(null);
        pricePerPizzaEditText.setText("10.99");
        pricePerPizzaEditText.setError(null);
        partyHungerLevelRadioGroup.check(R.id.light_radio_button);
        pizzaThicknessRadioGroup.check(R.id.thin_radio_button);
        totalPizzasTextView.setText(getString(R.string.total_pizzas_empty_field));
        totalPriceTextView.setText(getString(R.string.total_price_empty_field));
    }

    /**
     * Removes all values from the {@link View} objects in the form.
     */
    public void resetClick(View view) {
        partySizeEditText.setText("");
        partySizeEditText.setError(null);
        slicesPerPizzaEditText.setText("");
        slicesPerPizzaEditText.setError(null);
        pricePerPizzaEditText.setText("");
        pricePerPizzaEditText.setError(null);
        partyHungerLevelRadioGroup.check(R.id.light_radio_button);
        pizzaThicknessRadioGroup.check(R.id.thin_radio_button);
        totalPizzasTextView.setText(getString(R.string.total_pizzas_empty_field));
        totalPriceTextView.setText(getString(R.string.total_price_empty_field));
    }

    /**
     * Uses an instance of {@link PizzaCalculator} to calculate the total pizzas the party should
     * order and the total price of the pizzas given the values in the form. If a field in the form
     * is invalid, then the field is set in an error state and the function returns early.
     */
    public void calculateClick(View view) {

        // Get party size from its EditText.
        int partySize;
        try {
            String partySizeString = partySizeEditText.getText().toString();
            partySize = Integer.parseInt(partySizeString);
            partySizeEditText.setError(null);
        } catch (NumberFormatException e) {
            partySizeEditText.setError(getString(R.string.positive_integer_hint));
            return;
        }
        if (partySize < 0) {
            partySizeEditText.setError(getString(R.string.positive_integer_hint));
            return;
        }

        // Get slices per pizza from its EditText.
        int slicesPerPizza;
        try {
            String slicesPerPizzaString = slicesPerPizzaEditText.getText().toString();
            slicesPerPizza = Integer.parseInt(slicesPerPizzaString);
            slicesPerPizzaEditText.setError(null);
        } catch (NumberFormatException e) {
            slicesPerPizzaEditText.setError(getString(R.string.positive_integer_hint));
            return;
        }
        if (slicesPerPizza < 0) {
            slicesPerPizzaEditText.setError(getString(R.string.positive_integer_hint));
            return;
        }

        // Get price per pizza from its EditText.
        double pricePerPizza;
        try {
            String pricePerPizzaString = pricePerPizzaEditText.getText().toString();
            pricePerPizza = Double.parseDouble(pricePerPizzaString);
            pricePerPizzaEditText.setError(null);
        } catch (NumberFormatException e) {
            pricePerPizzaEditText.setError(getString(R.string.price_hint));
            return;
        }
        if (pricePerPizza < 0) {
            pricePerPizzaEditText.setError(getString(R.string.price_hint));
            return;
        }

        // Get party hunger level from its RadioGroup.
        PizzaCalculator.PartyHungerLevel partyHungerLevel = null;
        int partyHungerLevelCheckedRadioButtonId = partyHungerLevelRadioGroup.getCheckedRadioButtonId();
        if (partyHungerLevelCheckedRadioButtonId == R.id.light_radio_button) {
            partyHungerLevel = PizzaCalculator.PartyHungerLevel.LIGHT;
        } else if (partyHungerLevelCheckedRadioButtonId == R.id.medium_radio_button) {
            partyHungerLevel = PizzaCalculator.PartyHungerLevel.MEDIUM;
        } else if (partyHungerLevelCheckedRadioButtonId == R.id.ravenous_radio_button) {
            partyHungerLevel = PizzaCalculator.PartyHungerLevel.RAVENOUS;
        }

        // Get pizza thickness from its RadioGroup.
        PizzaCalculator.PizzaThickness pizzaThickness = null;
        int pizzaThicknessCheckedRadioButtonId = pizzaThicknessRadioGroup.getCheckedRadioButtonId();
        if (pizzaThicknessCheckedRadioButtonId == R.id.thin_radio_button) {
            pizzaThickness = PizzaCalculator.PizzaThickness.THIN;
        } else if (pizzaThicknessCheckedRadioButtonId == R.id.thick_radio_button) {
            pizzaThickness = PizzaCalculator.PizzaThickness.THICK;
        }

        // Get total pizzas and total price from new PizzaCalculator given the arguments we got from the form.
        PizzaCalculator pizzaCalculator = new PizzaCalculator(partySize, slicesPerPizza, pricePerPizza, partyHungerLevel, pizzaThickness);
        int totalPizzas = pizzaCalculator.getTotalPizzas();
        double totalPrice = pizzaCalculator.getTotalPrice();

        // Update the TextViews with the total pizzas and total price.
        totalPizzasTextView.setText(getString(R.string.total_pizzas_field, totalPizzas));
        totalPriceTextView.setText(getString(R.string.total_price_field, NumberFormat.getCurrencyInstance().format(totalPrice)));
    }
}