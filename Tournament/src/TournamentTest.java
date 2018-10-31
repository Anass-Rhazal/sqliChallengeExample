
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;



/**
 * This is a duel simulation
 * <p>
 * Blow exchange are sequential, A engage B means that A will give the first
 * blow, then B will respond
 */
public class TournamentTest {

    /**
     * A warriors.Swordsman has 100 hit points and use a 1 hand sword that does 5 dmg A
     * warriors.Viking has 120 hit points and use a 1 hand axe that does 6 dmg
     */
    @Test
    public void SwordsmanVsViking() {

        Swordsman swordsman = new Swordsman();

        Viking viking = new Viking();

        swordsman.engage(viking);

        assertThat(swordsman.hitPoints()).isEqualTo(0);
        assertThat(viking.hitPoints()).isEqualTo(35);

    }

    /**
     * a buckler cancel all the damages of a blow one time out of two a buckler
     * is destroyed after blocking 3 blow from an axe
     */
    @Test
    public void SwordsmanWithBucklerVsVikingWithBuckler() {

        Swordsman swordsman = new Swordsman().equip("buckler");

        Viking viking = new Viking().equip("buckler");

        swordsman.engage(viking);

        assertThat(swordsman.hitPoints()).isEqualTo(0);
        assertThat(viking.hitPoints()).isEqualTo(70);
    }

    /**
     * an warriors.Highlander as 150 hit points and fight with a Great Sword a Great
     * Sword is a two handed sword deliver 12 damages, but can attack only 2 every 3
     * an defense : reduce all received damages by 3 & reduce delivered
     * damages by one
     */
    @Test
    public void ArmoredSwordsmanVsViking() {

        Highlander highlander = new Highlander();

        Swordsman swordsman = new Swordsman().equip("buckler").equip("defense");

        swordsman.engage(highlander);

        assertThat(swordsman.hitPoints()).isEqualTo(0);
        assertThat(highlander.hitPoints()).isEqualTo(10);

    }

//    /**
//     * a vicious warriors.Swordsman is a warriors.Swordsman that put poison on his weapon. poison
//     * add 20 damages on two first blows a veteran warriors.Highlander goes Berserk once
//     * his hit points are under 30% of his initial total once Berserk, he
//     * doubles his damages
//     */
//    @Test
//    // interesting but might be a bit long...
//    public void ViciousSwordsmanVsVeteranHighlander() {
//
//        Swordsman swordsman = new Swordsman("Vicious").equip("axe").equip("buckler").equip("defense");
//
//        Highlander highlander = new Highlander("Veteran");
//
//        swordsman.engage(highlander);
//
//        assertThat(swordsman.hitPoints()).isEqualTo(1);
//        assertThat(highlander.hitPoints()).isEqualTo(0);
//
//    }

}
