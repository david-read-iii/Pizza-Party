package com.davidread.pizzaparty.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.davidread.pizzaparty.R;
import com.davidread.pizzaparty.model.PizzaCalculator;
import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;

/**
 * {@link MainActivity} is an activity class that shows a form to the user for calculating the
 * number of pizzas and total price of the pizzas.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * {@link String} identifiers for objects put into instance state {@link Bundle} objects.
     */
    private static final String PARTY_SIZE_EXTRA = "party_size";
    private static final String SLICES_PER_PIZZA_EXTRA = "slices_per_pizza";
    private static final String PRICE_PER_PIZZA_EXTRA = "price_per_pizza";
    private static final String PARTY_HUNGER_LEVEL_EXTRA = "party_hunger_level";
    private static final String PIZZA_THICKNESS_EXTRA = "pizza_thickness";
    private static final String TOTAL_PIZZAS_EXTRA = "total_pizzas";
    private static final String TOTAL_PRICE_EXTRA = "total_price";

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
     * {@link Spinner} to get the party hunger level from the user.
     */
    private Spinner partyHungerLevelSpinner;

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
     * {@link Button} to insert dummy values into the form.
     */
    private Button dummyValuesButton;

    /**
     * {@link Button} to reset the form.
     */
    private Button resetButton;

    /**
     * {@link Button} to calculate the total number of pizzas and total price.
     */
    private Button calculateButton;

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
        partyHungerLevelSpinner = findViewById(R.id.party_hunger_level_spinner);
        pizzaThicknessRadioGroup = findViewById(R.id.pizza_thickness_radio_group);
        totalPizzasTextView = findViewById(R.id.total_pizzas_text_view);
        totalPriceTextView = findViewById(R.id.total_price_text_view);
        dummyValuesButton = findViewById(R.id.dummy_values_button);
        resetButton = findViewById(R.id.reset_button);
        calculateButton = findViewById(R.id.calculate_button);

        // Add TextWatchers on each EditText.
        partySizeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int partySize;
                try {
                    partySize = Integer.parseInt(s.toString());
                    partySizeEditText.setError(null);
                } catch (NumberFormatException e) {
                    partySizeEditText.setError(getString(R.string.positive_integer_hint));
                    Snackbar.make(partySizeEditText, R.string.form_error_message, Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (partySize < 0) {
                    partySizeEditText.setError(getString(R.string.positive_integer_hint));
                    Snackbar.make(partySizeEditText, R.string.form_error_message, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        slicesPerPizzaEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int slicesPerPizza;
                try {
                    slicesPerPizza = Integer.parseInt(s.toString());
                    slicesPerPizzaEditText.setError(null);
                } catch (NumberFormatException e) {
                    slicesPerPizzaEditText.setError(getString(R.string.positive_integer_hint));
                    Snackbar.make(slicesPerPizzaEditText, R.string.form_error_message, Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (slicesPerPizza < 0) {
                    slicesPerPizzaEditText.setError(getString(R.string.positive_integer_hint));
                    Snackbar.make(slicesPerPizzaEditText, R.string.form_error_message, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        pricePerPizzaEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double pricePerPizza;
                try {
                    pricePerPizza = Double.parseDouble(s.toString());
                    pricePerPizzaEditText.setError(null);
                } catch (NumberFormatException e) {
                    pricePerPizzaEditText.setError(getString(R.string.price_hint));
                    Snackbar.make(pricePerPizzaEditText, R.string.form_error_message, Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (pricePerPizza < 0) {
                    pricePerPizzaEditText.setError(getString(R.string.price_hint));
                    Snackbar.make(pricePerPizzaEditText, R.string.form_error_message, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Set adapter of Spinner.
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.party_hunger_level_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        partyHungerLevelSpinner.setAdapter(spinnerAdapter);

        // Attach OnClickListeners to each button.
        dummyValuesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dummyValuesClick(v);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetClick(v);
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateClick(v);
            }
        });
    }

    /**
     * Callback method invoked before a configuration change. It saves the state of the form.
     *
     * @param outState {@link Bundle} holding the state of the form.
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(PARTY_SIZE_EXTRA, partySizeEditText.getText().toString());
        outState.putString(SLICES_PER_PIZZA_EXTRA, slicesPerPizzaEditText.getText().toString());
        outState.putString(PRICE_PER_PIZZA_EXTRA, pricePerPizzaEditText.getText().toString());
        outState.putInt(PARTY_HUNGER_LEVEL_EXTRA, partyHungerLevelSpinner.getSelectedItemPosition());
        outState.putInt(PIZZA_THICKNESS_EXTRA, pizzaThicknessRadioGroup.getCheckedRadioButtonId());
        outState.putString(TOTAL_PIZZAS_EXTRA, totalPizzasTextView.getText().toString());
        outState.putString(TOTAL_PRICE_EXTRA, totalPriceTextView.getText().toString());
    }

    /**
     * Callback method invoked after a configuration change occurs. It restores the state of the
     * form.
     *
     * @param savedInstanceState {@link Bundle} holding the state of the form before the
     *                           configuration change.
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        partySizeEditText.setText(savedInstanceState.getString(PARTY_SIZE_EXTRA));
        slicesPerPizzaEditText.setText(savedInstanceState.getString(SLICES_PER_PIZZA_EXTRA));
        pricePerPizzaEditText.setText(savedInstanceState.getString(PRICE_PER_PIZZA_EXTRA));
        partyHungerLevelSpinner.setSelection(savedInstanceState.getInt(PARTY_HUNGER_LEVEL_EXTRA));
        pizzaThicknessRadioGroup.check(savedInstanceState.getInt(PIZZA_THICKNESS_EXTRA));
        totalPizzasTextView.setText(savedInstanceState.getString(TOTAL_PIZZAS_EXTRA));
        totalPriceTextView.setText(savedInstanceState.getString(TOTAL_PRICE_EXTRA));
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
        partyHungerLevelSpinner.setSelection(0);
        pizzaThicknessRadioGroup.check(R.id.thin_radio_button);
        totalPizzasTextView.setText(getString(R.string.total_pizzas_empty_field));
        totalPriceTextView.setText(getString(R.string.total_price_empty_field));
        Snackbar.make(view, R.string.dummy_values_inserted_message, Snackbar.LENGTH_SHORT).show();
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
        partyHungerLevelSpinner.setSelection(0);
        pizzaThicknessRadioGroup.check(R.id.thin_radio_button);
        totalPizzasTextView.setText(getString(R.string.total_pizzas_empty_field));
        totalPriceTextView.setText(getString(R.string.total_price_empty_field));
        Snackbar.make(view, R.string.form_reset_message, Snackbar.LENGTH_SHORT).show();
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
        } catch (NumberFormatException e) {
            return;
        }
        if (partySize < 0) {
            return;
        }

        // Get slices per pizza from its EditText.
        int slicesPerPizza;
        try {
            String slicesPerPizzaString = slicesPerPizzaEditText.getText().toString();
            slicesPerPizza = Integer.parseInt(slicesPerPizzaString);
        } catch (NumberFormatException e) {
            return;
        }
        if (slicesPerPizza < 0) {
            return;
        }

        // Get price per pizza from its EditText.
        double pricePerPizza;
        try {
            String pricePerPizzaString = pricePerPizzaEditText.getText().toString();
            pricePerPizza = Double.parseDouble(pricePerPizzaString);
        } catch (NumberFormatException e) {
            return;
        }
        if (pricePerPizza < 0) {
            return;
        }

        // Get party hunger level from its RadioGroup.
        PizzaCalculator.PartyHungerLevel partyHungerLevel = null;
        int partyHungerLevelSpinnerPosition = partyHungerLevelSpinner.getSelectedItemPosition();
        if (partyHungerLevelSpinnerPosition == 0) {
            partyHungerLevel = PizzaCalculator.PartyHungerLevel.LIGHT;
        } else if (partyHungerLevelSpinnerPosition == 1) {
            partyHungerLevel = PizzaCalculator.PartyHungerLevel.MEDIUM;
        } else if (partyHungerLevelSpinnerPosition == 2) {
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