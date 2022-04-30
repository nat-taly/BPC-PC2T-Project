package cz.vut;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Numbers {
    public static int pouzeCelaCisla(Scanner sc) {
        int cislo = 0;
        try
        {
            cislo = sc.nextInt();
        }
        catch(Exception e)
        {
            System.out.println("Nastala vyjimka typu "+e.toString());
            System.out.println("zadejte prosim cele cislo ");
            sc.nextLine();
            cislo = pouzeCelaCisla(sc);
        }
        return cislo;
    }
    public static float pouzeCisla(Scanner sc) {
        float cislo = 0;
        try
        {
            cislo = sc.nextFloat();
        }
        catch(Exception e)
        {
            System.out.println("Nastala vyjimka typu "+e.toString());
            System.out.println("zadejte prosim cislo ");
            sc.nextLine();
            cislo = pouzeCisla(sc);
        }
        return cislo;
    }
    public static int pouzeZnamku(Scanner sc){
        int cislo;
        try {
            cislo = sc.nextInt();
            if (cislo > 5 || cislo < 1){
                throw new MarkException(cislo);
            }
        } catch (MarkException e) {
            System.out.println("Známka musí být od 1 - 5");
            sc.nextLine();
            cislo = pouzeZnamku(sc);
        } catch (InputMismatchException e){
            System.out.println("Známka musí být celé číslo");
            sc.nextLine();
            cislo = pouzeZnamku(sc);
        }
        return cislo;


    }
}
