package server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import db.DB;
import db.table.Storage;

@RestController
public class MainWeb {

	@Autowired
	private Storage storage;

	@RequestMapping("/greeting")
	public String greeting() throws Exception {
		String ss = "";
		for (DB dd : storage.getDbs()) {
			ss += dd.toString();
		}
		return ss;
	}
}
