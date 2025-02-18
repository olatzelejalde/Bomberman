package eredua;

public class Gelaxka {
	private String mota;
	private boolean suntsiezina;
	private boolean okupatuta;
	
	public Gelaxka(String mota, boolean suntsiezina) {
		this.mota = mota;
		this.suntsiezina = suntsiezina;
		this.okupatuta = false;
	}

	public String getMota() {
		return mota;
	}

	public boolean suntsiezinaDa() {
		return suntsiezina;
	}

	public boolean okupatutaDago() {
		return okupatuta;
	}

	public boolean bideaDago() {
		if (!suntsiezina && !okupatuta && !mota.equals("bonba")) {
			return true;
		}
		else {
			return false;
		}
	}

	public void apurtu() {
		if (mota.equals("apurkorra")) {
			mota = "biguna";
			suntsiezina = false;
		}
	}

	public void bonbaJarri() {
		mota = "bonba";
		okupatuta = true;
	}

	public void bonbaKendu() {
		mota = "hutsik";
		okupatuta = false;
	}

	public void suaJarri() {
		mota = "sua";
		okupatuta = true;
	}

	public void suaKendu() {
		mota = "hutsik";
		okupatuta = false;
	}

}
