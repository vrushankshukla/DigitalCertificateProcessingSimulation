import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.io.IOUtils;

public class LoginForm implements ActionListener {

	public JFrame frame, certFrame, emailFrame;
	JTextField userText, keyText, publicKeyText, privateKeyText, nameText,countryText, oUnitText, oText,
			locationText, issuerText, validityText, algoText, privKeyText,
			pubKeyText, emailNameText, emailLocationText, emailIssuerText,emailOUText, emailOText, emailCText,
			emailValidityText, emailAlgoText, emailPrivKeyText,
			emailPubKeyText;
	JPasswordField passwordText;
	JLabel userLabel, keyTextLabel, formLabel, passwordLabel, base64Label, oUnitLabel, oLabel,countryLabel,
			base64Key, publicKeyLabel, privateKeyLabel, nameLabel,
			locationLabel, issuerLabel, validityLabel, algoLabel, privKeyLabel,
			pubKeyLabel, certiFormLabel, emailFormLabel, emailNameLabel,
			emailLocLabel, emailIssuerLabel, emailValidityLabel,emailOULabel,emailOLabel,emailCLabel,
			emailAlgoLabel, emailPrivKeyLabel, emailPubKeyLabel;
	JButton loginButton, clearButton, genKeyButton, validateButton,
			createCertificateButton, generateCertiButton, saveAsTextButton,
			emailButton, requestCertificateButton, emailCertiButton;
	JComboBox certiAlgoDropdown, emailAlgoDropdown;

