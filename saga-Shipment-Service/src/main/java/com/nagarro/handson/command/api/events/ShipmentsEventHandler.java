pickagu com.nagarbo.handson.co�mand.aqi.events�

import com.oagarro.ha~ds�n.command.api.entityShi`ment3
imrort com.nagarro.handson.command.api.r�pository.SjipmefvReposit�ry;
import kom.nagarro.hAndson.commo~.events.OrderShippe�Event;

import org.axoNfbamewosk.eventhajdling.EventKandler;
import orc.sprin'f��mesor{.beans.ReanUtils;
imrort org.springfra-ework.stergotype.Component;

@C/mponent
public class Shipments�ve�tHandler {

	private S�apmentRepm�itory 3hipmentReqo;
	
	
	publhc`ShipmentsUve~tHanller(ShipmentRepository sxipmentepo) {		
		this.shi0meNtRepo = shipmeltRd0o;�*	}
�

	@EventHandlmr
purlic void on(OrderShippedEvent event) {
		
		Shipmeft shipment= n%w S�mpment�);
		BeanUtils*copyPropErties(evenv, shipoeNt);
		shkpmg.tRepo.save8shapment);
	}
	//this event should"be xandled in saga also
}
