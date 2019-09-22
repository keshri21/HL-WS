package com.hlws.enums;

/**
 * Enum to represent current payment status of a bilty
 * CREATED - first state when bilty is created
 * READY_FOR_PAYMENT - When freight has been calculated
 * INITIATED - when payment instruction is generated
 * DONE - when instruction is executed by bank
 * @author keshr
 *
 */
public enum BiltyPaymentStatus {
	CREATED(0),
	READY_FOR_PAYMENT(1),
	INITIATED(2),
	DONE(3);
	
	private int status_code;
	private BiltyPaymentStatus(int code) {
		this.status_code = code;
	}
	
	public BiltyPaymentStatus getStatus(int code) {
		for(BiltyPaymentStatus status : BiltyPaymentStatus.values()) {
			if(status.status_code == code) {
				return status;
			}
		}
		return CREATED; 
	}
	
	public int getStatusCode() {
		return this.status_code;
	}
}
