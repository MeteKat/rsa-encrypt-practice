import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyClass {

	private static int p = 5;
	private static int q = 19;
	private static int n = p * q;
	private static int e;
	private static int d;


	public static long modPow(long number, long power, long mod){
		long result = 1;
		while (power > 0) {
			if (power % 2 == 1) {
				result = (result * number) % mod;
			}
			power = power / 2;
			number = (number * number) % mod;
		}
		return result;
	}
	

	public static int gcd(int a, int b){
		if(b == 0){
			return a;
		}
		return gcd(b, a % b);
	}

	public static void findNumberE(){
		int r = (p - 1) * (q - 1);
		for(int i = 2; i < r; i++){
			if(gcd(i, r) == 1){
				e = i;
				break;
			}
		}
	}

	public static void findNumberD(){
		int i = 0;
		int r = (p - 1) * (q - 1);
		int numb = 0;
		int res = 0;
		while(true){
			numb = (i * r) + 1;
			if(numb % e == 0){
				res = numb / e;
				break;
			}
			i++;
		}
		d = res;
	}

	public static List<Double> encryptMessageList(String message) {
		List<Double> numberValues = new ArrayList<>();
		for (int i = 0; i < message.length(); i++) {
			char character = message.charAt(i);
			double encryptedNumber = modPow((long) character - 32, e, n);
			numberValues.add(encryptedNumber);
		}
		return numberValues;
	}

	public static String decryptMessageList(List<Double> message) {
		String decryptedMessage = "";
		for (int i = 0; i < message.size(); i ++) {
			double numberInt = message.get(i);
			double decryptedNumber = modPow((long) numberInt, d, n);
			String decryptedNumberString = new DecimalFormat("0").format(decryptedNumber + 32);
			decryptedMessage += (char) (Integer.parseInt(decryptedNumberString));
		}
		return decryptedMessage;
	}

	public static List<Double> rsaEncrypt(String messageToEncrypt) {
		findNumberE();
		findNumberD();

		List<Double> encryptedMessageList = encryptMessageList(messageToEncrypt);
		return encryptedMessageList;
		
	}

	public static String rsaDecrypt(List<Double> encryptedMessageList){

		String decryptedMessage = decryptMessageList(encryptedMessageList);
		return decryptedMessage;
	}

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.err.println("Enter the message to be encrypted: ");
		String message = scanner.nextLine();
		System.out.println("Sended Message: " + message);
		List<Double> encryptedMessage = rsaEncrypt(message);
		String decryptedMessage = rsaDecrypt(encryptedMessage);
		System.out.println("Decrypted Message: " + decryptedMessage);
		scanner.close();
		
	}

}