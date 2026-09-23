package pekan3;
import java.util.ArrayList;
public class Rekening {
	private String nomorRekening;
	private String namaPemilik;
	private double saldo;
	private String pin;
	
	
	ArrayList<Transaksi> riwayatTransaksi;
	
	public Rekening (String nomor, String nama, double saldoAwal, String pinAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		if(pinAwal.length() == 6) {
			this.pin = pinAwal;
		}else {
			System.out.println("Peringatan:PIN harus 6 digit! Menggunakan PIN default 123456 ");
			this.pin = "123456";
		}
		
		this.riwayatTransaksi = new ArrayList<>();
	
		System.out.println("Rekening atas nama "+ namaPemilik + " berhasil dibuat."); 
	}
	
	public String getNomorRekening() {return nomorRekening;}
	public String getNamaPemilik() {return namaPemilik;}
	
	public boolean otentikasi(String inputPin) {
		return this.pin.equals(inputPin);
	}
	
	public void setorTunai(double nominal) {
		if (nominal > 0 && nominal <= 500000 ) {
			saldo += nominal;
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi (idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
		}else {
			System.out.println("Gagal: nominal setor harus lebih dari 0 atau melebihi 500.000!");
		}
	}
	
public void tarikTunai (double nominalDebit) {
		
		if (nominalDebit > 10000) {
			if(saldo < nominalDebit) {
				System.out.println("saldo anda tidak cukup");
				
			}else {
			saldo -= nominalDebit;
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi (idTrx, "Debit", nominalDebit);
			riwayatTransaksi.add(trxBaru);
			System.out.println("tarik tunai Rp"+ nominalDebit + "berhasil. Saldo saat ini : Rp"+saldo);
			}
			
		}else {
			System.out.println("Gagal: Nominal tarik harus lebih dari Rp10000!");
		}
	}

public void cetakMutasi() {
	if (riwayatTransaksi.isEmpty()) {
		System.out.println(" Belum ada transaksi");
	}else {
		for (Transaksi trx : riwayatTransaksi) {
			trx.cetakDetail();
		}
		
	}
}

public void cekInformasi() {
	System.out.println("--- INFO REKENING ---");
	System.out.println("No. Rekening: "+ nomorRekening);
	System.out.println("Nama Pemilik: "+ namaPemilik);
	System.out.println("Saldo Akhir: Rp"+ saldo);
	System.out.println("-------------------");
	System.out.println("");
}

public static boolean isPinLemah(String pin) {
    boolean semuaSama = true;
    boolean berurutanNaik = true;
    boolean berurutanTurun = true;

    for (int i = 0; i < pin.length() - 1; i++) {
        int digitSekarang = Character.getNumericValue(pin.charAt(i));
        int digitBerikutnya = Character.getNumericValue(pin.charAt(i + 1));

        
        if (digitSekarang != digitBerikutnya) {
            semuaSama = false;
        }

        
        if (digitBerikutnya != digitSekarang + 1) {
            berurutanNaik = false;
        }

        
        if (digitBerikutnya != digitSekarang - 1) {
            berurutanTurun = false;
        }
    }


    return semuaSama || berurutanNaik || berurutanTurun;
}



}
