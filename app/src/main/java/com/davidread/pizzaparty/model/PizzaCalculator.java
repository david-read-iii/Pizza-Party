package com.davidread.pizzaparty.model;

/**
 * {@link PizzaCalculator} is a model class for calculating the total number of pizzas a party
 * should order and the total price for the pizzas given the parameters in the constructor.
 */
public class PizzaCalculator {

    /**
     * Enum constants to represent the hunger level of a party of people.
     */
    public enum PartyHungerLevel {
        LIGHT, MEDIUM, RAVENOUS
    }

    /**
     * Enum constants to represent the thickness of a pizza's crust.
     */
    public enum PizzaThickness {
        THIN, THICK
    }

    /**
     * Int representing the number of people at the party. Should be a non-negative integer.
     */
    private final int partySize;

    /**
     * Int representing the number of slices on each pizza pie. Should be a non-negative integer.
     */
    private final int slicesPerPizza;

    /**
     * Double representing the price of each pizza pie. Should be a non-negative double.
     */
    private final double pricePerPizza;

    /**
     * {@link PartyHungerLevel} representing the hunger level of the party.
     */
    private final PartyHungerLevel partyHungerLevel;

    /**
     * {@link PizzaThickness} representing the thickness of each pizza pie.
     */
    private final PizzaThickness pizzaThickness;

    /**
     * Constructs a new {@link PizzaCalculator}.
     *
     * @param partySize        Int representing the number of people at the party. Should be a
     *                         non-negative integer.
     * @param slicesPerPizza   Int representing the number of slices on each pizza pie. Should be a
     *                         non-negative integer.
     * @param pricePerPizza    Double representing the price of each pizza pie. Should be a
     *                         non-negative double.
     * @param partyHungerLevel {@link PartyHungerLevel} representing the hunger level of the party.
     * @param pizzaThickness   {@link PizzaThickness} representing the thickness of each pizza pie.
     */
    public PizzaCalculator(int partySize, int slicesPerPizza, double pricePerPizza,
                           PartyHungerLevel partyHungerLevel, PizzaThickness pizzaThickness) {
        this.partySize = Math.max(partySize, 0);
        this.slicesPerPizza = Math.max(slicesPerPizza, 0);
        this.pricePerPizza = Math.max(pricePerPizza, 0.00);
        this.partyHungerLevel = partyHungerLevel;
        this.pizzaThickness = pizzaThickness;
    }

    /**
     * Returns the number of pizzas the party should order.
     */
    public int getTotalPizzas() {

        int slicesPerPerson;
        switch (partyHungerLevel) {
            case LIGHT:
                // For light hunger, each person gets 2 slices.
                slicesPerPerson = 2;
                break;
            case MEDIUM:
                // For medium hunger, each person gets 3 slices.
                slicesPerPerson = 3;
                break;
            case RAVENOUS:
                // For ravenous hunger, each person gets 4 slices.
                slicesPerPerson = 4;
                break;
            default:
                slicesPerPerson = 0;
        }

        if (pizzaThickness == PizzaThickness.THICK && slicesPerPerson > 0) {
            // For thick crust pizza, each person gets 1 less slice.
            slicesPerPerson--;
        }

        return (int) Math.ceil(partySize * slicesPerPerson / (double) slicesPerPizza);
    }

    /**
     * Returns the price the party can expect to pay when ordering the pizzas.
     */
    public double getTotalPrice() {
        return getTotalPizzas() * pricePerPizza;
    }
}
