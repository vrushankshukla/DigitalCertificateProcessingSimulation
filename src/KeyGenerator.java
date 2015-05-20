import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;


public class KeyGenerator {
	
	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	public KeyGenerator() throws NoSuchAlgorithmException{
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		// generating public and private key
		keyGen.initialize(1024);
		KeyPair keypair = keyGen.genKeyPair();
		privateKey = keypair.getPrivate();
		publicKey = keypair.getPublic();
	}
	
	
	public PrivateKey getPrivateKey(){
		return privateKey;
	}
	
	public PublicKey getPublicKey(){
		return publicKey;
	}
}
