import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Driver {

	public static void main(String[] args) {
		File file = new File("sozluk.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		FileReader fileReader = null;
		FileWriter fileWriter = null;
		BufferedReader bufferedReader = null;
		SortedMap<String, String> sortedMap = new TreeMap<>();
		Scanner scanner = new Scanner(System.in);
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			fileWriter = new FileWriter(file, true); //yazılan metni dosyanın sonuna ekler
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				String[] kelimeler = line.split(":");
				sortedMap.put(kelimeler[0], kelimeler[1]);

			}
			Set<Entry<String, String>> entries = sortedMap.entrySet();

			String kelime = null;
			System.out.print("kelime giriniz:");
			while (!(kelime = scanner.nextLine()).equals("exit")) {
				String value = sortedMap.get(kelime);
				if (value != null)
					System.out.println(value);
				else {
					System.out.println("kelime bulunamadı.Eklemek ister misiniz: Evet(E) Hayır(H)");
					String cevap = scanner.nextLine();
					if (cevap.equals("E")) {
						System.out.println("yeni kelimeyi giriniz.");
						String yeniKelime = scanner.nextLine();
						fileWriter.write("\n");
						fileWriter.write(kelime);
						fileWriter.write(":");
						fileWriter.write(yeniKelime);
						fileWriter.flush();
						
						sortedMap.put(kelime, yeniKelime);
					}
					
				}
				System.out.print("kelime giriniz:");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (fileWriter != null) {
					fileWriter.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		scanner.close();
	}

}

