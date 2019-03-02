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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hlws.dal.IPanDAL;
import com.hlws.dto.BuiltyDTO;
import com.hlws.enums.PaymentInstructionColumn;
import com.hlws.model.Account;
import com.hlws.model.Pan;

@Component
public class BillHelper {
	
	@Autowired
	IPanDAL panRepository;
	
	private static Map<Integer, ByteArrayInputStream> cached_instruction = new ConcurrentHashMap<>(); 
	
	private Map<Pan, Double> consolidateFreightBill(List<BuiltyDTO> builties){
		Map<Pan, Double> paymentMap = new HashMap<>();
		builties.forEach(builty -> {
			Pan owner = panRepository.getVehicleOwner(builty.getVehicleNo(), builty.getBuiltyDate());
			if(paymentMap.get(owner) != null) {
				paymentMap.put(owner, paymentMap.get(owner)+builty.getFreightBill());
			}else {
				paymentMap.put(owner, builty.getFreightBill());
			}
		});
		return paymentMap;
	}
	
	public Integer generatePaymentInstructionSheet(List<BuiltyDTO> builties) throws IOException {
		Map<Pan, Double> paymentMap = consolidateFreightBill(builties); 
		
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
				cell.setCellStyle(this.getBoldStyle(workbook));
				colIndex++;
			}
					
			Double totalAmount = 0d;
			for (Entry<Pan, Double> entry : paymentMap.entrySet()) {
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
			} //END - for loop paymentMap
			
			Row totalRow = sheet.createRow(rowIndex+2);
			cell = totalRow.createCell(0);
			cell.setCellValue(("TOTAL"));
			cell.setCellStyle(this.getBoldStyle(workbook));
			//totalRow.createCell(0).
			cell = totalRow.createCell(noOfColumns-1, CellType.NUMERIC);
			cell.setCellValue(totalAmount);
			cell.setCellStyle(this.getBoldStyle(workbook));
			//sheet.addMergedRegion(CellRangeAddress.valueOf(cell.getAddress().A1))
			
			workbook.write(out);
			return this.addIntructions(new ByteArrayInputStream(out.toByteArray()));
		} // END - try block
		
		
	}
	
	private CellStyle getBoldStyle(Workbook wb) {
		CellStyle boldStyle = wb.createCellStyle();
		/* Create HSSFFont object from the workbook */
        Font boldFont = wb.createFont();
        /* set the weight of the font */
        boldFont.setBold(true);
        /* attach the font to the style created earlier */
        boldStyle.setFont(boldFont);
        /* At this stage, we have a bold style created which we can attach to a cell */
        
        return boldStyle;
	}
	
	public ByteArrayInputStream getInstructions(Integer cacheKey) {
		return cached_instruction.get(cacheKey);
	}
	
	private Integer addIntructions(ByteArrayInputStream is) {
		Random random = new Random();
		
		Integer cacheKey = 10000000 + random.nextInt(90000000);
		cached_instruction.put(cacheKey, is);
		return cacheKey;
	}
	
	

}
