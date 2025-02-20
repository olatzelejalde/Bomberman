package eredua;

public class Gelaxka {
	private String mota;
	private boolean gogorra;
	private boolean okupatuta;
	
	public Gelaxka(String mota, boolean gogorra) {
		this.mota = mota;
		this.gogorra = gogorra;
		this.okupatuta = false;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = "mota";
	}
	
	public boolean gogorraDa() {
		return gogorra;
	}

	public boolean okupatutaDago() {
		return okupatuta;
	}

	public boolean bideaDago() {
		return mota.equals("hutsik");
	}

	public void apurtu() {
		if (getMota().equals("biguna")) {
			mota = "hutsik";
			System.out.println("Bloke biguna zegoen eta orain hutsik dago!!");
		}
		else if (okupatutaDago() == false) {
			System.out.println("Hutsik zegoen!!");
		}
		else { // gogorraDa() == true
			System.out.println("Bloke googorra da eta ezin da apurtu!!");
		}
	}

	public void suaJarri() {
		this.mota = "sua";
	}

	public void suaKendu() {
		this.mota = "hutsik";
	}

}
