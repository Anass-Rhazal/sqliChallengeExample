package electicTrip;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * An electric car's endurance is given by the amount of energy it can store in its battery (KWh)
 * and its efficiency (how many Km you can drive with one KWh of energy). Since electric
 * chargers are not yet so frequent on the road it is important to plan a trip according to where
 * the you can getCurrentCharge.
 * The very first charging stations are found today on highways near cities.
 * A trip is defined by a set of cities separated by the number of Km between two cities.
 * It is useful to have system that tells you to stop when you are passing by a charger if there's
 * no further charging infrastructure within the given range. This test suite will bring you to
 * build such a system.
 */
public class ElectricTripTest {

    @Test
    public void shouldGetAtDestinationWithLowerCharge() {
        ElectricTrip trip = new ElectricTrip("PARIS-200-BOURGES");
        int batterySize = 85; //KWh
        int lowSpeedPerformance = 5; //Km per KWh
        int highSpeedPerformance = 5; //Km per KWh
        int participantId = trip.startTripIn("PARIS", batterySize, lowSpeedPerformance, highSpeedPerformance);
        trip.go(participantId);
        assertThat(trip.locationOf(participantId), is("BOURGES"));
        assertThat(trip.chargeOf(participantId), is("53%")); //% is rounded to closest integer
    }

    @Test
    public void shouldGoToFinalDestination() {
        ElectricTrip trip = new ElectricTrip("PARIS-250-LIMOGES-100-BORDEAUX");
        int participantId = trip.startTripIn("PARIS", 85, 5, 5);
        trip.go(participantId);
        assertThat(trip.locationOf(participantId), is("BORDEAUX"));
        assertThat(trip.chargeOf(participantId), is("18%"));
    }

    @Test
    public void shouldStayAtIntermediateStopIfNotEnoughCharge() {
        ElectricTrip trip = new ElectricTrip("PARIS-250-LIMOGES-500-MARSEILLES");
        int participantId = trip.startTripIn("PARIS", 85, 5, 5);
        trip.go(participantId);
        assertThat(trip.locationOf(participantId), is("LIMOGES"));
        assertThat(trip.chargeOf(participantId), is("41%"));
        trip.go(participantId);
        assertThat(trip.locationOf(participantId), is("LIMOGES"));
        assertThat(trip.chargeOf(participantId), is("41%"));
    }

    @Test
    public void shouldConsumeMoreWhenSprinting() {
        ElectricTrip trip = new ElectricTrip("PARIS-250-LIMOGES-100-BORDEAUX");
        int participantId = trip.startTripIn("PARIS", 85, 5, 3);
        trip.sprint(participantId);
        assertThat(trip.locationOf(participantId), is("LIMOGES"));
        assertThat(trip.chargeOf(participantId), is("2%"));
    }

    @Test
    public void shouldMoveMultipleParticipantsAccordingly() {
        ElectricTrip trip = new ElectricTrip("PARIS-250-LIMOGES-100-BORDEAUX");
        int id1 = trip.startTripIn("PARIS", 85, 5, 3);
        int id2 = trip.startTripIn("LIMOGES", 70, 5, 3);
        trip.sprint(id1);
        trip.go(id2);
        assertThat(trip.locationOf(id1), is("LIMOGES"));
        assertThat(trip.chargeOf(id1), is("2%"));
        assertThat(trip.locationOf(id2), is("BORDEAUX"));
        assertThat(trip.chargeOf(id2),is("71%"));
    }

    @Test
    public void shouldAllowCharging() {
        ElectricTrip trip = new ElectricTrip("PARIS-250-LIMOGES:25-100-BORDEAUX"); //25 is the KWh charged per hour of getCurrentCharge time at Limoges
        int id = trip.startTripIn("PARIS", 85, 5, 3);
        trip.sprint(id);
        assertThat(trip.locationOf(id), is("LIMOGES"));
        assertThat(trip.chargeOf(id), is("2%"));
        int hoursOfCharge = 3;
        trip.charge(id, hoursOfCharge);
        trip.sprint(id);
        assertThat(trip.locationOf(id), is("BORDEAUX"));
        assertThat(trip.chargeOf(id), is("52%"));
    }

    @Test
    public void shouldNotChargeIfNoChargingAtLocation() {
        ElectricTrip trip = new ElectricTrip("LIMOGES:25-100-BORDEAUX-400-MARSEILLES");
        int id = trip.startTripIn("LIMOGES", 85, 5, 3);
        trip.go(id);
        assertThat(trip.locationOf(id), is("BORDEAUX"));
        String chargeOnArrival = trip.chargeOf(id);
        trip.charge(id, 4);
        trip.go(id);
        assertThat(trip.locationOf(id), is("BORDEAUX"));
        assertThat(trip.chargeOf(id), is(chargeOnArrival));
    }

    @Test
    public void shouldStopAtChargingLocationToReachFinalDestination() {
        ElectricTrip trip = new ElectricTrip("PARIS-250-LIMOGES:25-100-BORDEAUX-200-TOULOUSE:50-50-MONTPELLIER-200-MARSEILLES");
        int id = trip.startTripIn("PARIS", 85, 5, 3);
        trip.go(id);
        assertThat(trip.locationOf(id), is("LIMOGES"));
        assertThat(trip.chargeOf(id), is("41%"));
        trip.charge(id, 4);
        trip.go(id);
        assertThat(trip.locationOf(id), is("TOULOUSE"));
        assertThat(trip.chargeOf(id), is("29%"));
        trip.go(id);
        assertThat(trip.locationOf(id), is("TOULOUSE"));
        assertThat(trip.chargeOf(id), is("29%"));
        trip.charge(id,2);
        trip.go(id);
        assertThat(trip.locationOf(id), is("MARSEILLES"));
        assertThat(trip.chargeOf(id), is("41%"));
    }

}
