package eredua;

public class EztandaUltra implements BonbaPortaera {

    public EztandaUltra() {
    }

    // Metodo bonba eztanda egiteko
    public void eztanda(int x, int y) {
        Laberinto laberinto = Jokoa.getJokoa().getLaberinto();
        laberinto.getMatriz()[x][y].setBonba(null);

        System.out.println("Eztanda Ultra: (" + x + "," + y + ")");

        // Eztanda kokatu bere posizioan
        laberinto.jarriSua(x, y);

        // Sua jarri bonbaren zutabe osoan (GOIKO ATALA)
        for (int zutabe = x - 1; zutabe >= 0; zutabe--) {
            if (blokeGogorraDa(laberinto, zutabe, y)) break;
            laberinto.jarriSua(zutabe, y);
            if (blokeBigunaDa(laberinto, zutabe, y)) break;
        }

        // Sua jarri bonbaren zutabe osoan (BEHEKO ATALA)
        for (int zutabe = x + 1; zutabe != 11; zutabe++) {
            if (blokeGogorraDa(laberinto, zutabe, y)) break;
            laberinto.jarriSua(zutabe, y);
            if (blokeBigunaDa(laberinto, zutabe, y)) break;
        }
        // Sua jarri bonbaren lerro osoan (EZKERRA)
        for (int lerro = y - 1; lerro >= 0; lerro--) {
            if (blokeGogorraDa(laberinto, x, lerro)) break;
            laberinto.jarriSua(x, lerro);
            if (blokeBigunaDa(laberinto, x, lerro)) break;
        }

        // Sua jarri bonbaren lerro osoan (ESKUINA)
        for (int lerro = y + 1; lerro < 17; lerro++) {
            if (blokeGogorraDa(laberinto, x, lerro)) break;
            laberinto.jarriSua(x, lerro);
            if (blokeBigunaDa(laberinto, x, lerro)) break;
        }
    }

    private boolean blokeGogorraDa(Laberinto laberinto, int i, int j) {
        return laberinto.getMatriz()[i][j].blokeDu() && !laberinto.getMatriz()[i][j].apurtuDaiteke();
    }

    private boolean blokeBigunaDa(Laberinto laberinto, int i, int j) {
        if (laberinto.getMatriz()[i][j].apurtuDaiteke()) {
            laberinto.getMatriz()[i][j].apurtuBlokea();
            return true;
        }
        return false;
    }

    // Sua kendu bonbaren posizioan eta ondokoetan
    public void kenduSua(int x, int y) {
        Laberinto laberinto = Jokoa.getJokoa().getLaberinto();

        // Bonbaren posizioa
        laberinto.kenduSua(x, y);

        // Zutabe (GOIKO ATALA)
        for (int zutabe = x - 1; zutabe >= 0; zutabe--) {
            if (blokeGogorraDa(laberinto, zutabe, y)) break;
            laberinto.kenduSua(zutabe, y);
            if (blokeBigunaDaKendu(laberinto, zutabe, y)) break;
        }

        // Zutabe (BEHEKO ATALA)
        for (int zutabe = x + 1; zutabe < 11; zutabe++) {
            if (blokeGogorraDa(laberinto, zutabe, y)) break;
            laberinto.kenduSua(zutabe, y);
            if (blokeBigunaDaKendu(laberinto, zutabe, y)) break;
        }

        // Lerro (EZKERRA)
        for (int lerro = y - 1; lerro >= 0; lerro--) {
            if (blokeGogorraDa(laberinto, x, lerro)) break;
            laberinto.kenduSua(x, lerro);
            if (blokeBigunaDaKendu(laberinto, x, lerro)) break;
        }

        // Lerro (ESKUINA)
        for (int lerro = y + 1; lerro < 17; lerro++) {
            if (blokeGogorraDa(laberinto, x, lerro)) break;
            laberinto.kenduSua(x, lerro);
            if (blokeBigunaDaKendu(laberinto, x, lerro)) break;
        }
    }

    private boolean blokeBigunaDaKendu(Laberinto laberinto, int i, int j) {
        return laberinto.getMatriz()[i][j].blokeDu() && laberinto.getMatriz()[i][j].apurtuDaiteke();
    }
}
