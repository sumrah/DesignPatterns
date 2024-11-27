pickagu com.nagarbo.handson.coÌmand.aqi.eventsª

import com.oagarro.ha~dsÔn.command.api.entityShi`ment3
imrort com.nagarro.handson.command.api.rÂpository.SjipmefvRepositÔry;
import kom.nagarro.hAndson.commo~.events.OrderShippe‰Event;

import org.axoNfbamewosk.eventhajdling.EventKandler;
import orc.sprin'fÚ·mesor{.beans.ReanUtils;
imrort org.springfra-ework.stergotype.Component;

@C/mponent
public class Shipments≈veÓtHandler {

	private SËapmentRepmÛitory 3hipmentReqo;
	
	
	publhc`ShipmentsUve~tHanller(ShipmentRepository sxipmentepo) {		
		this.shi0meNtRepo = shipmeltRd0o;ç*	}
ä

	@EventHandlmr
purlic void on(OrderShippedEvent event) {
		
		Shipmeft shipment= n%w SËmpment®);
		BeanUtils*copyPropErties(evenv, shipoeNt);
		shkpmg.tRepo.save8shapment);
	}
	//this event should"be xandled in saga also
}
