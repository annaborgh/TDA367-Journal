package src.Data;

import java.util.Scanner;

/*
* Kommentar av Adam:
*
* Du hade missat att implementera ILock så jag la till det. Detta gör så att lås gjorda av denna klassen är både av typ
* PinLock och ILock, och att klassen automatiskt "får" variabler och metoder som specifiseras i ILock.
* */
public class PinLock implements ILock {

    /*
    * Kommentar av Adam:
    *
    * De variabler som definieras nedan känns konstiga enl. mig. Tanken med dessa lås-klasser är ju att de ska hålla
    * låstypens grund-funktionalitet och inte mer. Tänk dig typ "Vad är ett lås i grund och botten" och det är vad denna
    * klassen är.
    * */
    // Wilma-variabler
    /*
    Scanner keypad = new Scanner(System.in);
    int setPinCode = keypad.nextInt();
    int pinCode = setPinCode();
    int inputPin = keypad.nextInt();
    int getPinCode = keypad.nextInt();
    */

    // Adam-variabler
    private int pinCode;
    private int lockId = 0;
    private boolean lockState = false;
    private boolean lockActive = false;

    /*
     * Kommentar av Adam:
     *
     * Detta är konstruktorn så man kan skapa ett objekt av denna klassen. Jag tänkte inte för mycket när jag bestämde
     * vad man skulle behöva bestämma för att skapa låset, så dessa kan så klart ändras.
     * */
    public PinLock(int pinCode, int lockId) {
        this.pinCode = pinCode;
        this.lockId = lockId;
    }

    /*
     * Kommentar av Adam:
     *
     * Detta är en getter, är endast här så man kan hämta och använda pinkoden utan att behöva direkt interagera med
     * variabeln. Det skapar en lite högre nivå av säkerhet i koden.
     * */
    public int getPinCode() {
        return this.pinCode;
    }

    /*
     * Kommentar av Adam:
     *
     * Denna metod är bra då man vill kunna ändra sin pinkod, men en set-metod har nästan aldrig något return värde.
     *
     * Jag ändrade metoden så den har en parameter newPin som när metoden kallas måste ges. Ändrade också så att metoden
     * inte ger ett return värde, tanken är att för att få värdet så ska man kalla metoden getPinCode ovan.
     * */
    public void setPinCode(int newPin) {
        this.pinCode = newPin;

        //Detta hade du skrivit
        /*
        System.out.print("Enter Pin Code: ");
        // int input
        return setPinCode();
        */
    }

    /*
     * Kommentar av Adam:
     *
     * Detta är en getter för lockId variabeln.
     * */
    public int getLockId() {
        return lockId;
    }

    /*
     * Kommentar av Adam:
     *
     * Denna metoden är så vi kan se om denna låstypen är aktiv eller inte. Tanken är att endast ett lås ska vara
     * aktivt åt gången.
     * */
    public boolean isLockActive() {
        return lockActive;
    }

    /*
     * Kommentar av Adam:
     *
     * Detta är en setter för variabeln lockActive. Min tanke med denna variabeln är att om den är true så är denna
     * lås-typen aktiv och att den ska vara lätt att ändra.
     * */
    public void setLockActive(boolean lockActive) {
        this.lockActive = lockActive;
    }

    /*
    * Kommentar av Adam:
    *
    * Denna metod finns i ILock och är ett default för alla lås.
    *
    * Tanken bakom den är att den är till för att ändra om låset är upplåst eller inte. Tänk dig att om boolean'en
    * lockState är true så är låset upplåst och om den är false så är det låst.
    * */
    @Override
    public void setLockState(boolean newLockState) {
        this.lockState = newLockState;
    }

    /*
    * Kommentar av Adam:
    *
    * Jag tar och kommenterar ut alla metoder som du gjort då vissa blir irrelevanta med mina variabler, samt då
    * vissa känns konstiga (Ger mer specifika tankar vid varje metod).
    * */


    /*
    * Kommentar av Adam:
    *
    * Jag håller med att det kan vara redundant att se sin pinkod som du skrev i din kommentar nedan.
    * */
    /*
    // onödigt att få se sin pinkod?

    public int pinCode () {
        System.out.print("Din pinkod är: " + pinCode);
        return 0;
    }
    */

    /*
     * Kommentar av Adam:
     *
     * Detta är en bra setter, missade den helt tills jag skrev den identiska settern ovan och är för lat för att ändra
     * det.
     * */
    /*
    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
    */

    /*
     * Kommentar av Adam:
     *
     * Variabeln getPinCode finns inte bland mina nya variabler så denna metoden blir redundant. Men bara som notering,
     * en getter ska endast returnera variabeln bunden till den, inte något annat. Detta betyder att kodraden:
     *       "getPinCode = this.inputPinCode()"
     * inte "får" finnas i denna metoden. (NOTERA: Detta är konvention, inte lag)
     * */
    /*
    public int getGetPinCode() {
            getPinCode = this.inputPinCode();
        return getPinCode;
    }
    */

    /*
     * Kommentar av Adam:
     *
     * Denna metoden ser ut som en setter som också returnerar ett värde, och då du redan hade skapat settern blir denna
     * redundant.
     * */
    /*
    private int pinCode() {
        this.pinCode = pinCode;
        return 0;
    }
    */

    /*
     * Kommentar av Adam:
     *
     * Jag förstår vad du tänkte med denna metod, men funktionaliteten som hanterar input av pinkoden, etc. ska inte
     * finnas i låset i sig självt. Detta hanterar antingen den stora model-klassen eller en liknande klass.
     *
     * Denna klassen (och klasserna för de andra låsen) är endast så vi kan separera de olika låstyperna och hantera
     * olika typer av lås på olika sätt.
     * */
    /*
    public int inputPinCode() {
        return 0;
    }
    */



}
