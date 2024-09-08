import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyClass {

	public static List<Integer> convertToASCII(String message, int n, int e) {
		List<Integer> numberValues = new ArrayList<>();
		for (int i = 0; i < message.length(); i++) {
			char character = message.charAt(i);
			int number = (int) character - 64;
			int encryptedNumber = (int) (Math.pow(number, e) % n);
			numberValues.add(encryptedNumber);
		}
		return numberValues;
	}
	public static String decryptMessageList(List<Integer> message, double n, double d) {
		String decryptedMessage = "";
		for (int i = 0; i < message.size(); i ++) {
			String number = message.get(i).toString();
			int numberInt = Integer.parseInt(number);
			String decryptedNumber = new DecimalFormat("0").format(Math.pow(numberInt, d) % n);
			decryptedMessage += (char) (Integer.parseInt(decryptedNumber) + 64);
		}
		return decryptedMessage;
	}
	public static List<Integer> rsaEncrypt(String messageToEncrypt) {
		int p = 11;
		int q = 3;
		int n = p * q; // public key (n = 33 e = 3)
		int e = 3;
		int d = 7; //private key (n = 33 d = 11)
		

		List<Integer> encryptedMessageList = convertToASCII(messageToEncrypt, n, e);


		return encryptedMessageList;
	}
	public static String rsaDecrypt(List<Integer> encryptedMessageList, double n, double e){

		String decryptedMessage = decryptMessageList(encryptedMessageList, n, e);
		return decryptedMessage;
	}
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a message: ");
		String message = scanner.nextLine();
		System.out.println("Sended Message: " + message);
		List<Integer> encryptedMessage = rsaEncrypt(message);
		String decryptedMessage = rsaDecrypt(encryptedMessage, 33, 7);
		System.out.println("Decrypted Message: " + decryptedMessage);
		scanner.close();
		
	}

}