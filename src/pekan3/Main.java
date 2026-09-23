package pekan3;
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
			System.out.println("6. cetak mutasi (riwayat)");
			System.out.println("7. ganti pin");
			System.out.println("0. Keluar");
			System.out.println("Pilih Menu: ");
			  
	              
	            
			int pilihan = input.nextInt();
			input.nextLine();
			switch (pilihan) {
			case 1:
			    String pin = "";
			    boolean pinValid = false;

			    while (!pinValid) {
			        System.out.print("Masukkan No PIN awal (6 digit angka): ");
			        pin = input.nextLine();

			      
			        if (pin.length() != 6 || !pin.matches("\\d+")) {
			            System.out.println("Gagal: PIN harus terdiri dari tepat 6 digit angka!\n");
			        } 
		
			        else if (Rekening.isPinLemah(pin)) {
			            System.out.println("Gagal: PIN terlalu mudah! Tidak boleh menggunakan angka berulang (misal: 111111) atau berurutan (misal: 123456 / 654321).\n");
			        } 
			     
			        else {
			            pinValid = true;
			        }
			    }

			    System.out.print("Masukkan No Rekening: ");
			    String no = input.nextLine();

			    System.out.print("Masukkan Nama Pemilik: ");
			    String nama = input.nextLine();

			    System.out.print("Masukkan Saldo Awal: ");
			    double saldo = input.nextDouble();
			    input.nextLine(); // Consume newline

			    if (saldo < 50000) {
			        System.out.println("Gagal: Saldo awal minimal Rp50.000");
			    } else {    
			        akunAktif = new Rekening(no, nama, saldo, pin);
			        daftarRekening.add(akunAktif);
			    }
			    break;
			
			case 2: 
				if (akunAktif == null) {
					System.out.println("Error : Mohon Maaf, Anda belum Memiliki nomor rekening!");
				}else {
					System.out.println("masukkan pin anda:");
					String InputPin = input.nextLine();
					if (akunAktif.otentikasi(InputPin)) {
					
					System.out.println("Masukkan nominal setor: ");
					double setor = input.nextDouble();
					
					if(setor < 10000) {
						System.out.println("saldo setor minimal Rp10.000");
						
					}else {
						
					
					akunAktif.setorTunai(setor);
					}}
					
					}
				break;
				
			case 3:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				}else {
					System.out.println("masukkan pin anda:");
					String InputPin = input.nextLine();
					if (akunAktif.otentikasi(InputPin)) {
					System.out.println("Masukkan nominal tarik tunai: ");
					double tarik = input.nextDouble();	
						akunAktif.tarikTunai(tarik);
					
				}}
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
				
			case 6:
				akunAktif.cetakMutasi();
				break;				
			case 7:
				System.out.println("masukkan pin awal:");
				String pinawal = input.nextLine(); 
				if(akunAktif.otentikasi(pinawal)) {
					System.out.println("masukkan pin baru");
					String pinbaru = input.nextLine();
				}else {
					System.out.println("pin salah");
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
	}}
