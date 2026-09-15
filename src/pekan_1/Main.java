package pekan_1;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
	public static void main (String[]args) {
		Scanner input = new Scanner (System.in);
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		Rekening akunAktif = null;
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama: ");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. ganti akun aktif");
			System.out.println("0. Keluar");
			System.out.println("Pilih Menu: ");
			  
	              
	            
			int pilihan = input.nextInt();
			input.nextLine();
			switch (pilihan) {
			case 1 :
			System.out.println("Masukkan No Rekening: ");
			String no = input.nextLine();
			System.out.println("Masukkan Nama Pemilik: ");
			String nama = input.nextLine();
			System.out.println("Masukkan Saldo Awal: ");
			double saldo = input.nextDouble();
			if(saldo < 50000) {
				System.out.println("saldo awal minimal Rp50.000");
				
			}else {	
			
			akunAktif = new Rekening(no, nama, saldo);
			daftarRekening.add(akunAktif);
			}
			break;
			
			case 2: 
				if (akunAktif == null) {
					System.out.println("Error : Mohon Maaf, Anda belum Memiliki nomor rekening!");
				}else {
					System.out.println("Masukkan nominal setor: ");
					double setor = input.nextDouble();
					
					if(setor < 10000) {
						System.out.println("saldo setor minimal Rp10.000");
						
					}else {
						
					
					akunAktif.setorTunai(setor);
					}
				}
				break;
				
			case 3:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				}else {
					System.out.println("Masukkan nominal tarik tunai: ");
					double tarik = input.nextDouble();	
						akunAktif.tarikTunai(tarik);
					
				}
				break;
				
			case 4: 
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				}else { 
					akunAktif.cekInformasi();
				}
				break;
				
			case 5:
				if (daftarRekening.isEmpty()) {
					System.out.println("Belum ada rekening yang terdaftar!");
				} else {
					System.out.println("DAFTAR REKENING TERDAFTAR ");
					for (int i = 0; i < daftarRekening.size(); i++) {
						System.out.println("Pilihan Ke-" + (i + 1) + ":");
					
						daftarRekening.get(i).cekInformasi();
						System.out.println("=============");
					}
					
					System.out.print("Pilih nomor indeks rekening yang ingin diaktifkan (1 - " + daftarRekening.size() + "): ");
					int indeksPilihan = input.nextInt();
					
					if (indeksPilihan >= 1 && indeksPilihan <= daftarRekening.size()) {
						
						akunAktif = daftarRekening.get(indeksPilihan - 1);
						System.out.println("Berhasil berganti ke akun pilihan Anda!");
					} else {
						System.out.println("Nomor pilihan tidak valid!");
					}
				}
				break;
                
			case 0: 
				isRunning = false;
				System.out.println("Sistem ditutup. Terima kasih!");
				break;
				
				default:
					System.out.println("Pilihan tidak valid!");
			}
	}
		input.close();
	}

}
