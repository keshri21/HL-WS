package com.hlws.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hlws.dal.IPanDAL;
import com.hlws.dto.BuiltyDTO;
import com.hlws.enums.PaymentInstructionColumn;
import com.hlws.model.Account;
import com.hlws.model.Pan;
import com.hlws.rest.resource.BuiltyResource;
import com.hlws.util.XlsUtil;

@Component
public class BillHelper {
	private final Logger LOG = LoggerFactory.getLogger(BillHelper.class);
	
	@Autowired
	IPanDAL panRepository;
	
	private Map<Pan, Double> consolidateFreightBill(List<BuiltyDTO> builties){
		Map<Pan, Double> paymentMap = new HashMap<>();
		builties.forEach(builty -> {
			Pan owner = panRepository.getVehicleOwner(builty.getVehicleNo(), builty.getBuiltyDate());
			if(paymentMap.get(owner) != null) {
				paymentMap.put(owner, paymentMap.get(owner)+builty.getFreightBill());
			}else {
				paymentMap.put(owner, builty.getFreightBill() + owner.getExtraPayment());
			}
		});
		return paymentMap;
	}
	
	public Integer generatePaymentInstructionSheet(List<BuiltyDTO> builties) throws IOException {
		Map<Pan, Double> paymentMap = consolidateFreightBill(builties); 
		
		// first update extraPayment for pan
		for (Entry<Pan, Double> entry : paymentMap.entrySet()) {
			//if its negative put actual value so it can be adjusted in future payments
			if(entry.getValue() < 0) {
				LOG.warn("Updating extrapayment for PAN: [{}], Extrapayment: [{}]", entry.getKey().getPanNo(), entry.getValue());
				panRepository.updateExtraPayment(entry.getKey().getPanNo(), entry.getValue());
			}else { // if positive update extra payment to 0
				panRepository.updateExtraPayment(entry.getKey().getPanNo(), 0d);
			}
		}
		
		try(
				Workbook workbook = new XSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
		){
			Sheet sheet = workbook.createSheet("payment instructions");
			int rowIndex = 5;
			Row row = sheet.createRow(rowIndex);
			Cell cell = null;
			int colIndex = 0;
			int noOfColumns = PaymentInstructionColumn.values().length;
			for (PaymentInstructionColumn column : PaymentInstructionColumn.values()) {
				sheet.setColumnWidth(colIndex, column.getWidth());
				cell = row.createCell(colIndex);
				cell.setCellValue(column.getValue());				
				cell.setCellStyle(XlsUtil.getBoldStyle(workbook));
				colIndex++;
			}
					
			Double totalAmount = 0d;
			for (Entry<Pan, Double> entry : paymentMap.entrySet()) {
				if(entry.getValue() > 0) {
					rowIndex++;
					colIndex = 0;
					row = sheet.createRow(rowIndex);
					
					Account account = entry.getKey().getPrimaryAccount();
					if(null == account) {
						continue; //TODO decide what to do with null primary account. skip or break
					}
					while(colIndex < noOfColumns) {	
						PaymentInstructionColumn column = PaymentInstructionColumn.values()[colIndex];
						
						switch (column) {
							case BENEFICIARY:
								cell = row.createCell(colIndex, CellType.STRING);
								cell.setCellValue(account.getAccountHoldername());							
								break;
							case TRANSACTION_PARTICULARS:
								cell = row.createCell(colIndex, CellType.STRING);
								cell.setCellValue(entry.getKey().getPanNo());
								break;
							case BENEFICIARY_ACCOUNT:
								cell = row.createCell(colIndex, CellType.NUMERIC);
								cell.setCellValue(account.getAccountNo());
								break;
							case IFSC:
								cell = row.createCell(colIndex, CellType.STRING);
								cell.setCellValue(account.getIfscCode());
								break;
							case BRANCH:
								cell = row.createCell(colIndex, CellType.STRING);
								cell.setCellValue(account.getBranchName());
								break;
							case AMOUNT:
								cell = row.createCell(colIndex, CellType.NUMERIC);
								cell.setCellValue(entry.getValue());
								totalAmount += entry.getValue();
								break;
							default:
								break;
						}
						
						colIndex++;
						
					} //END - while loop
				}
			} //END - for loop paymentMap
			
			Row totalRow = sheet.createRow(rowIndex+2);
			cell = totalRow.createCell(0);
			cell.setCellValue(("TOTAL"));
			cell.setCellStyle(XlsUtil.getBoldStyle(workbook));
			//totalRow.createCell(0).
			cell = totalRow.createCell(noOfColumns-1, CellType.NUMERIC);
			cell.setCellValue(totalAmount);
			cell.setCellStyle(XlsUtil.getBoldStyle(workbook));
			//sheet.addMergedRegion(CellRangeAddress.valueOf(cell.getAddress().A1))
			
			workbook.write(out);
						
			return XlsUtil.addToCache(new ByteArrayInputStream(out.toByteArray()));
		} // END - try block
		
		
	}
	

}
