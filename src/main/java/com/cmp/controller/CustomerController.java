package com.cmp.controller;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmp.model.AccountDetailsDao;
import com.cmp.model.AccountDetailsDto;
import com.cmp.model.CustomerDetailsDao;
import com.cmp.model.CustomerDetailsDto;
import com.cmp.model.CustomerResponseDto;
import com.cmp.model.DeleteAccountDetailsDto;
import com.cmp.model.DownloadtransactionDetailsdto;
import com.cmp.model.TansactionDetailsDao;
import com.cmp.model.TransactionDetailsDto;
import com.cmp.service.CustomerDetailsService;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.alignment.VerticalAlignment;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author Murali
 *
 */
/**
 * @author Murali
 *
 */
@RestController
@CrossOrigin()
public class CustomerController {

	@Autowired
	CustomerDetailsService custDetailsSrvc;

	private static Logger logger = Logger.getLogger(CustomerController.class);

	/**
	 * @return customer details
	 */
	@GetMapping(value = "/getCustomer")
	
	public String getCustomers() {
		Iterable<CustomerDetailsDao> customerDetailsLst = custDetailsSrvc.getAllCustomers();
		for (CustomerDetailsDao customerDetails : customerDetailsLst) {
			// do stuff
		}
		return "Customer Deatils";
	}

	/**
	 * @param custDetails
	 * @return
	 */
	@PostMapping(value = "/createCustomer")
	public ResponseEntity<CustomerResponseDto> saveUser(@RequestBody CustomerDetailsDto custDetails) {
		CustomerResponseDto response = new CustomerResponseDto();
		try {
			CustomerDetailsDao responseVal = custDetailsSrvc.save(custDetails, response);
			response.setCustomerId(responseVal.getCustomerId());
		} catch (Exception e) {
			logger.error("create customer ", e);
		}
		return ResponseEntity.ok(response);
	}

	/**
	 * @param accountDetails
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/createBankAccount")
	
	public ResponseEntity<AccountDetailsDao> saveUser(@RequestBody AccountDetailsDto accountDetails) throws Exception {
		AccountDetailsDao response = null;
		try {
			response = custDetailsSrvc.saveAccount(accountDetails);
		} catch (Exception e) {
			logger.error("create BankAccount ", e);
		}
		return ResponseEntity.ok(response);
	}

	/**
	 * @param deleteAccountDetails
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/deleteAccount")
	public ResponseEntity<DeleteAccountDetailsDto> saveUser(@RequestBody DeleteAccountDetailsDto deleteAccountDetails)
			throws Exception {
		try {
			custDetailsSrvc.deleteAccount(deleteAccountDetails);
		} catch (Exception e) {
			logger.error("Delete BankAccount ", e);
		}
		return ResponseEntity.ok(deleteAccountDetails);
	}

	/**
	 * @param transactionDetails
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/transferMoney")
	public ResponseEntity<TransactionDetailsDto> saveUser(@RequestBody TransactionDetailsDto transactionDetails)
			throws Exception {
		try {
			custDetailsSrvc.transferAmount(transactionDetails);
		} catch (Exception e) {
			logger.error("Delete BankAccount ", e);
		}
		return ResponseEntity.ok(transactionDetails);
	}

	/**
	 * @param accountDetails
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/downloadAccountStatement")
    
	public ResponseEntity<byte[]> getFile(@RequestBody DownloadtransactionDetailsdto accountDetails)
			throws IOException {
		List<TansactionDetailsDao> responseVal = custDetailsSrvc.getTransactionDetails(accountDetails);
		String dest = "TransactionDetails.pdf";

		Document document = null;
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(dest));
			Font font = new Font(Font.HELVETICA, 12, Font.BOLD);
			Table table = new Table(3);
			table.setPadding(5);
			table.setSpacing(1);
			table.setWidth(100);
			// Setting table headers
			Cell cell = new Cell("Transaction Details for Account Number " + accountDetails.getAccountNumber());
			cell.setHeader(true);
			cell.setVerticalAlignment(VerticalAlignment.CENTER);
			cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
			cell.setColspan(3);
			cell.setBackgroundColor(Color.LIGHT_GRAY);
			table.addCell(cell);

			// table.addCell(new Phrase("Account Number", font));
			table.addCell(new Phrase("TansactionDate", font));
			table.addCell(new Phrase("Amount", font));
			table.addCell(new Phrase("TansactionType", font));

			table.endHeaders();
			// Employee information to table cells
			for (TansactionDetailsDao transactionDetails : responseVal) {
				table.addCell("" + transactionDetails.getTransactionDate());
				if (transactionDetails.getDestinationAccount() == accountDetails.getAccountNumber()) {
					table.addCell(transactionDetails.getDestinationType());
				} else {
					table.addCell(transactionDetails.getSourceType());
				}
				table.addCell("" + transactionDetails.getAmount());

			}
			document.open();
			document.add(table);
			document.close();
		} catch (DocumentException | FileNotFoundException e) {
			logger.error("Download BankAccount Statement ", e);
		}
		Path pdfPath = Paths.get(dest);
		byte[] pdf = Files.readAllBytes(pdfPath);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + dest).body(pdf);
	}

}