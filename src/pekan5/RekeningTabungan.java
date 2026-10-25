package pekan5;

public class RekeningTabungan extends Rekening{
	private double sukuBunga;
	
	public RekeningTabungan (String nomor, String nama, double saldoAwal, String pinAwal, double sukuBunga) {
		super(nomor, nama, saldoAwal, pinAwal);
		this.sukuBunga = sukuBunga;
	}
	
	public void tambahBungaAkhirBulan() {
		double nominalBunga = saldo * (sukuBunga / 100);
		saldo += nominalBunga;
		
		String idTrx = "TRX-B-" + System.currentTimeMillis();
		riwayatTransaksi.add(new Transaksi(idTrx, "Bunga", nominalBunga));
		System.out.println("Bunga " + sukuBunga + "% berhasil ditambahkan: Rp" + nominalBunga);
	}
	public void tambahBungaAkhirTahun() {
		if (saldo > 10000000) {
			double nominalBunga = saldo * (sukuBunga / 100);
			saldo += nominalBunga;
			
			String idTrx = "TRX-B-" + System.currentTimeMillis();
			riwayatTransaksi.add(new Transaksi(idTrx, "Bunga", nominalBunga));
			System.out.println("Bunga " + sukuBunga + "% berhasil ditambahkan: Rp" + nominalBunga);
	} else {
		System.out.println("nominal saldo tidak mencukupi");
	}
	
	}
}
