package pekan_1;
import java.text.NumberFormat;
public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	NumberFormat nf = NumberFormat.getCurrencyInstance();
	
	public Rekening (String nomor, String nama, double saldoAwal) {
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp"+ nf.format(saldoAwal));
	}
	
	public void setorTunai (double nominal) {
		
		if (nominal > 0) {
			saldo += nominal;
			System.out.println("Setor tunai Rp"+ nominal + "berhasil. Saldo saat ini : Rp" + nf.format(saldo));
			
		}else {
			System.out.println("Gagal: Nominal setor harus lebih dari 0!");
		}
	}
	
public void tarikTunai (double nominal) {
		
		if (nominal > 10000) {
			if(saldo < nominal) {
				System.out.println("saldo anda tidak cukup");
				
			}else {
			saldo -= nominal;
			System.out.println("tarik tunai Rp"+ nominal + "berhasil. Saldo saat ini : Rp" + nf.format(saldo));
			}
			
		}else {
			System.out.println("Gagal: Nominal tarik harus lebih dari Rp10000!");
		}
	}
	
	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening: "+ nomorRekening);
		System.out.println("Nama Pemilik: "+ namaPemilik);
		System.out.println("Saldo Akhir: Rp"+ saldo);
		System.out.println("-------------------");
	}

}
