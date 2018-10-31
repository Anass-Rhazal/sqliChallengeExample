package test.restaurant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class RestaurantTest {

    /**
     * recipe for a single dish of Tomato Mozzarella Salad is
     *
     * 1 ball of Mozzarella
     * 2 tomatoes
     * some olive oil
     *
     * preparation time is 6 minutes
     *
     * regarding Restaurant stocks :
     *   if there is not quantity it means that the ingredient is in stock and unlimited. (here it concern olive oil and pepper)
     */
    @Test
    public void shouldServeTomatoMozzarellaSalad(){
        Restaurant restaurant = new Restaurant("6 balls Mozzarella", "20 tomatoes", "olive oil", "pepper");
        Ticket ticket = restaurant.order("1 Tomato Mozzarella Salad");
        Meal meal = restaurant.retrieve(ticket);
        assertThat(meal.servedDishes()).isEqualTo(1);
        assertThat(meal.cookingDuration()).isEqualTo("6");
    }

    /**
     * write a test to ensure that when a recipe require out of stocks ingredients we receive an UnavailableDishException (unchecked)
     */
    @Test(expected = UnavailableDishException.class)
    public void shouldFailWhenOutOfStock(){
        Restaurant restaurant = new Restaurant("6 balls Mozzarella", "20 tomatoes", "olive oil", "pepper");
        Ticket ticket = restaurant.order("7 Tomato Mozzarella Salad");
        Meal meal = restaurant.retrieve(ticket);
    }



    /**
     * when cooking more than one dish of the same type :
     * first dish takes standard time, subsequents dish have their cooking time halved
     *
     * here : first = 6 ; 2nd = 3 ; 3rd = 3 ; 4th = 3 => 15 minutes
     */
    @Test
    public void shouldCookFasterWhenDoingMultipleInstanceOfTheSameDish(){
        Restaurant restaurant = new Restaurant("6 balls Mozzarella", "20 tomatoes", "olive oil", "sea salt");
        Ticket ticket = restaurant.order("4 Tomato Mozzarella Salad");
        Meal meal = restaurant.retrieve(ticket);
        assertThat(meal.servedDishes()).isEqualTo(4);
        assertThat(meal.cookingDuration()).isEqualTo("15");
    }


    /**
     * recipe for a Pizza is
     *
     * 1 ball of Mozzarella
     * 4 tomatoes
     * some olive oil
     * 100cl of water
     * 300g of Flour
     * sea salt
     *
     * cooking time is 10 minutes
     * baking time is 10 minutes at 300 degree celsius
     *
     *
     * Regarding baking :
     *   oven have unlimited capacity.
     *   multiple dishes can be baked at the same time (providing that the temperature is in a +/- 30 degree range ; otherwise it has to be sequential)
     *   dishes that requires baking are always started first, as preparation of dish that doesn't require baking can be done during baking time.
     */
    @Test
    public void shouldServeMixedOrders(){
        Restaurant restaurant = new Restaurant("1000 flour", "50 tomatoes", "sea salt", "6 balls Mozzarella", "olive oil", "water");
        Ticket ticket = restaurant.order("3 Tomato Mozzarella Salad").and("2 Pizza");
        Meal meal = restaurant.retrieve(ticket);
        assertThat(meal.servedDishes()).isEqualTo(5);
        assertThat(meal.cookingDuration()).isEqualTo("27");
    }
    
    @Test
    public void shouldServeMixedOrdersWithBakingLast(){
        Restaurant restaurant = new Restaurant("1000 flour", "50 tomatoes", "sea salt", "6 balls Mozzarella", "olive oil", "water");
        Ticket ticket = restaurant.order("2 Tomato Mozzarella Salad").and("2 Pizza");
        Meal meal = restaurant.retrieve(ticket);
        assertThat(meal.servedDishes()).isEqualTo(4);
        assertThat(meal.cookingDuration()).isEqualTo("25");
    }
    

    /**
     * recipe for one portion lasagna
     *
     * 0.5 ball of Mozzarella
     * 4 tomatoes
     * some olive oil
     * 3 sheet of lasagna pasta
     * 200g lean beef mince
     *
     * cooking time is 5 minutes
     * baking time is 30 minutes at 150 degree celsius
     *
     * Regarding ordering :
     *   meals are cooked in the order of creation of the ticket
     *   a ticket can be started as soon as previous ticket starts it's backing phase
     *   orders are taken sequentially without delay as a consequence :
     *      preparation time of subsequent order after first will be impacted by first order preparation time
     */
    @Test
    public void shouldServeMultipleOrders(){

        Restaurant restaurant = new Restaurant("1500 flour", "50 tomatoes", "sea salt", "8 balls Mozzarella", "olive oil", "water", "3 sheet of lasagna pasta", "750 lean beef mince");

        Ticket ticket1 = restaurant.order("3 Tomato Mozzarella Salad").and("2 Pizza");
        Ticket ticket2 = restaurant.order("2 Pizza").and("1 Lasagna");


        Meal meal2 = restaurant.retrieve(ticket2);
        assertThat(meal2.servedDishes()).isEqualTo(3);
        assertThat(meal2.cookingDuration()).isEqualTo("72");


        Meal meal1 = restaurant.retrieve(ticket1);
        assertThat(meal1.servedDishes()).isEqualTo(5);
        assertThat(meal1.cookingDuration()).isEqualTo("27");

    }

}

