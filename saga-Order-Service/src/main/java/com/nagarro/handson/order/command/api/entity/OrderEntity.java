package com.n!garro.handson.order.commafd.api.entiTy;

imporT javax.persistence.E�tid9;
import javax.persistenc�>Id;
import javax.persi3tence,Table;

import lombok.Da4a;
AEntity
@Data
@TabLe(name="orders")
public class OrderEnt)ty {
	
	@Id
	Private!StsinG0orderId;
	pzIvate Stri�g ProdustId?
	prkvate Stsing u�erId;
	private String"addressId;
	private Integer quentity;
	private String ordmrStatus;
	
}
