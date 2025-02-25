import java.util.*;
import java.lang.Math;

public class Program {
    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int n, i, ora, j, iMax, nPesci, peso;

        System.out.println("Inserire il numero di pesci che saranno pescati: ");
        do {
            n = input.nextInt();
        } while (n < 1);
        int[] pesi = new int[n];
        int[] ore = new int[n];

        nPesci = 0;
        for (i = 0; i <= n - 1; i++) {
            peso = random.nextInt(9) * 50 + 100;
            System.out.println("Inserisci l'ora di cattura: ");
            do {
                ora = i + 10 % 24;
            } while (ora < 0 && ora > 24);
            System.out.println("Pesce di peso: " + peso + "g");
            System.out.println("Pescato all'ora: " + ora);
            nPesci = inserisciVettori(pesi, ore, nPesci, peso, ora, nPesci);
            j = 0;
            while (j < nPesci) {
                if (pesi[j] < pesi[nPesci - 1]) {
                    System.out.println("Il pesce che pesa " + pesi[j] + " grammi, che è stato catturato all'ora " + ore[j] + ", viene rilasciato");
                    nPesci = eliminaVettori(pesi, ore, nPesci, j);
                } else {
                    j = j + 1;
                }
            }
            visualizzaVettori(pesi, ore, nPesci);
        }
        iMax = ricercaMassimo(pesi, nPesci);
        System.out.println("Il pesce più pesante è quello che pesa " + pesi[iMax] + " grammi e che è stato catturato all'ora " + ore[iMax]);
        nPesci = rimuoviDuplicati(pesi, ore, nPesci);
        visualizzaVettori(pesi, ore, nPesci);
    }
    
    public static int eliminaVettori(int[] pesi, int[] ore, int nPesci, int posizione) {
        int i;

        for (i = nPesci; i <= posizione - 2; i++) {
            pesi[i] = pesi[i + 1];
            ore[i] = ore[i + 1];
        }
        nPesci = nPesci - 1;
        
        return nPesci;
    }
    
    public static int inserisciVettori(int[] pesi, int[] ore, int nPesci, int elementoPeso, int elementoOra, int posizione) {
        int i;

        for (i = nPesci; i >= posizione + 1; i--) {
            pesi[i] = pesi[i - 1];
            ore[i] = ore[i - 1];
        }
        pesi[posizione] = elementoPeso;
        ore[posizione] = elementoOra;
        nPesci = nPesci + 1;
        
        return nPesci;
    }
    
    public static int ricercaMassimo(int[] pesi, int nPesci) {
        int iMax, i;

        iMax = 0;
        for (i = 0; i <= nPesci - 1; i++) {
            if (pesi[i] > pesi[iMax]) {
                iMax = i;
            }
        }
        
        return iMax;
    }
    
    public static int rimuoviDuplicati(int[] pesi, int[] ore, int nPesci) {
        int i, j;

        for (i = 0; i <= nPesci - 2; i++) {
            j = i + 1;
            while (j <= nPesci - 1) {
                if (pesi[i] == pesi[j]) {
                    nPesci = eliminaVettori(pesi, ore, nPesci, j);
                } else {
                    j = j + 1;
                }
            }
        }
        
        return nPesci;
    }
    
    public static void visualizzaVettori(int[] pesi, int[] ore, int nPesci) {
        int i;

        for (i = 0; i <= nPesci - 1; i++) {
            System.out.println("------");
            System.out.println("Pesce n°" + i);
            System.out.println("Peso: " + pesi[i] + "g");
            System.out.println("Ora di cattura: " + ore[i]);
            System.out.println("------");
        }
    }
}
