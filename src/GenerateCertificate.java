import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import sun.security.x509.*;
import java.security.cert.*;
import java.security.*;
import java.math.BigInteger;
import java.util.Date;
import java.io.IOException;
import java.io.OutputStreamWriter;
import sun.misc.BASE64Encoder;
 
/** 
 * Create a self-signed X.509 Certificate
 * @param dn the X.509 Distinguished Name, eg "CN=Test, L=London, C=GB"
 * @param pair the KeyPair
 * @param days how many days from now the Certificate is valid for
 * @param algorithm the signing algorithm, eg "SHA1withRSA"
 */ 
public class GenerateCertificate {
    PrivateKey caPKey;
    KeyPair CAkeypair;
    public GenerateCertificate() throws NoSuchAlgorithmException {
          KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        CAkeypair= keyGen.genKeyPair();
        caPKey = CAkeypair.getPrivate();
    }
    String dnCA = "CN=Dr. Bill(www.tfbor.com), OU=CS, O=Dr. Bill, L=New York, C=US";
    X509Certificate rootCertificate;
    public static void main(String[] args) throws NoSuchAlgorithmException, GeneralSecurityException, IOException {
 
        int day = 365;
        
        String algo = "SHA1withRSA"; 
       
        GenerateCertificate  generateRootCertificate = new GenerateCertificate();
        
        //calling 1st method to generate root certificate using CA public and private key
        generateRootCertificate.rootCertificate= generateRootCertificate.generateCertificate(generateRootCertificate.dnCA, generateRootCertificate.CAkeypair, day, algo);

        //Creating an Object of BASE64Encoder class
        BASE64Encoder encoder=new BASE64Encoder();
        
        //Encoding the whole root certificate into Base64
        String rootB64Certificate = encoder.encodeBuffer(generateRootCertificate.rootCertificate.getEncoded());
        
        //creating txt file to save Base64 string of root certificate
        FileWriter w = new FileWriter("C:\\Certificates\\root.txt");
        BufferedWriter bw = new BufferedWriter(w);
        bw.write(rootB64Certificate);
        bw.flush();
        bw.close();
        //Now saving root.txt file as root.cer
        File file  = new File("C:\\Certificates\\root.txt"); // handler to your ZIP file
        File file2 = new File("C:\\Certificates\\root.cer"); // destination dir of your file
        file.renameTo(file2);
   
    }
    
    //this is a method which generate x.509 version 3 certificate using input
  
    X509Certificate generateCertificate(String dn, KeyPair pair, int days, String algorithm)
        throws GeneralSecurityException, IOException {
        PrivateKey privkey = pair.getPrivate();
        X509CertInfo info = new X509CertInfo();//creating an X509CertInfo object
        Date from = new Date();
        Date to = new Date(from.getTime() + days * 86400000l);
        CertificateValidity interval = new CertificateValidity(from, to);
        BigInteger sn = new BigInteger(64, new SecureRandom());
        X500Name owner = new X500Name(dn);
        
        //now set all the information of X509CertInfo class 
        
        info.set(X509CertInfo.VALIDITY, interval);
        info.set(X509CertInfo.SERIAL_NUMBER, new CertificateSerialNumber(sn));
        info.set(X509CertInfo.SUBJECT, new CertificateSubjectName(owner));
        info.set(X509CertInfo.ISSUER, new CertificateIssuerName(owner));
        info.set(X509CertInfo.KEY, new CertificateX509Key(pair.getPublic()));
        info.set(X509CertInfo.VERSION, new CertificateVersion(CertificateVersion.V3));
        AlgorithmId algo = new AlgorithmId(AlgorithmId.md5WithRSAEncryption_oid);
        info.set(X509CertInfo.ALGORITHM_ID, new CertificateAlgorithmId(algo));
              CertificateExtensions extensions = new CertificateExtensions();
        BasicConstraintsExtension bce = new BasicConstraintsExtension(true, -1);
        extensions.set(BasicConstraintsExtension.NAME, new BasicConstraintsExtension(false, bce.getExtensionValue()));
        info.set(X509CertInfo.EXTENSIONS, extensions);
        // Sign the cert to identify the algorithm that's used.
        X509CertImpl cert = new X509CertImpl(info);
        cert.sign(privkey, algorithm);
 
        // Update the algorith, and resign.
        algo = (AlgorithmId)cert.get(X509CertImpl.SIG_ALG);
        info.set(CertificateAlgorithmId.NAME + "." + CertificateAlgorithmId.ALGORITHM, algo);
        cert = new X509CertImpl(info);
        cert.sign(privkey, algorithm);
        return cert;
    }   
    
    X509Certificate generateUserCertificate(String userdn, PublicKey userKey, int days, String algorithm)
        throws GeneralSecurityException, IOException {
//        PrivateKey privkey = caPKey;
        X509CertInfo info = new X509CertInfo();//creating an X509CertInfo object
        Date from = new Date();
        Date to = new Date(from.getTime() + days * 86400000l);
        CertificateValidity interval = new CertificateValidity(from, to);
        BigInteger sn = new BigInteger(64, new SecureRandom());
        X500Name owner = new X500Name(this.dnCA);
        X500Name user = new X500Name(userdn);
        
        //now set all the information of X509CertInfo class 
        
        info.set(X509CertInfo.VALIDITY, interval);
        info.set(X509CertInfo.SERIAL_NUMBER, new CertificateSerialNumber(sn));
        info.set(X509CertInfo.SUBJECT, new CertificateSubjectName(user));
        info.set(X509CertInfo.ISSUER, new CertificateIssuerName(owner));
        info.set(X509CertInfo.KEY, new CertificateX509Key(userKey));
        info.set(X509CertInfo.VERSION, new CertificateVersion(CertificateVersion.V3));
        AlgorithmId algo = new AlgorithmId(AlgorithmId.md5WithRSAEncryption_oid);
        info.set(X509CertInfo.ALGORITHM_ID, new CertificateAlgorithmId(algo));
        
        CertificateExtensions extensions = new CertificateExtensions();
        BasicConstraintsExtension bce = new BasicConstraintsExtension(true, -1);
        extensions.set(BasicConstraintsExtension.NAME, new BasicConstraintsExtension(false, bce.getExtensionValue()));
        info.set(X509CertInfo.EXTENSIONS, extensions);
        // Sign the cert to identify the algorithm that's used.
        X509CertImpl cert = new X509CertImpl(info);
        cert.sign(caPKey, algorithm);
 
        // Update the algorith, and resign.
        algo = (AlgorithmId)cert.get(X509CertImpl.SIG_ALG);
        info.set(CertificateAlgorithmId.NAME + "." + CertificateAlgorithmId.ALGORITHM, algo);
        cert = new X509CertImpl(info);
        cert.sign(caPKey, algorithm);
        return cert;
    }   
}
