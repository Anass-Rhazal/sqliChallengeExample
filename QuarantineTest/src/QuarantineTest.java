import static org.junit.Assert.assertEquals;
 
import org.junit.Before;
import org.junit.Test;
 

public class QuarantineTest {
 
    private Quarantine quarantine;
 
    @Before
    public void setUp() {
        // The responsibility of the Quarantine object is to simulate diseases on a group of patients.
        // It is initialized with a list of patients' health status, separated by a comma.
        // Each health status is described by one or more characters
        // (in the test below, we will always have only one disease / patient)
        // The characters mean:
        // H : Healthy
        // F : Fever
        // D : Diabetes
        // T : Tuberculosis
        quarantine = new Quarantine("F,H,D,D,D,H,T");
 
        // Quarantine provides medicines to the patients, but can not target a specific group of patient.
        // The same medicines are always given to all the patients.
       
        // Then Quarantine can provide a report with this format:
        // "F:1 H:2 D:0 T:1 X:3"
        // Report give the number of patients that have the given disease.
        // X means Dead
    }
 
    @Test
    public void beforeTreatment() throws Exception {
        assertEquals("F:1 H:2 D:3 T:1 X:0", quarantine.report());
    }
 
    @Test
    public void noTreatment() throws Exception {
        quarantine.wait40Days();
        // diabetics die without insulin
        assertEquals("F:1 H:2 D:0 T:1 X:3", quarantine.report());
    }
// 
//    @Test
//    public void aspirin() throws Exception {
//        quarantine.aspirin();
//        quarantine.wait40Days();
//        // aspirin cure Fever
//        assertEquals("F:0 H:3 D:0 T:1 X:3", quarantine.report());
//    }
// 
//    @Test
//    public void antibiotic() throws Exception {
//        quarantine.antibiotic();
//        quarantine.wait40Days();
//        // antibiotic cure Tuberculosis
//        // but healthy people catch Fever if mixed with insulin.
//        assertEquals("F:1 H:3 D:0 T:0 X:3", quarantine.report());
//    }
// 
//    @Test
//    public void insulin() throws Exception {
//        quarantine.insulin();
//        quarantine.wait40Days();
//        // insulin prevent diabetic subject from dying, does not cure Diabetes,
//        assertEquals("F:1 H:2 D:3 T:1 X:0", quarantine.report());
//    }
// 
//    @Test
//    public void antibioticPlusInsulin() throws Exception {
//        quarantine.antibiotic();
//        quarantine.insulin();
//        quarantine.wait40Days();
//        // if insulin is mixed with antibiotic, healthy people catch Fever
//        assertEquals("F:3 H:1 D:3 T:0 X:0", quarantine.report());
//    }
// 
//    @Test
//    public void paracetamol() throws Exception {
//        quarantine.paracetamol();
//        quarantine.wait40Days();
//        // paracetamol heals fever
//        assertEquals("F:0 H:3 D:0 T:1 X:3", quarantine.report());
//    }
// 
//    @Test
//    public void paracetamolAndAspirin() throws Exception {
//        quarantine.paracetamol();
//        quarantine.aspirin();
//        quarantine.wait40Days();
//        // paracetamol kills subject if mixed with aspirin
//        assertEquals("F:0 H:0 D:0 T:0 X:7", quarantine.report());
//    }
 
}
 