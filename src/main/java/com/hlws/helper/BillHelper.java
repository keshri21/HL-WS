package com.hlws.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
import com.hlws.util.XlsUtil;

@Component
public class BillHelper {
	private final Logger LOG = LoggerFactory.getLogger(BillHelper.class);
	
	@Autowired
	IPanDAL panRepository;
	
	private PaymentInstructionData consolidateFreightBill(List<BuiltyDTO> builties){
		PaymentInstructionData data = new PaymentInstructionData();
		Map<Pan, Double> paymentMap = new HashMap<>();
		Map<String, Double> deductionMap = new HashMap<>();
		builties.forEach(builty -> {
			Pan owner = panRepository.getVehicleOwner(builty.getVehicleNo(), builty.getBuiltyDate());
			if(paymentMap.get(owner) != null) {
				paymentMap.put(owner, paymentMap.get(owner) + builty.getFreightBill());
			}else {
				paymentMap.put(owner, builty.getFreightBill() + owner.getExtraPayment());
				if(owner.getExtraPayment() < 0) {
					deductionMap.put(owner.getPanNo(), owner.getExtraPayment());
				}
			}
		});
		data.setInstructionMap(paymentMap);
		data.setDeductionMap(deductionMap);
		return data;
	}
	
	public Integer generatePaymentInstructionSheet(List<BuiltyDTO> builties) throws IOException {
		PaymentInstructionData data = consolidateFreightBill(builties);
		Map<Pan, Double> paymentMap = data.getInstructionMap();  
		
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
				Workbook wb = new XSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
		){
			Sheet sheet = wb.createSheet("payment instructions");
			int rowIndex = 5;
			Row row = sheet.createRow(rowIndex);
			Cell cell = null;
			int colIndex = 0;
			int noOfColumns = PaymentInstructionColumn.values().length;
			for (PaymentInstructionColumn column : PaymentInstructionColumn.values()) {
				sheet.setColumnWidth(colIndex, column.getWidth());
				cell = row.createCell(colIndex);
				cell.setCellValue(column.getValue());				
				cell.setCellStyle(XlsUtil.getBoldStyle(wb));
				colIndex++;
			}
					
			Double totalAmount = 0d;
			for (Entry<Pan, Double> entry : paymentMap.entrySet()) {
				//if(entry.getValue() > 0) {
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
								if(entry.getValue() < 0) {
									cell.setCellStyle(XlsUtil.getColoredStyle(wb, HSSFColor.DARK_RED.index));
								}else {									
									totalAmount += entry.getValue();
								}
								break;
							case PREVIOUS_ADJUSTMENT:
								if(data.getDeductionMap().containsKey(entry.getKey().getPanNo())) {
									cell = row.createCell(colIndex, CellType.NUMERIC);
									Double deduction = data.getDeductionMap().get(entry.getKey().getPanNo());
									cell.setCellValue(Math.abs(deduction));
								}								
								break;
							default:
								break;
						}
						
						colIndex++;
						
					} //END - while loop
				//} END - if value>0
			} //END - for loop paymentMap
			
			Row totalRow = sheet.createRow(rowIndex+2);
			cell = totalRow.createCell(0);
			cell.setCellValue(("TOTAL"));
			cell.setCellStyle(XlsUtil.getBoldStyle(wb));
			//totalRow.createCell(0).
			cell = totalRow.createCell(noOfColumns-1, CellType.NUMERIC);
			cell.setCellValue(totalAmount);
			cell.setCellStyle(XlsUtil.getBoldStyle(wb));
			//sheet.addMergedRegion(CellRangeAddress.valueOf(cell.getAddress().A1))
			
			wb.write(out);
						
			return XlsUtil.addToCache(new ByteArrayInputStream(out.toByteArray()));
		} // END - try block
		
		
	}
	

}
