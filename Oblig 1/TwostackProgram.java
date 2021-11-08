package no.uib.info233.Oblig1;

import java.util.Arrays;

/**
 *Klasse TwostackProgram implementerer et kjørbart program som endrer fra infix til prefix
 *
 * @author Cecilie Lyshoel
 * @version oblig1 v 1
 *
 */
public class TwostackProgram{

    /**
     * Metode for å bergene vekt/presidens av operatir
     *
     * @param operator
     * @return vekt for operator
     */
    private static int operatorWeight(String operator){
        if(operator.equals("+") ||operator.equals("-")){
            return 1;
        }else if(operator.equals("*")|| operator.equals("/")){
            return 2;
        }else
            return 0;
    }

    /**
     * Metode for å finne vekten/presidensen for operatir på toppen av høyre stabel
     *
     * @param stack
     * @return vekten til topen av stabelen
     */
    private static int getTopOfRightStackWeight(TwostackArray<String> stack) {
        try{
            return operatorWeight(stack.peek(true));
        } catch(TwostackEmptyException e) {
            return 0;
        }
    }
//kjører stack peek right. om ikke feilmelding, anta at det er et element. Om exception, anta ikke element

    /**
     * Metode for å sjekke om høyre stabel er tom
     *
     * @param stack
     * @return om stack er tom
     */
    private static boolean isRightStackEmpty(TwostackArray<String> stack) {
        try{
            stack.peek(true);
            return false;
        } catch(TwostackEmptyException e) {
            return true;
        }
    }

    /**
     * Metode for å plukke to periandi og en operator og sette sammen til en string og legge på ventsre stabel
     *
     * @param stack
     * @throws TwostackEmptyException
     */
    private static void flyttOperasjon(TwostackArray<String> stack) throws TwostackEmptyException {
        String operand1 = stack.pop(false);
        String operand2 = stack.pop(false);
        String operator1 = stack.pop(true);

        String tmp = operator1 + operand2 + operand1;// byttet om på operand1 og operand2 for å få algoritmen til å gå opp
        stack.push(false, tmp);
    }

    /**
     * Main-metode for å kjøre program
     * Algoritmen er noe annerledes enn beskrevet i oppgaven, men denne implementasjonen gir korrekt utput i motsetning til når man følger algoritmen i oppgaven.
     * blant annet skal detvare operator+operand2+operand1, og man skal legge. Dessuten ser det ut til at deler av algoritmen for å få korrekt output mangler.
     *
     * @param args
     */
    public static void main(String[]args){
        String[] inputArray = new String[]{"a","+","b","*","c"}; //input til inputArray
        TwostackArray<String> stack = new TwostackArray();


        for(String input : inputArray){ //går gjennom elementer i array
            if (input.equals("+")||input.equals("-")||input.equals("*")||input.equals("/")) {//om operator
                try {
                    while(getTopOfRightStackWeight(stack) >= operatorWeight(input)){ //om vekten på topen av høyre stabel er lik eller mindre enn inputweight
                            flyttOperasjon(stack);//kjør flyttOperasjon()
                    }
                }catch (TwostackEmptyException e) {
                        System.out.println("Operatorer og opeander i feil rekkefølge");
                }

                    stack.push(true, input);//legg til høyre stabel
            }else{

                stack.push(false, input); //legg til venstre stabel, operandi
            }
        }

        try {
            while (!isRightStackEmpty(stack)) {//hvis høyre stabel ikke er tom
                flyttOperasjon(stack);//kjør flyttOperasjon()
            }
        } catch (TwostackEmptyException e) {
            System.out.println("Kunne ikke hente mer data fra stack");
        }

        System.out.println(Arrays.asList(inputArray));//Skriv ut input
        try {
            System.out.println(stack.pop(false));//Skriv ut output
        } catch (TwostackEmptyException e) {
            System.out.println("Ingen data å hente");
        }
    }
}