	public void createLoginForm() {
		frame = new JFrame("Generate Key Application");
		frame.setSize(470, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.placeComponents();
		frame.setVisible(true);
	}

	public void createCertificateForm() {
		certFrame = new JFrame("Create Certificate");
		certFrame.setSize(600, 600);
		certFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.placeComponents();
		certFrame.setLocationRelativeTo(null);
		this.placeCertificateFormComponents();
		certFrame.setVisible(true);
	}

	public void createEmailForm() {
		emailFrame = new JFrame("Email Certificate Details");
		emailFrame.setSize(600, 600);
		emailFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.placeComponents();
		emailFrame.setLocationRelativeTo(null);
		this.placeCertificateEmailFormComponents();
		emailFrame.setVisible(true);
	}

	private void placeComponents() {
		frame.setLayout(null);

		formLabel = new JLabel("Please Enter Login Details");
		formLabel.setBounds(120, 10, 200, 25);
		frame.add(formLabel);
	
		userLabel = new JLabel("User");
		userLabel.setBounds(50, 50, 80, 25);
		frame.add(userLabel);

		keyTextLabel = new JLabel("Enter Text");
		keyTextLabel.setBounds(50, 50, 80, 25);
		frame.add(keyTextLabel);
		keyTextLabel.setVisible(false);

		userText = new JTextField(20);
		userText.setBounds(150, 50, 160, 25);
		frame.add(userText);

		keyText = new JTextField(20);
		keyText.setBounds(150, 50, 200, 25);
		frame.add(keyText);
		keyText.setVisible(false);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(50, 80, 80, 25);
		frame.add(passwordLabel);

		base64Label = new JLabel("Base64 Key");
		base64Label.setBounds(50, 90, 80, 25);
		frame.add(base64Label);
		base64Label.setVisible(false);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(150, 80, 160, 25);
		frame.add(passwordText);

		loginButton = new JButton("Login");
		loginButton.setBounds(80, 120, 80, 25);
		frame.add(loginButton);

		clearButton = new JButton("Clear");
		clearButton.setBounds(210, 120, 80, 25);
		frame.add(clearButton);

		genKeyButton = new JButton("Generate Key");
		genKeyButton.setBounds(10, 210, 120, 25);
		frame.add(genKeyButton);
		genKeyButton.setVisible(false);

		validateButton = new JButton("Validate Keys");
		validateButton.setBounds(145, 210, 120, 25);
		frame.add(validateButton);
		validateButton.setVisible(false);

		createCertificateButton = new JButton("Create Certificate");
		createCertificateButton.setBounds(280, 210, 140, 25);
		frame.add(createCertificateButton);
		createCertificateButton.setVisible(false);

		requestCertificateButton = new JButton("Request Certificate");
		requestCertificateButton.setBounds(280, 210, 160, 25);
		frame.add(requestCertificateButton);
		requestCertificateButton.setVisible(false);

		// ActionListener myButtonListener = new FormActionListener();
		loginButton.addActionListener(this);
		clearButton.addActionListener(this);
		genKeyButton.addActionListener(this);
		validateButton.addActionListener(this);
		createCertificateButton.addActionListener(this);
		requestCertificateButton.addActionListener(this);
	}

	private void placeCertificateFormComponents() {
		certFrame.setLayout(null);

		certiFormLabel = new JLabel("Please Enter Certificate Details");
		certiFormLabel.setBounds(150, 10, 200, 25);
		certFrame.add(certiFormLabel);

		nameLabel = new JLabel("Common Name (CN)");
		nameLabel.setBounds(50, 50, 200, 25);
		certFrame.add(nameLabel);
                
                oUnitLabel = new JLabel("Oraganization Unit (OU)");
		oUnitLabel.setBounds(50, 100, 200, 25);
		certFrame.add(oUnitLabel);
                
                oLabel = new JLabel("Oraganization (O)");
		oLabel.setBounds(50, 150, 200, 25);
		certFrame.add(oLabel);

		locationLabel = new JLabel("Location (L)");
		locationLabel.setBounds(50, 200, 200, 25);
		certFrame.add(locationLabel);
                
                countryLabel = new JLabel("Country (C) (Put 2 Character only)");
		countryLabel.setBounds(50, 250, 200, 25);
		certFrame.add(countryLabel);

//		issuerLabel = new JLabel("Issuer");
//		issuerLabel.setBounds(50, 150, 80, 25);
//		certFrame.add(issuerLabel);

		validityLabel = new JLabel("Validity");
		validityLabel.setBounds(50, 300, 200, 25);
		certFrame.add(validityLabel);

		algoLabel = new JLabel("Algorithm");
		algoLabel.setBounds(50, 350, 200, 25);
		certFrame.add(algoLabel);

//		privKeyLabel = new JLabel("Private Key");
//		privKeyLabel.setBounds(50, 300, 80, 25);
//		certFrame.add(privKeyLabel);

		pubKeyLabel = new JLabel("Public Key");
		pubKeyLabel.setBounds(50, 400, 200, 25);
		certFrame.add(pubKeyLabel);

		nameText = new JTextField(100);
		nameText.setBounds(250, 50, 250, 25);
		certFrame.add(nameText);
                
                oUnitText = new JTextField(100);
		oUnitText.setBounds(250, 100, 250, 25);
		certFrame.add(oUnitText);
                
                oText = new JTextField(100);
		oText.setBounds(250, 150, 150, 25);
		certFrame.add(oText);
                
                
		locationText = new JTextField(100);
		locationText.setBounds(250, 200, 250, 25);
		certFrame.add(locationText);
                
                countryText = new JTextField(100);
		countryText.setBounds(250, 250, 250, 25);
		certFrame.add(countryText);
                
//		issuerText = new JTextField(100);
//		issuerText.setBounds(150, 150, 250, 25);
//		certFrame.add(issuerText);

		validityText = new JTextField(100);
		validityText.setBounds(250, 300, 250, 25);
		certFrame.add(validityText);

		certiAlgoDropdown = new JComboBox();
		certiAlgoDropdown.addItem("SHA1withRSA");
		certiAlgoDropdown.setBounds(250, 350, 250, 25);
		certFrame.add(certiAlgoDropdown);

//		privKeyText = new JTextField(300);
//		privKeyText.setBounds(150, 300, 250, 25);
//		certFrame.add(privKeyText);

		pubKeyText = new JTextField(300);
		pubKeyText.setBounds(250, 400, 250, 25);
		certFrame.add(pubKeyText);

		generateCertiButton = new JButton("Generate Certificate");
		generateCertiButton.setBounds(60, 450, 180, 25);
		certFrame.add(generateCertiButton);
		generateCertiButton.addActionListener(this);

		emailCertiButton = new JButton("Email Certificate");
		emailCertiButton.setBounds(250, 450, 180, 25);
		certFrame.add(emailCertiButton);
		emailCertiButton.addActionListener(this);
		emailCertiButton.setEnabled(false);
	}

	private void placeCertificateEmailFormComponents() {
		emailFrame.setLayout(null);

		emailFormLabel = new JLabel("Please Enter Certificate Details");
		emailFormLabel.setBounds(150, 10, 200, 25);
		emailFrame.add(emailFormLabel);

		emailNameLabel = new JLabel("Common Name (CN)");
		emailNameLabel.setBounds(50, 50, 200, 25);
		emailFrame.add(emailNameLabel);
                
                emailOULabel = new JLabel("Organization Unit (OU)");
		emailOULabel.setBounds(50, 100, 200, 25);
		emailFrame.add(emailOULabel);
                
                emailOLabel = new JLabel("Organization (O)");
		emailOLabel.setBounds(50, 150, 200, 25);
		emailFrame.add(emailOLabel);
                
                emailLocLabel = new JLabel("Location (L)");
		emailLocLabel.setBounds(50, 200, 200, 25);
		emailFrame.add(emailLocLabel);

		emailCLabel = new JLabel("Country (C) (Put 2 Character only)");
		emailCLabel.setBounds(50, 250, 200, 25);
		emailFrame.add(emailCLabel);
                
//                emailIssuerLabel = new JLabel("Issuer");
//		emailIssuerLabel.setBounds(50, 150, 80, 25);
//		emailFrame.add(emailIssuerLabel);

		emailValidityLabel = new JLabel("Validity");
		emailValidityLabel.setBounds(50, 300, 200, 25);
		emailFrame.add(emailValidityLabel);

		emailAlgoLabel = new JLabel("Algorithm");
		emailAlgoLabel.setBounds(50, 350, 200, 25);
		emailFrame.add(emailAlgoLabel);

//		emailPrivKeyLabel = new JLabel("Private Key");
//		emailPrivKeyLabel.setBounds(50, 300, 80, 25);
//		emailFrame.add(emailPrivKeyLabel);

		emailPubKeyLabel = new JLabel("Public Key");
//		emailPubKeyLabel.setBounds(50, 350, 80, 25);
		emailPubKeyLabel.setBounds(50, 400, 200, 25);
		emailFrame.add(emailPubKeyLabel);

		emailNameText = new JTextField(100);
		emailNameText.setBounds(250, 50, 250, 25);
		emailFrame.add(emailNameText);
                
                emailOUText = new JTextField(100);
		emailOUText.setBounds(250, 100, 250, 25);
		emailFrame.add(emailOUText);
                
                emailOText = new JTextField(100);
		emailOText.setBounds(250, 150, 250, 25);
		emailFrame.add(emailOText);

		emailLocationText = new JTextField(100);
		emailLocationText.setBounds(250, 200, 250, 25);
		emailFrame.add(emailLocationText);
                
                emailCText = new JTextField(100);
		emailCText.setBounds(250, 250, 250, 25);
		emailFrame.add(emailCText);

//		emailIssuerText = new JTextField(100);
//		emailIssuerText.setBounds(150, 300, 250, 25);
//		emailFrame.add(emailIssuerText);

		emailValidityText = new JTextField(100);
		emailValidityText.setBounds(250, 300, 250, 25);
		emailFrame.add(emailValidityText);

		emailAlgoDropdown = new JComboBox();
		emailAlgoDropdown.addItem("SHA1withRSA");
		emailAlgoDropdown.setBounds(250, 350, 250, 25);
		emailFrame.add(emailAlgoDropdown);

	//	emailPrivKeyText = new JTextField(300);
	//	emailPrivKeyText.setBounds(150, 300, 250, 25);
	//	emailFrame.add(emailPrivKeyText);

		emailPubKeyText = new JTextField(300);
//		emailPubKeyText.setBounds(150, 350, 250, 25);
		emailPubKeyText.setBounds(250, 400, 250, 25);
		emailFrame.add(emailPubKeyText);

		saveAsTextButton = new JButton("Save As Text");
		saveAsTextButton.setBounds(60, 450, 180, 25);
		emailFrame.add(saveAsTextButton);
		saveAsTextButton.addActionListener(this);

		emailButton = new JButton("Email Form");
		emailButton.setBounds(250, 450, 180, 25);
		emailFrame.add(emailButton);
		emailButton.addActionListener(this);
		emailButton.setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source.getText().equalsIgnoreCase("login")) {
			int loginFlag = checkLogin(userText.getText(), new String(
					passwordText.getPassword()));
			if (loginFlag > 0) {
				// JOptionPane.showMessageDialog(source, "Login Successful!");
				replaceLoginFields(loginFlag);
			} else {
				JOptionPane.showMessageDialog(source,
						"Unsuccessful Login! Try Again.");
				clearLoginForm();

			}
		} else if (source.getText().equalsIgnoreCase("Clear")) {
			clearLoginForm();
		} else if (source.getText().equalsIgnoreCase("Generate Key")) {
			generateKey(keyText.getText());
		} else if (source.getText().equalsIgnoreCase("Validate Keys")) {
			int validFlag = validateKeys();
			if (validFlag == 1) {
				JOptionPane.showMessageDialog(source, "Keys Validated!");
	//			replaceLoginFields();
			} else {
				JOptionPane.showMessageDialog(source, "Invalid Keys");
			}
		} else if (source.getText().equalsIgnoreCase("Create Certificate")) {
			createCertificateForm();
		} else if (source.getText().equalsIgnoreCase("Generate Certificate")) {
			int cert_Status = generateCertificate();
			if (cert_Status == 1) {
				JOptionPane.showMessageDialog(source,
						"Certificate Generated Sucessfully!");
				emailCertiButton.setEnabled(true);
				generateCertiButton.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(source,
						"Certificate Generation Error!");
			}
		} else if (source.getText().equalsIgnoreCase("Request Certificate")) {
			createEmailForm();
		} else if (source.getText().equalsIgnoreCase("Save As Text")) {
			try {
				saveAsTextFile();
				saveAsTextButton.setEnabled(false);
				emailButton.setEnabled(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (source.getText().equalsIgnoreCase("Email Form")) {
			emailButton.setEnabled(false);
			int status = sendAsEmail("neetha.nyit.certi@gmail.com",
					"nyit.certificate.auth@gmail.com", "C:\\Certificates\\FormDetails.txt",
					"Certificate Form Details",
					"Please use the attached details to create a certificate.");
			if (status == 1) {
				JOptionPane
						.showMessageDialog(source, "Email Sent Successfully");
			} else {
				emailButton.setEnabled(true);
				JOptionPane.showMessageDialog(source, "Email Error");
			}
		} else if (source.getText().equalsIgnoreCase("Email Certificate")) {
			emailCertiButton.setEnabled(false);
			int status = sendAsEmail(
					"nyit.certificate.auth@gmail.com",
					"neetha.nyit.certi@gmail.com",
					"C:\\Certificates\\GeneratedCert.cer,C:\\Certificates\\CertificateDetails.pdf",
					"Generated Certificate",
					"Hi, Certificate you requested, and a details document is attached. ");
			if (status == 1) {
				JOptionPane
						.showMessageDialog(source, "Email Sent Successfully");
			} else {
				emailButton.setEnabled(true);
				JOptionPane.showMessageDialog(source, "Email Error");
			}
		}

	}

	public int checkLogin(String userName, String password) {
		if (userName.equals("User") && password.equals("password")) {
			return 1;
		}else if (userName.equals("Admin") && password.equals("password")) {
				return 2;
		} else {
			return 0;
		}
	}

	public void clearLoginForm() {
		userText.setText("");
		passwordText.setText("");
	}

	public void replaceLoginFields(int loginFlag) {
		formLabel.setVisible(false);
		userLabel.setVisible(false);
		userText.setVisible(false);
		passwordText.setVisible(false);
		passwordLabel.setVisible(false);
		loginButton.setVisible(false);
		clearButton.setVisible(false);
		// formLabel.setText("Enter Text to generate key");
		keyTextLabel.setVisible(true);
		keyText.setVisible(true);
		genKeyButton.setVisible(true);
		validateButton.setVisible(true);
		if(loginFlag == 1){
			createCertificateButton.setVisible(false);
			requestCertificateButton.setVisible(true);
		}else if (loginFlag == 2){
			createCertificateButton.setVisible(true);
			requestCertificateButton.setVisible(false);
		}
	}

	public void generateKey(String userInput) {
		try {
			Base64Converter base64Converter = new Base64Converter();
			base64Key = new JLabel(base64Converter.encodetoBase64(userInput));
			base64Key.setBounds(150, 90, 160, 25);
			frame.add(base64Key);
			base64Label.setVisible(true);
			base64Key.setVisible(true);

			KeyGenerator kg = new KeyGenerator();
			Key prKey = kg.getPrivateKey();
			Key pbKey = kg.getPublicKey();

			writeKeyToFile(base64Converter.encodetoBase64(prKey.getEncoded()), "PrivateKeyFile");
			writeKeyToFile(base64Converter.encodetoBase64(pbKey.getEncoded()), "PublicKeyFile");
			
			publicKeyLabel = new JLabel("Public Key");
			publicKeyLabel.setBounds(50, 130, 80, 25);
			frame.add(publicKeyLabel);

			publicKeyText = new JTextField(base64Converter.encodetoBase64(pbKey
					.getEncoded()));
			publicKeyText.setBounds(150, 130, 200, 25);
			frame.add(publicKeyText);
			publicKeyText.setEditable(false);

			privateKeyLabel = new JLabel("Private Key");
			privateKeyLabel.setBounds(50, 170, 80, 25);
			frame.add(privateKeyLabel);

			privateKeyText = new JTextField(base64Converter
					.encodetoBase64(prKey.getEncoded()));
			privateKeyText.setBounds(150, 170, 200, 25);
			frame.add(privateKeyText);
			privateKeyText.setEditable(false);

			genKeyButton.setEnabled(false);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void writeKeyToFile(String key, String filename) {
		try {
			FileWriter w = new FileWriter(
					"C:\\Certificates\\"+filename+".txt");
			BufferedWriter bw = new BufferedWriter(w);
			bw.write(key);
			bw.flush();
			bw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int validateKeys() {
		try {
			FileReader publicKeyfile = new FileReader("C:\\Certificates\\PublicKeyFile.txt");
			Base64Converter base64Converter = new Base64Converter();
			String pbKey = publicKeyText.getText();
			String prKey = privateKeyText.getText();

			BufferedReader  pvKeyReader = new BufferedReader(new FileReader("C:\\Certificates\\PrivateKeyFile.txt")); 
			String privKeyFile = pvKeyReader.readLine();
			if(privKeyFile == null){
				return 0;
			}
			
			BufferedReader  pbKeyReader = new BufferedReader(new FileReader("C:\\Certificates\\PublicKeyFile.txt")); 
			String pubKeyFile = pbKeyReader.readLine();
			if(pubKeyFile == null){
				return 0;
			}
			if (privKeyFile.equals(prKey)
					&& pubKeyFile.equals(pbKey)) {
				return 1;
			} else {
				return 0;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int generateCertificate() {
		String name = nameText.getText();
                String ou = oUnitText.getText();
                String o = oText.getText();
		String location = locationText.getText();
                String c = countryText.getText();
		int validity = Integer.parseInt(validityText.getText());
		String algo = certiAlgoDropdown.getSelectedItem().toString();
//		String prKey = privKeyText.getText();
		String pbKey = pubKeyText.getText();
		Base64Converter bc = new Base64Converter();

		String dn = "CN=" + name +", OU=" + ou + ", O="+ o + ", L=" + location + ", C=" + c;
		try {
			GenerateCertificate mc = new GenerateCertificate();
			KeyFactory kf = KeyFactory.getInstance("RSA");
//			PrivateKey priv = kf.generatePrivate(new PKCS8EncodedKeySpec(bc
//					.decodeBase64ToByte(prKey)));
			PublicKey pub = kf.generatePublic(new X509EncodedKeySpec(bc
					.decodeBase64ToByte(pbKey)));
//			KeyPair kp = new KeyPair(pub, priv);
			X509Certificate rootCertificate = mc.generateUserCertificate(dn, pub,
					validity, algo);
			String psB64Certificate = bc.encodetoBase64(rootCertificate
					.getEncoded());
			CreatePDFDoc pdfDoc = new CreatePDFDoc();
			pdfDoc.createPDFDocument(rootCertificate.toString(),
					"C:\\Certificates\\CertificateDetails.pdf");
			FileWriter w = new FileWriter(
					"C:\\Certificates\\CertificateIn64.txt");

			// PrintWriter p = new PrintWriter(w);
			BufferedWriter bw = new BufferedWriter(w);
			bw.write(psB64Certificate);
			bw.flush();
			bw.close();
			File file = new File("C:\\Certificates\\CertificateIn64.txt");
			// handler to your ZIP file
			File file2 = new File("C:\\Certificates\\GeneratedCert.cer");
			// destination dir of your file
			file.renameTo(file2);
			return 1;
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	public void saveAsTextFile() throws IOException {

		String fileString = "Common Name (CN):" + emailNameText.getText() + "\n";
		
                fileString = fileString + "Organization Unit (OU):" + emailOUText.getText() + "\n";
                fileString = fileString + "Organization (O):" + emailOText.getText() + "\n";
                
                fileString = fileString + "Location: (L)" + emailLocationText.getText() + "\n";
		fileString = fileString + "Country (C):" + emailCText.getText() + "\n";
		fileString = fileString + "Validity:" + emailValidityText.getText() + "\n";
		fileString = fileString + "Algorithm:" + emailAlgoDropdown.getSelectedItem() + "\n";
//		fileString = fileString + "Private Key:" + emailPrivKeyText.getText()
//				+ "\n";
		fileString = fileString + "Public Key:" + emailPubKeyText.getText();
		File file = new File("C:\\Certificates\\FormDetails.txt");

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(fileString);
		bw.close();
	}

	public int sendAsEmail(String from, String to, String fileName,
			String subject, String body) {
		SendFileEmail s = new SendFileEmail();
		return s.sendEmail(from, to, fileName, subject, body);
	}
}