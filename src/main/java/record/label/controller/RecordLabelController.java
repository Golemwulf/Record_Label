package record.label.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import record.label.service.RecordLabelService;

@RestController
@RequestMapping("/record_label")
@Slf4j
public class RecordLabelController {

	public RecordLabelController (RecordLabelService service) {
		
	}
}
 