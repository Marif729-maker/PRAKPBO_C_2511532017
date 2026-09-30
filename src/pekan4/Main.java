package pekan4;
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
			System.out.println("8. simulasi akhir bulan");
			System.out.println("9. simulasi akhir tahun");
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
			            System.out.println("Gagal: PIN terlalu mudah! Tidak boleh menggunakan angka berulang (misal: 111111) "
			            		+ "atau berurutan (misal: 123456 / 654321).\n");
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
			    input.nextLine();
			    System.out.println("pilih produk \n1.Tabungan umum \n2.Tabungan Giro");
			    int produk = input.nextInt();
			    if (produk == 1) {
			    	System.out.println("masukkan jumlah suku bunga (dalam persen):");
			    	double bunga = input.nextDouble();
			    	akunAktif = new RekeningTabungan ( no, nama, saldo, pin, bunga);
			    } else  {
			    	System.out.println("masukkan limit pinjaman: ");
			    	double limit = input.nextDouble();
			    	akunAktif = new RekeningGiro (no, nama, saldo, pin, limit);
			    	
			    }

			    if (saldo < 50000) {
			        System.out.println("Gagal: Saldo awal minimal Rp50.000");
			    } else {    
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
			    if (akunAktif == null) {
			        System.out.println("Error: Anda belum membuka rekening!");
			    } else {
			        System.out.print("Masukkan PIN saat ini: ");
			        String pinLama = input.nextLine(); 
			        
			        if (akunAktif.otentikasi(pinLama)) {
			            System.out.print("Masukkan PIN baru (6 digit angka): ");
			            String pinBaru = input.nextLine();
			            
			            if (akunAktif.setPin(pinBaru)) {
			                System.out.println("PIN berhasil diubah!");
			            } else {
			                System.out.println("Gagal mengubah PIN: PIN baru harus 6 digit angka dan "
			                		+ "tidak boleh terlalu mudah (berulang/berurutan).");
			            }
			        } else {
			            System.out.println("Gagal: PIN lama salah!");
			        }
			    }
			    break;	
			    
			case 8:
				if (akunAktif == null) {
                    System.out.println("Error: Anda belum memilih rekening!");
                } else {
                    if (akunAktif instanceof RekeningTabungan) {
                 
                        RekeningTabungan tabungan = (RekeningTabungan) akunAktif;
                        tabungan.tambahBungaAkhirBulan();
                    } else if (akunAktif instanceof RekeningGiro) {
                        System.out.println("Rekening Giro tidak memiliki fitur penambahan bunga otomatis.");
                    } else {
                        System.out.println("Jenis rekening tidak dikenali.");
                    }
                }
                break;
                
			case 9:
				if (akunAktif == null) {
                    System.out.println("Error: Anda belum memilih rekening!");
                } else {
                    if (akunAktif instanceof RekeningTabungan) {
                 
                        RekeningTabungan tabungan = (RekeningTabungan) akunAktif;
                        tabungan.tambahBungaAkhirTahun();
                    } else if (akunAktif instanceof RekeningGiro) {
                        System.out.println("Rekening Giro tidak memiliki fitur penambahan bunga otomatis.");
                    } else {
                        System.out.println("Jenis rekening tidak dikenali.");
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
	}}
